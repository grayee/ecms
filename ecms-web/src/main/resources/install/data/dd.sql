/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012/6/5 11:19:05                            */
/*==============================================================*/


drop table if exists DD_BASEDATA;

drop table if exists DD_BASEDATA_TYPE;

drop table if exists DD_COLUMN;

drop table if exists DD_CONSTRAINT;

drop table if exists DD_MODULE;

drop table if exists DD_MODULE_COMMON;

drop table if exists DD_MODULE_MASTER;

drop table if exists DD_MODULE_MD_DETAIL;

drop table if exists DD_MODULE_QUERY;

drop table if exists DD_MODULE_QUERY_DETAIL;

drop table if exists DD_RELATION;

drop table if exists DD_TABLE;

drop table if exists DD_TEMPLATE;

drop table if exists DD_VIEW;

drop table if exists DD_VIEW_FIELD;

drop table if exists DD_VIEW_TABLE;

/*==============================================================*/
/* Table: DD_BASEDATA                                           */
/*==============================================================*/
create table DD_BASEDATA
(
   ID                   varchar(32) not null comment '主键',
   CODE                 varchar(64) comment '编码',
   TYPEID               varchar(32) not null comment '类型ID',
   TYPECODE             varchar(64) not null comment '类型编码',
   LEVELNUM             numeric(2,0) not null comment '级次',
   ISLEAF               char(1) not null default '1' comment '是否底级 1: 是 0:否',
   NAME                 varchar(64) not null comment '名称',
   ISBNCODE             varchar(64) comment '国标码',
   HINTCODE             varchar(64) comment '提示码',
   STATUS               char(1) not null default '1' comment '是否启用 1: 是 0:否',
   STARTDATE            varchar(64) comment '开始日期',
   ENDDATE              varchar(64) comment '结束日期',
   DESCRIPTION          varchar(256) comment '说明',
   TREECODE             varchar(64) comment '级次编码',
   SEQNO                numeric(3,0) not null default 0 comment '显示序号',
   CREATEDBY            varchar(64) comment '创建者',
   UPDATEDBY            varchar(64) comment '修改者',
   CREATED_DATE              date  default null comment '创建时间',
   UPDATED              date comment '修改时间',
   PARENTID             varchar(64) comment '父ID',
   SPARE1               numeric(8,0),
   SPARE2               varchar(1024),
   SPARE3               varchar(1024),
   SPARE4               varchar(1024),
   SPARE5               date,
   primary key (ID)
);

alter table DD_BASEDATA comment '该表存储了系统中所有的基础数据';

/*==============================================================*/
/* Table: DD_BASEDATA_TYPE                                      */
/*==============================================================*/
create table DD_BASEDATA_TYPE
(
   ID                   varchar(32) not null comment '主键',
   CODE                 varchar(64) comment '类型编码',
   NAME                 varchar(64) not null comment '类型名称',
   ISSYSTEM             char(1) not null default '0' comment '是否系统',
   primary key (ID)
);

alter table DD_BASEDATA_TYPE comment '该表存储了系统中所有的基础数据类型';

/*==============================================================*/
/* Table: DD_COLUMN                                             */
/*==============================================================*/
create table DD_COLUMN
(
   ID                   varchar(32) not null,
   TABLEID              varchar(32),
   TABLENAME            varchar(128) not null,
   COLUMNNAME           varchar(128) not null,
   DISPLAYNAME          varchar(128) comment '对应的中文名',
   COLUMNTYPE           varchar(10),
   COLUMNLEN            numeric(10,0),
   COLUMNPREC           numeric(8,0) comment '仅对小数有效',
   DEFAULTVAL           varchar(128),
   ISNULLABLE           char(1) default '1' comment '1: 是 0:否',
   ISVIRTUAL            char(1) default '0' comment '1: 是 0:否',
   VALUEFROM            numeric(8,0) comment '有意义的数字有含义，null表示无穷小',
   VALUETO              numeric(8,0) comment '有意义的数字有含义，null表示无穷大',
   FORMULA              varchar(128),
   ISCREATED            char(1) default '0' comment '1: 是 0:否',
   STATUS               char(1) not null default '1' comment '1：启用；0：禁用',
   CATEGORY             varchar(64) comment '列分类',
   CREATEDBY            varchar(64),
   UPDATEDBY            varchar(64),
   CREATED_DATE              date  default null,
   UPDATED              date,
   REMARK               varchar(1024),
   SPARE1               numeric(8,0),
   SPARE2               numeric(8,0),
   SPARE3               numeric(8,0),
   SPARE4               varchar(1024),
   SPARE5               varchar(1024),
   SPARE6               date,
   primary key (ID)
);

alter table DD_COLUMN comment '该表存储了系统所涉及的数据对象的所有字段，并对字段属性进行描述';

