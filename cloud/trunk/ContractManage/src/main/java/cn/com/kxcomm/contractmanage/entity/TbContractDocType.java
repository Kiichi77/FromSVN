package cn.com.kxcomm.contractmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 合同文挡类型
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_doc_type")
public class TbContractDocType implements java.io.Serializable{
	private static final long serialVersionUID = 1158557932755715105L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同文档类型名称
	 */
	@Column(name = "name", unique = false, updatable = true, nullable = false, insertable = true, length = 50)
	private String name;

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
	
}
