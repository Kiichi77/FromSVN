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
 * 合同订单
 * @author YuKy1327
 *
 */
@Entity
@Table(name = "tb_contract_order")
public class TbContractOrder  implements java.io.Serializable{
	private static final long serialVersionUID = 5621266422279067110L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同类型（1：正式合同-><code>TbContract</code>，2：临时合同-><code>TbContractTemporary</code>）<br/>
	 * 当为1时：TbContract contract不能为空<br/>
	 * 当为2时：TbContractTemporary contractTemporary 不能为空
	 */
	@Column(name = "contract_type", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer contractType;
	
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = true)
	private TbContract contract;
	
	
	/**
	 * 临时合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_temporary_id", nullable = true)
	private TbContractTemporary contractTemporary;
	
	/**
	 * 备注
	 */
	@Column(name = "note", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String note;
	
	/**
	 * 订单编号
	 */
	@Column(name = "order_no", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String orderNo;
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
	 * 收货信息<br/>
	 * 格式：广东省广州市天河区云景机房（张建华收）15817130480
	 */
	@Column(name = "receive_info", unique = false, updatable = true, nullable = true, insertable = true, length = 500)
	private String receiveInfo;
	/**
	 * 收货人
	 */
	@Column(name = "consignee", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String consignee;
	/**
	 * 收货人联系号码
	 */
	@Column(name = "consignee_phone", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String consigneePhone;
	/**
	 * 状态,见<code>S_ContractOrder</code>
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 审核时间
	 */
	@Column(name = "caudit_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date caudittime;
	
	/**
	 * 审核者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caudit_id", nullable = true)
	private TbUser caudit ;
	
	/**
	 * 采购人员
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_id", nullable = true)
	private TbUser purchase ;
	
	/**
	 * 采购公司
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_company_id", nullable = true)
	private TbPurchaseCompany purchaseCompany;
	
	/**
	 * 订单下所有的数据
	 */
	@OneToMany(mappedBy="contractOrder")
	@Cascade({CascadeType.ALL})
	private Set<TbContractOrderRelationship> contractOrderRelationship;
	
	public String getReceiveInfo() {
		return receiveInfo;
	}


	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}


	public Integer getId() {
		return id;
	}


	public Date getCaudittime() {
		return caudittime;
	}

	public void setCaudittime(Date caudittime) {
		this.caudittime = caudittime;
	}

	/**
	 * 合同类型（1：正式合同-><code>TbContract</code>，2：临时合同-><code>TbContractTemporary</code>）<br/>
	 * 当为1时：TbContract contract不能为空<br/>
	 * 当为2时：TbContractTemporary contractTemporary 不能为空
	 */
	public Integer getContractType() {
		return contractType;
	}

	/**
	 * 合同类型（1：正式合同-><code>TbContract</code>，2：临时合同-><code>TbContractTemporary</code>）<br/>
	 * 当为1时：TbContract contract不能为空<br/>
	 * 当为2时：TbContractTemporary contractTemporary 不能为空
	 */
	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public TbContractTemporary getContractTemporary() {
		return contractTemporary;
	}


	public void setContractTemporary(TbContractTemporary contractTemporary) {
		this.contractTemporary = contractTemporary;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public TbUser getCaudit() {
		return caudit;
	}

	public void setCaudit(TbUser caudit) {
		this.caudit = caudit;
	}

	public TbPurchaseCompany getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(TbPurchaseCompany purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public TbUser getPurchase() {
		return purchase;
	}

	public void setPurchase(TbUser purchase) {
		this.purchase = purchase;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<TbContractOrderRelationship> getContractOrderRelationship() {
		return contractOrderRelationship;
	}

	public void setContractOrderRelationship(
			Set<TbContractOrderRelationship> contractOrderRelationship) {
		this.contractOrderRelationship = contractOrderRelationship;
	}


	public String getConsignee() {
		return consignee;
	}


	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}


	public String getConsigneePhone() {
		return consigneePhone;
	}


	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	
	
}
