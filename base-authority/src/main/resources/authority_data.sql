-- 内置管理员 --
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,avatar,login_failure_count,version)
VALUES (1, now(), now(), 30, '1990-01-01', 'admin@company.com', '1', 'admin', '1.1.1.1', '13800138000', 'admin', '$2a$10$6/Kqyc16VJHvQQJAAfZQeOnGX1dISWqQfTgVHAM00aOqRjHgbl2Oa', 'admin','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,0);
-- 内置普通用户（测试）--
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,avatar,login_failure_count,version)
VALUES (2, now(), now(), 30, '1990-01-01', 'test@company.com', '1', 'test', '1.1.1.1', '13800138000', 'test', '$2a$10$8zPhZE9L0Nflca5su/WV0.LioSd3WLUKqpVQhGyi00n2NsJr48M52', 'test','',0,0);

-- 内置角色---
INSERT INTO au_role (id, create_date, modify_date, remark, enable_status, name, value,type_id,version)
VALUES (1, now(), now(), '超级管理员', '1', '超级管理员', 'ROLE_ADMIN',1,0);

INSERT INTO au_role (id, create_date, modify_date, remark, enable_status, name, value,type_id,version)
VALUES (2, now(), now(), '普通用户', '1', '普通用户', 'ROLE_USER',2,0);

-- 内置用户组--
INSERT INTO au_usergroup (id, create_date, modify_date, code, remark, enable_status, name,version)
VALUES (1, now(), now(), '1', '管理员组', '1', '管理员组',0);

INSERT INTO au_usergroup (id, create_date, modify_date, code, remark, enable_status, name,version)
VALUES (2, now(), now(), '2', '普通用户组', '1', '普通用户组',0);

-- 内置用户组角色--
INSERT INTO au_usergroup_role (usergroup_id, role_id) VALUES (1, 1);
INSERT INTO au_usergroup_role (usergroup_id, role_id) VALUES (2, 2);
-- 管理员角色 --
INSERT INTO au_user_role (user_id, role_id) VALUES (1, 1);
-- 普通用户角色 --
INSERT INTO au_user_role (user_id, role_id) VALUES (2, 2);
-- 管理员用户组 --
INSERT INTO au_usergroup_user (usergroup_id, user_id) VALUES (1, 1);
-- 普通用户组 --
INSERT INTO au_usergroup_user (usergroup_id, user_id) VALUES (2, 2);

-- 权限 --
INSERT INTO au_permission (id, create_date, modify_date, version, remark, enable_status, name, type, value,system)
VALUES (1, now(), now(), 1, '管理权限', '1', '系统默认管理权限', 0, 'common:manager',1);

-- 角色权限关系 --
INSERT INTO au_role_permission (role_id, permission_id)VALUES (1, 1);

-- OAUTH2 --
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('client_id_1234567890', '', '$2a$10$tGvVrgaz18nF7oVgN0H8puZ6No3yj.e4zjS2ieFTGiy9hKCGFHjli', 'select', 'password,refresh_token', 'http://localhost:8081/ecms/login', NULL, 0, 0, '{"country":"CN","country_code":"086"}', 'false');

INSERT INTO clientdetails (appId, resourceIds, appSecret, scope, grantTypes, redirectUrl, authorities, access_token_validity, refresh_token_validity, additionalInformation, autoApproveScopes)
VALUES ('appid-1234567890', '', 'appsecret-1234567890', null, null, '', '', 10000, 10000, '', null);

