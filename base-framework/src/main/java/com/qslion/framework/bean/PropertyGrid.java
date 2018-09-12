package com.qslion.framework.bean;

import com.google.common.collect.Lists;

import com.qslion.framework.util.Localize;

import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by zhangruigang on 2018/1/6.
 */
public class PropertyGrid<T> {


    /**
     * total : 7 rows : [{"name":"Name","value":"Bill Smith","group":"ID
     * Settings","editor":{"type":"text"}},{"name":"Address","value":"","group":"ID
     * Settings","editor":{"type":"text"}},{"name":"Age","value":"40","group":"ID
     * Settings","editor":{"type":"numberbox"}},{"name":"Birthday","value":"01/02/2012","group":"ID
     * Settings","editor":{"type":"datebox"}},{"name":"SSN","value":"123-456-7890","group":"ID
     * Settings","editor":{"type":"text"}},{"name":"Email","value":"bill@gmail.com","group":"Marketing
     * Settings","editor":{"type":"validatebox","options":{"validType":"email"}}},{"name":"FrequentBuyer","value":"false","group":"Marketing
     * Settings","editor":{"type":"checkbox","options":{"on":true,"off":false}}}]
     */

    public enum EditorType {
        text, textarea, checkbox, numberbox, validatebox, datebox, combobox, combotree
    }


    private int total;
    private List<GridRow> rows;


    public PropertyGrid(T entity) {
        rows = Lists.newArrayList();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Class<?> fieldType = field.getType();
                Object fieldValue = null;
                if (fieldType.isEnum()) {
                    fieldValue = fieldType.getMethod("getName").getDefaultValue();
                } else {
                    field.get(entity);
                }

                if (fieldValue != null) {
                    GridProperty gridProperty = field.getAnnotation(GridProperty.class);
                    if (gridProperty != null) {
                        GridRow gridRow = new GridRow(Localize.getMessage(gridProperty.value()), fieldValue.toString());
                        if (fieldValue instanceof String) {
                            gridRow.setEditor(new GridRow.RowEditor(EditorType.text.toString()));
                        }
                        rows.add(gridRow);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (CollectionUtils.isNotEmpty(rows)) {
            total = rows.size();
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GridRow> getRows() {
        return rows;
    }

    public void setRows(List<GridRow> rows) {
        this.rows = rows;
    }

    public static class GridRow {
        /**
         * name : Name value : Bill Smith group : ID Settings editor : {"type":"text"}
         */

        private String name;
        private String value;
        private String group;
        private RowEditor editor;

        public GridRow(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public RowEditor getEditor() {
            return editor;
        }

        public void setEditor(RowEditor editor) {
            this.editor = editor;
        }

        public static class RowEditor {
            /**
             * type : text
             */

            private String type;

            private Object options;

            public RowEditor(String type) {
                this.type = type;
            }

            public Object getOptions() {
                return options;
            }

            public void setOptions(Object options) {
                this.options = options;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
