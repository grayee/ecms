
package com.qslion.framework.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 筛选
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
public class QueryFilter implements Serializable {

    private static final long serialVersionUID = -8712382358441065075L;

    /**
     * 运算符
     */
    public enum Operator {
        /**
         * filed = value
         */
        equal,

        //下面四个用于Number类型的比较
        /**
         * filed > value
         */
        gt,
        /**
         * field >= value
         */
        ge,
        /**
         * field < value
         */
        lt,
        /**
         * field <= value
         */
        le,

        /**
         * field != value
         */
        notEqual,
        /**
         * field like value
         */
        like,
        /**
         * field not like value
         */
        notLike,

        // 下面四个用于可比较类型(Comparable)的比较
        /**
         * field > value
         */
        greaterThan,
        /**
         * field >= value
         */
        greaterThanOrEqualTo,
        /**
         * field < value
         */
        lessThan,
        /**
         * field <= value
         */
        lessThanOrEqualTo,
        /**
         * 包含
         */
        in,

        /**
         * 为Null
         */
        isNull,

        /**
         * 不为Null
         */
        isNotNull,
        /**
         * 区间
         */
        between,
    }

    /**
     * 默认是否忽略大小写
     */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /**
     * 属性
     */
    private String property;

    /**
     * 运算符
     */
    private Operator operator;

    /**
     * 值
     */
    private Object value;

    /**
     * 是否忽略大小写
     */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 构造方法
     */
    public QueryFilter() {
    }

    /**
     * 构造方法
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     */
    public QueryFilter(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 构造方法
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     * @param ignoreCase 忽略大小写
     */
    public QueryFilter(String property, Operator operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 返回等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 等于筛选
     */
    public static QueryFilter eq(String property, Object value) {
        return new QueryFilter(property, Operator.equal, value);
    }

    /**
     * 返回等于筛选
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static QueryFilter eq(String property, Object value, boolean ignoreCase) {
        return new QueryFilter(property, Operator.equal, value, ignoreCase);
    }

    /**
     * 返回不等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 不等于筛选
     */
    public static QueryFilter ne(String property, Object value) {
        return new QueryFilter(property, Operator.notEqual, value);
    }

    /**
     * 返回不等于筛选
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static QueryFilter ne(String property, Object value, boolean ignoreCase) {
        return new QueryFilter(property, Operator.notEqual, value, ignoreCase);
    }

    /**
     * 返回大于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 大于筛选
     */
    public static QueryFilter gt(String property, Object value) {
        return new QueryFilter(property, Operator.gt, value);
    }

    /**
     * 返回小于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 小于筛选
     */
    public static QueryFilter lt(String property, Object value) {
        return new QueryFilter(property, Operator.lt, value);
    }

    /**
     * 返回大于等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 大于等于筛选
     */
    public static QueryFilter ge(String property, Object value) {
        return new QueryFilter(property, Operator.ge, value);
    }

    /**
     * 返回小于等于筛选
     *
     * @param property 属性
     * @param value 值
     * @return 小于等于筛选
     */
    public static QueryFilter le(String property, Object value) {
        return new QueryFilter(property, Operator.le, value);
    }

    /**
     * 返回相似筛选
     *
     * @param property 属性
     * @param value 值
     * @return 相似筛选
     */
    public static QueryFilter like(String property, Object value) {
        return new QueryFilter(property, Operator.like, value);
    }

    /**
     * 返回包含筛选
     *
     * @param property 属性
     * @param value 值
     * @return 包含筛选
     */
    public static QueryFilter in(String property, Object value) {
        return new QueryFilter(property, Operator.in, value);
    }

    /**
     * 返回为Null筛选
     *
     * @param property 属性
     * @return 为Null筛选
     */
    public static QueryFilter isNull(String property) {
        return new QueryFilter(property, Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    public static QueryFilter isNotNull(String property) {
        return new QueryFilter(property, Operator.isNotNull, null);
    }

    /**
     * 返回忽略大小写筛选
     *
     * @return 忽略大小写筛选
     */
    public QueryFilter ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    /**
     * 获取属性
     *
     * @return 属性
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置属性
     *
     * @param property 属性
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取运算符
     *
     * @return 运算符
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置运算符
     *
     * @param operator 运算符
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取是否忽略大小写
     *
     * @return 是否忽略大小写
     */
    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    /**
     * 设置是否忽略大小写
     *
     * @param ignoreCase 是否忽略大小写
     */
    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    /**
     * 重写equals方法
     *
     * @param obj 对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * 重写hashCode方法
     *
     * @return HashCode
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}