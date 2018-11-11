package com.qslion.moudles.codegen.ddl;

import com.google.common.collect.Maps;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.ForeignKey;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/9.
 */
public class ForeignKeyMetaData {

    private final String name;
    private final String refTable;
    private final Map<String, String> references = Maps.newHashMap();

    ForeignKeyMetaData(ResultSet rs) throws SQLException {
        this.name = rs.getString("FK_NAME");
        this.refTable = rs.getString("PKTABLE_NAME");
    }

    public String getName() {
        return this.name;
    }

    public String getReferencedTableName() {
        return this.refTable;
    }

    void addReference(ResultSet rs) throws SQLException {
        this.references.put(rs.getString("FKCOLUMN_NAME").toLowerCase(), rs.getString("PKCOLUMN_NAME"));
    }

    private boolean hasReference(Column column, Column ref) {
        String refName = this.references.get(column.getName().toLowerCase());
        return ref.getName().equalsIgnoreCase(refName);
    }

    public boolean matches(ForeignKey fk) {
        if (this.refTable.equalsIgnoreCase(fk.getReferencedTable().getName()) && fk.getColumnSpan() == this.references
            .size()) {
            List fkRefs;
            if (fk.isReferenceToPrimaryKey()) {
                fkRefs = fk.getReferencedTable().getPrimaryKey().getColumns();
            } else {
                fkRefs = fk.getReferencedColumns();
            }

            for (int i = 0; i < fk.getColumnSpan(); ++i) {
                Column column = fk.getColumn(i);
                Column ref = (Column) fkRefs.get(i);
                if (!this.hasReference(column, ref)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ForeignKeyMetaData(" + this.name + ')';
    }
}
