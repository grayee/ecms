package com.qslion.framework.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 分页信息
 *
 * @author Gray.Z
 * @date 2018/4/3 20:25.
 */
public class Pageable implements Serializable {

    private static final long serialVersionUID = -3930180379790344299L;

    /**
     * 默认页码
     */
    private static final int DEFAULT_PAGE_NUMBER = 1;

    /**
     * 默认每页记录数
     */
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 最大每页记录数
     */
    private static final int MAX_PAGE_SIZE = 1000;

    /**
     * 页码
     */
    private int pageNo = DEFAULT_PAGE_NUMBER;

    /**
     * 每页记录数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 搜索属性
     */
    private String searchProperty;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 排序属性
     */
    private String orderProperty;

    /**
     * 排序方向
     */
    private Order.Direction orderDirection;

    /**
     * 筛选
     */
    private List<QueryFilter> queryFilters = new ArrayList<>();

    /**
     * 排序
     */
    private List<Order> orders = new ArrayList<>();

    /**
     * 构造方法
     */
    public Pageable() {
    }

    /**
     * 构造方法
     *
     * @param pageNo 页码
     * @param pageSize 每页记录数
     */
    public Pageable(Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageNo >= 1) {
            this.pageNo = pageNo;
        }
        if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置页码
     *
     * @param pageNo 页码
     */
    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = DEFAULT_PAGE_NUMBER;
        }
        this.pageNo = pageNo;
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     *
     * @param pageSize 每页记录数
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * 获取搜索属性
     *
     * @return 搜索属性
     */
    public String getSearchProperty() {
        return searchProperty;
    }

    /**
     * 设置搜索属性
     *
     * @param searchProperty 搜索属性
     */
    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }

    /**
     * 获取搜索值
     *
     * @return 搜索值
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * 设置搜索值
     *
     * @param searchValue 搜索值
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * 获取排序属性
     *
     * @return 排序属性
     */
    public String getOrderProperty() {
        return orderProperty;
    }

    /**
     * 设置排序属性
     *
     * @param orderProperty 排序属性
     */
    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    /**
     * 获取排序方向
     *
     * @return 排序方向
     */
    public Order.Direction getOrderDirection() {
        return orderDirection;
    }

    /**
     * 设置排序方向
     *
     * @param orderDirection 排序方向
     */
    public void setOrderDirection(Order.Direction orderDirection) {
        this.orderDirection = orderDirection;
    }

    /**
     * 获取筛选
     *
     * @return 筛选
     */
    public List<QueryFilter> getQueryFilters() {
        return queryFilters;
    }

    /**
     * 设置筛选
     *
     * @param queryFilters 筛选
     */
    public void setQueryFilters(List<QueryFilter> queryFilters) {
        this.queryFilters = queryFilters;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * 设置排序
     *
     * @param orders 排序
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
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