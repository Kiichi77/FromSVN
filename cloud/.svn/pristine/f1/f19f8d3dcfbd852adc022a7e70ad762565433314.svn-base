package cn.com.woyun.keystone.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.KeystoneClient;
import cn.com.woyun.keystone.api.AddUserToTenant;
import cn.com.woyun.keystone.api.ListUserRolesOnTenant;
import cn.com.woyun.keystone.api.RemoveUserFromTenant;
import cn.com.woyun.keystone.model.Role;
import cn.com.woyun.keystone.model.Roles;

/**
 * 
* 功能描述:租户用户角色的关系管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class TenantUserRoleApi {

	private static TenantUserRoleApi tenantUserRoleApi = null;
	
	private TenantUserRoleApi(){
	}
	
	public static TenantUserRoleApi getInstance(){
		if(null==tenantUserRoleApi){
			tenantUserRoleApi = new TenantUserRoleApi();
		}
		return tenantUserRoleApi;
	}
	
	/**
	 * 
	* 方法用途和描述: 给租户的用户添加角色
	* @author chenliang 新增日期：2012-11-19
	* @since WoYun_Api
	* @return 
	* @param
	 * @throws Exception 
	 */
	//TODO
	public void addUserToTenant(String token,String tenantId,String userId,String roleId) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			keystone.execute(new AddUserToTenant(tenantId, userId, roleId));
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	* 方法用途和描述: 获取某个租户下某个用户的权限
	* @param passwordCredentials
	* @param tenantId
	* @param userId
	* @author chenliang 新增日期：2012-11-19
	* @since WoYun_Api
	* @return @param passwordCredentials
	* @return @param tenantId
	* @return @param userId
	* @param @param passwordCredentials
	* @param @param tenantId
	* @param @param userId
	 * @throws Exception 
	 */
	public List<Role> listUserRoleOnTenant(String token,String tenantId,String userId) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Roles roles = keystone.execute(new ListUserRolesOnTenant(tenantId, userId));
			List<Role> rolelist = roles.getList();
			return rolelist;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除某个租户下的某个用户的权限
	* @param passwordCredentials
	* @author chenliang 新增日期：2012-11-19
	* @since WoYun_Api
	* @return @param passwordCredentials
	* @param @param passwordCredentials
	 * @throws Exception 
	 */
	public void removeUserFromTenant(String token,String tenantId,String userId,String roleId) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_AUTH_URL);
			keystone.setToken(token);
			keystone.execute(new RemoveUserFromTenant(tenantId, userId, roleId));
		} catch (Exception e) {
			throw e;
		}
	}
}
