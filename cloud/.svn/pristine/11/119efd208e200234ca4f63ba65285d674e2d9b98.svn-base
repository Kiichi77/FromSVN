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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 合同模版与合同文件关系表
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_templates_relationship")
public class TbContractTemplatesRelationship implements java.io.Serializable{
	private static final long serialVersionUID = 2204706444011192325L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同模版
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_templates_id", nullable = false)
	private TbContractTemplates contractTemplates;
	
	/**
	 * 合同文件
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_templates_doc_id", nullable = false)
	private TbContractTemplatesDoc contractTemplatesDoc;

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

	public TbContractTemplatesDoc getContractTemplatesDoc() {
		return contractTemplatesDoc;
	}

	public void setContractTemplatesDoc(TbContractTemplatesDoc contractTemplatesDoc) {
		this.contractTemplatesDoc = contractTemplatesDoc;
	}
	
}
