package com.qslion.moudles.district.dao;

import com.qslion.framework.dao.IGenericRepository;
import com.qslion.moudles.district.entity.CommonDistrict;
import java.util.List;

/**
 * ecms 区域Service
 *
 * @author Gray.Z
 * @date 2018/10/30 22:43.
 */
public interface DistrictRepository extends IGenericRepository<CommonDistrict, Long> {

    /**
     * 根据上级ID查询区域
     *
     * @param upid 上级区域ID
     * @return 区域对象
     */
    List<CommonDistrict> findByUpid(Long upid);
}
