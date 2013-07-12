package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Users;

public class ListUsers implements KeystoneCommand<Users>{

	@Override
	public Users execute(WebTarget target) throws Exception {
		try {
			return target.path("users").request(MediaType.APPLICATION_JSON).get(Users.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
