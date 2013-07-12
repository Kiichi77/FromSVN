package cn.com.kxcomm.contractmanage.entity;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user")
public class TbUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -2902524605776298679L;


	@Override
	public String toString() {
		return "TbUser [id=" + id + ", tbRole=" + tbRole + ", userName="
				+ userName + ", accouont=" + accouont + ", email=" + email
				+ ", note=" + note + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", jobDuties=" + jobDuties
				+ ", jobPlace=" + jobPlace + ", isInterface=" + isInterface
				+ "]";
	}

	private Long id;  //主键
	private TbRole tbRole; //角色
	private String userName; //姓名
	private String accouont; //用户名
	private String email;  //邮件
	private String note;  //备注
	private String password;  //密码
	private String phoneNumber; //联系方式
	/**
	 * 职务
	 */
	private TbJobduties jobDuties;
	
	/**
	 * 工作地点
	 */
	private TbJobPlace jobPlace;
	
	/**
	 * 是否是接口人,0：不是，1：是
	 */
	private Integer isInterface;


	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** minimal constructor */
	public TbUser(TbRole tbRole, String userName, String accouont,
			String email, String password, String phoneNumber) {
		this.tbRole = tbRole;
		this.userName = userName;
		this.accouont = accouont;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_duties_id", nullable = false)
	public TbJobduties getJobDuties() {
		return jobDuties;
	}

	public void setJobDuties(TbJobduties jobDuties) {
		this.jobDuties = jobDuties;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_place_id", nullable = false)
	public TbJobPlace getJobPlace() {
		return jobPlace;
	}

	public void setJobPlace(TbJobPlace jobPlace) {
		this.jobPlace = jobPlace;
	}

	/** full constructor */
	public TbUser(TbRole tbRole, String userName, String accouont,
			String email, String note, String password, String phoneNumber
			) {
		this.tbRole = tbRole;
		this.userName = userName;
		this.accouont = accouont;
		this.email = email;
		this.note = note;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "accouont", nullable = false, length = 20)
	public String getAccouont() {
		return this.accouont;
	}

	public void setAccouont(String accouont) {
		this.accouont = accouont;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "password", nullable = false, length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone_number", nullable = false, length = 30)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "is_interface", nullable = false, length = 30)
	public Integer getIsInterface() {
		return isInterface;
	}

	public void setIsInterface(Integer isInterface) {
		this.isInterface = isInterface;
	}
	

}