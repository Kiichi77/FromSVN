package cn.com.kxcomm.ipmitool.entity;

/**
 * 
* 功耗/温度对象
* @author zhangjh 新增日期：2012-8-22
* @since ipmi_sdk
 */
public class PowerReading implements java.io.Serializable{
	private static final long serialVersionUID = 2332394546095326346L;
	/**
	 * 策略ID
	 */
	private Integer policyId;
	/**
	 * 当前功率/温度
	 */
	private Integer current;
	/**
	 * 最小功率/温度
	 */
	private Integer min;
	/**
	 * 最大功率/温度
	 */
	private Integer max;
	/**
	 * 平均功率/温度
	 */
	private Integer average;
	/**
	 * 当前时间
	 * 如果返回有效地时间戳，默认为1970-01-16 21:45:26即IPMI 2.0的默认规定
	 */
	private Long currentTime;
	/**
	 * 统计日志时间
	 */
	private Long statReportingPeriod;
	/**
	 * 描述 Power:Watts/Temperature:Celsius
	 */
	private String desc;

	/**
	 * 域(0:global 1:per cpu domain 2:per mem domain)
	 */
	private Integer component;
	/**
	 * 策略开关
	 */
	private boolean policyEnabled = false;

	public Long getStatReportingPeriod() {
		return statReportingPeriod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setStatReportingPeriod(Long statReportingPeriod) {
		this.statReportingPeriod = statReportingPeriod;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getAverage() {
		return average;
	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public Integer getComponent() {
		return component;
	}

	public void setComponent(Integer component) {
		this.component = component;
	}

	public boolean isPolicyEnabled() {
		return policyEnabled;
	}

	public void setPolicyEnabled(boolean policyEnabled) {
		this.policyEnabled = policyEnabled;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "PowerReading [policyId=" + policyId + ", current=" + current
				+ ", min=" + min + ", max=" + max + ", average=" + average
				+ ", currentTime=" + currentTime + ", statReportingPeriod="
				+ statReportingPeriod + ", desc=" + desc + ", component="
				+ component + ", policyEnabled=" + policyEnabled + "]";
	}

	
}
