package cn.com.kxcomm.ipmitool.entity;

public class Errors {
	/**
	 * 错误代码
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Errors [code=" + code + ", msg=" + msg + "]";
	}

	public Errors(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Errors() {
		super();
	}

}
