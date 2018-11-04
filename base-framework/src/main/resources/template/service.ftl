package ${servicePackageName};

import ${entityPackageName}.${entityClassName};
import com.qslion.framework.service.IGenericHibernateService;

/**
* @descrip: ${descript}
* @copyright: ${copyright}
* @author: ${author}
* @link: ${link}
* @create_date: ${createDate?string("yyyy-MM-dd HH:mm:ss")}
* @update_date: ${modifyDate?string("yyyy-MM-dd HH:mm:ss")}
*
*/

public interface ${entityClassName}Service extends IGenericHibernateService<${entityClassName}, ${idType}> {

public String ${entityClassName?uncap_first}Service();

}