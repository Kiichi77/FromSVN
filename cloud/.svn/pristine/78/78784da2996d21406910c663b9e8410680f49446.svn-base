package cn.com.kxcomm.ipmitool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.ChassisStatus;
import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.Record;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Util;

public class Sel {
	private static final Logger log = Logger.getLogger(Sensor.class);
	private static Sel instance = null;
	public static Sel getSingleInstance() {
		if (instance == null) {
			instance = new Sel();
			
		}
		return instance;
	}

	private Sel() {
	}

	/**
	 * 
	 * 查询BMC中关于SEL和其内容的相关信息
	 * 当 Percent Used = 100%,建议备份日志,然后清空日志
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public Map<String,String> getSelInfo(SerInfo ser) {
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sel info";
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		Map<String,String> map = new HashMap<String,String>();
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
		return map;
	}

	/**
	 * 
	 * 返回SEL所有日志
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public List<String> querySelLog(SerInfo ser) {
		Util.checkNetwork(ser.getHost());
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sel elist";
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		List<String> list = new ArrayList<String>();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				try{
					list.add(inline);
				}catch(Exception e){
					log.warn("Exception:",e);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 清空SEL中的信息。它不能被撤消所以要小心 sel clear
	 * 
	 * @param ser
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public void clear(SerInfo ser) {
		Util.checkNetwork(ser.getHost());
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sel clear";
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				System.out.println(inline);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 导出日志文件 ipmitool sel list >yukytets.txt
	 * 
	 * @param ser
	 * @param path
	 * @param filename
	 * @author zhangjh 新增日期：2012-8-27
	 * @since ipmi_sdk
	 */
	public void exportLogFile(SerInfo ser, String path, String filename) {
		Util.checkNetwork(ser.getHost());
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sel elist >"+path+filename;
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				System.out.println(inline);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
