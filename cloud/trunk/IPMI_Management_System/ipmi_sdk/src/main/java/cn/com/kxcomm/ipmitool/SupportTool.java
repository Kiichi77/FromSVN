package cn.com.kxcomm.ipmitool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.Errors;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.exception.SeriousError;
import cn.com.kxcomm.ipmitool.util.ErrorCode;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 
* 提供一些辅助类
* @author zhangjh 新增日期：2012-8-23
* @since ipmi_sdk
 */
public class SupportTool {
	private static final Logger log = Logger.getLogger(SupportTool.class);
	private static SupportTool instance = null;

	public static SupportTool getSingleInstance() {
		if (instance == null) {
			instance = new SupportTool();
		}
		return instance;
	}

	private SupportTool() {}
	/**
	 * 
	* 获取IPMI版本
	* @param ser
	* @return
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	@SuppressWarnings("all") 
	public String getIpmiVersion(SerInfo ser){
		ExecutorService service = Executors.newSingleThreadExecutor();
		Callable checkOut = new NMversionCallable(ser);  
		FutureTask<String> task = new FutureTask<String>(checkOut);
		service.submit(task);
		Errors errors = new Errors(ErrorCode.NOT_SUPPORT_NM,"Host not support NodeManager,Please check your server.Error host:"+ser.getHost());
		try {
			//3秒
			task.get(3000, TimeUnit.MILLISECONDS);
		}catch (TimeoutException e) {
			throw new SeriousError(errors);
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			service.shutdown();
		}
		try {
			String version = task.get();
			return version;
		} catch (Exception e) {
			throw new SeriousError(errors);
		} 		
	}
	
	/**
	* 
	* 检测队列中的服务器是否支持IPMI
	* @param list
	* @return List
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public List<SerInfo> checkSucessIpmi(List<SerInfo> list){
		List<SerInfo> sucess = new ArrayList<SerInfo>();
		for(int i=0;i<list.size();i++){
			try{
				SerInfo ser = (SerInfo) list.get(i);
				boolean is = checkSucessIpmi(ser);
				if(is){
					sucess.add(ser);
				}
			}catch(SeriousError e ){
				log.info(e.getMessage());
			}
		}
		return sucess;
	}
	
	/**
	* 
	* 检测服务器是否支持IPMI
	* @param ser
	* @return boolean
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public boolean checkSucessIpmi(SerInfo ser){
		try{
			//Util.checkNetwork(ser.getHost());
			String version = getIpmiVersion(ser);
			if(!StringUtils.isBlank(version)){
				log.info("support NodeManager:"+ser.getHost()+",version"+version);
				return true;
			}
		}catch(SeriousError e ){
			log.info(e.getMessage());
		}
		return false;
	}
	
	/**
	 * 
	 * 用于IP能通,但是不支持NM的服务器判断
	 * 
	 * @author zhangjh 新增日期：2012-8-23
	 * @since ipmi_sdk
	 */
	@SuppressWarnings("all")
	class NMversionCallable implements Callable {
		SerInfo ser;
		
		public NMversionCallable(SerInfo ser) {
			this.ser = ser;
		}

		@Override
		public String call() throws Exception {
			String s_version = null;
			String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " mc info";
			log.info("command:" + command);
			Process p;
			StringBuffer sb = new StringBuffer();
			Runtime r = Runtime.getRuntime();
			HashMap map = new HashMap();
			try {
				p = r.exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String inline;
				while (StringUtils.isNotEmpty(inline = br.readLine())) {
					try{
						String[] tmp = StringUtils.split(inline,":");
						if(tmp.length>1){
							map.put(StringUtils.trim(tmp[0]), StringUtils.trim(tmp[1]));
						}
					}catch(Exception e){
						log.warn("Exception:",e);
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String version =  (String) map.get(Parameter.IPMI_Version);
			if("1.5".equals(version)){
				s_version = Parameter.NodeManager_15;
			}else if("2.0".equals( version)){
				s_version = Parameter.NodeManager_Unkown;
			}else{
				s_version = Parameter.NodeManager_20;
			}
			return s_version;
		}
	}
}
