package cn.com.woyun.nova.api;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.Parameter;
import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.Metadata;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.ServerAction.ChangePassword;
import cn.com.woyun.nova.model.ServerAction.ConfirmResize;
import cn.com.woyun.nova.model.ServerAction.CreateImage;
import cn.com.woyun.nova.model.ServerAction.Pause;
import cn.com.woyun.nova.model.ServerAction.Reboot;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.ServerAction.Resize;
import cn.com.woyun.nova.model.ServerAction.RevertResize;
import cn.com.woyun.nova.model.ServerAction.Start;
import cn.com.woyun.nova.model.ServerAction.Stop;
import cn.com.woyun.nova.model.ServerAction.Unpause;
import cn.com.woyun.nova.model.ServerForCreate;
import cn.com.woyun.nova.model.Servers;

public class ServersCore {
	
	public static class ListServers implements NovaCommand<Servers> {
		
		boolean detail;
		
		public ListServers(boolean detail) {
			this.detail = detail;
		}
		
		public ListServers() {
			this(false);
		}

		@Override
		public Servers execute(WebTarget target) throws Exception {
			try {
				String path = detail ? "servers/detail" : "servers";
				return target.path(path).request(MediaType.APPLICATION_JSON).get(Servers.class);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public static class CreateServer implements NovaCommand<Server> {

		private ServerForCreate serverForCreate;
		
		public CreateServer(ServerForCreate serverForCreate) {
			this.serverForCreate = serverForCreate;
		}

		@Override
		public Server execute(WebTarget target) throws Exception {
			try {
				return target.path("servers").request(MediaType.APPLICATION_JSON).post(Entity.json(serverForCreate), Server.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ShowServer implements NovaCommand<Server> {

		private String id;
		
		public ShowServer(String id) {
			this.id = id;
		}

		@Override
		public Server execute(WebTarget target) throws Exception {
			try {
				return target.path("servers").path(id).request(MediaType.APPLICATION_JSON).get(Server.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ShowServerMetadata implements NovaCommand<Map<String, String>> {

		private String id;
		
		public ShowServerMetadata(String id) {
			this.id = id;
		}

		@Override
		public Map<String, String> execute(WebTarget target) throws Exception {
			try {
				Metadata metadata = target.path("servers").path(id).path("metadata").request(MediaType.APPLICATION_JSON).get(Metadata.class);
				return metadata.getMetadata();
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ShowServerAddresses implements NovaCommand<Server.Addresses> {

		private String id;
		
		public ShowServerAddresses(String id) {
			this.id = id;
		}

		@Override
		public Server.Addresses execute(WebTarget target) throws Exception {
			try {
				return target.path("servers").path(id).path("ips").request(MediaType.APPLICATION_JSON).get(Server.Addresses.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}


	
	public static class UpdateServer implements NovaCommand<Server> {

		private ServerForCreate serverForCreate;
		private String id;
		
		public UpdateServer(ServerForCreate serverForCreate,String id) {
			this.serverForCreate = serverForCreate;
			this.id = id;
		}

		@Override
		public Server execute(WebTarget target) throws Exception {
			try {
				return target.path("servers").path(id).request(MediaType.APPLICATION_JSON).post(Entity.json(serverForCreate), Server.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}

	
	public static class DeleteServer implements NovaCommand<Void> {

		private String id;
		
		public DeleteServer(String id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).request(MediaType.APPLICATION_JSON).delete();
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ChangePasswordServer implements NovaCommand<Void> {
		
		private ChangePassword action;

		private String id;
		
		public ChangePasswordServer(String id, ChangePassword action) {
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
	
	public static class PausesServer implements NovaCommand<Void> {
		
		private Pause pause;
		
		private String id;
		
		public PausesServer(Pause pause,String id){
			this.pause = pause;
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(pause));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public static class UnPausesServer implements NovaCommand<Void>{
		
		private Unpause unpause;
		private String id;
		
		public UnPausesServer(Unpause unpause,String id){
			this.unpause = unpause;
			this.id = id;
		}
		
		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(unpause));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public static class StartServer implements NovaCommand<Void>{

		private Start start;
		private String id;
		
		public StartServer(Start start,String id){
			this.start = start;
			this.id = id;
		}
		
		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(start));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}

	public static class StopServer implements NovaCommand<Void>{
		
		private Stop stop;
		private String id;
		
		public StopServer(Stop stop,String id){
			this.stop = stop;
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(stop));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public static class RebootServer implements NovaCommand<Void> {
		
		private Reboot action;

		private String id;
		
		public RebootServer(String id, Reboot action) {
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
	
	public static class RebuildServer implements NovaCommand<Void> {
		
		private final Rebuild action;

		private final String id;
		
		public RebuildServer(String id, Rebuild action) {
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
	
	public static class ResizeServer implements NovaCommand<Void> {
		
		private final Resize action;

		private final String id;
		
		public ResizeServer(String id, Resize action) {
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
	
	public static class ConfirmResizeServer implements NovaCommand<Void> {
		
		private static final ConfirmResize ACTION = new ConfirmResize();

		private final String id;
		
		public ConfirmResizeServer(String id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(ACTION));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static class RevertResizeServer implements NovaCommand<Void> {
		
		private static final RevertResize ACTION = new RevertResize();

		private final String id;
		
		public RevertResizeServer(String id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("servers").path(id).path("action").request(MediaType.APPLICATION_JSON).post(Entity.json(ACTION));
				return null;
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static class CreateImageServer implements NovaCommand<Void> {
		
		private CreateImage action;

		private String id;
		
		public CreateImageServer(String id, CreateImage action) {
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

	
	public static ListServers listServers(boolean detail) throws Exception  {
		return new ListServers(detail);
	}
	
	public static ListServers listServers()  throws Exception {
		return listServers(false);
	}
	
	public static CreateServer createServer(ServerForCreate serverForCreate) throws Exception  {
		return new CreateServer(serverForCreate);
	}
	
	public static ShowServer showServer(String id) throws Exception  {
		return new ShowServer(id);
	}
	
	public static ShowServerMetadata showServerMetadata(String id) throws Exception  {
		return new ShowServerMetadata(id);
	}
	
	public static ShowServerAddresses showServerAddresses(String id)  throws Exception {
		return new ShowServerAddresses(id);
	}
	
	public static UpdateServer updateServer(ServerForCreate serverForCreate,String id) throws Exception {
		return new UpdateServer(serverForCreate,id);
	}
	
	public static DeleteServer deleteServer(String id)  throws Exception {
		return new DeleteServer(id);
	}
	
	public static ChangePasswordServer changePassword(String id, String adminPass)  throws Exception {
		ChangePassword changePassword = new ChangePassword(adminPass);
		return new ChangePasswordServer(id, changePassword);
	}
	
	public static RebootServer reboot(String id) throws Exception  {
		Reboot action = new Reboot();
		action.setType(Parameter.REBOOT_TYPE);
		return new RebootServer(id, action);
	}
	
	public static RebuildServer rebuild(String id, Rebuild rebuild) throws Exception  {
		return new RebuildServer(id, rebuild);
	}
	
	public static ResizeServer resize(String id, Resize action)  throws Exception {
		return new ResizeServer(id, action);
	}
	
	public static RevertResizeServer revertResize(String id) throws Exception  {
		return new RevertResizeServer(id);
	}
	
	public static ConfirmResizeServer confirmResize(String id) throws Exception  {
		return new ConfirmResizeServer(id);
	}
	
	public static CreateImageServer createImageServer(String id, CreateImage action)  throws Exception {
		return new CreateImageServer(id, action);
	}
	
	public static PausesServer pausesServer(String id) throws Exception {
		Pause pause = new Pause();
		pause.setPause(null);
		return new PausesServer(pause, id);
	}
	
	public static UnPausesServer unPausesServer(String id) throws Exception {
		Unpause unpause = new Unpause();
		unpause.setUnpause(null);
		return new UnPausesServer(unpause, id);
	}

	public static StartServer startServer(String id) throws Exception {
		Start start = new Start();
		start.setOsStart(null);
		return new StartServer(start, id);
	}
	
	public static StopServer stopServer(String id) throws Exception {
		Stop stop = new Stop();
		stop.setOsStop(null);
		return new StopServer(stop, id);
	}
}
