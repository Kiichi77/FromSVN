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
 * 客户表
 * @author YuKy1327
 *
 */
@Entity
@Table(name = "tb_customer")
public class TbCustomer implements java.io.Serializable{
	private static final long serialVersionUID = -8924825435516878844L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer customerId;
	
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
	 * 公司名称
	 */
	@Column(name = "company_name", unique = false, updatable = true, nullable = false, insertable = true, length = 30)
	private String companyName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_place_id", nullable = true)
	private TbJobPlace jobPlace;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}


	public TbJobPlace getJobPlace() {
		return jobPlace;
	}

	public void setJobPlace(TbJobPlace jobPlace) {
		this.jobPlace = jobPlace;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
