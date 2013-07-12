package cn.com.kxcomm.ipmitool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.ChassisStatus;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.raw.RawCreator;
import cn.com.kxcomm.ipmitool.raw.SystemRaw;

public class Chassis {
	private static final Logger log = Logger.getLogger(Chassis.class);
	private static Chassis instance = null;
	
	public static Chassis getSingleInstance() {
		if (instance == null) {
			instance = new Chassis();
		}
		return instance;
	}

	private Chassis() {}
	
	/**
	* 
	* 远程开机<br/>
	* 命令<br/>
	* NM 1.5: raw 0x00 0x02 0x01<br/>
	* NM 2.0:一样<br/>
	* IDRAC7:一样<br/>
	* @param ser
	* @author zhangjh 新增日期：2012-8-22
	* @since ipmi_sdk
	 */
	public void remotePowerOn(SerInfo ser){
		SystemRaw c=RawCreator.creatorSystem(ser);
		c.remotePowerOn();
	}
	
	/**
	* 
	* 远程关机
	* 命令<br/>
	* NM 1.5: raw 0x00 0x02 0x00<br/>
	* NM 2.0:一样<br/>
	* IDRAC7:一样<br/>
	* @param ser
	* @author zhangjh 新增日期：2012-8-22
	* @since ipmi_sdk
	 */
	public void remotePowerOff(SerInfo ser){
		SystemRaw c=RawCreator.creatorSystem(ser);
		c.remotePowerOff();
	}
	
	/**
	* 
	* 查看服务器的状态
	* 命令<br/>
	* NM 1.5: raw 0x00 0x01<br/>
	* NM 2.0:一样<br/>
	* IDRAC7:一样<br/>
	* @param ser 
	* @return true:开机 false:关机
	* @author zhangjh 新增日期：2012-8-22
	* @since ipmi_sdk
	 */
	public boolean currentPowerState(SerInfo ser){
		SystemRaw c=RawCreator.creatorSystem(ser);
		return c.currentPowerState();
	}
	
	/**
	 * 
	 * 重启系统 
	 * 命令<br/>
	 * NM 1.5: raw 0x00 0x02 0x03<br/>
	 * NM 2.0:一样<br/>
	 * IDRAC7:一样<br/>
	 * 
	 * @author zhangjh 新增日期：2012-8-22
	 * @since ipmi_sdk
	 */
	public void remoePowerReset(SerInfo ser){
		SystemRaw c=RawCreator.creatorSystem(ser);
		c.remoePowerReset();
	}
	
	/**
	* 
	* 获取底盘状态
	* @param ser
	* @return
	* @author zhangjh 新增日期：2012-8-23
	* @since ipmi_sdk
	 */
	public ChassisStatus chassisStatus(SerInfo ser){
		ChassisStatus entity = null;
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " chassis status";
		log.info("command:" + command);
		Process p;
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			Map<String, String> map = new HashMap<String, String>();
			while (null != (inline = br.readLine())) {
				String[] tmp = StringUtils.split(inline,":");
				map.put(tmp[0].trim(), tmp[1]);
			}
			br.close();
			if(map.size()>0){
				entity = new ChassisStatus();
				entity.setSystemPower(map.get("System Power").equals("true")?true:false);
				entity.setPowerOverload(map.get("Power Overload").equals("true")?true:false);
				entity.setPowerInterlock(map.get("Power Interlock"));
				entity.setMainPowerFault(map.get("Main Power Fault").equals("true")?true:false);
				entity.setPowerControlFault(map.get("Power Control Fault").equals("true")?true:false);
				entity.setPowerRestorePolicy(map.get("Power Restore Policy"));
				entity.setLastPowerEvent(map.get("Last Power Event"));
				entity.setChassisIntrusion(map.get("Chassis Intrusion"));
				entity.setFrontPanelLockout(map.get("Front-Panel Lockout"));
				entity.setDriveFault(map.get("Drive Fault").equals("true")?true:false);
				entity.setCoolingFanFault(map.get("Cooling/Fan Fault").equals("true")?true:false);
				entity.setSleepButtonDisable(map.get("Sleep Button Disable"));
				entity.setDiagButtonDisable(map.get("Diag Button Disable"));
				entity.setResetButtonDisable(map.get("Reset Button Disable"));
				entity.setPowerButtonDisable(map.get("Power Button Disable"));
				entity.setSleepButtonDisabled(map.get("Sleep Button Disabled").equals("true")?true:false);
				entity.setDiagButtonDisabled(map.get("Diag Button Disabled").equals("true")?true:false);
				entity.setResetButtonDisabled(map.get("Reset Button Disabled").equals("true")?true:false);
				entity.setPowerButtonDisabled(map.get("Power Button Disabled").equals("true")?true:false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
