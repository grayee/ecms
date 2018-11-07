
package com.qslion.moudles.job.service;

import com.qslion.moudles.job.entity.Schedule;
import java.util.List;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/5/5 14:48.
 */
public interface JobService {

    List<Schedule> listEntity(Schedule quartz, Integer pageNo, Integer pageSize);

    Long listEntity(Schedule quartz);

}
