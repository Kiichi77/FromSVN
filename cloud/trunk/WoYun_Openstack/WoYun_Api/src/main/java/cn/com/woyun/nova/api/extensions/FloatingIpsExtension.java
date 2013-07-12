package cn.com.woyun.nova.api.extensions;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.FloatingIps;

public class FloatingIpsExtension {
	
	public static class ListFloatingIps implements NovaCommand<FloatingIps>{

		@Override
		public FloatingIps execute(WebTarget target) throws Exception {
			try {
				return target.path("os-floating-ips").request(MediaType.APPLICATION_JSON).get(FloatingIps.class);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public static class AllocateFloatingIp implements NovaCommand<Void> {
		
		private Map<String, String> body;
			
		public AllocateFloatingIp(String pool) {
			body = new HashMap<String, String>();
			if(pool != null) {
				body.put("pool", pool);
			}
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("os-floating-ips").request(MediaType.APPLICATION_JSON).post(Entity.json(body));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static class DeallocateFloatingIp implements NovaCommand<Void> {

		private String id;
		
		public DeallocateFloatingIp(String id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("os-floating-ips").path(id).request(MediaType.APPLICATION_JSON).delete();
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}

	
	public static class AssociateFloatingIp implements NovaCommand<Void> {
		
		private cn.com.woyun.nova.model.ServerAction.AssociateFloatingIp action;

		private String id;
		
		public AssociateFloatingIp(String id, cn.com.woyun.nova.model.ServerAction.AssociateFloatingIp action) {
			this.id = id;
			this.action = action;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(action));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static class DisassociateFloatingIp implements NovaCommand<Void> {
		
		private cn.com.woyun.nova.model.ServerAction.DisassociateFloatingIp action;

		private String id;
		
		public DisassociateFloatingIp(String id, cn.com.woyun.nova.model.ServerAction.DisassociateFloatingIp action) {
			this.id = id;
			this.action = action;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(action));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public static ListFloatingIps listFloatingIps() throws Exception {
		return new ListFloatingIps();
	}
	
	public static AllocateFloatingIp allocateFloatingIp(String pool)  throws Exception {
		return new AllocateFloatingIp(pool);
	}
	
	public static DeallocateFloatingIp deallocateFloatingIp(String id)  throws Exception {
		return new DeallocateFloatingIp(id);
	}
	
	public static AssociateFloatingIp associateFloatingIp(String serverId, String floatingIpAddress) throws Exception{
		cn.com.woyun.nova.model.ServerAction.AssociateFloatingIp action = new cn.com.woyun.nova.model.ServerAction.AssociateFloatingIp(floatingIpAddress);
		return new AssociateFloatingIp(serverId, action);
	}
	
	public static DisassociateFloatingIp disassociateFloatingIp(String serverId, String floatingIpAddress) throws Exception {
		cn.com.woyun.nova.model.ServerAction.DisassociateFloatingIp action = new cn.com.woyun.nova.model.ServerAction.DisassociateFloatingIp(floatingIpAddress);
		return new DisassociateFloatingIp(serverId, action);
	}
	
}
