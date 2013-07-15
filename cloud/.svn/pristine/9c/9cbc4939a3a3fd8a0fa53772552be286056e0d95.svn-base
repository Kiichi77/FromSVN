package cn.com.kxcomm.ipmitool.raw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.Capability;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.interfaces.ISystemRaw;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.ipmitool.util.Util;

/**
 * 
 * 原始命令集,以IDRAC7为基准,如果NM2.0或1.5与其不一样,则在对应的类重写该方法
 * 
 * @author zhangjh 新增日期：2012-9-28
 * @since ipmi_sdk
 */
public  class SystemRaw implements ISystemRaw {
	private static final Logger log = Logger.getLogger(SystemRaw.class);
	protected SerInfo ser;
	
	public SystemRaw (SerInfo ser){
		this.ser = ser;
	}
	
	@Override
	public void remotePowerOn() {
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " chassis power on";
		log.info(command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (null != (inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remotePowerOff() {
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + "  chassis power off";
		log.info(command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (null != (inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean currentPowerState() {
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " chassis power status";
		log.info("command:" + command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (null != (inline = br.readLine())) {
				sb.append(inline).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		String str = sb.toString();
		if ("Chassis Power is on".equals(str.trim())) {
			return true;
		}else{
			
		}
		return false;
	}

	@Override
	public void remoePowerReset() {
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " chassis power reset";
		log.info("command:" + command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			while (null != (inline = br.readLine())) {
				sb.append(inline);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PowerReading getPlatformPowerReading(int domain) {
		PowerReading powerReading = new PowerReading();
		powerReading.setDesc("Watts");
		String _domain = Util.getDomain(domain);
		if ("null".equals(_domain)) {
			return null;
		}
		String raw  = " raw 0x2e 0xc8 0x57 0x01 0x00 0x01 " + _domain + " 0x00";
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + raw;
		
		log.info("command:" + command);
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

	@Override
	public Capability getCapabilityByPower() {
		Capability entity = new Capability();
		String command = "ipmitool -H " + ser.getHost() + " -U "  + ser.getUser() + " -P " + ser.getPasswd() + " raw 0x2e 0xc9 0x57 0x01 0x00 0x00 0x10";
		log.info("command:" + command);
		Process p;
		StringBuffer sb = new StringBuffer();
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
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

		entity.setMaxConcurrentActivePolicy(Integer.parseInt(tmp[4], 16));
		entity.setMaxPower(Integer.parseInt(tmp[6] + tmp[5], 16));
		entity.setMinPower(Integer.parseInt(tmp[8] + tmp[7], 16));
		entity.setMinCorrectionTime(Integer.parseInt(tmp[12] + tmp[11]
				+ tmp[10] + tmp[9], 16));
		entity.setMaxCorrectionTime(Integer.parseInt(
				tmp[16].trim() + tmp[15].trim() + tmp[14].trim()
						+ tmp[13].trim(), 16));
		entity.setMinStatisticsReportPeriod(Integer.parseInt(tmp[18] + tmp[17],
				16));
		entity.setMaxStatisticsReportPeriod(Integer.parseInt(tmp[20] + tmp[19],
				16));
		int cc = Integer.parseInt(tmp[21].trim(), 16);
		System.out.println(cc);
		if (0x00 == cc) {
			entity.setDomain(0);
		} else if (0x01 == cc) {
			entity.setDomain(1);
		} else if (0x02 == cc) {
			entity.setDomain(2);
		}
		return entity;
		}
}
