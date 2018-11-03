package com.qslion.framework.util;

import com.zaxxer.hikari.HikariConfig;
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
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

public class DbUtil extends JdbcUtils {

    /**
     * JDBC数据库连接配置文件
     */
    private static final String JDBC_CONFIG_FILE_NAME = "jdbc.properties";
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private final static String MYSQL = "MySQL";
    private final static String ORACLE = "Oracle";

    private DbUtil() {
        super();
    }

    /**
     * 取得数据库连接
     *
     * @return Connection
     */
    public synchronized static Connection getConnection() {
        DataSource dataSource = null;
        try {
            dataSource = getDataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dataSource == null) {
            Connection conn = threadLocal.get();
            if (conn == null) {
                try {
                    Properties props = getProperties();
                    String dbDriver = props.getProperty("driverClassName");
                    String dbUrl = props.getProperty("jdbcUrl");
                    String dbUser = props.getProperty("dataSource.user");
                    String dbPassword = props.getProperty("dataSource.password");
                    Class.forName(dbDriver);
                    conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                    threadLocal.set(conn);
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
            return conn;
        } else {
            Connection conn = DataSourceUtils.getConnection(dataSource);
            threadLocal.set(conn);
            return conn;
        }
    }

    public static DataSource getDataSource() throws IOException {
        Properties props = getProperties();
        HikariConfig config = new HikariConfig(props);
        return new HikariDataSource(config);
    }

    private static Properties getProperties() throws IOException {
        Properties props = new Properties();
        File configFile = ResourceUtils.getFile(String.format("classpath:%s", JDBC_CONFIG_FILE_NAME));
        props.load(Files.newInputStream(configFile.toPath()));
        return props;
    }

    /**
     * 查询后，得到数据
     *
     * @param sql sql
     * @return ResultSet
     * @throws Exception ex
     */
    public static ResultSet execute(String sql) throws Exception {
        // 取得连接
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 得到一个结果集
        return pstmt.executeQuery();
    }

    /**
     * 关闭指定数据库连接
     *
     * @param conn 链接
     */
    public static void close(Connection conn) {
        JdbcUtils.closeConnection(conn);
    }

    /**
     * 关闭指定statement
     *
     * @param stmt Statement
     */
    public static void close(Statement stmt) {
        JdbcUtils.closeStatement(stmt);
    }

    /**
     * 关闭结果集
     *
     * @param rs ResultSet
     */
    public static void close(ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
    }

    /**
     * 事物提交
     *
     * @throws Exception ex
     */
    public static void commit() throws Exception {
        getConnection().commit();
    }

    /**
     * 回滚
     */
    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    /**
     * 关闭全局数据库连接池
     *
     * @throws Exception ex
     */
    public void close() throws Exception {
        Connection conn = getConnection();
        try {
            if (null != conn && !conn.isClosed()) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("关闭数据库连接失败..");
        }finally {
            threadLocal.remove();
        }
    }

    public static void main(String args[]) throws Exception {
        QueryRunner qr = new QueryRunner(DbUtil.getDataSource());
        System.out.println(qr.query("select * from au_user", new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet.getFetchSize()+"----->>>>");
                return null;
            }
        }));
        System.out.println(DbUtil.execute("select * from au_user").getString("username")+"===========>");
    }
}
