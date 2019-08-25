package com.qslion.custom.service.impl;


import com.qslion.core.dao.PartyRelationRepository;
import com.qslion.core.entity.AuPartyRelation;
import com.qslion.core.enums.AuPartyRelationType;
import com.qslion.core.service.PartyRelationService;
import com.qslion.custom.dao.AuDepartmentRepository;
import com.qslion.custom.entity.AuDepartment;
import com.qslion.custom.service.AuDepartmentService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import com.qslion.framework.util.CopyUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 部门Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("departmentService")
public class AuDepartmentServiceImpl extends GenericServiceImpl<AuDepartment, Long> implements AuDepartmentService {

    @Autowired
    private AuDepartmentRepository departmentRepository;

    @Autowired
    private PartyRelationService partyRelationService;

    @Autowired
    private PartyRelationRepository partyRelationRepository;

    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param department 用于添加的VO对象
     * @return 若添加成功，则返回新添加记录
     */
    @Override
    public AuDepartment insert(AuDepartment department) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(department.getDeptNo())) {
            department.setDeptNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        //添加团体关系
        partyRelationService.addPartyRelation(department, AuPartyRelationType.ADMINISTRATIVE);
        return departmentRepository.save(department);
    }

    /**
     * 删除多条记录，删除自身并同时删除相应的团体、团体关系、帐户、权限等记录
     *
     * @param ids 用于删除的记录的id
     * @return 成功删除的记录数
     */
    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(departmentId -> {
            AuDepartment department = departmentRepository.findById(departmentId).orElse(null);
            if (department != null) {
                departmentRepository.delete(department);
                partyRelationService.removePartyRelation(department);
            }
        });
        return true;
    }

    /**
     * 更新单条记录，同时调用接口更新相应的团体、团体关系记录
     *
     * @param department 用于更新的VO对象
     * @return 成功更新的记录数
     */
    @Override
    public AuDepartment update(AuDepartment department) {
        AuDepartment auDepartment = departmentRepository.findById(department.getId()).get();
        AuPartyRelation partyRelation = partyRelationRepository.findByPartyId(department.getId());
        partyRelation.setName(department.getDeptName());
        partyRelation.setRemark(department.getRemark());
        partyRelationRepository.saveAndFlush(partyRelation);
        CopyUtils.copyProperties(department, auDepartment);
        return departmentRepository.saveAndFlush(auDepartment);
    }
}
