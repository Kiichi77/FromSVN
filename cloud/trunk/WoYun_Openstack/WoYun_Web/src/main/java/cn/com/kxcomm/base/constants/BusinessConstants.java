package cn.com.kxcomm.base.constants;
/**
 * 
* 功能描述:定义业务常量
* <p>版权所有：北京康讯通讯
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author libu 新增日期：2010-6-9
* @author 你的姓名 修改日期：2010-6-9
* @since ipmi_web
 */
public class BusinessConstants {
	
	//项目编码
	public static final String PROJECT_ENCODE = "UTF-8";
	
	//服务器所属集合表级别
	public static final short SERVER_DATACENTER = 1; //数据中心
	public static final short SERVER_SERVERROOM = 2;  //机房
	public static final short SERVER_STACKLINE = 3;  //机架系
	public static final short SERVER_STACK = 4;		//机架
	public static final short SERVER_CHASSIS = 5;		//机房
	
	//服务器数据采集类型
	public static final int COLLECT_CPU_CONSUMPTION = 1; //cpu功耗
	public static final int COLLECT_RAM_CONSUMPTION = 2; //内存功耗
	public static final int COLLECT_POWER_CONSUMPTION = 3;//电源功耗
	public static final int COLLECT_AIR_INLET_TEMP = 4;//进风口温度
	
	//系统操作日志操作类型
	public static final String syslog_login = "1"; //登陆
	public static final String syslog_add = "2"; //添加
	public static final String syslog_update = "3";//修改
	public static final String syslog_delete = "4";//删除
	public static final String syslog_batchDelete = "5";//批量删除
	
	
	//策略类型
	public static final short policy_system_power = 1;   //系统功耗策略
	public static final short policy_cpu_power = 2;     //cpu功耗策略
	public static final short policy_memory_power = 3;	//内存功耗策略
	public static final short policy_system_thermal = 4;  //系统温度策略
	public static final short policy_cpu_thermal = 5;   //cpu温度策略
	public static final short policy_memory_thermal = 6;//内存温度策略
	public static final short policy_airInlet_thermal = 7;//进风口温度策略
	
	//错误代码
	//操作类型
	public static final int ERRORCODE_OPERTYPE_ADD=1; //添加
	public static final int ERRORCODE_OPERTYPE_EDIT=2; //修改
	//失败原因
	public static final int FAILURE_REASON_CONNECTIONOUTTIME=1;//连接超时
	public static final int FAILURE_REASON_POLICYIDOCCU=2;//policy id被占用
	public static final int FAILURE_REASON_OTHER=3;//其它原因
	
}
