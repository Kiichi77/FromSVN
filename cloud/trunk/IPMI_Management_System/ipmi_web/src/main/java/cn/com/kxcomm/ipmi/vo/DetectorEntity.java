package cn.com.kxcomm.ipmi.vo;

/**
 * 
 * 功能描述:探测器实体
 * 
 * @author chenl 新增日期：2012-8-17
 * @since ipmi_web
 */
public class DetectorEntity implements java.io.Serializable {

	public String deviceId;  //cpu专有
	public String deviceAccessAddress; //cpu专有
	public String slaveAddress;//cpu专有
	public String channelNumber; //cpu专有
	public String lunbus;	//cpu专有
	public String deviceTypeModifier; //cpu专有
	public String oem;     //cpu专有
	public String sensorId;
	public String entityId;
	public String sensorType;
	public String sensorReading;
	public String stat;
	public String lowerNonRecoverable;
	public String lowerCritical;
	public String lowerNonCritical;
	public String upperNonCritical;
	public String upperCritical;
	public String upperNonRecoverable;
	public String assertionEvents;
	public String assertionsEnabled;
	public String deassertionsEnabled;
	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceAccessAddress() {
		return deviceAccessAddress;
	}

	public void setDeviceAccessAddress(String deviceAccessAddress) {
		this.deviceAccessAddress = deviceAccessAddress;
	}

	public String getSlaveAddress() {
		return slaveAddress;
	}

	public void setSlaveAddress(String slaveAddress) {
		this.slaveAddress = slaveAddress;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getLunbus() {
		return lunbus;
	}

	public void setLunbus(String lunbus) {
		this.lunbus = lunbus;
	}

	public String getDeviceTypeModifier() {
		return deviceTypeModifier;
	}

	public void setDeviceTypeModifier(String deviceTypeModifier) {
		this.deviceTypeModifier = deviceTypeModifier;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getAssertionEvents() {
		return assertionEvents;
	}

	public void setAssertionEvents(String assertionEvents) {
		this.assertionEvents = assertionEvents;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public String getSensorReading() {
		return sensorReading;
	}

	public void setSensorReading(String sensorReading) {
		this.sensorReading = sensorReading;
	}

	public String getLowerNonRecoverable() {
		return lowerNonRecoverable;
	}

	public void setLowerNonRecoverable(String lowerNonRecoverable) {
		this.lowerNonRecoverable = lowerNonRecoverable;
	}

	public String getLowerCritical() {
		return lowerCritical;
	}

	public void setLowerCritical(String lowerCritical) {
		this.lowerCritical = lowerCritical;
	}

	public String getLowerNonCritical() {
		return lowerNonCritical;
	}

	public void setLowerNonCritical(String lowerNonCritical) {
		this.lowerNonCritical = lowerNonCritical;
	}

	public String getUpperNonCritical() {
		return upperNonCritical;
	}

	public void setUpperNonCritical(String upperNonCritical) {
		this.upperNonCritical = upperNonCritical;
	}

	public String getAssertionsEnabled() {
		return assertionsEnabled;
	}

	public void setAssertionsEnabled(String assertionsEnabled) {
		this.assertionsEnabled = assertionsEnabled;
	}

	public String getDeassertionsEnabled() {
		return deassertionsEnabled;
	}

	public void setDeassertionsEnabled(String deassertionsEnabled) {
		this.deassertionsEnabled = deassertionsEnabled;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getUpperCritical() {
		return upperCritical;
	}

	public void setUpperCritical(String upperCritical) {
		this.upperCritical = upperCritical;
	}

	public String getUpperNonRecoverable() {
		return upperNonRecoverable;
	}

	public void setUpperNonRecoverable(String upperNonRecoverable) {
		this.upperNonRecoverable = upperNonRecoverable;
	}

	@Override
	public String toString() {
		return "DetectorEntity [sensorId=" + sensorId + ", entityId="
				+ entityId + ", sensorType=" + sensorType + ", sensorReading="
				+ sensorReading + ", stat=" + stat + ", lowerNonRecoverable="
				+ lowerNonRecoverable + ", lowerCritical=" + lowerCritical
				+ ", lowerNonCritical=" + lowerNonCritical
				+ ", upperNonCritical=" + upperNonCritical + ", upperCritical="
				+ upperCritical + ", upperNonRecoverable="
				+ upperNonRecoverable + ", assertionEvents=" + assertionEvents
				+ ", assertionsEnabled=" + assertionsEnabled
				+ ", deassertionsEnabled=" + deassertionsEnabled + "]";
	}

}