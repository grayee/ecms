package com.qslion.moudles.job.controller;

import com.qslion.framework.bean.ResponseResult;
import com.qslion.framework.controller.BaseController;
import com.qslion.framework.util.QuartzUtils;
import com.qslion.moudles.job.entity.Schedule;
import com.qslion.moudles.job.service.JobService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作业控制类
 *
 * @author Gray.Z
 * @date 2018/5/5 15:30.
 */
@ResponseResult
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    @Autowired
    private Scheduler scheduler;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @PostMapping("/add")
    public boolean save(Schedule quartz) {
        logger.info("新增任务");
        try {
            //如果是修改  展示旧的 任务
            if (quartz.getOldJobGroup() != null) {
                QuartzUtils
                    .removeJob(scheduler, quartz.getOldJobName(), quartz.getOldJobGroup(), quartz.getTriggerName(), quartz.getTriggerGroupName());
            }
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            QuartzUtils.addJob(scheduler, quartz.getOldJobName(), quartz.getOldJobGroup(), quartz.getTriggerName(), quartz.getTriggerGroupName(), cls,
                quartz.getCronExpression());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @PostMapping("/list")
    public List<Schedule> list(Schedule quartz, Integer pageNo, Integer pageSize) {
        logger.info("任务列表");
        List<Schedule> list = jobService.listEntity(quartz, pageNo, pageSize);
        return list;
    }

    @PostMapping("/trigger")
    public boolean trigger(Schedule quartz, HttpServletResponse response) {
        logger.info("触发任务");
        try {
            QuartzUtils.triggerJob(scheduler, quartz.getJobName(), quartz.getJobGroup());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @PostMapping("/pause")
    public boolean pause(Schedule quartz, HttpServletResponse response) {
        logger.info("停止任务");
        try {
            QuartzUtils.pauseJob(scheduler, quartz.getJobName(), quartz.getJobGroup());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @PostMapping("/resume")
    public boolean resume(Schedule quartz, HttpServletResponse response) {
        logger.info("恢复任务");
        try {
            QuartzUtils.resumeJob(scheduler, quartz.getJobName(), quartz.getJobGroup());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @PostMapping("/remove")
    public boolean remove(Schedule quartz, HttpServletResponse response) {
        logger.info("移除任务");
        try {
            QuartzUtils.removeJob(scheduler, quartz.getJobName(), quartz.getJobGroup(), quartz.getTriggerName(), quartz.getTriggerGroupName());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
