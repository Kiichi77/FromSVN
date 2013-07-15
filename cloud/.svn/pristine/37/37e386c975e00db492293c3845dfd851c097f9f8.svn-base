package cn.com.kxcomm.test;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.SystemRaw;
import cn.com.kxcomm.ipmitool.raw.RawCreator;
import cn.com.kxcomm.ipmitool.util.Parameter;

public class CommandTest {
	private SerInfo ser = new SerInfo();

	public CommandTest() {
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
	}
	@Test
	public void run() {
		SystemRaw c=RawCreator.creatorSystem(ser);
		c.getPlatformPowerReading(Parameter.domainCpu);
	}
}
