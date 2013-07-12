package cn.com.woyun.keystone.api;


import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Authentication;

public class Authenticate implements KeystoneCommand<Access> {
	
	private Authentication authentication;
	
	public Authenticate(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public Access execute(WebTarget target) throws Exception {
		try {
			return target.path("/tokens").request(MediaType.APPLICATION_JSON).post(Entity.json(authentication), Access.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
