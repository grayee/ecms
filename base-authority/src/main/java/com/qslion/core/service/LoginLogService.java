/**
 *
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuLoginLog;
import com.qslion.core.vo.LoginSessionVo;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**
 * 登陆日志Service
 *
 * @author Gray.Z
 * @date 2018/9/18.
 */
public interface LoginLogService extends IGenericService<AuLoginLog, Long> {

    AuLoginLog find(String paramString);

    int getRecordCount(String paramString, LoginSessionVo paramLoginSessionVo);

    List queryByCondition();

    List queryByCondition(String paramString);

    List queryByCondition(String paramString1, String paramString2);

    List queryByCondition(int paramInt1, int paramInt2, String paramString, LoginSessionVo paramLoginSessionVo);

    List queryByCondition(int paramInt1, int paramInt2, String paramString1, String paramString2,
        LoginSessionVo paramLoginSessionVo);

    int doUpdate(String paramString);

}
