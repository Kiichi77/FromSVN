package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.kxcomm.woyun.vo.SecurityGroupVO;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;

/**
 * 
* 功能描述: 安全组
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
public interface SecurityGroupService extends CommonService<SecurityGroupVO> {

	//安全组列表
	public List<SecurityGroupVO> listSecurityGroup(Access access) throws Exception;

	//根据实例id查询安全组信息
	public List<SecurityGroupVO> showSecurityGroupByServerId(Access access,
			String serverId) throws Exception;
	
	//创建安全组
	public boolean createSecurityGroup(Access access,SecurityGroupVO securityGroupVo) throws Exception;
	
	//删除安全组
	public boolean deleteSecurityGroup(Access access,Integer id) throws Exception;
	
	//根据id获取某个安全组
	public SecurityGroupVO showSecurityGroup(Access access,Integer id) throws Exception;
	
	//创建安全组规则
	public void createSecurityGroupRule(Access access ,SecurityGroupRuleForCreate securityGroupRuleForCreate) throws Exception;
	
	//删除安全组规则
	public void deleteSecurityGroupRule(Access access,Integer id) throws Exception;
}
