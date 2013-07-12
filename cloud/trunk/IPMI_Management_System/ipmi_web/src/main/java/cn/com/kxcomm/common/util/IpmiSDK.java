package cn.com.kxcomm.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.memcached.CacheClientImpl;
import cn.com.kxcomm.ipmi.memcached.MemcachedKey;
import cn.com.kxcomm.ipmi.vo.DetectorEntity;
import cn.com.kxcomm.ipmi.vo.ServerInfoEntity;
import cn.com.kxcomm.ipmitool.Chassis;
import cn.com.kxcomm.ipmitool.Power;
import cn.com.kxcomm.ipmitool.Sensor;
import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;

public class IpmiSDK {

	private static final Logger log = Logger.getLogger(IpmiSDK.class);
	private SupportTool supporTool = SupportTool.getSingleInstance();

	private Chassis chassis = Chassis.getSingleInstance();
	private Power power = Power.getSingleInstance();
	private Sensor sensor = Sensor.getSingleInstance();
	private CacheClientImpl cache;
	private GobblerServer server;
	public static IpmiSDK ipmiSDK;
	
	public IpmiSDK(CacheClientImpl cache){
		server = new GobblerServer();
		server.setHost(AppConfig.getInstance().getString("gobbler.host"));
		server.setUser(AppConfig.getInstance().getString("gobbler.user"));
		server.setPasswd(AppConfig.getInstance().getString("gobbler.passwd"));
		this.cache = cache; 
	}
	
