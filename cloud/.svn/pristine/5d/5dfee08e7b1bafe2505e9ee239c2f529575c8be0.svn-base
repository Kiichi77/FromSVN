package cn.com.woyun.exception;


/**
 * 
* 抛出自定义错误到调用方
* @author zhangjh 新增日期：2012-8-23
* @since ipmi_sdk
 */
public class SeriousError extends Error{
	/**
	 * 说明清楚此属性的业务含义
	 */
	private static final long serialVersionUID = -3585214286102901371L;
	private Errors error;
	public SeriousError(Errors error){
		this.error = error;
	}

	@Override
	public String getMessage() {
		return error.getMsg();
	}

	@Override
	public String toString() {
		return error.toString();
	}
	
	/**
	 * 
	* 返回错误的对象
	* @return
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public Errors getError(){
		return error;
	}
}
