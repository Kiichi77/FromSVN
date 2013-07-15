package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;
import java.util.List;

import cn.com.woyun.nova.model.Flavor;
import cn.com.woyun.nova.model.SecurityGroup;
import cn.com.woyun.nova.model.Server.Addresses;

public class ServerForCreateVO implements Serializable {

	private String serverId; // 实例id
	private String name; // 实例名称
	private String adminPass; // 密码
	private String imageRef; // 镜像id
	private String imageName; // 镜像名称
	private String flavorRef; // 模板id
	private String accessIPv4; // ipv4地址
	private String accessIPv6; // ipv6地址
	private Integer min;
	private Integer max;
	private String diskConfig;
	private String keyName;
	private List<SecurityGroup> securityGroupList;

	private String state; // 状态
	private FlavorsVO flavor; // 模板

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	private List<AddressVO> addresses;// 地址

	public FlavorsVO getFlavor() {
		return flavor;
	}

	public void setFlavor(FlavorsVO flavor) {
		this.flavor = flavor;
	}

	public List<AddressVO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressVO> addresses) {
		this.addresses = addresses;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<SecurityGroup> getSecurityGroupList() {
		return securityGroupList;
	}

	public void setSecurityGroupList(List<SecurityGroup> securityGroupList) {
		this.securityGroupList = securityGroupList;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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

	public String getImageRef() {
		return imageRef;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

	public String getFlavorRef() {
		return flavorRef;
	}

	public void setFlavorRef(String flavorRef) {
		this.flavorRef = flavorRef;
	}

	public String getAccessIPv4() {
		return accessIPv4;
	}

	public void setAccessIPv4(String accessIPv4) {
		this.accessIPv4 = accessIPv4;
	}

	public String getAccessIPv6() {
		return accessIPv6;
	}

	public void setAccessIPv6(String accessIPv6) {
		this.accessIPv6 = accessIPv6;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getDiskConfig() {
		return diskConfig;
	}

	public void setDiskConfig(String diskConfig) {
		this.diskConfig = diskConfig;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Override
	public String toString() {
		return "ServerForCreateVO [serverId=" + serverId + ", name=" + name
				+ ", adminPass=" + adminPass + ", imageRef=" + imageRef
				+ ", flavorRef=" + flavorRef + ", accessIPv4=" + accessIPv4
				+ ", accessIPv6=" + accessIPv6 + ", min=" + min + ", max="
				+ max + ", diskConfig=" + diskConfig + ", keyName=" + keyName
				+ ", securityGroupList=" + securityGroupList + "]";
	}

}
