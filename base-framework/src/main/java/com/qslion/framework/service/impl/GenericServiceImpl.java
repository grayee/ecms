
package com.qslion.framework.service.impl;

import com.google.common.collect.Lists;
import com.qslion.framework.bean.Order;
import com.qslion.framework.bean.Pageable;
import com.qslion.framework.bean.Pager;
import com.qslion.framework.bean.QueryFilter;
import com.qslion.framework.dao.IGenericRepository;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.service.IGenericService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    protected PageRequest getPageRequest(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNo() - 1, pageable.getPageSize());
        if (StringUtils.isNotEmpty(pageable.getOrderProperty())) {
            pageRequest = PageRequest.of(pageable.getPageNo() - 1, pageable.getPageSize(),
                new Sort(Direction.valueOf(pageable.getOrderDirection().name().toUpperCase()),
                    pageable.getOrderProperty()));
        }
        return pageRequest;
    }

    protected Specification<T> getSpecification(Pageable pageable) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            pageable.getQueryFilters().stream().forEach(filter -> {
                Path path = root.get(filter.getProperty());
                switch (filter.getOperator()) {
                    case equal:
                        predicates.add(criteriaBuilder.equal(path, filter.getValue()));
                        break;
                    case notEqual:
                        predicates.add(criteriaBuilder.notEqual(path, filter.getValue()));
                        break;
                    case gt:
                        predicates.add(criteriaBuilder.gt(path, (Number) filter.getValue()));
                        break;
                    case lt:
                        predicates.add(criteriaBuilder.lt(path, (Number) filter.getValue()));
                        break;
                    case ge:
                        predicates.add(criteriaBuilder.le(path, (Number) filter.getValue()));
                        break;
                    case le:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(path, (Date) filter.getValue()));
                        break;
                    case like:
                        predicates.add(criteriaBuilder.like(path, String.format("%%s%", filter.getValue())));
                        break;
                    case notLike:
                        predicates.add(criteriaBuilder.notLike(path, "%" + filter.getValue() + "%"));
                        break;
                    case in:
                        predicates.add(path.in(filter.getValue()));
                        break;
                    case isNull:
                        predicates.add(criteriaBuilder.isNull(path));
                        break;
                    case isNotNull:
                        predicates.add(criteriaBuilder.isNotNull(path));
                        break;
                    case between:
                        //criteriaBuilder.between(root.<Date>get(filter.getProperty()),"");
                        break;
                    case greaterThan:
                        predicates.add(criteriaBuilder.greaterThan(path, (Comparable) filter.getValue()));
                        break;
                    case greaterThanOrEqualTo:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, (Comparable) filter.getValue()));
                        break;
                    case lessThan:
                        predicates.add(criteriaBuilder.lessThan(path, (Comparable) filter.getValue()));
                        break;
                    case lessThanOrEqualTo:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(path, (Comparable) filter.getValue()));
                        break;
                    default:
                        break;
                }

            });

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]))
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
    public List<T> findList(Integer count, List<QueryFilter> queryFilters, List<Order> orders) {
        return null;
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public List<T> findList(Integer first, Integer count, List<QueryFilter> queryFilters, List<Order> orders) {
        return null;
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public Pager<T> findPage(Pageable pageable) {
        PageRequest pageRequest = getPageRequest(pageable);
        Page<T> page = genericRepository.findAll(getSpecification(pageable), pageRequest);

        List<T> result = Lists.newArrayList();
        page.getContent().stream().forEach(t -> result.add(t));
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
        return genericRepository.count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery,
                CriteriaBuilder criteriaBuilder) {

                return null;
            }
        });
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public boolean exists(ID id) {
        return genericRepository.existsById(id);
    }

    @Transactional(value = "transactionManager", readOnly = true)
    @Override
    public boolean exists(QueryFilter... queryFilters) {
        return false;
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
