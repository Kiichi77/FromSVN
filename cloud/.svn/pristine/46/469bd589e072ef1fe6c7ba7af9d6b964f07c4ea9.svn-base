package cn.com.kxcomm.test;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Sdr;
import cn.com.kxcomm.ipmitool.Sensor;
import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;

public class SensorTest {
	Sdr sdr = Sdr.getSingleInstance();
	Sensor sensor = Sensor.getSingleInstance();
	private GobblerServer GobblerServer =new GobblerServer("192.168.1.11", "root","root.123"); 
	private SerInfo ser = new SerInfo();

	public SensorTest() {
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
	}

	/**
	 * 
	 * 获取传感器列表和其详情 注意: 如果获取所有传感器信息会花费比较长时间,不建议实时查询
	 * 
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void getFanRPMInfo() {
		List<List<GlobalEntity>> ll = sensor.getCPUInfo(ser,GobblerServer);
		System.out.println("all:" + ll.size());
		for (int i = 0; i < ll.size(); i++) {
			List map = ll.get(i);
			System.out.println("map.size():"+map.size());
			for (int j=0;j<map.size();j++) {
				GlobalEntity cc = (GlobalEntity) map.get(j);
				System.out.println(cc.toString());
			}
			System.out.println("**********************************************");
		}
	}

	/**
	 * 
	 * 获取传感器信息
	 * 
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void getSensorInfo() {
		List map = sensor.getSensorInfo(ser, Parameter.PwrConsumption,GobblerServer);
		for (int j=0;j<map.size();j++) {
			GlobalEntity cc = (GlobalEntity) map.get(j);
			System.out.println(cc.toString());
		}
	}
}
