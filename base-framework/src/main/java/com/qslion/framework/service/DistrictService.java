package com.qslion.framework.service;

import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.entity.CommonDistrict;
import java.util.List;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/10/30 22:45.
 */
public interface DistrictService extends IGenericService<CommonDistrict, Long> {

    /**
     * 根据层级获取区划树形
     */
    List<TreeNode> getDistrctTreeByUpid(String upid);
}
