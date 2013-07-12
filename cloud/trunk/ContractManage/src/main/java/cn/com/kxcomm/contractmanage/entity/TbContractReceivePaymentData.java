package cn.com.kxcomm.contractmanage.entity;

import java.util.Date;

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
 * 合同收款信息
 * 
 * @author zhangjh 新增日期：2013-3-13
 * @since ContractManage
 */
@Entity
@Table(name = "tb_contract_receive_payment_data")
public class TbContractReceivePaymentData implements java.io.Serializable{
	private static final long serialVersionUID = -2991491213394444305L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 合同收款信息
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_receive_payment_id", nullable = false)
	private TbContractReceivePayment contractReceivePayment;
	
	/**
	 * 收款类型( 11：收货 12：初验 13：终验)<br/>
	 * 引用状态类<code>S_ContractReceivePayment</code>  
	 */
	@Column(name = "pay_type", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer payType;
	
	/**
	 * 收款数
	 */
	@Column(name = "money", unique = false, updatable = true, nullable = false, insertable = true)
	private Double money;
	
	/**
	 * 预计到账时间
	 */
	@Column(name = "receive_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date receiveTime;
	
	/**
	 * 接收款项时间
	 */
	@Column(name = "accpet_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date accpetTime;
	
	/**
	 * 财务人员
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financial_id", nullable = true)
	private TbUser financial;
	/**
	 * 买方帐号 1371415160****5676
	 */
	@Column(name = "buyer_account", unique = false, updatable = true, nullable = true, insertable = true)
	private String buyerAccount;
	
	/**
	 * 收款状态( 1：待收货款 2：待收初验款 3：待收终验款 0：完成收款)
	 */
	@Column(name = "status", unique = false, updatable = true, nullable = false, insertable = true)
	private Integer status;
	
	/**
	 * 备注
	 */
	@Column(name = "notes", unique = false, updatable = true, nullable = true, insertable = true,length=300)
	private String Notes;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public void setBuyerAccount(String buyerAccount) {
		this.buyerAccount = buyerAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public TbContractReceivePayment getContractReceivePayment() {
		return contractReceivePayment;
	}

	public void setContractReceivePayment(
			TbContractReceivePayment contractReceivePayment) {
		this.contractReceivePayment = contractReceivePayment;
	}

	public Integer getPayType() {
		return payType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getAccpetTime() {
		return accpetTime;
	}

	public void setAccpetTime(Date accpetTime) {
		this.accpetTime = accpetTime;
	}

	public TbUser getFinancial() {
		return financial;
	}

	public void setFinancial(TbUser financial) {
		this.financial = financial;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "TbContractReceivePaymentData [id=" + id
				+ ", contractReceivePayment=" + contractReceivePayment
				+ ", payType=" + payType + ", money=" + money
				+ ", receiveTime=" + receiveTime + ", accpetTime=" + accpetTime
				+ ", financial=" + financial + ", buyerAccount=" + buyerAccount
				+ ", status=" + status + ", Notes=" + Notes + "]";
	}
}
