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
 * 合同模版
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_templates")
public class TbContractTemplates implements java.io.Serializable{
	private static final long serialVersionUID = 3576042931514514222L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同模版名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 50)
	private String name;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createtime;
	
	/**
	 * 合同模版状态,0:审核中，1:未审核，2:销售审核通过,3:销售审核未通过,4:财务审核通过,5:财务审核未通过,6:经理审核通过,7:经理审核未通过
	 * <code>BusinessConstants.合同模板审核状态</code>
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	


	@OneToMany(mappedBy="contractTemplates")
	@Cascade({CascadeType.ALL})
	private Set<TbContractTemplatesRelationship> contractTemplatesRelationship;
	
	@OneToMany(mappedBy="contractTemplates")
	@Cascade({CascadeType.ALL})
	private Set<TbContract> contractMembers;
	
	@OneToMany(mappedBy="contractTemplates")
	@Cascade({CascadeType.ALL})
	private Set<TbTemplateChecked> templateCheckedmembers;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public Set<TbContractTemplatesRelationship> getContractTemplatesRelationship() {
		return contractTemplatesRelationship;
	}

	public void setContractTemplatesRelationship(
			Set<TbContractTemplatesRelationship> contractTemplatesRelationship) {
		this.contractTemplatesRelationship = contractTemplatesRelationship;
	}

	public Set<TbContract> getContractMembers() {
		return contractMembers;
	}

	public void setContractMembers(Set<TbContract> contractMembers) {
		this.contractMembers = contractMembers;
	}

	public Set<TbTemplateChecked> getTemplateCheckedmembers() {
		return templateCheckedmembers;
	}

	public void setTemplateCheckedmembers(
			Set<TbTemplateChecked> templateCheckedmembers) {
		this.templateCheckedmembers = templateCheckedmembers;
	}
	
}
