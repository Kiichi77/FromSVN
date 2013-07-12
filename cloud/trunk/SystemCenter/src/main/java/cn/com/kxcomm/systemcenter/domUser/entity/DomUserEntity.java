package cn.com.kxcomm.systemcenter.domUser.entity;

import java.io.Serializable;

/**
 * 
 * 功能描述:域用户实体
 * 
 * @author chenliang 新增日期：2013-6-24
 * @since system_center_sdk
 */
public class DomUserEntity implements Serializable {

	private Long id;
	private String userName;
	private String password;
	private String account;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
