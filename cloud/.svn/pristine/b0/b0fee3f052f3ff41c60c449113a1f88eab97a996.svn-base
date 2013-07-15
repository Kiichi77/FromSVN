package cn.com.woyun.exception;


/**
 * 
* 严重的错误
* @author zhangjh 新增日期：2012-8-23
* @since ipmi_sdk
 */
public class SeriousException extends Exception{
	/**
	 * 说明清楚此属性的业务含义
	 */
	private static final long serialVersionUID = 5791180284185311105L;
	private Errors error;
	public SeriousException(Errors error){
		this.error = error;
	}

	@Override
	public String getMessage() {
		return error.getMsg();
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
