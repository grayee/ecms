
package com.qslion.moudles.job.service.impl;

import com.qslion.moudles.job.entity.Schedule;
import com.qslion.moudles.job.service.JobService;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/5/5 14:49.
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory emf;

    private Query createNativeQuery(String sql, Object... params) {
        Query q = emf.createEntityManager().createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                // 与Hiberante不同,jpaquery从位置1开始
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }

    @SuppressWarnings({"unchecked"})
    public <T> List<T> nativeQueryList(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        //@todo develop a new approach to result transformers
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.TO_LIST);
        return q.getResultList();
    }

    @SuppressWarnings({"unchecked"})
    public <T> List<T> nativeQueryListModel(Class<T> resultClass,
        String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        //@todo develop a new approach to result transformers
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }

    @SuppressWarnings({"unchecked"})
    public <T> List<T> nativeQueryListMap(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        //@todo develop a new approach to result transformers
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    public Long nativeQueryCount(String nativeSql, Object... params) {
        Object count = createNativeQuery(nativeSql, params).getSingleResult();
        return ((Number) count).longValue();
    }

    @Override
    public List<Schedule> listEntity(Schedule quartz,
        Integer pageNo, Integer pageSize) {
        StringBuffer nativeSql = new StringBuffer();
        nativeSql
            .append("SELECT job.JOB_NAME as jobName,job.JOB_GROUP as jobGroup,job.DESCRIPTION as description,job.JOB_CLASS_NAME as jobClassName,");
        nativeSql.append("cron.CRON_EXPRESSION as cronExpression,tri.TRIGGER_NAME as triggerName,tri.TRIGGER_STATE as triggerState,");
        nativeSql.append("job.JOB_NAME as oldJobName,job.JOB_GROUP as oldJobGroup ");
        nativeSql.append("FROM qrtz_job_details AS job LEFT JOIN qrtz_triggers AS tri ON job.JOB_NAME = tri.JOB_NAME ");
        nativeSql.append("LEFT JOIN qrtz_cron_triggers AS cron ON cron.TRIGGER_NAME = tri.TRIGGER_NAME ");
        nativeSql.append("WHERE tri.TRIGGER_TYPE = 'CRON'");
        Object[] params = new Object[]{};
        if (StringUtils.isNotEmpty(quartz.getJobName())) {//加入JobName搜索其他条件自行实现
            nativeSql.append(" AND job.JOB_NAME = ?");
            params = new Object[]{quartz.getJobName()};
        }
        return nativeQueryListModel(Schedule.class, nativeSql.toString(), params);
    }

    @Override
    public Long listEntity(Schedule quartz) {
        StringBuffer nativeSql = new StringBuffer();
        nativeSql.append("SELECT COUNT(*)");
        nativeSql.append("FROM qrtz_job_details AS job LEFT JOIN qrtz_triggers AS tri ON job.JOB_NAME = tri.JOB_NAME ");
        nativeSql.append("LEFT JOIN qrtz_cron_triggers AS cron ON cron.TRIGGER_NAME = tri.TRIGGER_NAME ");
        nativeSql.append("WHERE tri.TRIGGER_TYPE = 'CRON'");
        return nativeQueryCount(nativeSql.toString(), new Object[]{});
    }
}
