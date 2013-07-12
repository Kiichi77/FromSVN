package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Role;

public class AddUserToTenant implements KeystoneCommand<Void> {

	private String tenantId;
	private String userId;
	private String roleId;
	
	public AddUserToTenant(String tenantId, String userId, String roleId) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.roleId = roleId;
	}
	
	@Override
	public Void execute(WebTarget target) throws Exception {
		try {
			target.path("tenants").path(tenantId).path("users").path(userId).path("roles/OS-KSADM").path(roleId).request(MediaType.APPLICATION_JSON).method("PUT",Role.class);
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
