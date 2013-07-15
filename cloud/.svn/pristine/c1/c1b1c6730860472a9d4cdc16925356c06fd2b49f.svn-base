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
 * 采购-厂家进项发票
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_invoice")
public class TbPurchaseInvoice implements java.io.Serializable{
	private static final long serialVersionUID = 5712184754337744504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private TbSuppliers suppliers; 
	
	/**
	 * 采购公司
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_company_id", nullable = false)
	private TbPurchaseCompany purchaseCompany; 
	
	/**
	 * 采购合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchaseorder_contractfile_id", nullable = false)
	private TbPurchaseOrderContractFile purchaseOrderContractFile; 
	
	/**
	 * 供应商帐号
	 */
	@Column(name = "purchase_account", unique = false, updatable = true, nullable = false, insertable = true,length=100)
	private String PurchaseAccount;
	
	/**
	 * 供应商开户行
	 */
	@Column(name = "purchase_bank_open", unique = false, updatable = true, nullable = false, insertable = true,length=100)
	private String PurchaseBankOpen;
	
	/**
	 * 纳税人识别号
	 */
	@Column(name = "taxpayer_no", unique = false, updatable = true, nullable = false, insertable = true,length=100)
	private String taxpayerNo;
	
	/**
	 * 开票日期
	 */
	@Column(name = "date_invoice", unique = false, updatable = true, nullable = false, insertable = true,length=100)
	private String dateInvoice;
	/**
	 * 发票编号
	 */
	@Column(name = "invoice_no", unique = false, updatable = true, nullable = false, insertable = true,length=100)
	private String invoiceNo;
	
	/**
	 * 金额
	 */
	@Column(name = "amount", unique = false, updatable = true, nullable = false, insertable = true)
	private Double amount;
	
	/**
	 * 进项发票文件路径
	 */
	@Column(name = "path_name", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String pathName;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;


	public TbPurchaseOrderContractFile getPurchaseOrderContractFile() {
		return purchaseOrderContractFile;
	}

	public void setPurchaseOrderContractFile(
			TbPurchaseOrderContractFile purchaseOrderContractFile) {
		this.purchaseOrderContractFile = purchaseOrderContractFile;
	}

	public String getPurchaseAccount() {
		return PurchaseAccount;
	}

	public void setPurchaseAccount(String purchaseAccount) {
		PurchaseAccount = purchaseAccount;
	}

	public String getPurchaseBankOpen() {
		return PurchaseBankOpen;
	}

	public void setPurchaseBankOpen(String purchaseBankOpen) {
		PurchaseBankOpen = purchaseBankOpen;
	}

	public String getTaxpayerNo() {
		return taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}

	public String getDateInvoice() {
		return dateInvoice;
	}

	public void setDateInvoice(String dateInvoice) {
		this.dateInvoice = dateInvoice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public TbPurchaseCompany getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(TbPurchaseCompany purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	} 
	
	
}
