package cn.com.kxcomm.ipmi.vo;

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

import cn.com.kxcomm.ipmi.entity.TbServer;

public class HistoryServerInfoEntity implements java.io.Serializable {

	// Fields

	private Long id;  //id
	private String dateTime;
	private Integer collectType;  //数据采集类型
	private String collectString;  //采集字符串
	private Float collectAvg;    //平均值
	private int collectMax;    //最大值
	private int collectMin;     //最小值
	
	private String serverName;     //服务器名称
	private String serverIpIn;     //服务器IP
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIpIn() {
		return serverIpIn;
	}

	public void setServerIpIn(String serverIpIn) {
		this.serverIpIn = serverIpIn;
	}

	public Integer getCollectType() {
		return collectType;
	}

	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}

	public String getCollectString() {
		return collectString;
	}

	public void setCollectString(String collectString) {
		this.collectString = collectString;
	}

	public Float getCollectAvg() {
		return collectAvg;
	}

	public void setCollectAvg(Float collectAvg) {
		this.collectAvg = collectAvg;
	}

	public int getCollectMax() {
		return collectMax;
	}

	public void setCollectMax(int collectMax) {
		this.collectMax = collectMax;
	}

	public int getCollectMin() {
		return collectMin;
	}

	public void setCollectMin(int collectMin) {
		this.collectMin = collectMin;
	}

	@Override
	public String toString() {
		return "HistoryServerInfoEntity [id=" + id + ", dateTime=" + dateTime
				+ ", collectType=" + collectType + ", collectString="
				+ collectString + ", collectAvg=" + collectAvg
				+ ", collectMax=" + collectMax + ", collectMin=" + collectMin
				+ ", serverName=" + serverName + ", serverIpIn=" + serverIpIn
				+ "]";
	}

}