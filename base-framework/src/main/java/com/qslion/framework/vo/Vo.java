package com.qslion.framework.vo;

import com.qslion.framework.bean.Pager;
import org.springframework.stereotype.Component;


@Component
public abstract class Vo<E> {

    private E entity;
    private Pager<E> pager;

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public Pager<E> getPager() {
        return pager;
    }

    public void setPager(Pager<E> pager) {
        this.pager = pager;
    }
}
