package cn.com.woyun.keystone.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.User;

public class CreateUser implements KeystoneCommand<User> {

	private User userForCreate;
	
	public CreateUser(User userForCreate) {
		this.userForCreate = userForCreate;
	}

	@Override
	public User execute(WebTarget target) throws Exception {
		try {
			return target.path("/users").request(MediaType.APPLICATION_JSON).post(Entity.json(userForCreate), User.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
