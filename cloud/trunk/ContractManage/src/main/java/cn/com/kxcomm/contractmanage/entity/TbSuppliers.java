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
 * 供应商
 * 
 * @author zhangjh 新增日期：2013-3-4
 * @since ContractManage
 */
@Entity
@Table(name = "tb_suppliers")
public class TbSuppliers implements java.io.Serializable{
	private static final long serialVersionUID = 3092949682364117361L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 公司名称
	 */
	@Column(name = "company_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String companyName;
	
	/**
	 * 公司地址
	 */
	@Column(name = "address", unique = false, updatable = true, nullable = true, insertable = true, length = 200)
	private String address;
	
	/**
	 * 联系人
	 */
	@Column(name = "contact", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String contact;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "phone", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String phone;
	
	/**
	 * 描述
	 */
	@Column(name = "description", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String description;
	
	/**
	 * 邮箱地址
	 */
	@Column(name = "email", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String email;

	/**
	 * 纳税人识别号
	 */
	@Column(name = "taxpayer_number", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String taxpayerNumber;
	
	/**
	 * 开户行
	 */
	@Column(name = "open_bank", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String openBank;
	
	/**
	 * 帐号名称
	 */
	@Column(name = "accounts_name", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String accountsName;
	/**
	 * 帐号
	 */
	@Column(name = "accounts", unique = false, updatable = true, nullable = true, insertable = true, length = 30)
	private String accounts;
	
	/**
	 * 状态:<code></code>
	 */
	@Column(name = "state", unique = false, nullable = false, insertable = true)
	private Integer state;
	
	/**
	 * 审核结果
	 */
	@Column(name = "check_result", unique = false, nullable = true, length = 30)
	private String checkResult;
	
	
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxpayerNumber() {
		return taxpayerNumber;
	}

	public void setTaxpayerNumber(String taxpayerNumber) {
		this.taxpayerNumber = taxpayerNumber;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getAccountsName() {
		return accountsName;
	}

	public void setAccountsName(String accountsName) {
		this.accountsName = accountsName;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	
	
}
