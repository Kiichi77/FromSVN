package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;

public class DeleteRole implements KeystoneCommand<Void> {

	private String id;
	
	public DeleteRole(String id) {
		this.id = id;
	}

	@Override
	public Void execute(WebTarget target) throws Exception {
		try {
			target.path("OS-KSADM/roles").path(id).request(MediaType.APPLICATION_JSON).delete();
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
