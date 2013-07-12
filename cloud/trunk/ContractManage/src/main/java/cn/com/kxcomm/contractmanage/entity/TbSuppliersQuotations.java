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
 * 功能描述:供应商报价表
 * 
 * @author chenliang 新增日期：2013-4-19
 * @since ContractManage
 */
@Entity
@Table(name = "tb_suppliers_quotations")
public class TbSuppliersQuotations implements java.io.Serializable {
	private static final long serialVersionUID = -768465780007802803L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "superliers_quotations_id", updatable = false, nullable = false, insertable = false, length = 32)
	private Integer superliersQuotationsId;

	/**
	 * 供应商报价表名称
	 */
	@Column(name = "suppliers_quotations_name", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String suppliersQuotationsName;

	/**
	 * 供应商报价表描述
	 */
	@Column(name = "description", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String description;

	/**
	 * 创建者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	private TbUser creator;

	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private TbSuppliers suppliers;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = false, nullable = false, insertable = true)
	private Date createtime;

	/**
	 * 供应商报价表文件下载地址(只保存文件名)
	 */
	@Column(name = "url", unique = false, updatable = false, nullable = false, insertable = true)
	private String url;
	
	/**
	 * 状态:0、已用，1、未用
	 */
	@Column(name = "state", unique = false, updatable = false, nullable = false, insertable = true)
	private Integer state;
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSuperliersQuotationsId() {
		return superliersQuotationsId;
	}

	public void setSuperliersQuotationsId(Integer superliersQuotationsId) {
		this.superliersQuotationsId = superliersQuotationsId;
	}

	public String getSuppliersQuotationsName() {
		return suppliersQuotationsName;
	}

	public void setSuppliersQuotationsName(String suppliersQuotationsName) {
		this.suppliersQuotationsName = suppliersQuotationsName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TbUser getCreator() {
		return creator;
	}

	public void setCreator(TbUser creator) {
		this.creator = creator;
	}

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TbSuppliersQuotations [superliersQuotationsId="
				+ superliersQuotationsId + ", suppliersQuotationsName="
				+ suppliersQuotationsName + ", description=" + description
				+ ", creator=" + creator + ", suppliers=" + suppliers
				+ ", createtime=" + createtime + ", url=" + url + "]";
	}

}
