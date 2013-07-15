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
 * TbServerBelong entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_server_belong")
public class TbServerBelong implements java.io.Serializable {

	// Fields

	private TbServerBelongId id;
	private TbBelong tbBelong;
	private TbServer tbServer;

	// Constructors

	/** default constructor */
	public TbServerBelong() {
	}

	/** full constructor */
	public TbServerBelong(TbServerBelongId id, TbBelong tbBelong,
			TbServer tbServer) {
		this.id = id;
		this.tbBelong = tbBelong;
		this.tbServer = tbServer;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "belongId", column = @Column(name = "belong_id", nullable = false)),
			@AttributeOverride(name = "serverId", column = @Column(name = "server_id", nullable = false)) })
	public TbServerBelongId getId() {
		return this.id;
	}

	public void setId(TbServerBelongId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "belong_id", nullable = false, insertable = false, updatable = false)
	public TbBelong getTbBelong() {
		return this.tbBelong;
	}

	public void setTbBelong(TbBelong tbBelong) {
		this.tbBelong = tbBelong;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "server_id", nullable = false, insertable = false, updatable = false)
	public TbServer getTbServer() {
		return this.tbServer;
	}

	public void setTbServer(TbServer tbServer) {
		this.tbServer = tbServer;
	}

}