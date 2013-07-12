package cn.com.kxcomm.ipmitool.util;

/**
 * 
 * 执行命令的服务器
 * 
 * @author zhangjh 新增日期：2012-9-6
 * @since ipmi_sdk
 */
public class GobblerServer {
	private String host;
	private String user;
	private String passwd;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public GobblerServer(String host, String user, String passwd) {
		super();
		this.host = host;
		this.user = user;
		this.passwd = passwd;
	}

	public GobblerServer() {
		super();
	}

	@Override
	public String toString() {
		return "GobblerServer [host=" + host + ", user=" + user + ", passwd="
				+ passwd + "]";
	}

}
