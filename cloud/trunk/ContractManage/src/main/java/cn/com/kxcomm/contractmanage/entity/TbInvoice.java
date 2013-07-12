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
 * 发票管理
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_invoice")
public class TbInvoice  implements java.io.Serializable{
	private static final long serialVersionUID = -773674224416245172L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 客户公司名称
	 * 抬头
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private TbCustomer customer;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContract contract;
	
	/**
	 * 增值税类型
	 */
	@Column(name = "tax_type", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer taxType;
	
	/**
	 * 金额
	 */
	@Column(name = "amount", unique = false, updatable = true, nullable = false, insertable = true)
	private Double amount;
	
	/**
	 * 公司名称(1、北京市康讯通讯设备有限公司  2、北京云上云天信息技术有限公司)
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_company_id", nullable = false)
	private TbPurchaseCompany purchaseCompany;
	
	/**
	 * 状态（1：申请中，2：快递中，3：已送达）
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 创建人
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser create;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public Integer getTaxType() {
		return taxType;
	}

	public void setTaxType(Integer taxType) {
		this.taxType = taxType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public TbPurchaseCompany getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(TbPurchaseCompany purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
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

	public TbUser getCreate() {
		return create;
	}

	public void setCreate(TbUser create) {
		this.create = create;
	}
	
}
