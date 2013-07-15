package cn.com.kxcomm.test;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Power;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;

public class PowerTest {
	Power aa = Power.getSingleInstance();
	private SerInfo ser = new SerInfo();

	public PowerTest() {
		ser.setHost("192.168.1.24");
		ser.setUser("root");
		ser.setPasswd("123456");
	}

	int policyId = 3;
	int component = Parameter.domainSystem;



	/**
	 * 
	 * 获取平台功耗,最大,最小,平均等
	 * 
	 * @author zhangjh 新增日期：2012-8-23
	 * @since ipmi_sdk
	 */
	@Test
	public void getGlobalPowerReading() {
		PowerReading cc = aa.getPlatformPowerReading(ser,Parameter.domainSystem);
		if (cc != null)
			System.out.println("policyThermal:" + cc.toString());

		cc = aa.getPlatformPowerReading(ser,Parameter.domainCpu);
		if (cc != null)
			System.out.println("policyPower:" + cc.toString());
	}


}
