package cn.com.kxcomm.ipmi.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbPolicySever entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_policy_sever")
public class TbPolicySever implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "TbPolicySever [id=" + id +", biningServerType="
				+ biningServerType + ", biningState=" + biningState
				+ ", biningType=" + biningType + ", policyidInServer="
				+ policyidInServer + "]";
	}

	private Long id;
	private TbServer tbServer;
	private TbPolicy tbPolicy;
	private Integer biningServerType;
	private Integer biningState;
	private Integer biningType;
    private Integer policyidInServer;
	// Constructors

	@Column(name = "policyid_in_server", nullable = false)
	public Integer getPolicyidInServer() {
		return policyidInServer;
	}

	public void setPolicyidInServer(Integer policyidInServer) {
		this.policyidInServer = policyidInServer;
	}


	/** default constructor */
	public TbPolicySever() {
	}

	/** full constructor */
	public TbPolicySever(TbServer tbServer, TbPolicy tbPolicy,
			Integer biningServerType, Integer biningState, Integer biningType) {
		this.tbServer = tbServer;
		this.tbPolicy = tbPolicy;
		this.biningServerType = biningServerType;
		this.biningState = biningState;
		this.biningType = biningType;
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
	@JoinColumn(name = "bining_server_id", nullable = false)
	public TbServer getTbServer() {
		return this.tbServer;
	}

	public void setTbServer(TbServer tbServer) {
		this.tbServer = tbServer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bining_id", nullable = false)
	public TbPolicy getTbPolicy() {
		return this.tbPolicy;
	}

	public void setTbPolicy(TbPolicy tbPolicy) {
		this.tbPolicy = tbPolicy;
	}

	@Column(name = "bining_server_type", nullable = true)
	public Integer getBiningServerType() {
		return this.biningServerType;
	}

	public void setBiningServerType(Integer biningServerType) {
		this.biningServerType = biningServerType;
	}

	@Column(name = "bining_state", nullable = false)
	public Integer getBiningState() {
		return this.biningState;
	}

	public void setBiningState(Integer biningState) {
		this.biningState = biningState;
	}

	@Column(name = "bining_type", nullable = true)
	public Integer getBiningType() {
		return this.biningType;
	}

	public void setBiningType(Integer biningType) {
		this.biningType = biningType;
	}

}