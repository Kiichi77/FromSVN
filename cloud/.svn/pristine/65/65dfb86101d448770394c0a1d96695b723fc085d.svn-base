package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Roles;

public class ListRoles implements KeystoneCommand<Roles>{

	@Override
	public Roles execute(WebTarget target) throws Exception {
		try {
			return target.path("OS-KSADM/roles").request(MediaType.APPLICATION_JSON).get(Roles.class);
		} catch (Exception e) {
			throw e;
		}
	}

}

