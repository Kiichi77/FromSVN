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
 * 合同
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract")
public class TbContract implements java.io.Serializable{
	private static final long serialVersionUID = 4303672250456507914L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同模版
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_templates_id", nullable = false)
	private TbContractTemplates contractTemplates;
	
	/**
	 * 合同名称
	 */
	@Column(name = "title", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String title;
	
	/**
	 * 合同编号(手动输入 )
	 */
	@Column(name = "contract_number", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String contractNumber;
	
	/**
	 * 合同内部编号
	 */
	@Column(name = "contract_no", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String contractNo;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 状态<br/>
	 * 见<code>S_Contract</code>
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 合同打包文件下载
	 */
	@Column(name = "download_url", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String downloadUrl;
	
	/**
	 * 价格总表地址
	 */
	@Column(name = "quotations_excel", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String quotationsExcel;
	

	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;
	
	/**
	 * 项目
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	private TbProject project;
	
	/**
	 * 收货证明
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "arrivalProved_id", nullable = true)
	private TbArrivalProved arrivalProved;
	
	@OneToMany(mappedBy="contract")
	@Cascade({CascadeType.ALL})
	private Set<TbContractDoc> contractDocMembers;
	
	@OneToMany(mappedBy="contract")
	@Cascade({CascadeType.ALL})
	private Set<TbContractQuotations> quotationsMembers;
	
	public TbArrivalProved getArrivalProved() {
		return arrivalProved;
	}
	public void setArrivalProved(TbArrivalProved arrivalProved) {
		this.arrivalProved = arrivalProved;
	}
	@Override
	public String toString() {
		return "TbContract [id=" + id + ", contractTemplates="
				+ contractTemplates + ", title=" + title + ", contractNo="
				+ contractNo + ", createtime=" + createtime + ", status="
				+ status + ", downloadUrl=" + downloadUrl
				+ ", quotationsExcel=" + quotationsExcel + ", creator=" + creator + ", project=" + project + "]";
	}
	public Set<TbContractDoc> getContractDocMembers() {
		return contractDocMembers;
	}
	public void setContractDocMembers(Set<TbContractDoc> contractDocMembers) {
		this.contractDocMembers = contractDocMembers;
	}

	public Set<TbContractQuotations> getQuotationsMembers() {
		return quotationsMembers;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public void setQuotationsMembers(Set<TbContractQuotations> quotationsMembers) {
		this.quotationsMembers = quotationsMembers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TbContractTemplates getContractTemplates() {
		return contractTemplates;
	}
	public void setContractTemplates(TbContractTemplates contractTemplates) {
		this.contractTemplates = contractTemplates;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getQuotationsExcel() {
		return quotationsExcel;
	}
	public void setQuotationsExcel(String quotationsExcel) {
		this.quotationsExcel = quotationsExcel;
	}
	
	public TbUser getCreator() {
		return creator;
	}
	public void setCreator(TbUser creator) {
		this.creator = creator;
	}
	
	public TbProject getProject() {
		return project;
	}

	public void setProject(TbProject project) {
		this.project = project;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
}
