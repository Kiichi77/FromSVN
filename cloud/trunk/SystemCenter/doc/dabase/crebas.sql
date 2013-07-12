/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/7/2 11:37:50                            */
/*==============================================================*/


drop table if exists tb_dom_user;

drop table if exists tb_domain;

drop table if exists tb_group;

drop table if exists tb_powerShell2;

drop table if exists tb_user;

drop table if exists tb_variable;

/*==============================================================*/
/* Table: tb_dom_user                                           */
/*==============================================================*/
create table tb_dom_user
(
   id                   bigint not null,
   tb_domain_id         bigint,
   tb_group_id          bigint,
   first_name           varchar(150),
   last_name            varchar(150),
   name                 varchar(150),
   english_name         varchar(150),
   login_name           varchar(150),
   password             varchar(150),
   not_update_pwd       smallint comment '1、不能更改
            2、能更改',
   next_update_pwd      smallint comment '1、登陆须更改密码
            2、登陆不更改密码',
   pwd_expired          char(10) comment '1、永不过期
            2、过期',
   account_disabled     smallint comment '1、已禁用
            2、未禁用',
   creater              varchar(150),
   primary key (id)
);

alter table tb_dom_user comment '域用户，system center 登陆用户';

/*==============================================================*/
/* Table: tb_domain                                             */
/*==============================================================*/
create table tb_domain
(
   id                   bigint not null,
   domain_name          varchar(150),
   primary key (id)
);

alter table tb_domain comment '系统域';

/*==============================================================*/
/* Table: tb_group                                              */
/*==============================================================*/
create table tb_group
(
   id                   bigint not null,
   group_name           varchar(150),
   group_domain         smallint comment '1、全局域
            2、本地域
            3、通用',
   group_type           smallint comment '1、安全组
            2、通讯组',
   primary key (id)
);

alter table tb_group comment '系统组';

/*==============================================================*/
/* Table: tb_powerShell2                                        */
/*==============================================================*/
create table tb_powerShell2
(
   file_name            varchar(150),
   powerShell_name      varchar(150),
   remark               varchar(150),
   id                   bigint not null,
   tb_variable_id       bigint,
   primary key (id)
);

alter table tb_powerShell2 comment '脚本管理表';

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   bigint not null,
   tb_dom_user_id       bigint,
   user_name            varchar(150),
   email                varchar(150),
   account              varchar(150),
   password             varchar(150),
   register_time        timestamp,
   unregister_time      timestamp,
   primary key (id)
);

alter table tb_user comment '用于web登陆的用户';

/*==============================================================*/
/* Table: tb_variable                                           */
/*==============================================================*/
create table tb_variable
(
   name                 varchar(150),
   value                varchar(150),
   id                   bigint not null,
   primary key (id)
);

alter table tb_variable comment '脚本变量表';

alter table tb_dom_user add constraint FK_Relationship_2 foreign key (tb_domain_id)
      references tb_domain (id) on delete restrict on update restrict;

alter table tb_dom_user add constraint FK_Relationship_3 foreign key (tb_group_id)
      references tb_group (id) on delete restrict on update restrict;

alter table tb_powerShell2 add constraint FK_Relationship_1 foreign key (tb_variable_id)
      references tb_variable (id) on delete restrict on update restrict;

alter table tb_user add constraint FK_Relationship_4 foreign key (tb_dom_user_id)
      references tb_dom_user (id) on delete restrict on update restrict;

