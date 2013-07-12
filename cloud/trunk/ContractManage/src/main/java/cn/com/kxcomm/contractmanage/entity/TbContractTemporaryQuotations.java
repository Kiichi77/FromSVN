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
 * 临时合同下的报价表
 * 
 * @author zhangjh 新增日期：2013-4-15
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_temporary_quotations")
public class TbContractTemporaryQuotations  implements java.io.Serializable{
	private static final long serialVersionUID = -7790916332410342466L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContractTemporary contractTemporary;
	
	/**
	 * 报价表
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quotations_id", nullable = false)
	private TbQuotations quotations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbContractTemporary getContractTemporary() {
		return contractTemporary;
	}

	public void setContractTemporary(TbContractTemporary contractTemporary) {
		this.contractTemporary = contractTemporary;
	}

	public TbQuotations getQuotations() {
		return quotations;
	}

	public void setQuotations(TbQuotations quotations) {
		this.quotations = quotations;
	}
	
	
}
