package cn.com.kxcomm.ipmi.entity;
// default package

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
 * 采集的历史数据
 */
@Entity
@Table(name = "tb_history_server_info")
public class TbHistoryServerInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1400654722841318877L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false, length = 32)
	private Long id;
	/**
	 * 服务器
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "server_id", nullable = false)
	private TbServer tbServer;
	
	/**
	 * 系统功耗
	 */
	@Column(name = "system_power", unique = false, updatable = true, nullable = true, insertable = true)
	private Short systemPower;
	/**
	 * CPU功耗
	 */
	@Column(name = "cpu_power", unique = false, updatable = true, nullable = true, insertable = true)
	private Short cpuPower;
	/**
	 * 内存功耗
	 */
	@Column(name = "memory_power", unique = false, updatable = true, nullable = true, insertable = true)
	private Short memoryPower;
	/**
	 * 出风口温度
	 */
	@Column(name = "exhaust_temp", unique = false, updatable = true, nullable = true, insertable = true)
	private Short exhaustTemp;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", unique = false, updatable = true, nullable = false, insertable = true)
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbHistoryServerInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbServer getTbServer() {
		return tbServer;
	}

	public void setTbServer(TbServer tbServer) {
		this.tbServer = tbServer;
	}

	public Short getSystemPower() {
		return systemPower;
	}

	public void setSystemPower(Short systemPower) {
		this.systemPower = systemPower;
	}

	public Short getCpuPower() {
		return cpuPower;
	}

	public void setCpuPower(Short cpuPower) {
		this.cpuPower = cpuPower;
	}

	public Short getMemoryPower() {
		return memoryPower;
	}

	public void setMemoryPower(Short memoryPower) {
		this.memoryPower = memoryPower;
	}

	public Short getExhaustTemp() {
		return exhaustTemp;
	}

	public void setExhaustTemp(Short exhaustTemp) {
		this.exhaustTemp = exhaustTemp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public TbHistoryServerInfo(Long id, TbServer tbServer, Short systemPower,
			Short cpuPower, Short memoryPower, Short exhaustTemp,
			Date createTime) {
		super();
		this.id = id;
		this.tbServer = tbServer;
		this.systemPower = systemPower;
		this.cpuPower = cpuPower;
		this.memoryPower = memoryPower;
		this.exhaustTemp = exhaustTemp;
		this.createTime = createTime;
	}
}