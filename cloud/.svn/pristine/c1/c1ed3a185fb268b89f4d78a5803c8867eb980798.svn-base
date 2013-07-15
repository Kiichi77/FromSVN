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
 * 合同价格总表
 * 
 * @author zhangjh 新增日期：2012-12-18
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_quotations")
public class TbContractQuotations implements java.io.Serializable{
	private static final long serialVersionUID = 1767929380773501928L;

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
	private TbContract contract;
	
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

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}

	public TbQuotations getQuotations() {
		return quotations;
	}

	public void setQuotations(TbQuotations quotations) {
		this.quotations = quotations;
	}
	
}
