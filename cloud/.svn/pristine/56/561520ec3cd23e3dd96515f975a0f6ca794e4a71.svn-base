package cn.com.kxcomm.ipmi.service;

import java.io.Serializable;
import java.util.List;

import cn.com.kxcomm.ipmi.entity.AlertServerEntity;

/**
 * 
* 功能描述:告警与服务器关系业务接口
* @author chenl 新增日期：2012-9-20
* @since ipmi_web
 */
public interface AlertServerService extends CommonService<AlertServerEntity> {

	/**
	 * 
	* 方法用途和描述: 根据告警信息id删除
	* @param ids
	* @return
	* @author chenl 新增日期：2012-9-20
	* @since ipmi_web
	 */
	public boolean deleteByAlertID(Serializable[] ids);
	
	/**
	 * 
	* 方法用途和描述: 根据告警信息id查询策略所关联的服务器ID
	* @param fieldName,fieldValue
	* @return
	* @author lizl 新增日期：2012-9-20
	* @since ipmi_web
	 */
	public List<AlertServerEntity> findListByField(String fieldName, int fieldValue);
}
