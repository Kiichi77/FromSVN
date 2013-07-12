package cn.com.kxcomm.base.exception;

/**
 * 
 * 功能描述:系统异常类，该类型的异常要写日志
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public class SystemException extends BaseException {
	/**
	 * 类序列化ID
	 */
	private static final long serialVersionUID = 2519586663837180588L;

	public SystemException() {
		super();
	}

	public SystemException(final String errorCode) {
		super(errorCode);   
	}
}
