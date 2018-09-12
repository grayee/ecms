package com.qslion.framework.util;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    public static final String JDBC_CONFIG_FILE_NAME = "jdbc.properties";// JDBC配置文件
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private final static String MYSQL = "MySQL";
    private final static String ORACLE = "Oracle";

    private DBUtils() {
        super();
    }

    // 取得数据库连接
    public synchronized static Connection getConnection() {
        Connection conn = (Connection) threadLocal.get();
        try {
            Properties props = new Properties();
            InputStream in = DBUtils.class.getClassLoader()
                    .getResourceAsStream("/WEB-INF/config/" + JDBC_CONFIG_FILE_NAME);
            props.load(in);
            if (conn == null || conn.isClosed()) {
                String dbDriver = props.getProperty("jdbc.driver");
                String dbUrl = props.getProperty("jdbc.url");
                String dbUser = props.getProperty("jdbc.username");
                String dbPassword = props.getProperty("jdbc.password");
                Class.forName(dbDriver);
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                threadLocal.set(conn);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    // 查询后，得到数据
    public static ResultSet execute(String sql) throws Exception {
        // 取得连接
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 得到一个结果集
        return pstmt.executeQuery();
    }

    // 关闭指定数据库连接
    public static void close(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭指定statement
    public static void close(Statement stmt) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    // 关闭结果集
    public static void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    // 事物提交
    public static void commit() throws Exception {
        getConnection().commit();
    }

    // 回滚
    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDBProductName() {
        String databaseProductName = "";
        try {
            databaseProductName = getConnection().getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return databaseProductName;
    }

    public static String getPageSql(String sourceSql, int firstResult, int maxResult) {
        String dbname = getDBProductName();
        Assert.notNull(dbname, " dabasebase Product name is null !");
        int low = firstResult;
        int up = firstResult + maxResult + 1;
        StringBuffer buffer = new StringBuffer();
        if (dbname.equals(MYSQL)) {
            buffer.append("select * from (");
            buffer.append(sourceSql);
            buffer.append(")A limit " + low + "," + up);
        } else if (dbname.equals(ORACLE)) {
            buffer.append("SELECT * FROM(SELECT A.*, rownum as rid FROM( ");
            buffer.append(sourceSql);
            buffer.append(") A WHERE rownum<" + up + ") WHERE rid >" + low);
        }
        System.out.println("page sql:" + buffer.toString());
        return buffer.toString();
    }

    public static void main(String args[]) throws Exception {
        System.out.println(DBUtils.execute("select * from au_functree"));
    }

    // 关闭全局数据库连接池
    public void close() throws Exception {
        Connection conn = getConnection();
        try {
            if (null != conn && !conn.isClosed()) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("关闭数据库连接失败..");
        }
        threadLocal.set(null);
    }
}
