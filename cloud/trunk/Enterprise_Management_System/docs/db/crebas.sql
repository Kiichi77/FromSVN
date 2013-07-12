/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/7/10 14:33:42                           */
/*==============================================================*/


drop table if exists tb_depart;

drop table if exists tb_employee;

drop table if exists tb_employee_contracts;

drop table if exists tb_orgen;

drop table if exists tb_permanent_assets;

drop table if exists tb_post;

/*==============================================================*/
/* Table: tb_depart                                             */
/*==============================================================*/
create table tb_depart
(
   dept_id              varchar(50) not null,
   name                 varchar(100) comment '资产姓名',
   duty                 varchar(100),
   parent_depart        varchar(50),
   primary key (dept_id)
);

alter table tb_depart comment '部门表';

/*==============================================================*/
/* Table: tb_employee                                           */
/*==============================================================*/
create table tb_employee
(
   employee_id          varchar(50) not null,
   orgen_jd             varchar(50) comment '主键id',
   id                   varchar(50),
   name                 varchar(100) comment '资产姓名',
   identity_card        varchar(18),
   birthday             date,
   address              varchar(100),
   phone                varchar(50) comment '联系电话',
   native_place         varchar(10),
   domicile_place       varchar(50),
   graduation_date      date,
   graduate_school      varchar(50),
   major                varchar(20),
   education_background varchar(20),
   marry                varchar(20),
   nation               varchar(20),
   sex                  char(2),
   politics_status      varchar(20),
   dates_employed       date,
   working_time         date,
   E_mail               varchar(50),
   immediate_family     varchar(50),
   status               char(2),
   opening_bank         varchar(50),
   card_no              numeric(20,0),
   household_properties char(2),
   job_title            varchar(20),
   title_level          varchar(20),
   change_jobs          varchar(20),
   social_security_time date,
   primary key (employee_id)
);

/*==============================================================*/
/* Table: tb_employee_contracts                                 */
/*==============================================================*/
create table tb_employee_contracts
(
   contracts_id         varchar(50) not null,
   employee_id          varchar(50),
   contract_effective_date date,
   contract_end_date    date,
   contract_date        date,
   status               char(2),
   primary key (contracts_id)
);

/*==============================================================*/
/* Table: tb_orgen                                              */
/*==============================================================*/
create table tb_orgen
(
   orgen_jd             varchar(50) not null comment '主键id',
   name                 varchar(100) comment '资产姓名',
   phone                varchar(50) comment '联系电话',
   place                varchar(100) comment '地址',
   type                 smallint comment '类型
            0：总公司
            1：分公司
            2：办事处',
   create_time          date,
   parent_orgen         int,
   number               varchar(100),
   tell_phone           varchar(50),
   business_license_no  varchar(100),
   bank_name            varchar(100),
   tax_registration_certificate varchar(100),
   primary key (orgen_jd)
);

alter table tb_orgen comment '机构表';

/*==============================================================*/
/* Table: tb_permanent_assets                                   */
/*==============================================================*/
create table tb_permanent_assets
(
   permanent_id         varchar(50) not null,
   number               varchar(100),
   name                 varchar(100) comment '资产姓名',
   acc_type             smallint,
   state                smallint comment '状态
            1：使用中
            2：损坏
            3：库存',
   buy_time             date,
   price                double,
   remark               varchar(100),
   primary key (permanent_id)
);

alter table tb_permanent_assets comment '固定资产';

/*==============================================================*/
/* Table: tb_post                                               */
/*==============================================================*/
create table tb_post
(
   id                   varchar(50) not null,
   dept_id              varchar(50),
   post_name            varchar(20),
   post_duty            varchar(50),
   primary key (id)
);

alter table tb_employee add constraint FK_Relationship_6 foreign key (id)
      references tb_post (id) on delete restrict on update restrict;

alter table tb_employee add constraint FK_Relationship_8 foreign key (orgen_jd)
      references tb_orgen (orgen_jd) on delete restrict on update restrict;

alter table tb_employee_contracts add constraint FK_Relationship_4 foreign key (employee_id)
      references tb_employee (employee_id) on delete restrict on update restrict;

alter table tb_post add constraint FK_Relationship_5 foreign key (dept_id)
      references tb_depart (dept_id) on delete restrict on update restrict;

