package cn.com.kxcomm.ipmi.vo;

/**
 * 
* 功能描述:服务器信息摘要数据详情
* @author chenl 新增日期：2012-8-17
* @since ipmi_web
 */
public class ServerInfoEntity implements java.io.Serializable {

	//温度(系统温度，cpu温度，内存温度)
	private int now_Temper; //当前温度
	private int max_Temper; //最大温度
	private int min_Temper; //最小温度
	private int avg_Temper; //平均温度
	
	//功耗(系统功耗，cpu功耗，内存功耗)
	private int now_PowerWaste; //当前功耗
	private int max_PowerWaste; //最大功耗
	private int min_PowerWaste; //最大功耗
	private int avg_PowerWaste; //最大功耗
	
	//阀值(电源当前)
	private int alarm_thresholdValue; //告警阀值
	private int fault_threshold;     //故障阀值
	//阀值(电源温度)
	private int alarm_max_thresholdValue; //告警最大阀值
	private int alarm_min_thresholdValue; //告警最小阀值
	private int fault_max_threshold;  //故障最大阀值
	private int fault_min_threshold;  //故障最小阀值
	
	//状态(电池，风扇，cpu，)
	private String state;  //状态
	private String condition; //状况
	
	//探测器(风扇，电池，cpu，电源)  进风口探测器
	private String detector; //探测器名称(探测某设备)
	
	//读数(电源温度读数，电源当前读数， 进风口)
	private String reading;  //读数
	
	//风扇冗余
	private String value; //值
	private String attribute; //属性
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getNow_Temper() {
		return now_Temper;
	}

	public void setNow_Temper(int now_Temper) {
		this.now_Temper = now_Temper;
	}

	public int getMax_Temper() {
		return max_Temper;
	}

	public void setMax_Temper(int max_Temper) {
		this.max_Temper = max_Temper;
	}

	public int getMin_Temper() {
		return min_Temper;
	}

	public void setMin_Temper(int min_Temper) {
		this.min_Temper = min_Temper;
	}

	public int getAvg_Temper() {
		return avg_Temper;
	}

	public void setAvg_Temper(int avg_Temper) {
		this.avg_Temper = avg_Temper;
	}

	public int getNow_PowerWaste() {
		return now_PowerWaste;
	}

	public void setNow_PowerWaste(int now_PowerWaste) {
		this.now_PowerWaste = now_PowerWaste;
	}

	public int getMax_PowerWaste() {
		return max_PowerWaste;
	}

	public void setMax_PowerWaste(int max_PowerWaste) {
		this.max_PowerWaste = max_PowerWaste;
	}

	public int getMin_PowerWaste() {
		return min_PowerWaste;
	}

	public void setMin_PowerWaste(int min_PowerWaste) {
		this.min_PowerWaste = min_PowerWaste;
	}

	public int getAvg_PowerWaste() {
		return avg_PowerWaste;
	}

	public void setAvg_PowerWaste(int avg_PowerWaste) {
		this.avg_PowerWaste = avg_PowerWaste;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public int getAlarm_thresholdValue() {
		return alarm_thresholdValue;
	}

	public void setAlarm_thresholdValue(int alarm_thresholdValue) {
		this.alarm_thresholdValue = alarm_thresholdValue;
	}

	public int getFault_threshold() {
		return fault_threshold;
	}

	public void setFault_threshold(int fault_threshold) {
		this.fault_threshold = fault_threshold;
	}

	public int getAlarm_max_thresholdValue() {
		return alarm_max_thresholdValue;
	}

	public void setAlarm_max_thresholdValue(int alarm_max_thresholdValue) {
		this.alarm_max_thresholdValue = alarm_max_thresholdValue;
	}

	public int getAlarm_min_thresholdValue() {
		return alarm_min_thresholdValue;
	}

	public void setAlarm_min_thresholdValue(int alarm_min_thresholdValue) {
		this.alarm_min_thresholdValue = alarm_min_thresholdValue;
	}

	public int getFault_max_threshold() {
		return fault_max_threshold;
	}

	public void setFault_max_threshold(int fault_max_threshold) {
		this.fault_max_threshold = fault_max_threshold;
	}

	public int getFault_min_threshold() {
		return fault_min_threshold;
	}

	public void setFault_min_threshold(int fault_min_threshold) {
		this.fault_min_threshold = fault_min_threshold;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDetector() {
		return detector;
	}

	public void setDetector(String detector) {
		this.detector = detector;
	}

	@Override
	public String toString() {
		return "ServerInfoEntity [now_Temper=" + now_Temper + ", max_Temper="
				+ max_Temper + ", min_Temper=" + min_Temper + ", avg_Temper="
				+ avg_Temper + ", now_PowerWaste=" + now_PowerWaste
				+ ", max_PowerWaste=" + max_PowerWaste + ", min_PowerWaste="
				+ min_PowerWaste + ", avg_PowerWaste=" + avg_PowerWaste
				+ ", alarm_thresholdValue=" + alarm_thresholdValue
				+ ", fault_threshold=" + fault_threshold
				+ ", alarm_max_thresholdValue=" + alarm_max_thresholdValue
				+ ", alarm_min_thresholdValue=" + alarm_min_thresholdValue
				+ ", fault_max_threshold=" + fault_max_threshold
				+ ", fault_min_threshold=" + fault_min_threshold + ", state="
				+ state + ", condition=" + condition + ", detector=" + detector
				+ ", reading=" + reading + ", value=" + value + ", attribute="
				+ attribute + "]";
	}
}