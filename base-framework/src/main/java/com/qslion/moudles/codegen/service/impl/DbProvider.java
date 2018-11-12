package com.qslion.moudles.codegen.service.impl;

import static com.qslion.framework.util.DbUtil.getConnection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qslion.framework.util.DbUtil;
import com.qslion.moudles.codegen.ddl.TableMetaData;
import com.qslion.moudles.codegen.entity.DdColumn;
import com.qslion.moudles.codegen.entity.DdConstraint;
import com.qslion.moudles.codegen.entity.DdConstraint.ConstraintType;
import com.qslion.moudles.codegen.entity.DdTable;
import com.qslion.moudles.codegen.service.ColumnHandler;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 10:30.
 */
public class DbProvider {

    private final String TABLE_SEPARATOR = ",";
    private final String TABLE_NAME_PATTERN = "%";
    private String catalog;
    private String schema;
    private String tableNamePatterns;
    private List<ColumnHandler> columnHandlers = Lists.newArrayList();
    private List<String> tableNames = Lists.newArrayList();
    private Map<String, String> tableComments;
    private Map<String, TableMetaData> tableMetadatas = Maps.newHashMap();


    private static Map<String, Class<?>> javaTypeMap = ImmutableMap.<String, Class<?>>builder()
        .put("Integer", Integer.class)
        .put("Long", Long.class)
        .put("Short", Short.class)
        .put("Byte", Byte.class)
        .put("Byte[]", Byte[].class)
        .put("Double", Double.class)
        .put("BigDecimal", BigDecimal.class)
        .put("Float", Float.class)
        .put("Date", Date.class)
        .put("String", String.class)
        .put("Boolean", Boolean.class)
        .put("Enum", Enum.class)
        .build();

