package cn.com.kxcomm.ipmi.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.IpmiSDK;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.ServerDAO;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;
import cn.com.kxcomm.ipmi.memcached.CacheClientImpl;
import cn.com.kxcomm.ipmi.service.ServerService;
import cn.com.kxcomm.ipmi.vo.DetectorEntity;
import cn.com.kxcomm.ipmi.vo.ResultServerListEntity;
import cn.com.kxcomm.ipmi.vo.ServerInfoEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 服务器管理业务逻辑类
 * @author chenliang
 *
 */
@Service("serverService")
public class ServerServiceImpl extends CommonServiceImpl<TbServer> implements ServerService {

	private final static Logger log = Logger.getLogger(ServerServiceImpl.class);
	
	@Autowired(required=true)
	public ServerDAO serverDAO;
	
	@Autowired(required = true)
	private CacheClientImpl cache;
	
	@Override
	public CommonDAO<TbServer> getBindDao() {
		return serverDAO;
	}

	/**
	 * 服务器分页查询
	 * 根据serverName，serverIpIn,groupId查找
	 * params:entity 分页实体类，用于存放条件值
	 * 		  pageInfo 分页对象
	 * return page 分页集合
	 */
	@Override
	public Page<TbServer> findByPage(TbServer entity, Page<TbServer> pageInfo) {
		log.debug("entity:"+entity.toString());
		StringBuffer hql = new StringBuffer(" from TbServer ts where ts.id <> ALL (select serverId from TbBlackName)");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getServerName())){
				hql.append(" and ts.serverName like '%"+entity.getServerName()+"%' ");
			}
			if(!BlankUtil.isBlank(entity.getServerIpIn())){
				hql.append(" and ts.serverIpIn like '%"+entity.getServerIpIn()+"%' ");
			}
		}
		Page<TbServer> pageList = serverDAO.findByPage(pageInfo,hql.toString());
		//设置服务器电源状态，是否开关机
		if(null!=pageList){
			IpmiSDK ipmisdk = IpmiSDK.getInstance(cache);
			List<TbServer> list = pageList.getResult();
			for (int i = 0; i < list.size(); i++) {
				TbServer server = list.get(i);
				//设置服务器连接信息
				SerInfo ser = ipmisdk.setSerInfo(server);
				short powerState = ipmisdk.getCurrentPowerState(ser);
				server.setCangetPowerConsumption(powerState);
			}
		}
		log.debug("result:"+pageList.getResult().toString());
		return pageList;
	}

	/**
	 * 保存服务器与服务器所属集合列表的关系
	 */
	@Override
	public void insertServerBelong(TbServerBelong serverBelong) {
		log.debug("ServerService serverBelong"+serverBelong.toString());
		serverDAO.insertServerBelong(serverBelong);
	}
	
	/**
	 * 修改服务器与服务器所属集合列表的关系
	 */
	@Override
	public void updateServerBelong(TbServerBelong serverBelong) {
		log.debug("update updateServerBelong ,rightIds:"+serverBelong.toString());
		if(!BlankUtil.isBlank(serverBelong)){
			String hql = "delete TbServerBelong tr where tr.id.serverId=? ";
			//删除该之前的服务器与服务器所属集合列表的关系
			serverDAO.executeByHQL(hql,serverBelong.getId().getServerId());
			log.debug("delete tbRightRole by roleId success.insert rightRole.");
			//更新新的关系
			log.debug("ServerService serverBelong"+serverBelong.toString());
			serverDAO.updateServerBelong(serverBelong);
		}else{
			String hql = "delete TbServerBelong tr where tr.id.serverId=? ";
			//权限id为空，删除该角色之前的所有权限
			int result = serverDAO.executeByHQL(hql,serverBelong.getId().getServerId());
			if(result>0){
				log.debug("update TbServerBelong success.");
			}else{
				log.debug("update TbServerBelong fail.");
			}
		}
		
	}
	
	/**
	 * 删除服务器与服务器所属集合列表的关系
	 * params:ids 服务器id集合
	 */
	@Override
	public boolean deleteServerBelong(Long[] ids) {
		log.debug("ServerService serverBelong"+ids.toString());
		int result = 0;
		try {
			String hql = "delete TbServerBelong where id.serverId=?";
			for (Serializable id : ids) {
				result += serverDAO.executeByHQL(hql, id);
			}
			log.debug("result:"+result);
			if(result>0){
				log.debug("batch delete ServerBelong successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deleteServerBelong error."+e);
			return false;
		}
	}

	/**
	 * 删除服务器采集数据
	 */
	@Override
	public boolean deleteHistoryServerInfo(Long[] ids) {
		log.debug("deleteHistoryServerInfo by Ids:"+ids.toString());
		try {
			String hql = "delete TbHistoryServerInfo where tbServer.id = ?";
			int result = 0;
			for (Serializable id : ids) {
				result += serverDAO.executeByHQL(hql, id);
			}
			log.info("result:"+result);
			if(result>0){
				log.info("batch delete HistoryServerInfo successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deleteHistoryServerInfo error."+e);
			return false;
		}
	}
	
	public List<TbServer> getServerIdByIp(String ip){
		List<TbServer> servers=new ArrayList();
	    String hql="from TbServer where idracIp like ? and id not in (select serverId from TbBlackName) and quickServerCode !='自动导入'";
	    servers=serverDAO.find(hql, "%"+ip+"%");
		return servers;
	}
	

	/**
	 * 获取服务器实施数据详细信息
	 */
	@Override
	public ResultServerListEntity getServerRealTimeData(TbServer server) {
		IpmiSDK sdk = IpmiSDK.getInstance(cache);
		//设置服务器连接信息
		SerInfo ser = sdk.setSerInfo(server);
		ResultServerListEntity resultEntityList = new ResultServerListEntity();
		if(sdk.checkSuccessIpmi(ser)){
			//cpu功耗
			List<ServerInfoEntity> cpuPowerList= new ArrayList<ServerInfoEntity>();
			cpuPowerList.add(sdk.getPower(ser, Parameter.domainCpu));
			resultEntityList.setCpu_powerWaste_list(cpuPowerList);
			//系统功耗
			List<ServerInfoEntity> sysPowerList= new ArrayList<ServerInfoEntity>();
			sysPowerList.add(sdk.getPower(ser, Parameter.domainSystem));
			resultEntityList.setSys_powerWaste_list(sysPowerList);
			//内存功耗
			List<ServerInfoEntity> memoryPowerList= new ArrayList<ServerInfoEntity>();
			memoryPowerList.add(sdk.getPower(ser, Parameter.domainMemory));
			resultEntityList.setMemory_powerWaste_list(memoryPowerList);
			//进风口温度
			List<DetectorEntity> airInletTemper = sdk.getSensorInfo(ser);
			resultEntityList.setSys_temper_list(airInletTemper);
			//出风口温度
			List<DetectorEntity> outInletTemper = sdk.getInletTemp(ser);
			resultEntityList.setInletTemp_list(outInletTemper);
			//电池探测器
			List<DetectorEntity> batteryList = sdk.getBatteryInfoList(ser);
			resultEntityList.setBattery_list(batteryList);
			//风扇冗余
			List<DetectorEntity> fanRedundancy= sdk.getFanRedundancy(ser);
			resultEntityList.setFan_redundancy_list(fanRedundancy);
			//风扇探测器列表
			List<DetectorEntity> detectorList = sdk.getFanRPMList(ser);
			resultEntityList.setFan_detector_list(detectorList);
			//cpu探测器
			List<DetectorEntity> cpuDetectorList = sdk.getCPUInfoList(ser);
			resultEntityList.setCpu_state_list(cpuDetectorList);
			//电源的当前读数
			List<DetectorEntity> powerReadingList = sdk.getSystemCapability(ser);
			resultEntityList.setPower_reading_list(powerReadingList);
			
			//电源温度
//			log.info("resultEntityList:"+Json.toJson(resultEntityList));
		}else{
			log.info("host "+server.getIdracIp()+" Can't connect to server");
		}
		return resultEntityList;
	}

	/**
	 * 开机/关机 ，调用接口
	 * @param server 服务器对象
	 * @param stateType 1：开机，2：关机
	 */
	@Override
	public boolean openShutdown(TbServer server, int stateType) {
		IpmiSDK sdk = IpmiSDK.getInstance(cache);
		//设置服务器连接信息
		SerInfo ser = sdk.setSerInfo(server);
		return sdk.remotePowerOnOff(ser, stateType);
	}

	/**
	 * 
	* 方法用途和描述: 删除服务器与告警信息之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	@Override
	public boolean deleteAlertServer(Long[] keyId) {
		log.debug("AlertEntity by Ids:"+keyId);
		try {
			String hql = "delete AlertEntity where serverId = ?";
			int result = 0;
			for (Serializable id : keyId) {
				result += serverDAO.executeByHQL(hql, id);
			}
			log.info("result:"+result);
			if(result>0){
				log.info("batch delete AlertEntity successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deleteAlertServer error."+e);
			return false;
		}
	}

	/**
	 * 
	* 方法用途和描述: 删除服务器与黑名单之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	@Override
	public boolean deleteBlackNameServer(Long[] keyId) {
		log.debug("TbBlackName by Ids:"+keyId);
		try {
			String hql = "delete TbBlackName where serverId = ?";
			int result = 0;
			for (Serializable id : keyId) {
				result += serverDAO.executeByHQL(hql, id);
			}
			log.info("result:"+result);
			if(result>0){
				log.info("batch delete TbBlackName successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deleteBlackNameServer error."+e);
			return false;
		}
	}

	/**
	 * 
	* 方法用途和描述:删除服务器与策略之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	@Override
	public boolean deletePolicyServer(Long[] keyId) {
		log.debug("TbPolicySever by Ids:"+keyId);
		try {
			String hql = "delete TbPolicySever where tbServer.id = ?";
			int result = 0;
			for (Serializable id : keyId) {
				result += serverDAO.executeByHQL(hql, id);
			}
			log.info("result:"+result);
			if(result>0){
				log.info("batch delete TbPolicySever successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deletePolicyServer error."+e);
			return false;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据服务器id，查询服务器与策略之间的关系
	* @return false 服务器关联了数据
	* @return true  服务器没有关联数据，可以删除
	* @author chenl 新增日期：2012-9-7
	* @since ipmi_web
	 */
	@Override
	public boolean findByServerId(Long[] keyId){
		StringBuffer hql = new StringBuffer();
		hql.append("select id,bining_id,bining_server_id from tb_policy_sever where 1=1 ");
		if(keyId.length>0){
			hql.append(" and bining_server_id in (");
			for (int i = 0; i < keyId.length; i++) {
				hql.append("?");
				if(i!=keyId.length-1){
					hql.append(",");
				}
			}
			hql.append(")");
		}
		List list = serverDAO.findByHql(hql.toString(),keyId);
		log.info("list.size:"+list.size());
		if(list.size()>0){  //查出数据则服务器关联了策略
			return false;
		}
		return true;
	}

	@Override
	public void littleBatchSave(List<TbServer> list) {
		Session session=serverDAO.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		if(list.size()>0){
			for(int i = 0 ; i<list.size();i++){
				TbServer  t = list.get(i);
				session.save(t);
				if(i%30==0)
				{
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		}
	}
}
