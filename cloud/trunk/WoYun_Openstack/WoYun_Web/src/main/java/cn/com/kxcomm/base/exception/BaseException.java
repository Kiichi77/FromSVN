package cn.com.kxcomm.base.exception;

/**
 * 
 * 功能描述:异常处理基类
 * <p>
 * 版权所有：北京康讯通讯
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public class BaseException extends Exception {
	/**
	 * 类序列化ID
	 */
	private static final long serialVersionUID = -5246579929018578466L;
	/**
	 * 国际化信息key
	 */
	private String errorCode;

	public BaseException() {
		super();
	}

	public BaseException(final String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
