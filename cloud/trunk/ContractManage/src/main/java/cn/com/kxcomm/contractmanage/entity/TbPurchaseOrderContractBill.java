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
* 采购订购合同付款明细
* @author zhangjh 新增日期：2013-3-7
* @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_order_contract_bill")
public class TbPurchaseOrderContractBill  implements java.io.Serializable{
	private static final long serialVersionUID = -679914621616134127L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 订购合同文件
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_order_contractfile_id", nullable = false)
	private TbPurchaseOrderContractFile purchaseOrderContractFile;
	
	/**
	 * 已支出金额
	 */
	@Column(name = "payment", unique = false, updatable = true, nullable = true, insertable = true)
	private Double payment;
	
	/**
	 * 付款人
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payer", nullable = false)
	private TbUser payer;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 采购公司
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_company_id", nullable = false)
	private TbPurchaseCompany purchaseCompany;
	
	/**
	 * 是否付多款( 0:没多付款，1：多付款)
	 */
	@Column(name = "is_overpay", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer isOverpay;
	
	/**
	 * 多付款的金额（实付款-应付款=多付款）
	 */
	@Column(name = "overpay_money", unique = false, updatable = true, nullable = true, insertable = true)
	private Double overPayMoney;
	
	/**
	 * 是否使用对冲( 0:不对冲，1：对冲)
	 */
	@Column(name = "use_overpay", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer useOverPay;
	
	/**
	 * 对冲金额
	 */
	@Column(name = "use_overpay_money", unique = false, updatable = true, nullable = true, insertable = true)
	private Double userOverPayMoney;
	

	/**
	 * 支票号
	 */
	@Column(name = "check_no", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String checkNo;
	
	/**
	 * 付款时间
	 */
	@Column(name = "payment_time", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String paymentTime;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUseOverPay() {
		return useOverPay;
	}

	public void setUseOverPay(Integer useOverPay) {
		this.useOverPay = useOverPay;
	}

	public Double getUserOverPayMoney() {
		return userOverPayMoney;
	}

	public void setUserOverPayMoney(Double userOverPayMoney) {
		this.userOverPayMoney = userOverPayMoney;
	}

	public TbPurchaseOrderContractFile getPurchaseOrderContractFile() {
		return purchaseOrderContractFile;
	}

	public TbPurchaseCompany getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(TbPurchaseCompany purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public void setPurchaseOrderContractFile(
			TbPurchaseOrderContractFile purchaseOrderContractFile) {
		this.purchaseOrderContractFile = purchaseOrderContractFile;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public TbUser getPayer() {
		return payer;
	}

	public void setPayer(TbUser payer) {
		this.payer = payer;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getIsOverpay() {
		return isOverpay;
	}

	public void setIsOverpay(Integer isOverpay) {
		this.isOverpay = isOverpay;
	}

	public Double getOverPayMoney() {
		return overPayMoney;
	}

	public void setOverPayMoney(Double overPayMoney) {
		this.overPayMoney = overPayMoney;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	
}
