package cn.com.woyun.keystone.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.User;

public class UpdateUser implements KeystoneCommand<User> {

	private String userId;
	
	private User userForCreate;
	
	public UpdateUser(String id, User userForCreate) {
		this.userForCreate = userForCreate;
		this.userId = id;
	}

	@Override
	public User execute(WebTarget target) throws Exception {
		try {
			return target.path("users").path(userId).request(MediaType.APPLICATION_JSON).put(Entity.json(userForCreate), User.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
