package com.qslion.moudles.codegen;

import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.util.ResourceUtils;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/4 19:39.
 */
public abstract class AbstractEngine implements CodeCreator {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    Configuration cfg;
    Properties props;

    AbstractEngine() {
        try {
            //初始化FreeMarker配置 ,创建一个Configuration实例
            cfg = new Configuration(Configuration.VERSION_2_3_28);
            //模板加载器,Class.getResource()方法来找到模板,第二个参数是给模板的名称来加前缀的
            cfg.setClassForTemplateLoading(getClass(), StringUtils.EMPTY);
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.toString());
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            props = loadProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Map<String, Object> getDataMap() {
        Map<String, Object> dataMap = Maps.newHashMap();
        Set<Object> ps = props.keySet();
        for (Object obj : ps) {
            String key = (String) obj;
            String value = props.getProperty(key);
            dataMap.put(key, value);
        }
        dataMap.put("createDate", DateTime.now().toDate());
        dataMap.put("idType", "String");
        return dataMap;
    }

    Path prepareFile(String filePath) {
        return Paths.get(filePath);
    }

    Template prepareTemplate(String templateName) throws IOException {
        if (!".ftl".endsWith(templateName)) {
            return null;
        }
        return cfg.getTemplate(templateName);
    }

    /**
     * 数据模型+模板=输出
     *
     * @param templateName 模板名称
     * @param filePath 文件
     * @param dataMap 数据
     * @throws IOException ex
     */
    void writeToFile(String templateName, String filePath, Map<String, Object> dataMap) throws IOException {
        try {
            Path path = prepareFile(filePath);
            Writer out = Files.newBufferedWriter(path);
            Template template = prepareTemplate(templateName);
            template.process(dataMap, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Properties loadProperties() throws Exception {
        Properties props = new Properties();
        String fileName = "moduleConfig.properties";
        File configFile = ResourceUtils.getFile(String.format("classpath:%s", fileName));
        props.load(Files.newInputStream(configFile.toPath()));
        return props;
    }

    @Override
    public void generateAll(String tableName) throws IOException {
        generateEntity(tableName);
        generateDao(tableName);
        generateService(tableName);
        generateServiceImpl(tableName);
        generateController(tableName);
    }

}
