package com.qslion.moudles.codegen.datamodel.impl;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
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

    private static final String ENTITY_NAME = "entity";

    @Override
    public Set<String> getImports() {
        Set<String> imports = Sets.newHashSet();
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
        return getImplementList();
    }

    @Override
    public String getExtends() {
        return BaseEntity.class.getName();
    }

    @Override
    public String getFtl() {
        return String.format("%s.%s", ENTITY_NAME, FILE_FTL_EXT);
    }

    @Override
    public String getPackagePath() {
        return String.format("%s.%s", basePackage, ENTITY_NAME);
    }

    @Override
    public String getClassName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
    }
}
