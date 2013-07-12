package cn.com.woyun.glance.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.glance.GlanceCommand;
import cn.com.woyun.glance.model.Image;

public class UpdateImage implements GlanceCommand<Image>{

	private Image image;
	
	public UpdateImage(Image image) {
		this.image = image;
	}
	
	@Override
	public Image execute(WebTarget endpoint) throws Exception {
		try {
			return endpoint.path("images").path(image.getId()).request(MediaType.APPLICATION_JSON).put(Entity.json(image), Image.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
