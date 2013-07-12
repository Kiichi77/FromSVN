package cn.com.woyun.nova.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.NovaClient;
import cn.com.woyun.nova.api.extensions.SecurityGroupsExtension;
import cn.com.woyun.nova.model.SecurityGroup;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;
import cn.com.woyun.nova.model.SecurityGroup.Rule;
import cn.com.woyun.nova.model.SecurityGroups;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
* 功能描述:安全组管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class SecurityGroupApi {

	public static SecurityGroupApi securityGroupApi;
	
	private SecurityGroupApi(){
	}
	
	public static SecurityGroupApi getInstance(){
		if(null == securityGroupApi){
			securityGroupApi = new SecurityGroupApi();
		}
		return securityGroupApi;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取安全组列表
	* @param access 验证对象
	* @return
	* @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public List<SecurityGroup> listSecurityGroup(Access access) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(),Parameter.NOVA_TYPE_COMPUTE, null,Parameter.FACTING_ADMIN),access.getToken().getId());
			SecurityGroups securityGroups;
			securityGroups = novaClient.execute(SecurityGroupsExtension.listSecurityGroups());
			return securityGroups.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据id获取某个特定的安全组
	 * @return
	 * @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public SecurityGroup showSecurityGroup(Access access,Integer id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_ADMIN), access.getToken().getId());
			SecurityGroup securityGroup;
			securityGroup = novaClient.execute(SecurityGroupsExtension.showSecurityGroup(id));
			return securityGroup;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组 
	* @param access  验证对象
	* @param name  名字
	* @param description 描述
	* @return
	* @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public SecurityGroup createSecurityGroup(Access access,String name,String description) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_ADMIN), access.getToken().getId());
			SecurityGroup securityGroup;
			securityGroup = novaClient.execute(SecurityGroupsExtension.createSecurityGroup(name, description));
			return securityGroup;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 
	* 方法用途和描述: 根据id删除安全组
	* @param access
	* @param id
	* @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void deleteSecurityGroup(Access access,Integer id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_ADMIN), access.getToken().getId());
			novaClient.execute(SecurityGroupsExtension.deleteSecurityGroup(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组规则
	* @param access  验证对象
	* @param ruleForCreate 规则对象
	* @param flat  判断哪种创建，true设置GroupIp创建规则，false设置Cidr创建规则
	* @return
	* @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public Rule createSecurityGroupRule(Access access,SecurityGroupRuleForCreate ruleForCreate,boolean flat) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_ADMIN), access.getToken().getId());
			Rule rule = null;
			if(flat){
				rule = novaClient.execute(SecurityGroupsExtension.createSecurityGroupRule(ruleForCreate.getParentGroupId(), ruleForCreate.getIpProtocol(),ruleForCreate.getFromPort(),ruleForCreate.getToPort(),ruleForCreate.getGroupId()));
			}else{
				rule = novaClient.execute(SecurityGroupsExtension.createSecurityGroupRule(ruleForCreate.getParentGroupId(), ruleForCreate.getIpProtocol(),ruleForCreate.getFromPort(),ruleForCreate.getToPort(),ruleForCreate.getCidr()));
			}
			return rule;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除安全组规则
	* @param access
	* @param id
	* @author chenliang 新增日期：2012-11-26
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void deleteSecurityGroupRule(Access access,Integer id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_ADMIN), access.getToken().getId());
			novaClient.execute(SecurityGroupsExtension.deleteSecurityGroupRule(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据实例id查询实例的安全组信息
	* @param access
	* @param serverId 实例id
	* @return
	* @throws Exception
	* @author chenliang 新增日期：2012-12-5
	* @since WoYun_Api
	 */
	public List<SecurityGroup> showSecurityGroupByServerId(Access access,String serverId) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null,Parameter.FACTING_ADMIN), access.getToken().getId());
			SecurityGroups securityGroups = novaClient.execute(SecurityGroupsExtension.showSecurityGroupByServerId(serverId));
			if(null!=securityGroups){
				return securityGroups.getList();
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
}
