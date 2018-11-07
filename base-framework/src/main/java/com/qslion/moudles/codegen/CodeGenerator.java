package com.qslion.moudles.codegen;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodeGenerator extends AbstractEngine {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private CodeGenerator() throws Exception {
        super();
    }

    @Override
    public void generateEntity(String tableName) throws IOException {
        String serviceFilePath = getFilePath("entityPackageName", "entityClassName");
        writeToFile("entity.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateDao(String tableName) throws IOException {
        String serviceFilePath = getFilePath("daoPackageName", "entityClassName", "Repository.java");
        writeToFile("repository.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateService(String tableName) throws IOException {
        String serviceFilePath = getFilePath("servicePackageName", "entityClassName", "Service.java");
        writeToFile("service.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateServiceImpl(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath("serviceImplPackageName", "entityClassName", "ServiceImpl.java");
        writeToFile("serviceImpl.ftl", serviceImplFilePath, getDataMap());
    }

    @Override
    public void generateController(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath("controllerPackageName", "entityClassName", "Controller.java");
        writeToFile("controller.ftl", serviceImplFilePath, getDataMap());
    }


    public static void main(String[] args) throws Exception {
        new CodeGenerator().generateAll("au_user");
    }
}
