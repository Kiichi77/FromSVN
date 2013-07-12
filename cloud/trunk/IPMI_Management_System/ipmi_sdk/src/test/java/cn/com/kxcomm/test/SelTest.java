package cn.com.kxcomm.test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Sel;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class SelTest {
	Sel aa = Sel.getSingleInstance();
	private SerInfo ser = new SerInfo();

	public SelTest() {
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
	}

	/**
	 * 
	 * 查询BMC中关于SEL和其内容的相关信息
	 * 
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public void getSelInfo() {
		Map<String, String> map = aa.getSelInfo(ser);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
	}

	/**
	 * 
	 * 返回SEL所有日志
	 * 
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	
	public void querySelLog() {
		List<String> list = aa.querySelLog(ser);
		for (String str : list) {
			System.out.println(str);
		}
	}
	/**
	 * 
	 * 清空SEL日志
	 * 
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public void clear(){
		aa.clear(ser);
	}

}
