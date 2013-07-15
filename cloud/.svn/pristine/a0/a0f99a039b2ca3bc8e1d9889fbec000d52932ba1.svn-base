package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 项目
 * @author YuKy1327
 *
 */
@Entity
@Table(name = "tb_project")
public class TbProject implements java.io.Serializable{
	private static final long serialVersionUID = -768465780007802803L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="project_id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer projectId;
	
	/**
	 * 项目名称
	 */
	@Column(name = "project_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String projectName;
	
	/**
	 * 项目描述
	 */
	@Column(name = "description", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String description;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;
	
	/**
	 * 客户
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private TbCustomer customer;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = false, nullable = false, insertable = true)
	private Date createtime;
	

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "TbProject [projectId=" + this.projectId + ", projectName="
				+ this.projectName + ", description=" + this.description
				+ ", createtime=" + this.createtime + "]";
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TbUser getCreator() {
		return creator;
	}

	public void setCreator(TbUser creator) {
		this.creator = creator;
	}

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
