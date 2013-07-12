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
 * 合同订单关联表
 * @author YuKy1327
 *
 */
@Entity
@Table(name = "tb_contract_order_relationship")
public class TbContractOrderRelationship   implements java.io.Serializable{
	private static final long serialVersionUID = -1101446898567312422L;
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
	 * 合同报价表的机型配置数据
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "config_model_id", nullable = false)
	private TbConfigModels configModels;
	
	/**
	 * 订购数量
	 */
	@Column(name = "order_quantity", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer orderQuantity;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;

	/**
	 * 合同报价表的对象
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_quotations_id", nullable = false)
	private TbQuotations quotations;
	
	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = true)
	private TbSuppliers suppliers;
	
	/**
	 * 供应商报价表
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_quotations_id", nullable = true)
	private TbSuppliersQuotations suppliersQuotations;
	
	/**
	 * 采购总价格，人工输入(不自动计算)
	 */
	@Column(name = "purchase_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double purchasePrice;
	
	public TbQuotations getQuotations() {
		return this.quotations;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public void setQuotations(TbQuotations quotations) {
		this.quotations = quotations;
	}

	public Integer getId() {
		return id;
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

	public TbConfigModels getConfigModels() {
		return this.configModels;
	}

	public void setConfigModels(TbConfigModels configModels) {
		this.configModels = configModels;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public TbSuppliersQuotations getSuppliersQuotations() {
		return suppliersQuotations;
	}

	public void setSuppliersQuotations(TbSuppliersQuotations suppliersQuotations) {
		this.suppliersQuotations = suppliersQuotations;
	}
}
