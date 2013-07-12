package cn.com.kxcomm.contractmanage.vo;

public class SystemLogVo {
  private String id;
  private String logType;
  private String operaterName;
  private String result;
  private String operTime;
  private String message;
  private String operatContent;
  private String endtime;
  private String starttime;
  
public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getLogType() {
	return logType;
}


public void setLogType(String logType) {
	this.logType = logType;
}


public String getOperaterName() {
	return operaterName;
}


public void setOperaterName(String operaterName) {
	this.operaterName = operaterName;
}


public String getResult() {
	return result;
}


public void setResult(String result) {
	this.result = result;
}


public String getOperTime() {
	return operTime;
}


public void setOperTime(String operTime) {
	this.operTime = operTime;
}


public String getMessage() {
	return message;
}


public void setMessage(String message) {
	this.message = message;
}


public String getOperatContent() {
	return operatContent;
}


public void setOperatContent(String operatContent) {
	this.operatContent = operatContent;
}


public String getEndtime() {
	return endtime;
}


public void setEndtime(String endtime) {
	this.endtime = endtime;
}


public String getStarttime() {
	return starttime;
}


public void setStarttime(String starttime) {
	this.starttime = starttime;
}


@Override
public String toString() {
	return "SystemLogVo [id=" + id + ", logType=" + logType + ", operaterName="
			+ operaterName + ", result=" + result + ", operTime=" + operTime
			+ ", message=" + message + ", operatContent=" + operatContent
			+ ", endtime=" + endtime + ", starttime=" + starttime + "]";
}





  
}
