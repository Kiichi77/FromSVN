package cn.com.kxcomm.ipmi.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbRightRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_right_role")
public class TbRightRole implements java.io.Serializable {

	// Fields

	private TbRightRoleId id;
	private TbRole tbRole;
	private TbRight tbRight;

	// Constructors

	/** default constructor */
	public TbRightRole() {
	}

	/** full constructor */
	public TbRightRole(TbRightRoleId id, TbRole tbRole, TbRight tbRight) {
		this.id = id;
		this.tbRole = tbRole;
		this.tbRight = tbRight;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "rightId", column = @Column(name = "right_id", nullable = false)) })
	public TbRightRoleId getId() {
		return this.id;
	}

	public void setId(TbRightRoleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "right_id", nullable = false, insertable = false, updatable = false)
	public TbRight getTbRight() {
		return this.tbRight;
	}

	public void setTbRight(TbRight tbRight) {
		this.tbRight = tbRight;
	}

}