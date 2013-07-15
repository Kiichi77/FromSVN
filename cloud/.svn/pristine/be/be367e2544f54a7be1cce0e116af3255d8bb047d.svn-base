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
* 功能描述:合同模板审核表
* @author chenliang 新增日期：2013-3-4
* @since ContractManage
 */
@Entity
@Table(name = "tb_template_checked")
public class TbTemplateChecked implements java.io.Serializable {
	private static final long serialVersionUID = 4303672250456507914L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Integer id;

	/**
	 * 合同模版
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_templates_id", nullable = false)
	private TbContractTemplates contractTemplates;

	/**
	 * 审核人
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checker_id", nullable = true)
	private TbUser checker;

	/**
	 * 审核状态
	 */
	@Column(name = "state", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private Integer state;

	/**
	 * 批示
	 */
	@Column(name = "instructions", unique = false, updatable = true, nullable = false, insertable = true,length=200)
	private String instructions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractTemplates getContractTemplates() {
		return contractTemplates;
	}

	public void setContractTemplates(TbContractTemplates contractTemplates) {
		this.contractTemplates = contractTemplates;
	}

	public TbUser getChecker() {
		return checker;
	}

	public void setChecker(TbUser checker) {
		this.checker = checker;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
