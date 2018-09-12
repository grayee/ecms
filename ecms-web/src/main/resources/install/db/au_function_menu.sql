DROP TABLE IF EXISTS au_function_menu;

CREATE TABLE au_function_menu
(
  id          VARCHAR(32) NOT NULL,
  name        VARCHAR(50),
  url         VARCHAR(500),
  icon_class  VARCHAR(50),
  remark      VARCHAR(300),
  type        INT  NOT NULL,
  parent_id   VARCHAR(32),
  keyword     VARCHAR(50),
  hot_key     VARCHAR(50),
  code        VARCHAR(32),
  order_code  INT,
  tree_level  SMALLINT,
  is_leaf     BOOLEAN     NOT NULL,
  is_public   BOOLEAN,
  is_ssl      BOOLEAN,
  lft         INT,
  rgt         INT,
  status      INT         NOT NULL,
  enable_date DATETIME,
  create_date DATETIME    NOT NULL,
  modify_date DATETIME,
  PRIMARY KEY (id)
);

INSERT INTO ecms.au_function_menu (id, name, url, icon_class,remark, parent_id, keyword, hot_key, code, order_code, type, tree_level, is_leaf, is_public, is_ssl, lft, rgt, status, enable_date, create_date, modify_date)
VALUES ('1', '功能菜单', '/test', 'icon-chart-organisation','功能菜单', 1, '1', '功能菜单', '功能菜单', '001', '1', 1, 1, 1, 1, 1, 1000, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ecms.au_function_menu (id, name, url, icon_class,remark, parent_id, keyword, hot_key, code, order_code, type, tree_level, is_leaf, is_public, is_ssl, lft, rgt, status, enable_date, create_date, modify_date)
VALUES ('2', '组织机构', '/test','', '组织机构', 1, '1', '组织机构', '组织机构', '001', '1', 1, 1, 1, 1, 1, 1000, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ecms.au_function_menu (id, name, url, icon_class,remark, parent_id, keyword, hot_key, code, order_code, type, tree_level, is_leaf, is_public, is_ssl, lft, rgt, status, enable_date, create_date, modify_date)
VALUES ('3', '权限管理', '/test','', '权限管理', 1, '1', '权限管理', '权限管理', '001', '1', 1, 1, 1, 1, 1, 1000, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ecms.au_function_menu (id, name, url, icon_class, remark, parent_id, keyword, hot_key, code, order_code, type, tree_level, is_leaf, is_public, is_ssl, lft, rgt, status, enable_date, create_date, modify_date)
VALUES ('4', '组织机构管理', '/test','', '组织机构管理', 1, '1', '组织机构管理', '组织机构管理', '001', '1', 1, 1, 1, 1, 1, 1000, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

