package com.qslion.moudles.codegen.service.impl;

import com.google.common.collect.Maps;
import com.qslion.framework.enums.ResultCode;
import com.qslion.framework.exception.BusinessException;
import com.qslion.moudles.codegen.config.ProjectConfig;
import com.qslion.moudles.codegen.service.CodeCreator;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * 代码生成模板引擎
 *
 * @author Gray.Z
 * @date 2018/11/4 19:39.
 */
public abstract class AbstractEngine implements CodeCreator {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private static final String FILE_FTL_EXT = ".ftl";


    /**
     * 模板配置
     */
    private Configuration cfg;
    ProjectConfig projectConfig = new ProjectConfig();

    AbstractEngine() {
        try {
            //初始化FreeMarker配置 ,创建一个Configuration实例
            cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setSharedVaribles(getDefaultDataModel());
            DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
            owb.setDefaultDateType(TemplateDateModel.DATETIME);
            owb.setForceLegacyNonListCollections(false);
            cfg.setObjectWrapper(owb.build());
            //模板加载器,Class.getResource()方法来找到模板,第二个参数是给模板的名称来加前缀的
            cfg.setClassForTemplateLoading(getClass(), File.separator + "template");
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.toString());
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Map<String, Object> getDefaultDataModel() {
        Map<String, Object> dataMap = Maps.newHashMap();
        Properties props = projectConfig.loadProperties();
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
     * @param dataModel 数据
     */
    public void writeToFile(String templateName, String filePath, Map<String, Object> dataModel){
        Path path = prepareFile(filePath);
        try (Writer writer = Files.newBufferedWriter(path)) {
            Template template = prepareTemplate(templateName);
            template.process(dataModel, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写为字符
     *
     * @param templateName 模板名称
     * @param dataModel 数据
     * @return 字符串
     */
    String writeAsString(String templateName, Map<String, Object> dataModel) throws IOException, TemplateException {
        Template template = prepareTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dataModel);
    }


    /**
     * 代码生成模板方法
     *
     * @param tableName 表名称
     * @throws IOException 异常
     */
    public final void generateAll(String tableName) throws IOException {
        generateEntity(tableName);
//        generateDao(tableName);
//        generateService(tableName);
//        generateServiceImpl(tableName);
//        generateController(tableName);
    }

}
