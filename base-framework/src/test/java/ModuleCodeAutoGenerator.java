import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModuleCodeAutoGenerator {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    //文件分隔符
    public static final String SPT = File.separator;
    //文件编码
    public static final String ENCODING = "UTF-8";
    private Properties prop = new Properties();
    //包名
    private String packageName;
    //文件名称
    private String fileName;
    //服务层文件
    private File serviceFile;
    private File serviceImplFile;

    private File actionFile;
    private File pageListFile;
    private File pageEditFile;
    private File pageAddFile;

    private Template serviceTpl;
    private Template serviceImplTpl;
    private Template actionTpl;
    private Template pageListTpl;
    private Template pageEditTpl;
    private Template pageAddTpl;

    private Configuration cfg;

    public ModuleCodeAutoGenerator(String packageName, String fileName) throws Exception {
        super();
        this.packageName = packageName;
        this.fileName = fileName;
        loadProperties();
        //初始化FreeMarker配置 ,创建一个Configuration实例
        cfg = new Configuration();
        //设置FreeMarker的模版文件位置  
        try {
            cfg.setDirectoryForTemplateLoading(new File("D://works//Qslion//src//com//qslion//framework//template"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void loadProperties() throws Exception {
        logger.info("开始加载配置文件,文件位置:" + getFilePath(packageName, fileName));
        FileInputStream fileInput = new FileInputStream(getFilePath(packageName, fileName));
        prop.load(fileInput);
        if (logger.isInfoEnabled()) {
            Set<Object> ps = prop.keySet();
            for (Object o : ps) {
                logger.info(o + "=" + prop.get(o));
            }
        }
    }

    private void prepareFile() {
        //服务层代码
        String serviceImplFilePath = getFilePath(prop.getProperty("serviceImplPackageName"),
            prop.getProperty("entityClassName") + "ServiceImpl.java");
        serviceImplFile = new File(serviceImplFilePath);
        logger.info("serviceImplFile:" + serviceImplFile.getAbsolutePath());

        String serviceFilePath = getFilePath(prop.getProperty("servicePackageName"), prop.getProperty("entityClassName") + "Service.java");
        serviceFile = new File(serviceFilePath);
        logger.info("serviceFile:" + serviceFile.getAbsolutePath());
        //控制层代码
        String actionFilePath = getFilePath(prop.getProperty("actionPackageName"), prop.getProperty("entityClassName") + "Action.java");
        actionFile = new File(actionFilePath);
        logger.info("actionFile:" + actionFile.getAbsolutePath());
        //视图层代码
     /* String pagePath = "WebRoot/WEB-INF/"+ prop.getProperty("pagesDirectroy") + "/"+ prop.getProperty("pagesModuleDirectory") + "/";
    pageListFile = new File(pagePath + "list.html");
		logger.info("pageListFile:" + pageListFile.getAbsolutePath());
		pageEditFile = new File(pagePath + "edit.html");
		logger.info("pageEditFile:" + pageEditFile.getAbsolutePath());
		pageAddFile = new File(pagePath + "add.html");
		logger.info("pageAddFile:" + pageAddFile.getAbsolutePath());*/

    }

    private void prepareTemplate() throws IOException {
        String templatePackage = prop.getProperty("template_dir");
        logger.info("tplPackage:" + templatePackage);
        serviceImplTpl = cfg.getTemplate("serviceImpl.ftl");
        serviceTpl = cfg.getTemplate("service.ftl");
        actionTpl = cfg.getTemplate("action.ftl");
	/*	pageListTpl =cfg.getTemplate("page_list.ftl");
		pageAddTpl = cfg.getTemplate("page_add.ftl");
		pageEditTpl = cfg.getTemplate("page_edit.ftl");*/
    }

    private void writeFile() throws IOException {
        Map<String, Object> commonData = new HashMap<String, Object>();
        Set<Object> ps = prop.keySet();
        for (Object o : ps) {
            String key = (String) o;
            String value = prop.getProperty(key);
            commonData.put(key, value);
        }
        Date date = new Date();
        commonData.put("createDate", date);
        commonData.put("modifyDate", date);
        commonData.put("idType", "String");
        writeTemplateFileContent(serviceImplTpl, serviceImplFile, commonData);
        writeTemplateFileContent(serviceTpl, serviceFile, commonData);
        writeTemplateFileContent(actionTpl, actionFile, commonData);
    }

    private String getFilePath(String packageName, String name) {
        //logger.info("before replace:" + packageName);
        String path = packageName.replaceAll("\\.", "/");
        //logger.info("after relpace:" + path);
        return "src/" + path + "/" + name;
    }

    private static void writeTemplateFileContent(Template template, File file, Map<String, Object> data) throws IOException {
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(file), ENCODING);
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void codeGenerate() throws Exception {
        prepareFile();
        prepareTemplate();
        writeFile();
        logger.info("代码生成结束!");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String packageName = "com.qslion.app";
        String fileName = "moduleConfig.properties";
        new ModuleCodeAutoGenerator(packageName, fileName).codeGenerate();

    }

}
