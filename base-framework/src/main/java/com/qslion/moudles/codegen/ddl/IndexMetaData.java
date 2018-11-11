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
public class IndexMetaData {

    private final String name;
    private final List<ColumnMetaData> columns = Lists.newArrayList();

    IndexMetaData(ResultSet rs) throws SQLException {
        this.name = rs.getString("INDEX_NAME");
    }

    public String getName() {
        return this.name;
    }

    void addColumn(ColumnMetaData column) {
        if (column != null) {
            this.columns.add(column);
        }
    }

    public ColumnMetaData[] getColumns() {
        return this.columns.toArray(new ColumnMetaData[columns.size()]);
    }

    @Override
    public String toString() {
        return "IndexMatadata(" + this.name + ')';
    }
}
