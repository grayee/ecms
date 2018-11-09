package com.qslion.moudles.codegen.ddl;

import com.google.common.collect.Lists;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/9.
 */
public class IndexMetadata {

    private final String name;
    private final List<ColumnMetadata> columns = Lists.newArrayList();

    IndexMetadata(ResultSet rs) throws SQLException {
        this.name = rs.getString("INDEX_NAME");
    }

    public String getName() {
        return this.name;
    }

    void addColumn(ColumnMetadata column) {
        if (column != null) {
            this.columns.add(column);
        }
    }

    public ColumnMetadata[] getColumns() {
        return this.columns.toArray(new ColumnMetadata[columns.size()]);
    }

    @Override
    public String toString() {
        return "IndexMatadata(" + this.name + ')';
    }
}
