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
 * 采购单
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_order")
public class TbPurchaseOrder  implements java.io.Serializable{
	private static final long serialVersionUID = 5712184754337744504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同订单
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_order_id", nullable = false)
	private TbContractOrder contractOrder;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_id", nullable = false)
	private TbUser create ;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private TbSuppliers suppliers; 
	
	/**
	 * 订购合同文件
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchaseordercontractfile_id", nullable = false)
	private TbPurchaseOrderContractFile purchaseOrderContractFile;
	
	/**
	 * 采购订单编号(查发票)
	 */
	@Column(name = "order_no", unique = false, updatable = true, nullable = true, insertable = true,length=30)
	private String orderNo;
	
	/**
	 * 报价单号(查收货)
	 */
	@Column(name = "quote_no", unique = false, updatable = true, nullable = true, insertable = true,length=30)
	private String quoteNo;
	
	/**
	 * 状态 1：待审核，2：审核同意，3：审核不同意，4：待付款，5：已付款，6：待收货，7：已收货,8:已签订采购合同
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer status;
	
	@OneToMany(mappedBy="purchaseOrder")
	@Cascade({CascadeType.ALL})
	private Set<TbPurchaseOrderRelationship> purchaseOrderRelationshipMembers;

	
	/**
	 * 收货时间
	 */
	@Column(name = "receiver_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date receivertime; 
	
	/**
	 * 售后人员
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aftersales_id", nullable = false)
	private TbUser afterSales ; 
	
	/**
	 * 备注
	 */
	@Column(name = "remark", unique = false, updatable = true, nullable = true, insertable = true, length = 150)
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getId() {
		return id;
	}

	public Set<TbPurchaseOrderRelationship> getPurchaseOrderRelationshipMembers() {
		return purchaseOrderRelationshipMembers;
	}

	public void setPurchaseOrderRelationshipMembers(
			Set<TbPurchaseOrderRelationship> purchaseOrderRelationshipMembers) {
		this.purchaseOrderRelationshipMembers = purchaseOrderRelationshipMembers;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractOrder getContractOrder() {
		return contractOrder;
	}

	public void setContractOrder(TbContractOrder contractOrder) {
		this.contractOrder = contractOrder;
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

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public TbPurchaseOrderContractFile getPurchaseOrderContractFile() {
		return purchaseOrderContractFile;
	}

	public void setPurchaseOrderContractFile(
			TbPurchaseOrderContractFile purchaseOrderContractFile) {
		this.purchaseOrderContractFile = purchaseOrderContractFile;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getReceivertime() {
		return receivertime;
	}

	public void setReceivertime(Date receivertime) {
		this.receivertime = receivertime;
	}

	public TbUser getAfterSales() {
		return afterSales;
	}

	public void setAfterSales(TbUser afterSales) {
		this.afterSales = afterSales;
	}

}
