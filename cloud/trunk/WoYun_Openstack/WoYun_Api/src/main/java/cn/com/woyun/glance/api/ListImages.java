package cn.com.woyun.glance.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.glance.GlanceCommand;
import cn.com.woyun.glance.model.Images;

public class ListImages implements GlanceCommand<Images> {

	boolean detail;
	
	public ListImages(boolean detail) {
		this.detail = detail;
	}
	
	public ListImages() {
		this(false);
	}

	@Override
	public Images execute(WebTarget target) throws Exception {
		try {
			String path = detail ? "images/detail" : "images";
			return target.path(path).request(MediaType.APPLICATION_JSON).get(Images.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
