
package com.qslion.framework.service.impl;

import com.google.common.collect.Lists;
import com.qslion.framework.bean.Order;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.dao.IGenericRepository;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.service.IGenericService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/13 10:56.
 */
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class GenericServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements IGenericService<T, ID> {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 属性分隔符
     */
    private static final String ATTRIBUTE_SEPARATOR = ".";


    private IGenericRepository<T, ID> genericRepository;

    @Autowired
    protected void setGenericRepository(IGenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    private PageRequest getPageRequest(Pageable pageable) {
        Sort sort = Sort.by(new Sort.Order[0]);
        if (CollectionUtils.isNotEmpty(pageable.getOrders())) {
            List<Sort.Order> orders = pageable.getOrders().stream().map(order ->
                    new Sort.Order(Sort.Direction.fromString(order.getDirection().name()), order.getProperty()))
                    .collect(Collectors.toList());
            if (StringUtils.isNotEmpty(pageable.getOrderProperty())) {
                orders.add(new Sort.Order(Sort.Direction.valueOf(pageable.getOrderDirection().name().toUpperCase()),
                        pageable.getOrderProperty()));
            }
            sort = Sort.by(orders);
        } else {
            if (StringUtils.isNotEmpty(pageable.getOrderProperty())) {
                sort = new Sort(Direction.valueOf(pageable.getOrderDirection().name().toUpperCase()),
                        pageable.getOrderProperty());
            }
        }
        return PageRequest.of(pageable.getPageNo() - 1, pageable.getPageSize(), sort);
    }

    private Specification<T> getSpecification(List<QueryFilter> queryFilters) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            queryFilters.forEach(filter -> {
                Path path = root.get(filter.getProperty());
                switch (filter.getOperator()) {
                    case equal:
                        predicates.add(criteriaBuilder.equal(path, filter.getValue()));
                        break;
                    case notEqual:
                        predicates.add(criteriaBuilder.notEqual(path, filter.getValue()));
                        break;
                    case gt:
                        predicates.add(criteriaBuilder.gt(root.get(filter.getProperty()), (Number) filter.getValue()));
                        break;
                    case lt:
                        predicates.add(criteriaBuilder.lt(root.get(filter.getProperty()), (Number) filter.getValue()));
                        break;
                    case ge:
                        predicates.add(criteriaBuilder.le(root.get(filter.getProperty()), (Number) filter.getValue()));
                        break;
                    case le:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(filter.getProperty()), (Date) filter.getValue()));
                        break;
                    case like:
                        predicates.add(criteriaBuilder.like(root.get(filter.getProperty()), "%" + filter.getValue() + "%"));
                        break;
                    case notLike:
                        predicates.add(criteriaBuilder.notLike(root.get(filter.getProperty()), "%" + filter.getValue() + "%"));
                        break;
                    case in:
                        predicates.add(path.in(filter.getValue()));
                        break;
                    case isNull:
                        predicates.add(criteriaBuilder.isNull(root.get(filter.getProperty())));
                        break;
                    case isNotNull:
                        predicates.add(criteriaBuilder.isNotNull(root.get(filter.getProperty())));
                        break;
                    case between:
                        //criteriaBuilder.between(root.<Date>get(filter.getProperty()),"");
                        break;
                    case greaterThan:
                        predicates.add(criteriaBuilder.greaterThan(root.get(filter.getProperty()), (Comparable) filter.getValue()));
                        break;
                    case greaterThanOrEqualTo:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getProperty()), (Comparable) filter.getValue()));
                        break;
                    case lessThan:
                        predicates.add(criteriaBuilder.lessThan(root.get(filter.getProperty()), (Comparable) filter.getValue()));
                        break;
                    case lessThanOrEqualTo:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(filter.getProperty()), (Comparable) filter.getValue()));
                        break;
                    default:
                        break;
                }

            });

            return criteriaQuery.where(predicates.toArray(new Predicate[0]))
                    .getRestriction();
        };
    }


    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public T findById(ID id) {
        return genericRepository.findById(id).orElse(null);
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public List<T> findList(ID... ids) {
        return genericRepository.findAllById(Lists.newArrayList(ids));
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public List<T> findList(List<QueryFilter> queryFilters, List<Order> orders) {
        Pageable pageable = new Pageable(1, 100000);
        pageable.setQueryFilters(queryFilters);
        pageable.setOrders(orders);
        return findPage(pageable).getContent();
    }


    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public Pager<T> findPage(Pageable pageable) {
        PageRequest pageRequest = getPageRequest(pageable);
        List<QueryFilter> queryFilters = pageable.getQueryFilters();
        Page<T> page = genericRepository.findAll(getSpecification(queryFilters), pageRequest);

        List<T> result = Lists.newArrayList();
        result.addAll(page.getContent());
        return new Pager<>(result, page.getTotalElements(), pageable);
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public long count() {
        return genericRepository.count();
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public long count(QueryFilter... queryFilters) {
        return genericRepository.count(getSpecification(Lists.newArrayList(queryFilters)));
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public boolean exists(ID id) {
        return genericRepository.existsById(id);
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public boolean exists(QueryFilter... queryFilters) {
        return findList(Lists.newArrayList(queryFilters), null).size() > 0;
    }

    @Transactional(value = "transactionManager")
    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    @Transactional(value = "transactionManager")
    public T update(T entity) {
        return genericRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(value = "transactionManager")
    public T saveOrUpdate(T entity) {
        return genericRepository.saveAndFlush(entity);
    }

    @Transactional(value = "transactionManager")
    @Override
    public void delete(ID id) {
        genericRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    @Override
    public void delete(List<T> entities) {
        genericRepository.deleteAll(entities);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void delete(T entity) {
        genericRepository.delete(entity);
    }
}
