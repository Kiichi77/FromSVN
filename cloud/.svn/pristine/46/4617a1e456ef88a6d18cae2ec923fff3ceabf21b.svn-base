package cn.com.woyun;

import cn.com.woyun.glance.service.GlanceApi;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;
import cn.com.woyun.keystone.service.RoleApi;
import cn.com.woyun.keystone.service.TenantApi;
import cn.com.woyun.keystone.service.TenantUserRoleApi;
import cn.com.woyun.keystone.service.UserApi;
import cn.com.woyun.nova.service.FlavorsApi;
import cn.com.woyun.nova.service.FloatingIpApi;
import cn.com.woyun.nova.service.QuotaApi;
import cn.com.woyun.nova.service.SecurityGroupApi;
import cn.com.woyun.nova.service.ServersApi;

public class BaseTest {
	public TenantApi tenantApi = TenantApi.getInstance();
	public RoleApi roleApi = RoleApi.getInstance();
	public UserApi userApi = UserApi.getInstance();
	public GlanceApi glanceApi = GlanceApi.getInstance();
	public FlavorsApi flavorsApi = FlavorsApi.getInstance();
	public ServersApi serversApi = ServersApi.getInstance();
	public TenantUserRoleApi tenantUserRoleApi = TenantUserRoleApi.getInstance();
	public QuotaApi quotaApi = QuotaApi.getInstance();
	public FloatingIpApi floatingIpApi = FloatingIpApi.getInstance();
	public SecurityGroupApi securityGroupApi = SecurityGroupApi.getInstance();
	public TokenLogin tokenLogin = null;

	
	public PasswordCredentials passwordCredentials = null;
	
	public BaseTest() {
		passwordCredentials = new PasswordCredentials();
		passwordCredentials.setUsername("admin");
		passwordCredentials.setPassword("admin");
		tokenLogin = TokenLogin.getInstance(passwordCredentials);
		System.out.println("普通认证后的token："+tokenLogin.getAuthToken());
		System.out.println("租户认证后的token："+tokenLogin.getAccess().toString());
	}
}
