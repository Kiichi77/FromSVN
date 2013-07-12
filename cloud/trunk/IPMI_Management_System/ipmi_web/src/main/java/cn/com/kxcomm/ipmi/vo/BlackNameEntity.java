package cn.com.kxcomm.ipmi.vo;

import java.io.Serializable;
import cn.com.kxcomm.ipmi.entity.TbServer;

public class BlackNameEntity implements Serializable {
	private Long id;
	private Long serviceId;
	private TbServer serverEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public TbServer getServerEntity() {
		return serverEntity;
	}

	public void setServerEntity(TbServer serverEntity) {
		this.serverEntity = serverEntity;
	}

	@Override
	public String toString() {
		return "BlackNameEntity [id=" + id + ", serviceId=" + serviceId
				+ ", serverEntity=" + serverEntity + "]";
	}

}
