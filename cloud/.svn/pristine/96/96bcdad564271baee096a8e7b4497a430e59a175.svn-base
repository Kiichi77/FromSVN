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
 * 合同文件的变量设置
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_doc_variable")
public class TbContractDocVariable implements java.io.Serializable{

	private static final long serialVersionUID = -2247135329419892374L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同文件
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_doc_id", nullable = false)
	private TbContractDoc contractDoc;
	
	/**
	 * 变量名称
	 */
	@Column(name = "variable_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String variableName;
	
	/**
	 * 内容
	 */
	@Column(name = "content", unique = false, updatable = true, nullable = true, insertable = true)
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractDoc getContractDoc() {
		return contractDoc;
	}

	public void setContractDoc(TbContractDoc contractDoc) {
		this.contractDoc = contractDoc;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "TbContractDocVariable [id=" + id + ", contractDoc="
				+ contractDoc + ", variableName=" + variableName + ", content="
				+ content + "]";
	}
	
}
