package com.qslion.framework.util;

import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

public class DbUtil {

    /**
     * JDBC数据库连接配置文件
     */
    private static final String JDBC_CONFIG_FILE_NAME = "jdbc.properties";
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private final static String MYSQL = "MySQL";
    private final static String ORACLE = "Oracle";

    private DbUtil() {
        super();
    }

    // 取得数据库连接
    public synchronized static Connection getConnection() {
        Connection conn = (Connection) threadLocal.get();
        try {
            Properties props = new Properties();
            File configFile = ResourceUtils.getFile(String.format("classpath:%s", JDBC_CONFIG_FILE_NAME));
            props.load(Files.newInputStream(configFile.toPath()));
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

    public static DataSource getDataSource() {
        HikariDataSource hds = new HikariDataSource();
        return hds;
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
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭指定statement
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭结果集
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    /**
     *
     * @return
     */
    private static String getDBProductName() {
        String databaseProductName = StringUtils.EMPTY;
        try {
            databaseProductName = getConnection().getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
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
        QueryRunner qr = new QueryRunner();
        System.out.println(DbUtil.execute("select * from au_user"));
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
