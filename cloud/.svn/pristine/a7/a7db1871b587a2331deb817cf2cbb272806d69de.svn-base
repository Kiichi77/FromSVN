package kxcomm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.kxcomm.ipmitool.Chassis;
import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

public class ServerRun {
	private SupportTool supportTool = SupportTool.getSingleInstance();
	private Chassis chassis = Chassis.getSingleInstance();
	public void run() throws Exception{
		System.out.println("IPMI服务器管理工具");
        long start = System.currentTimeMillis();
		ArrayList<SerInfo> list = new ArrayList<SerInfo>();
		HashMap map = new HashMap();
		map.put("192.168.1.27", "192.168.1.55");
		map.put("192.168.1.26", "192.168.1.50");
		map.put("192.168.1.25", "192.168.1.11");
		map.put("192.168.1.24", "192.168.1.12");
		for(int i=24;i<=27;i++){
			SerInfo ser = new SerInfo();
			ser.setHost("192.168.1."+i);
			ser.setUser("ipmi");
			ser.setPasswd("123456");
			list.add(ser);
		}
		
		List<SerInfo> rs = supportTool.checkSucessIpmi(list);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("请选择开机 or 关机:\n 1:开机 \n 2:关机");
        String oper = in.readLine();    
        if(!"1".equals(oper)&&!"2".equals(oper)){
	    	System.out.println("非法选择,终止程序");
	    	return;
	    }
		for(int i=0;i<rs.size();i++){
			SerInfo ser = rs.get(i);
			//当前状态
			boolean isTrue = chassis.currentPowerState(ser);
			System.out.println("服务器："+map.get(ser.getHost())+",Idrac IP:"+ser.getHost()+".目前系统状态为"+(isTrue==true?"开机":"关机")+"。是否进行"+("1".equals(oper)?"开机":"关机")+"?");
			System.out.println("请选择Yes 、No、Cancel.\n Y:Yes \n N:No \n C:Cancel");
			in = new BufferedReader(new InputStreamReader(System.in)); 
		    String open = in.readLine();    
		    if(open==null||"".equals(open)){
		    	System.out.println("非法选择,终止程序");
		    	break;
		    }
		    if(!"Y".equalsIgnoreCase(open) && !"N".equalsIgnoreCase(open) && !"C".equalsIgnoreCase(open)){
		    	System.out.println("非法选择,终止程序");
		    	break;
		    }
		    if("C".equalsIgnoreCase(open)){
		    	break;
		    }
		    if("N".equalsIgnoreCase(open)){
		    	continue;
		    }
		    //开机情况
		    if("1".equals(oper)){
		    	if(!isTrue){
		    		chassis.remotePowerOn(ser);
		    		System.out.println("正在开机.....");
		    	}else{
		    		System.out.println("系统已开机");
		    	}
		    }else if("2".equals(oper)){
		    	if(isTrue){
		    		chassis.remotePowerOff(ser);
		    		System.out.println("正在关机.....");
		    	}else{
		    		System.out.println("系统已关机");
		    	}
		    }
		}
		long end = System.currentTimeMillis();
		System.out.println("处理时间:"+(end - start)/1000+" 秒");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerRun s = new ServerRun();
		try {
			s.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