/*==============================================================*/
/* Table: DD_CONSTRAINT                                         */
/*==============================================================*/
create table DD_CONSTRAINT
(
   ID                   varchar(32) not null,
   CONSTRAINTNAME       varchar(64) comment '约束名称',
   CONSTRAINTTYPE       char(1) comment '约束类型',
   TABLENAME            varchar(64) comment '列名',
   COLUMNNAME           varchar(64),
   CONDITION_CONTENT            varchar(300) comment '约束内容',
   REFTABLENAME         varchar(64) comment '参照表名',
   STATUS               char(1) comment '1：启用；0：禁用',
   REFCOLUMNNAME        varchar(64) comment '参照列名',
   CREATEDBY            varchar(64),
   UPDATEDBY            varchar(64),
   CREATED_DATE              date  default null,
   UPDATED              date,
   primary key (ID)
);

alter table DD_CONSTRAINT comment '该表用来描述系统中的约束';

/*==============================================================*/
/* Table: DD_MODULE                                             */
/*==============================================================*/
create table DD_MODULE
(
   ID                   varchar(64) not null comment '主键',
   NAME                 varchar(64) not null comment '功能的特征标识，不允许重复',
   TEMPLATEID           varchar(64) not null comment '001：普通模板 002：主子表模板 003：查询模板',
   ISSYSTEM             char(1) not null default '0' comment '是否系统视图',
   primary key (ID)
);

/*==============================================================*/
/* Table: DD_MODULE_COMMON                                      */
/*==============================================================*/
create table DD_MODULE_COMMON
(
   MODULEID             varchar(64) not null comment '主键',
   TABLENAME            varchar(64) not null comment '表名',
   PARAM                varchar(512) comment '格式：p1#***+p2#***',
   URL                  varchar(512) not null comment '功能链接',
   primary key (MODULEID)
);

/*==============================================================*/
/* Table: DD_MODULE_MASTER                                      */
/*==============================================================*/
create table DD_MODULE_MASTER
(
   MODULEID             varchar(64) not null comment '主键',
   TABLENAME            varchar(64) not null comment '表名',
   TABNAME              varchar(64) not null comment '页签名称',
   PARAM                varchar(512) comment '格式：p1#***+p2#***',
   TRANFLAG             char(1) default '1' comment '1：主子表在一个事务中 0：主子表不在同一事务中',
   URL                  varchar(512) not null comment '功能链接',
   primary key (MODULEID)
);

/*==============================================================*/
/* Table: DD_MODULE_MD_DETAIL                                   */
/*==============================================================*/
create table DD_MODULE_MD_DETAIL
(
   ID                   varchar(64) not null comment '主键',
   MODULEID             varchar(64) not null comment '功能主键',
   TABLENAME            varchar(64) not null comment '表名',
   TABNAME              varchar(64) not null comment '页签名称',
   PARAM                varchar(512) comment '格式：p1#***+p2#***',
   primary key (ID)
);

/*==============================================================*/
/* Table: DD_MODULE_QUERY                                       */
/*==============================================================*/
create table DD_MODULE_QUERY
(
   MODULEID             varchar(64) not null comment '主键',
   CONDITION_CONTENT          varchar(512) comment '过滤条件',
   URL                  varchar(512) not null comment '功能链接',
   QUERYFIELDSCONF      varchar(512),
   primary key (MODULEID)
);

/*==============================================================*/
/* Table: DD_MODULE_QUERY_DETAIL                                */
/*==============================================================*/
create table DD_MODULE_QUERY_DETAIL
(
   ID                   varchar(64) not null comment '主键',
   MODULEID             varchar(64) not null comment '功能主键',
   TABLENAME            varchar(64) not null comment '表名',
   VIEWCODE             varchar(64) comment '视图code',
   FIELDID              varchar(64),
   TYPE                 varchar(64) comment '字段类型：LIST，QUERY',
   SEQNO                numeric(8,0) comment '排列序号',
   primary key (ID)
);

/*==============================================================*/
/* Table: DD_RELATION                                           */
/*==============================================================*/
create table DD_RELATION
(
   ID                   varchar(32) not null,
   RELATIONNAME         varchar(128) not null,
   DISPLAYNAME          varchar(128),
   PKTABLE              varchar(128) not null,
   PKCOLUMN             varchar(128) not null,
   FKTABLE              varchar(128) not null,
   FKCOLUMN             varchar(128) not null,
   ISCREATED            char(1) default '0' comment '1: 是 0:否',
   STATUS               char(1) not null default '1' comment '1：启用；0：禁用',
   CREATEDBY            varchar(64),
   UPDATEDBY            varchar(64),
   CREATED_DATE              date  default null,
   UPDATED              date,
   REMARK               varchar(1024),
   SPARE1               numeric(8,0),
   SPARE2               numeric(8,0),
   SPARE3               numeric(8,0),
   SPARE4               varchar(1024),
   SPARE5               varchar(1024),
   SPARE6               date,
   primary key (ID)
);

alter table DD_RELATION comment '该表存储了系统所涉及的各表之间的关系，并对表间关系进行描述';

