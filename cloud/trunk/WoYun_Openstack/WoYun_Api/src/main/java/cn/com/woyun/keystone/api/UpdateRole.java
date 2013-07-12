package cn.com.woyun.keystone.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Role;

public class UpdateRole implements KeystoneCommand<Role> {

	private Role roleForCreate;
	
	public UpdateRole(Role roleForCreate) {
		this.roleForCreate = roleForCreate;
	}

	@Override
	public Role execute(WebTarget target) throws Exception {
		try {
			return target.path("OS-KSADM/roles").path(roleForCreate.getId()).request(MediaType.APPLICATION_JSON).put(Entity.json(roleForCreate), Role.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
