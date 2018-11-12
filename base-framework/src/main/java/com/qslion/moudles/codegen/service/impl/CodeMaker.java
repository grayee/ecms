package com.qslion.moudles.codegen.service.impl;

import java.io.IOException;
import java.util.Map;

/**
 * 代码构造器
 *
 * @author Gray.Z
 * @date 2018/11/7 21:51.
 */
public class CodeMaker {

    /**
     * 模板
     */
    private String ftl;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 数据
     */
    private Map<String, Object>  dataModel;

    public String getFtl() {
        return ftl;
    }

    public CodeMaker setFtl(String ftl) {
        this.ftl = ftl;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public CodeMaker setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Map<String, Object>  getDataModel() {
        return dataModel;
    }

    public CodeMaker setDataModel(Map<String, Object>  dataModel) {
        this.dataModel = dataModel;
        return this;
    }

    public void make(AbstractEngine engine) throws IOException {
        engine.writeToFile(getFtl(), getFilePath(), getDataModel());
    }
}
