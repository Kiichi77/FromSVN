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
 * TbServer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_server")
public class TbServer implements java.io.Serializable {

	// Fields

	private Long id;
	private Long groupId;
	private String serverName;
	private String operateSystem;
	private String idracIp;
	private String idracUser;
	private String idracPassword;
	private String serverIpIn;
	private String serverIpOut;
	private String serverDescription;
	private Short cangetCpuConsumption;
	private Short cangetPowerConsumption;
	private Short cangetMemoryConsumption;
	private Short cangetWindTemperature;
	private Short cpuConsumptionPolicy;
	private Short memoryConsumptionPolicy;
	private Short powerConsumptionPolicy;
	private String quickServerCode;
	private String searchNote1;
	private String searchNote2;
	private Integer policyid;
	@Column(name = "policyid", nullable = true)
	public Integer getPolicyid() {
		return policyid;
	}

	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}

	@Column(name = "search_note1", nullable = true, length = 100)
	public String getSearchNote1() {
		return searchNote1;
	}

	public void setSearchNote1(String search_note1) {
		this.searchNote1 = search_note1;
	}
	@Column(name = "search_note2", nullable = true, length = 100)
	public String getSearchNote2() {
		return searchNote2;
	}

	public void setSearchNote2(String search_note2) {
		this.searchNote2 = search_note2;
	}

	@Column(name = "quick_server_code", nullable = true, length = 40)
	public String getQuickServerCode() {
		return quickServerCode;
	}

	public void setQuickServerCode(String quickServerCode) {
		this.quickServerCode = quickServerCode;
	}

	private Set<TbServerBelong> tbServerBelongs = new HashSet<TbServerBelong>(0);
	private Set<TbPolicySever> tbPolicySevers = new HashSet<TbPolicySever>(0);
	private Set<TbHistoryServerInfo> tbHistoryServerInfos = new HashSet<TbHistoryServerInfo>(
			0);

	// Constructors

	/** default constructor */
	public TbServer() {
	}

	/** minimal constructor */
	public TbServer(String serverName, String operateSystem, String idracIp,
			String idracUser, String idracPassword, String serverIpIn) {
		this.serverName = serverName;
		this.operateSystem = operateSystem;
		this.idracIp = idracIp;
		this.idracUser = idracUser;
		this.idracPassword = idracPassword;
		this.serverIpIn = serverIpIn;
	}

	/** full constructor */
	public TbServer(Long groupId, String serverName, String operateSystem,
			String idracIp, String idracUser, String idracPassword,
			String serverIpIn, String serverIpOut, String serverDescription,
			Short cangetCpuConsumption, Short cangetPowerConsumption,
			Short cangetMemoryConsumption, Short cangetWindTemperature,
			Short cpuConsumptionPolicy, Short memoryConsumptionPolicy,
			Short powerConsumptionPolicy, Set<TbServerBelong> tbServerBelongs,
			Set<TbPolicySever> tbPolicySevers,
			Set<TbHistoryServerInfo> tbHistoryServerInfos) {
		this.groupId = groupId;
		this.serverName = serverName;
		this.operateSystem = operateSystem;
		this.idracIp = idracIp;
		this.idracUser = idracUser;
		this.idracPassword = idracPassword;
		this.serverIpIn = serverIpIn;
		this.serverIpOut = serverIpOut;
		this.serverDescription = serverDescription;
		this.cangetCpuConsumption = cangetCpuConsumption;
		this.cangetPowerConsumption = cangetPowerConsumption;
		this.cangetMemoryConsumption = cangetMemoryConsumption;
		this.cangetWindTemperature = cangetWindTemperature;
		this.cpuConsumptionPolicy = cpuConsumptionPolicy;
		this.memoryConsumptionPolicy = memoryConsumptionPolicy;
		this.powerConsumptionPolicy = powerConsumptionPolicy;
		this.tbServerBelongs = tbServerBelongs;
		this.tbPolicySevers = tbPolicySevers;
		this.tbHistoryServerInfos = tbHistoryServerInfos;
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

	@Column(name = "group_id")
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "server_name", nullable = false, length = 20)
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Column(name = "operate_system", nullable = false, length = 20)
	public String getOperateSystem() {
		return this.operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	@Column(name = "idrac_ip", nullable = false, length = 20)
	public String getIdracIp() {
		return this.idracIp;
	}

	public void setIdracIp(String idracIp) {
		this.idracIp = idracIp;
	}

	@Column(name = "idrac_user", nullable = false, length = 20)
	public String getIdracUser() {
		return this.idracUser;
	}

	public void setIdracUser(String idracUser) {
		this.idracUser = idracUser;
	}

	@Column(name = "idrac_password", nullable = false, length = 20)
	public String getIdracPassword() {
		return this.idracPassword;
	}

	public void setIdracPassword(String idracPassword) {
		this.idracPassword = idracPassword;
	}

	@Column(name = "server_ip_in", nullable = false, length = 20)
	public String getServerIpIn() {
		return this.serverIpIn;
	}

	public void setServerIpIn(String serverIpIn) {
		this.serverIpIn = serverIpIn;
	}

	@Column(name = "server_ip_out", length = 20)
	public String getServerIpOut() {
		return this.serverIpOut;
	}

	public void setServerIpOut(String serverIpOut) {
		this.serverIpOut = serverIpOut;
	}

	@Column(name = "server_description", length = 20)
	public String getServerDescription() {
		return this.serverDescription;
	}

	public void setServerDescription(String serverDescription) {
		this.serverDescription = serverDescription;
	}

	@Column(name = "canget_cpu_consumption")
	public Short getCangetCpuConsumption() {
		return this.cangetCpuConsumption;
	}

	public void setCangetCpuConsumption(Short cangetCpuConsumption) {
		this.cangetCpuConsumption = cangetCpuConsumption;
	}

	@Column(name = "canget_power_consumption")
	public Short getCangetPowerConsumption() {
		return this.cangetPowerConsumption;
	}

	public void setCangetPowerConsumption(Short cangetPowerConsumption) {
		this.cangetPowerConsumption = cangetPowerConsumption;
	}

	@Column(name = "canget_memory_consumption")
	public Short getCangetMemoryConsumption() {
		return this.cangetMemoryConsumption;
	}

	public void setCangetMemoryConsumption(Short cangetMemoryConsumption) {
		this.cangetMemoryConsumption = cangetMemoryConsumption;
	}

	@Column(name = "canget_wind_temperature")
	public Short getCangetWindTemperature() {
		return this.cangetWindTemperature;
	}

	public void setCangetWindTemperature(Short cangetWindTemperature) {
		this.cangetWindTemperature = cangetWindTemperature;
	}

	@Column(name = "cpu_consumption_policy")
	public Short getCpuConsumptionPolicy() {
		return this.cpuConsumptionPolicy;
	}

	public void setCpuConsumptionPolicy(Short cpuConsumptionPolicy) {
		this.cpuConsumptionPolicy = cpuConsumptionPolicy;
	}

	@Column(name = "memory_consumption_policy")
	public Short getMemoryConsumptionPolicy() {
		return this.memoryConsumptionPolicy;
	}

	public void setMemoryConsumptionPolicy(Short memoryConsumptionPolicy) {
		this.memoryConsumptionPolicy = memoryConsumptionPolicy;
	}

	@Column(name = "power_consumption_policy")
	public Short getPowerConsumptionPolicy() {
		return this.powerConsumptionPolicy;
	}

	public void setPowerConsumptionPolicy(Short powerConsumptionPolicy) {
		this.powerConsumptionPolicy = powerConsumptionPolicy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbServer")
	public Set<TbServerBelong> getTbServerBelongs() {
		return this.tbServerBelongs;
	}

	public void setTbServerBelongs(Set<TbServerBelong> tbServerBelongs) {
		this.tbServerBelongs = tbServerBelongs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbServer")
	public Set<TbPolicySever> getTbPolicySevers() {
		return this.tbPolicySevers;
	}

	public void setTbPolicySevers(Set<TbPolicySever> tbPolicySevers) {
		this.tbPolicySevers = tbPolicySevers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbServer")
	public Set<TbHistoryServerInfo> getTbHistoryServerInfos() {
		return this.tbHistoryServerInfos;
	}

	public void setTbHistoryServerInfos(
			Set<TbHistoryServerInfo> tbHistoryServerInfos) {
		this.tbHistoryServerInfos = tbHistoryServerInfos;
	}

}