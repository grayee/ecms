package com.qslion.moudles.codegen.datamodel.impl;

import com.google.common.collect.ImmutableSet;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.moudles.codegen.datamodel.DataModel;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 22:00.
 */
public class EntityDataModel extends DataModel {

    @Override
    public Set<String> getImports() {
        imports.addAll(ImmutableSet.<String>builder()
            .add(Entity.class.getName())
            .add(Table.class.getName())
            .add(Column.class.getName())
            .add(BaseEntity.class.getName())
            .build());
        return imports;
    }

    @Override
    public Set<String> getImplements() {
        return implementList;
    }

    @Override
    public String getExtends() {
        return BaseEntity.class.getName();
    }

}
