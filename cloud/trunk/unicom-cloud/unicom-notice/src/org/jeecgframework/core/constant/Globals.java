package org.jeecgframework.core.constant;
/**  
* 项目名称：jeecg
* 类名称：Globals   
* 类描述：  全局变量定义
* 创建人：jeecg      
* @version    
*
 */
public interface Globals {
	/**
	 * 保存用户到SESSION
	 */
	public static String USER_SESSION="USER_SESSION";
	/**
	 * 人员类型
	 */
	Short User_Normal=1;//正常
	Short User_Forbidden=0;//禁用
	Short User_ADMIN=-1;//超级管理员
	/**
	 *日志级别定义
	 */
	 Short Log_Leavel_INFO=1;
	 Short Log_Leavel_WARRING=2;
	 Short Log_Leavel_ERROR=3;
	 /**
	  * 日志类型
	  */
	 Short Log_Type_LOGIN=1; //登陆
	 Short Log_Type_EXIT=2;  //退出
	 Short Log_Type_INSERT=3; //插入
	 Short Log_Type_DEL=4; //删除
	 Short Log_Type_UPDATE=5; //更新
	 Short Log_Type_UPLOAD=6; //上传
	 Short Log_Type_OTHER=7; //其他
	 
	 /**
	  * 流程发布状态
	  */
	 Short Process_Deploy_NO=0;//未发布
	 Short Process_Deploy_YES=1;//已发布
	 
	 /**
	  * 流程节点监听映射表状态
	  */
	 Short Process_Listener_NO=0;//禁用
	 Short Process_Listener_YES=1;//启用
	 
	 /**
	  * 监听状态值
	  */
	 Short Listener_No=0;//禁用
	 Short Listener_Yes=1;//启用
	 
	 /**
	  * 监听器类型
	  */
	 Short Listener_Type_Task=2;//任务监听器
	 Short Listener_Type_Excution=1;//执行监听器
	 
	 /**
	  * 词典分组定义
	  */
	 String TypeGroup_Database="database";//数据表分类
	 
	 /**
	  * 数据库表名字前缀
	  */
	 String DataBase_Activiti="act";//引擎表
	 String DataBase_Base="t_s";//系统基础表
	 String DataBase_Bus="t_b";//业务表
	 String DataBase_Process="t_p";//自定义引擎表
	 
	 /**
	  * 用户是否同步到引擎
	  */
	 Short Activiti_Deploy_NO=0;//不同步
	 Short Activiti_Deploy_YES=1;//同步
	 
	 
	 /**
	  * 权限等级
	  */
	 Short Function_Leave_ONE=0;//一级权限
	 Short Function_Leave_TWO=1;//二级权限
	 
	 /**
	  * 权限等级前缀
	  */
	 String Function_Order_ONE="ofun";//一级权限
	 String Function_Order_TWO="tfun";//二级权限

	 /**
	  * 新闻法规
	  */
	 Short Document_NEW=0; //新建
	 Short Document_PUBLICH=0; //发布

	 


	 

	
}
