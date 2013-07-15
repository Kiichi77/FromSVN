package cn.com.woyun.keystone.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Tenant;

public class CreateTenant implements KeystoneCommand<Tenant> {

	private Tenant tenant;
	
	public CreateTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	@Override
	public Tenant execute(WebTarget target) {
		return target.path("tenants").request(MediaType.APPLICATION_JSON).post(Entity.json(tenant), Tenant.class);
	}
	
}