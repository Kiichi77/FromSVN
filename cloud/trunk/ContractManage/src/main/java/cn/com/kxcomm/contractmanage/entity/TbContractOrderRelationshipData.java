package cn.com.kxcomm.contractmanage.entity;

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
 * 合同订单详情<br/>
 * 机型配置单元数据
 * 
 * @author zhangjh 新增日期：2013-3-13
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_order_relationship_data")
public class TbContractOrderRelationshipData  implements java.io.Serializable{
	private static final long serialVersionUID = 5712184754337744504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 采购
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_order_relationship_id", nullable = false)
	private TbContractOrderRelationship contractOrderRelationship;

	/**
	 * 配置单数据
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_data_id", nullable = false)
	private TbModelData modelData;
	
	/**
	 * 订购数量(全通用单元有效,TbProductCategory.isRepeat=1时，该字段有效)
	 */
	@Column(name = "order_quantity", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer orderQuantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractOrderRelationship getContractOrderRelationship() {
		return contractOrderRelationship;
	}

	public void setContractOrderRelationship(
			TbContractOrderRelationship contractOrderRelationship) {
		this.contractOrderRelationship = contractOrderRelationship;
	}

	public TbModelData getModelData() {
		return modelData;
	}

	public void setModelData(TbModelData modelData) {
		this.modelData = modelData;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
