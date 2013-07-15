 
/*PostgreSQL Data  

 -- Author:邢双阳

 --Date: 2013-05-40 20:30:12
 */

 
-- ----------------------------
-- Table structure for  jeecg_demo 
-- ----------------------------

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for  jeecg_demo 
-- ----------------------------
DROP TABLE IF EXISTS  jeecg_demo ;
CREATE TABLE  jeecg_demo  (
   id  varchar(255) NOT NULL,
   age   int  default NULL,
   birthday  date default NULL,
   create_time  date default NULL,
   dep_id  varchar(255) default NULL,
   email  varchar(255) default NULL,
   mobile_phone  varchar(255) default NULL,
   office_phone  varchar(255) default NULL,
   salary  decimal(19,2) default NULL,
   sex  varchar(4) default NULL,
   user_name  varchar(32) NOT NULL  ,
   status  varchar(4) default NULL  ,
   content  varchar(100) default NULL,
   content1  varchar(100) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE    (id)
) ;

-- ----------------------------
-- Records of jeecg_demo
-- ----------------------------
INSERT INTO  jeecg_demo  VALUES ('8a8a8a813ef47c6e013ef48239820030', '27', '2013-05-10 00:00:00', '2013-05-24 04:16:09', '297e5a493d9f2cbc013d9f4450530001', 'zhangdaiscott@163.com', '13426342929', '123456', '20000.00', '0', '张代浩', '1', '1111', null);
INSERT INTO  jeecg_demo  VALUES ('8a8a8a813ef47c6e013ef48471220035', '33', null, null, '150', 'zhangdaiscott@163.com', '', '123456', '33.00', '0', 'zhangdaiscott', '0', null, null);

-- ----------------------------
-- Table structure for  jeecg_one_to_one 
-- ----------------------------
DROP TABLE IF EXISTS  jeecg_one_to_one ;
CREATE TABLE  jeecg_one_to_one  (
   ID  varchar(36) NOT NULL ,-- COMMENT '主键',
   NAME  varchar(32) default NULL ,-- COMMENT '用户名',
   AGE   int  default NULL ,-- COMMENT '年龄',
   SALARY  decimal(10,2) default NULL ,-- COMMENT '工资',
   BIRTHDAY  date default NULL ,-- COMMENT '生日',
   REGISTER_DT  date default NULL ,-- COMMENT '注册时间',
   CRTUSER  varchar(36) default NULL ,-- COMMENT '创建人ID',
   CRTUSER_NAME  varchar(50) default NULL ,-- COMMENT '创建人',
   CREATE_DT  date default NULL ,-- COMMENT '创建时间',
  PRIMARY KEY  ( ID ),
  UNIQUE   ( ID )
) ;

-- ----------------------------
-- Records of jeecg_one_to_one
-- ----------------------------

