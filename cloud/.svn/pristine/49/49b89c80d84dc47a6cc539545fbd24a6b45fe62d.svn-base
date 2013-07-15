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
 * 
 * 日期纬度
 * 
 * @author zhangjh 新增日期：2012-9-18
 * @since ipmi_share
 */
@Entity
@Table(name = "tb_dim_date")
public class DimDateEntity implements java.io.Serializable {
	private static final long serialVersionUID = -7144419678210724429L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "gen", strategy = "uuid")
	@Column(name = "date_id", updatable = false, nullable = false, insertable = false, length = 32)
	private Long dateId;
	@Column(name = "full_date", unique = false, updatable = true, nullable = false, insertable = true)
	private Date fullDate;
	@Column(name = "day", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Short day;
	@Column(name = "week", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Short week;
	@Column(name = "month", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Short month;
	@Column(name = "quarter", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Short quarter;
	@Column(name = "year", unique = false, updatable = true, nullable = false, insertable = true, length = 10)
	private Short year;

	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public Date getFullDate() {
		return fullDate;
	}

	public void setFullDate(Date fullDate) {
		this.fullDate = fullDate;
	}

	public Short getDay() {
		return day;
	}

	public void setDay(Short day) {
		this.day = day;
	}

	public Short getWeek() {
		return week;
	}

	public void setWeek(Short week) {
		this.week = week;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	public Short getQuarter() {
		return quarter;
	}

	public void setQuarter(Short quarter) {
		this.quarter = quarter;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public DimDateEntity(Long dateId, Date fullDate, Short day, Short week,
			Short month, Short quarter, Short year) {
		super();
		this.dateId = dateId;
		this.fullDate = fullDate;
		this.day = day;
		this.week = week;
		this.month = month;
		this.quarter = quarter;
		this.year = year;
	}

	public DimDateEntity() {
		super();
	}

	@Override
	public String toString() {
		return "DimDateEntity [dateId=" + dateId + ", fullDate=" + fullDate
				+ ", day=" + day + ", week=" + week + ", month=" + month
				+ ", quarter=" + quarter + ", year=" + year + "]";
	}

}
