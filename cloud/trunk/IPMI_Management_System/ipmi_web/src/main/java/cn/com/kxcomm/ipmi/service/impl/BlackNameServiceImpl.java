package cn.com.kxcomm.ipmi.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.IpmiSDK;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.BlackNameDAO;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.BlackNameService;
import cn.com.kxcomm.ipmi.vo.BlackNameEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;

/**
 * 黑名单业务逻辑处理类
 * @author chenliang
 *
 */
@Service("blackNameService")
public class BlackNameServiceImpl extends CommonServiceImpl<TbBlackName> implements
		BlackNameService {
	
	private static Logger log = Logger.getLogger(BlackNameServiceImpl.class);
	
	@Autowired(required=true)
	public BlackNameDAO blackNameDAO;
	
	@Override
	public CommonDAO<TbBlackName> getBindDao() {
		return blackNameDAO;
	}

	/**
	 * 分页查询
	 * params:entity 实体类
	 * 		  pageInfo 分页对象
	 * return:page 分页对象
	 */
	@Override
	public Page<BlackNameEntity> queryPage(BlackNameEntity entity,
			Page<TbBlackName> pageInfo) {
		Page<BlackNameEntity> pagelist = new Page<BlackNameEntity>();
		//查找所有lists
		StringBuffer hql = new StringBuffer();
		hql.append(" select ts.id as serverId,ts.group_id,ts.server_name,ts.operate_system,ts.idrac_ip,ts.idrac_user,ts.idrac_password,ts.server_ip_in,ts.server_ip_out,ts.server_description, " +
				" ts.canget_cpu_consumption,ts.canget_power_consumption,ts.canget_memory_consumption,ts.canget_wind_temperature,ts.cpu_consumption_policy,ts.memory_consumption_policy, " +
				" ts.power_consumption_policy,tbn.id as blackNameId from tb_black_name tbn , tb_server ts where ts.id=tbn.server_id ");
		// 拼接条件
		if(!BlankUtil.isBlank(entity) && !BlankUtil.isBlank(entity.getServerEntity())){
			if(!BlankUtil.isBlank(entity.getServerEntity().getServerIpIn())){
				hql.append(" and ts.server_ip_in = ?");
			}
			if(!BlankUtil.isBlank(entity.getServerEntity().getServerName())){
				hql.append(" and ts.server_name like '%"+entity.getServerEntity().getServerName()+"%'");
			}
		}
		//查找总记录数sql
		String counthql = "select count(*) as count from ("+hql.toString()+") t ";
		log.debug("[counthql]"+counthql);
		int count = blackNameDAO.findCount(counthql,entity.getServerEntity().getServerIpIn());
		log.debug("count:"+count);
		log.debug("[hql]"+hql);
		List list = blackNameDAO.findByPage(hql.toString(),pageInfo,entity.getServerEntity().getServerIpIn());
		log.debug("list:"+list.toString());
		List<BlackNameEntity> blackNameList = new ArrayList<BlackNameEntity>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			TbServer server = new TbServer();
			server.setId(parserLong((BigInteger) obj[0]));
			server.setGroupId(parserLong((BigInteger) obj[1]));
			server.setServerName(BlankUtil.isNull(obj[2]));
			server.setOperateSystem(BlankUtil.isNull(obj[3]));
			server.setIdracIp(BlankUtil.isNull(obj[4]));
			server.setIdracUser(BlankUtil.isNull(obj[5]));
			server.setIdracPassword(BlankUtil.isNull(obj[6]));
			server.setServerIpIn(BlankUtil.isNull(obj[7]));
			server.setServerIpOut(BlankUtil.isNull(obj[8]));
			server.setServerDescription(BlankUtil.isNull(obj[9]));
			server.setCangetCpuConsumption(parserShort((Byte) obj[10]));
			server.setCangetPowerConsumption(parserShort((Byte) obj[11]));
			server.setCangetMemoryConsumption(parserShort((Byte) obj[12]));
			server.setCangetWindTemperature(parserShort((Byte) obj[13]));
			server.setCpuConsumptionPolicy(parserShort((Byte) obj[14]));
			server.setMemoryConsumptionPolicy(parserShort((Byte)obj[15]));
			server.setPowerConsumptionPolicy(parserShort((Byte)obj[16]));
			BlackNameEntity blackNameEntity = new BlackNameEntity();
			blackNameEntity.setId(parserLong((BigInteger) obj[17]));
			blackNameEntity.setServiceId(server.getId());
			blackNameEntity.setServerEntity(server);
			blackNameList.add(blackNameEntity);
		}
		pagelist.setTotalCount(count);
		pagelist.setResult(blackNameList);
		log.debug("[pagelist]"+pagelist.toString());
		return pagelist;
	}
	
	/**
	 * 
	* 方法用途和描述:BigInteger类型转为Long类型
	* @param params
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	private Long parserLong(BigInteger params){
		Long result = 0L;
		if(!BlankUtil.isBlank(params)){
			result = Long.parseLong(params.toString());
		}
		return result;
	}
	
	/**
	 * 
	* 方法用途和描述:TinyInt类型转为short类型
	* @param params
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	private Short parserShort(Byte params){
		Short result = 0;
		if(!BlankUtil.isBlank(params)){
			result = params.shortValue();
		}
		return result;
	}

	/**
	 * 
	* 方法用途和描述:重新检测
	* @param params
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	@Override
	public boolean toDetection(TbServer server,Long id) {
		boolean flat = false;
		IpmiSDK sdk = IpmiSDK.getInstance(null);
		SerInfo ser = sdk.setSerInfo(server);
		//重新检测机制
		for (int i = 0; i < 2; i++) {
			if(sdk.checkSuccessIpmi(ser)){
				log.info("检测成功！");
				flat = true;
				break;
			}else{
				flat = false;
				log.info("第"+(i+1)+"次重新检测。");
			}
		}
		//检测成功，删除数据库黑名单的数据。
		if(flat){
			String hql = "delete TbBlackName where id = ? ";
			int result = blackNameDAO.executeByHQL(hql, id);
			log.debug("delete tbBlackName result:"+result);
		}
		//调用sdk数据采集的方法
		return flat;
	}
	
}
