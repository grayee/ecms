package com.qslion.framework.util;

import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
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
                    Properties props = loadProperties();
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
        Properties props = loadProperties();
        HikariConfig config = new HikariConfig(props);
        return new HikariDataSource(config);
    }

    private static Properties loadProperties() throws IOException {
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
    public static void close() throws Exception {
        Connection conn = getConnection();
        try {
            if (null != conn && !conn.isClosed()) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("关闭数据库连接失败..");
        } finally {
            threadLocal.remove();
        }
    }

    public static void main(String args[]) throws Exception {
        String sql = "select * from au_user";
        try {
            QueryRunner qr = new QueryRunner(DbUtil.getDataSource());
            List<Object[]> list = qr.query(sql, resultSet -> {
                ResultSetMetaData meta = resultSet.getMetaData();
                int cols = meta.getColumnCount();
                List<Object[]> result = Lists.newArrayList();
                while (resultSet.next()) {
                    Object[] rowResult = new Object[cols];
                    for (int i = 0; i < cols; i++) {
                        rowResult[i] = JdbcUtils.getResultSetValue(resultSet, i + 1);
                    }
                    result.add(rowResult);
                }
                return result;
            });
            System.out.println("object array" + JSONUtils.writeValueAsString(list));
            //BeanHandler,BeanListHandler,ScalarHandler
            List<Map<String, Object>> list1 = qr.query(sql, new MapListHandler());
            System.out.println("map object" + JSONUtils.writeValueAsString(list1));

            ResultSet rs = DbUtil.execute(sql);
            while (rs.next()) {
                System.out.println(rs.getString("username") + "=====>>>======>>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        testMeteData();
    }

    public static void testMeteData() throws SQLException {
        DatabaseMetaData databaseMetaData = getConnection().getMetaData();
        //数据库的URL
        System.out.println("url:" + databaseMetaData.getURL());
        //当前数据库管理系统的用户名
        System.out.println("username:" + databaseMetaData.getUserName());
        //数据库是否只允许读操作
        System.out.println("isReadOnly:" + databaseMetaData.isReadOnly());
        //数据库的产品名称
        System.out.println("productName:" + databaseMetaData.getDatabaseProductName());
        //返回数据库的版本号
        System.out.println("productVersion:" + databaseMetaData.getDatabaseProductVersion());
        System.out.println("driverName:" + databaseMetaData.getDriverName());
        System.out.println("driverVersion:" + databaseMetaData.getDriverVersion());
        System.out.println("tableTypes:" + databaseMetaData.getTableTypes());

        ResultSet resultSet = databaseMetaData.getTableTypes();

        ResultSetMetaData meta = resultSet.getMetaData();
        //列的数量
        int cols = meta.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < cols; i++) {
                System.out.println("index " + (i + 1) + " value: " + JdbcUtils.getResultSetValue(resultSet, i + 1));
                //列名称
                System.out.println("columnName:" + meta.getColumnName(i + 1));
                //列的数据库特定的类型名称
                System.out.println("columnTypeName:" + meta.getColumnTypeName(i + 1));
                //列的最大标准宽度，以字符为单位
                System.out.println("getColumnDisplaySize:" + meta.getColumnDisplaySize(i + 1));
                //列中的值是否可以为 null
                System.out.println("isNullable:" + meta.isNullable(i + 1));
                //是否自动为指定列进行编号
                System.out.println("isAutoIncrement:" + meta.isAutoIncrement(i + 1));
            }
        }

    }
}