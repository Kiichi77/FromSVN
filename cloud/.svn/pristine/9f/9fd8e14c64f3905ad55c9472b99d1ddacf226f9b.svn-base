package cn.com.kxcomm.ipmi.service;

import java.util.List;

import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;
import cn.com.kxcomm.ipmi.vo.ResultServerListEntity;

/**
 * 
 * 功能描述:服务器业务处理逻辑接口
 * @author chenl 新增日期：2012-8-10
 * @since ipmi_web
 */
public interface ServerService extends CommonService<TbServer> {

	/**
	 * 
	 * 方法用途和描述: 插入服务器与服务器所属集合表关系
	 * @param serverBelong
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	public void insertServerBelong(TbServerBelong serverBelong);

	/**
	 * 修改服务器与服务器所属集合列表的关系
	 */
	public void updateServerBelong(TbServerBelong serverBelong) ;

	public List getServerIdByIp(String ip);

	/**
	 * 
	 * 方法用途和描述: 获取服务器实施数据详细信息
	 * @param server
	 * @return
	 * @author chenl 新增日期：2012-8-17
	 * @since ipmi_web
	 */
	public ResultServerListEntity getServerRealTimeData(TbServer server);
	
	/**
	 * 
	 * 方法用途和描述: 开机/关机 ，调用接口
	 * @param server 服务器对象
	 * @param stateType 1：开机，2：关机
	 * @return
	 * @author chenl 新增日期：2012-8-23
	 * @since ipmi_web
	 */
	public boolean openShutdown(TbServer server,int stateType);
	
	/**
	 * 删除服务器与服务器所属集合列表的关系
	 *  params:ids 服务器id集合
	 */
	public boolean deleteServerBelong(Long[] ids);
	
	/**
	 * 
	 * 方法用途和描述: 删除服务器采集数据表的数据
	 * @param ids
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	public boolean deleteHistoryServerInfo(Long[] ids);


	/**
	 * 
	* 方法用途和描述: 删除服务器与告警信息之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	public boolean deleteAlertServer(Long[] keyId);

	/**
	 * 
	* 方法用途和描述:删除服务器与黑名单之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	public boolean deleteBlackNameServer(Long[] keyId);

	/**
	 * 
	* 方法用途和描述: 删除服务器与策略之间的关系
	* @param keyId
	* @author chenl 新增日期：2012-9-4
	* @since ipmi_web
	 */
	public boolean deletePolicyServer(Long[] keyId);
	
	/**
	 * 
	* 方法用途和描述: 根据服务器id查找策略id
	* @param keyId
	* @return
	* @author chenl 新增日期：2012-9-7
	* @since ipmi_web
	 */
	public boolean findByServerId(Long[] keyId);
	public void littleBatchSave(List<TbServer> a);
}
