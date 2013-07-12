package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;

public class JsonBean implements Serializable {

	private String id;
	private String ip;
	private String serverId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return "JsonBean [id=" + id + ", ip=" + ip + ", serverId=" + serverId
				+ "]";
	}

}
