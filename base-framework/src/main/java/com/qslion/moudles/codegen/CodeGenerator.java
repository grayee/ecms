package com.qslion.moudles.codegen;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodeGenerator extends AbstractEngine {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    //文件分隔符
    public static final String SPT = File.separator;
    //包名
    private String packageName;
    //文件名称
    private String fileName;


    public CodeGenerator(String packageName, String fileName) throws Exception {
        super();
        this.packageName = packageName;
        this.fileName = fileName;
    }


    private String getFilePath(String packageName, String name) {
        String path = packageName.replaceAll("\\.", File.separator);
        return String.format("src/%s/%s", path, name);
    }

    @Override
    public void generateEntity(String tableName) throws IOException {
        String serviceFilePath = getFilePath(props.getProperty("entityPackageName"),
            props.getProperty("entityClassName") + ".java");
        writeToFile("entity.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateDao(String tableName) throws IOException {
        String serviceFilePath = getFilePath(props.getProperty("daoPackageName"),
            props.getProperty("entityClassName") + "Repository.java");
        writeToFile("repository.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateService(String tableName) throws IOException {
        String serviceFilePath = getFilePath(props.getProperty("servicePackageName"),
            props.getProperty("entityClassName") + "Service.java");
        writeToFile("service.ftl", serviceFilePath, getDataMap());
    }

    @Override
    public void generateServiceImpl(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath(props.getProperty("serviceImplPackageName"),
            props.getProperty("entityClassName") + "ServiceImpl.java");
        writeToFile("serviceImpl.ftl", serviceImplFilePath, getDataMap());
    }

    @Override
    public void generateController(String tableName) throws IOException {
        String serviceImplFilePath = getFilePath(props.getProperty("controllerPackageName"),
            props.getProperty("entityClassName") + "Controller.java");
        writeToFile("controller.ftl", serviceImplFilePath, getDataMap());
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String packageName = "com.qslion.app";
        String fileName = "moduleConfig.properties";
        new CodeGenerator(packageName, fileName).generateAll("au_user");
    }
}
