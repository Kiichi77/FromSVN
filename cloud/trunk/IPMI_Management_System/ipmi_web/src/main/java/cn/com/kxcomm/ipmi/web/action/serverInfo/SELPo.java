package cn.com.kxcomm.ipmi.web.action.serverInfo;

public class SELPo {
	@Override
	public String toString() {
		return "SELPo [id=" + id + ", time=" + time + ", event=" + event
				+ ", ssert=" + ssert + ", eventResult=" + eventResult + "]";
	}
	/**
	 * 序列号
	 */
	private String id;
	/**
	 * 2 3 个合起来的 时间
	 */
	private String time;
	/**
	 * 事件
	 */
	private String event;
	/**
	 * 不知道是啥
	 */
	private String ssert;
	public String getSsert() {
		return ssert;
	}
	public void setSsert(String ssert) {
		this.ssert = ssert;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventResult() {
		return eventResult;
	}
	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}
	/**
	 * 事件结果
	 */
	private String eventResult;
	
}
