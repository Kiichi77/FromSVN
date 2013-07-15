package cn.com.kxcomm.contractmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the tb_process_user database table.
 * 
 */
@Entity
@Table(name = "tb_process_user")
public class TbProcessUser implements java.io.Serializable {
	private static final long serialVersionUID = 7039551267783269420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Long id;

	@Column(name = "process_id", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String processId;

	@Column(name = "process_def_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String processDefName;
	
	@Column(name = "project_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "project_id", unique = false, updatable = true, nullable = true, insertable = true)
	private Long projectId;
	
	@Column(name = "contract_order_id", unique = false, updatable = true, nullable = true, insertable = true)
	private Long contractOrderId;

	/**
	 * 售后保存合同id
	 */
	@Column(name = "contract_id", unique = false, updatable = true, nullable = true, insertable = true)
	private Long contractId;
	
	/**
	 * 售后保存客户id
	 */
	@Column(name = "customer_id", unique = false, updatable = true, nullable = true, insertable = true)
	private Long customerId;
	
	/**
	 * 存储各个主键id
	 */
	@Column(name = "pkid", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer pkid; 
	
	public Integer getPkid() {
		return pkid;
	}

	public void setPkid(Integer pkid) {
		this.pkid = pkid;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(Long contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	@Column(name = "process_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String processName;

	@Column(name = "user_id", unique = false, updatable = true, nullable = false, insertable = true)
	private Long userId;
	
	@Column(name = "state", unique = false, updatable = true, nullable = true, insertable = true,length = 20)
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TbProcessUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}