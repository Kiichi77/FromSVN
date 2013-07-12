package cn.com.kxcomm.common.constant;
/**
 * 
* 功能描述:定义业务常量
* <p>版权所有：金鹏科技
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author chenliang 新增日期：2013-6-9
* @author 你的姓名 修改日期：2013-6-9
* @since system_center_sdk
 */
public class BusinessConstants {
	
	//项目编码
	public static final String PROJECT_ENCODE = "UTF-8";
	
	//---------------------是否有效
	public static final int STATUS_ONLINE = 1; //有效
	public static final int STATUS_OFFLINE = 2; //无效
	
	
	//---------------------排行榜类型
	public static final int TOP_WEEK = 1; //周排行
	public static final int TOP_MONTH = 2; //月排行
	public static final int TOP_SEASON = 3; //季排行
	public static final int CURR_TOP_WEEK = 4; //当前周排行
	public static final int CURR_TOP_MONTH = 5; //当前月排行
	public static final int CURR_TOP_SEASON = 6; //当前季排行	
	
	
	//------------------------任务运行状态
	/**
	 * 任务未执行
	 */
	public static final int TASK_NOT_RUN = 1;
	
	/**
	 * 任务执行中
	 */
	public static final int TASK_RUNNING = 2;
	
	/**
	 * 任务已完成
	 */
	public static final int TASK_RUN_OVER = 3;
	
	/**
	 * 任务运行完，但未统计,被暂停了
	 */
	public static final int TASK_RUN_NOT_COUNT = 4;
	
	/**
	 * 任务执行失败，但未统计
	 */
	public static final int TASK_RUN_FAILD_NOT_COUNT = 6;
	
	/**
	 * 任务执行中，失败
	 */
	public static final int TASK_RUNNING_FAILD = 7;
	
	/**
	 * 任务开始执行
	 */
	public static final int TASK_RUN_START = 8;
	
	/**
	 * 任务运行完成但失败
	 */
	public static final int TASK_RUN_FAILD = 9;
	
	/**
	 * 任务已启动，但未到任务运行点已停止
	 */
	public static final int TASK_RUN_DATE = 10;
	
	/**
     * 任务执行类型1：策略任务(固定周期);
     */
	public static final short JOB_RUN_TYPE_FIX = 1;
	
    /**
     * 任务执行类型2：触发任务( 相对周期) ;
     */
	public static final short JOB_RUN_TYPE_RELA = 2;
    /**
     * 任务状态是否启用1：启用。
     */
	public static final short JOB_USE_FLAG_YES = 1;
	
    /**
     * 任务状态是否启用 0:暂停
     */
	public static final short JOB_USE_FLAG_NO = 0;
	
    /**
     * 推送日期类型 1:每月
     */
	public static final short TIME_TYPE_EVEVYMONTH =1; 

	 /**
     * 推送日期类型2：指定日期
     */
	public static final short TIME_TYPE_SOMEDAY =2;
	
	 /**
     * 推送日期类型3：非指定日期
     */
	public static final short TIME_TYPE_UNSPECIFIED =3;
	
	
	/**
	 * 表字段：blacklist_flag支持黑名单标志 0：否；
	 */
	public static final short FLAG_BLACKLIST_NO =0;
	
	/**
	 * 表字段：blacklist_flag支持黑名单标志 1：是
	 */
	public static final short FLAG_BLACKLIST_YES =1;
	
	/**
	 * 网络类型G网
	 */
	public static final short MDN_NET_G =1;
	
	/**
	 * 网络类型C网
	 */
	public static final short MDN_NET_C =2;
	
	/**
	 * 策略任务推送用户范围：全体用户 1
	 */
	public static final short TYPE_RANG_MDN_ALL = 1;
	
	
	/**
	 * 策略任务推送用户范围：指定号段 2
	 */
	public static final short TYPE_RANG_MDN_INTERVAL = 2;
	
	
	/**
	 * 策略任务推送用户范围：号码列表 3
	 */
	public static final short TYPE_RANG_MDN_LIST = 3;
	
	/**
	 * 任务推送用户范围：用户策略
	 */
	public static final Short TYPE_USER_PROLICY = 4;
	
	/**
	 * 策略任务规则：权重匹配规则 1
	 */
	public static final short TACTICS_JOB_RULE_TYPE_WEIGHTMATCH = 1;
	
	/**
	 * 策略任务规则：相关性规则 2
	 */
	public static final short TACTICS_JOB_RULE_TYPE_CORRELATION = 2;
	
