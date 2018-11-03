package com.qslion.framework.dao.impl;

import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.dao.IGenericJdbcDao;
import com.qslion.framework.util.DbUtil;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

/**
 * Dao实现类 - Dao实现类基类
 */

//@Repository
public class GenericJdbcDaoImpl<T, PK extends Serializable> implements IGenericJdbcDao<T, PK> {

    private static final Logger logger = LogManager.getLogger();
    protected JdbcTemplate jdbcTemplate;
    private Class<T> entityClazz;
    private String tableName;


    @SuppressWarnings("unchecked")
    public GenericJdbcDaoImpl() {
        this.entityClazz = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClazz = (Class<T>) parameterizedType[0];
            if (entityClazz.isAnnotationPresent(Table.class)) {
                tableName = entityClazz.getAnnotation(Table.class).name();
            } else if (entityClazz.isAnnotationPresent(Entity.class)) {
                tableName = entityClazz.getAnnotation(Entity.class).name();
            }

        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean delete(PK id) throws Exception {
        // TODO Auto-generated method stub
        //获得ID字段名和值
        String idColumnName = "";
        boolean flag = false;
        Class<?> superClass = entityClazz.getSuperclass();
        Field[] fields = superClass.getDeclaredFields();
        for (Field field : fields) {
            logger.info("entity field name :" + field.getName());
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entityClazz);
            Method getMethod = pd.getReadMethod();
            if (getMethod.isAnnotationPresent(Id.class)) {
                idColumnName = field.getName();
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new Exception(entityClazz.getName() + " object not found id property.");
        }
        //拼装sql
        String sql = "delete from " + tableName + " where " + idColumnName + "='" + id + "'";
        logger.info(sql);
        getJdbcTemplate().execute(sql);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() throws Exception {
        // TODO Auto-generated method stub
        String sql = getQuerySqlWithValues(entityClazz.newInstance()).get(0).toString();
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(entityClazz);
        List<T> result = null;
        List<Object> values = (List<Object>) getQuerySqlWithValues(entityClazz.newInstance()).get(1);
        if (values != null && values.size() > 0) {
            result = getJdbcTemplate().query(sql, values.toArray(), mapper);
        } else {
            result = getJdbcTemplate().query(sql, mapper);
        }
        return result;
    }

    @Override
    public T findById(String id) throws Exception {
        // TODO Auto-generated method stub
        Class<?> superClass = entityClazz.getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        Field[] fields = entityClazz.getDeclaredFields();
        fields = (Field[]) ArrayUtils.addAll(superFields, fields);
        StringBuilder columnNames = new StringBuilder();
        for (Field field : fields) {
            logger.info("entity field name :" + field.getName());
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entityClazz);
            Method getMethod = pd.getReadMethod();
            if (getMethod.isAnnotationPresent(Id.class)) {
                columnNames.append(field.getName()).append(",");
            } else if (getMethod.isAnnotationPresent(Column.class)) {
                columnNames.append(getMethod.getAnnotation(Column.class).name()).append(",");
            }
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        String sql = "select " + columnNames.toString() + " from " + tableName + " where id='" + id + "'";
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(entityClazz);
        return getJdbcTemplate().queryForObject(sql, mapper);
    }

    @Override
    public PK insert(T entity) throws Exception {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO " + tableName;
        Assert.notNull(entity.getClass().getSuperclass());
        Class<?> superClass = entity.getClass().getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        Field[] fields = entity.getClass().getDeclaredFields();
        fields = (Field[]) ArrayUtils.addAll(superFields, fields);
        StringBuilder columnNames = new StringBuilder();
        //占位符
        StringBuilder placeholders = new StringBuilder();
        List<Object> columnValues = new ArrayList<Object>();
        for (Field field : fields) {
            logger.info("entity field name :" + field.getName());
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entity.getClass());
            Method getMethod = pd.getReadMethod();
            if (getMethod.isAnnotationPresent(Id.class)) {
                columnNames.append(field.getName()).append(",");
                columnValues.add(getMethod.invoke(entity).toString().replace("-", ""));
                placeholders.append("?").append(",");
            } else if (getMethod.isAnnotationPresent(Column.class)) {
                columnNames.append(getMethod.getAnnotation(Column.class).name()).append(",");
                columnValues.add(getMethod.invoke(entity));
                placeholders.append("?").append(",");
            }
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        placeholders.deleteCharAt(placeholders.length() - 1);
        sql += "(" + columnNames.toString() + ")\nVALUES(" + placeholders.toString() + ");";

        logger.info("sql:" + sql);
        final Object[] paramValues = columnValues.toArray();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                for (int i = 1; i <= paramValues.length; i++) {
                    ps.setObject(i, paramValues[i - 1]);
                }
            }
        };
        getJdbcTemplate().update(sql, pss);
        return null;
    }


    public boolean update(T entity) throws Exception {
        // TODO Auto-generated method stub
        List<Object> columnNames = new ArrayList<Object>();
        List<Object> columnValues = new ArrayList<Object>();
        List<String> placeholders = new ArrayList<String>();
        String idColumnName = "";
        Object idColumnValue = "";

        Assert.notNull(entity.getClass().getSuperclass());
        Class<?> superClass = entity.getClass().getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        Field[] fields = entity.getClass().getDeclaredFields();
        fields = (Field[]) ArrayUtils.addAll(superFields, fields);
        for (Field field : fields) {
            logger.info("entity field name :" + field.getName());
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entity.getClass());
            Method getMethod = pd.getReadMethod();
            if (getMethod.isAnnotationPresent(Id.class)) {
                idColumnName = field.getName();
                idColumnValue = getMethod.invoke(entity);
            } else if (getMethod.isAnnotationPresent(Column.class)) {
                columnNames.add(getMethod.getAnnotation(Column.class).name());
                columnValues.add(getMethod.invoke(entity));
                placeholders.add("?");
            }
        }

