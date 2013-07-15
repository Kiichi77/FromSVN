package cn.com.kxcomm.ipmi.service;

import java.util.List;

import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.vo.PolicyServerEntity;

/**
 * 策略业务处理逻辑接口
 * @author chenliang
 *
 */
public interface PolicyService extends CommonService<TbPolicy> {

	/**
	 * 
	* 方法用途和描述: 保存设置策略与服务器的关系
	* @param policyServerEntity
	* @return
	* @author chenl 新增日期：2012-8-9
	* @since ipmi_web
	 */
	public boolean saveOrUpdateSetPolicy(PolicyServerEntity policyServerEntity);
	
	/**
	 * 
	* 方法用途和描述: 修改策略，调用张建华接口
	* @param policyServerList
	* @return
	* @author chenl 新增日期：2012-8-22
	* @since ipmi_web
	 */
	public boolean updateSetPolicy(List<TbPolicySever> policyServerList,TbPolicy tbpolicy);

}
