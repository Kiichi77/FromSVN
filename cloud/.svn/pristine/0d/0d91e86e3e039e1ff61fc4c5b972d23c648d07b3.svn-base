package cn.com.kxcomm.ipmi.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user")
public class TbUser implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "TbUser [id=" + id + ", tbRole=" + tbRole + ", userName="
				+ userName + ", accouont=" + accouont + ", email=" + email
				+ ", note=" + note + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", tbSystemoperlogs="
				+ tbSystemoperlogs + "]";
	}

	private Long id;
	private TbRole tbRole;
	private String userName;
	private String accouont;
	private String email;
	private String note;
	private String password;
	private String phoneNumber;
	private Set<TbSystemoperlog> tbSystemoperlogs = new HashSet<TbSystemoperlog>(
			0);

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

	/** full constructor */
	public TbUser(TbRole tbRole, String userName, String accouont,
			String email, String note, String password, String phoneNumber,
			Set<TbSystemoperlog> tbSystemoperlogs) {
		this.tbRole = tbRole;
		this.userName = userName;
		this.accouont = accouont;
		this.email = email;
		this.note = note;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.tbSystemoperlogs = tbSystemoperlogs;
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

	@ManyToOne(fetch = FetchType.LAZY)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbUser")
	public Set<TbSystemoperlog> getTbSystemoperlogs() {
		return this.tbSystemoperlogs;
	}

	public void setTbSystemoperlogs(Set<TbSystemoperlog> tbSystemoperlogs) {
		this.tbSystemoperlogs = tbSystemoperlogs;
	}

}