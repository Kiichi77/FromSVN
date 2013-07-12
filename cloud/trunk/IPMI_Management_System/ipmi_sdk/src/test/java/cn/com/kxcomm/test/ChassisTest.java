package cn.com.kxcomm.test;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Chassis;
import cn.com.kxcomm.ipmitool.entity.ChassisStatus;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class ChassisTest {
	private SerInfo ser = new SerInfo();

	public ChassisTest() {
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
	}

	Chassis aa = Chassis.getSingleInstance();

	
	/**
	 * 
	* 测试获取当前电源状态
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void currentPowerState() {
		boolean is = aa.currentPowerState(ser);
		System.out.println(is);
	}

	
	/**
	 * 
	* 远程开机
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void remotePowerOn() {
		aa.remotePowerOn(ser);
	}

	/**
	 * 
	* 远程关机
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void remotePowerOff() {
		aa.remotePowerOff(ser);
	}
	
	/**
	 * 
	* 重启
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void remotePowerReset() {
		aa.remoePowerReset(ser);
	}
	
	/**
	 * 
	* 获取底盘状态
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public void chassisStatus(){
		ChassisStatus bb = aa.chassisStatus(ser);
		System.out.println(bb.toString());
	}

}
