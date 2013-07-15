package cn.com.kxcomm.task.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.memcached.CacheClientImpl;
import cn.com.kxcomm.ipmi.memcached.MemcachedKey;
import cn.com.kxcomm.ipmitool.Chassis;
import cn.com.kxcomm.ipmitool.Power;
import cn.com.kxcomm.ipmitool.Sensor;
import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.GlobalEntity;
import cn.com.kxcomm.ipmitool.entity.PowerReading;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.GobblerServer;
import cn.com.kxcomm.ipmitool.util.Parameter;
import cn.com.kxcomm.task.dao.TbBlackNameDAO;
import cn.com.kxcomm.task.dao.TbHistoryServerInfoDAO;
import cn.com.kxcomm.task.dao.TbServerDAO;
import cn.com.kxcomm.task.util.Config;

/**
 * 
 * 采集数据类
 * <p>
 * 1.获取服务器列表(除黑名单外)<br/>
 * 2.检测服务器是否能到达,不能则加入黑名单<br/>
 * 3.采集系统功耗,CPU功耗,内存功耗,进风口温度<br/>
 * 4.记录服务器摘要页面需要的数据到缓存中.
 * </p>
 * @author zhangjh 新增日期：2012-8-29
 * @since ipmi_task
 */
@Service("collectDataService")
public class CollectDataService {
	@Autowired(required = true)
	private TbServerDAO tbServerDAO;
	@Autowired(required = true)
	private TbBlackNameDAO tbBlackNameDAO;
	@Autowired(required = true)
	private TbHistoryServerInfoDAO tbHistoryServerInfoDAO;
	@Autowired(required = true)
	private CacheClientImpl cache;

	private Power power = Power.getSingleInstance();
	private Sensor sensor = Sensor.getSingleInstance();
	private Chassis chassis = Chassis.getSingleInstance();
	private SupportTool supportTool = SupportTool.getSingleInstance();
	private static final Logger logger = Logger.getLogger(CollectDataService.class);  


	/**
	 * 
	 * 采集数据
	 * 
	 * @author zhangjh 新增日期：2012-8-29
	 * @since ipmi_task
	 */
	@SuppressWarnings("unchecked")
	public void collectMachineInfo (){
		List<TbServer> serverList = tbServerDAO.queryNormalServer();
		StringBuilder msg = new StringBuilder();
		CompositeConfiguration conf = Config.getConfig();

		for(TbServer server :serverList){
			SerInfo ser = new SerInfo();
			ser.setHost(server.getIdracIp());
			ser.setUser(server.getIdracUser());
			ser.setPasswd(server.getIdracPassword());
			GobblerServer gobblerServer = new GobblerServer(conf.getString("gobbler.host"),conf.getString("gobbler.user"),conf.getString("gobbler.passwd"));
			msg.append("server->"+ser.getHost()+"\t");
			//黑名单判断
			if(!checkBlackHost(ser, server.getId())){
				msg.append("check Ipmi->false.End!");
				continue;
			}
			TbHistoryServerInfo tbHistoryServerInfo=new TbHistoryServerInfo();
			tbHistoryServerInfo.setTbServer(server);
			tbHistoryServerInfo.setCreateTime(new Date());
			//获取服务器的CPU的当前功耗
			PowerReading powerReading = power.getPlatformPowerReading(ser,Parameter.domainCpu);
			if(powerReading!=null){
				tbHistoryServerInfo.setCpuPower(Short.parseShort(powerReading.getCurrent().toString()));
				cache.set(ser.getHost()+MemcachedKey.IPMI_CPUPOWER, powerReading);
				msg.append("CpuPowerReading->"+powerReading.getCurrent()+"\t");
			}
			
			//获取服务器的memory的当前功耗
			powerReading = power.getPlatformPowerReading(ser, Parameter.domainMemory);
			if(powerReading!=null){
				tbHistoryServerInfo.setMemoryPower(Short.parseShort(powerReading.getCurrent().toString()));
				cache.set(ser.getHost()+MemcachedKey.IPMI_MEMORYPOWER, powerReading);
				msg.append("MemoryPowerReading->"+powerReading.getCurrent()+"\t");
			}
			
			//获取服务器的system的当前功耗
			powerReading = power.getPlatformPowerReading(ser, Parameter.domainSystem);
			if(powerReading!=null){
				tbHistoryServerInfo.setSystemPower(Short.parseShort(powerReading.getCurrent().toString()));
				cache.set(ser.getHost()+MemcachedKey.IPMI_SYSTEMPOWER, powerReading);
				msg.append("SystemPowerReading->"+powerReading.getCurrent()+"\t");
			}
			
			//获取服务器的进风口温度的当前功耗
			Integer inletTemp = sensor.getTempSensorReading(ser,gobblerServer,Parameter.InletTemp);
			if(inletTemp!=null){
				int a = inletTemp;
				tbHistoryServerInfo.setExhaustTemp((short)a);
				msg.append("inletTemp->"+inletTemp+"\t");
			}
			
			tbHistoryServerInfoDAO.save(tbHistoryServerInfo);
			logger.info("采集数据:"+msg.toString());

			List<GlobalEntity>  aa = sensor.getSensorInfo(ser, Parameter.ExhaustTemp,gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_EXHAUSTTEMP, aa);
			aa = sensor.getSensorInfo(ser, Parameter.InletTemp,gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_INLETTTEMP, aa);
			List<List<GlobalEntity>> bb = sensor.getBatteryInfo(ser, gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_BATTERY, bb);
			List<GlobalEntity> cc = sensor.getSensorInfo(ser, Parameter.FanRedundancy,gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_FANREDUNDANCY, cc);
			List<List<GlobalEntity>> dd = sensor.getFanRPMInfo(ser, gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_FANRPM, dd);
			dd = sensor.getCPUInfo(ser, gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_CPU, dd);
			aa = sensor.getSensorInfo(ser, Parameter.PwrConsumption,gobblerServer);
			cache.set(ser.getHost()+MemcachedKey.IPMI_PWRCONSUMPTION, aa);
			boolean isPower = chassis.currentPowerState(ser);
			cache.set(ser.getHost()+MemcachedKey.IPMI_POWER_STATUS, isPower);
		}
	}

	/**
	 * 
	 * 判断网络是否通与是否支持IPMI，不支持则写入黑名单
	 * 
	 * @param ser
	 * @param serId
	 * @return
	 * @author zhangjh 新增日期：2012-8-29
	 * @since ipmi_task
	 */
	private boolean checkBlackHost(SerInfo ser,long serId){
		if(supportTool.checkSucessIpmi(ser))
			return true;
		TbBlackName tbBlackName = new TbBlackName();
		tbBlackName.setServerId(serId);
		tbBlackNameDAO.save(tbBlackName);
		cache.delete(ser.getHost()+MemcachedKey.IPMI_POWER_STATUS);
		return false;
	}
}
