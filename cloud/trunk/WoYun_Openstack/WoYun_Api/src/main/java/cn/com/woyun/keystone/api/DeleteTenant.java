package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;

public class DeleteTenant implements KeystoneCommand<Void> {

	private String id;
	
	public DeleteTenant(String id) {
		this.id = id;
	}

	@Override
	public Void execute(WebTarget target) {
		target.path("tenants").path(id).request(MediaType.APPLICATION_JSON).delete();
		return null;
	}
	
}

