package cn.com.kxcomm.ipmi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 服务器系统日志
 */
@Entity
@Table(name = "tb_system_event_log")
public class TbSystemEventLog implements java.io.Serializable {
	private static final long serialVersionUID = -6159021602276345318L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="log_id",updatable = false, nullable = false,insertable = false, length = 32)
	private Long id;
	/**
	 * 服务器IP
	 */
	@Column(name = "server_ip", unique = false, updatable = true, nullable = false, insertable = true, length = 20)
	private String serverIp;
	
	/**
	 * SEL日志
	 */
	@Column(name = "sel_msg", unique = false, updatable = true, nullable = false, insertable = true, length = 200)
	private String selMsg;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createTime;
	
	/**
	 * 状态(Asserted,Deasserted)
	 */
	@Column(name = "state", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getSelMsg() {
		return selMsg;
	}

	public void setSelMsg(String selMsg) {
		this.selMsg = selMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TbSystemEventLog [id=" + id + ", serverIp=" + serverIp
				+ ", selMsg=" + selMsg + ", createTime=" + createTime
				+ ", state=" + state + "]";
	}

	public TbSystemEventLog(Long id, String serverIp, String selMsg,
			Date createTime, String state) {
		super();
		this.id = id;
		this.serverIp = serverIp;
		this.selMsg = selMsg;
		this.createTime = createTime;
		this.state = state;
	}

	public TbSystemEventLog() {
		super();
	}
}