/*==============================================================*/
/* Table: DD_TABLE                                              */
/*==============================================================*/
create table DD_TABLE
(
   ID                   varchar(32) not null,
   TABLENAME            varchar(128) not null comment '表名（不能修改）',
   DISPLAYNAME          varchar(128) not null comment '表对应的中文名',
   CATEGORY             char(1) not null comment '1：数据字典表；2：基础数据表；3：控制数据表；4：业务数据表',
   SUBCATEGORY          varchar(128),
   ISVIRTUAL            char(1) default '0' comment '1: 是 0:否',
   CHECKEXPRESSION      varchar(128),
   ISCREATED            char(1) default '0' comment '1: 是 0:否',
   STATUS               char(1) not null default '1' comment '1：启用；0：禁用',
   CREATEDBY            varchar(64),
   UPDATEDBY            varchar(64),
   CREATED_DATE              date  default null,
   UPDATED              date,
   REMARK               varchar(1024),
   SPARE1               numeric(8,0),
   SPARE2               numeric(8,0),
   SPARE3               numeric(8,0),
   SPARE4               varchar(1024),
   SPARE5               varchar(1024),
   SPARE6               date,
   primary key (ID)
);

alter table DD_TABLE comment '该表用来描述系统中所用到的所有的数据表以及各表的属性';

/*==============================================================*/
/* Table: DD_TEMPLATE                                           */
/*==============================================================*/
create table DD_TEMPLATE
(
   ID                   varchar(64) not null comment '主键',
   NAME                 varchar(64) not null comment '名称',
   MAINURL              varchar(128) not null comment '入口URL',
   primary key (ID)
);

/*==============================================================*/
/* Table: DD_VIEW                                               */
/*==============================================================*/
create table DD_VIEW
(
   ID                   varchar(32) not null comment '主键',
   CODE                 varchar(64) comment '编码',
   NAME                 varchar(64) not null comment '名称',
   VIEWTYPE             numeric(2,0) not null comment '视图类型(0:列表1：编辑2：查询)',
   CONDITION_CONTENT          varchar(512) comment '查询条件',
   BUSINESSCLASS        varchar(512) comment '业务逻辑类',
   FIELDLAYOUT          varchar(2000) comment '字段布局信息，只对编辑和查询视图起作用',
   PAGESIZE             numeric(3,0) default 0 comment '每页数据条数，只用于列表视图',
   ISSYSTEM             char(1) not null default '0' comment '是否系统视图',
   primary key (ID)
);

alter table DD_VIEW comment '该表存储了系统中所有的视图辅助表';

/*==============================================================*/
/* Table: DD_VIEW_FIELD                                         */
/*==============================================================*/
create table DD_VIEW_FIELD
(
   ID                   varchar(32) not null comment '主键',
   NAME                 varchar(64) not null comment '名称',
   DISPLAYNAME          varchar(64) comment '显示名称',
   VIEWID               varchar(32) not null comment '所属视图',
   VIEWCODE             varchar(64) not null comment '所属视图编码',
   STATUS               char(1) not null default '1' comment '是否启用 1: 是 0:否',
   DESCRIPTION          varchar(512) comment '说明',
   DISPLAYTYPE          numeric(2,0) not null comment '显示方式',
   REFMODEL             varchar(512) comment '参照模型',
   REFSOURCE            varchar(64) comment '基础数据',
   REFSOURCECLASS       varchar(64) comment '数据来源',
   CONDITION_CONTENT          varchar(512) comment '过滤条件',
   CALLBACK             varchar(512) comment '处理脚本',
   TABLENAME            varchar(64) comment '对应表',
   COLUMNNAME           varchar(64) comment '对应列',
   COLUMNSQL            varchar(512) comment '计算公式',
   READONLY             char(1) not null default '0' comment '是否只读',
   DISPLAYORIGINVALUE   char(1) not null default '1' comment '是否显示原始值',
   KEYSOURCE            char(1) not null default '1' comment '主键来源：1:id 0:code',
   DISPLAY              char(1) not null default '1' comment '是否显示',
   SEQNO                numeric(10,0) comment '显示序号',
   SORTNO               numeric(10,0) comment '排序编号',
   CREATEDBY            varchar(64) comment '创建者',
   UPDATEDBY            varchar(64) comment '修改者',
   CREATED_DATE              date  default null comment '创建时间',
   UPDATED              date comment '修改时间',
   SORT                 char(1) not null default '0' comment '是否排序',
   WIDTH                varchar(10) comment '显示宽度',
   ALIGN                varchar(10) comment '对齐方式',
   CATEGORY             varchar(64) comment '字段分组',
   BUSKEY               varchar(64) comment '字段的业务标识',
   primary key (ID)
);

alter table DD_VIEW_FIELD comment '该表存储了系统中所有的显示字段';

/*==============================================================*/
/* Table: DD_VIEW_TABLE                                         */
/*==============================================================*/
create table DD_VIEW_TABLE
(
   ID                   varchar(32) not null comment '主键',
   VIEWID               varchar(32) not null comment '所属视图',
   VIEWCODE             varchar(64) not null comment '所属视图编码',
   TABLEID              varchar(32) not null comment '表标识',
   KEYNAME              varchar(64) comment '主键',
   primary key (ID)
);

