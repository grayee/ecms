package com.qslion.moudles.district.service;

import com.qslion.framework.bean.TreeNode;
import com.qslion.framework.service.IGenericService;
import com.qslion.moudles.district.entity.CommonDistrict;
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
