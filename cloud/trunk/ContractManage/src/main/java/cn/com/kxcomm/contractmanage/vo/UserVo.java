package cn.com.kxcomm.contractmanage.vo;

import cn.com.kxcomm.contractmanage.entity.TbJobPlace;
import cn.com.kxcomm.contractmanage.entity.TbJobduties;

// default package

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */
public class UserVo implements java.io.Serializable {

	private Long id;
	private RoleVo tbRole;
	private String userName;
	private String accouont;
	private String email;
	private String note;
	private String password;
	private String phoneNumber;
	private TbJobduties jobDuties;
	private TbJobPlace jobPlace;
	private String isInterface;

	// Constructors

	/** default constructor */
	public UserVo() {
	}
	
	

	public String getIsInterface() {
		return isInterface;
	}



	public void setIsInterface(String isInterface) {
		this.isInterface = isInterface;
	}



	public TbJobduties getJobDuties() {
		return jobDuties;
	}



	public void setJobDuties(TbJobduties jobDuties) {
		this.jobDuties = jobDuties;
	}



	public TbJobPlace getJobPlace() {
		return jobPlace;
	}

	public void setJobPlace(TbJobPlace jobPlace) {
		this.jobPlace = jobPlace;
	}

	public UserVo(Long id, RoleVo tbRole, String userName, String accouont,
			String email, String note, String password, String phoneNumber) {
		super();
		this.id = id;
		this.tbRole = tbRole;
		this.userName = userName;
		this.accouont = accouont;
		this.email = email;
		this.note = note;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "UserVo [id=" + id + ", tbRole=" + tbRole + ", userName="
				+ userName + ", accouont=" + accouont + ", email=" + email
				+ ", note=" + note + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleVo getTbRole() {
		return tbRole;
	}

	public void setTbRole(RoleVo tbRole) {
		this.tbRole = tbRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccouont() {
		return accouont;
	}

	public void setAccouont(String accouont) {
		this.accouont = accouont;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}