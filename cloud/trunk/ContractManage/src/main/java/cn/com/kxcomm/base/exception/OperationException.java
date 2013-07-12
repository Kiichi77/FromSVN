package cn.com.kxcomm.base.exception;

/**
 * 
 * 功能描述:业务异常类，该类型异常可不用写日志，异常信息需要在提示给用户
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public class OperationException extends BaseException {
	/**
	 * 类序列化ID
	 */
	private static final long serialVersionUID = -2657816827277800681L;

	public OperationException() {
		super();
	}

	public OperationException(final String errorCode) {
		super(errorCode);
	}
}
