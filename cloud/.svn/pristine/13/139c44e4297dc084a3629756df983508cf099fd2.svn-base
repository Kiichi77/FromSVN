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
 * 机型配置单总表
 * 
 * @author zhangjh 新增日期：2012-12-5
 * @since ContractManage
 */
@Entity
@Table(name = "tb_config_models")
public class TbConfigModels implements java.io.Serializable{
	private static final long serialVersionUID = 2514372814674876963L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 数据源总表ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_source_id", nullable = false)
	private TbDataSource dataSource;
	
	/**
	 * 配置单名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String name;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 类型,0:配置单模版,1:配置单
	 */
	@Column(name = "type", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer type;
	
	/**
	 * 状态,0:无效,1:未审核，2:已审核
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 目录合价
	 */
	@Column(name = "catalog_total_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double catalogTotalprice;
	
	/**
	 * 折扣后价格
	 */
	@Column(name = "after_discount_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double afterDiscountPrice;
	
	/**
	 * 折扣后现场价
	 */
	@Column(name = "after_discount_now_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double afterDiscountNowPrice ;
	/**
	 * 合价
	 */
	@Column(name = "total_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double totalPrice;
	
	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;
	
	@OneToMany(mappedBy="configModel")
	@Cascade({CascadeType.ALL})
	private Set<TbModelData> modelDataMembers;
	


	public Set<TbModelData> getModelDataMembers() {
		return modelDataMembers;
	}

	
	public Double getCatalogTotalprice() {
		return catalogTotalprice;
	}

	public void setCatalogTotalprice(Double catalogTotalprice) {
		this.catalogTotalprice = catalogTotalprice;
	}

	public Double getAfterDiscountPrice() {
		return afterDiscountPrice;
	}

	public void setAfterDiscountPrice(Double afterDiscountPrice) {
		this.afterDiscountPrice = afterDiscountPrice;
	}

	public Double getAfterDiscountNowPrice() {
		return afterDiscountNowPrice;
	}

	public void setAfterDiscountNowPrice(Double afterDiscountNowPrice) {
		this.afterDiscountNowPrice = afterDiscountNowPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setModelDataMembers(Set<TbModelData> modelDataMembers) {
		this.modelDataMembers = modelDataMembers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbDataSource getDataSource() {
		return dataSource;
	}

	public TbUser getCreator() {
		return creator;
	}

	public void setCreator(TbUser creator) {
		this.creator = creator;
	}

	public void setDataSource(TbDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
