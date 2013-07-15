package cn.com.woyun.keystone.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.KeystoneClient;
import cn.com.woyun.keystone.api.CreateUser;
import cn.com.woyun.keystone.api.DeleteUser;
import cn.com.woyun.keystone.api.ListUsers;
import cn.com.woyun.keystone.api.ShowUser;
import cn.com.woyun.keystone.api.UpdateUser;
import cn.com.woyun.keystone.model.User;
import cn.com.woyun.keystone.model.Users;

/**
 * 
* 功能描述:用户管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class UserApi {
	private static UserApi instance = null;
	
	private UserApi() {
	}

	public static UserApi getInstance() {
		if (instance == null) {
			instance = new UserApi();
		}
		return instance;
	}
	
	/**
	 * 
	 * 创建用户
	 * 
	 * @param token
	 * @param req
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public User createUser(String token, User req) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			User u = keystone.execute(new CreateUser( req));
			return u;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 删除用户
	 * 
	 * @param token
	 * @param id
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void deleteUser(String token,String id) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			keystone.execute(new DeleteUser(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 查看所有用户列表
	 * 
	 * @param token
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public List<User> listUsers(String token) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			Users users = keystone.execute(new ListUsers());
			return users.getList();	
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据用户id获取用户的详细信息
	* @param token  第二次验证的令牌id
	* @param id  用户id
	* @return
	* @throws Exception
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Api
	 */
	public User showUser(String token,String id) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			User user = keystone.execute(new ShowUser(id));
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 更新用户信息
	 * 
	 * @param token
	 * @param id
	 * @param req
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public User updateUser(String token,String id,User req) throws Exception {
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			keystone.setToken(token);
			User tt = keystone.execute(new UpdateUser(id, req));
			return tt;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
