package com.qslion.authority.custom.service.impl;


import com.qslion.authority.core.dao.PartyRelationRepository;
import com.qslion.authority.core.enums.AuPartyRelationType;
import com.qslion.authority.core.service.PartyRelationService;
import com.qslion.authority.custom.dao.AuEmployeeRepository;
import com.qslion.authority.custom.entity.AuEmployee;
import com.qslion.authority.custom.service.AuEmployeeService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工Service类
 *
 * @author Gray.Z
 * @date 2018/4/30 13:56.
 */
@Service("employeeService")
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class AuEmployeeServiceImpl extends GenericServiceImpl<AuEmployee, Long> implements AuEmployeeService {
    @Autowired
    private AuEmployeeRepository employeeRepository;

    @Autowired
    private PartyRelationService partyRelationService;

    @Autowired
    private PartyRelationRepository partyRelationRepository;


    /**
     * 添加新记录，同时添加团体、团体关系（如果parentRelId为空则不添加团体关系）
     *
     * @param employee 用于添加的VO对象
     * @return 若添加成功，则返回新添加记录的主键
     */
    public AuEmployee insert(AuEmployee employee) {
        //如果用户不手工编号，则系统自动编号
        if (StringUtils.isEmpty(employee.getEmployeeNo())) {
            employee.setEmployeeNo(String.valueOf(RandomUtils.nextInt(1000, 9999)));
        }
        AuEmployee auEmployee = employeeRepository.save(employee);
        //添加团体关系
        partyRelationService.addPartyRelation(auEmployee, AuPartyRelationType.ADMINISTRATIVE);
        return auEmployee;
    }

    /**
     * 如果是一人多岗或人员既属于部门又属于岗位，只删除当前关系 如果是一人一岗，把该人员挂到部门下面 如果是人员只属于部门，彻底删除该人员
     *
     * @param ids ids
     * @return
     */
    @Override
    public boolean remove(List<Long> ids) {
        ids.forEach(employeeId -> {
            AuEmployee employee = employeeRepository.findById(employeeId).orElse(null);
            if (employee != null) {
                employeeRepository.delete(employee);
                partyRelationService.removePartyRelation(employee);
            }
        });
        return true;
    }


    /**
     * 更新单条记录，同时调用接口更新相应的团体、团体关系记录
     *
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public AuEmployee update(AuEmployee vo) {

        //vo.setAuParty(party);
       /* employeeDao.clear();
        boolean flag = employeeDao.update(vo);
        AuPartyRelation relation = partyRelationDao.findByPartyId(vo.getId(), GlobalConstants.getRelTypeComp());
        relation.setName(vo.getPersonName());
        partyRelationDao.update(relation);*/
        //RmLogHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        return null;
    }
}
