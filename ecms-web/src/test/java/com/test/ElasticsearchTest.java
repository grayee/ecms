package com.test;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;

//import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

/**
 * @author Gray.Zhang
 * @date 2017/9/20.
 */
public class ElasticsearchTest {

    private static final String INDEX_NAME = "test.incident";

    public enum DocType {
        NETWORK, ENDPOINT, DISCOVERY
    }

    @Test
    public void test() throws IOException {
        //get transport client
        Client client = getClient();
        testCreateIndex(client, INDEX_NAME);
        testCreateDocument(client);
        testDeleteByQuery(client);
    }

    private void testCreateDocument(Client client) {
        try {
            for (int i = 0; i < 20; i++) {
                indexDocument(client, INDEX_NAME, "network-incident", i * 50000, (i - 1));
                //indexDocument(client, indexName, "endpoint-incident", 1000);
                //indexDocument(client, indexName, "discovery-incident", 1000);
            }

        } catch (IOException e) {
            System.out.println("index document occur a io exception" + e);
        }
    }

    private void testDeleteByQuery(Client client) {
        Instant startTime = Instant.now();
        AggregationBuilder filterAggBuilder = AggregationBuilders.filter("filterAgg",
            QueryBuilders.termsQuery("channelType", Lists.newArrayList(1, 2)));
        ValueCountAggregationBuilder valueCountAggBuilder = AggregationBuilders.count("valueCountAgg")
            .field("channelType");
        SearchResponse searchResponse = client.prepareSearch(INDEX_NAME).addAggregation(filterAggBuilder)
            .addAggregation(valueCountAggBuilder).get();
        ValueCount valueCountAgg = searchResponse.getAggregations().get("valueCountAgg");
        long value = valueCountAgg.getValue();

        Filter filterAgg = searchResponse.getAggregations().get("filterAgg");
        long docCount = filterAgg.getDocCount(); // Doc count

        Instant endTime = Instant.now();
        Interval duration = new Interval(startTime, endTime);
        System.out.println("query " + docCount + " document cost time :" + duration.toDuration());

        Instant deleteStartTime = Instant.now();
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
            .filter(QueryBuilders.termsQuery("channelType", Lists.newArrayList(1, 2)))
            .source(INDEX_NAME)
            .get();
        long deleted = response.getDeleted();
        Instant deleteEndTime = Instant.now();
        Interval deleteDuration = new Interval(deleteStartTime, deleteEndTime);
        System.out.println("delete " + deleted + " document cost time :" + deleteDuration.toDuration());
    }

    public void testCreateIndex(Client client, String indexName) {
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        System.out.println("create client " + client.toString() + " success...");
        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse indicesExistsResponse = indicesAdminClient.exists(indicesExistsRequest).actionGet();

        if (!indicesExistsResponse.isExists()) {
            System.out.println("index " + indexName + " not exist...");
            Settings indexCreateSettings = Settings.builder()
                .put("index.number_of_shards", 2)
                .put("index.number_of_replicas", 3).build();

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName, indexCreateSettings);
            try {
                createIndexRequest.mapping("network-incident", FileUtils
                    .readFileToString(ResourceUtils.getFile("classpath:network_incident_mappings.json"),
                        Charset.defaultCharset()), XContentType.JSON);
            } catch (IOException e) {
                System.out.println("create mappings happened an exception" + e.getMessage());
            }

            ActionFuture<CreateIndexResponse> createFuture = indicesAdminClient.create(createIndexRequest);
            CreateIndexResponse indexCreateResponse = createFuture.actionGet();
            System.out.println(String.format("index create response is acknowledged :%s,shards acked:%s",
                indexCreateResponse.isAcknowledged(), indexCreateResponse.isAcknowledged()));
        } else {
            System.out.println("index " + indexName + " is already exist!");
            indicesAdminClient.prepareRefresh(indexName).get();
            indicesAdminClient.prepareDelete(indexName).get();
            System.out.println("index " + indexName + " is deleted successful!");
        }

        String[] indices = indicesAdminClient.prepareGetIndex().get().indices();
        System.out.println("indices count:" + indices.length + ",detail:" + Joiner.on(",").join(indices));

