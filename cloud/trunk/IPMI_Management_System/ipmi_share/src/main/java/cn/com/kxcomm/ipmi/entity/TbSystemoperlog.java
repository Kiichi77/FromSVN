package cn.com.kxcomm.ipmi.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbSystemoperlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_systemoperlog")
public class TbSystemoperlog implements java.io.Serializable {

	// Fields

	private Long id;
	private TbUser tbUser;
	private Short operationResult;  //0成功，1失败
	private Timestamp dateTime;
	private String operationType;  //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
	private String note;
	private String param;
	private String ip;
	
	// Constructors
	@Column(name = "ip", nullable = true, length = 20)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "param", nullable = true)
	public String getParam() {
		return param;
	}
	@Column(name = "note", nullable = true)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/** default constructor */
	public TbSystemoperlog() {
	}

	/** minimal constructor */
	public TbSystemoperlog(Short operationResult, String operationType) {
		this.operationResult = operationResult;
		this.operationType = operationType;
	}

	/** full constructor */
	public TbSystemoperlog(TbUser tbUser, Short operationResult,
			Timestamp dateTime, String operationType) {
		this.tbUser = tbUser;
		this.operationResult = operationResult;
		this.dateTime = dateTime;
		this.operationType = operationType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	@Column(name = "operation_result", nullable = false)
	public Short getOperationResult() {
		return this.operationResult;
	}

	public void setOperationResult(Short operationResult) {
		this.operationResult = operationResult;
	}

	@Column(name = "date_time", length = 19)
	public Timestamp getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "operation_type", nullable = false, length = 20)
	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

}