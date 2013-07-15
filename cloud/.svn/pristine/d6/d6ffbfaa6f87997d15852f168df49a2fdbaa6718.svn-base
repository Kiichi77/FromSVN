package cn.com.kxcomm.ipmi.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbBlackName entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_black_name")
public class TbBlackName implements java.io.Serializable {

	// Fields

	private Long id;
	private Long serverId;

	// Constructors

	/** default constructor */
	public TbBlackName() {
	}

	/** full constructor */
	public TbBlackName(Long serverId) {
		this.serverId = serverId;
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

	@Column(name = "server_id", nullable = false)
	public Long getServerId() {
		return this.serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

}