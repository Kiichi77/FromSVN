package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Services;

public class ListServices implements KeystoneCommand<Services>{

	@Override
	public Services execute(WebTarget target) {
		return target.path("OS-KSADM/services").request(MediaType.APPLICATION_JSON).get(Services.class);
	}

}
