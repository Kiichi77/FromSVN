package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 合同模版文件
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_templates_doc")
public class TbContractTemplatesDoc implements java.io.Serializable{
	private static final long serialVersionUID = -6340306812622756861L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 文件名称
	 */
	@Column(name = "doc_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String docname;
	
	/**
	 * 路径
	 */
	@Column(name = "path", unique = false, updatable = true, nullable = false, insertable = true, length = 200)
	private String path;
	
	/**
	 * 是否设置变量,0:无变量,1:有变量
	 */
	@Column(name = "b_variable", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer bVariable;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	@OneToMany(mappedBy="templatesDoc")
	@Cascade({CascadeType.ALL})
	private Set<TbContractTemplatesDocVariable> contractTemplatesDocVariableMembers;
	
	@OneToMany(mappedBy="doc")
	@Cascade({CascadeType.ALL})
	private Set<TbContractTemplatesDocType> contractTemplatesDocTypeMembers;
	
	@OneToMany(mappedBy="doc")
	@Cascade({CascadeType.ALL})
	private Set<TbContractDoc> contractDocMembers;

	public Set<TbContractTemplatesDocVariable> getContractTemplatesDocVariableMembers() {
		return contractTemplatesDocVariableMembers;
	}

	public void setContractTemplatesDocVariableMembers(
			Set<TbContractTemplatesDocVariable> contractTemplatesDocVariableMembers) {
		this.contractTemplatesDocVariableMembers = contractTemplatesDocVariableMembers;
	}

	public Set<TbContractTemplatesDocType> getContractTemplatesDocTypeMembers() {
		return contractTemplatesDocTypeMembers;
	}

	public void setContractTemplatesDocTypeMembers(
			Set<TbContractTemplatesDocType> contractTemplatesDocTypeMembers) {
		this.contractTemplatesDocTypeMembers = contractTemplatesDocTypeMembers;
	}

	public Set<TbContractDoc> getContractDocMembers() {
		return contractDocMembers;
	}

	public void setContractDocMembers(Set<TbContractDoc> contractDocMembers) {
		this.contractDocMembers = contractDocMembers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getbVariable() {
		return bVariable;
	}

	public void setbVariable(Integer bVariable) {
		this.bVariable = bVariable;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
