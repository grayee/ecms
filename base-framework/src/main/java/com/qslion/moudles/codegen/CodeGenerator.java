package com.qslion.moudles.codegen;

import com.google.common.base.CaseFormat;
import com.qslion.framework.util.JSONUtils;
import com.qslion.moudles.codegen.datamodel.impl.EntityDataModel;
import com.qslion.moudles.codegen.ddl.TableMetadata;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodeGenerator extends AbstractEngine {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private CodeGenerator() throws Exception {
        super();
    }

    @Override
    public void generateEntity(String tableName) throws IOException {
        TableMetadata tableMetadata = new DbProvider().getTableMetaData(tableName).get(tableName);

        Map<String, Object> dataModel = new EntityDataModel()
            .setClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName))
            .setTableName(tableName)
            .setPackagePath(String.format("%s.%s", projectConfig.getPackagePath(), "entity"))
            .build();

        new CodeMaker()
            .setFtl("entity.ftl")
            .setFilePath(getFilePath(tableName, "entity"))
            .setDataModel(dataModel)
            .make(this);
    }

    @Override
    public void generateDao(String tableName) throws IOException {
        String serviceFilePath = getFilePath(tableName, "dao", "Repository");
        writeToFile("repository.ftl", serviceFilePath, getDefaultDataModel());
    }

    @Override
    public void generateService(String tableName) throws IOException {
        String serviceFilePath = getFilePath(tableName, "service", "Service");
        writeToFile("service.ftl", serviceFilePath, getDefaultDataModel());
    }

    @Override
    public void generateServiceImpl(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath(tableName, "service.impl", "ServiceImpl");
        writeToFile("serviceImpl.ftl", serviceImplFilePath, getDefaultDataModel());
    }

    @Override
    public void generateController(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath(tableName, "controller", "Controller");
        writeToFile("controller.ftl", serviceImplFilePath, getDefaultDataModel());
    }


    public static void main(String[] args) throws Exception {
        // new CodeGenerator().generateAll("au_user");
        DbProvider provider = new DbProvider();
        List<String> list = provider.getTableNames();
        Map<String, TableMetadata> map = provider.getTableMetaData();
        System.out.println(JSONUtils.writeValueAsString(map));
    }
}
