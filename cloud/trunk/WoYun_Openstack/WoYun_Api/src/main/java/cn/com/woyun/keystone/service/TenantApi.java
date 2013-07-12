package cn.com.woyun.keystone.service;

import java.util.ArrayList;
import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.KeystoneClient;
import cn.com.woyun.keystone.api.CreateTenant;
import cn.com.woyun.keystone.api.DeleteTenant;
import cn.com.woyun.keystone.api.ListTenants;
import cn.com.woyun.keystone.api.ListUsersOnTenant;
import cn.com.woyun.keystone.api.UpdateTenant;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.Tenants;
import cn.com.woyun.keystone.model.User;
import cn.com.woyun.keystone.model.Users;

/**
 * 
* 功能描述:租户管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class TenantApi {
	private static TenantApi instance = null;
	
	private TenantApi(){}
	
	public static TenantApi getInstance(){
		if (instance == null){
			instance = new TenantApi();
		}
			return instance; 
	}
	
	/**
	 *  
	* 方法用途和描述: 返回用户所属的租户信息
	* @param token 第二次验证之后得到的tokenid
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @return
	* @param @param token
	* @param @return
	 * @throws Exception 
	 */
	 
	 
	public List<Tenant> getTenants(String token) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_AUTH_URL);
			keystone.setToken(token);
			Tenants tenants = keystone.execute(new ListTenants());
			List<Tenant> tenantsList = tenants.getList();
			return tenantsList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 获取租户
	* @param token
	* @param id
	* @return
	* @throws Exception
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Api
	 */
	public Tenant showTenant(String token,String id) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Tenant tenant = keystone.execute(new cn.com.woyun.keystone.api.showTenant(id));
			return tenant;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	/**
	 * 
	* 方法用途和描述: 返回所有租户列表
	* @param token 第二验证之后的tokenid
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @return
	* @param @param token
	* @param @return
	 * @throws Exception 
	 */
	
	public List<Tenant> listTenants(String token) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Tenants tenants = keystone.execute(new ListTenants());
			return tenants.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建租户
	* @param token 第二次验证之后得到的tokenid
	* @param req 租户对象
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param req
	* @return @return
	* @param @param token
	* @param @param req
	* @param @return
	 * @throws Exception 
	 */
	public Tenant createTenant(String token,Tenant req) throws Exception{
		try {
			// 请求访问地址
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Tenant tt = keystone.execute(new CreateTenant(req));
			return tt;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 修改租户
	* @param token 第二次验证之后获得tokenid
	* @param req  租户对象
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param req
	* @return @return
	* @param @param token
	* @param @param req
	* @param @return
	 * @throws Exception 
	 */
	public Tenant updateTenant(String token,Tenant req) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Tenant tt = keystone.execute(new UpdateTenant(req));
			return tt;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除租户
	* @param token 第二次验证之后获得tokenid
	* @param id 租户id
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param id
	* @param @param token
	* @param @param id
	 * @throws Exception 
	 */
	public void deleteTenant(String token,String id) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			keystone.execute(new DeleteTenant(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 返回租户下所有用户
	* @param token 第二次验证之后获取的tokenid
	* @param tenantId 
	* @return
	* @author chenliang 新增日期：2012-11-22
	* @since WoYun_Api
	* @return @param token
	* @return @param tenantId
	* @return @return
	* @param @param token
	* @param @param tenantId
	* @param @return
	 * @throws Exception 
	 */
	public List<User> listUsersOnTenant (String token,String tenantId) throws Exception{
		try {
			List<User> newList = new ArrayList<User>();
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Users us = keystone.execute(new ListUsersOnTenant(tenantId));
			for (User tt : us) {
				newList.add(tt);
			}
			return newList;
		} catch (Exception e) {
			throw e;
		}
	}
}
