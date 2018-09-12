package ${serviceImplPackageName};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ${entityPackageName}.${entityClassName};
import ${servicePackageName}.${entityClassName}Service;

import com.qslion.framework.service.impl.GenericHibernateServiceImpl;

/**
* @descrip: ${descript}
* @copyright: ${copyright}
* @author: ${author}
* @link: ${link}
* @create_date: ${createDate?string("yyyy-MM-dd HH:mm:ss")}
* @update_date: ${modifyDate?string("yyyy-MM-dd HH:mm:ss")}
*
*/

@Service
public class ${entityClassName}ServiceImpl extends GenericHibernateServiceImpl<${entityClassName}, ${idType}> implements ${entityClassName}Service {

@Resource
private ${entityClassName}Dao ${entityClassName?uncap_first}Dao;
@Resource
public void setBaseDao(${entityClassName}Dao ${entityClassName?uncap_first}Dao) {
super.setBaseDao(${entityClassName?uncap_first}Dao);
}


public String ${entityClassName?uncap_first}Service() {
// TODO Auto-generated method stub
System.out.println(this.getBaseDao().toString()+"test Service........");
return null;
}

}
