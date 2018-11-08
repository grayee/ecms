package com.qslion.moudles.codegen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    public static class DataModel extends HashMap<String, Object> {

        private String packagePath;
        private String className;
        protected List<String> imports = Lists.newArrayList();

        public String getPackagePath() {
            return packagePath;
        }

        public DataModel setPackagePath(String packagePath) {
            this.packagePath = packagePath;
            return this;
        }

        public String getClassName() {
            return className;
        }

        public DataModel setClassName(String className) {
            this.className = className;
            return this;
        }

        public List<String> getImports() {
            return imports;
        }

        public DataModel setImports(List<String> imports) {
            this.imports = imports;
            return this;
        }

        public Map<String, Object> build() {
            put("packageName", getPackagePath());
            put("imports", getImports());
            put("className", getClassName());
            return this;
        }
    }
}