-- ----------------------------
-- Table structure for  jeecg_order_custom 
-- ----------------------------
DROP TABLE IF EXISTS  jeecg_order_custom ;
CREATE TABLE  jeecg_order_custom  (
   id  varchar(36) NOT NULL,
   GORDER_ID  varchar(36) default NULL ,-- COMMENT '关联主订单外键',
   go_order_code  varchar(36) NOT NULL ,-- COMMENT '团购订单号',
   goc_cus_name  varchar(50) default NULL ,-- COMMENT '姓名',
   goc_sex  varchar(2) default NULL ,-- COMMENT '性别',
   goc_idcard  varchar(32) default NULL ,-- COMMENT '身份证号',
   goc_passport_code  varchar(32) default NULL ,-- COMMENT '护照号',
   goc_buss_content  varchar(100) default NULL ,-- COMMENT '业务',
   goc_content  varchar(200) default NULL ,-- COMMENT '备注',
   crtuser  varchar(36) default NULL ,-- COMMENT '创建人',
   crtuser_name  varchar(32) default NULL ,-- COMMENT '创建人名字',
   create_dt  date default NULL ,-- COMMENT '创建时间',
   modifier  varchar(36) default NULL ,-- COMMENT '修改人',
   modifier_name  varchar(32) default NULL ,-- COMMENT '修改人名字',
   modify_dt  date default NULL ,-- COMMENT '修改时间',
   delflag   int  default NULL ,-- COMMENT '删除标记',
   del_dt  date default NULL ,-- COMMENT '删除时间',
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of jeecg_order_custom
-- ----------------------------
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813ed3f193013ed410b6530038', null, '9999', '张三', '0', '123', '', '', '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813ed3f193013ed410b6920039', null, '9999', '李四', '0', '456', '', '', '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813edeee60013edeefe08f0003', null, '123456', '', '', '', '', '', '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813edeee60013edeefe09f0004', null, '123456', '', '', '', '', '', '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813edf898e013edf8b2b230002', '8a8a8a813edf898e013edf8b2b140001', 'A01', '张三', '', '', '', '', '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813edf898e013edf8b2b230003', '8a8a8a813edf898e013edf8b2b140001', 'A01', '李四', '', '', '', '', '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813ee0d4fb013ee0d5ad2f0002', '8a8a8a813ee0d4fb013ee0d5ad200001', '213123', '', '', '', '', '', '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813ef47c6e013ef47fbedd0017', null, '1231', '222222', '0', '', '', '', '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_custom  VALUES ('8a8a8a813ef47c6e013ef47fbedd0018', null, '1231', '22222', '1', '', '', '', '', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for  jeecg_order_main 
-- ----------------------------
DROP TABLE IF EXISTS  jeecg_order_main ;
CREATE TABLE  jeecg_order_main  (
   id  varchar(36) NOT NULL ,-- COMMENT '主键',
   go_order_code  varchar(36) NOT NULL ,-- COMMENT '订单号',
   goder_type  varchar(2) default NULL ,-- COMMENT '订单类型',
   usertype  varchar(1) default NULL ,-- COMMENT '顾客类型 : 1直客 2同行',
   go_contact_name  varchar(50) NOT NULL ,-- COMMENT '联系人',
   go_telphone  varchar(20) default NULL ,-- COMMENT '手机',
   go_order_count   int  default NULL ,-- COMMENT '订单人数',
   go_all_price  decimal(10,2) NOT NULL ,-- COMMENT '总价(不含返款)',
   go_return_price  decimal(10,2) default NULL ,-- COMMENT '返款',
   go_content  varchar(200) default NULL ,-- COMMENT '备注',
   crtuser  varchar(36) default NULL ,-- COMMENT '创建人',
   crtuser_name  varchar(32) default NULL ,-- COMMENT '创建人名字',
   create_dt  date default NULL ,-- COMMENT '创建时间',
   modifier  varchar(36) default NULL ,-- COMMENT '修改人',
   modifier_name  varchar(32) default NULL ,-- COMMENT '修改人名字',
   modify_dt  date default NULL ,-- COMMENT '修改时间',
   delflag   int  default NULL ,-- COMMENT '删除标记',
   del_dt  date default NULL ,-- COMMENT '删除时间',
  PRIMARY KEY  ( id ),
  UNIQUE     ( id )
) ;

-- ----------------------------
-- Records of jeecg_order_main
-- ----------------------------
INSERT INTO  jeecg_order_main  VALUES ('8a8a8a813ee37e07013ee37f599b0001', 'C001', '1', '1', '2', '', '100', '200.00', '3000.00', '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_main  VALUES ('8ab1c1153ee9df5a013ee9e153e30001', 'C002', '2', '1', '123', '', '123123', '222.00', '222.00', '', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for  jeecg_order_product 
-- ----------------------------
DROP TABLE IF EXISTS  jeecg_order_product ;
CREATE TABLE  jeecg_order_product  (
   id  varchar(36) NOT NULL,
   GORDER_ID  varchar(36) default NULL ,-- COMMENT '关联主订单外键',
   go_order_code  varchar(36) NOT NULL ,-- COMMENT '团购订单号',
   gop_product_type  varchar(3) NOT NULL ,-- COMMENT '服务项目类型',
   gop_product_name  varchar(100) default NULL ,-- COMMENT '产品名称',
   gop_count   int  default NULL ,-- COMMENT '个数',
   gop_one_price  decimal(10,2) default NULL ,-- COMMENT '单价',
   gop_sum_price  decimal(10,2) default NULL ,-- COMMENT '小计',
   gop_content  varchar(200) default NULL ,-- COMMENT '备注',
   crtuser  varchar(36) default NULL ,-- COMMENT '创建人',
   crtuser_name  varchar(32) default NULL ,-- COMMENT '创建人名字',
   create_dt  date default NULL ,-- COMMENT '创建时间',
   modifier  varchar(36) default NULL ,-- COMMENT '修改人',
   modifier_name  varchar(32) default NULL ,-- COMMENT '修改人名字',
   modify_dt  date default NULL ,-- COMMENT '修改时间',
   delflag   int  default NULL ,-- COMMENT '删除标记',
   del_dt  date default NULL ,-- COMMENT '删除时间',
  PRIMARY KEY  ( id ),
  UNIQUE    ( id )
) ;

-- ----------------------------
-- Records of jeecg_order_product
-- ----------------------------
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ed3f193013ed410b5c70036', null, '9999', '2', '产品1234', '1', '1.00', null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ed3f193013ed410b6530037', null, '9999', '2', '产品123456', '1', '1.00', null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813edeee60013edeefe09f0005', null, '123456', '', '', null, null, null, '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813edeee60013edeefe09f0006', null, '123456', '', '', null, null, null, '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813edf898e013edf8b2b230004', '8a8a8a813edf898e013edf8b2b140001', 'A01', '1', '苹果', null, null, null, '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813edf898e013edf8b2b520005', '8a8a8a813edf898e013edf8b2b140001', 'A01', '2', '栗子', null, null, null, '', '', '', null, '', '', null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef47fbece0015', null, '1231', '1', '1233333', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef47fbedd0016', null, '1231', '1', '3333333', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef48071550022', null, 'C002', '1', '产品名称11', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef48071640023', null, 'C002', '1', '产品名称22', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef4807cce0025', null, 'C001', '2', '产品名称11', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8a8a8a813ef47c6e013ef4807cce0026', null, 'C001', '2', '产品名称12', null, null, null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8aa6cad73e9ddad8013e9dea18cc000b', null, '123456', '2', '1234456', '1', '1.00', null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8aa6cad73e9ddad8013e9dea18cc000c', null, '123456', '1', '21234456', '2', '2.00', null, '', null, null, null, null, null, null, null, null);
INSERT INTO  jeecg_order_product  VALUES ('8aa6cad73e9ddad8013e9dea18dc000d', null, '123456', '1', '31234456', '3', '3.00', null, '', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for  jg_person 
-- ----------------------------
DROP TABLE IF EXISTS  jg_person ;
CREATE TABLE  jg_person  (
   id  varchar(255) NOT NULL,
   age   int  NOT NULL ,-- COMMENT '年龄',
   birthday  date default NULL ,-- COMMENT '生日',
   createdt  date NOT NULL ,-- COMMENT '出生日期',
   name  varchar(255) default NULL ,-- COMMENT '用户名',
   salary  decimal(19,2) NOT NULL ,-- COMMENT '工资',
  PRIMARY KEY  ( id ),
  UNIQUE     ( id )
) ;

-- ----------------------------
-- Records of jg_person
-- ----------------------------
INSERT INTO  jg_person  VALUES ('8a8a8a813ed3f193013ed4146736003b', '20', '2013-03-14', '2013-03-12 02:52:45', '张三', '10000.00');
INSERT INTO  jg_person  VALUES ('8a8a8a813ed3f193013ed414685f003d', '30', '2013-04-24', '2013-04-24 04:43:26', '李四', '9000.00');

-- ----------------------------
-- Table structure for  person 
-- ----------------------------
DROP TABLE IF EXISTS  person ;
CREATE TABLE  person  (
   ID  varchar(32) NOT NULL default '' ,-- COMMENT '主键',
   NAME  varchar(32) NOT NULL ,-- COMMENT '用户名',
   AGE   int  default NULL ,-- COMMENT '年龄',
   SALARY  decimal(10,2) default NULL ,-- COMMENT '工资',
   content  BYTEA ,-- COMMENT '个人简述',
   createDt  date default NULL ,-- COMMENT '注册日期',
   birthday  date default NULL ,-- COMMENT '生日',
   create_date  date default NULL ,-- COMMENT '创建时间',
   create_by  varchar(32) default NULL ,-- COMMENT '创建人ID',
   create_name  varchar(32) default NULL ,-- COMMENT '创建人名字',
   update_date  date default NULL ,-- COMMENT '修改时间',
   update_by  varchar(32) default NULL ,-- COMMENT '修改人ID',
   update_name  varchar(32) default NULL ,-- COMMENT '修改人名字',
  PRIMARY KEY  ( ID ),
  UNIQUE     ( ID )
) ;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO  person  VALUES ('8a8a8a813ed72ff9013ed730f3b70002', '张代浩', '27', '10000.00', null, '2013-05-24 23:38:25', '2013-05-24', null, null, null, null, null, null);
INSERT INTO  person  VALUES ('8a8a8a813ed72ff9013ed7329d6f0004', '3', '3', '3.00', null, null, null, '2013-05-24 23:43:22', 'admin', '管理员', null, null, null);
INSERT INTO  person  VALUES ('8a8a8a813ed72ff9013ed736359a0008', '3333', '33', '3333.00', null, null, null, '2013-05-24 23:44:14', 'admin', '管理员', null, null, null);
INSERT INTO  person  VALUES ('8a8a8a813ed999e7013ed99b02bd0001', '123', '123', '123.00', null, '2013-05-25 10:53:26', '2013-05-25', '2013-05-25 10:53:34', 'admin', '管理员', null, null, null);
INSERT INTO  person  VALUES ('8a8a8a813ed9b4bd013ed9b557180001', '2', '2', '2.00', null, null, null, '2013-05-25 11:22:20', 'admin', '管理员', null, null, null);

-- ----------------------------
-- Table structure for  t_s_attachment 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_attachment ;
CREATE TABLE  t_s_attachment  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   userid  varchar(32) default NULL ,-- COMMENT '用户ID',
   businesskey  varchar(32) default NULL,
   infotypeid  varchar(32) default NULL ,-- COMMENT '类型ID',
   attachmenttitle  varchar(100) default NULL,
   realpath  varchar(100) default NULL ,-- COMMENT '附件路径',
   subclassname  text,
   createdate  date default NULL,
   attachmentcontent  BYTEA ,-- COMMENT '附件内容',
   swfpath  text ,-- COMMENT 'flash路径',
   note  text,
   extend  varchar(32) default NULL,
   busentityname  varchar(100) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of t_s_attachment
-- ----------------------------
 
-- ----------------------------
-- Table structure for  t_s_base_user 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_base_user ;
CREATE TABLE  t_s_base_user  (
   id  varchar(32) NOT NULL,
   browser  varchar(20) default NULL,
   signature  BYTEA,
   userkey  varchar(200) default NULL,
   status   smallint default NULL,
   realname  varchar(50) default NULL,
   username  varchar(50) default NULL,
   departid  varchar(32) default NULL,
   password  varchar(100) default NULL,
   activitisync   smallint default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE      ( id ) 
 ) ;

-- ----------------------------
-- Records of t_s_base_user
-- ----------------------------
INSERT INTO  t_s_base_user  VALUES ('40', null, null, 'D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900', '1', '管理员', 'admin', '150', 'c44b01947c9e6e3f', '1');
INSERT INTO  t_s_base_user  VALUES ('402881e73d87d62a013d87ee2d4b0011', null, null, null, '1', '采购员', 'cgy', '150', 'f2322ec2fb9f40d1', null);
INSERT INTO  t_s_base_user  VALUES ('402881e73d87d62a013d87eeaba10014', null, null, null, '1', '采购审批员', 'cgspy', '152', 'a324509dc1a3089a', '1');
INSERT INTO  t_s_base_user  VALUES ('8a8a8a813e63096e013e630c1df10003', null, null, null, '1', '张代浩', 'scott', '150', '97c07a884bf272b5', null);

-- ----------------------------
-- Table structure for  t_s_config 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_config ;
CREATE TABLE  t_s_config  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   userid  varchar(32) default NULL ,-- COMMENT '用户ID',
   code  varchar(100) default NULL ,-- COMMENT '编码',
   name  varchar(100) NOT NULL ,-- COMMENT '配置名称',
   content  text ,-- COMMENT '参数内容，  可以是 配置项，也可以是文件路径',
   note  text ,-- COMMENT '备注',
  PRIMARY KEY  ( id ),
  UNIQUE      ( id ) 
) ;

-- ----------------------------
-- Records of t_s_config
-- ----------------------------

-- ----------------------------
-- Table structure for  t_s_demo 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_demo ;
CREATE TABLE  t_s_demo  (
   id  varchar(255) NOT NULL,
   democode  text,
   demoorder   smallint default NULL,
   demotitle  varchar(200) default NULL,
   demourl  varchar(200) default NULL,
   demopid  varchar(255) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE ( id )
) ;

-- ----------------------------
-- Records of t_s_demo
-- ----------------------------
INSERT INTO  t_s_demo  VALUES ('1', '<div class=\"form\">\r\n   <label class=\"Validform_label\">\r\n     非空验证：\r\n    </label>\r\n    <input name=\"demotitle\" id=\"demotitle\" datatype=\"*\" errormsg=\"该字段不为空\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     URL验证：\r\n    </label>\r\n    <input name=\"demourl\" id=\"demourl\" datatype=\"url\" errormsg=\"必须是URL\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     至少选择2项：\r\n    </label>\r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite21\" type=\"checkbox\" value=\"1\" datatype=\"need2\" nullmsg=\"请选择您的爱好！\" />\r\n  \r\n     阅读\r\n  \r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite22\" type=\"checkbox\" value=\"2\" />\r\n    \r\n     音乐\r\n  \r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite23\" type=\"checkbox\" value=\"3\" />\r\n  \r\n     运动\r\n   \r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     邮箱：\r\n    </label>\r\n    <input name=\"demoorder\" id=\"demoorder\" datatype=\"e\" errormsg=\"邮箱非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     手机号：\r\n    </label>\r\n    <input name=\"phone\" id=\"phone\" datatype=\"m\" errormsg=\"手机号非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     金额：\r\n    </label>\r\n    <input name=\"money\" id=\"money\" datatype=\"d\" errormsg=\"金额非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     日期：\r\n    </label>\r\n    <input name=\"date\" id=\"date\" class=\"easyui-datebox\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     时间：\r\n    </label>\r\n    <input name=\"time\" id=\"time\" class=\"easyui-datebox\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>', null, '表单验证', null, null);

-- ----------------------------
-- Table structure for  t_s_depart 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_depart ;
CREATE TABLE  t_s_depart  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   departname  varchar(50) NOT NULL,
   description  text ,-- COMMENT '部门职能',
   parentdepartid  varchar(32) default NULL ,-- COMMENT '上级部门名称',
  PRIMARY KEY  ( id ),
  UNIQUE    ( id ) 
) ;

-- ----------------------------
-- Records of t_s_depart
-- ----------------------------
INSERT INTO  t_s_depart  VALUES ('150', '信息部', '12', null);
INSERT INTO  t_s_depart  VALUES ('152', '设计部', '', null);
INSERT INTO  t_s_depart  VALUES ('297e5a493d9f2cbc013d9f4450530001', '研发室', '研发技术难题', '152');

-- ----------------------------
-- Table structure for  t_s_document 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_document ;
CREATE TABLE  t_s_document  (
   id  varchar(32) NOT NULL,
   documenttitle  varchar(100) default NULL,
   pictureindex  BYTEA,
   documentstate   smallint default NULL,
   showhome   smallint default NULL,
   typeid  varchar(32) default NULL,
  PRIMARY KEY  ( id )
) ;

-- ----------------------------
-- Records of t_s_document
-- ----------------------------

-- ----------------------------
-- Table structure for  t_s_function 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_function ;
CREATE TABLE  t_s_function  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   iconid  varchar(32) default NULL ,-- COMMENT '图标ID',
   parentfunctionid  varchar(32) default NULL ,-- COMMENT '父权限ID',
   functionname  varchar(50) NOT NULL ,-- COMMENT '菜单名称',
   functionlevel   smallint default NULL ,-- COMMENT '菜单等级',
   functionurl  varchar(100) default NULL ,-- COMMENT '菜单地址',
   iconpath  text ,-- COMMENT '图标路径',
   functionorder  varchar(10) default NULL,
   functioniframe   smallint default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE     ( id ) 
) ;

-- ----------------------------
-- Records of t_s_function
-- ----------------------------
INSERT INTO  t_s_function  VALUES ('27', '402881763a019ea9013a01b009b80019', null, '系统管理', '0', '', '', '5', null);
INSERT INTO  t_s_function  VALUES ('28', '402881763a019ea9013a01af2d330015', '27', '用户管理', '1', 'userController.do?user', '', '5', null);
INSERT INTO  t_s_function  VALUES ('297e5a493d39e132013d39e316050001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', '单表模型', '1', 'jeecgNoteController.do?jeecgNote', null, '40', null);
INSERT INTO  t_s_function  VALUES ('297e5a493d8d8e26013d8d90de950001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', '一对多示例', '1', 'jeecgOrderMainController.do?jeecgOrderMain', null, '42', null);
INSERT INTO  t_s_function  VALUES ('297e5a493da1e22b013da1e75dec0001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', 'Excel导入导出', '1', 'jpPersonController.do?jpPerson', null, '45', null);
INSERT INTO  t_s_function  VALUES ('30', '402881763a019ea9013a01af2d330015', '27', '角色管理', '1', 'roleController.do?role', '', '6', null);
INSERT INTO  t_s_function  VALUES ('31', '402881763a019ea9013a01af2d330015', '27', '菜单管理', '1', 'functionController.do?function', '', '7', null);
INSERT INTO  t_s_function  VALUES ('40284b813d1becf1013d1bf26afd0001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', 'Demo示例', '1', 'jeecgDemoController.do?jeecgDemo', null, '37', null);
INSERT INTO  t_s_function  VALUES ('40284b813d1fa3a8013d1fab697b0001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', 'DEMO维护', '1', 'demoController.do?demoList', null, '38', null);
INSERT INTO  t_s_function  VALUES ('40284b813d1fb07f013d1fb2df360001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', 'DEMO分类', '1', 'demoController.do?demoIframe', null, '39', null);
INSERT INTO  t_s_function  VALUES ('402881e53c8589f7013c85afc393000b', '55', null, '统计查询', '0', '', null, '3', null);
INSERT INTO  t_s_function  VALUES ('402881e53c8adce5013c8b000beb0003', '402881763a019ea9013a01af5caf0016', '402881e53c8589f7013c85afc393000b', '用户分析', '1', 'logController.do?statisticTabs&isIframe', null, '17', '1');
INSERT INTO  t_s_function  VALUES ('402881e53c98df12013c98fc3b300002', '402881763a019ea9013a01af2d330015', null, '常用插件', '0', '', null, '8', null);
INSERT INTO  t_s_function  VALUES ('402881e53c996700013c9967f0320001', '402881763a019ea9013a01af2d330015', '402881e53c9a4b8e013c9a4c90960001', '数据监控', '1', 'dataSourceController.do?goDruid&isIframe', null, '11', null);
INSERT INTO  t_s_function  VALUES ('402881e53c9a4b8e013c9a4c90960001', '402881763a019ea9013a01af2d330015', null, '系统监控', '0', '', null, '11', null);
INSERT INTO  t_s_function  VALUES ('402881e93cb3f340013cb3f93e9a0070', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', '表单验证', '1', 'demoController.do?formTabs', null, '12', null);
INSERT INTO  t_s_function  VALUES ('4028abee3e6b42b2013e6b4a4af40001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', '字典标签', '1', 'demoController.do?dictSelect', null, '99', null);
INSERT INTO  t_s_function  VALUES ('49', '402881763a019ea9013a01af2d330015', '27', '图标管理', '1', 'iconController.do?icon', '', '18', null);
INSERT INTO  t_s_function  VALUES ('71', '402881763a019ea9013a01af2d330015', '402881e53c9a4b8e013c9a4c90960001', '系统日志', '1', 'logController.do?log', '', '21', null);
INSERT INTO  t_s_function  VALUES ('80', '402881763a019ea9013a01af2d330015', '27', '部门管理', '1', 'departController.do?depart', '', '22', null);
INSERT INTO  t_s_function  VALUES ('8a8a8a813e63303d013e6331c9ae0001', '402881763a019ea9013a01aeefad0014', '27', '字典分组', '1', 'systemController.do?typeGroupList', null, '6', null);
INSERT INTO  t_s_function  VALUES ('8a8a8a813e63303d013e63322c8e0003', '402881763a019ea9013a01aeefad0014', '27', '数据字典', '1', 'systemController.do?typeGroupTabs', null, '7', null);
INSERT INTO  t_s_function  VALUES ('8a8a8a813ea8afbf013ea8b6e4360002', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', '上传下载', '1', 'commonController.do?listTurn&turn=system/document/filesList', null, '100', null);
INSERT INTO  t_s_function  VALUES ('8a8a8a813ec14e83013ec157ea940001', '402881763a019ea9013a01af2d330015', '402881e53c98df12013c98fc3b300002', 'jdbc示例', '1', 'jeecgJdbcController.do?jeecgJdbc', null, '100', null);

-- ----------------------------
-- Table structure for  t_s_icon 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_icon ;
CREATE TABLE  t_s_icon  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   name  varchar(100) NOT NULL,
   type   smallint default NULL,
   path  text ,-- COMMENT '路径',
   content  BYTEA,
   iconclas  varchar(200) default NULL,
   extend  varchar(5) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of t_s_icon
-- ----------------------------
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01adcf29000f', '返回', '1', 'plug-in/accordion/images/back.png',  null, 'back', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01aeefad0014', '饼图', '1', 'plug-in/accordion/images/pie.png',  null, 'pie', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01af2d330015', '图片', '1', 'plug-in/accordion/images/pictures.png',  null, 'pictures', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01af5caf0016', '笔', '1', 'plug-in/accordion/images/pencil.png',  null, 'pencil', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01af94b70017', '地图', '1', 'plug-in/accordion/images/map.png',  null, 'map', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01b009b80019', '组', '1', 'plug-in/accordion/images/group_add.png',  null, 'group_add', 'png');
INSERT INTO  t_s_icon  VALUES ('402881763a019ea9013a01b043a4001a', '计算器', '1', 'plug-in/accordion/images/calculator.png',  null, 'calculator', 'png');
INSERT INTO  t_s_icon  VALUES ('55', 'folder.png', '1', 'plug-in/accordion/images/folder.png', null, 'folder', 'png');

-- ----------------------------
-- Table structure for  t_s_log 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_log ;
CREATE TABLE  t_s_log  (
   id  varchar(32) NOT NULL,
   userid  varchar(32) default NULL,
   loglevel   smallint default NULL,
   operatetime  date NOT NULL ,-- COMMENT '操作时间',
   operatetype   smallint default NULL ,-- COMMENT '操作类型：1登录、2退出、3插入、4删除、5更新、6打印、7上传、8其他',
   logcontent  text NOT NULL ,-- COMMENT '日志内容',
   note  text,
   broswer  varchar(100) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of t_s_log
-- ----------------------------
INSERT INTO  t_s_log  VALUES ('297e48083e3b229b013e3b232c480000', '40', '1', '2013-04-24 16:22:40', '1', '用户: admin登录成功', '169.254.200.136', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e48083e3b229b013e3b50ac0c0001', '40', '1', '2013-04-24 17:12:22', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533b59030000', '40', '1', '2013-03-10 15:37:11', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533c9925002c', null, '2', '2013-03-10 15:38:33', '1', '用户admin已退出', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533cbc4d002d', '40', '1', '2013-03-10 15:38:42', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d053c002e', '40', '4', '2013-03-10 15:39:00', '1', 'JeecgDemo例子: 12被删除 成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d0d4b002f', '40', '4', '2013-03-10 15:39:02', '1', 'JeecgDemo例子: 12被删除 成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d13460030', '40', '4', '2013-03-10 15:39:04', '1', 'JeecgDemo例子: 12被删除 成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d78590031', '40', '5', '2013-03-10 15:39:30', '1', '权限: 单表模型被更新成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d99010032', '40', '4', '2013-03-10 15:39:38', '1', '删除成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533d9edd0033', '40', '4', '2013-03-10 15:39:40', '1', '删除成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533da45b0034', '40', '4', '2013-03-10 15:39:41', '1', '删除成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533da9c90035', '40', '4', '2013-03-10 15:39:42', '1', '删除成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533decfd0037', '40', '3', '2013-03-10 15:40:00', '1', '添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533dfcac0038', '40', '5', '2013-03-10 15:40:04', '1', '更新成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533e9b7c003a', '40', '3', '2013-03-10 15:40:44', '1', 'JeecgDemo例子: 12被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533f3ccd003b', '40', '5', '2013-03-10 15:41:26', '1', '部门: 信息部被更新成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d533f6c2a003c', '40', '5', '2013-03-10 15:41:38', '1', '部门: 设计部被更新成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d533ad1013d534444660067', '40', '5', '2013-03-10 15:46:55', '1', '类型: 信息部流程被更新成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d5345a4013d5345faa20000', '40', '1', '2013-03-10 15:48:47', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8d8c36013d8d8c90970000', '40', '1', '2013-03-21 23:23:52', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8d8e26013d8d8eda680000', '40', '1', '2013-03-21 23:26:22', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8d8e26013d8d90dec40002', '40', '3', '2013-03-21 23:28:34', '1', '权限: 一对多实例被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8f7b2d013d8f7c19f40000', '40', '1', '2013-03-22 08:25:07', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8fa0fe1b0000', '40', '1', '2013-03-22 09:05:25', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8fa4590d0001', '40', '1', '2013-03-22 09:09:05', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8fb670180002', '40', '1', '2013-03-22 09:28:50', '1', '用户: admin登录成功', '169.254.218.201', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8ff128330003', '40', '1', '2013-03-22 10:32:59', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8ff3afa9000c', '40', '3', '2013-03-22 10:35:44', '1', '物品: 笔记本添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d8fa0ad013d8ff9324e000d', '40', '1', '2013-03-22 10:41:46', '1', '用户: admin登录成功', '169.254.218.201', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('297e5a493d912600013d9126d80c0000', '40', '1', '2013-03-22 16:11:14', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d925baf013d925cb9640000', '40', '1', '2013-03-22 21:49:43', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d92ac3d013d92acd11d0000', '40', '1', '2013-03-22 23:17:12', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d92b56c013d92b620d80000', '40', '1', '2013-03-22 23:27:22', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d92e253013d92e2ce670000', '40', '1', '2013-03-23 00:16:10', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d92e869013d92e8daed0000', '40', '1', '2013-03-23 00:22:46', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d92f00f013d92f08dc00000', '40', '1', '2013-03-23 00:31:11', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d95e654013d95eaa6eb0000', '40', '1', '2013-03-23 14:23:36', '1', '用户: admin登录成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d95e654013d961102de0004', '40', '3', '2013-03-23 15:05:30', '1', '流程参数: 主任审批被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d95e654013d9611319e0006', '40', '3', '2013-03-23 15:05:42', '1', '业务参数: 入职申请被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d95e654013d9617d3dc0008', '40', '3', '2013-03-23 15:12:56', '1', '权限: 入职申请被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297e5a493d95e654013d96183b8f000a', '40', '3', '2013-03-23 15:13:23', '1', '权限: 入职办理被添加成功', '169.254.218.201', 'Chrome');
INSERT INTO  t_s_log  VALUES ('297eba0d3e78b01b013e78bccd000000', '40', '1', '2013-05-06 15:27:19', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('402881e73d884efb013d885331e30008', '40', '3', '2013-03-20 23:03:06', '1', '物品: 555添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8861f3013d886223f90000', '40', '1', '2013-03-20 23:19:25', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8cd9d7013d8cda30aa0000', '40', '1', '2013-03-21 20:09:02', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8ceac8013d8ceb06e50000', '40', '1', '2013-03-21 20:27:25', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8cfa71013d8cfad2230000', '40', '1', '2013-03-21 20:44:40', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d0354013d8d03906d0000', '40', '1', '2013-03-21 20:54:13', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d0a56013d8d0a994a0000', '40', '1', '2013-03-21 21:01:54', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d139d013d8d14d3410000', '40', '1', '2013-03-21 21:13:05', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d139d013d8d16b0070003', '40', '3', '2013-03-21 21:15:07', '1', '物品: 55添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d1db6013d8d1ddf1b0000', '40', '1', '2013-03-21 21:22:57', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d1db6013d8d1e16ed0002', '40', '3', '2013-03-21 21:23:12', '1', '物品: 55添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d1db6013d8d1ea1930005', '40', '3', '2013-03-21 21:23:47', '1', '物品: 33添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d1fb4013d8d1fdf280000', '40', '1', '2013-03-21 21:25:09', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2274fc0000', '40', '1', '2013-03-21 21:27:58', '1', '用户: admin登录成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2378000002', '40', '3', '2013-03-21 21:29:04', '1', '权限: 采购审批被添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2442ba002f', '40', '5', '2013-03-21 21:29:56', '1', '权限: 采购审批被更新成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2472c90030', '40', '5', '2013-03-21 21:30:08', '1', '权限: 采购审批被更新成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d258fad003b', '40', '5', '2013-03-21 21:31:21', '1', '用户: admin更新成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d29dd7d003d', '40', '3', '2013-03-21 21:36:03', '1', '流程参数: 采购审批员审批被添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d29fc82003e', '40', '5', '2013-03-21 21:36:11', '1', '流程参数: 采购审批员审批被更新成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2afa77003f', '40', '5', '2013-03-21 21:37:16', '1', '流程参数: 采购审批员审批被更新成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d2bf30b0040', '40', '4', '2013-03-21 21:38:20', '1', '流程类别: 采购审批员审批被删除 成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e73d8d2242013d8d30ff5b0043', '40', '3', '2013-03-21 21:43:51', '1', '物品: 44添加成功', '192.168.1.103', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb269b4013cb26b1d890000', '40', '1', '2013-02-07 10:10:29', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb29ada013cb29f41700001', null, '3', '2013-02-07 11:07:26', '1', '权限: 上传下载被添加成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb29ada013cb29f73600002', null, '4', '2013-02-07 11:07:39', '1', '权限: 插件演示被删除成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb29ada013cb29fad880003', '40', '1', '2013-02-07 11:07:54', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb29ada013cb2d19aca002a', '40', '1', '2013-02-07 12:02:26', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb29ada013cb30584ac002c', '40', '4', '2013-02-07 12:59:08', '1', 'bg删除成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb319d9013cb31a6dfd0000', '40', '1', '2013-02-07 13:21:59', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb347bc013cb348fb8a0000', '40', '1', '2013-02-07 14:12:50', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb347bc013cb34bcc7d0002', '40', '4', '2013-02-07 14:15:54', '1', 'bg删除成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('402881e93cb347bc013cb34c91290003', '40', '1', '2013-02-07 14:16:45', '1', '用户: admin登录成功', '192.168.1.105', 'MSIE 9.0');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1521013eea1606fa0000', '40', '1', '2013-05-28 15:41:52', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1521013eea1684770001', '40', '5', '2013-05-28 15:42:24', '1', '用户申请成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1827013eea18e1a00000', '40', '1', '2013-05-28 15:44:59', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1827013eea1965e40001', '40', '5', '2013-05-28 15:45:33', '1', '权限: 代码测试被更新成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1827013eea19765f0002', null, '2', '2013-05-28 15:45:37', '1', '用户admin已退出', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1827013eea19a7a00003', '40', '1', '2013-05-28 15:45:49', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1827013eea1aaa6a0008', '40', '3', '2013-05-28 15:46:56', '1', '添加成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea1fa5013eea2019b60000', '40', '1', '2013-05-28 15:52:52', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea24e1013eea252c4b0000', '40', '1', '2013-05-28 15:58:24', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea27d0013eea2bc2f10000', '40', '1', '2013-05-28 16:05:36', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea340e013eea3458a20000', '40', '1', '2013-05-28 16:14:59', '1', '用户: admin登录成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a88cdae3eea340e013eea387f220001', '40', '5', '2013-05-28 16:19:31', '1', '用户申请成功', '10.8.77.46', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8908e33ec669da013ec66c179c0000', '40', '1', '2013-05-21 17:29:32', '1', '用户: admin登录成功', '10.9.136.99', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8908e33ec669da013ec66ef81f0001', '40', '5', '2013-05-21 17:32:41', '1', '权限: 数据字典标签被更新成功', '10.9.136.99', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8908e33ec669da013ec66f14320002', '40', '5', '2013-05-21 17:32:48', '1', '权限: jdbc例子被更新成功', '10.9.136.99', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9bd8e40000', '40', '1', '2013-05-29 12:46:31', '1', '用户: admin登录成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9cec960019', '40', '4', '2013-05-29 12:47:41', '1', '权限: 代码测试被删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9d4a18001b', '40', '3', '2013-05-29 12:48:05', '1', '权限: 12333被添加成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9d5fb3001c', '40', '4', '2013-05-29 12:48:11', '1', '权限: 12333被删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9d7260001d', null, '2', '2013-05-29 12:48:15', '1', '用户admin已退出', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9d8fcc001e', '40', '1', '2013-05-29 12:48:23', '1', '用户: admin登录成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9db93d001f', '40', '4', '2013-05-29 12:48:34', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9dc3300020', '40', '4', '2013-05-29 12:48:36', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9dcdfd0021', '40', '4', '2013-05-29 12:48:39', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9dd4470022', '40', '4', '2013-05-29 12:48:41', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9dddbd0023', '40', '4', '2013-05-29 12:48:43', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9de4540024', '40', '4', '2013-05-29 12:48:45', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9deb2a0025', '40', '4', '2013-05-29 12:48:46', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9df1640026', '40', '4', '2013-05-29 12:48:48', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9df8b70027', '40', '4', '2013-05-29 12:48:50', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e01fe0028', '40', '4', '2013-05-29 12:48:52', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e08570029', '40', '4', '2013-05-29 12:48:54', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e0d97002a', '40', '4', '2013-05-29 12:48:55', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e1315002b', '40', '4', '2013-05-29 12:48:57', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e1893002c', '40', '4', '2013-05-29 12:48:58', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e1e02002d', '40', '4', '2013-05-29 12:48:59', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e23af002e', '40', '4', '2013-05-29 12:49:01', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e2ac3002f', '40', '4', '2013-05-29 12:49:03', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e30030030', '40', '4', '2013-05-29 12:49:04', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e35240031', '40', '4', '2013-05-29 12:49:05', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e3b0f0032', '40', '4', '2013-05-29 12:49:07', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e409d0033', '40', '4', '2013-05-29 12:49:08', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e4ba90034', '40', '4', '2013-05-29 12:49:11', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e51f30035', '40', '4', '2013-05-29 12:49:13', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e581d0036', '40', '4', '2013-05-29 12:49:14', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e650d0037', '40', '4', '2013-05-29 12:49:18', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e6aca0038', '40', '4', '2013-05-29 12:49:19', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e70480039', '40', '4', '2013-05-29 12:49:20', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9e779b003a', '40', '4', '2013-05-29 12:49:22', '1', '删除成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eee992c013eee9f7bbd003b', null, '2', '2013-05-29 12:50:29', '1', '用户admin已退出', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eeeafca013eeeb131ad0000', '40', '1', '2013-05-29 13:09:50', '1', '用户: admin登录成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a890ffd3eeec84d013eeec923ae0000', '40', '1', '2013-05-29 13:35:59', '1', '用户: admin登录成功', '10.9.143.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f3f5b60000', '40', '1', '2013-05-21 19:57:57', '1', '用户: admin登录成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f53ee00001', '40', '4', '2013-05-21 19:59:21', '1', '555被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f546df0002', '40', '4', '2013-05-21 19:59:23', '1', '898989被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f551fb0003', '40', '4', '2013-05-21 19:59:26', '1', '555555555被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f55de20004', '40', '4', '2013-05-21 19:59:29', '1', '333333333被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f566da0005', '40', '4', '2013-05-21 19:59:31', '1', '222222222222被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a89dad23ec6f350013ec6f5710c0006', '40', '4', '2013-05-21 19:59:34', '1', '123123132被删除成功', '10.9.90.82', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e041037013e0410b4370000', '40', '1', '2013-04-13 23:43:23', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e067ebe013e0680447a0000', '40', '1', '2013-04-14 11:04:29', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e067ebe013e069704d10004', '40', '3', '2013-04-14 11:29:20', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e067ebe013e069725880006', '40', '5', '2013-04-14 11:29:28', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e069aac013e069b92f50000', '40', '1', '2013-04-14 11:34:19', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e06ac77013e06aeab240000', '40', '1', '2013-04-14 11:55:10', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e06ac77013e06af12e60002', '40', '3', '2013-04-14 11:55:37', '1', '角色: 1111111被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e06ac77013e06af46c70018', '40', '4', '2013-04-14 11:55:50', '1', '角色: 1111111被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e06ac77013e06ddb6f60019', '40', '1', '2013-04-14 12:46:33', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e06ac77013e06ed08d6001a', '40', '1', '2013-04-14 13:03:17', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e070ec1013e070fa7ff0000', '40', '1', '2013-04-14 13:41:06', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0adfc40000', '40', '1', '2013-04-15 22:13:13', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0bf7100002', '40', '3', '2013-04-15 22:14:25', '1', 'JeecgDemo例子: 12被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0c1b610003', '40', '5', '2013-04-15 22:14:34', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0c57ce0004', '40', '4', '2013-04-15 22:14:50', '1', 'JeecgDemo例子: 12被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0c5fae0005', '40', '4', '2013-04-15 22:14:52', '1', 'JeecgDemo例子: 12被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0c69040006', '40', '4', '2013-04-15 22:14:54', '1', 'JeecgDemo例子: 09090909被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e0e0a1d013e0e0c76fe0007', '40', '4', '2013-04-15 22:14:58', '1', 'JeecgDemo例子: 1221被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1337e8013e1338d92b0000', '40', '1', '2013-04-16 22:21:32', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d2146013e1d21c65b0000', '40', '1', '2013-04-18 20:32:32', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d2de7013e1d2e665a0000', '40', '1', '2013-04-18 20:46:20', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d422e013e1d4453250000', '40', '1', '2013-04-18 21:10:17', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d4a45013e1d50dc110000', '40', '1', '2013-04-18 21:23:58', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d51b2013e1d522cad0000', '40', '1', '2013-04-18 21:25:24', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e1d580b013e1d58b3640000', '40', '1', '2013-04-18 21:32:32', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3206f0013e320729780000', '40', '1', '2013-04-22 21:55:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3206f0013e320a1f680003', '40', '3', '2013-04-22 21:58:44', '1', '用户: 134添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3206f0013e320a30ec0005', '40', '5', '2013-04-22 21:58:48', '1', '用户: 134更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e37a045013e37a188ff0000', '40', '1', '2013-04-24 00:02:13', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3969b2013e396ac1270000', '40', '1', '2013-04-24 08:21:37', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3969b2013e396c7a6f0003', '40', '5', '2013-04-24 08:23:30', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3969b2013e396c84720004', '40', '4', '2013-04-24 08:23:33', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e399e86013e399ed6480000', '40', '1', '2013-04-24 09:18:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3af03a013e3af228300000', '40', '1', '2013-04-24 15:29:08', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3af7a8013e3af80c9c0000', '40', '1', '2013-04-24 15:35:34', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3afa94013e3afb03a50000', '40', '1', '2013-04-24 15:38:49', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e3b571a013e3b57c2a00000', '40', '1', '2013-04-24 17:20:07', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e44efcf013e44f246ff0000', '40', '1', '2013-04-26 14:05:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e44efcf013e44f291750001', '40', '4', '2013-04-26 14:05:47', '1', 'JeecgDemo例子: 123被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e450a9e013e450b0b340000', '40', '1', '2013-04-26 14:32:31', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e47144f013e47171aff0000', '40', '1', '2013-04-27 00:04:56', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e48f44c013e49009b110000', '40', '1', '2013-04-27 08:59:36', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e48f44c013e490769410001', '40', '1', '2013-04-27 09:07:02', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e48f44c013e490a36d80002', '40', '1', '2013-04-27 09:10:06', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e4953a7df0000', '40', '1', '2013-04-27 10:30:19', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e49544dc40004', '40', '3', '2013-04-27 10:31:01', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e4954642a0006', '40', '5', '2013-04-27 10:31:07', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e495ba6af0008', '40', '5', '2013-04-27 10:39:03', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e495c6ced000a', '40', '3', '2013-04-27 10:39:54', '1', 'JeecgDemo例子: 1221被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e49434f013e49601f66000c', '40', '3', '2013-04-27 10:43:56', '1', 'JeecgDemo例子: 123被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62aa0a370000', '40', '1', '2013-05-02 08:35:11', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e320c70002', '40', '3', '2013-05-02 09:37:32', '1', '操作: 录入被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e355e10004', '40', '3', '2013-05-02 09:37:45', '1', '操作: 编辑被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e380f80006', '40', '3', '2013-05-02 09:37:56', '1', '操作: 删除被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e3f80c0008', '40', '3', '2013-05-02 09:38:27', '1', '操作: 审核被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e4d85a0009', null, '2', '2013-05-02 09:39:24', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e4f865000a', '40', '1', '2013-05-02 09:39:33', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e7f8e4000b', null, '2', '2013-05-02 09:42:49', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e62a855013e62e8170a000c', '40', '1', '2013-05-02 09:42:57', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630af5fb0000', '40', '1', '2013-05-02 10:21:02', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630ba0a30001', '40', '4', '2013-05-02 10:21:46', '1', '用户：134删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630ba8630002', '40', '4', '2013-05-02 10:21:48', '1', '用户：123删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630c1f1a0005', '40', '3', '2013-05-02 10:22:18', '1', '用户: scott添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630c7b720012', null, '2', '2013-05-02 10:22:42', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63096e013e630cb2ee0013', '8a8a8a813e63096e013e630c1df10003', '1', '2013-05-02 10:22:56', '1', '用户: scott登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6330af450000', '40', '1', '2013-05-02 11:02:15', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6331ca4a0002', '40', '3', '2013-05-02 11:03:27', '1', '权限: 字典分组被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e63322d4a0004', '40', '3', '2013-05-02 11:03:52', '1', '权限: 数据字典被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6332472b0005', null, '2', '2013-05-02 11:03:59', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e63326e5a0006', '40', '1', '2013-05-02 11:04:09', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e633308a6001d', null, '2', '2013-05-02 11:04:49', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6333265f001e', '40', '1', '2013-05-02 11:04:56', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e63410bee001f', '40', '5', '2013-05-02 11:20:07', '1', '类型: 男性被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e63411dd00020', '40', '5', '2013-05-02 11:20:11', '1', '类型: 女性被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e634144e00022', '40', '3', '2013-05-02 11:20:21', '1', 'JeecgDemo例子: 11111111111111被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6341e40e0023', '40', '5', '2013-05-02 11:21:02', '1', '类型: 男性被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e6341fd140024', '40', '5', '2013-05-02 11:21:09', '1', '类型: 女性被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e63303d013e634234be0025', '40', '5', '2013-05-02 11:21:23', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e690b6c013e690beb970000', '40', '1', '2013-05-03 14:19:49', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e692d75013e692e21860000', '40', '1', '2013-05-03 14:57:11', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e692d75013e6931be450001', '40', '1', '2013-05-03 15:01:07', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e692d75013e6934ba200002', '40', '1', '2013-05-03 15:04:23', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e69a589013e69a6a2e40000', '40', '1', '2013-05-03 17:08:48', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6a6cb6013e6a6d66ae0000', '40', '1', '2013-05-03 20:45:54', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6a6cb6013e6a6e344f0001', '40', '5', '2013-05-03 20:46:47', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6a6cb6013e6a729b810002', '40', '5', '2013-05-03 20:51:35', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6d6b90013e6d6c3d780000', '40', '1', '2013-05-04 10:43:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f21c20c0000', '40', '1', '2013-05-04 18:41:23', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f22b2680018', null, '2', '2013-05-04 18:42:25', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f22d3f90019', '40', '1', '2013-05-04 18:42:33', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f25a0d5001a', '40', '5', '2013-05-04 18:45:37', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f25c535001b', '40', '5', '2013-05-04 18:45:46', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e6f2090013e6f25ecf1001d', '40', '3', '2013-05-04 18:45:56', '1', 'JeecgDemo例子: 12被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e77c244013e77c2c39a0000', '40', '1', '2013-05-06 10:54:12', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e77c244013e77c2f3650001', '40', '4', '2013-05-06 10:54:25', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e77c244013e77c2fb060002', '40', '4', '2013-05-06 10:54:27', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e77c244013e77c5e7ec0008', '40', '3', '2013-05-06 10:57:38', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e7a7b81013e7a7ccee20000', '40', '1', '2013-05-06 23:36:39', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e837b67013e837c45d50000', '40', '1', '2013-05-08 17:32:39', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e837b67013e837e01500001', '40', '1', '2013-05-08 17:34:33', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e837fa3013e838bbffe0000', '40', '1', '2013-05-08 17:49:34', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813e97264f013e9727ce350000', '40', '1', '2013-05-12 13:12:48', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea35a1a013ea35bdcd00000', '40', '1', '2013-05-14 22:05:06', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea8afbf013ea8b058dc0000', '40', '1', '2013-05-15 22:55:29', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea8afbf013ea8b6e4e20003', '40', '3', '2013-05-15 23:02:38', '1', '权限: 上传Demo被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea8afbf013ea8b7535b001c', null, '2', '2013-05-15 23:03:06', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea8afbf013ea8b76f8e001d', '40', '1', '2013-05-15 23:03:14', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ea8afbf013ea8b7ae9b001e', '40', '4', '2013-05-15 23:03:30', '1', '12222被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eade184013eade220130000', '40', '1', '2013-05-16 23:07:57', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6a410013eb6a4d8ac0000', '40', '1', '2013-05-18 15:57:36', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6a410013eb6a86a4f0001', '40', '5', '2013-05-18 16:01:30', '1', '部门: 121299被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6a410013eb6a89ae40003', '40', '3', '2013-05-18 16:01:43', '1', '部门: 8888被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6a410013eb6a8aa940004', '40', '4', '2013-05-18 16:01:47', '1', '部门: 8888被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6ae9b013eb6b192e90000', '40', '1', '2013-05-18 16:11:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6b46a013eb6b500c80000', '40', '1', '2013-05-18 16:15:15', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6b7af013eb6b838920000', '40', '1', '2013-05-18 16:18:46', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6bbf9013eb6bcb60c0000', '40', '1', '2013-05-18 16:23:40', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6bdec013eb6be565e0000', '40', '1', '2013-05-18 16:25:27', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6c388013eb6c450a40000', '40', '1', '2013-05-18 16:31:59', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6c4e4013eb6c535570000', '40', '1', '2013-05-18 16:32:57', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6c4e4013eb6c7f31f0001', '40', '5', '2013-05-18 16:35:57', '1', '部门: 信息部被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6c4e4013eb6c81f310002', '40', '5', '2013-05-18 16:36:08', '1', '部门: 研发室被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb6d09d013eb6d110120000', '40', '1', '2013-05-18 16:45:54', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eb746f8013eb774993e0000', '40', '1', '2013-05-18 19:44:32', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ebf7076013ebf72c1b60000', '40', '1', '2013-05-20 08:59:29', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec1077d013ec10802b40000', '40', '1', '2013-05-20 16:22:07', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec150af040000', '40', '1', '2013-05-20 17:41:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec157eb200002', '40', '3', '2013-05-20 17:49:24', '1', '权限: jdbc例子被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec1587d1f001b', '40', '4', '2013-05-20 17:50:02', '1', '权限: 上传下载被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec158bc0d001c', '40', '5', '2013-05-20 17:50:18', '1', '权限: 上传下载被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec158c861001d', null, '2', '2013-05-20 17:50:21', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec158e474001e', '40', '1', '2013-05-20 17:50:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec1598037001f', null, '2', '2013-05-20 17:51:08', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec159b05f0020', '40', '1', '2013-05-20 17:51:20', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15a6a780021', '40', '5', '2013-05-20 17:52:08', '1', '权限: jdbc例子被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15aab0b0022', null, '2', '2013-05-20 17:52:24', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15ac6920023', '40', '1', '2013-05-20 17:52:31', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15bd4e50024', '40', '5', '2013-05-20 17:53:41', '1', '权限: jdbc例子被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15be05f0025', null, '2', '2013-05-20 17:53:44', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec14e83013ec15bfd4d0026', '40', '1', '2013-05-20 17:53:51', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec15fed013ec16075480000', '40', '1', '2013-05-20 17:58:44', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec162a2013ec1631b140000', '40', '1', '2013-05-20 18:01:37', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec260fe013ec26352b10000', '40', '1', '2013-05-20 22:41:29', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec276b3013ec277dcb00000', '40', '1', '2013-05-20 23:03:55', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec29a08013ec29ac70b0000', '40', '1', '2013-05-20 23:42:03', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec29a08013ec2a28bef0004', '40', '1', '2013-05-20 23:50:32', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ec7d5cd013ec7d6875c0000', '40', '1', '2013-05-22 00:05:25', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3f238e80000', '40', '1', '2013-05-24 08:31:06', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3f6ad590001', '40', '1', '2013-05-24 08:35:58', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3fc1a050003', '40', '5', '2013-05-24 08:41:54', '1', '用户: cgy更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3fc80020005', '40', '5', '2013-05-24 08:42:20', '1', '用户: cgy更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3fdcf950007', '40', '3', '2013-05-24 08:43:46', '1', '角色: 12被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3fe25c30021', '40', '3', '2013-05-24 08:44:08', '1', '类型分组: 123被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed3fed6080023', '40', '3', '2013-05-24 08:44:53', '1', '类型: 2222被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40213030025', '40', '3', '2013-05-24 08:48:25', '1', '权限: 1234被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed4022fe20026', '40', '5', '2013-05-24 08:48:33', '1', '权限: 12345被更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40264300028', '40', '3', '2013-05-24 08:48:46', '1', '权限: 222222被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40276dd0029', '40', '4', '2013-05-24 08:48:51', '1', '权限: 12345被删除成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed4085b69002c', '40', '3', '2013-05-24 08:55:17', '1', 'JeecgDemo例子: 12被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed408f613002d', '40', '5', '2013-05-24 08:55:57', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40921f5002f', '40', '3', '2013-05-24 08:56:08', '1', 'JeecgDemo例子: 888被添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed4093a6f0030', '40', '4', '2013-05-24 08:56:14', '1', 'JeecgDemo例子: 123被删除 成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40947210031', '40', '4', '2013-05-24 08:56:17', '1', 'JeecgDemo例子: 11111111111111被删除 成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40f76bf0033', '40', '3', '2013-05-24 09:03:03', '1', '添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed40f899b0034', '40', '5', '2013-05-24 09:03:08', '1', '更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed410b6ff003a', '40', '3', '2013-05-24 09:04:25', '1', '添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed415cf13003f', '40', '4', '2013-05-24 09:09:59', '1', '删除成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed415d9250040', '40', '4', '2013-05-24 09:10:01', '1', '删除成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed419487b0045', '40', '1', '2013-05-24 09:13:46', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed3f193013ed41fab3f004b', '40', '1', '2013-05-24 09:20:45', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed59dad400000', '40', '1', '2013-05-24 16:18:00', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed59e38c70001', '40', '4', '2013-05-24 16:18:36', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed59e43c30002', '40', '4', '2013-05-24 16:18:39', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed59e4cdc0003', '40', '4', '2013-05-24 16:18:41', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed59e7f650004', '40', '4', '2013-05-24 16:18:54', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed5a018c20006', '40', '3', '2013-05-24 16:20:39', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed5a03bfa0007', '40', '4', '2013-05-24 16:20:48', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed5a452f90008', '40', '4', '2013-05-24 16:25:16', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed5a47f770009', '40', '4', '2013-05-24 16:25:27', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed59c7b013ed5a6cf05000a', '40', '4', '2013-05-24 16:27:59', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a819670000', '40', '1', '2013-05-24 16:29:23', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a874e50002', '40', '3', '2013-05-24 16:29:47', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a88ec60004', '40', '3', '2013-05-24 16:29:53', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a8a9430006', '40', '3', '2013-05-24 16:30:00', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a8cd270008', '40', '3', '2013-05-24 16:30:09', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5a7ce013ed5a8e7e20009', '40', '4', '2013-05-24 16:30:16', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5adee013ed5ae2ca40000', '40', '1', '2013-05-24 16:36:01', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5adee013ed5ae79f90002', '40', '3', '2013-05-24 16:36:21', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5adee013ed5ae99870003', '40', '4', '2013-05-24 16:36:29', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b4ca2f0000', '40', '1', '2013-05-24 16:43:15', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b5114a0002', '40', '3', '2013-05-24 16:43:33', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b524070003', '40', '4', '2013-05-24 16:43:38', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b5715b0005', '40', '3', '2013-05-24 16:43:58', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b62c3f0006', '40', '4', '2013-05-24 16:44:46', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b7ac190008', '40', '3', '2013-05-24 16:46:24', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b47e013ed5b7cb0b0009', '40', '4', '2013-05-24 16:46:32', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b9bb013ed5ba19600000', '40', '1', '2013-05-24 16:49:03', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b9bb013ed5ba5bd70002', '40', '3', '2013-05-24 16:49:20', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b9bb013ed5ba8c9c0003', '40', '4', '2013-05-24 16:49:32', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b9bb013ed5bae3280005', '40', '3', '2013-05-24 16:49:54', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5b9bb013ed5bb7d450006', '40', '4', '2013-05-24 16:50:34', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5bcf9013ed5bd3cb80000', '40', '1', '2013-05-24 16:52:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5bcf9013ed5bd8ceb0002', '40', '3', '2013-05-24 16:52:49', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5bcf9013ed5bdd7620003', '40', '4', '2013-05-24 16:53:08', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5bcf9013ed5bf356b0005', '40', '3', '2013-05-24 16:54:38', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5bcf9013ed5bf5f880006', '40', '4', '2013-05-24 16:54:48', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c35bcc0000', '40', '1', '2013-05-24 16:59:10', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c3bda30002', '40', '3', '2013-05-24 16:59:35', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c40ccc0003', '40', '4', '2013-05-24 16:59:55', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c55df50005', '40', '3', '2013-05-24 17:01:21', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c585910007', '40', '3', '2013-05-24 17:01:31', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c5b2ac0009', '40', '3', '2013-05-24 17:01:43', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c5c634000a', '40', '4', '2013-05-24 17:01:48', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c72835000c', '40', '3', '2013-05-24 17:03:19', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c73322000d', '40', '4', '2013-05-24 17:03:21', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c73bcd000e', '40', '4', '2013-05-24 17:03:24', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c7561b000f', '40', '4', '2013-05-24 17:03:30', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c7b5040011', '40', '3', '2013-05-24 17:03:55', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5c892f10012', '40', '4', '2013-05-24 17:04:51', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5cb66160014', '40', '3', '2013-05-24 17:07:57', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5cb8b320015', '40', '4', '2013-05-24 17:08:06', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5cc4c6e0017', '40', '3', '2013-05-24 17:08:56', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed5c31c013ed5cc60060018', '40', '4', '2013-05-24 17:09:01', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed6fedff40000', '40', '1', '2013-05-24 22:43:47', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed70037e30002', '40', '3', '2013-05-24 22:45:15', '1', '权限: 代码测试被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed7008b90001c', null, '2', '2013-05-24 22:45:37', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed700b03f001d', '40', '1', '2013-05-24 22:45:46', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed700bd20001e', '40', '1', '2013-05-24 22:45:49', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed701111c0020', '40', '3', '2013-05-24 22:46:11', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed7011f930021', '40', '5', '2013-05-24 22:46:15', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed6fe6d013ed70127c10022', '40', '4', '2013-05-24 22:46:17', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed73082bd0000', '40', '1', '2013-05-24 23:38:00', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed730ad480001', '40', '4', '2013-05-24 23:38:11', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed730f4630003', '40', '3', '2013-05-24 23:38:29', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed7356d970005', '40', '3', '2013-05-24 23:43:23', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed735b1b40006', '40', '1', '2013-05-24 23:43:40', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed735e6900007', '40', '5', '2013-05-24 23:43:54', '1', '更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed73636e20009', '40', '3', '2013-05-24 23:44:14', '1', '添加成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed72ff9013ed737929a000a', '40', '5', '2013-05-24 23:45:43', '1', '更新成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed98aa8013ed98b339e0000', '40', '1', '2013-05-25 10:36:18', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed999e7013ed99a77f20000', '40', '1', '2013-05-25 10:52:59', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed999e7013ed99b03590002', '40', '3', '2013-05-25 10:53:34', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed9aa76013ed9ac73850000', '40', '1', '2013-05-25 11:12:37', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed9b4bd013ed9b51d2c0000', '40', '1', '2013-05-25 11:22:05', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ed9b4bd013ed9b55bac0002', '40', '3', '2013-05-25 11:22:21', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edbaad0013edbad44960000', '40', '1', '2013-05-25 20:32:45', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea721c20000', '40', '1', '2013-05-26 10:24:55', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea7ca180001', '40', '5', '2013-05-26 10:25:38', '1', '权限: 代码测试被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea7f1660003', '40', '5', '2013-05-26 10:25:48', '1', '用户: cgy更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea85a710004', null, '2', '2013-05-26 10:26:15', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea877210005', '40', '1', '2013-05-26 10:26:22', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edea696013edea93c260006', '40', '4', '2013-05-26 10:27:13', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edeb3dd013edeb476990000', '40', '1', '2013-05-26 10:39:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edebf47013edebfb5810000', '40', '1', '2013-05-26 10:51:45', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edec191013edec1ef160000', '40', '1', '2013-05-26 10:54:11', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edec380013edec3dad90000', null, '2', '2013-05-26 10:56:17', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edec380013edec403ec0001', '40', '1', '2013-05-26 10:56:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edec560013edec5d2340000', '40', '1', '2013-05-26 10:58:26', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eded01e013eded0912e0000', '40', '1', '2013-05-26 11:10:10', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eded51c013eded58d1f0000', '40', '1', '2013-05-26 11:15:37', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eded8ad013eded915c80000', '40', '1', '2013-05-26 11:19:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edee13a013edee19f250000', '40', '1', '2013-05-26 11:28:48', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edeea06013edeea78770000', '40', '1', '2013-05-26 11:38:28', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edeee60013edeeeda6a0000', '40', '1', '2013-05-26 11:43:15', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edeee60013edeefe13b0007', '40', '3', '2013-05-26 11:44:22', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edefdc8013edefe1e400000', '40', '1', '2013-05-26 11:59:55', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf004e013edf00b8150000', '40', '1', '2013-05-26 12:02:46', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf898e013edf89f68b0000', '40', '1', '2013-05-26 14:32:40', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf898e013edf8b2bee0006', '40', '3', '2013-05-26 14:34:00', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf937e013edf93e16c0000', '40', '1', '2013-05-26 14:43:30', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf97d0013edf985c650000', '40', '1', '2013-05-26 14:48:24', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf9dbd013edf9e0fa00000', null, '2', '2013-05-26 14:54:37', '1', '用户admin已退出', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf9dbd013edf9e38d30001', '40', '1', '2013-05-26 14:54:48', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edf9f7b013edf9fda4e0000', '40', '1', '2013-05-26 14:56:35', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edfa28b013edfa2ee160000', '40', '1', '2013-05-26 14:59:57', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edfa749013edfa7a2fb0000', '40', '1', '2013-05-26 15:05:05', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edfb30a013edfb3652c0000', '40', '1', '2013-05-26 15:17:56', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813edfb30a013edfb4456a0006', '40', '3', '2013-05-26 15:18:53', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0addf013ee0ae91480000', '40', '1', '2013-05-26 19:52:16', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0d4fb013ee0d5505a0000', '40', '1', '2013-05-26 20:34:36', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0d4fb013ee0d5ae290003', '40', '3', '2013-05-26 20:35:00', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0d4fb013ee0d5f3500004', '40', '4', '2013-05-26 20:35:17', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0d4fb013ee0d607170005', '40', '4', '2013-05-26 20:35:23', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee0d4fb013ee0d6180f0006', '40', '4', '2013-05-26 20:35:27', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee37e07013ee37e797c0000', '40', '1', '2013-05-27 08:58:36', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee37e07013ee37f5a280005', '40', '3', '2013-05-27 08:59:34', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee37e07013ee37fc6bd0009', '40', '5', '2013-05-27 09:00:02', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee8845e013ee886375a0000', '40', '1', '2013-05-28 08:25:10', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee8845e013ee886b62f0001', '40', '1', '2013-05-28 08:25:42', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee8845e013ee887a5320002', '40', '1', '2013-05-28 08:26:43', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee8845e013ee88ae9320003', '40', '1', '2013-05-28 08:30:17', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9ead2013ee9eb1ac10000', '40', '1', '2013-05-28 14:54:59', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9f03c013ee9f076080000', '40', '1', '2013-05-28 15:00:50', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9f398013ee9f452020000', '40', '1', '2013-05-28 15:05:03', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9f7c6013ee9f808050000', '40', '1', '2013-05-28 15:09:06', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9f7c6013ee9f8ac340001', '40', '5', '2013-05-28 15:09:48', '1', '用户申请成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ee9f7c6013ee9f941ae0002', '40', '5', '2013-05-28 15:10:26', '1', '用户申请成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea42ce013eea439dd00000', '40', '1', '2013-05-28 16:31:39', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea47a4013eea47fd320000', '40', '1', '2013-05-28 16:36:26', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea4ad2013eea4b327c0000', '40', '1', '2013-05-28 16:39:56', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea50cb013eea5144710000', null, '2', '2013-05-28 16:46:34', '1', '用户admin已退出', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea50cb013eea515e900001', '40', '1', '2013-05-28 16:46:41', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eea529a013eea52f54d0000', '40', '1', '2013-05-28 16:48:25', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eeb7852013eeb795f2d0000', '40', '1', '2013-05-28 22:10:00', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eeba0b6013eeba209550000', '40', '1', '2013-05-28 22:54:25', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eeba87b013eebaa5a8b0000', '40', '1', '2013-05-28 23:03:30', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eebb378013eebb581280000', '40', '1', '2013-05-28 23:15:40', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eebe76f013eebe975440000', '40', '1', '2013-05-29 00:12:25', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedc2e4013eedc382ef0000', '40', '1', '2013-05-29 08:50:13', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedc7a4013eedc856970000', '40', '1', '2013-05-29 08:55:29', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedc7a4013eedcccd890001', '40', '1', '2013-05-29 09:00:22', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedd273013eedd2e0390000', '40', '1', '2013-05-29 09:07:00', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedd583013eedd5f0a50000', '40', '1', '2013-05-29 09:10:21', '1', '用户: admin登录成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedd583013eedd69aa10001', '40', '4', '2013-05-29 09:11:04', '1', '类型分组: 123被删除 成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedd583013eedd763fc0003', '40', '3', '2013-05-29 09:11:56', '1', '类型分组: 测试分类被添加成功', '10.10.10.1', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eedd583013eedd7bece0004', '40', '1', '2013-05-29 09:12:19', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee0764500000', '40', '1', '2013-05-29 10:04:21', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee09d2050002', '40', '3', '2013-05-29 10:07:01', '1', '类型分组: 222被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee13f1340003', '40', '1', '2013-05-29 10:18:04', '1', '用户: admin登录成功', '10.10.10.1', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee2bab0f0004', '40', '4', '2013-05-29 10:43:59', '1', '类型分组: 222被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee2bbca30005', '40', '4', '2013-05-29 10:44:03', '1', '类型分组: 测试分类被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee2c15040006', '40', '4', '2013-05-29 10:44:26', '1', '类型分组: 流程类型被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813eee05da013eee2c31e20007', '40', '4', '2013-05-29 10:44:33', '1', '类型分组: 请假类型被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47d13930000', '40', '1', '2013-05-30 16:10:37', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47d6c420001', '40', '4', '2013-05-30 16:11:00', '1', '角色: 12被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47d98340002', '40', '4', '2013-05-30 16:11:11', '1', '图标: 2222222被删除成功。', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47dbdfc0003', '40', '4', '2013-05-30 16:11:21', '1', 'JeecgDemo例子: 12被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47dc9180004', '40', '4', '2013-05-30 16:11:24', '1', 'JeecgDemo例子: 888被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47dd57c0005', '40', '4', '2013-05-30 16:11:27', '1', 'JeecgDemo例子: 12被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47dde070006', '40', '4', '2013-05-30 16:11:29', '1', 'JeecgDemo例子: 123被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47de7db0007', '40', '4', '2013-05-30 16:11:32', '1', 'JeecgDemo例子: 88888被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47e02d50008', '40', '4', '2013-05-30 16:11:39', '1', 'JeecgDemo例子: 12被删除 成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47e50590009', '40', '5', '2013-05-30 16:11:58', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47e8062000a', '40', '5', '2013-05-30 16:12:11', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47eb26e000b', '40', '4', '2013-05-30 16:12:24', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47ebc42000c', '40', '4', '2013-05-30 16:12:26', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47ec412000d', '40', '4', '2013-05-30 16:12:28', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47eceb1000e', '40', '4', '2013-05-30 16:12:31', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47edc6c000f', '40', '4', '2013-05-30 16:12:34', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47f9cde0014', '40', '5', '2013-05-30 16:13:24', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47fbf4b0019', '40', '5', '2013-05-30 16:13:32', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef47fe205001c', '40', '5', '2013-05-30 16:13:41', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef48065bc0021', '40', '5', '2013-05-30 16:14:15', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef48071e10024', '40', '5', '2013-05-30 16:14:18', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4807d8a0027', '40', '5', '2013-05-30 16:14:21', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef480ce590029', '40', '3', '2013-05-30 16:14:42', '1', '添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef480db0b002a', '40', '5', '2013-05-30 16:14:45', '1', '更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef480e750002b', '40', '4', '2013-05-30 16:14:48', '1', '删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef480fc20002c', '40', '4', '2013-05-30 16:14:53', '1', '2222被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4810392002d', '40', '4', '2013-05-30 16:14:55', '1', '55555555被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4810c0e002e', '40', '4', '2013-05-30 16:14:58', '1', '123123123被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4811323002f', '40', '4', '2013-05-30 16:14:59', '1', '11111111被删除成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4823a0f0031', '40', '3', '2013-05-30 16:16:15', '1', 'JeecgDemo例子: 张代浩被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4825d650032', '40', '5', '2013-05-30 16:16:24', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef48328e30033', '40', '5', '2013-05-30 16:17:16', '1', 'JeecgDemo例子: null被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef4838ced0034', '40', '5', '2013-05-30 16:17:42', '1', '用户申请成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef484719f0036', '40', '3', '2013-05-30 16:18:40', '1', 'JeecgDemo例子: zhangdaiscott被添加成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef47c6e013ef485af110037', '40', '5', '2013-05-30 16:20:01', '1', '部门: 研发室被更新成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a8a8a813ef48776013ef48838ba0000', '40', '1', '2013-05-30 16:22:48', '1', '用户: admin登录成功', '10.10.10.1', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a9417273ec6331a013ec63398890000', '40', '1', '2013-05-21 16:27:50', '1', '用户: admin登录成功', '10.20.151.167', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef4020f013ef40329bd0000', '40', '1', '2013-05-30 13:57:28', '1', '用户: admin登录成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef411a91a0000', '40', '1', '2013-05-30 14:13:18', '1', '用户: admin登录成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef412d8530002', '40', '3', '2013-05-30 14:14:35', '1', 'Demo例子: 1111被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef415047a0004', '40', '3', '2013-05-30 14:16:58', '1', 'Demo例子: 222被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41543a60006', '40', '3', '2013-05-30 14:17:14', '1', 'Demo例子: 2222被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41568a30008', '40', '3', '2013-05-30 14:17:23', '1', 'Demo例子: 2222被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41a4e6b0009', '40', '5', '2013-05-30 14:22:44', '1', 'Demo例子: 2222被更新成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41c709f000b', '40', '3', '2013-05-30 14:25:04', '1', 'Demo例子: 1111被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41d9e81000d', '40', '3', '2013-05-30 14:26:21', '1', 'Demo例子: 12222被添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41f0ab30011', '40', '3', '2013-05-30 14:27:55', '1', '用户: 12112添加成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8a95e01b3ef41114013ef41f1bab0012', '40', '4', '2013-05-30 14:27:59', '1', '用户：12112删除成功', '10.21.96.155', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa0f28e3ec1a064013ec1a0df270000', '40', '1', '2013-05-20 19:09:05', '1', '用户: admin登录成功', '10.32.114.14', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa0f28e3ec1a064013ec1a11f1e0001', '40', '5', '2013-05-20 19:09:22', '1', '更新成功', '10.32.114.14', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa0f28e3ec1a288013ec1a30ce40000', '40', '1', '2013-05-20 19:11:28', '1', '用户: admin登录成功', '10.32.114.14', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa34e4c3ebf65c4013ebf692c480000', '40', '1', '2013-05-20 08:49:01', '1', '用户: admin登录成功', '10.35.206.204', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa4c7c43e9d15d9013e9d168d380000', '40', '1', '2013-05-13 16:51:40', '1', '用户: admin登录成功', '10.36.71.68', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa640ee3eede604013eede66a7b0000', '40', '1', '2013-05-29 09:28:20', '1', '用户: admin登录成功', '10.38.192.110', 'MSIE 8.0');
INSERT INTO  t_s_log  VALUES ('8aa6cad73e9ddad8013e9de5cc940000', '40', '1', '2013-05-13 20:38:03', '1', '用户: admin登录成功', '10.38.74.87', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa6cad73e9ddad8013e9de6e0e20001', '40', '5', '2013-05-13 20:39:13', '1', '权限: 数据字典标签被更新成功', '10.38.74.87', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa6cad73e9ddad8013e9de838060009', '40', '3', '2013-05-13 20:40:41', '1', '添加成功', '10.38.74.87', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa6cad73e9ddad8013e9de9d51c000a', '40', '4', '2013-05-13 20:42:27', '1', '删除成功', '10.38.74.87', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa6cad73e9ddad8013e9dea1a24000e', '40', '5', '2013-05-13 20:42:45', '1', '更新成功', '10.38.74.87', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8aa7ec763eeade63013eeae03f970000', '40', '1', '2013-05-28 19:22:45', '1', '用户: admin登录成功', '10.39.108.246', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9bd4c013ee9beea320000', '40', '1', '2013-05-28 14:06:43', '1', '用户: admin登录成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9bd4c013ee9bfa45a0004', '40', '3', '2013-05-28 14:07:30', '1', '添加成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9bd4c013ee9c2e0f7000b', '40', '3', '2013-05-28 14:11:03', '1', '添加成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9bd4c013ee9c39bdb0011', '40', '3', '2013-05-28 14:11:50', '1', '添加成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9bd4c013ee9c58a5c0012', '40', '4', '2013-05-28 14:13:57', '1', '删除成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9d620013ee9d69edc0000', '40', '1', '2013-05-28 14:32:36', '1', '用户: admin登录成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9d620013ee9d7269a0002', '40', '3', '2013-05-28 14:33:11', '1', '添加成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9d620013ee9d7400d0003', '40', '5', '2013-05-28 14:33:18', '1', '更新成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9d620013ee9d74ea40004', '40', '4', '2013-05-28 14:33:21', '1', '删除成功', '10.49.65.149', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9df5a013ee9e0b7260000', '40', '1', '2013-05-28 14:43:38', '1', '用户: admin登录成功', '10.49.65.149', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9df5a013ee9e154dd0006', '40', '3', '2013-05-28 14:44:18', '1', '添加成功', '10.49.65.149', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9df5a013ee9e1c2f80008', '40', '3', '2013-05-28 14:44:46', '1', 'JeecgDemo例子: 123被添加成功', '10.49.65.149', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8ab1c1153ee9df5a013ee9e4d067000a', '40', '3', '2013-05-28 14:48:07', '1', 'JeecgDemo例子: 12被添加成功', '10.49.65.149', 'Firefox');
INSERT INTO  t_s_log  VALUES ('8ab1e1fd3eeef911013eeefa6a6d0000', '40', '1', '2013-05-29 14:29:48', '1', '用户: admin登录成功', '10.49.97.125', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab595853ec9e326013ec9e3eea80000', '40', '1', '2013-05-22 09:39:18', '1', '用户: admin登录成功', '10.53.21.5', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab76f963ed5db16013ed5dccac80000', '40', '1', '2013-05-24 17:26:56', '1', '用户: admin登录成功', '10.55.239.22', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab76f963ed5db16013ed5dd4fa80002', '40', '3', '2013-05-24 17:27:30', '1', '添加成功', '10.55.239.22', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab76f963ed5db16013ed5ddc4990004', '40', '3', '2013-05-24 17:28:00', '1', '添加成功', '10.55.239.22', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab76f963ed5db16013ed5dde60c0005', '40', '4', '2013-05-24 17:28:09', '1', '删除成功', '10.55.239.22', 'Chrome');
INSERT INTO  t_s_log  VALUES ('8ab76f963ed5db16013ed5ddf1a50006', '40', '4', '2013-05-24 17:28:12', '1', '删除成功', '10.55.239.22', 'Chrome');

-- ----------------------------
-- Table structure for  t_s_operation 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_operation ;
CREATE TABLE  t_s_operation  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   iconid  varchar(32) default NULL ,-- COMMENT '图标ID',
   functionid  varchar(32) default NULL ,-- COMMENT '菜单ID',
   operationname  varchar(50) default NULL ,-- COMMENT '操作名称',
   operationcode  varchar(50) default NULL ,-- COMMENT '操作编码',
   operationicon  varchar(100) default NULL ,-- COMMENT '操作图标',
   status   smallint default NULL ,-- COMMENT '状态',
  PRIMARY KEY  ( id )
) ;

-- ----------------------------
-- Records of t_s_operation
-- ----------------------------
INSERT INTO  t_s_operation  VALUES ('8a8a8a813e62a855013e62e3204a0001', '402881763a019ea9013a01adcf29000f', '40284b813d1becf1013d1bf26afd0001', '录入', 'add', null, null);
INSERT INTO  t_s_operation  VALUES ('8a8a8a813e62a855013e62e355540003', '402881763a019ea9013a01adcf29000f', '40284b813d1becf1013d1bf26afd0001', '编辑', 'edit', null, null);
INSERT INTO  t_s_operation  VALUES ('8a8a8a813e62a855013e62e3806b0005', '402881763a019ea9013a01adcf29000f', '40284b813d1becf1013d1bf26afd0001', '删除', 'del', null, null);
INSERT INTO  t_s_operation  VALUES ('8a8a8a813e62a855013e62e3f7320007', '402881763a019ea9013a01adcf29000f', '40284b813d1becf1013d1bf26afd0001', '审核', 'szqm', null, null);

-- ----------------------------
-- Table structure for  t_s_role 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_role ;
CREATE TABLE  t_s_role  (
   id  varchar(32) NOT NULL ,-- COMMENT '角色ID',
   rolename  varchar(20) default NULL ,-- COMMENT '角色名称',
   rolecode  varchar(15) default NULL ,-- COMMENT '角色编码',
  PRIMARY KEY  ( id ),
  UNIQUE     ( id )
) ;

-- ----------------------------
-- Records of t_s_role
-- ----------------------------
INSERT INTO  t_s_role  VALUES ('30', '管理员', 'admin');
INSERT INTO  t_s_role  VALUES ('402881e53c7f0dde013c7f2935da0004', '普通用户', 'manager');

-- ----------------------------
-- Table structure for  t_s_role_function 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_role_function ;
CREATE TABLE  t_s_role_function  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   roleid  varchar(32) default NULL ,-- COMMENT '角色ID',
   functionid  varchar(32) default NULL ,-- COMMENT '权限ID',
   operation  varchar(200) default NULL ,-- COMMENT '操作权限代码',
  PRIMARY KEY  ( id ),
  UNIQUE      ( id ) 
) ;

-- ----------------------------
-- Records of t_s_role_function
-- ----------------------------
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0001', '30', '28', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0002', '30', '30', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0003', '30', '31', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0004', '30', '49', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0005', '30', '80', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0006', '30', '8a8a8a813e63303d013e6331c9ae0001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0007', '30', '8a8a8a813e63303d013e63322c8e0003', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0008', '30', '402881e53c8adce5013c8b000beb0003', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0009', '30', '297e5a493d39e132013d39e316050001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000a', '30', '297e5a493d8d8e26013d8d90de950001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000b', '30', '297e5a493da1e22b013da1e75dec0001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000c', '30', '40284b813d1becf1013d1bf26afd0001', 'add,del,');
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000d', '30', '40284b813d1fa3a8013d1fab697b0001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000e', '30', '40284b813d1fb07f013d1fb2df360001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce000f', '30', '402881e93cb3f340013cb3f93e9a0070', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0010', '30', '4028abee3e6b42b2013e6b4a4af40001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0011', '30', '8a8a8a813ea8afbf013ea8b6e4360002', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0012', '30', '8a8a8a813ec14e83013ec157ea940001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0013', '30', '402881e53c996700013c9967f0320001', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0014', '30', '71', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85ce0015', '30', '27', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85dd0016', '30', '402881e53c8589f7013c85afc393000b', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85dd0017', '30', '402881e53c98df12013c98fc3b300002', null);
INSERT INTO  t_s_role_function  VALUES ('8a890ffd3eee992c013eee9c85dd0018', '30', '402881e53c9a4b8e013c9a4c90960001', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30006', '402881e53c7f0dde013c7f2935da0004', '28', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30007', '402881e53c7f0dde013c7f2935da0004', '30', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30008', '402881e53c7f0dde013c7f2935da0004', '31', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30009', '402881e53c7f0dde013c7f2935da0004', '49', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000a', '402881e53c7f0dde013c7f2935da0004', '80', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000b', '402881e53c7f0dde013c7f2935da0004', '402881e53c8adce5013c8b000beb0003', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000c', '402881e53c7f0dde013c7f2935da0004', '40284b813d1becf1013d1bf26afd0001', 'add,szqm,');
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000d', '402881e53c7f0dde013c7f2935da0004', '71', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000e', '402881e53c7f0dde013c7f2935da0004', '27', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea3000f', '402881e53c7f0dde013c7f2935da0004', '402881e53c8589f7013c85afc393000b', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30010', '402881e53c7f0dde013c7f2935da0004', '402881e53c98df12013c98fc3b300002', null);
INSERT INTO  t_s_role_function  VALUES ('8a8a8a813e63096e013e630c5ea30011', '402881e53c7f0dde013c7f2935da0004', '402881e53c9a4b8e013c9a4c90960001', null);

-- ----------------------------
-- Table structure for  t_s_role_user 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_role_user ;
CREATE TABLE  t_s_role_user  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   userid  varchar(32) default NULL ,-- COMMENT '用户ID',
   roleid  varchar(32) default NULL ,-- COMMENT '角色ID',
  PRIMARY KEY  ( id ),
  UNIQUE      ( id ) 
) ;

-- ----------------------------
-- Records of t_s_role_user
-- ----------------------------
INSERT INTO  t_s_role_user  VALUES ('297e5a493da0abb4013da0c17b7d000b', '40', '30');
INSERT INTO  t_s_role_user  VALUES ('297e5a493da0abb4013da0c17bcb000d', '40', '402881e53c7f0dde013c7f2935da0004');
INSERT INTO  t_s_role_user  VALUES ('8a8a8a813def666d013def688e1a0001', '402881e73d87d62a013d87eeaba10014', '402881e53c7f0dde013c7f2935da0004');
INSERT INTO  t_s_role_user  VALUES ('8a8a8a813e63096e013e630c1e8d0004', '8a8a8a813e63096e013e630c1df10003', '402881e53c7f0dde013c7f2935da0004');
INSERT INTO  t_s_role_user  VALUES ('8a8a8a813edea696013edea7f08b0002', '402881e73d87d62a013d87ee2d4b0011', '30');

-- ----------------------------
-- Table structure for  t_s_type 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_type ;
CREATE TABLE  t_s_type  (
   id  varchar(32) NOT NULL,
   typecode  varchar(50) default NULL,
   typename  varchar(50) default NULL,
   typepid  varchar(32) default NULL,
   typegroupid  varchar(32) default NULL,
   value  varchar(50) default NULL,
  PRIMARY KEY  ( id )
) ;

-- ----------------------------
-- Records of t_s_type
-- ----------------------------
INSERT INTO  t_s_type  VALUES ('12', '2', '菜单图标', null, '1', '1');
INSERT INTO  t_s_type  VALUES ('297e5a493d543ce2013d5462c1380005', 'files', '附件', null, '402881e93cb3ccfd013cb3d9f3f70004', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e9219b14a0007', '1', '优质订单', null, '402880853e92115c013e9219489f0005', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e9219deb60009', '2', '普通订单', null, '402880853e92115c013e9219489f0005', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e921dce10000d', '1', '签约客户', null, '402880853e92115c013e921d6194000b', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e921df56c000f', '2', '普通客户', null, '402880853e92115c013e921d6194000b', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e9247b3200022', '1', '特殊服务', null, '402880853e92115c013e92474e370020', null);
INSERT INTO  t_s_type  VALUES ('402880853e92115c013e9247ec1b0024', '2', '普通服务', null, '402880853e92115c013e92474e370020', null);
INSERT INTO  t_s_type  VALUES ('4028817639fb7f290139fb8e9cd80009', 'leave', '请假流程', null, '4028817639fb7f290139fb8c60c30003', null);
INSERT INTO  t_s_type  VALUES ('402881e53c9ae91f013c9aea80b30002', 'act', '工作流引擎表', null, '402881e53c9ae91f013c9aea80aa0001', null);
INSERT INTO  t_s_type  VALUES ('402881e53c9ae91f013c9aea80b90003', 't_s', '系统基础表', null, '402881e53c9ae91f013c9aea80aa0001', null);
INSERT INTO  t_s_type  VALUES ('402881e53c9ae91f013c9aea80be0004', 't_b', '业务表', null, '402881e53c9ae91f013c9aea80aa0001', null);
INSERT INTO  t_s_type  VALUES ('402881e53c9ae91f013c9aea80c30005', 't_p', '自定义引擎表', null, '402881e53c9ae91f013c9aea80aa0001', null);
INSERT INTO  t_s_type  VALUES ('402881e93cb3ccfd013cb3d9f45f0005', 'news', '新闻', null, '402881e93cb3ccfd013cb3d9f3f70004', null);
INSERT INTO  t_s_type  VALUES ('4028abee3e274960013e274cbec10002', '1', '系统图标', null, '1', '2');
INSERT INTO  t_s_type  VALUES ('4028abee3e2774e5013e277719990004', '0', '男性', null, '4028abee3e2774e5013e27758b170001', '1');
INSERT INTO  t_s_type  VALUES ('4028abee3e2774e5013e2777655a0006', '1', '女性', null, '4028abee3e2774e5013e27758b170001', '0');

-- ----------------------------
-- Table structure for  t_s_typegroup 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_typegroup ;
CREATE TABLE  t_s_typegroup  (
   id  varchar(32) NOT NULL,
   typegroupname  varchar(50) default NULL,
   typegroupcode  varchar(50) default NULL,
  PRIMARY KEY  ( id )
) ;

-- ----------------------------
-- Records of t_s_typegroup
-- ----------------------------
INSERT INTO  t_s_typegroup  VALUES ('1', '图标类型', 'icontype');
INSERT INTO  t_s_typegroup  VALUES ('402880853e92115c013e9219489f0005', '订单类型', 'order');
INSERT INTO  t_s_typegroup  VALUES ('402880853e92115c013e921d6194000b', '客户类型', 'custom');
INSERT INTO  t_s_typegroup  VALUES ('402880853e92115c013e92474e370020', '服务项目类型 ', 'service');
INSERT INTO  t_s_typegroup  VALUES ('402881e53c9ae91f013c9aea80aa0001', '数据表', 'database');
INSERT INTO  t_s_typegroup  VALUES ('402881e93cb3ccfd013cb3d9f3f70004', '文档分类', 'fieltype');
INSERT INTO  t_s_typegroup  VALUES ('4028abee3e2774e5013e27758b170001', '性别类', 'sex');

-- ----------------------------
-- Table structure for  t_s_user 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_user ;
CREATE TABLE  t_s_user  (
   id  varchar(32) NOT NULL ,-- COMMENT '主键ID',
   signaturefile  varchar(100) default NULL ,-- COMMENT '签名',
   mobilephone  varchar(30) default NULL ,-- COMMENT '手机号码',
   officephone  varchar(20) default NULL ,-- COMMENT '办公电话',
   email  varchar(50) default NULL ,-- COMMENT '电子邮箱',
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of t_s_user
-- ----------------------------
INSERT INTO  t_s_user  VALUES ('40', 'images/renfang/qm/licf.gif', '', '', '');
INSERT INTO  t_s_user  VALUES ('402881e73d87d62a013d87ee2d4b0011', null, '13426432910', '7496661', 'zhangdaiscott@163.com');
INSERT INTO  t_s_user  VALUES ('402881e73d87d62a013d87eeaba10014', null, '', '', '');
INSERT INTO  t_s_user  VALUES ('8a8a8a813e63096e013e630c1df10003', null, '', '', '');

-- ----------------------------
-- Table structure for  t_s_version 
-- ----------------------------
DROP TABLE IF EXISTS  t_s_version ;
CREATE TABLE  t_s_version  (
   id  varchar(32) NOT NULL,
   versionname  varchar(50) default NULL,
   versioncode  varchar(30) default NULL,
   loginpage  varchar(100) default NULL,
   versionnum  varchar(20) default NULL,
  PRIMARY KEY  ( id ),
  UNIQUE      ( id )
) ;

-- ----------------------------
-- Records of t_s_version
-- ----------------------------
