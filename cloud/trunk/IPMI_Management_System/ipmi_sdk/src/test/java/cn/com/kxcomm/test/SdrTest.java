package cn.com.kxcomm.test;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Sdr;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class SdrTest {
	Sdr aa = Sdr.getSingleInstance();
	private SerInfo ser = new SerInfo();
	public SdrTest(){
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
	}
	
	/**
	 * 
	 * 获取传感器列表
	 * 
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public void getSdrList(){
		List<String> ll = aa.getSdrList(ser,"all");
		System.out.println("all:"+ll.size());
		
		ll = aa.getSdrList(ser,"full");
		System.out.println("full:"+ll.size());
		
		ll = aa.getSdrList(ser,"compact");
		System.out.println("compact:"+ll.size());
		
		ll = aa.getSdrList(ser,"event");
		System.out.println("event:"+ll.size());
		
		ll = aa.getSdrList(ser,"mcloc");
		System.out.println("mcloc:"+ll.size());
		
		ll = aa.getSdrList(ser,"fru");
		System.out.println("fru:"+ll.size());
		
		ll = aa.getSdrList(ser,"generic");
		System.out.println("generic:"+ll.size());
	}
}
