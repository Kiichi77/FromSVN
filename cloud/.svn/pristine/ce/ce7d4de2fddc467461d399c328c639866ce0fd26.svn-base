package cn.com.kxcomm.ipmi.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbRightRoleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TbRightRoleId implements java.io.Serializable {

	// Fields

	private Long roleId;
	private Long rightId;

	// Constructors

	/** default constructor */
	public TbRightRoleId() {
	}

	/** full constructor */
	public TbRightRoleId(Long roleId, Long rightId) {
		this.roleId = roleId;
		this.rightId = rightId;
	}

	// Property accessors

	@Column(name = "role_id", nullable = false)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "right_id", nullable = false)
	public Long getRightId() {
		return this.rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbRightRoleId))
			return false;
		TbRightRoleId castOther = (TbRightRoleId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getRightId() == castOther.getRightId()) || (this
						.getRightId() != null && castOther.getRightId() != null && this
						.getRightId().equals(castOther.getRightId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getRightId() == null ? 0 : this.getRightId().hashCode());
		return result;
	}

}