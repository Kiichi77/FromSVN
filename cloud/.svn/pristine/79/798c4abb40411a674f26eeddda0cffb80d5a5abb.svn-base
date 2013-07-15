package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 合同选中的文件
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_doc")
public class TbContractDoc implements java.io.Serializable{
	private static final long serialVersionUID = -1440763283071853364L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContract contract;
	
	/**
	 * 合同模版的文件
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doc_id", nullable = false)
	private TbContractTemplatesDoc doc;
	
	/**
	 * 添加日期
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 导出目录
	 */
	@Column(name = "export_path", unique = false, updatable = true, nullable = false, insertable = true, length = 200)
	private String exportPath;
	
	/**
	 * 文件名(用于显示的文件名称)
	 */
	@Column(name = "file_name", unique = false, updatable = true, nullable = false, insertable = true, length = 100)
	private String fileName;

	@OneToMany(mappedBy="contractDoc")
	@Cascade({CascadeType.ALL})
	private Set<TbContractDocVariable> contractDocVariableMembers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContract getContract() {
		return contract;
	}

	public String getExportPath() {
		return exportPath;
	}

	public void setExportPath(String exportPath) {
		this.exportPath = exportPath;
	}

	public Set<TbContractDocVariable> getContractDocVariableMembers() {
		return contractDocVariableMembers;
	}

	public void setContractDocVariableMembers(
			Set<TbContractDocVariable> contractDocVariableMembers) {
		this.contractDocVariableMembers = contractDocVariableMembers;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public TbContractTemplatesDoc getDoc() {
		return doc;
	}

	public void setDoc(TbContractTemplatesDoc doc) {
		this.doc = doc;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
