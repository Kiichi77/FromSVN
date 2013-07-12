package cn.com.woyun.glance.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.glance.GlanceCommand;

public class DeleteImage implements GlanceCommand<Void> {
	
	private String id;
	
	public DeleteImage(String id) {
		this.id = id;
	}

	@Override
	public Void execute(WebTarget target) throws Exception {
		try {
			target.path("images").path(id).request(MediaType.WILDCARD).delete();
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
}
