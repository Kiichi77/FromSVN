package cn.com.kxcomm.ipmi.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * 功能描述:服务器所属集合表菜单entity
 * 
 * @author chenl 新增日期：2012-8-10
 * @since ipmi_web
 */
public class FailRecordEntity implements Serializable {
	private Long id;
	private Long serverId;
	private Long policyId;
	private Integer policyIdInServer;
	private Integer domainInServer;
	private Integer operat;
	private Integer failReason;
	private Integer modifyEnterLimit;
	private Integer modifyEnterHope;
	private String serverIp;
	private String datetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Integer getPolicyIdInServer() {
		return policyIdInServer;
	}

	public void setPolicyIdInServer(Integer policyIdInServer) {
		this.policyIdInServer = policyIdInServer;
	}

	public Integer getDomainInServer() {
		return domainInServer;
	}

	public void setDomainInServer(Integer domainInServer) {
		this.domainInServer = domainInServer;
	}

	public Integer getOperat() {
		return operat;
	}

	public void setOperat(Integer operat) {
		this.operat = operat;
	}

	public Integer getFailReason() {
		return failReason;
	}

	public void setFailReason(Integer failReason) {
		this.failReason = failReason;
	}

	public Integer getModifyEnterLimit() {
		return modifyEnterLimit;
	}

	public void setModifyEnterLimit(Integer modifyEnterLimit) {
		this.modifyEnterLimit = modifyEnterLimit;
	}

	public Integer getModifyEnterHope() {
		return modifyEnterHope;
	}

	public void setModifyEnterHope(Integer modifyEnterHope) {
		this.modifyEnterHope = modifyEnterHope;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "FailRecordEntity [id=" + id + ", serverId=" + serverId
				+ ", policyId=" + policyId + ", policyIdInServer="
				+ policyIdInServer + ", domainInServer=" + domainInServer
				+ ", operat=" + operat + ", failReason=" + failReason
				+ ", modifyEnterLimit=" + modifyEnterLimit
				+ ", modifyEnterHope=" + modifyEnterHope + ", serverIp="
				+ serverIp + ", datetime=" + datetime + "]";
	}

}
