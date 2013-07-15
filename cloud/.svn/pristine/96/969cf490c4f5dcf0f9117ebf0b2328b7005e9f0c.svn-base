package cn.com.kxcomm.ipmi.entity;
// default package

import java.sql.Time;
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
 * TbPolicy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_policy")
public class TbPolicy implements java.io.Serializable {

	// Fields

	private Long id;
	private String policyName;
	private Short policyType;
	private Integer policyLimit;
	private String command;
	private String policyTrigger;
	private String policyStartHour;
	private String policyStartMinute;
	private String policyEndHour;
	private String policyEndMinute;
	private Integer policyBumber;
	@Column(name = "policy_number", nullable = false)
	public Integer getPolicyBumber() {
		return policyBumber;
	}


	public void setPolicyBumber(Integer policyBumber) {
		this.policyBumber = policyBumber;
	}


	@Column(name = "policy_end_hour", nullable = true, length = 2)
	public String getPolicyEndHour() {
		return policyEndHour;
	}


	public void setPolicyEndHour(String policyEndHour) {
		this.policyEndHour = policyEndHour;
	}

	@Column(name = "policy_end_minute", nullable = true, length = 2)
	public String getPolicyEndMinute() {
		return policyEndMinute;
	}


	public void setPolicyEndMinute(String policyEndMinute) {
		this.policyEndMinute = policyEndMinute;
	}

	private String domain;
	private Set<TbPolicySever> tbPolicySevers = new HashSet<TbPolicySever>(0);

	// Constructors

	/** default constructor */
	public TbPolicy() {
	}

	
	@Column(name = "policy_start_hour", nullable = true, length = 2)
	public String getPolicyStartHour() {
		return policyStartHour;
	}



	public void setPolicyStartHour(String policyStartHour) {
		this.policyStartHour = policyStartHour;
	}



	@Column(name = "policy_start_minute", nullable = true, length = 2)
	public String getPolicyStartMinute() {
		return policyStartMinute;
	}



	public void setPolicyStartMinute(String policyStartMinute) {
		this.policyStartMinute = policyStartMinute;
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

	@Column(name = "policy_name", nullable = false, length = 20)
	public String getPolicyName() {
		return this.policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	@Column(name = "policy_type", nullable = false)
	public Short getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(Short policyType) {
		this.policyType = policyType;
	}

	@Column(name = "policy_limit", nullable = false)
	public Integer getPolicyLimit() {
		return this.policyLimit;
	}

	public void setPolicyLimit(Integer policyLimit) {
		this.policyLimit = policyLimit;
	}

	@Column(name = "command", nullable = true, length = 100)
	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Column(name = "policy_trigger", nullable = true, length = 30)
	public String getPolicyTrigger() {
		return this.policyTrigger;
	}

	public void setPolicyTrigger(String policyTrigger) {
		this.policyTrigger = policyTrigger;
	}

	

	@Column(name = "domain", nullable = true, length = 3)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbPolicy")
	public Set<TbPolicySever> getTbPolicySevers() {
		return this.tbPolicySevers;
	}

	public void setTbPolicySevers(Set<TbPolicySever> tbPolicySevers) {
		this.tbPolicySevers = tbPolicySevers;
	}

}