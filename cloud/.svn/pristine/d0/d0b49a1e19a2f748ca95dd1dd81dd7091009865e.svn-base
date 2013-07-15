package cn.com.kxcomm.ipmi.vo;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbUser;

/**
 * 
 * 警告类VO
 * 
 * @author zhangjh 新增日期：2012-8-29
 * @since ipmi_share
 */
public class AlertVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -3153852803950805002L;
	
	private int alertId;

	/**
	 * 筛选器类型(1:功耗，2:温度)
	 */
	private int filterType;

	/**
	 * 警告的开关(0:关闭，1：开启)
	 */
	private short warningEnable;
	
	/**
	 * 严重的开关(0:关闭，1：开启)
	 */
	private short seriousEnable;
	/**
	 * 严重的阀值
	 */
	private int seriousNum;
	/**
	 * 警告的阀值
	 */
	private int warningNum;
	
	/**
	 * 查询条件启用(0:关闭，1：开启)
	 */
	private int enable;

	/**
	 * 服务器
	 */
	private TbServer tbServer;

	/**
	 * 用户
	 */
	private TbUser tbUsers;
	/**
	 * 触发告警阀值
	 */
	private Integer alertTriggerLimit;
	
	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Integer getAlertTriggerLimit() {
		return alertTriggerLimit;
	}

	public void setAlertTriggerLimit(Integer alertTriggerLimit) {
		this.alertTriggerLimit = alertTriggerLimit;
	}

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

	public short getWarningEnable() {
		return warningEnable;
	}

	public void setWarningEnable(short warningEnable) {
		this.warningEnable = warningEnable;
	}

	public short getSeriousEnable() {
		return seriousEnable;
	}

	public void setSeriousEnable(short seriousEnable) {
		this.seriousEnable = seriousEnable;
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

	public TbServer getTbServer() {
		return tbServer;
	}

	public void setTbServer(TbServer tbServer) {
		this.tbServer = tbServer;
	}

	public TbUser getTbUsers() {
		return tbUsers;
	}

	public void setTbUsers(TbUser tbUsers) {
		this.tbUsers = tbUsers;
	}


}
