package com.qslion.moudles.codegen.datamodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 21:46.
 */
public abstract class DataModel extends HashMap<String, Object> {

    protected String basePackage;
    private boolean serializable = false;
    protected String tableName;

    /**
     * 包路径
     *
     * @return 包路径
     */
    public abstract String getPackagePath();

    public abstract String getClassName();

    public abstract Set<String> getImports();

    public abstract Set<String> getImplements();

    public abstract String getExtends();

    public String getBasePackage() {
        return basePackage;
    }

    public DataModel setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public DataModel setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public final Map<String, Object> build() {
        put("packageName", getPackagePath());
        put("imports", getImports());
        put("className", getClassName());
        put("tableName", getTableName());
        put("implements", getImplements());
        put("extends", getExtends());
        return this;
    }
}
