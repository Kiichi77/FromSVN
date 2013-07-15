package cn.com.kxcomm.task.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmitool.Sel;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class SdkTest {
	private Sel sel = Sel.getSingleInstance();
	@Test
	public void querySelLog() throws ParseException{
		SerInfo ser = new SerInfo();
		ser.setHost("192.168.1.20");
		ser.setUser("test");
		ser.setPasswd("123456");
		List<String> serLog = sel.querySelLog(ser);
		System.out.println(serLog.size());
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy  HH:mm:ss"); 
		for(String sLog:serLog){
			System.out.println(sLog);
			String aa[] = StringUtils.split(sLog,"|"); 
			System.out.println(aa.length);
			
			String createTime = aa[1]+aa[2];
			Date date = format.parse(createTime); 
			String selMsg = aa[3]+","+aa[4];
			String state = "";
			if(aa.length >= 6){
				state = aa[5];
			}
			System.out.println("createTime:"+createTime+",selMsg:"+selMsg+",state:"+state+",date:"+date);
		}
	}
}
