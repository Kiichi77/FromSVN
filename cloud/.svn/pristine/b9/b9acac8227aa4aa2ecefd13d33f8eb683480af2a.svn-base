package cn.com.woyun.keystone.service;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.KeystoneClient;
import cn.com.woyun.keystone.api.Authenticate;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Authentication;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;
import cn.com.woyun.keystone.model.Authentication.Token;

/**
 * 
* 功能描述:令牌管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class TokenApi {
	private static TokenApi instance = null;
	private TokenApi(){}
	
	public static TokenApi getInstance(){
		if (instance == null){
			instance = new TokenApi();
		}
			return instance; 
	}
	
	/**
	 * 
	 * 普通认证<br/>
	 * <p>如果用户已经认证则返回token，若还没认证则认证后返回token</p>
	 * @param passwordCredentials
	 * @return
	 * @author zhangjh 新增日期：2012-11-14
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public String authToken(PasswordCredentials passwordCredentials) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_AUTH_URL);
			Authentication authentication = new Authentication();
			authentication.setPasswordCredentials(passwordCredentials);
			//登陆验证，与无作用域的令牌访问
			Access access = keystone.execute(new Authenticate(authentication));
			return access.getToken().getId();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 租户管理认证<br/>
	 * <p>如果用户已经认证则返回token，若还没认证则认证后返回token</p>
	 * 
	 * @param passwordCredentials
	 * @param tenantId
	 * @return
	 * @author zhangjh 新增日期：2012-11-14
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public Access adminToken(PasswordCredentials passwordCredentials,String tenantId) throws Exception{
		try {
			KeystoneClient keystone = new KeystoneClient(Parameter.KEYSTONE_ADMIN_URL);
			Authentication authentication = new Authentication();
			authentication.setPasswordCredentials(passwordCredentials);
			Token token = new Token();
			token.setId(tenantId);
			authentication.setToken(token);
			authentication.setTenantId(tenantId);
			Access access = keystone.execute(new Authenticate(authentication));
			return access;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
