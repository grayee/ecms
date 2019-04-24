-- 内置管理员 --
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30, '1990-01-01', 'admin@company.com', '1', 'admin', '1.1.1.1', '13800138000', 'admin', 'admin', 'admin',0);
-- 内置普通用户（测试）--
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30, '1990-01-01', 'test@company.com', '1', 'test', '1.1.1.1', '13800138000', 'test', 'test', 'test',0);

-- 内置角色类型---
INSERT INTO au_role_type (id, create_date, modify_date, description, enable_status, name,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '管理类角色', '1', '管理类角色',0);

INSERT INTO au_role_type (id, create_date, modify_date, description, enable_status, name,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '普通类角色', '1', '普通类角色',0);

INSERT INTO au_role_type (id, create_date, modify_date, description, enable_status, name,version)
VALUES (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '其他类角色', '1', '其他类角色',0);

-- 内置角色---
INSERT INTO au_role (id, create_date, modify_date, description, enable_status, name, value,type_id,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '超级管理员', '1', '超级管理员', 'ROLE_ADMIN',1,0);

INSERT INTO au_role (id, create_date, modify_date, description, enable_status, name, value,type_id,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '普通用户', '1', '普通用户', 'ROLE_USER',2,0);

-- 内置用户组--
INSERT INTO au_usergroup (id, create_date, modify_date, code, description, enable_status, name,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1', '管理员组', '1', '管理员组',0);

INSERT INTO au_usergroup (id, create_date, modify_date, code, description, enable_status, name,,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2', '普通用户组', '1', '普通用户组',0);

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

-- 资源 --
INSERT INTO au_resource (id, create_date, modify_date, version, description, enable_status, name, parent_id, type, value)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'home', 1, '', 0, 1, '/home');
-- 权限 --
INSERT INTO au_permission (id, create_date, modify_date, version, description, enable_status, name, type, value)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '管理权限', '1', 'manager', '1', 'manager');
-- 资源权限关系 --
INSERT INTO au_permission_resource (permission_id, resource_id)VALUES (1, 1);
-- 角色权限关系 --
INSERT INTO au_role_permission (role_id, permission_id)VALUES (1, 1);

-- OAUTH2 --
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('client_id_1234567890', 'resource_ids', 'client_secret_1234567890', 'select', 'password,refresh_token', 'http://localhost:8081/ecms/login', NULL, 0, 0, '{"country":"CN","country_code":"086"}', 'false');

INSERT INTO clientdetails (appId, resourceIds, appSecret, scope, grantTypes, redirectUrl, authorities, access_token_validity, refresh_token_validity, additionalInformation, autoApproveScopes)
VALUES ('appid-1234567890', 'resourceid', 'appsecret-1234567890', null, null, '', '', 10000, 10000, '', null)

-- au_menu --
INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (1, '导航菜单',1,NULL,'菜单备注信息',1,NULL,1,false,1,'fa-navigation',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (2, '组织管理',1,NULL,'组织管理备注信息',2,1,1,false,1,'fa-org',2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (3, '组织机构管理',1,'/org','组织机构管理备注信息',1,2,1,true,1,'fa-org',3,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (4, '公司档案管理',1,'/org/company','公司档案备注信息',2,2,1,true,1,'fa-org',4,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (5, '部门档案管理',1,'/org/department','部门档案备注信息',3,2,1,true,1,'fa-org',5,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (6, '岗位信息管理',1,'/org/position','岗位信息备注信息',4,2,1,true,1,'fa-org',6,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (7, '人员档案管理',1,'/org/employee','人员档案备注信息',5,2,1,true,1,'fa-org',7,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (8, '权限管理',1,NULL,'权限管理备注信息',3,1,1,false,1,'fa-o',8,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (9, '用户管理',1,'/auth/user','用户管理备注信息',3,8,1,true,1,'fa-o',9,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (10, '角色管理',1,'/auth/role','角色管理备注信息',3,8,1,true,1,'fa-o',10,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (11, '菜单管理',1,'/auth/menu','菜单管理备注信息',3,8,1,true,1,'fa-o',11,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (12, '授权管理',1,NULL,'授权管理备注信息',3,8,1,false,1,'fa-o',12,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (13, '用户授权',1,'/auth/grant/user','用户授权备注信息',3,12,1,true,1,'fa-o',13,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (14, '角色授权',1,'/auth/grant/role','角色授权备注信息',3,12,1,true,1,'fa-o',14,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (15, '机构授权',1,'/auth/grant/org','机构授权备注信息',3,12,1,true,1,'fa-o',15,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (16, '资源管理',1,'/auth/resource','资源管理备注信息',3,8,1,true,1,'fa-o',16,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (17, '系统管理',1,NULL,'系统管理备注信息',4,1,1,false,1,'fa-o',17,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (18, '字典管理',1,'/sys/dict','字典管理备注信息',4,17,1,true,1,'fa-o',18,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

INSERT INTO au_menu (id, name,type,url,remark,order_no,parent_id, status, is_leaf, level,icon, resource_id,create_date, modify_date, version, enable_status)
VALUES (19, '登陆日志管理',1,'/sys/login_log','登陆日志管理备注信息',4,17,1,true,1,'fa-o',19,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1);

-- au_resource --

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (1, '导航菜单',NULL, '导航菜单描述信息', 1, NULL, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (2, '组织管理',NULL, '组织管理描述信息', 1, 1, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (3, '组织机构管理','/org', '组织机构管理描述信息', 1, 2, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (4, '公司档案管理','/org/company', '公司档案管理描述信息', 1, 2, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (5, '部门档案管理','/org/department', '部门档案管理描述信息', 1, 2, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (6, '岗位信息管理','/org/position', '岗位信息描述信息', 1,2, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (7, '人员档案管理','/org/employee', '人员档案管理描述信息', 1, 2, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (8, '权限管理',NULL, '权限管理描述信息', 1, 1, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (9, '用户管理','/auth/user', '用户管理描述信息', 1, 8, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (10, '角色管理','/auth/role', '角色管理描述信息', 1, 8, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (11, '菜单管理','/auth/menu', '菜单管理描述信息', 1, 8, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (12, '授权管理','/auth/grant', '授权管理描述信息', 1, 8, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (13, '用户授权','/auth/grant/user', '用户授权描述信息', 1, 12, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (14, '角色授权','/auth/grant/role', '角色授权描述信息', 1, 12, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (15, '机构授权','/auth/grant/org', '机构授权描述信息', 1, 12, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (16, '资源管理','/auth/resource', '资源管理描述信息', 1, 8, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (17, '系统管理',NULL, '系统管理描述信息', 1, 1, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (18, '字典管理','/sys/ddic', '字典管理描述信息', 1, 17, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);

INSERT INTO au_resource (id, name,value,description, type,parent_id,enable_status,create_date, modify_date, version)
VALUES (19, '登陆日志管理','/sys/login_log', '登陆日志管理描述信息', 1, 17, 1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1);