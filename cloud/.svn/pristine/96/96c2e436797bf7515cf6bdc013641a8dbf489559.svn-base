package cn.com.kxcomm.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;


public class SupportToolTest {
	SupportTool aa = SupportTool.getSingleInstance();
	
	/**
	 * 
	* 检查单个服务器是否支持NodeManager
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	//@Test
	public void checkSucessIpmi(){
		SerInfo ser = new SerInfo();
		ser.setHost("192.168.1.24");
		ser.setUser("root");
		ser.setPasswd("123456");
		boolean cc = aa.checkSucessIpmi(ser);
		System.out.println("checkSucessIpmi:"+cc);
	}
	
	/**
	 * 
	* 检查列表服务器是否支持NodeManager
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	//@Test
	public void checkSucessIpmiList(){
		long start = System.currentTimeMillis();
		ArrayList<SerInfo> list = new ArrayList<SerInfo>();
		for(int i=1;i<254;i++){
			SerInfo ser = new SerInfo();
			ser.setHost("192.168.1."+i);
			ser.setUser("ipmi");
			ser.setPasswd("123456");
			list.add(ser);
		}
		
		List<SerInfo> rs = aa.checkSucessIpmi(list);
		System.out.println(rs.size());
		for(int i=0;i<rs.size();i++){
			SerInfo ser = rs.get(i);
			System.out.println("Success----------------------------:"+ser.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("total time:"+(end - start));
	}
	
	@Test
	public void getIpmiVersion(){
		SerInfo ser = new SerInfo();
		ser.setHost("192.168.1.24");
		ser.setUser("ipmi");
		ser.setPasswd("123456");
		String version  = aa.getIpmiVersion(ser);
		System.out.println(version);
	}
}
