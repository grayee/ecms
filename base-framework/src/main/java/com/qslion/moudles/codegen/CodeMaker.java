package com.qslion.moudles.codegen;

import java.util.Map;

/**
 * ecms
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
    private Map<String, Object> dataMap;

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

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public CodeMaker setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
        return this;
    }
}
