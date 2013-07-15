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
 * 
 * 临时合同对象
 * 
 * @author zhangjh 新增日期：2013-4-15
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_temporary")
public class TbContractTemporary  implements java.io.Serializable{
	private static final long serialVersionUID = -8823012080688705340L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 临时合同编号
	 */
	@Column(name = "contract_no", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String contractNo;
	
	/**
	 * 临时合同名称
	 */
	@Column(name = "title", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String title;
	
	/**
	 * 状态<br/>
	 * 见<code>S_Contract</code>
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;
	
	/**
	 * 临时合同文件
	 */
	@Column(name = "file_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String fileName;
	
	/**
	 * 临时合同总价
	 */
	@Column(name = "contract_total_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double contractTotalPrice;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public Double getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(Double contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public TbUser getCreator() {
		return creator;
	}

	public void setCreator(TbUser creator) {
		this.creator = creator;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
