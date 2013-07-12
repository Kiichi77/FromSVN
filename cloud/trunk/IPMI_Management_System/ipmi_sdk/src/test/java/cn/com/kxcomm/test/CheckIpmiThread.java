package cn.com.kxcomm.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class CheckIpmiThread implements Callable  {
	private SerInfo ser;
	private SupportTool a;
	public CheckIpmiThread(String ip,String user,String pwd){
		a = SupportTool.getSingleInstance();
		ser=new SerInfo();
		ser.setHost(ip);
		ser.setPasswd(pwd);
		ser.setUser(user);
	}

	@Override
	public String call() throws Exception {
		String  status = ser.getHost();
		try {
			boolean flag = InetAddress.getByName(status).isReachable(2000);
			if(!flag)return "";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}
