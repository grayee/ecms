package com.qslion.moudles.codegen.ddl;

import com.google.common.collect.Maps;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.mapping.ForeignKey;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/9.
 */
public class TableMetadata {

    private final String catalog;
    private final String schema;
    private final String name;
    private final String comment;
    private final Map<String, ColumnMetadata> columns = Maps.newHashMap();
    private final Map<String, ForeignKeyMetadata> foreignKeys = Maps.newHashMap();
    private final Map<String, IndexMetadata> indexes = Maps.newHashMap();

    public TableMetadata(ResultSet rs, DatabaseMetaData meta, boolean extras) throws SQLException {
        this.catalog = rs.getString("TABLE_SCHEMA");
        this.schema = rs.getString("TABLE_SCHEMA");
        this.name = rs.getString("TABLE_NAME");
        this.comment = rs.getString("TABLE_COMMENT");
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

    @Override
    public String toString() {
        return "TableMetadata(" + this.name + ')';
    }

    public ColumnMetadata getColumnMetadata(String columnName) {
        return this.columns.get(columnName.toLowerCase());
    }

    public ForeignKeyMetadata getForeignKeyMetadata(String keyName) {
        return this.foreignKeys.get(keyName.toLowerCase());
    }

    public ForeignKeyMetadata getForeignKeyMetadata(ForeignKey fk) {
        Iterator var2 = this.foreignKeys.values().iterator();

        ForeignKeyMetadata existingFk;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            existingFk = (ForeignKeyMetadata) var2.next();
        } while (!existingFk.matches(fk));

        return existingFk;
    }

    public IndexMetadata getIndexMetadata(String indexName) {
        return this.indexes.get(indexName.toLowerCase());
    }

    private void addForeignKey(ResultSet rs) throws SQLException {
        String fk = rs.getString("FK_NAME");
        if (fk != null) {
            ForeignKeyMetadata info = this.getForeignKeyMetadata(fk);
            if (info == null) {
                info = new ForeignKeyMetadata(rs);
                this.foreignKeys.put(info.getName().toLowerCase(), info);
            }

            info.addReference(rs);
        }
    }

    private void addIndex(ResultSet rs) throws SQLException {
        String index = rs.getString("INDEX_NAME");
        if (index != null) {
            IndexMetadata info = this.getIndexMetadata(index);
            if (info == null) {
                info = new IndexMetadata(rs);
                this.indexes.put(info.getName().toLowerCase(), info);
            }

            info.addColumn(this.getColumnMetadata(rs.getString("COLUMN_NAME")));
        }
    }

    public void addColumn(ResultSet rs) throws SQLException {
        String column = rs.getString("COLUMN_NAME");
        if (column != null) {
            if (this.getColumnMetadata(column) == null) {
                ColumnMetadata info = new ColumnMetadata(rs);
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
