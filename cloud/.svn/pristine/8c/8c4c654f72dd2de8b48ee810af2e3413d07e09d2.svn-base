package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 报价表
 * 
 * @author zhangjh 新增日期：2012-12-5
 * @since ContractManage
 */
@Entity
@Table(name = "tb_quotations")
public class TbQuotations implements java.io.Serializable{
	private static final long serialVersionUID = 7655067900007759823L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	/**
	 * 报价表名称
	 */
	@Column(name = "title", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String title;
	
	/**
	 * 
	 * 状态,0:无效，1：未审核，2：待审核，3：审核通过，4：审核不通过
	 * <code>S_Quotations</code>
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 报价表下载地址.xlsx
	 */
	@Column(name = "down_url", unique = false, updatable = true, nullable = true, insertable = true, length = 200)
	private String downUrl;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;
	
	/**
	 * 报价表类型：1:普通报价表 2：临时合同机型配置转报价表<br/>
	 * insert初始化为1,请update方法修改类型
	 */
	@Column(name = "quotation_type", unique = false, updatable = true, nullable = true, insertable = false,columnDefinition="int(1) default 1")
	private Integer quotationType;
	
	/**
	 * 项目
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = true)
	private TbProject project;
	
	@OneToMany(mappedBy="quotations")
	@Cascade({CascadeType.ALL})
	private Set<TbQuotationsData> quotationsMembers;
	

	public TbProject getProject() {
		return project;
	}

	public Integer getQuotationType() {
		return quotationType;
	}

	public void setQuotationType(Integer quotationType) {
		this.quotationType = quotationType;
	}

	public void setProject(TbProject project) {
		this.project = project;
	}

	public Set<TbQuotationsData> getQuotationsMembers() {
		return quotationsMembers;
	}

	public void setQuotationsMembers(Set<TbQuotationsData> quotationsMembers) {
		this.quotationsMembers = quotationsMembers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public TbUser getCreator() {
		return creator;
	}

	public void setCreator(TbUser creator) {
		this.creator = creator;
	}
	
}
