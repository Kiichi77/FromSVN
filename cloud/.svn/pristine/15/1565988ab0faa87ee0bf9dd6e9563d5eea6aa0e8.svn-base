package cn.com.kxcomm.ipmi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 警告类
 * 
 * @author zhangjh 新增日期：2012-8-29
 * @since ipmi_share
 */
@Entity
@Table(name = "tb_alert")
public class AlertEntity implements java.io.Serializable{
	private static final long serialVersionUID = -3153852803950805002L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name="alert_id",updatable = false, nullable = false,insertable = false, length = 32)
	private int alertId;
	
	/**
	 * 筛选器类型(1:系统功耗,2:出风口温度,3.入风口温度,4.CPU功耗,5.内存功耗)
	 */
	@Column(name = "filter_type", unique = false, updatable = true, nullable = false, insertable = true, length = 2)
	private int filterType;
	
	/**
	 * 严重的阀值
	 */
	@Column(name = "serious_num", unique = false, updatable = true, nullable = false, insertable = true, length = 2)
	private int seriousNum;
	
	/**
	 * 警告的阀值
	 */
	@Column(name = "warning_num", unique = false, updatable = true, nullable = false, insertable = true, length = 2)
	private int warningNum;
	
	/**
	 * 警告的开关(0:关闭，1：开启)
	 */
	@Column(name = "warning_enable", unique = false, updatable = true, nullable = false, insertable = true, length = 2)
	private Short warningEnable;
	
	/**
	 * 严重的开关(0:关闭，1：开启)
	 */
	@Column(name = "serious_enable", unique = false, updatable = true, nullable = false, insertable = true, length = 2)
	private Short seriousEnable;
	
	/**
	 * 用户ID
	 */
	@Column(name = "userid", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Long userId;



	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	public int getSeriousNum() {
		return seriousNum;
	}

	public void setSeriousNum(int seriousNum) {
		this.seriousNum = seriousNum;
	}

	public int getWarningNum() {
		return warningNum;
	}

	public void setWarningNum(int warningNum) {
		this.warningNum = warningNum;
	}

	public int getWarningEnable() {
		return warningEnable;
	}

	public int getSeriousEnable() {
		return seriousEnable;
	}

	
	public void setWarningEnable(Short warningEnable) {
		this.warningEnable = warningEnable;
	}

	public void setSeriousEnable(Short seriousEnable) {
		this.seriousEnable = seriousEnable;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "AlertEntity [alertId=" + alertId + ", filterType=" + filterType
				+ ", seriousNum=" + seriousNum + ", warningNum=" + warningNum
				+ ", warningEnable=" + warningEnable + ", seriousEnable="
				+ seriousEnable +  ", userId=" + userId +  "]";
	}
}
