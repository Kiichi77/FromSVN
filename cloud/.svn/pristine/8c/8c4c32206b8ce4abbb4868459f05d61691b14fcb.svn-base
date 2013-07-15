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
 * 数据源数据记录
 * 
 * @author zhangjh 新增日期：2012-12-4
 * @since ContractManage
 */
@Entity
@Table(name = "tb_data_record")
public class TbDataRecord implements java.io.Serializable {
	private static final long serialVersionUID = -337412164376225598L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Integer id;
	/**
	 * 产品分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type_id", nullable = false)
	private TbProductType productType;

	/**
	 * 数据源
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_source_id", nullable = false)
	private TbDataSource dataSource;

	/**
	 * 产品订货号
	 */
	private String productOrderNo;
	/**
	 * 产品描述
	 */
	private String productDesc;
	/**
	 * 数量
	 */
	private Double quantity;
	/**
	 * 目录单价
	 */
	private Double unitPrice;
	/**
	 * 折扣率
	 */
	private Double discountRate;
	/**
	 * 运保及其他费率
	 */
	private Double otherRates;
	/**
	 * 安装服务费
	 */
	private Double InstallServiceCharge;

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

	// 备注
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbProductType getProductType() {
		return productType;
	}

	public void setProductType(TbProductType productType) {
		this.productType = productType;
	}

	public TbDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(TbDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getProductOrderNo() {
		return productOrderNo;
	}

	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public Double getOtherRates() {
		return otherRates;
	}

	public void setOtherRates(Double otherRates) {
		this.otherRates = otherRates;
	}

	public Double getInstallServiceCharge() {
		return InstallServiceCharge;
	}

	public void setInstallServiceCharge(Double installServiceCharge) {
		InstallServiceCharge = installServiceCharge;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TbDataRecord [id=" + id + ", productOrderNo=" + productOrderNo
				+ ", productDesc=" + productDesc + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", discountRate=" + discountRate
				+ ", otherRates=" + otherRates + ", InstallServiceCharge="
				+ InstallServiceCharge + ", firstYear=" + firstYear
				+ ", secondYear=" + secondYear + ", thirdYear=" + thirdYear
				+ ", remark=" + remark + "]";
	}

	

}
