package cn.com.kxcomm.woyun.vo;

import java.io.Serializable;


/**
 * 
 * 功能描述:地址vo
 * 
 * @author chenliang 新增日期：2012-11-30
 * @since WoYun_Web
 */
public class AddressVO implements Serializable {

	public String version;
	
	public String addr;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "AddressVO [version=" + version + ", addr=" + addr + "]";
	}
	
}
