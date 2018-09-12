/*
package com.test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

*/
/**
 * Created by zhangruigang on 2017/9/20.
 *//*

public class ElasticsearchTest {

    private static final Logger logger = LogManager.getLogger();

    private static final String INDEX_NAME = "test.incident";

    public enum DocType {
        NETWORK, ENDPOINT, DISCOVERY
    }

    @Test
    public void test() {
        testCreateIndex(INDEX_NAME);
    }

    public void testCreateIndex(String indexName) {
        //get transport client
        Client client = getTransportClient(Lists.newArrayList("127.0.0.1"), false);
        IndicesAdminClient indicesAdminClient = client.admin().indices();

        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse indicesExistsResponse = indicesAdminClient.exists(indicesExistsRequest).actionGet();

        if (!indicesExistsResponse.isExists()) {
            Settings indexCreateSettings = Settings.builder()
                    .put("index.number_of_shards", 2)
                    .put("index.number_of_replicas", 6).build();

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName, indexCreateSettings);
            try {
                createIndexRequest.mapping("network-incident", FileUtils.readFileToString(ResourceUtils.getFile("classpath:network_incident_mappings.json"), Charset.defaultCharset()), XContentType.JSON);
                createIndexRequest.mapping("endpoint-incident", FileUtils.readFileToString(ResourceUtils.getFile("classpath:endpoint_incident_mappings.json"), Charset.defaultCharset()), XContentType.JSON);
                createIndexRequest.mapping("discovery-incident", FileUtils.readFileToString(ResourceUtils.getFile("classpath:discovery_incident_mappings.json"), Charset.defaultCharset()), XContentType.JSON);
            } catch (IOException e) {
                logger.error("create mappings happened an exception", e);
            }

            ActionFuture<CreateIndexResponse> createFuture = indicesAdminClient.create(createIndexRequest);
            CreateIndexResponse indexCreateResponse = createFuture.actionGet();
            logger.info("index create response is acknowledged :{},shards acked:{}", indexCreateResponse.isAcknowledged(), indexCreateResponse.isShardsAcked());
        } else {
            logger.warn("index {} is already exist!", indexName);
            indicesAdminClient.prepareRefresh(indexName).get();
            indicesAdminClient.prepareDelete(indexName).get();
            logger.warn("index {} is deleted successful!", indexName);
        }


        try {
            indexDocument(client, indexName, "network-incident", 100);
            //indexDocument(client, indexName, "endpoint-incident", 1000);
            //indexDocument(client, indexName, "discovery-incident", 1000);
        } catch (IOException e) {
            logger.error("index document occur a io exception", e);
        }


        String[] indices = indicesAdminClient.prepareGetIndex().get().indices();
        logger.info("indices count:{},detail:{}", indices.length, Joiner.on(",").join(indices));


        GetSettingsResponse response = indicesAdminClient.prepareGetSettings(indices).get();
        for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
            String index = cursor.key;
            Settings settings = cursor.value;
            Integer shards = settings.getAsInt("index.number_of_shards", null);
            Integer replicas = settings.getAsInt("index.number_of_replicas", null);
            logger.info("current index:{},shards:{},replicas:{}", index, shards, replicas);
        }

    }

    public boolean indexDocument(Client client, String indexName, String docType, int docNum) throws IOException {
        Instant startTime = Instant.now();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        DateTimeFormatter hmsFormat = DateTimeFormat.forPattern("HH:mm:ss");

        for (int i = 0; i < docNum; i++) {
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
            jsonBuilder.startObject()
                    .field("serialId", i + 1)
                    .field("transactionId", UUID.randomUUID().toString())
                    .field("trafficId", RandomUtils.nextLong())
                    .field("incidentType", RandomUtils.nextInt(0,3) % 3 + 1)
                    .field("detectDateTime", new DateTime().toString(dateTimeFormat))
                    .field("detectTime", new DateTime().toString(hmsFormat))
                    .field("incidentDateTime", new DateTime().toString(dateTimeFormat))
                    .field("incidentTime", new DateTime().toString(hmsFormat))
                    .field("channelType", RandomUtils.nextInt(0,3) % 3 + 1)
                    .field("severityType", RandomUtils.nextInt(0,8) % 8 + 1)
                    .field("statusType", RandomUtils.nextInt(0,2) % 2 + 1)
                    .field("actionType", RandomUtils.nextInt(0,4) % 4 + 1)
                    .field("policyAction", RandomUtils.nextInt(0,6) % 6 + 1)
                    .field("agentAction", RandomUtils.nextInt(0,5) % 5 + 1)
                    .field("workMode", RandomUtils.nextInt(0,2) % 2 + 1)
                    .field("details", "incident details with index " + i)
                    .field("isIgnored", RandomUtils.nextInt(0,2) % 2 + 1)
                    .field("isReleased", RandomUtils.nextInt(0,2) % 2 + 1)
                    .field("hasForensics", RandomUtils.nextBoolean())
                    .field("tagContent", "incident tag content with index " + i)
                    .field("breachContents", "incident breach contents with index " + i)
                    .field("maxMatches", RandomUtils.nextInt(0,1000))
                    .field("transactionSize", RandomUtils.nextInt(0,1000))
                    .field("isVisible", RandomUtils.nextBoolean())
                    .field("deployVersion", RandomUtils.nextInt(0,1000))
                    .field("deviceId", UUID.randomUUID().toString())
                    .field("detectAgentUuid", UUID.randomUUID().toString())
                    .field("detectAgentType", RandomUtils.nextInt(0,4) % 4 + 1)
                    .field("detectAgentHostname", "incident detect agent host name with index " + i)
                    .field("detectAgentIp", "127.0.0." + RandomUtils.nextInt(0,255) % 255 + 1)
                    .field("detectAgentVersion", "incident details with index " + i)
                    .field("analyzeAgentUuid", UUID.randomUUID().toString())
                    .field("analyzeAgentType", RandomUtils.nextInt(0,6) % 6 + 1)

                    .field("analyzeAgentHostname", "incident analyze Agent Host name with index " + i)
                    .field("analyzeAgentIp", "127.0.0." + RandomUtils.nextInt(0,255) % 255 + 1)
                    .field("analyzeAgentVersion", i)
                    .field("hierarchy", i)

                    .endObject();
            logger.info("json builder {}==>:{}", i, jsonBuilder.string());
            bulkRequest.add(client.prepareIndex(indexName, docType).setSource(jsonBuilder));
        }

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            logger.error("bulk index document occur error then failed message:{}", bulkResponse.buildFailureMessage());
        }
        Instant endTime = Instant.now();
        Interval duration = new Interval(startTime, endTime);
        logger.info("generate {} document cost time :{}", docNum, duration.toDuration());
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
            client = new PreBuiltXPackTransportClient(settings == null ? Settings.EMPTY : settings);
        } else {
            client = new PreBuiltTransportClient(settings == null ? Settings.EMPTY : settings);
        }


        hosts.forEach(host -> {
            try {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });

        // on shutdown
        //client.close();

        return client;
    }

    private String getJsonFromTemplate(Map<String, String> dMap, DocType docType) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
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
            logger.error("process template with data error:", e);
        }
        return json;
    }
}
*/
