INSERT INTO dict_data_type (create_date, modify_date, version, code, description, name, is_system)
VALUES (now(), now(), 0, 'sex', '性别', '性别', 0);

INSERT INTO dict_data_type (create_date, modify_date, version, code, description, name, is_system)
VALUES (now(), now(), 0, 'isSystem', '是否系统内置', '是否系统内置', 0);


INSERT INTO dict_data_value (create_date, modify_date, version, code, description, name, order_no, value, type_id, enable_status)
VALUES (now(), now(), 2, 'male', '男', '男', 1, '0', 2, 1);

INSERT INTO dict_data_value (create_date, modify_date, version, code, description, name, order_no, value, type_id, enable_status)
VALUES (now(), now(), 0, 'female', ' 女', '女', 2, '1', 2, 1);

INSERT INTO dict_data_value (create_date, modify_date, version, code, description, name, order_no, value, type_id, enable_status)
VALUES (now(), now(), 0, 'yes', '是', '是', 1, 'true', 3, 1);

INSERT INTO dict_data_value (create_date, modify_date, version, code, description, name, order_no, value, type_id, enable_status)
VALUES (now(), now(), 0, 'no', '否', '否', 2, 'false', 3, 1);