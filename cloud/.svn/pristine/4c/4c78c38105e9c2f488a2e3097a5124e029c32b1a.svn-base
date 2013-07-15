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
 * 采购公司
 * 
 * @author zhangjh 新增日期：2013-3-7
 * @since ContractManage
 */
@Entity
@Table(name = "tb_purchase_company")
public class TbPurchaseCompany implements java.io.Serializable{
	private static final long serialVersionUID = 4131972335874452431L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 公司编号
	 */
	@Column(name = "company_no", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String companyNo;
	
	/**
	 * 公司名称
	 */
	@Column(name = "company_name", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String companyName;
	
	/**
	 * 银行信息-开户银行：广发银行北京分行方庄支行
	 */
	@Column(name = "account_opening_bank", unique = false, updatable = true, nullable = true, insertable = true, length = 200)
	private String accountOpeningBank;
	/**
	 * 银行信息-帐户名称: 北京市康讯通讯设备有限公司
	 */
	@Column(name = "account_name", unique = false, updatable = true, nullable = true, insertable = true, length = 200)
	private String accountName;
	/**
	 *  银行信息-帐户号码: 137141516010015676
	 */
	@Column(name = "account_number", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String accountNumber;
	/**
	 *  银行信息-银行地址：北京方庄
	 */
	@Column(name = "bank_address", unique = false, updatable = true, nullable = true, insertable = true, length = 100)
	private String bankAddress;
	/**
	 * 银行信息-邮编：100078
	 */
	@Column(name = "zip_code", unique = false, updatable = true, nullable = true, insertable = true, length = 20)
	private String zipCode ;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccountOpeningBank() {
		return accountOpeningBank;
	}

	public void setAccountOpeningBank(String accountOpeningBank) {
		this.accountOpeningBank = accountOpeningBank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
