package com.qslion.moudles.codegen.datamodel;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.qslion.moudles.codegen.service.impl.AbstractEngine;
import java.io.File;
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
public abstract class DataModel {

    protected static final String FILE_JAVA_EXT = ".java";
    protected static final String FILE_FTL_EXT = ".ftl";

    protected String basePackage;
    private boolean serializable = false;
    protected String tableName;
    protected String projectName;

    protected Set<String> implementList = new HashSet<>();

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

    public abstract String getFtl();

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

    public Set<String> getImplementList() {
        return implementList;
    }

    public DataModel setImplementList(Set<String> implementList) {
        this.implementList = implementList;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public DataModel setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getFilePath(String projectName, String... suffix) {
        String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String fileName = className + (suffix.length > 0 ? suffix[0] : StringUtils.EMPTY) + FILE_JAVA_EXT;
        String rootPath = System.getProperty("user.dir");
        List<String> paths = Lists.newArrayList();
        paths.add(rootPath);
        paths.add(projectName + File.separator + "src"
            + File.separator + "main" + File.separator + "java");
        paths.addAll(Splitter.on(".").splitToList(getPackagePath()));
        paths.add(fileName);
        return Joiner.on(File.separator).join(paths);
    }

    public final void build(AbstractEngine engine) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("packageName", getPackagePath());
        dataModel.put("imports", getImports());
        dataModel.put("className", getClassName());
        dataModel.put("tableName", getTableName());
        dataModel.put("implements", getImplements());
        dataModel.put("extends", getExtends());
        String filePath = getFilePath(getProjectName());
        engine.writeToFile(getFtl(), filePath, dataModel);
    }
}