    public static Map<String, Class<?>> jdbcTypeMap = ImmutableMap.<String, Class<?>>builder()
        .put("BIT", Boolean.class)
        .put("TINYINT", Byte.class)
        .put("SMALLINT", Short.class)
        .put("INTEGER", Integer.class)
        .put("INT", Integer.class)
        .put("BIGINT", Long.class)
        .put("FLOAT", Float.class)
        .put("DOUBLE", Double.class)
        .put("DECIMAL", BigDecimal.class)
        .put("CHAR", String.class)
        .put("VARCHAR", String.class)
        .put("LONGVARCHAR", String.class)
        .put("CLOB", String.class)
        .put("DATE", Date.class)
        .put("TIME", Date.class)
        .put("TIMESTAMP", Date.class)
        .put("DATETIME", Date.class)
        .put("BINARY", Byte[].class)
        .put("VARBINARY", Byte[].class)
        .put("LONGVARBINARY", Byte[].class)
        .put("BLOB", Byte[].class)
        .put("REAL", Byte[].class)
        .build();

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
                rs = getConnection().getMetaData().getTables(catalog, schema, TABLE_NAME_PATTERN, new String[]{"TABLE"});
                while (rs.next()) {
                    tableNames.add(rs.getString("TABLE_NAME"));
                }
            } else {
                for (String tnp : tableNamePatterns.split(TABLE_SEPARATOR)) {
                    rs = getConnection().getMetaData().getTables(catalog, schema, tnp, new String[]{"TABLE"});
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
    public Map<String, TableMetaData> getTableMetaData() {
        if (tableMetadatas != null && tableMetadatas.size() > 0) {
            return tableMetadatas;
        }
        if (StringUtils.isBlank(tableNamePatterns)) {
            tableMetadatas.putAll(getTableMetaData(TABLE_NAME_PATTERN));
        } else {
            for (String tnp : tableNamePatterns.split(TABLE_SEPARATOR)) {
                tableMetadatas.putAll(getTableMetaData(tnp));
            }
        }
        return tableMetadatas;
    }


    /**
     * 取得表的元数据
     *
     * @param tableNamePattern 表名称匹配字符串
     * @return 表的元数据
     */
    public Map<String, TableMetaData> getTableMetaData(String tableNamePattern) {
        ResultSet rs = null;
        try {
            DatabaseMetaData databaseMetaData = getConnection().getMetaData();
            rs = databaseMetaData.getTables(catalog, schema, tableNamePattern, new String[]{"TABLE"});
            List<String> columnNameList = Lists.newArrayList();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNameList.add(rsmd.getColumnName(i));
            }

            TableMetaData tmd;
            while (rs.next()) {
                tmd = new TableMetaData(rs, databaseMetaData, true);
                tableMetadatas.put(tmd.getName(), tmd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(rs);
        }
        return tableMetadatas;
    }

    /**
     * 获取指定表的所有的列名称
     *
     * @return 列名称集合
     */
    public List<String> getColumnNames(String tableName) {
        List<String> colList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().createStatement();
            String sql = String.format("select * from '%s'", tableName);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                colList.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(stmt);
            DbUtil.close(rs);
        }
        return colList;
    }


    /**
     * 获取指定表的主键模型集合
     *
     * @param tableName 表名称
     * @return 约束集合
     */
    protected List<DdConstraint> getKeys(String tableName) {
        ResultSet rs = null;
        List<DdConstraint> constraints = new ArrayList<>();
        try {
            DatabaseMetaData dbMetaData = DbUtil.getConnection().getMetaData();
            rs = dbMetaData.getPrimaryKeys(this.catalog, this.schema, tableName);
            while (rs.next()) {
                DdConstraint primaryKey = new DdConstraint(ConstraintType.PRIMARY_KEY);
                primaryKey.setTableName(rs.getString("TABLE_NAME"));
                primaryKey.setColumnName(rs.getString("COLUMN_NAME"));
                primaryKey.setConstraintName(rs.getString("PK_NAME"));
                constraints.add(primaryKey);
            }

            rs = dbMetaData.getImportedKeys(catalog, schema, tableName);
            while (rs.next()) {
                DdConstraint foreignKeyImported = new DdConstraint(ConstraintType.FOREIGN_KEY_IMPORTED);
                foreignKeyImported.setTableName(rs.getString("PKTABLE_NAME"));
                foreignKeyImported.setColumnName(rs.getString("PKCOLUMN_NAME"));
                foreignKeyImported.setRefTableName(rs.getString("FKTABLE_NAME"));
                foreignKeyImported.setRefColumnName(rs.getString("FKCOLUMN_NAME"));
                foreignKeyImported.setKeySeq(rs.getShort("KEY_SEQ"));
                foreignKeyImported.setConstraintName(rs.getString("PK_NAME"));
                foreignKeyImported.setRefConstraintName(rs.getString("FK_NAME"));
                constraints.add(foreignKeyImported);
            }

            rs = dbMetaData.getExportedKeys(catalog, schema, tableName);
            while (rs.next()) {
                DdConstraint foreignKeyExported = new DdConstraint(ConstraintType.FOREIGN_KEY_EXPORTED);
                foreignKeyExported.setTableName(rs.getString("PKTABLE_NAME"));
                foreignKeyExported.setColumnName(rs.getString("PKCOLUMN_NAME"));
                foreignKeyExported.setRefTableName(rs.getString("FKTABLE_NAME"));
                foreignKeyExported.setRefColumnName(rs.getString("FKCOLUMN_NAME"));
                foreignKeyExported.setKeySeq(rs.getShort("KEY_SEQ"));
                foreignKeyExported.setConstraintName(rs.getString("PK_NAME"));
                foreignKeyExported.setRefConstraintName(rs.getString("FK_NAME"));
                constraints.add(foreignKeyExported);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(rs);
        }
        return constraints;
    }


    /**
     * 根据表名获取表注释，如果未取得注释，则返回空字符串
     *
     * @param tableName 表名称
     * @return 表注释
     */
    public String getTableComment(String tableName) {
        return getTableComment(tableName, "");
    }

    /**
     * 根据表名获取表注释，如果未取得注释，则返回指定的默认值
     *
     * @param tableName 表名称
     * @param defaultValue 指定的默认表注释
     * @return 表注释
     */
    public String getTableComment(String tableName, String defaultValue) {
        String comment = getTableComments().get(tableName);
        return StringUtils.defaultString(comment, defaultValue);
    }

    /**
     * 取得所有表的表注释，不同的数据库方言有不同的实现方式。
     */
    protected Map<String, String> doGetTableComments() {
        return null;
    }

    /**
     * 一次性获取所有表的表注释，不同的数据库方言有不同的实现方式。
     */
    public Map<String, String> getTableComments() {
        if (tableComments == null) {
            tableComments = doGetTableComments();
        }
        return tableComments;
    }

    /**
     * 根据指定的表名创建一个表模型
     *
     * @param tableName 表名称
     */
    public DdTable createTableModel(String tableName) {
        DdTable table = new DdTable();

        //设置表的相关元数据
        table.setTableName(tableName);
        table.setSchema(getSchema());
        table.setCatalog(getCatalog());
        table.setConstraints(getKeys(table.getTableName()));

        table.setRemark(getTableComment(table.getTableName(), table.getTableName()));
        //table.setDisplayName(getTableLabelFromComment(table.getTabComment()));
        if (getTableMetaData().containsKey(tableName)) {
            table.setTableType(getTableMetaData().get(tableName).getType());
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DbUtil.getConnection().createStatement();
            String sql = String.format("select * from '%s'", tableName);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DdColumn col = null;
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                col = new DdColumn();
                table.getColumns().add(col);

                col.setColumnName(rsmd.getColumnName(i));
                col.setDisplayName(rsmd.getColumnName(i));
                //col.setRemark(getColumnComment(tableName, col.getColumnName()));
                //col.setColumnLabel(getColumnLabelFromComment(col.getColComment()));
                //col.setRemark(getColumnRemarkFromComment(col.getColComment()));
                //设置sql数据类型
                //col.setColumnType(rsmd.getColumnType(i));
                //设置sql数据类型的名称
                //col.setColumnTypeName(rsmd.getColumnTypeName(i));
                //设置列的sql数据类型在java编程语言中对应的具体数据类型
                //col.setColumnClassName(rsmd.getColumnClassName(i));

                //取得列显示的最大宽度
                col.setLength((long) rsmd.getColumnDisplaySize(i));
                //设置列的数据标度
                col.setScale(rsmd.getScale(i));
                //判断该列是否为自增列
                col.setAutoIncrement(rsmd.isAutoIncrement(i));
                //如果不是未知类型，则设置列的数据精度
                col.setPrecision(rsmd.getPrecision(i));
                //判断该列允许空否
                if (rsmd.isNullable(i) == ResultSetMetaData.columnNoNulls) {
                    col.setNullable(false);
                } else {
                    col.setNullable(true);
                }
                //判断是否为主键列
                if (table.isKey(col.getColumnName(), ConstraintType.PRIMARY_KEY)) {
                    col.setPrimaryKey(true);
                }
                //判断是否为外键列（参照其他表的键）
                if (table.isKey(col.getColumnName(), ConstraintType.FOREIGN_KEY_IMPORTED)) {
                    col.setImportedKey(true);
                }
                //判断是否为外键列（被其他表参照）
                if (table.isKey(col.getColumnName(), ConstraintType.FOREIGN_KEY_EXPORTED)) {
                    col.setExportedKey(true);
                }
                //判断是否为唯一键
                if (table.isKey(col.getColumnName(), ConstraintType.UNIQUE_KEY)) {
                    col.setUniqueKey(true);
                }

                //设置列所归属的表模型，这样在列模型处理器里还可以根据表模型的数据来做更多的逻辑判断
                col.setTable(table);

                //如果没有设置任何的列模型处理器，则默认使用一个java数据类型转换器来处理列模型
                if (columnHandlers == null || columnHandlers.size() == 0) {
                    col.setColumnType("");
                } else {
                    for (ColumnHandler columnHandler : columnHandlers) {
                        columnHandler.handle(col);
                    }
                }
            }
            System.out.println("成功创建表模型" + table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(stmt);
            DbUtil.close(rs);
        }
        return table;
    }


    public static Set<String> javaType() {
        return javaTypeMap.keySet();
    }

    public static Class<?> javaClass(String javaType) {
        return javaTypeMap.get(javaType);
    }

    public static Set<String> jdbcType() {
        return jdbcTypeMap.keySet();
    }

    public static Class<?> jdbcClass(String jdbcType) {
        return jdbcTypeMap.get(jdbcType);
    }

    public static void main(String[] args) {
        DbProvider p = new DbProvider();
        Map<String, TableMetaData> map = p.getTableMetaData("au_user");
        System.out.println();
    }
}
