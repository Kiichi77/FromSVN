package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;

public class DeleteUser implements KeystoneCommand<Void> {

	private String id;
	
	public DeleteUser(String id) {
		this.id = id;
	}

	@Override
	public Void execute(WebTarget target) throws Exception {
		try {
			target.path("users").path(id).request(MediaType.APPLICATION_JSON).delete();
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
