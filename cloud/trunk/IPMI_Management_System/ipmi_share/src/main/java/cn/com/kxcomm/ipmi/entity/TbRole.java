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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_role")
public class TbRole implements java.io.Serializable {

	// Fields

	private Long id;
	private String rolename;
	private String note;
	private Set<TbRightRole> tbRightRoles = new HashSet<TbRightRole>(0);
	private Set<TbUser> tbUsers = new HashSet<TbUser>(0);

	// Constructors

	/** default constructor */
	public TbRole() {
	}

	/** minimal constructor */
	public TbRole(String rolename) {
		this.rolename = rolename;
	}

	/** full constructor */
	public TbRole(String rolename, String note, Set<TbRightRole> tbRightRoles,
			Set<TbUser> tbUsers) {
		this.rolename = rolename;
		this.note = note;
		this.tbRightRoles = tbRightRoles;
		this.tbUsers = tbUsers;
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

	@Column(name = "rolename", nullable = false, length = 30)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbRole")
	public Set<TbRightRole> getTbRightRoles() {
		return this.tbRightRoles;
	}

	public void setTbRightRoles(Set<TbRightRole> tbRightRoles) {
		this.tbRightRoles = tbRightRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbRole")
	public Set<TbUser> getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(Set<TbUser> tbUsers) {
		this.tbUsers = tbUsers;
	}

}