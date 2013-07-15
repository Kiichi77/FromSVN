package cn.com.kxcomm.ipmitool.raw.nm20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.SystemRaw;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.ipmitool.util.Util;

public class SystemRaw20 extends SystemRaw {
	public SystemRaw20(SerInfo ser) {
		super(ser);
	}
	
	private static final Logger log = Logger.getLogger(SystemRaw20.class);
	@Override
	public PowerReading getPlatformPowerReading( int domain) {
		PowerReading powerReading = new PowerReading();
		powerReading.setDesc("Watts");
		String _domain = Util.getDomain(domain);
		if ("null".equals(_domain)) {
			return null;
		}
		String raw  = " raw 0x2e 0xc8 0x57 0x01 0x00 0x11 " + _domain + " 0x00";
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + raw;
		
		log.info(command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (StringUtils.isNotEmpty(inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String str = sb.toString();
		if ("".equals(str.trim())) {
			return null;
		}
		log.info("return:" + str);
		String[] tmp = str.split(" ");
			
		powerReading.setCurrent(Integer.parseInt(tmp[5] + tmp[4], 16));
		powerReading.setMin(Integer.parseInt(tmp[7] + tmp[6], 16));
		powerReading.setMax(Integer.parseInt(tmp[9] + tmp[8], 16));
		powerReading.setAverage(Integer.parseInt(tmp[11] + tmp[10], 16));
		long tt = Long.parseLong(tmp[15] + tmp[14] + tmp[13] + tmp[12], 16);
		powerReading.setCurrentTime(tt);
		tt = Long.parseLong(tmp[19].trim() + tmp[18].trim() + tmp[17].trim()
				+ tmp[16].trim(), 16);
		powerReading.setStatReportingPeriod(tt);
		int cc = Integer.parseInt(tmp[20].trim(), 16);
		if (0x40 == cc) {
			powerReading.setComponent(Parameter.domainSystem);
			powerReading.setPolicyEnabled(false);
		} else if (0x41 == cc) {
			powerReading.setComponent(Parameter.domainCpu);
			powerReading.setPolicyEnabled(false);
		} else if (0x42 == cc) {
			powerReading.setComponent(Parameter.domainMemory);
			powerReading.setPolicyEnabled(false);
		} else if (0x50 == cc) {
			powerReading.setComponent(Parameter.domainSystem);
			powerReading.setPolicyEnabled(true);
		} else if (0x51 == cc) {
			powerReading.setComponent(Parameter.domainCpu);
			powerReading.setPolicyEnabled(true);
		} else if (0x52 == cc) {
			powerReading.setComponent(Parameter.domainMemory);
			powerReading.setPolicyEnabled(true);
		}
		return powerReading;
	 }

}
