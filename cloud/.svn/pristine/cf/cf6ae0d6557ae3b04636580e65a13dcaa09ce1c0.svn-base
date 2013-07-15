package cn.com.woyun.keystone.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.keystone.KeystoneCommand;
import cn.com.woyun.keystone.model.Tenants;


public class ListTenants implements KeystoneCommand<Tenants>{

	@Override
	public Tenants execute(WebTarget target) throws Exception {
		try {
			return target.path("tenants").request(MediaType.APPLICATION_JSON).get(Tenants.class);
		} catch (Exception e) {
			throw e;
		}
	}

}