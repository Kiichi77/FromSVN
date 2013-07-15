package cn.com.woyun.nova.api.extensions;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.FloatingIpPools;

public class FloatingIpPoolsExtension {

	public static class ListFloatingIpPools implements NovaCommand<FloatingIpPools>{

		@Override
		public FloatingIpPools execute(WebTarget target) throws Exception {
			try {
				return target.path("os-floating-ip-pools").request(MediaType.APPLICATION_JSON).get(FloatingIpPools.class);
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static ListFloatingIpPools listFloatingIpPools() throws Exception {
		return new ListFloatingIpPools();
	}
	
}
