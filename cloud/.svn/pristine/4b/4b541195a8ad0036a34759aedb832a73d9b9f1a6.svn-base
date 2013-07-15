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
* 合同模版文件变量设置
* @author zhangjh 新增日期：2012-12-6
* @since ContractManage
 */
@Entity
@Table(name = "tb_contract_templates_doc_variable")
public class TbContractTemplatesDocVariable implements java.io.Serializable{
	private static final long serialVersionUID = 3767520439868448820L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同模版文件
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "templates_doc_id", nullable = false)
	private TbContractTemplatesDoc templatesDoc;
	
	/**
	 * 变量名称
	 */
	@Column(name = "variable_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String variableName;
	
	/**
	 * 描述
	 */
	@Column(name = "description", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractTemplatesDoc getTemplatesDoc() {
		return templatesDoc;
	}

	public void setTemplatesDoc(TbContractTemplatesDoc templatesDoc) {
		this.templatesDoc = templatesDoc;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TbContractTemplatesDocVariable [id=" + id + ", templatesDoc="
				+ templatesDoc + ", variableName=" + variableName
				+ ", description=" + description + "]";
	}
	
	
}