	/**
	 * 策略任务规则：排行榜 3
	 */
	public static final short  TACTICS_JOB_RULE_TYPE_LIST = 3;
	/**
	 * 不推送短信
	 */
	public static final short TACTICS_JOB_NOT_PUSH = -1;
	
	/**
	 * 触发任务 未定制
	 */
	public static final short RELATIVELY_JOB_NO_ORDER = 3;
	/**
	 * 触发任务 已定制
	 */
	public static final short RELATIVELY_JOB_ORDER = 1;
	/**
	 * 触发任务 已注册
	 */
	public static final short RELATIVELY_JOB_REGISTER = 2;
	/**
	 * 触发任务 注册但未下载
	 */
	public static final short RELATIVELY_JOB_NO_DOWNLOAD = 4;
	
	
	//-------------------参数类型

	/**
	 *类型一  业务初始化 
	 */
	public static final short PARAMETER_TYPE_USERLIST_INITIALIZE = 1;
	/**
	 * 类型二 渠道初始化
	 */
	public static final short PARAMETER_TYPE_CHANNELLIST_INITIALIZE = 2;
	/**
	 * 类型三 无限俱乐部等级初始化
	 */
	public static final short PARAMETER_TYPE_GRADELIST_INITIALIZE = 3;
	/**
	 * 类型四 铃音状态
	 */
	public static final short PARAMETER_TYPE_RINGSTATELIST_INITIALIZE = 4;
	/**
	 * 类型五 品牌初始化
	 */
	public static final short PARAMETER_TYPE_BRANDlIST_INITIALIZE = 5;
	/**
	 * 类型六 网络类型初始化
	 */
	public static final short PARAMETER_TYPE_NETTYPELIST_INITIALIZE = 6;
	
	/**
	 * 未审核
	 */
	public static final int NO_AUDIT = 0;
	
	/**
	 * 审核通过
	 */
	public static final int AUDIT_PASS = 1;
	
	/**
	 * 审核不通过(驳回审核)
	 */
	public static final int AUDIT_REFUSE = 2;
	
	/**
	 * 已提交审核
	 */
	public static final int AUDIT_PUTIN = 3;
	
	/**
	 * 未提交审核
	 */
	public static final int AUDIT_NOPUTIN = 4;
	
	/*****************标签规则属性名称******************/
	/**
	 * 用户类型
	 */
	public static final int USER_TYPE = 0;
	
	/**
	 * 开户方式
	 */
	public static final int OPEN_WAY = 2;
	
	/**
	 * 业务区
	 */
	public static final int CITYS = 3;
	
	/**
	 * 品牌
	 */
	public static final int BRANDE = 12;
	
	/**
	 * 是叶子标签
	 */
	public static final int IS_LEAF = 1;
	
	/**
	 * 不是叶子标签
	 */
	public static final int NOT_LEAF = 0;
	
	/**
	 * 名称已存在
	 */
	public static final int HAVE_NAME = 1;
	
	/**
	 * 该标签已经审核，不能修改其规则
	 */
	public static final int LABEL_AUDIT = 2;
	
	/**
	 * 修改成功
	 */
	public static final int EDIT_SUCCESS = 3;
	
	/**
	 * 修改失败
	 */
	public static final int EDIT_LOSE = 4;
	
	/**
	 * 用户属性标签
	 */
	public static final int LABEL_TYPE_PRO = 1;
	
	/**
	 * 用户行为标签
	 */
	public static final int LABEL_TYPE_ACTION = 2;
	
	/**
	 * 歌曲标签
	 */
	public static final int LABEL_TYPE_MUZIC = 3;
	
	/**
	 * 删除成功
	 */
	public static final int DELETE_SUCCESS = 1;
	
	/**
	 * 删除失败
	 */
	public static final int DELETE_LOSE = 0;
	
	/**
	 * 没有用户获得方式
	 */
	public static final int NOWANTUSERTYPE = 0;
	
	/**
	 * 用户筛选策略
	 */
	public static final int USERWANTPOLIEC = 1;
	
	/**
	 * 导入号码
	 */
	public static final int INPUTUSERNUMBER = 2;
	
	/**
	 * 数据的类型名称
	 * */
	public static final String[] DATATYPE = {"移动","电信","合计"};
	/**
	 * 重点客户设置，不启用
	 */
	public static final int NOTENABLE = 0;
	/**
	 * 重点客户设置，启用
	 */
	public static final int ENABLE = 1;
}
