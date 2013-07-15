package cn.com.kxcomm.ipmi.web.util;

import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class TakePolicyThread extends Thread {
	
	private SerInfo ser = new SerInfo();
	public TakePolicyThread(SerInfo sers) {
		ser = sers;
	} 
	
	@Override
	public void run() {
		Policy aa = Policy.getSingleInstance();
		aa.removeAllPolicy(ser);
	}
}
