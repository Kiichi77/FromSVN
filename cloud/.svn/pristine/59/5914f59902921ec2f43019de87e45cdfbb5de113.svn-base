package cn.com.kxcomm.ipmitool.raw.interfaces;

import cn.com.kxcomm.ipmitool.entity.Capability;
import cn.com.kxcomm.ipmitool.entity.PowerReading;

/**
 * 
 * 定义系统级的原始命令需要实现<br/>
 * 默认以IDRAC7为标准,如果判断不是IDRAC7则调其他命令
 * 
 * @author zhangjh 新增日期：2012-9-28
 * @since ipmi_sdk
 */
public interface ISystemRaw {
	/**
	 * 
	 * 远程开机<br/>
	 * 
	 * @param ser
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public void remotePowerOn();

	/**
	 * 
	 * 远程关机 命令<br/>
	 * 
	 * @param ser
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public void remotePowerOff();

	/**
	 * 
	 * 查看服务器的状态 命令<br/>
	 * 
	 * @param ser
	 * @return true:开机 false:关机
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public boolean currentPowerState();

	/**
	 * 
	 * 重启系统 命令<br/>
	 * 
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public void remoePowerReset();


	/**
	 * 
	 * 获取平台的功耗读数(系统,CPU,内存)<br/>
	 * 命令<br/>
	 * NM 1.5:raw 0x2E 0xC8 0x57 0x01 0x00 0x01 0x00 0x00<br/>
	 * NM 2.0:raw 0x2E 0xC8 0x57 0x01 0x00 0x11 0x00 0x00<br/>
	 * IDRAC7:raw 0x2E 0xC8 0x57 0x01 0x00 0x01 0x00 0x00<br/>
	 * 
	 * @param ser
	 * @param domain
	 * @return PowerReading
	 * @author zhangjh 新增日期：2012-8-21
	 * @since ipmi_sdk
	 */
	public PowerReading getPlatformPowerReading(int domain);

	/**
	 * 
	 * 获取功耗服务器的范围<br/>
	 * 命令<br/>
	 * NM 1.5:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	 * NM 2.0:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	 * IDRAC7:raw 0x2E 0xC9 0x57 0x01 0x00 0x00 0x10<br/>
	 * 
	 * @param ser
	 * @param domain
	 * @return Capability
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public Capability getCapabilityByPower();
}
