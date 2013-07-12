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

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * 
 * 合同收款信息
 * 
 * @author zhangjh 新增日期：2013-3-13
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_receive_payment")
public class TbContractReceivePayment  implements java.io.Serializable{
	@Override
	public String toString() {
		return "TbContractReceivePayment [id=" + id + ", contractTotalPrice="
				+ contractTotalPrice + ", contractTotalPriceText="
				+ contractTotalPriceText + ", contractGoodsPercent="
				+ contractGoodsPercent + ", contractGoodsPrice="
				+ contractGoodsPrice + ", contractGoodsPriceText="
				+ contractGoodsPriceText + ", contractBeginPercent="
				+ contractBeginPercent + ", contractBeginPrice="
				+ contractBeginPrice + ", contractBeginPriceText="
				+ contractBeginPriceText + ", contractFinalPercent="
				+ contractFinalPercent + ", contractFinalPrice="
				+ contractFinalPrice + ", contractFinalPriceText="
				+ contractFinalPriceText + ", status=" + status + ", contract="
				+ contract + "]";
	}

	private static final long serialVersionUID = 9017403347054647682L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	/**
	 * 合同总价
	 */
	@Column(name = "contract_total_price", unique = false, updatable = true, nullable = false, insertable = true)
	private Double contractTotalPrice;
	/**
	 * 合同总价大写
	 */
	@Column(name = "contract_total_price_text", unique = false, updatable = true, nullable = false, insertable = true,length=200)
	private String contractTotalPriceText;
	/**
	 * 货款的百分比
	 */
	@Column(name = "contract_goods_percent", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer contractGoodsPercent;
	/**
	 * 货款
	 */
	@Column(name = "contract_goods_price", unique = false, updatable = true, nullable = false, insertable = true)
	private Double contractGoodsPrice;
	/**
	 * 货款大写
	 */
	@Column(name = "contract_goods_price_text", unique = false, updatable = true, nullable = false, insertable = true,length=200)
	private String contractGoodsPriceText;
	
	/**
	 * 初验款项的百分比
	 */
	@Column(name = "contract_begin_percent", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer contractBeginPercent;
	/**
	 * 初验款项
	 */
	@Column(name = "contract_begin_price", unique = false, updatable = true, nullable = false, insertable = true)
	private Double contractBeginPrice;
	/**
	 * 初验款项大写
	 */
	@Column(name = "contract_begin_price_text", unique = false, updatable = true, nullable = false, insertable = true,length=200)
	private String contractBeginPriceText;
	/**
	 * 终验款项的百分比
	 */
	@Column(name = "contract_final_percent", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer contractFinalPercent;
	/**
	 * 终验款项
	 */
	@Column(name = "contract_final_price", unique = false, updatable = true, nullable = false, insertable = true)
	private Double contractFinalPrice;
	/**
	 * 终验款项大写
	 */
	@Column(name = "contract_final_price_text", unique = false, updatable = true, nullable = false, insertable = true,length=200)
	private String contractFinalPriceText;
	
	/**
	 * 收款状态( 1：待收货款 2：待收初验款 3：待收终验款 0：完成收款)<br/>
	 * 引用状态类<code>S_ContractReceivePayment</code>  
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 合同
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id", nullable = false)
	private TbContract contract;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getContractTotalPrice() {
		return contractTotalPrice;
	}

	public void setContractTotalPrice(Double contractTotalPrice) {
		this.contractTotalPrice = contractTotalPrice;
	}

	public String getContractTotalPriceText() {
		return contractTotalPriceText;
	}

	public void setContractTotalPriceText(String contractTotalPriceText) {
		this.contractTotalPriceText = contractTotalPriceText;
	}

	public Integer getContractGoodsPercent() {
		return contractGoodsPercent;
	}

	public void setContractGoodsPercent(Integer contractGoodsPercent) {
		this.contractGoodsPercent = contractGoodsPercent;
	}

	public Double getContractGoodsPrice() {
		return contractGoodsPrice;
	}

	public void setContractGoodsPrice(Double contractGoodsPrice) {
		this.contractGoodsPrice = contractGoodsPrice;
	}

	public String getContractGoodsPriceText() {
		return contractGoodsPriceText;
	}

	public void setContractGoodsPriceText(String contractGoodsPriceText) {
		this.contractGoodsPriceText = contractGoodsPriceText;
	}

	public Integer getContractBeginPercent() {
		return contractBeginPercent;
	}

	public void setContractBeginPercent(Integer contractBeginPercent) {
		this.contractBeginPercent = contractBeginPercent;
	}

	public Double getContractBeginPrice() {
		return contractBeginPrice;
	}

	public void setContractBeginPrice(Double contractBeginPrice) {
		this.contractBeginPrice = contractBeginPrice;
	}

	public String getContractBeginPriceText() {
		return contractBeginPriceText;
	}

	public void setContractBeginPriceText(String contractBeginPriceText) {
		this.contractBeginPriceText = contractBeginPriceText;
	}

	public Integer getContractFinalPercent() {
		return contractFinalPercent;
	}

	public void setContractFinalPercent(Integer contractFinalPercent) {
		this.contractFinalPercent = contractFinalPercent;
	}

	public Double getContractFinalPrice() {
		return contractFinalPrice;
	}

	public void setContractFinalPrice(Double contractFinalPrice) {
		this.contractFinalPrice = contractFinalPrice;
	}

	public String getContractFinalPriceText() {
		return contractFinalPriceText;
	}

	public void setContractFinalPriceText(String contractFinalPriceText) {
		this.contractFinalPriceText = contractFinalPriceText;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TbContract getContract() {
		return contract;
	}

	public void setContract(TbContract contract) {
		this.contract = contract;
	}
}
