package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Users;

public class ListUsersOnTenant implements KeystoneCommand<Users>{
	
	private String tenantId;
	
	public ListUsersOnTenant(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public Users execute(WebTarget target) {
		return target.path("tenants").path(tenantId).path("users").request(MediaType.APPLICATION_JSON).get(Users.class);
	}

}