        GetSettingsResponse response = indicesAdminClient.prepareGetSettings(indices).get();
        for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
            String index = cursor.key;
            Settings settings = cursor.value;
            Integer shards = settings.getAsInt("index.number_of_shards", null);
            Integer replicas = settings.getAsInt("index.number_of_replicas", null);
            System.out.println("current index:" + index + ",shards:" + shards + ",replicas:" + replicas);
        }

    }

    private Client getClient() {
        return getTransportClient(Lists.newArrayList("192.168.137.164"), false);
    }

    private boolean indexDocument(Client client, String indexName, String docType, int docNum, int index)
        throws IOException {
        Instant startTime = Instant.now();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        DateTimeFormatter hmsFormat = DateTimeFormat.forPattern("HH:mm:ss");

        for (int i = 50000 * index; i < docNum; i++) {
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
            jsonBuilder.startObject()
                .field("serialId", i + 1)
                .field("transactionId", UUID.randomUUID().toString())
                .field("trafficId", RandomUtils.nextLong())
                .field("incidentType", RandomUtils.nextInt(0, 3) % 3 + 1)
                .field("detectDateTime", new DateTime().toString(dateTimeFormat))
                .field("detectTime", new DateTime().toString(hmsFormat))
                .field("incidentDateTime", new DateTime().toString(dateTimeFormat))
                .field("incidentTime", new DateTime().toString(hmsFormat))
                .field("channelType", RandomUtils.nextInt(0, 3) % 3 + 1)
                .field("severityType", RandomUtils.nextInt(0, 8) % 8 + 1)
                .field("statusType", RandomUtils.nextInt(0, 2) % 2 + 1)
                .field("actionType", RandomUtils.nextInt(0, 4) % 4 + 1)
                .field("policyAction", RandomUtils.nextInt(0, 6) % 6 + 1)
                .field("agentAction", RandomUtils.nextInt(0, 5) % 5 + 1)
                .field("workMode", RandomUtils.nextInt(0, 2) % 2 + 1)
                .field("details", "incident details with index " + i)
                .field("isIgnored", RandomUtils.nextInt(0, 2) % 2 + 1)
                .field("isReleased", RandomUtils.nextInt(0, 2) % 2 + 1)
                .field("hasForensics", RandomUtils.nextBoolean())
                .field("tagContent", "incident tag content with index " + i)
                .field("breachContents", "incident breach contents with index " + i)
                .field("maxMatches", RandomUtils.nextInt(0, 1000))
                .field("transactionSize", RandomUtils.nextInt(0, 1000))
                .field("isVisible", RandomUtils.nextBoolean())
                .field("deployVersion", RandomUtils.nextInt(0, 1000))
                .field("deviceId", UUID.randomUUID().toString())
                .field("detectAgentUuid", UUID.randomUUID().toString())
                .field("detectAgentType", RandomUtils.nextInt(0, 4) % 4 + 1)
                .field("detectAgentHostname", "incident detect agent host name with index " + i)
                .field("detectAgentIp", "127.0.0." + RandomUtils.nextInt(0, 255) % 255 + 1)
                .field("detectAgentVersion", "incident details with index " + i)
                .field("analyzeAgentUuid", UUID.randomUUID().toString())
                .field("analyzeAgentType", RandomUtils.nextInt(0, 6) % 6 + 1)

                .field("analyzeAgentHostname", "incident analyze Agent Host name with index " + i)
                .field("analyzeAgentIp", "127.0.0." + RandomUtils.nextInt(0, 255) % 255 + 1)
                .field("analyzeAgentVersion", i)
                .field("hierarchy", i)

                .endObject();
            System.out.println("json builder " + i + "==>:" + jsonBuilder.toString());
            bulkRequest.add(client.prepareIndex(indexName, docType).setSource(jsonBuilder));
        }

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out
                .println("bulk index document occur error then failed message:" + bulkResponse.buildFailureMessage());
        }
        Instant endTime = Instant.now();
        Interval duration = new Interval(startTime, endTime);
        System.out.println("generate " + docNum + " document cost time :" + duration.toDuration());
        return true;
    }

    public Client getTransportClient(List<String> hosts, boolean hasXpack) {
        Settings settings = Settings.builder()
            .put("cluster.name", "elasticsearch")
            .put("client.transport.sniff", true)
            .put("client.transport.ping_timeout", "60s")
            .put("client.transport.nodes_sampler_interval", "60s")
            .build();
        // on startup
        TransportClient client;

        if (hasXpack) {
            settings = settings.builder()
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", "elastic:changeme")
                .build();
            client = null;//new PreBuiltXPackTransportClient(settings == null ? Settings.EMPTY : settings);
        } else {
            client = new PreBuiltTransportClient(settings == null ? Settings.EMPTY : settings);
        }

        hosts.forEach(host -> {
            try {
                client.addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9300));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });

        // on shutdown
        //client.close();

        return client;
    }

    private String getJsonFromTemplate(Map<String, String> dMap, DocType docType) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        //cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setClassForTemplateLoading(getClass(), StringUtils.EMPTY);

        // Set the preferred charset template files are stored in. UTF-8 is a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        cfg.setLogTemplateExceptions(false);

        String json = StringUtils.EMPTY;
        String ftl = "";
        switch (docType) {
            case NETWORK:
                ftl = "network_incident.ftl";
                break;
            case ENDPOINT:
                ftl = "endpoint_incident.ftl";
                break;
            case DISCOVERY:
                ftl = "discovery_incident.ftl";
                break;
        }
        try {
            Template template = cfg.getTemplate(ftl);
            json = FreeMarkerTemplateUtils.processTemplateIntoString(template, dMap);
        } catch (Exception e) {
            System.out.println("process template with data error:" + e);
        }
        return json;
    }
}
