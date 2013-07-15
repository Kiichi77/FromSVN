package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;

public class ServerVo implements Serializable {

	private String serverId; //实例id
	private String name; // 实例名称
	private String adminPass; //实例密码
	private String state; // 实例状态
	private String publicIP; // 公网ip地址
	private String privateIP; // 内网ip地址
	private String instanceName; // 设备
	private String workState; // 工作状态
	private Integer progress;// 硬盘
	private String powerState;// 电源状态  1为运行running，3为暂停Paused
	private String vmState ; // 虚拟机状态
	private String updatedTime; // 更新时间
	private String createdTime; // 创建时间
	private String userId; // 用户id
	private String tenantId; // 租户id
	
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getVmState() {
		return vmState;
	}

	public void setVmState(String vmState) {
		this.vmState = vmState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPublicIP() {
		return publicIP;
	}

	public void setPublicIP(String publicIP) {
		this.publicIP = publicIP;
	}

	public String getPrivateIP() {
		return privateIP;
	}

	public void setPrivateIP(String privateIP) {
		this.privateIP = privateIP;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getPowerState() {
		if("1".equals(powerState)){
			return "Running";
		}else if("3".equals(powerState)){
			return "Paused";
		}else{
			return "Error";
		}
	}

	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Override
	public String toString() {
		return "ServerVo [name=" + name + ", adminPass=" + adminPass
				+ ", state=" + state + ", publicIP=" + publicIP
				+ ", privateIP=" + privateIP + ", instanceName=" + instanceName
				+ ", workState=" + workState + ", progress=" + progress
				+ ", powerState=" + powerState + ", vmState=" + vmState
				+ ", updatedTime=" + updatedTime + ", createdTime="
				+ createdTime + ", userId=" + userId + ", tenantId=" + tenantId
				+ "]";
	}

}
