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
* 合同模版文件与文件类型多对多关联表
* @author zhangjh 新增日期：2012-12-6
* @since ContractManage
 */
@Entity
@Table(name = "tb_contract_templates_doc_type")
public class TbContractTemplatesDocType  implements java.io.Serializable{
	private static final long serialVersionUID = -7754108982156051829L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同文件类型
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_type_id", nullable = false)
	private TbContractDocType docType;
	
	/**
	 * 合同文件
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_id", nullable = false)
	private TbContractTemplatesDoc doc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractDocType getDocType() {
		return docType;
	}

	public void setDocType(TbContractDocType docType) {
		this.docType = docType;
	}

	public TbContractTemplatesDoc getDoc() {
		return doc;
	}

	public void setDoc(TbContractTemplatesDoc doc) {
		this.doc = doc;
	}
	
	
}
