package com.qslion.moudles.codegen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.util.DbUtil;
import com.qslion.moudles.codegen.ddl.TableMetadata;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 10:30.
 */
public class DbProvider {

    private String catalog;
    private String schema;
    private String tableNamePatterns;
    private List<ColumnHandler> columnHandlers = Lists.newArrayList();
    private List<String> tableNames = Lists.newArrayList();
    private Map<String, TableMetadata> tableMetadatas = Maps.newHashMap();


    /**
     * 根据数据库连接构造一个数据库信息提供者
     */
    public DbProvider() {
        super();
    }

    /**
     * @return 获取当前的数据库类别(一般为数据库名，可能为空)
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * @param catalog 设置当前的数据库类别(一般为数据库名，可以为空)
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * @return 获取当前的数据库属主(一般为用户名，可能为空)
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema 设置当前的数据库属主(一般为用户名，可以为空)
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @return 表名称匹配字符串, 表名模式，支持多个表名模式，以英文逗号分隔
     */
    public String getTableNamePatterns() {
        return tableNamePatterns;
    }

    /**
     * @param tableNamePatterns 设置表名模式
     */
    public void setTableNamePatterns(String tableNamePatterns) {
        this.tableNamePatterns = tableNamePatterns;
    }

    /**
     * @return 取得一组列模型处理器
     */
    public List<ColumnHandler> getColumnHandlers() {
        return columnHandlers;
    }

    /**
     * @param columnHandlers 设置一组列模型处理器
     */
    public void setColumnHandlers(List<ColumnHandler> columnHandlers) {
        this.columnHandlers = columnHandlers;
    }


    /**
     * 获取在catalog和schema范围内的所有表名称。
     */
    public List<String> getTableNames() throws Exception {
        if (tableNames != null) {
            return tableNames;
        }
        ResultSet rs;
        try {
            if (StringUtils.isBlank(tableNamePatterns)) {
                rs = DbUtil.getConnection().getMetaData().getTables(catalog, schema, "%", new String[]{"TABLE"});
                while (rs.next()) {
                    tableNames.add(rs.getString("TABLE_NAME"));
                }
            } else {
                for (String tnp : tableNamePatterns.split(",")) {
                    rs = DbUtil.getConnection().getMetaData().getTables(catalog, schema, tnp, null);
                    while (rs.next()) {
                        tableNames.add(rs.getString("TABLE_NAME"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
        }
        return tableNames;
    }

    /**
     * 取得表的元数据
     *
     * 表类型，典型的类型包括 "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
     */
    public Map<String, TableMetadata> getTableMetaData() {
        if (tableMetadatas != null && tableMetadatas.size() > 0) {
            return tableMetadatas;
        }
        if (StringUtils.isBlank(tableNamePatterns)) {
            tableMetadatas.putAll(getTableMetaData("%"));
        } else {
            for (String tnp : tableNamePatterns.split(",")) {
                tableMetadatas.putAll(getTableMetaData(tnp));
            }
        }

        return tableMetadatas;
    }


    public Map<String, TableMetadata> getTableMetaData(String tableNamePattern) {
        ResultSet rs = null;
        try {
            DatabaseMetaData databaseMetaData = DbUtil.getConnection().getMetaData();
            rs = databaseMetaData.getTables(catalog, schema, tableNamePattern, null);
            List<String> columnNameList = Lists.newArrayList();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNameList.add(rsmd.getColumnName(i));
            }

            TableMetadata tmd;
            while (rs.next()) {
                tmd = new TableMetadata(rs, databaseMetaData, true);
                tableMetadatas.put(tmd.getName(), tmd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(rs);
        }
        return tableMetadatas;
    }

    public static void main(String[] args) {
        DbProvider p = new DbProvider();
        Map<String, TableMetadata> map = p.getTableMetaData("au_user");
        System.out.println();
    }
}
