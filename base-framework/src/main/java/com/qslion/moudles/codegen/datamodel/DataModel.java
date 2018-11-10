package com.qslion.moudles.codegen.datamodel;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 21:46.
 */
public abstract class DataModel extends HashMap<String, Object> {

    private String packagePath;
    private String className;
    protected String extendsClassName;
    protected Class<?> extendsClass;
    protected Set<String> implementList = new HashSet<String>();
    protected boolean serializable = false;

    protected List<String> imports = Lists.newArrayList();
    private String tableName;

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

    public abstract List<String> getImports();

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
        return this;
    }
}
