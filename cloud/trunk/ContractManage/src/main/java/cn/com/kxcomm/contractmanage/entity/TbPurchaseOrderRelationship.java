package cn.com.kxcomm.contractmanage.entity;

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
 * 采购与合同订单关联表
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_order_relationship")
public class TbPurchaseOrderRelationship  implements java.io.Serializable{
	private static final long serialVersionUID = 5712184754337744504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 采购订单
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_order_id", nullable = false)
	private TbPurchaseOrder purchaseOrder;
	
	/**
	 * 合同订单关联表
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractorderrelationship_id", nullable = false)
	private TbContractOrderRelationship contractOrderRelationship;
	
	/**
	 * 采购数量
	 */
	@Column(name = "purchase_quantity", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer purchaseQuantity;
	
	@OneToMany(mappedBy="purchaseOrderRelationship")
	@Cascade({CascadeType.ALL})
	private Set<TbPurchaseOrderRelationshipData> purchaseOrderRelationshipData;
	
	public Integer getId() {
		return id;
	}

	public Set<TbPurchaseOrderRelationshipData> getPurchaseOrderRelationshipData() {
		return purchaseOrderRelationshipData;
	}

	public void setPurchaseOrderRelationshipData(
			Set<TbPurchaseOrderRelationshipData> purchaseOrderRelationshipData) {
		this.purchaseOrderRelationshipData = purchaseOrderRelationshipData;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbPurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(TbPurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public TbContractOrderRelationship getContractOrderRelationship() {
		return contractOrderRelationship;
	}

	public void setContractOrderRelationship(
			TbContractOrderRelationship contractOrderRelationship) {
		this.contractOrderRelationship = contractOrderRelationship;
	}

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	
	
	
}