        //ID作为更新条件，放在集合中的最后一个元素
        columnNames.add(idColumnName);
        columnValues.add(idColumnValue);
        placeholders.add("?");


        //拼接sql
        StringBuilder sql = new StringBuilder("");
        sql.append("update ").append(tableName).append(" set ");
        int index = columnNames.size() - 1;
        for (int i = 0; i < index; i++) {
            sql.append(columnNames.get(i)).append("=").append(placeholders.get(i)).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" where ").append(columnNames.get(index)).append("=").append("?");
        logger.info("sql:" + sql);
        final Object[] paramValues = columnValues.toArray();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                for (int i = 1; i <= paramValues.length; i++) {
                    ps.setObject(i, paramValues[i - 1]);
                }
            }
        };
        return getJdbcTemplate().update(sql.toString(), pss) > 0 ? true : false;
    }

    @SuppressWarnings("unchecked")
    public Pager<T> findByPager(Pageable pageable) throws Exception {
        // TODO Auto-generated method stub
        if (pageable == null) {
            pageable = new Pageable();
        }
        String sql = getQuerySqlWithValues(entityClazz.newInstance()).get(0).toString();
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(entityClazz);
        List<T> result = null;
        Integer totalCount= 0;
        List<Object> values = (List<Object>) getQuerySqlWithValues(entityClazz.newInstance()).get(1);

        if (values != null && values.size() > 0) {
            result = getJdbcTemplate().query(
                DbUtil.getPageSql(sql, (pageable.getPageNo() - 1) * pageable.getPageSize(), pageable.getPageSize()), values.toArray(), mapper);
            totalCount = getTotalCount(sql, values.toArray(), mapper);
        } else {
            result = getJdbcTemplate().query(DbUtil.getPageSql(sql, (pageable.getPageNo() - 1) * pageable.getPageSize(), pageable.getPageSize()), mapper);
            totalCount = getTotalCount(sql, null, mapper);
        }

        Pager<T> pager =new Pager<T>(result,totalCount,pageable);
        return pager;
    }

    private Integer getTotalCount(String sql, Object[] args, RowMapper<T> mapper) {
        if (args != null) {
            return getJdbcTemplate().query(sql, args, mapper).size();
        } else {
            return getJdbcTemplate().query(sql, mapper).size();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Object> getQuerySqlWithValues(T entity) throws Exception {
        List<Object> list = new ArrayList<Object>();
        Assert.notNull(entity.getClass().getSuperclass());
        Class<?> superClass = entity.getClass().getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        Field[] fields = entity.getClass().getDeclaredFields();
        fields = (Field[]) ArrayUtils.addAll(superFields, fields);
        StringBuilder columnNames = new StringBuilder();

        Map<String, Object> sqlWhereMap = new HashMap<String, Object>();
        for (Field field : fields) {
            logger.info("entity field name :" + field.getName());
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entity.getClass());
            Method getMethod = pd.getReadMethod();
            if (getMethod.isAnnotationPresent(Id.class)) {
                String columnName = field.getName();
                columnNames.append(columnName).append(",");
                if (null != getMethod.invoke(entity)) {
                    sqlWhereMap.put(columnName, getMethod.invoke(entity));
                }
            } else if (getMethod.isAnnotationPresent(Column.class)) {
                String columnName = getMethod.getAnnotation(Column.class).name();
                columnNames.append(columnName).append(",");
                if (null != getMethod.invoke(entity)) {
                    sqlWhereMap.put(columnName, getMethod.invoke(entity));
                }
            }
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        String sql = "select " + columnNames.toString() + " from " + tableName;


        List<Object> values = null;
        if (sqlWhereMap != null) {
            List<Object> sqlWhereWithValues = getSqlWhereWithValues(sqlWhereMap);
            if (sqlWhereWithValues != null) {
                //拼接SQL条件
                String sqlWhere = (String) sqlWhereWithValues.get(0);
                sql += sqlWhere;
                //得到SQL条件中占位符的值
                values = (List<Object>) sqlWhereWithValues.get(1);
            }
        }

        logger.info("sql:" + sql);
        list.add(sql);
        list.add(values);
        return list;
    }

    /**
     * 根据条件，返回sql条件和条件中占位符的值
     *
     * @param sqlWhereMap key：字段名 value：字段值
     * @return 第一个元素为SQL条件，第二个元素为SQL条件中占位符的值
     */
    private List<Object> getSqlWhereWithValues(Map<String, Object> sqlWhereMap) {
        if (sqlWhereMap.size() < 1) return null;
        List<Object> list = new ArrayList<Object>();
        List<Object> fieldValues = new ArrayList<Object>();
        StringBuffer sqlWhere = new StringBuffer(" where ");
        Set<Entry<String, Object>> entrySets = sqlWhereMap.entrySet();
        for (Iterator<Entry<String, Object>> iteraotr = entrySets.iterator(); iteraotr.hasNext(); ) {
            Entry<String, Object> entrySet = iteraotr.next();
            fieldValues.add(entrySet.getValue());
            Object value = entrySet.getValue();
            if (value.getClass() == String.class) {
                sqlWhere.append(entrySet.getKey()).append(" like ").append("?").append(" and ");
            } else {
                sqlWhere.append(entrySet.getKey()).append("=").append("?").append(" and ");
            }
        }
        sqlWhere.delete(sqlWhere.lastIndexOf("and"), sqlWhere.length());
        list.add(sqlWhere.toString());
        list.add(fieldValues);
        return list;
    }
}
