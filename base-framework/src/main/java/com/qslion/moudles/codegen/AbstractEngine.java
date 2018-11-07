package com.qslion.moudles.codegen;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/4 19:39.
 */
public abstract class AbstractEngine implements CodeCreator {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private static final String FILE_FTL_EXT = ".ftl";
    private static final String FILE_JAVA_EXT = ".java";

    private Configuration cfg;
    private Properties props;

    AbstractEngine() {
        try {
            //初始化FreeMarker配置 ,创建一个Configuration实例
            cfg = new Configuration(Configuration.VERSION_2_3_28);
            //模板加载器,Class.getResource()方法来找到模板,第二个参数是给模板的名称来加前缀的
            cfg.setClassForTemplateLoading(getClass(), File.separator + "template");
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

    String getFilePath(String pkgNameKey, String fileNameKey, String... suffix) {
        String packageName = props.getProperty(pkgNameKey);
        String fileName = props.getProperty(fileNameKey) +
            (suffix.length > 0 ? suffix[0] : StringUtils.EMPTY) + FILE_JAVA_EXT;
        String rootPath = System.getProperty("user.dir");
        List<String> paths = Lists.newArrayList();
        paths.add(rootPath);
        paths.add(props.get("projectName") + File.separator + "src"
            + File.separator + "main" + File.separator + "java");
        paths.addAll(Splitter.on(".").splitToList(packageName));
        paths.add(fileName);
        return Joiner.on(File.separator).join(paths);
    }

    private Path prepareFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    private Template prepareTemplate(String templateName) throws IOException {
        if (!templateName.endsWith(FILE_FTL_EXT)) {
            throw new BusinessException(ResultCode.FAIL);
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
            Writer writer = Files.newBufferedWriter(path);
            Template template = prepareTemplate(templateName);
            template.process(dataMap, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写为字符
     *
     * @param templateName 模板名称
     * @param dataMap 数据
     * @return 字符串
     */
    String writeAsString(String templateName, Map<String, Object> dataMap) throws IOException, TemplateException {
        Template template = prepareTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dataMap);
    }

    private Properties loadProperties() throws Exception {
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
