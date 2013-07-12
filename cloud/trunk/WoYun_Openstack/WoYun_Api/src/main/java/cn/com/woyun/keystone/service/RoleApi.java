package cn.com.woyun.keystone.service;

import java.util.ArrayList;
import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.KeystoneClient;
import cn.com.woyun.keystone.api.CreateRole;
import cn.com.woyun.keystone.api.DeleteRole;
import cn.com.woyun.keystone.api.ListRoles;
import cn.com.woyun.keystone.api.ListServices;
import cn.com.woyun.keystone.api.UpdateRole;
import cn.com.woyun.keystone.model.Role;
import cn.com.woyun.keystone.model.Roles;
import cn.com.woyun.keystone.model.Services;

/**
 * 
* 功能描述:角色管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class RoleApi {
	private static RoleApi instance = null;

	private RoleApi() {
	}

	public static RoleApi getInstance() {
		if (instance == null) {
			instance = new RoleApi();
		}
		return instance;
	}

	/**
	 * 
	* 方法用途和描述: 创建角色
	* @param token 第二验证之后获取的tokenid
	* @param role 角色对象
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param role
	* @return @return
	* @param @param token
	* @param @param role
	* @param @return
	 * @throws Exception 
	 */
	public Role createRole(String token, Role role) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Role roleForCreate = keystone.execute(new CreateRole(role));
			return roleForCreate;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 返回所有角色
	* @param token 第二次验证之后获取的tokenid
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @return
	* @param @param token
	* @param @return
	 * @throws Exception 
	 */
	public List<Role> listRoles(String token) throws Exception {
		try {
			List<Role> newList = new ArrayList<Role>();
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Roles roles = keystone.execute(new ListRoles());
			for (Role r : roles) {
				newList.add(r);
			}
			return newList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述:删除角色
	* @param token 第二次验证之后获取的tokenid
	* @param id 角色id
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param id
	* @param @param token
	* @param @param id
	 * @throws Exception 
	 */
	public void deleteRole(String token,String id) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			keystone.execute(new DeleteRole(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 更新用户
	* @param token 第二次验证之后获取的tokenid
	* @param req
	* @param id 角色id
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param req
	* @return @param id
	* @return @return
	* @param @param token
	* @param @param req
	* @param @param id
	* @param @return
	 * @throws Exception 
	 */
	public Role updateRole(String token,Role req) throws Exception {
		//TODO 执行出错
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Role tt = keystone.execute(new UpdateRole(req));
			return tt;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 返回所有服务
	* @param token 第二次验证之后获取的tokenid
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @return
	* @param @param token
	* @param @return
	 * @throws Exception 
	 */
	public Services listServices(String token) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Services s = keystone.execute(new ListServices());
			return s;
		} catch (Exception e) {
			throw e;
		}
	}
}