-- au_menu --
INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (1, '导航菜单',1,NULL,'菜单备注信息',1,NULL,1,false,1,'fa-navigation',1,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (2, '组织管理',0,NULL,'组织管理备注信息',2,1,1,false,1,'fa fa-archive',2,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (201, '组织机构管理',1,'/org/relation','组织机构管理备注信息',1,2,1,true,1,'fa-org',201,now(),now(),'Organization',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (202, '公司档案管理',1,'/org/company','公司档案备注信息',2,2,1,true,1,'fa-org',202,now(),now(),'Company',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (203, '部门档案管理',1,'/org/department','部门档案备注信息',3,2,1,true,1,'fa-org',203,now(),now(),'Department',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (204, '岗位信息管理',1,'/org/position','岗位信息备注信息',4,2,1,true,1,'fa-org',204,now(),now(),'Position',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (205, '人员档案管理',1,'/org/employee','人员档案备注信息',5,2,1,true,1,'fa-org',205,now(),now(),'Employee',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (206, '添加公司',2,'/org/company/add','添加公司',1,202,1,true,1,'fa-o',206,now(),now(),'CompanyAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (207, '添加部门',2,'/org/department/add','添加部门',1,203,1,true,1,'fa-o',207,now(),now(),'DepartmentAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (208, '添加岗位',2,'/org/position/add','添加岗位',1,204,1,true,1,'fa-o',208,now(),now(),'PositionAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (209, '添加员工',2,'/org/employee/add','添加员工',1,205,1,true,1,'fa-o',209,now(),now(),'EmployeeAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (3, '权限管理',0,NULL,'权限管理备注信息',3,1,1,false,1,'fa-o',3,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (301, '用户管理',1,'/auth/user','用户管理备注信息',1,3,1,true,1,'fa-o',301,now(),now(),'User',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (302, '角色管理',1,'/auth/role','角色管理备注信息',2,3,1,true,1,'fa-o',302,now(),now(),'Role',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (303, '菜单管理',1,'/auth/menu','菜单管理备注信息',3,3,1,true,1,'fa-o',303,now(),now(),'Menu',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (304, '资源管理',1,'/auth/resource','资源管理备注信息',4,3,1,true,1,'fa-o',304,now(),now(),'Resource',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (305, '用户新增',2,'/auth/user/add','用户新增备注信息',1,301,1,true,1,'fa-o',305,now(),now(),'UserAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (4, '系统管理',0,NULL,'系统管理备注信息',4,1,1,false,1,'fa-o',4,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (401, '字典管理',1,'/sys/dict','字典管理备注信息',1,4,1,true,1,'fa-o',401,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (402, '登陆日志管理',1,'/sys/login/log','登陆日志管理备注信息',2,4,1,true,1,'fa-o',402,now(),now(),'LoginLog',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (403, '添加字典值',2,'/sys/dict/add','添加字典值',1,401,1,true,1,'fa-o',403,now(),now(),'DictionaryAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (5, '基础数据设置',0,NULL,'基础数据设置备注信息',5,1,1,false,1,'fa-o',5,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (501, '账套设置',1,'/baseSetting/accountSet','账套设置备注信息',1,5,1,true,1,'fa-o',501,now(),now(),'AccountSet',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (502, '科目设置',1,'/baseSetting/accountSubject','科目设置备注信息',2,5,1,true,1,'fa-o',502,now(),now(),'AccountSubject',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (503, '期初余额设置',1,'/baseSetting/accountBalance','期初设置备注信息',3,5,1,true,1,'fa-o',503,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (504, '辅助核算设置',1,'/sys/dict','辅助核算设置备注信息',4,5,1,true,1,'fa-o',504,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (505, '币别设置',1,'/sys/dict','币别设置备注信息',5,5,1,true,1,'fa-o',505,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (506, '凭证字设置',1,'/sys/dict','凭证字设置备注信息',6,5,1,true,1,'fa-o',506,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (507, '新建账套',2,'/baseSetting/accountSet/add','新建账套',1,501,1,true,1,'fa-o',507,now(),now(),'AccountSetAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (6, '账务处理',0,NULL,'账务处理备注信息',6,1,1,false,1,'fa-o',6,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (601, '凭证管理',1,'/accounting/voucher','凭证管理备注信息',1,6,1,true,1,'fa-o',601,now(),now(),'AccountVoucher',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (602, '期末处理',1,'/sys/dict','期末处理备注信息',2,6,1,true,1,'fa-o',602,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (603, '账表输出',1,'/sys/dict','账表输出备注信息',3,6,1,true,1,'fa-o',603,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (604, '新增凭证',2,'/accounting/voucher/add','账表输出备注信息',3,601,1,true,1,'fa-o',604,now(),now(),'AccountVoucherAdd',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (7, '出纳管理',0,NULL,'出纳管理备注信息',7,1,1,false,1,'fa-o',7,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (701, '现金日记账',1,'/sys/dict','现金日记账备注信息',1,7,1,true,1,'fa-o',701,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (702, '银行日记账',1,'/sys/dict','银行日记账备注信息',2,7,1,true,1,'fa-o',702,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (703, '结账管理',1,'/sys/dict','结账管理备注信息',3,7,1,true,1,'fa-o',703,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (8, '报表管理',0,NULL,'账务处理备注信息',8,1,1,false,1,'fa-o',8,now(),now(),'',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (801, '资产负债表',1,'/sys/dict','资产负债表备注信息',1,8,1,true,1,'fa-o',801,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (802, '利润表',1,'/sys/dict','利润表备注信息',2,8,1,true,1,'fa-o',802,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (803, '利润分配表',1,'/sys/dict','利润分配表备注信息',3,8,1,true,1,'fa-o',803,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (804, '现金流量表',1,'/sys/dict','现金流量备注信息',4,8,1,true,1,'fa-o',804,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (805, '试算平衡表',1,'/sys/dict','现金流量备注信息',5,8,1,true,1,'fa-o',805,now(),now(),'Dictionary',1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_code,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, component,version, enable_status)
VALUES (806, '科目余额表',1,'/sys/dict','科目余额备注信息',6,8,1,true,1,'fa-o',806,now(),now(),'Dictionary',1,1);

-- au_resource --
INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (1, '资源根节点',NULL, '资源根节点', NULL, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (2, '组织管理',NULL, '组织管理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (201, '组织机构管理','/org', '组织机构管理描述信息', 2, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (202, '公司档案管理','/org/company', '公司档案管理描述信息', 2, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (203, '部门档案管理','/org/department', '部门档案管理描述信息', 2, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (204, '岗位信息管理','/org/position', '岗位信息描述信息',2, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (205, '人员档案管理','/org/employee', '人员档案管理描述信息', 2, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (206, '添加公司','/org/company/add', '添加公司', 202, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (207, '添加部门','/org/department/add', '添加部门', 203, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (208, '添加岗位','/org/position/add', '添加岗位', 204, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (209, '添加员工','/org/employee/add', '添加员工', 205, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (3, '权限管理',NULL, '权限管理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (301, '用户管理','/auth/user', '用户管理描述信息', 3, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (302, '角色管理','/auth/role', '角色管理描述信息', 3, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (303, '菜单管理','/auth/menu', '菜单管理描述信息', 3, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (304, '资源管理','/auth/resource', '资源管理描述信息', 3, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (305, '用户新增','/auth/user/add', '用户新增', 301, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (4, '系统管理',NULL, '系统管理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (401, '字典管理','/sys/dict', '字典管理描述信息', 4, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (402, '登陆日志管理','/sys/login_log', '登陆日志管理描述信息', 4, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (403, '添加字典值','/sys/dict/add', '字典值', 401, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (5, '基础数据设置',NULL, '基础数据设置描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (501, '账套设置','/baseSetting/accountSet', '账套设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (502, '科目设置','/baseSetting/accountSubject', '科目设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (503, '期初设置','/sys/ddic', '期初设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (504, '辅助核算设置','/sys/ddic', '辅助核算设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (505, '币别设置','/sys/ddic', '币别设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (506, '凭证字设置','/sys/ddic', '凭证字设置描述信息', 5, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (507, '新建账套','/baseSetting/accountSet/add', '新建账套', 501, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (6, '账务处理',NULL, '账务处理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (601, '凭证管理','/accounting/voucher', '凭证管理描述信息', 6, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (602, '期末处理','/sys/ddic', '期末处理描述信息', 6, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (603, '账表输出','/sys/ddic', '账表输出描述信息', 6, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (604, '新增凭证','/baseSetting/accountSet/add', '新增凭证', 601, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (7, '出纳管理',NULL, '出纳管理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (701, '现金日记账','/sys/ddic', '现金日记账描述信息', 7, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (702, '银行日记账','/sys/ddic', '银行日记账描述信息', 7, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (703, '结账管理','/sys/ddic', '结账管理描述信息', 7, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (8, '报表管理',NULL, '报表管理描述信息', 1, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (801, '资产负债表','/sys/ddic', '资产负债表描述信息', 8, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (802, '利润表','/sys/ddic', '利润表描述信息', 8, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (803, '利润分配表','/sys/ddic', '利润分配表描述信息', 8, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (804, '现金流量表','/sys/ddic', '现金流量表描述信息', 8, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (805, '试算平衡表','/sys/ddic', '试算平衡表描述信息', 8, 1,now(),now(),1);

INSERT INTO au_resource (id, name,value,remark,parent_id,enable_status,create_date, modify_date, version)
VALUES (806, '科目余额表','/sys/ddic', '科目余额表描述信息', 8, 1,now(),now(),1);

-- au_connection_rule --
INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (1, now(), now(), 0, 0, '行政关系规则-公司-公司', '行政关系-公司-公司', 0, 0);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (2, now(), now(), 0, 0, '行政关系规则-公司-部门', '行政关系-公司-部门', 0, 1);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (3, now(), now(), 0, 0, '行政关系规则-公司-岗位', '行政关系-公司-岗位', 0, 2);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (4, now(), now(), 0, 0, '行政关系规则-公司-员工', '行政关系-公司-员工', 0, 3);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (5, now(), now(), 0, 0, '行政关系规则-部门-部门', '行政关系-部门-部门', 1, 1);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (6, now(), now(), 0, 0, '行政关系规则-部门-岗位', '行政关系-部门-岗位', 1, 2);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (7, now(), now(), 0, 0, '行政关系规则-部门-员工', '行政关系-部门-员工', 1, 3);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (8, now(), now(), 0, 0, '行政关系规则-岗位-员工', '行政关系-岗位-员工', 2, 3);

INSERT INTO au_connection_rule (id, create_date, modify_date, version, relation_type, name, remark, cur_org_type, sub_org_type)
VALUES (9, now(), now(), 0, 1, '角色关系规则-角色-角色', '角色关系-角色-角色', 4, 4);

-- au_party_relation--
INSERT INTO au_org_relation (id,create_date, modify_date, version, parent_id, relation_type, is_leaf, `level`, name, order_code, remark,org_type,org_id)
VALUES (1,now(), now(), 0, NULL, 1, 0, 0, '角色管理', 0, '角色管理根节点', 4,0);

INSERT INTO au_org_relation (id,create_date, modify_date, version, parent_id, relation_type, is_leaf, `level`, name, order_code, remark,org_type, org_id)
VALUES (2,now(), now(), 0, 1, 1, 0, 0, '超级管理员', 1, '超级管理员', 4,1);

INSERT INTO au_org_relation (id,create_date, modify_date, version, parent_id, relation_type, is_leaf, `level`, name, order_code, remark,org_type, org_id)
VALUES (3,now(), now(), 0, 1, 1, 0, 0, '普通用户', 2, '普通用户', 4,2);

