/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.framework.service.impl;

import com.google.common.collect.Lists;
import com.qslion.framework.bean.Filter;
import com.qslion.framework.bean.Order;
import com.qslion.framework.bean.Page;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.dao.IGenericRepository;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.service.IGenericService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/13 10:56.
 */
@Transactional(value = "jpaTransactionManager")
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

    protected PageRequest getPageRequest(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        if (StringUtils.isNotEmpty(pageable.getOrderProperty())) {
            pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(),
                new Sort(Direction.valueOf(pageable.getOrderDirection().name().toUpperCase()), pageable.getOrderProperty()));
        }
        return pageRequest;
    }

    protected Specification<T> getSpecification(Pageable pageable) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            pageable.getFilters().stream().forEach(filter -> {
                switch (filter.getOperator()) {
                    case eq:
                        predicates.add(criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
                        break;
                    case ne:
                        break;
                    case gt:
                        predicates.add(criteriaBuilder.gt(root.get(filter.getProperty()), (Number) filter.getValue()));
                        break;
                    case lt:
                        predicates.add(criteriaBuilder.lt(root.get(filter.getProperty()), (Number) filter.getValue()));
                        break;
                    case ge:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getProperty()), (Date) filter.getValue()));
                        break;
                    case le:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(filter.getProperty()), (Date) filter.getValue()));
                        break;
                    case like:
                        predicates.add(criteriaBuilder.like(root.get(filter.getProperty()), String.format("%%s%", filter.getValue())));
                        break;
                    case in:
                        predicates.add(root.get(filter.getProperty()).in(filter.getValue()));
                        break;
                    case isNull:
                        break;
                    case isNotNull:
                        break;
                    case between:
                        //criteriaBuilder.between(root.<Date>get(filter.getProperty()),"");
                        break;
                    default:
                        break;
                }

            });
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]))
                .getRestriction();
        };
    }


    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public T find(ID id) {
        return genericRepository.findById(id).orElse(null);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<T> findList(ID... ids) {
        return genericRepository.findAllById(Lists.newArrayList(ids));
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<T> findList(Integer count, List<Filter> filters, List<Order> orders) {
        return null;
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        return null;
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public Page<T> findPage(Pageable pageable) {
        PageRequest pageRequest = getPageRequest(pageable);
        org.springframework.data.domain.Page<T> page = genericRepository.findAll(
            getSpecification(pageable), pageRequest);

        List<T> result = Lists.newArrayList();
        page.getContent().stream().forEach(t -> result.add(t));
        return new Page<>(result, page.getTotalElements(), pageable);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public long count() {
        return genericRepository.count();
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public long count(Filter... filters) {
        return genericRepository.count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return null;
            }
        });
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public boolean exists(ID id) {
        return genericRepository.existsById(id);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public boolean exists(Filter... filters) {
        return false;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public T update(T entity) {
        return genericRepository.saveAndFlush(entity);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public T update(T entity, String... ignoreProperties) {
        return null;
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void delete(ID id) {
        genericRepository.deleteById(id);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void delete(List<T> entities) {
        genericRepository.deleteAll(entities);
    }

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void delete(T entity) {
        genericRepository.delete(entity);
    }
}
