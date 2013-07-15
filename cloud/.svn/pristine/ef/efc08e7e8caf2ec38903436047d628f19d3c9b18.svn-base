package cn.com.kxcomm.ipmitool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerCommand;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 
 * 传感器
 * 
 * @author zhangjh 新增日期：2012-8-24
 * @since ipmi_sdk
 */
public class Sensor {
	private static final Logger log = Logger.getLogger(Sensor.class);
	private static Sensor instance = null;
	private Sdr sdr = Sdr.getSingleInstance();
	public static Sensor getSingleInstance() {
		if (instance == null) {
			instance = new Sensor();
			
		}
		return instance;
	}

	private Sensor() {
	}
	
	/**
	 * 
	 * 获取传感器信息
	 * 
	 * @param ser
	 * @param name 可以从sdr获取传感器列表得到
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public List<GlobalEntity> getSensorInfo(SerInfo ser,String name,GobblerServer gobblerServer){
		String command = "ipmitool -H " + ser.getHost() + " -U " + ser.getUser() + " -P " + ser.getPasswd() + " sensor get '" + name+"'";
		log.info("command:" + command);
		List<GlobalEntity>  list = new ArrayList<GlobalEntity>();
		try {
			GobblerCommand exe = new GobblerCommand(gobblerServer.getHost(), gobblerServer.getUser(),gobblerServer.getPasswd());
			String rs = exe.executeCommand(command);
			String[] aa= StringUtils.split(rs,"\n");
			for(String cc : aa){
				String[] tmp = StringUtils.split(cc,":");
				if(tmp.length>1){
					GlobalEntity dd = new GlobalEntity(StringUtils.trim(tmp[0]), StringUtils.trim(tmp[1]));
					list.add(dd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 
	 * 获取传感器列表和其详情
	 * 注意: 如果获取所有传感器信息会花费比较长时间,不建议实时查询
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
	 * @return List<List>
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	@SuppressWarnings("rawtypes")
	public List<List> getSensorListInfo(SerInfo ser,String type,GobblerServer gobblerServer){
		List<List> list = new ArrayList<List>();
		List<String> ll = sdr.getSdrList(ser,type);
		for(int i=0;i<ll.size();i++){
			String name = ll.get(i);
			List<GlobalEntity> cc = getSensorInfo(ser, name,gobblerServer);
			if(cc.size()>0){
				list.add(cc);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 返回风扇探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	private List<String> getFanRPM(SerInfo ser){
		List<String> list = new ArrayList<String>();
		List<String> ll = sdr.getSdrList(ser, "full");
		for(String str:ll) {
			if(StringUtils.endsWith(str, "RPM")){
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 
	 * 返回CPU探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	private List<String> getCpu(SerInfo ser){
		List<String> list = new ArrayList<String>();
		List<String> ll = sdr.getSdrList(ser, "fru");
		for(String str:ll) {
			if(StringUtils.startsWith(str, "CPU") && str.length()<=4){
				list.add(str);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 返回电池探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	private List<String> getBattery(SerInfo ser){
		List<String> list = new ArrayList<String>();
		List<String> ll = sdr.getSdrList(ser, "compact");
		for(String str:ll) {
			if(StringUtils.endsWith(str, "Battery")){
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 
	 * 返回风扇探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public List<List<GlobalEntity>> getFanRPMInfo(SerInfo ser,GobblerServer gobblerServer){
		List<String> list = getFanRPM(ser);
		List<List<GlobalEntity>> ll  = new ArrayList<List<GlobalEntity>>();
		for(String str:list){
			List<GlobalEntity> cc = getSensorInfo(ser, str,gobblerServer);
			ll.add(cc);
		}
		return ll;
	}
	
	/**
	 * 
	 * 返回CPU探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public List<List<GlobalEntity>> getCPUInfo(SerInfo ser,GobblerServer gobblerServer){
		List<String> list = getCpu(ser);
		List<List<GlobalEntity>> ll  = new ArrayList<List<GlobalEntity>>();
		for(String str:list){
			List<GlobalEntity> cc = getSensorInfo(ser, str,gobblerServer);
			ll.add(cc);
		}
		return ll;
	}
	

	/**
	 * 
	 * 返回电池探测器列表
	 * 
	 * @param ser
	 * @return
	 * @author zhangjh 新增日期：2012-8-24
	 * @since ipmi_sdk
	 */
	public List<List<GlobalEntity>> getBatteryInfo(SerInfo ser,GobblerServer gobblerServer){
		List<String> list = getBattery(ser);
		List<List<GlobalEntity>> ll  = new ArrayList<List<GlobalEntity>>();
		for(String str:list){
			List<GlobalEntity> cc = getSensorInfo(ser, str,gobblerServer);
			ll.add(cc);
		}
		return ll;
	}
	
	/**
	 * 
	 * 获取入风/出风口温度
	 * 
	 * @param ser
	 * @param gobblerServer
	 * @param tempType Parameter.InletTemp 或 Parameter.ExhaustTemp
	 * @return
	 * @author zhangjh 新增日期：2012-9-5
	 * @since ipmi_task
	 */
	public Integer getTempSensorReading(SerInfo ser,GobblerServer gobblerServer,String tempType){
		if(!StringUtils.equals(tempType, Parameter.InletTemp)&&!StringUtils.equals(tempType, Parameter.ExhaustTemp))
			return null;
		int reading = 0;
		List<GlobalEntity> temp = getSensorInfo(ser, tempType,gobblerServer);
		for(GlobalEntity g:temp){
			if(StringUtils.equals("Sensor Reading", g.getName())){
				String value =StringUtils.substring(g.getValue(), 0, g.getValue().indexOf('('));
				reading = Integer.parseInt(StringUtils.trim(value));
				break;
			}
		}
		return reading;
	}
}
