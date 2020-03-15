package com.qslion.framework.bean;

import com.google.common.collect.Lists;
import com.qslion.framework.util.Localize;
import com.qslion.framework.util.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;


/**
 * 显示列
 *
 * @author Gray.Z
 * @date 2018/9/22 22:05.
 */
public class DisplayColumn {

    private double id;
    private String title;
    private String field;
    private String width;
    private boolean show;
    private String align;
    private boolean sortable;

    public DisplayColumn(String field, DisplayTitle displayTitle, DisplayField displayField) {
        this.id = displayField.id();
        if (displayField.title().startsWith("{") && displayField.title().endsWith("}")) {
            this.title = Localize.getMessage(displayField.title().replaceAll("[{|}]+", ""));
        } else if (displayField.title().startsWith("%s")) {
            this.title = displayTitle != null ? String.format(displayField.title(), displayTitle.name()) : displayField.title().replace("%s", "");
        } else {
            this.title = displayField.title();
        }
        this.field = field;
        if (String.valueOf(displayField.width()).endsWith("px")) {
            this.width = String.valueOf(displayField.width());
        } else {
            this.width = String.format("%s%%", displayField.width());
        }

        this.show = displayField.show();
        this.align = displayField.align();
        this.sortable = displayField.sortable();
    }

    public static List<DisplayColumn> getDisplayColumns(Class<?> entityClazz) {
        List<DisplayColumn> displayColumns = Lists.newArrayList();
        List<Field> fieldList = ReflectUtils.getFields(entityClazz);
        DisplayTitle displayTitle = entityClazz.getDeclaredAnnotation(DisplayTitle.class);
        fieldList.stream().filter(field -> field.isAnnotationPresent(DisplayField.class)).forEach(field -> {
            DisplayField displayField = field.getAnnotation(DisplayField.class);
            DisplayColumn displayColumn = new DisplayColumn(field.getName(), displayTitle, displayField);
            displayColumns.add(displayColumn);
        });
        displayColumns.sort(Comparator.comparing(DisplayColumn::getId));
        return displayColumns;
    }


    public double getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }
}
