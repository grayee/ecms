-- 内置管理员 --
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30, '1990-01-01', 'admin@company.com', '1', 'admin', '1.1.1.1', '13800138000', 'admin', 'admin', 'admin',0);
-- 内置普通用户（测试）--
INSERT INTO au_user (id, create_date, modify_date, age, birthday, email, enable_status, login_id, login_ip, mobile, nickname, password, username,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 30, '1990-01-01', 'test@company.com', '1', 'test', '1.1.1.1', '13800138000', 'test', 'test', 'test',0);

-- 内置角色---
INSERT INTO au_role (id, create_date, modify_date, description, enable_status, name, value,version)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '超级管理员', '1', '超级管理员', 'ROLE_ADMIN',0);

INSERT INTO au_role (id, create_date, modify_date, description, enable_status, name, value,version)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '普通用户', '1', '普通用户', 'ROLE_USER',0);

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


