package cn.com.kxcomm.ipmi.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbServerBelongId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TbServerBelongId implements java.io.Serializable {

	// Fields

	private Long belongId;
	private Long serverId;

	// Constructors

	/** default constructor */
	public TbServerBelongId() {
	}

	/** full constructor */
	public TbServerBelongId(Long belongId, Long serverId) {
		this.belongId = belongId;
		this.serverId = serverId;
	}

	// Property accessors

	@Column(name = "belong_id", nullable = false)
	public Long getBelongId() {
		return this.belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	@Column(name = "server_id", nullable = false)
	public Long getServerId() {
		return this.serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbServerBelongId))
			return false;
		TbServerBelongId castOther = (TbServerBelongId) other;

		return ((this.getBelongId() == castOther.getBelongId()) || (this
				.getBelongId() != null && castOther.getBelongId() != null && this
				.getBelongId().equals(castOther.getBelongId())))
				&& ((this.getServerId() == castOther.getServerId()) || (this
						.getServerId() != null
						&& castOther.getServerId() != null && this
						.getServerId().equals(castOther.getServerId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBelongId() == null ? 0 : this.getBelongId().hashCode());
		result = 37 * result
				+ (getServerId() == null ? 0 : this.getServerId().hashCode());
		return result;
	}

}