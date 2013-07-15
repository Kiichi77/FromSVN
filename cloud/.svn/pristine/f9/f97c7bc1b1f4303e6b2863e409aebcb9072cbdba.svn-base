package cn.com.woyun.keystone.api;


import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Throwables;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Role;

public class CreateRole implements KeystoneCommand<Role> {

	private Role roleForCreate;
	
	public CreateRole(Role roleForCreate) {
		this.roleForCreate = roleForCreate;
	}

	@Override
	public Role execute(WebTarget target) throws Exception {
		try {
			return target.path("OS-KSADM/roles").request(MediaType.APPLICATION_JSON).post(Entity.json(roleForCreate), Role.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
