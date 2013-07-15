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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 配置单数据
 * 
 * @author zhangjh 新增日期：2012-12-5
 * @since ContractManage
 */
@Entity
@Table(name = "tb_configmodel_data")
public class TbModelData implements java.io.Serializable {
	private static final long serialVersionUID = 3346504710781335689L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Integer id;
	/**
	 * 机型配置单
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.REMOVE })
	@JoinColumn(name = "config_model_id", nullable = false)
	private TbConfigModels configModel;
	/**
	 * 数据源数据
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "data_record_id", nullable = false)
	private TbDataRecord dataRecord;
	/**
	 * 数量
	 */
	@Column(name = "quantity", unique = false, updatable = true, nullable = false, insertable = true)
	private Double quantity;
	/**
	 * 折扣率
	 */
	@Column(name = "discountRate", unique = false, updatable = true, nullable = false, insertable = true)
	private Double discountRate;

	/**
	 * 第一年保修费
	 */
	@Column(name = "first_year", unique = false, updatable = true, nullable = false, insertable = true)
	private Double firstYear;

	/**
	 * 第二年保修费
	 */
	@Column(name = "second_year", unique = false, updatable = true, nullable = false, insertable = true)
	private Double secondYear;

	/**
	 * 第三年保修费
	 */
	@Column(name = "third_year", unique = false, updatable = true, nullable = false, insertable = true)
	private Double thirdYear;
	
	/**
	 * 目录合价
	 */
	@Column(name = "catalog_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double catalogPrice;
	
	/**
	 * 折扣后价格
	 */
	@Column(name = "discounted_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double discountedPrice;
	
	/**
	 * 折扣后现场价
	 */
	@Column(name = "discounted_after_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double discountAfterPrice;
	
	/**
	 * 合计
	 */
	@Column(name = "total_price", unique = false, updatable = true, nullable = true, insertable = true)
	private Double totalPrice;

	public Integer getId() {
		return id;
	}

	public Double getCatalogPrice() {
		return catalogPrice;
	}

	public void setCatalogPrice(Double catalogPrice) {
		this.catalogPrice = catalogPrice;
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Double getDiscountAfterPrice() {
		return discountAfterPrice;
	}

	public void setDiscountAfterPrice(Double discountAfterPrice) {
		this.discountAfterPrice = discountAfterPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbConfigModels getConfigModel() {
		return configModel;
	}

	public void setConfigModel(TbConfigModels configModel) {
		this.configModel = configModel;
	}

	public TbDataRecord getDataRecord() {
		return dataRecord;
	}

	public void setDataRecord(TbDataRecord dataRecord) {
		this.dataRecord = dataRecord;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public Double getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(Double firstYear) {
		this.firstYear = firstYear;
	}

	public Double getSecondYear() {
		return secondYear;
	}

	public void setSecondYear(Double secondYear) {
		this.secondYear = secondYear;
	}

	public Double getThirdYear() {
		return thirdYear;
	}

	public void setThirdYear(Double thirdYear) {
		this.thirdYear = thirdYear;
	}

}
