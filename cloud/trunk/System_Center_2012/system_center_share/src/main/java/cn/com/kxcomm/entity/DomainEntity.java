package cn.com.kxcomm.entity;

import java.io.Serializable;

/**
 * 
 * 功能描述:域实体
 * 
 * @author chenliang 新增日期：2013-6-24
 * @since system_center_sdk
 */
public class DomainEntity implements Serializable {

	private Long id;
	private String domainName; //域名
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
