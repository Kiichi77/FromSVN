/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2012/8/2 11:01:03                            */
/*==============================================================*/


drop index log_index on tb_systemoperlog;

drop table if exists tb_systemoperlog;

drop table if exists tb_user;

drop index blacklist_index on tb_black_name;

drop table if exists tb_black_name;

drop table if exists tb_chassis;

drop table if exists tb_data_center;

drop index collect_index on tb_history_server_info;

drop table if exists tb_history_server_info;

drop index policy_index on tb_policy;

drop table if exists tb_policy;

drop table if exists tb_policy_sever;

drop table if exists tb_right;

drop table if exists tb_right_role;

drop table if exists tb_role;

drop index server_index on tb_server;

drop table if exists tb_server;

drop table if exists tb_server_room;

drop table if exists tb_stack;

drop table if exists tb_stack_line;

drop table if exists tb_system_event_log;

/*==============================================================*/
/* Table: tb_systemoperlog                                      */
/*==============================================================*/
create table tb_systemoperlog
(
   id                             bigint                         not null,
   admin_id                       bigint,
   operation_result               tinyint                        not null,
   date_time                      datetime,
   operation_type                 varchar(20)                    not null
);

/*==============================================================*/
/* index: "Reference_5_fk"                                            */
/*==============================================================*/
create index Reference_5_fk
(
   admin_id
);

/*==============================================================*/
/* Index: log_index                                             */
/*==============================================================*/
create index log_index on tb_systemoperlog
(
   admin_id,
   date_time,
   operation_type
);

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                             bigint                         not null,
   name                           varchar(20),
   accouont                       varchar(20)                    not null,
   email                          varchar(50),
   role                           bigint,
   note                           varchar(200),
   password                       varchar(20)                    not null,
   phone_number                   varchar(30)
);

/*==============================================================*/
/* index: "Reference_4_fk"                                            */
/*==============================================================*/
create index Reference_4_fk
(
   ROLE
);

/*==============================================================*/
/* Table: tb_black_name                                         */
/*==============================================================*/
create table tb_black_name
(
   id                             bigint                         not null auto_increment,
   server_id                      bigint                         not null
);

/*==============================================================*/
/* Index: blacklist_index                                       */
/*==============================================================*/
create index blacklist_index on tb_black_name
(
   server_id
);

/*==============================================================*/
/* Table: tb_chassis                                            */
/*==============================================================*/
create table tb_chassis
(
   id                             bigint                         not null auto_increment,
   frame_id                       int,
   case_name                      varchar(30),
   note                           varchar(200)
);

/*==============================================================*/
/* index: "Reference_7_fk"                                            */
/*==============================================================*/
create index Reference_7_fk
(
   frame_id
);

/*==============================================================*/
/* Table: tb_data_center                                        */
/*==============================================================*/
create table tb_data_center
(
   id                             bigint                         not null auto_increment,
   note                           varchar(200),
   center_name                    varchar(30)                    not null
);

/*==============================================================*/
/* Table: tb_history_server_info                                */
/*==============================================================*/
create table tb_history_server_info
(
   id                             bigint                         not null,
   server_id                      bigint                         not null,
   datet_ime                      datetime                       not null,
   collect_type                   int                            not null,
   collect_string                 varchar(200)                   not null,
   collect_number                 varchar(50)                    not null
);

/*==============================================================*/
/* index: "Reference_3_fk"                                            */
/*==============================================================*/
create index Reference_3_fk
(
   server_id
);

/*==============================================================*/
/* Index: collect_index                                         */
/*==============================================================*/
create index collect_index on tb_history_server_info
(
   server_id,
   datet_ime,
   collect_type
);

/*==============================================================*/
/* Table: tb_policy                                             */
/*==============================================================*/
create table tb_policy
(
   id                             bigint                         not null auto_increment,
   policy_name                    varchar(20)                    not null,
   policy_type                    tinyint                        not null,
   policy_limit                   int                            not null,
   command                        varchar(100)                   not null,
   policy_trigger                 varchar(30)                    not null,
   policy_start_time              time                           not null,
   policy_end_time                time                           not null,
   domain                         char(3)                        not null
);

/*==============================================================*/
/* Index: policy_index                                          */
/*==============================================================*/
create index policy_index on tb_policy
(
   policy_name
);

/*==============================================================*/
/* Table: tb_policy_sever                                       */
/*==============================================================*/
create table tb_policy_sever
(
   id                             bigint                         not null auto_increment,
   bining_server_id               bigint                         not null,
   bining_server_type             int                            not null,
   bining_state                   int                            not null,
   bining_type                    int                            not null,
   bining_id                      bigint                         not null
);

/*==============================================================*/
/* index: "Reference_2_fk"                                            */
/*==============================================================*/
create index Reference_2_fk
(
   bining_id
);

