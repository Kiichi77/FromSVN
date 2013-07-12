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
 * 临时合同转正式合同所选择的机型配置关系
 * 
 * @author zhangjh 新增日期：2013-4-18
 * @since ContractManage
 */
@Entity
@Table(name = "tb_temporarydata_to_formal")
public class TbTemporaryDataToFormal  implements java.io.Serializable{
	private static final long serialVersionUID = -8823012080688705340L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 临时合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContractTemporary contractTemporary; 
	
	/**
	 * 临时合同的报价表的机型配置
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quotations_data_id", nullable = false)
	private TbQuotationsData quotationsData;
	
	/**
	 * 使用数量
	 */
	@Column(name = "used_num", unique = false, updatable = true, nullable = true, insertable = true)
	private Integer usedNum;
	
	/**
	 * 正式合同的报价表
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

	public TbQuotationsData getQuotationsData() {
		return quotationsData;
	}

	public void setQuotationsData(TbQuotationsData quotationsData) {
		this.quotationsData = quotationsData;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public TbQuotations getQuotations() {
		return quotations;
	}

	public void setQuotations(TbQuotations quotations) {
		this.quotations = quotations;
	}
}
