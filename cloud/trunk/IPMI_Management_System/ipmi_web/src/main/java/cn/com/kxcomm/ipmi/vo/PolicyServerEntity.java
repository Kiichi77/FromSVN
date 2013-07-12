package cn.com.kxcomm.ipmi.vo;

import java.io.Serializable;

public class PolicyServerEntity implements Serializable {

	private Long policyId; // 策略id
	private Long[] serverId; // 服务器id
	private Integer biningServerType; // 绑定服务器类型
	private Integer biningState; // 绑定状态
	private Integer biningType; // 策略类型

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Long[] getServerId() {
		return serverId;
	}

	public void setServerId(Long[] serverId) {
		this.serverId = serverId;
	}

	public Integer getBiningServerType() {
		return biningServerType;
	}

	public void setBiningServerType(Integer biningServerType) {
		this.biningServerType = biningServerType;
	}

	public Integer getBiningState() {
		return biningState;
	}

	public void setBiningState(Integer biningState) {
		this.biningState = biningState;
	}

	public Integer getBiningType() {
		return biningType;
	}

	public void setBiningType(Integer biningType) {
		this.biningType = biningType;
	}

}
