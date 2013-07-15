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
import org.hibernate.type.TrueFalseType;

/**
 * 
 * 订购合同文件
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_order_contractfile")
public class TbPurchaseOrderContractFile  implements java.io.Serializable{
	private static final long serialVersionUID = -8188872071101176042L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 订购合同文件名称
	 */
	@Column(name = "title", unique = false, updatable = true, nullable = false, insertable = true, length = 200)
	private String title;
	
	/**
	 * 合同文件路径
	 */
	@Column(name = "path_name", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String pathName;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_id", nullable = false)
	private TbUser create;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContract contract;
	
	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private TbSuppliers suppliers;
	
	/**
	 * 是否已付款：0 待付款 1：已付款
	 */
	@Column(name = "is_pay", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer isPay;
	
	/**
	 * 金额
	 */
	@Column(name = "contract_money", unique = false, updatable = true, nullable = false, insertable = true)
	private Double contractMoney;
	

	/**
	 * 付款方式
	 */
	@Column(name = "payment_type", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String paymentType;
	
	/**
	 * 已支出，累加
	 */
	@Column(name = "payment", unique = false, updatable = true, nullable = true, insertable = true)
	private Double payment;
	
	/**
	 * 付款明细
	 */
	@OneToMany(mappedBy="purchaseOrderContractFile")
	@Cascade({CascadeType.ALL})
	private Set<TbPurchaseOrderContractBill> billMembers;
	
	/**
	 * 任务ID
	 */
	@Column(name = "process_id", unique = false, updatable = true, nullable = true, insertable = true)
	private String processId;
	
	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Set<TbPurchaseOrderContractBill> getBillMembers() {
		return billMembers;
	}

	public void setBillMembers(Set<TbPurchaseOrderContractBill> billMembers) {
		this.billMembers = billMembers;
	}

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public TbUser getCreate() {
		return create;
	}

	public void setCreate(TbUser create) {
		this.create = create;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
}
