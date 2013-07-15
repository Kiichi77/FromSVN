package cn.com.kxcomm.ipmi.entity;

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

@Entity
@Table(name = "tb_fact_server_info")
public class FactServerInfoEntity implements java.io.Serializable{
	private static final long serialVersionUID = 7204351457209514144L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="id",updatable = false, nullable = false,insertable = false)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "date_id", nullable = false)
	private DimDateEntity dimDate; // date_id
	
	/**
	 * 采集类型(1.系统功耗 ,2.CPU功耗,3.内存功耗,4.出风口温度)
	 */
	@Column(name = "collect_type", unique = false, updatable = true, nullable = false, insertable = true)
	private Short collectType;
	
	/**
	 * 最小功率/温度
	 */
	@Column(name = "min", unique = false, updatable = true, nullable = false, insertable = true)
	private Short min;
	/**
	 * 最大功率/温度
	 */
	@Column(name = "max", unique = false, updatable = true, nullable = false, insertable = true)
	private Short max;
	/**
	 * 平均功率/温度
	 */
	@Column(name = "average", unique = false, updatable = true, nullable = false, insertable = true)
	private Short average;
	
	/**
	 * 服务器ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "server_id", nullable = false)
	private TbServer server;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DimDateEntity getDimDate() {
		return dimDate;
	}
	public void setDimDate(DimDateEntity dimDate) {
		this.dimDate = dimDate;
	}
	public Short getCollectType() {
		return collectType;
	}
	public void setCollectType(Short collectType) {
		this.collectType = collectType;
	}
	public Short getMin() {
		return min;
	}
	public void setMin(Short min) {
		this.min = min;
	}
	public Short getMax() {
		return max;
	}
	public void setMax(Short max) {
		this.max = max;
	}
	public Short getAverage() {
		return average;
	}
	public void setAverage(Short average) {
		this.average = average;
	}
	public TbServer getServer() {
		return server;
	}
	public void setServer(TbServer server) {
		this.server = server;
	}

}
