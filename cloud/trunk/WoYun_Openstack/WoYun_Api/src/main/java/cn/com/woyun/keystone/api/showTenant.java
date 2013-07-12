package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Tenant;


public class showTenant implements KeystoneCommand<Tenant>{

	private String id;
	
	public showTenant(String id) {
		this.id = id;
	}
	
	@Override
	public Tenant execute(WebTarget target) throws Exception {
		try {
			return target.path("tenants").path(id).request(MediaType.APPLICATION_JSON).get(Tenant.class);
		} catch (Exception e) {
			throw e;
		}
	}

}