	public static IpmiSDK getInstance(CacheClientImpl cache){
		if(null==ipmiSDK){
			ipmiSDK = new IpmiSDK(cache);
		}
		return ipmiSDK;
	}
	
	
	/**
	 * 
	* 方法用途和描述:判断服务器是否可以连接
	* @param ser
	* @return
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public boolean checkSuccessIpmi(SerInfo ser){
		try {
			if(supporTool.checkSucessIpmi(ser)){
				return true;
			}
		} catch (Exception e) {
			log.error("checkSuccessIpmi :"+e);
		}
		return false;
	}
	
	/**
	 * 
	* 方法用途和描述: 设置serinfo对象的值
	* @param server
	* @return
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public SerInfo setSerInfo(TbServer server){
		SerInfo ser = new SerInfo();
		ser.setHost(server.getIdracIp());
		ser.setPasswd(server.getIdracPassword());
		ser.setUser(server.getIdracUser());
		return ser;
	}
	
	
	/**
	 * 
	* 方法用途和描述: 获取系统，CPU，内存功耗
	* @param server 服务器信息
	* @param domain 域
	* @return
	* @author chenl 新增日期：2012-8-20
	* @since ipmi_web
	 */
	public ServerInfoEntity getPower(SerInfo ser,int domain){
		ServerInfoEntity info = new ServerInfoEntity();
		try {
			if(supporTool.checkSucessIpmi(ser)){
				PowerReading reading=null;
				if(Parameter.domainCpu == domain){  //cpu功耗
					reading = (PowerReading) cache.get(ser.getHost()+MemcachedKey.IPMI_CPUPOWER);
					if(BlankUtil.isBlank(reading)){
						reading = power.getPlatformPowerReading(ser,Parameter.domainCpu);
						cache.set(ser.getHost()+MemcachedKey.IPMI_CPUPOWER, reading);
					}
				}else if(Parameter.domainSystem == domain){ //系统功耗
					reading = (PowerReading) cache.get(ser.getHost()+MemcachedKey.IPMI_SYSTEMPOWER);
					if(BlankUtil.isBlank(reading)){
						reading  = power.getPlatformPowerReading(ser, Parameter.domainSystem);
						cache.set(ser.getHost()+MemcachedKey.IPMI_SYSTEMPOWER, reading);
					}
				}else if(Parameter.domainMemory == domain){ //内存功耗
					reading = (PowerReading) cache.get(ser.getHost()+MemcachedKey.IPMI_MEMORYPOWER);
					if(BlankUtil.isBlank(reading)){
						reading  = power.getPlatformPowerReading(ser, Parameter.domainMemory);
						cache.set(ser.getHost()+MemcachedKey.IPMI_MEMORYPOWER, reading);
					}
				}
				if(!BlankUtil.isBlank(reading)){
					info.setNow_PowerWaste(reading.getCurrent());
					info.setMin_PowerWaste(reading.getMin());
					info.setMax_PowerWaste(reading.getMax());
					info.setAvg_PowerWaste(reading.getAverage());
				}else{
					log.info("powerReading is null.");
				}
			}else{
				log.info("getPower host "+ser.getHost()+" check network error.");
			}
		} catch (Exception e) {
			log.info("getPower error.",e);
		}
		return info;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取进风口温度
	* @param server 服务器信息
	* @param domain 域
	* @return
	* @author chenl 新增日期：2012-8-20
	* @since ipmi_web
	* 
	 */
	public List<DetectorEntity> getSensorInfo(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			DetectorEntity entity = new DetectorEntity();
			List<GlobalEntity> senList = (List<GlobalEntity>) cache.get(ser.getHost()+MemcachedKey.IPMI_EXHAUSTTEMP);
			if(BlankUtil.isBlank(senList) || senList.size()<=0){
				senList = sensor.getSensorInfo(ser, Parameter.ExhaustTemp,server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_EXHAUSTTEMP, senList);
			}
			log.info("senList:"+senList);
			if(!BlankUtil.isBlank(senList) && senList.size()>0){
				serverInfoList.add(invoke(entity, senList));
			}else{
				log.info("getSensorInfo is null.");
			}
		} catch (Exception e) {
			log.error("getAirinletThermal is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取出风口温度
	* @param server 服务器信息
	* @param domain 域
	* @return
	* @author chenl 新增日期：2012-8-20
	* @since ipmi_web
	* 
	 */
	public List<DetectorEntity> getInletTemp(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			DetectorEntity entity = new DetectorEntity();
			List<GlobalEntity> senList = (List<GlobalEntity>) cache.get(ser.getHost()+MemcachedKey.IPMI_INLETTTEMP);
			if(BlankUtil.isBlank(senList) || senList.size()<=0){
				senList = sensor.getSensorInfo(ser, Parameter.InletTemp,server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_INLETTTEMP, senList);
			}
			log.debug("getInletTemp senList:"+senList);
			if(!BlankUtil.isBlank(senList) && senList.size()>0){
				serverInfoList.add(invoke(entity, senList));
			}else{
				log.info("getSensorInfo is null.");
			}
		} catch (Exception e) {
			log.error("getAirinletThermal is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取风扇冗余
	* @param server 服务器信息
	* @param domain 域
	* @return
	* @author chenl 新增日期：2012-8-20
	* @since ipmi_web
	* 
	 */
	public List<DetectorEntity> getFanRedundancy(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			DetectorEntity entity = new DetectorEntity();
			List<GlobalEntity> senList = (List<GlobalEntity>) cache.get(ser.getHost()+MemcachedKey.IPMI_FANREDUNDANCY);
			if(BlankUtil.isBlank(senList) || senList.size()<=0){
				senList = sensor.getSensorInfo(ser, Parameter.FanRedundancy,server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_FANREDUNDANCY, senList);
			}
			if(!BlankUtil.isBlank(senList) && senList.size()>0){
				serverInfoList.add(invoke(entity,senList));
			}
		} catch (Exception e) {
			log.error("getAirinletThermal is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取风扇探测器列表
	* @return
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public List<DetectorEntity> getFanRPMList(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			List<List<GlobalEntity>> sensorList = (List<List<GlobalEntity>>) cache.get(ser.getHost()+MemcachedKey.IPMI_FANRPM);
			if(BlankUtil.isBlank(sensorList) || sensorList.size()<=0){
				sensorList = sensor.getFanRPMInfo(ser, server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_FANRPM, sensorList);
			}
			if(!BlankUtil.isBlank(sensorList) && sensorList.size()>0){
				for (int i = 0; i < sensorList.size(); i++) {
					DetectorEntity entity = new DetectorEntity();
					List<GlobalEntity> list = sensorList.get(i);
					serverInfoList.add(invoke(entity, list));
				}
			}else{
				log.info("getFanRPMList is null.");
			}
			log.debug("serverInfoList.size():"+serverInfoList.size());
		} catch (Exception e) {
			log.error("getSdrList is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取CPU探测器列表
	* @return
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public List<DetectorEntity> getCPUInfoList(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			List<List<GlobalEntity>> sensorList = (List<List<GlobalEntity>>) cache.get(ser.getHost()+MemcachedKey.IPMI_CPU);
			if(BlankUtil.isBlank(sensorList) || sensorList.size()<=0){
				sensorList = sensor.getCPUInfo(ser, server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_CPU, sensorList);
			}
			if(!BlankUtil.isBlank(sensorList) && sensorList.size()>0){
				for (int i = 0; i < sensorList.size(); i++) {
					DetectorEntity entity = new DetectorEntity();
					List<GlobalEntity> list = sensorList.get(i);
					serverInfoList.add(invoke(entity, list));
				}
			}else{
				log.info("getCPUInfoList is null.");
			}
			log.debug("serverInfoList.size():"+serverInfoList.size());
		} catch (Exception e) {
			log.error("getSdrList is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取电池探测器列表
	* @return
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public List<DetectorEntity> getBatteryInfoList(SerInfo ser){
		List<DetectorEntity> serverInfoList = new ArrayList<DetectorEntity>();
		try {
			List<List<GlobalEntity>> sensorList = (List<List<GlobalEntity>>) cache.get(ser.getHost()+MemcachedKey.IPMI_BATTERY);
			if(BlankUtil.isBlank(sensorList) || sensorList.size()<=0){
				sensorList = sensor.getBatteryInfo(ser, server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_BATTERY, sensorList);
			}
			if(!BlankUtil.isBlank(sensorList) && sensorList.size()>0){
				for (int i = 0; i < sensorList.size(); i++) {
					DetectorEntity entity = new DetectorEntity();
					List<GlobalEntity> list = sensorList.get(i);
					serverInfoList.add(invoke(entity, list));
				}
			}else{
				log.info("getBatteryInfoList is null.");
			}
			log.debug("serverInfoList.size():"+serverInfoList.size());
		} catch (Exception e) {
			log.error("getSdrList is error.",e);
		}
		return serverInfoList;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取电源温度的阀值
	* @return
	* @author chenl 新增日期：2012-8-28
	* @since ipmi_web
	 */
	public List<DetectorEntity> getSystemCapability(SerInfo ser){
		List<DetectorEntity> detectorList = new ArrayList<DetectorEntity>();
		try {
			List<GlobalEntity> powerList = (List<GlobalEntity>) cache.get(ser.getHost()+MemcachedKey.IPMI_PWRCONSUMPTION);
			if(BlankUtil.isBlank(powerList) || powerList.size()<=0){
				powerList = sensor.getSensorInfo(ser, Parameter.PwrConsumption,server);
				cache.set(ser.getHost()+MemcachedKey.IPMI_PWRCONSUMPTION, powerList);
			}
			if(!BlankUtil.isBlank(powerList) && powerList.size()>0){
				DetectorEntity entity = new DetectorEntity();
				detectorList.add(invoke(entity, powerList));
			}else{
				log.info("getSystemCapability is null.");
			}
		} catch (Exception e) {
			log.error("getSystemCapability is error.",e);
		}
		return detectorList;
	}
	
	/**
	 * 
	* 方法用途和描述: 开机，关机
	* @param server
	* @author chenl 新增日期：2012-8-23
	* @since ipmi_web
	 */
	public boolean remotePowerOnOff(SerInfo ser,int stateType){
		try {
			if(checkSuccessIpmi(ser)){
				if(1==stateType){
					chassis.remotePowerOn(ser);			
				}else if(2==stateType){
					chassis.remotePowerOff(ser);
				}else{
					log.info("remotePowerOnOff stateType is error.");
					return false;
				}
			}else{
				log.info("remotePowerOnoff host "+ser.getHost()+" check network error.");
			}
			return true;
		} catch (Exception e) {
			log.info("remotePowerOnOff is error.",e);
			return false;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 获取服务器的当前状态
	* @return 0无法检测 
	* @return 1为关机
	* @return 2为开机
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public short getCurrentPowerState(SerInfo ser){
		short result = 0; //1为关机，2为开机
		try {
			boolean flat = false;
			if(cache.get(ser.getHost()+MemcachedKey.IPMI_POWER_STATUS)==null){
				flat = chassis.currentPowerState(ser);
				cache.set(ser.getHost()+MemcachedKey.IPMI_POWER_STATUS, flat);
			}
			flat = (Boolean) cache.get(ser.getHost()+MemcachedKey.IPMI_POWER_STATUS);
			result = (short) (flat?1:2);
			log.info("chassis flat："+result);
		} catch (Exception e) {
			log.info("remotePowerOnOff is error.",e);
			result = 0;
		}
		return result;
	}
	
	/**
	 * 
	* 方法用途和描述: 通过反射给DetectorEntity实体对象赋值
	* @param entity
	* @param list
	* @return
	* @author chenl 新增日期：2012-8-28
	* @since ipmi_web
	 */
	private DetectorEntity invoke(DetectorEntity entity,List<GlobalEntity> list){
		try {
			Field[] field = DetectorEntity.class.getFields();
			int len = field.length;
			Method[] methods= new Method[len];
			for (int i = 0; i < len; i++) {
				String ff = field[i].getName().substring(0,1).toUpperCase()+field[i].getName().substring(1);
					methods[i] = DetectorEntity.class.getDeclaredMethod("set"+ff,String.class);
			}
			for (int i = 0; i < methods.length; i++) {
				Method me = methods[i];
				String str = me.getName().substring(3).trim().toLowerCase();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getName().trim().replace(" ", "").replace("-", "").replace(".", "").toLowerCase().contains(str)){
						me.invoke(entity,list.get(j).getValue());
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public static void main(String[] args) {
		ApplicationContext springContext =  new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    	CacheClientImpl ca = (CacheClientImpl)springContext.getBean("cache");
		IpmiSDK sdk = IpmiSDK.getInstance(ca);
		TbServer server = new TbServer();
		server.setIdracIp("192.168.1.20");
		server.setIdracPassword("123456");
		server.setIdracUser("test");
		SerInfo ser = sdk.setSerInfo(server);
		List<DetectorEntity> info = sdk.getSystemCapability(ser);
		log.info("list:"+Json.toJson(info));
	}
}
