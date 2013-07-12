
package cn.com.kxcomm.ipmitool;

import cn.com.kxcomm.ipmitool.entity.Capability;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.RawCreator;
import cn.com.kxcomm.ipmitool.raw.SystemRaw;

public class Power {
	private static Power instance = null;

	public static Power getSingleInstance() {
		if (instance == null) {
			instance = new Power();
			
		}
		return instance;
	}

	private Power() {
	}
		
	/**
	* 
	* 获取平台的功耗读数(系统,CPU,内存)<br/>
	* 命令<br/>
	* NM 1.5:raw 0x2E 0xC8 0x57 0x01 0x00 0x01 0x00 0x00<br/>
	* NM 2.0:raw 0x2E 0xC8 0x57 0x01 0x00 0x11 0x00 0x00<br/>
	* IDRAC7:raw 0x2E 0xC8 0x57 0x01 0x00 0x01 0x00 0x00<br/>
	* @param ser
	* @param domain
	* @return PowerReading
	* @author zhangjh 新增日期：2012-8-21
	* @since ipmi_sdk
	 */
	 public PowerReading getPlatformPowerReading(SerInfo ser,int domain){
		SystemRaw c = RawCreator.creatorSystem(ser);
		return c.getPlatformPowerReading(domain);
	 }
	 
	 /**
	 * 
	 * 获取功耗服务器的范围<br/>
	* 命令<br/>
	* NM 1.5:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	* NM 2.0:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	* IDRAC7:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	 * @param ser
	 * @param domain 
	 * @return Capability
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	  */
	 public Capability getCapabilityByPower(SerInfo ser){
			SystemRaw c = RawCreator.creatorSystem(ser);
			return c.getCapabilityByPower();
	 }
}
