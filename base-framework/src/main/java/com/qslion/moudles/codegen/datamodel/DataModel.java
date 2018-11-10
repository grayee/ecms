package com.qslion.moudles.codegen.datamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
    protected Set<String> imports = new TreeSet<>();
    protected Set<String> implementList = new HashSet<>();
    protected boolean serializable = false;

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

    public abstract Set<String> getImports();

    public DataModel setImports(Set<String> imports) {
        this.imports = imports;
        return this;
    }

    public DataModel addImports(String addImport) {
        this.imports.add(addImport);
        return this;
    }

    public abstract Set<String> getImplements();

    public DataModel setImplements(Set<String> implementList) {
        this.implementList = implementList;
        return this;
    }

    public DataModel addImplements(String addImplement) {
        this.implementList.add(addImplement);
        return this;
    }

    public abstract String getExtends();

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
