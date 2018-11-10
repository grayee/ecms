package com.qslion.moudles.codegen.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import org.springframework.util.ResourceUtils;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 9:00.
 */
public class ProjectConfig {

    private static final String CONFIG_FILE = "moduleConfig.properties";
    private String projectName;
    private String projectLabel;
    private String outputEncoding = "UTF-8";
    private String packagePath;
    private boolean defaultProject;
    private String copyright;
    private String description;
    private String author;

    public ProjectConfig() {
        Properties props = loadProperties();
        copyright = props.getProperty("copyright");
        projectName = props.getProperty("projectName");
        packagePath = props.getProperty("packagePath");
        description = props.getProperty("description");
        author = props.getProperty("author");
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectConfig setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getProjectLabel() {
        return projectLabel;
    }

    public ProjectConfig setProjectLabel(String projectLabel) {
        this.projectLabel = projectLabel;
        return this;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public ProjectConfig setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
        return this;
    }

    public boolean isDefaultProject() {
        return defaultProject;
    }

    public ProjectConfig setDefaultProject(boolean defaultProject) {
        this.defaultProject = defaultProject;
        return this;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public ProjectConfig setPackagePath(String packagePath) {
        this.packagePath = packagePath;
        return this;
    }

    public Properties loadProperties() {
        Properties props = new Properties();
        File configFile = null;
        try {
            configFile = ResourceUtils.getFile(String.format("classpath:%s", CONFIG_FILE));
            props.load(Files.newInputStream(configFile.toPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public String getCopyright() {
        return copyright;
    }

    public ProjectConfig setCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectConfig setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ProjectConfig setAuthor(String author) {
        this.author = author;
        return this;
    }
}
