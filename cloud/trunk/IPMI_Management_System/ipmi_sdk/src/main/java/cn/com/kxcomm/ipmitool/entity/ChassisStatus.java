package cn.com.kxcomm.ipmitool.entity;

/**
* 
* 底盘状态
* @author zhangjh 新增日期：2012-8-22
* @since ipmi_sdk
 */
public class ChassisStatus {
	/**
	 * 系统电源
	 */
	private boolean systemPower;
	/**
	 * 电源过载
	 */
	private boolean powerOverload;
	/**
	 * 电源联锁
	 */
	private String powerInterlock;
	/**
	 * 主电源故障
	 */
	private boolean mainPowerFault;
	/**
	 * 电源控制故障
	 */
	private boolean powerControlFault;
	/**
	 * 电源恢复策略
	 */
	private String powerRestorePolicy;
	/**
	 * 最后电源事件
	 */
	private String lastPowerEvent;
	/**
	 * 机箱入侵
	 */
	private String chassisIntrusion;
	/**
	 * 前面板锁定
	 */
	private String frontPanelLockout;
	/**
	 * 驱动器故障
	 */
	private boolean driveFault;
	/**
	 * 冷却/风扇故障
	 */
	private boolean coolingFanFault;
	/**
	 * 睡眠按钮禁用
	 */
	private String sleepButtonDisable;
	/**
	 * DIAG键禁用
	 */
	private String diagButtonDisable;
	/**
	 * 复位按钮禁用
	 */
	private String resetButtonDisable;
	/**
	 * 电源按钮关闭
	 */
	private String powerButtonDisable;
	/**
	 * 睡眠按钮无效
	 */
	private boolean sleepButtonDisabled;
	/**
	 * DIAG按钮无效
	 */
	private boolean diagButtonDisabled;
	/**
	 * 复位按钮无效
	 */
	private boolean resetButtonDisabled;
	/**
	 * 电源按钮无效
	 */
	private boolean powerButtonDisabled;
	public boolean isSystemPower() {
		return systemPower;
	}
	public void setSystemPower(boolean systemPower) {
		this.systemPower = systemPower;
	}
	public boolean isPowerOverload() {
		return powerOverload;
	}
	public void setPowerOverload(boolean powerOverload) {
		this.powerOverload = powerOverload;
	}
	public String getPowerInterlock() {
		return powerInterlock;
	}
	public void setPowerInterlock(String powerInterlock) {
		this.powerInterlock = powerInterlock;
	}
	public boolean isMainPowerFault() {
		return mainPowerFault;
	}
	public void setMainPowerFault(boolean mainPowerFault) {
		this.mainPowerFault = mainPowerFault;
	}
	public boolean isPowerControlFault() {
		return powerControlFault;
	}
	public void setPowerControlFault(boolean powerControlFault) {
		this.powerControlFault = powerControlFault;
	}
	public String getPowerRestorePolicy() {
		return powerRestorePolicy;
	}
	public void setPowerRestorePolicy(String powerRestorePolicy) {
		this.powerRestorePolicy = powerRestorePolicy;
	}
	public String getLastPowerEvent() {
		return lastPowerEvent;
	}
	public void setLastPowerEvent(String lastPowerEvent) {
		this.lastPowerEvent = lastPowerEvent;
	}
	public String getChassisIntrusion() {
		return chassisIntrusion;
	}
	public void setChassisIntrusion(String chassisIntrusion) {
		this.chassisIntrusion = chassisIntrusion;
	}
	public String getFrontPanelLockout() {
		return frontPanelLockout;
	}
	public void setFrontPanelLockout(String frontPanelLockout) {
		this.frontPanelLockout = frontPanelLockout;
	}
	public boolean isDriveFault() {
		return driveFault;
	}
	public void setDriveFault(boolean driveFault) {
		this.driveFault = driveFault;
	}
	public boolean isCoolingFanFault() {
		return coolingFanFault;
	}
	public void setCoolingFanFault(boolean coolingFanFault) {
		this.coolingFanFault = coolingFanFault;
	}
	public String getSleepButtonDisable() {
		return sleepButtonDisable;
	}
	public void setSleepButtonDisable(String sleepButtonDisable) {
		this.sleepButtonDisable = sleepButtonDisable;
	}
	public String getDiagButtonDisable() {
		return diagButtonDisable;
	}
	public void setDiagButtonDisable(String diagButtonDisable) {
		this.diagButtonDisable = diagButtonDisable;
	}
	public String getResetButtonDisable() {
		return resetButtonDisable;
	}
	public void setResetButtonDisable(String resetButtonDisable) {
		this.resetButtonDisable = resetButtonDisable;
	}
	public String getPowerButtonDisable() {
		return powerButtonDisable;
	}
	public void setPowerButtonDisable(String powerButtonDisable) {
		this.powerButtonDisable = powerButtonDisable;
	}
	public boolean isSleepButtonDisabled() {
		return sleepButtonDisabled;
	}
	public void setSleepButtonDisabled(boolean sleepButtonDisabled) {
		this.sleepButtonDisabled = sleepButtonDisabled;
	}
	public boolean isDiagButtonDisabled() {
		return diagButtonDisabled;
	}
	public void setDiagButtonDisabled(boolean diagButtonDisabled) {
		this.diagButtonDisabled = diagButtonDisabled;
	}
	public boolean isResetButtonDisabled() {
		return resetButtonDisabled;
	}
	public void setResetButtonDisabled(boolean resetButtonDisabled) {
		this.resetButtonDisabled = resetButtonDisabled;
	}
	public boolean isPowerButtonDisabled() {
		return powerButtonDisabled;
	}
	public void setPowerButtonDisabled(boolean powerButtonDisabled) {
		this.powerButtonDisabled = powerButtonDisabled;
	}
	@Override
	public String toString() {
		return "ChassisStatus [systemPower=" + systemPower + ", powerOverload="
				+ powerOverload + ", powerInterlock=" + powerInterlock
				+ ", mainPowerFault=" + mainPowerFault + ", powerControlFault="
				+ powerControlFault + ", powerRestorePolicy="
				+ powerRestorePolicy + ", lastPowerEvent=" + lastPowerEvent
				+ ", chassisIntrusion=" + chassisIntrusion
				+ ", frontPanelLockout=" + frontPanelLockout + ", driveFault="
				+ driveFault + ", coolingFanFault=" + coolingFanFault
				+ ", sleepButtonDisable=" + sleepButtonDisable
				+ ", diagButtonDisable=" + diagButtonDisable
				+ ", resetButtonDisable=" + resetButtonDisable
				+ ", powerButtonDisable=" + powerButtonDisable
				+ ", sleepButtonDisabled=" + sleepButtonDisabled
				+ ", diagButtonDisabled=" + diagButtonDisabled
				+ ", resetButtonDisabled=" + resetButtonDisabled
				+ ", powerButtonDisabled=" + powerButtonDisabled + "]";
	}
}
