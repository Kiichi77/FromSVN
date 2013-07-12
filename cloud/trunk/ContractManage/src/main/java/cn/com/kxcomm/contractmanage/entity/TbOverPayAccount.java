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
 * 对冲总账
 * 
 * @author zhangjh 新增日期：2013-3-11
 * @since ContractManage
 */
@Entity
@Table(name = "tb_overpay_account")
public class TbOverPayAccount implements java.io.Serializable{
	private static final long serialVersionUID = -3812302120274018378L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 供应商
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suppliers_id", nullable = false)
	private TbSuppliers suppliers;
	
	/**
	 * 采购公司
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_company_id", nullable = false)
	private TbPurchaseCompany purchaseCompany;
	
	/**
	 * 多付款的总金额（若对冲使用，则减去对冲的金额）
	 */
	@Column(name = "total_overpay_money", unique = false, updatable = true, nullable = false, insertable = true)
	private Double totalOverPayMoney;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public TbPurchaseCompany getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(TbPurchaseCompany purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public Double getTotalOverPayMoney() {
		return totalOverPayMoney;
	}

	public void setTotalOverPayMoney(Double totalOverPayMoney) {
		this.totalOverPayMoney = totalOverPayMoney;
	}
}
