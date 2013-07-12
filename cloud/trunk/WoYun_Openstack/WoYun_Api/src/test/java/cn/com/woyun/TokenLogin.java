package cn.com.woyun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.service.TenantApi;
import cn.com.woyun.keystone.service.TokenApi;

public class TokenLogin {
	private static TokenLogin instance = null;
	private String authToken;
	private Access access;
	private PasswordCredentials passwordCredentials;
	private static TokenApi tokenApi = TokenApi.getInstance();
	private TenantApi tenantApi = TenantApi.getInstance();
	private String tenantId ;

	
	private TokenLogin(PasswordCredentials passwordCredentials){
		this.passwordCredentials = passwordCredentials;
		try {
			loginAuth();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入选择的租户作为认证：*********************************");
			tenantId = in.readLine();
			System.out.println("选中的租户ID为:"+tenantId);
			tenantAuth(tenantId);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static TokenLogin getInstance(PasswordCredentials passwordCredentials){
		if(instance == null)
			instance = new TokenLogin(passwordCredentials);
		
		return instance;
	}
	
	/**
	 * 
	 * 首次认证(帐号、密码)
	 * 
	 * @author zhangjh 新增日期：2012-11-22
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void loginAuth() throws Exception{
		try {
			authToken = tokenApi.authToken(passwordCredentials);
			List<Tenant> tenantsList = tenantApi.getTenants(authToken);
			for (Tenant tenant2 : tenantsList) {
				System.out.println(tenant2.toString());
			}	
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 二次认证(帐号、密码、租户)
	 * 
	 * @param tenantId
	 * @author zhangjh 新增日期：2012-11-22
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void tenantAuth(String tenantId) throws Exception{
		try {
			access = tokenApi.adminToken(passwordCredentials, tenantId);
		} catch (Exception e) {
			throw e;
		}
	}
	public String getAuthToken() {
		return authToken;
	}
	
	public Access getAccess() {
		return access;
	}
	public PasswordCredentials getPasswordCredentials() {
		return passwordCredentials;
	}
	public String getTenantId() {
		return tenantId;
	}
	
}
