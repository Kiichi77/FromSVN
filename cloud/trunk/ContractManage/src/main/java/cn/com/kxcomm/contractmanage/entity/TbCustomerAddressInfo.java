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
 * 客户收货信息
 * 
 * @author zhangjh 新增日期：2013-4-8
 * @since ContractManage
 */
@Entity
@Table(name = "tb_customer_address_info")
public class TbCustomerAddressInfo implements java.io.Serializable{
	private static final long serialVersionUID = -5342552300817063060L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false)
	private Integer id;
	/**
	 * 收货信息<br/>
	 * 格式：广东省广州市天河区云景机房（张建华收）15817130480
	 */
	@Column(name = "receive_info", unique = false, updatable = true, nullable = true, insertable = true, length = 500)
	private String receiveInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReceiveInfo() {
		return receiveInfo;
	}

	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}
	
}
