package com.qslion.moudles.codegen.ddl;

import com.google.common.collect.Maps;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.mapping.ForeignKey;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/9.
 */
public class TableMetaData {

    /**
     * 表类别
     */
    private final String catalog;
    /**
     * 表模式
     */
    private final String schema;
    /**
     * 表名称
     */
    private final String name;
    /**
     * 表的解释性注释
     */
    private final String comment;
    /**
     * 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"
     */
    private final String type;
    /**
     * 表类型的类别
     */
    private final String typeCatalog;
    /**
     * 表类型模式
     */
    private final String typeSchema;
    /**
     * 表类型名称
     */
    private final String typeName;
    /**
     * 类型表的指定 "identifier" 列的名称（可为 null）
     */
    private final String selfReferencingColName;
    /**
     * 指定在 SELF_REFERENCING_COL_NAME 中创建值的方式。值为 "SYSTEM"、"USER" 和 "DERIVED"。（可能为 null）
     */
    private final String fefGeneration;
    private final Map<String, ColumnMetaData> columns = Maps.newHashMap();
    private final Map<String, ForeignKeyMetaData> foreignKeys = Maps.newHashMap();
    private final Map<String, IndexMetaData> indexes = Maps.newHashMap();

    public TableMetaData(ResultSet rs, DatabaseMetaData meta, boolean extras) throws SQLException {
        List<String> columnNameList = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnNameList.add(rsmd.getColumnName(i));
        }
        this.catalog = columnNameList.contains("TABLE_CAT") ? rs.getString("TABLE_CAT") : StringUtils.EMPTY;
        this.schema = columnNameList.contains("TABLE_SCHEM") ? rs.getString("TABLE_SCHEM") : StringUtils.EMPTY;
        this.name = columnNameList.contains("TABLE_NAME") ? rs.getString("TABLE_NAME") : StringUtils.EMPTY;
        this.comment = columnNameList.contains("REMARKS") ? rs.getString("REMARKS") : StringUtils.EMPTY;
        this.type = columnNameList.contains("TABLE_TYPE") ? rs.getString("TABLE_TYPE") : StringUtils.EMPTY;
        this.typeCatalog = columnNameList.contains("TYPE_CAT") ? rs.getString("TYPE_CAT") : StringUtils.EMPTY;
        this.typeSchema = columnNameList.contains("TYPE_SCHEM") ? rs.getString("TYPE_SCHEM") : StringUtils.EMPTY;
        this.typeName = columnNameList.contains("TYPE_NAME") ? rs.getString("TYPE_NAME") : StringUtils.EMPTY;
        this.selfReferencingColName = columnNameList.contains("SELF_REFERENCING_COL_NAME") ?
            rs.getString("SELF_REFERENCING_COL_NAME") : StringUtils.EMPTY;
        this.fefGeneration = columnNameList.contains("REF_GENERATION") ? rs.getString("REF_GENERATION") : StringUtils.EMPTY;

        this.initColumns(meta);
        if (extras) {
            this.initForeignKeys(meta);
            this.initIndexes(meta);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public String getSchema() {
        return this.schema;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }

    public String getTypeCatalog() {
        return typeCatalog;
    }

    public String getTypeSchema() {
        return typeSchema;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getSelfReferencingColName() {
        return selfReferencingColName;
    }

    public String getFefGeneration() {
        return fefGeneration;
    }

    @Override
    public String toString() {
        return "TableMetaData(" + this.name + ')';
    }

    public ColumnMetaData getColumnMetadata(String columnName) {
        return this.columns.get(columnName.toLowerCase());
    }

    public ForeignKeyMetaData getForeignKeyMetadata(String keyName) {
        return this.foreignKeys.get(keyName.toLowerCase());
    }

    public ForeignKeyMetaData getForeignKeyMetadata(ForeignKey fk) {
        Iterator var2 = this.foreignKeys.values().iterator();

        ForeignKeyMetaData existingFk;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            existingFk = (ForeignKeyMetaData) var2.next();
        } while (!existingFk.matches(fk));

        return existingFk;
    }

    public IndexMetaData getIndexMetadata(String indexName) {
        return this.indexes.get(indexName.toLowerCase());
    }

    private void addForeignKey(ResultSet rs) throws SQLException {
        String fk = rs.getString("FK_NAME");
        if (fk != null) {
            ForeignKeyMetaData info = this.getForeignKeyMetadata(fk);
            if (info == null) {
                info = new ForeignKeyMetaData(rs);
                this.foreignKeys.put(info.getName().toLowerCase(), info);
            }

            info.addReference(rs);
        }
    }

    private void addIndex(ResultSet rs) throws SQLException {
        String index = rs.getString("INDEX_NAME");
        if (index != null) {
            IndexMetaData info = this.getIndexMetadata(index);
            if (info == null) {
                info = new IndexMetaData(rs);
                this.indexes.put(info.getName().toLowerCase(), info);
            }

            info.addColumn(this.getColumnMetadata(rs.getString("COLUMN_NAME")));
        }
    }

    public void addColumn(ResultSet rs) throws SQLException {
        String column = rs.getString("COLUMN_NAME");
        if (column != null) {
            if (this.getColumnMetadata(column) == null) {
                ColumnMetaData info = new ColumnMetaData(rs);
                this.columns.put(info.getName().toLowerCase(), info);
            }
        }
    }

    private void initForeignKeys(DatabaseMetaData meta) throws SQLException {
        ResultSet rs = null;
        try {
            rs = meta.getImportedKeys(this.catalog, this.schema, this.name);
            while (rs.next()) {
                this.addForeignKey(rs);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private void initIndexes(DatabaseMetaData meta) throws SQLException {
        ResultSet rs = null;
        try {
            rs = meta.getIndexInfo(this.catalog, this.schema, this.name, false, true);
            while (rs.next()) {
                if (rs.getShort("TYPE") != 0) {
                    this.addIndex(rs);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private void initColumns(DatabaseMetaData meta) throws SQLException {
        ResultSet rs = null;
        try {
            rs = meta.getColumns(this.catalog, this.schema, this.name, "%");
            while (rs.next()) {
                this.addColumn(rs);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