/*==============================================================*/
/* Table: tb_right                                              */
/*==============================================================*/
create table tb_right
(
   id                             bigint                         not null auto_increment,
   url                            varchar(50)
);

/*==============================================================*/
/* Table: tb_right_role                                         */
/*==============================================================*/
create table tb_right_role
(
   role_id                        bigint                         not null,
   right_id                       bigint                         not null
);

/*==============================================================*/
/* Table: tb_role                                               */
/*==============================================================*/
create table tb_role
(
   id                             bigint                         not null auto_increment,
   rolename                       varchar(30),
   authority_id                   bigint,
   note                           varchar(200)
);

/*==============================================================*/
/* index: "Reference_6_fk"                                            */
/*==============================================================*/
create index Reference_6_fk
(
   authority_id
);

/*==============================================================*/
/* Table: tb_server                                             */
/*==============================================================*/
create table tb_server
(
   id                             bigint                         not null,
   case_id                        bigint,
   group_id                       bigint,
   server_name                    varchar(20)                    not null,
   operate_system                 varchar(20)                    not null,
   idrac_ip                       varchar(20)                    not null,
   idrac_user                     varchar(20)                    not null,
   idrac_password                 varchar(20)                    not null,
   server_ip_in                   varchar(20)                    not null,
   server_ip_out                  varchar(20),
   server_description             varchar(20),
   canget_cpu_consumption         tinyint,
   canget_power_consumption       tinyint,
   canget_memory_consumption      tinyint,
   canget_wind_temperature        tinyint,
   cpu_consumption_policy         tinyint,
   memory_consumption_policy      tinyint,
   power_consumption_policy       tinyint
);

/*==============================================================*/
/* index: "Reference_1_fk"                                            */
/*==============================================================*/
create index Reference_1_fk
(
   case_id
);

/*==============================================================*/
/* Index: server_index                                          */
/*==============================================================*/
create index server_index on tb_server
(
   case_id,
   group_id,
   server_name,
   operate_system
);

/*==============================================================*/
/* Table: tb_server_room                                        */
/*==============================================================*/
create table tb_server_room
(
   id                             bigint                         not null,
   server_name                    varchar(30),
   center_id                      bigint,
   note                           varchar(200)
);

/*==============================================================*/
/* index: "Reference_10_fk"                                            */
/*==============================================================*/
create index Reference_10_fk
(
   center_id
);

/*==============================================================*/
/* Table: tb_stack                                              */
/*==============================================================*/
create table tb_stack
(
   id                             bigint                         not null auto_increment,
   frame_name                     blob,
   note                           blob,
   frame_series_id                bigint
);

/*==============================================================*/
/* index: "Reference_8_fk"                                            */
/*==============================================================*/
create index Reference_8_fk
(
   frame_series_id
);

/*==============================================================*/
/* Table: tb_stack_line                                         */
/*==============================================================*/
create table tb_stack_line
(
   id                             bigint                         not null auto_increment,
   machine_romm_id                bigint,
   frame_series_name              varchar(30),
   note                           varchar(200)
);

/*==============================================================*/
/* index: "Reference_9_fk"                                            */
/*==============================================================*/
create index Reference_9_fk
(
   machine_romm_id
);

/*==============================================================*/
/* Table: tb_system_event_log                                   */
/*==============================================================*/
create table tb_system_event_log
(
   id                             bigint                         not null,
   server_ip                      varchar(20),
   server_sel                     varchar(100),
   date_time                      datetime,
   state                          tinyint
);

alter table tb_systemoperlog add constraint fk_reference_5 foreign key (admin_id)
      references tb_user (id) on delete restrict on update restrict;

alter table tb_user add constraint fk_reference_4 foreign key (role)
      references tb_role (id) on delete restrict on update restrict;

alter table tb_chassis add constraint fk_reference_7 foreign key (frame_id)
      references tb_stack (id) on delete restrict on update restrict;

alter table tb_history_server_info add constraint fk_reference_3 foreign key (server_id)
      references tb_server (id) on delete restrict on update restrict;

alter table tb_policy_sever add constraint fk_reference_2 foreign key (bining_id)
      references tb_policy (id) on delete restrict on update restrict;

alter table tb_role add constraint fk_reference_6 foreign key (authority_id)
      references tb_right (id) on delete restrict on update restrict;

alter table tb_server add constraint fk_reference_1 foreign key (case_id)
      references tb_chassis (id) on delete restrict on update restrict;

alter table tb_server_room add constraint fk_reference_10 foreign key (center_id)
      references tb_data_center (id) on delete restrict on update restrict;

alter table tb_stack add constraint fk_reference_8 foreign key (frame_series_id)
      references tb_stack_line (id) on delete restrict on update restrict;

alter table tb_stack_line add constraint fk_reference_9 foreign key (machine_romm_id)
      references tb_server_room (id) on delete restrict on update restrict;

