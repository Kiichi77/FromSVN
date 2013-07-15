package cn.com.kxcomm.ipmitool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Util;

/**
 * 
 * 获取所有传感器列表
 * 
 * @author zhangjh 新增日期：2012-8-24
 * @since ipmi_sdk
 */
public class Sdr {
	private static final Logger log = Logger.getLogger(Sdr.class);
	private static Sdr instance = null;

	public static Sdr getSingleInstance() {
		if (instance == null) {
			instance = new Sdr();
		}
		return instance;
	}

	private Sdr() {
	}
	
	/**
	 * 
	 * 获取传感器列表
	 * 
	 * @param ser
	 * @param type
	 *            [all|full|compact|event|mcloc|fru|generic] 
	 *            Valid types are: 
	 *            all All SDR records (Sensor and Locator)
	 *            full Full Sensor Record
	 *            compact Compact Sensor Record
	 *            event Event-Only Sensor Record
	 *            mcloc Management Controller Locator Record
	 *            fru FRU Locator Record
	 *            generic Generic SDR records
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public List<String> getSdrList(SerInfo ser,String type){
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sdr list " + type;
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		ArrayList<String> ll = new ArrayList<String>();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				String[] tmp = StringUtils.split(inline,"|");
				ll.add(tmp[0].trim());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ll;
	}
}
