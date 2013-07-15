package cn.com.kxcomm.ipmi.service;

import java.util.List;

import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;

/**
 * 策略分配业务处理逻辑接口
 * @author chenliang
 *
 */
public interface PolicyServerService extends CommonService<TbPolicySever> {

	public void littleBatchSave(List<TbPolicySever> list, String policyid);

	/**
	 * 
	* 方法用途和描述: 根据策略id查找管理服务器的服务器策略id
	* @param id
	* @return
	* @author chenl 新增日期：2012-8-22
	* @since ipmi_web
	 */
	public List<TbPolicySever> findByPolicyId(Long id);
	public Integer getFreePolicyId(TbServer server);
	public TbPolicySever findByPolicyIdandServerId(Long pid,Long sid);
}
