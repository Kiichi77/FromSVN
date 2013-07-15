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
 * 报价表数据
 * 
 * @author zhangjh 新增日期：2012-12-5
 * @since ContractManage
 */
@Entity
@Table(name = "tb_quotations_data")
public class TbQuotationsData implements java.io.Serializable{
	private static final long serialVersionUID = 684024723225947343L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 报价表
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quotations_id", nullable = false)
	private TbQuotations quotations;
	
	/**
	 * 机型配置单总表
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "config_models_id", nullable = false)
	private TbConfigModels configModels;
	
	/**
	 * 项目名称
	 */
	@Column(name = "project_name", unique = false, updatable = true, nullable = false, insertable = true, length = 50)
	private String projectName;
	
	/**
	 * 名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String name;
	
	/**
	 * 数量
	 */
	@Column(name = "quantity", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer quantity;
	
	/**
	 * 已下单数量
	 */
	@Column(name = "ordered", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer ordered;
	
	/**
	 * 被使用(临时合同转正式合同)
	 */
	@Column(name = "to_used", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer toUsed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbQuotations getQuotations() {
		return quotations;
	}

	public void setQuotations(TbQuotations quotations) {
		this.quotations = quotations;
	}

	public TbConfigModels getConfigModels() {
		return configModels;
	}

	public void setConfigModels(TbConfigModels configModels) {
		this.configModels = configModels;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public Integer getToUsed() {
		return toUsed;
	}

	public void setToUsed(Integer toUsed) {
		this.toUsed = toUsed;
	}
}
