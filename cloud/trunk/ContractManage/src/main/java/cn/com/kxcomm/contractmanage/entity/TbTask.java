package cn.com.kxcomm.contractmanage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the tb_task_url database table. 任务表
 */
@Entity
@Table(name = "tb_task")
public class TbTask implements Serializable {
	private static final long serialVersionUID = -5860975181698105471L;

	/**
	 * 任务序号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Long id;

	/**
	 * 发起人
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sponsor_id", nullable = false)
	private TbUser sponsorId;

	/**
	 * 任务名称,见<code>TaskName</code>定义
	 */
	@Column(name = "task_name", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String taskName;

	/**
	 * 任务类型，0：角色，1：个人
	 */
	@Column(name = "task_type", unique = false, updatable = false, nullable = true, insertable = true, length = 100)
	private String taskType;

	/**
	 * 负责人
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_in_charge_id", nullable = true)
	private TbUser personInChargeId;

	/**
	 * 负责角色
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = true)
	private TbRole role;

	/**
	 * 分配时间
	 */
	@Column(name = "allocation_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date allocationTime;

	/**
	 * 完成时间
	 */
	@Column(name = "finish_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date finishTime;

	/**
	 * 状态，0：未处理，1：已完成，2：处理中，3：打回
	 */
	@Column(name = "state", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer state;

	/**
	 * 项目
	 */
	// @JoinColumn(name = "project_id", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = true)
	private TbProject project;

	/**
	 * 备注
	 */
	@Column(name = "remark", unique = false, updatable = true, nullable = true, insertable = true, length = 200)
	private String remark;

	/**
	 * 跳转地址
	 */
	@Column(name = "url", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String url;

	/**
	 * 关联业务的主键id
	 */
	@Column(name = "param_pkid", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String paramPkid;

	public TbRole getRole() {
		return role;
	}

	public void setRole(TbRole role) {
		this.role = role;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public TbUser getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(TbUser sponsorId) {
		this.sponsorId = sponsorId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TbUser getPersonInChargeId() {
		return personInChargeId;
	}

	public void setPersonInChargeId(TbUser personInChargeId) {
		this.personInChargeId = personInChargeId;
	}

	public Date getAllocationTime() {
		return allocationTime;
	}

	public void setAllocationTime(Date allocationTime) {
		this.allocationTime = allocationTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TbProject getProject() {
		return project;
	}

	public void setProject(TbProject project) {
		this.project = project;
	}

	public String getParamPkid() {
		return paramPkid;
	}

	public void setParamPkid(String paramPkid) {
		this.paramPkid = paramPkid;
	}

}