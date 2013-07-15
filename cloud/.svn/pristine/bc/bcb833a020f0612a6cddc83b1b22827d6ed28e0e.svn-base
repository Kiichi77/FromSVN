package cn.com.kxcomm.ipmitool.entity;

/**
 * 
* 策略对象
* @author zhangjh 新增日期：2012-8-22
* @since ipmi_sdk
 */
public class PolicyEntity {
	/**
	 * 域(0:global 1:per cpu domain 2:per mem domain)
	 */
	private Integer component;
	/**
	 * 策略ID 可用策略ID(6-256),系统默认0-5(不可删除),本系统设置最大策略ID为30
	 */
	private Integer policyId;
	/**
	 * 策略类型:
	 *  0(0x10):power
		1(0x11):Thermal
		2(0x50):默认:系统策略(1,5)保留既用于Inlet Temperature监测，又用于基于进风口温度的功耗控制 (前提：该服务器支持基于进风口温度的Node Manager功耗监测和控制）
		3(0x14):默认:系统策略(3)保留用于Node Manager 2.0监测内存功耗
		4(0x12):默认:系统策略(4)保留用于Node Manager 最小功耗控制
	 */
	private Integer policyType;
	/**
	 * 策略限制
	 */
	private Integer policyLimit;
	/**
	 * 发送警告 0x01,0x03时为true
	 */
	private boolean sendAlert = false;
	/**
	 * 关机 0x03时为true
	 */
	private boolean shutdown  = false;
	/**
	 * 校正时间
	 */
	private Long correctionTime;
	/**
	 * 日志时间
	 */
	private Integer statReportingPeriod;
	/**
	 * 策略开关
	 */
	private boolean policyEnabled  = false;
	/**
	 * 触发器限制,一般与policyLimit一致
	 */
	private Integer policyTriggerLimit;
	
	/**
	 * @return the component
	 */
	public Integer getComponent() {
		return component;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(Integer component) {
		this.component = component;
	}

	/**
	 * @return the policyId
	 */
	public Integer getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId the policyId to set
	 */
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	/**
	 * @return the policyType
	 */
	public Integer getPolicyType() {
		return policyType;
	}

	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}

	/**
	 * @return the policyLimit
	 */
	public Integer getPolicyLimit() {
		return policyLimit;
	}

	/**
	 * @param policyLimit the policyLimit to set
	 */
	public void setPolicyLimit(Integer policyLimit) {
		this.policyLimit = policyLimit;
	}

	/**
	 * @return the sendAlert
	 */
	public boolean isSendAlert() {
		return sendAlert;
	}

	/**
	 * @param sendAlert the sendAlert to set
	 */
	public void setSendAlert(boolean sendAlert) {
		this.sendAlert = sendAlert;
	}

	/**
	 * @return the shutdown
	 */
	public boolean isShutdown() {
		return shutdown;
	}

	/**
	 * @param shutdown the shutdown to set
	 */
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	/**
	 * @return the correctionTime
	 */
	public Long getCorrectionTime() {
		return correctionTime;
	}

	/**
	 * @param correctionTime the correctionTime to set
	 */
	public void setCorrectionTime(Long correctionTime) {
		this.correctionTime = correctionTime;
	}

	/**
	 * @return the statReportingPeriod
	 */
	public Integer getStatReportingPeriod() {
		return statReportingPeriod;
	}

	/**
	 * @param statReportingPeriod the statReportingPeriod to set
	 */
	public void setStatReportingPeriod(Integer statReportingPeriod) {
		this.statReportingPeriod = statReportingPeriod;
	}

	/**
	 * @return the policyEnabled
	 */
	public boolean isPolicyEnabled() {
		return policyEnabled;
	}

	/**
	 * @param policyEnabled the policyEnabled to set
	 */
	public void setPolicyEnabled(boolean policyEnabled) {
		this.policyEnabled = policyEnabled;
	}

	/**
	 * @return the policyTriggerLimit
	 */
	public Integer getPolicyTriggerLimit() {
		return policyTriggerLimit;
	}

	/**
	 * @param policyTriggerLimit the policyTriggerLimit to set
	 */
	public void setPolicyTriggerLimit(Integer policyTriggerLimit) {
		this.policyTriggerLimit = policyTriggerLimit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PolicyEntity [component=" + component + ", policyId="
				+ policyId + ", policyType=" + policyType + ", policyLimit="
				+ policyLimit + ", sendAlert=" + sendAlert + ", shutdown="
				+ shutdown + ", correctionTime=" + correctionTime
				+ ", statReportingPeriod=" + statReportingPeriod
				+ ", policyEnabled=" + policyEnabled + ", policyTriggerLimit="
				+ policyTriggerLimit + "]";
	}

	

	
}
