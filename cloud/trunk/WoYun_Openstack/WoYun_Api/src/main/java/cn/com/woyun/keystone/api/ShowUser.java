package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.User;

public class ShowUser implements KeystoneCommand<User>{
	
	private String id;
	
	public ShowUser(String id){
		this.id = id;
	}

	@Override
	public User execute(WebTarget target) throws Exception {
		try {
			return target.path("users").path(id).request(MediaType.APPLICATION_JSON).get(User.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
