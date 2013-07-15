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
 * 系统日志记录
 * 
 * @author zhangjh 新增日期：2013-4-1
 * @since ContractManage
 */
@Entity
@Table(name = "tb_system_log")
public class TbSystemLog  implements java.io.Serializable{
	private static final long serialVersionUID = 1048572139734436635L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Integer id;
	
	/**
	 * 日志类型
	 */
	@Column(name = "log_type", unique = false, updatable = true, nullable = true, insertable = true,length=300)
	private String logType;
	
	/**
	 * 操作人
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "operater_id", nullable = false)
	private TbUser operater;
	
	/**
	 * 操作结果
	 */
	@Column(name = "result", unique = false, updatable = true, nullable = true, insertable = true,length=100)
	private String result;
	
	/**
	 * 操作结果
	 */
	@Column(name = "oper_time", unique = false, updatable = true, nullable = true, insertable = true)
	private Date operTime;
	
	/**
	 * 信息，操作失败的错误信息
	 */
	@Column(name = "message", unique = false, updatable = true, nullable = true, insertable = true,length=300)
	private String message;
	
	/**
	 * 操作内容
	 */
	@Column(name = "operat_content", unique = false, updatable = true, nullable = true, insertable = true,length=300)
	private String operatContent;

	public Integer getId() {
		return id;
	}

	public String getOperatContent() {
		return operatContent;
	}

	public void setOperatContent(String operatContent) {
		this.operatContent = operatContent;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public TbUser getOperater() {
		return operater;
	}

	public void setOperater(TbUser operater) {
		this.operater = operater;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
