/**
 * 
 */
package com.qslion.core.service;

import com.qslion.core.entity.AuLoginLog;
import com.qslion.core.vo.LoginSessionVo;
import com.qslion.framework.service.IGenericService;
import java.util.List;

/**   
 *    
 * 项目名称：authority   
 * 类名称：LoginLogService   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2011-8-8 下午04:43:48   
 * 修改人：Administrator   
 * 修改时间：2011-8-8 下午04:43:48   
 * 修改备注：   
 * @version    
 *    
 */

public interface LoginLogService extends IGenericService<AuLoginLog, Long> {
	
	  public abstract AuLoginLog find(String paramString);

	  public abstract int getRecordCount(String paramString, LoginSessionVo paramLoginSessionVo);

	  public abstract List queryByCondition();

	  public abstract List queryByCondition(String paramString);

	  public abstract List queryByCondition(String paramString1, String paramString2);

	  public abstract List queryByCondition(int paramInt1, int paramInt2, String paramString, LoginSessionVo paramLoginSessionVo);

	  public abstract List queryByCondition(int paramInt1, int paramInt2, String paramString1, String paramString2, LoginSessionVo paramLoginSessionVo);

	  public abstract int doUpdate(String paramString);

}
