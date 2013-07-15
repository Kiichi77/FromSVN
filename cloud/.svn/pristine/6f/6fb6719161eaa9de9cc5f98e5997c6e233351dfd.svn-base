package cn.com.woyun.nova.api;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.Flavor;
import cn.com.woyun.nova.model.FlavorForCreate;
import cn.com.woyun.nova.model.Flavors;
import cn.com.woyun.nova.model.Metadata;

public class FlavorsCore {
	
	public static class CreateFlavors implements NovaCommand<FlavorForCreate>{
		
		FlavorForCreate flavorForCreate;
		
		public CreateFlavors(FlavorForCreate flavorForCreate){
			this.flavorForCreate = flavorForCreate;
		}
		
		@Override
		public FlavorForCreate execute(WebTarget target) throws Exception {
			try {
				return target.path("/flavors").request(MediaType.APPLICATION_JSON).post(Entity.json(flavorForCreate),FlavorForCreate.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class DeleteFlavors implements NovaCommand<Void>{

		String id;
		
		public DeleteFlavors(String id){
			this.id = id;
		}
		
		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("/flavors").path(id).request(MediaType.APPLICATION_JSON).delete();
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ListFlavors implements NovaCommand<Flavors>{

		boolean detail;
		
		public ListFlavors(boolean detail) {
			this.detail = detail;
		}
		
		public ListFlavors() {
			this(false);
		}

		@Override
		public Flavors execute(WebTarget target) throws Exception {
			try {
				String path = detail ? "flavors/detail" : "flavors";
				return target.path(path).request(MediaType.APPLICATION_JSON).get(Flavors.class);
			} catch (Exception e) {
				throw e;
			}
		}

	}
	
	public static class ShowFlavor implements NovaCommand<Flavor> {

		private String id;
		
		public ShowFlavor(String id) {
			this.id = id;
		}

		@Override
		public Flavor execute(WebTarget target) throws Exception {
			try {
				return target.path("flavors").path(id).request(MediaType.APPLICATION_JSON).get(Flavor.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}

	
	public static class ShowFlavorMetadata implements NovaCommand<Map<String, String>> {

		private String id;
		
		public ShowFlavorMetadata(String id) {
			this.id = id;
		}

		@Override
		public Map<String, String> execute(WebTarget target) throws Exception {
			try {
				Metadata metadata = target.path("flavors").path(id).path("metadata").request(MediaType.APPLICATION_JSON).get(Metadata.class);
				return metadata.getMetadata();
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static ListFlavors listFlavors(boolean detail) throws Exception {
		return new ListFlavors(detail);
	}
	
	public static ListFlavors listFlavors() throws Exception {
		return listFlavors(false);
	}
	
	public static ShowFlavor showFlavor(String id) throws Exception {
		return new ShowFlavor(id);
	}
	
	public static ShowFlavorMetadata showFlavorMetadata() throws Exception {
		return new ShowFlavorMetadata("");
	}

	public static CreateFlavors createFlavor(FlavorForCreate flavorForCreate) throws Exception{
		return new CreateFlavors(flavorForCreate);
	}
	
	public static DeleteFlavors deleteFlavor(String id) throws Exception{
		return new DeleteFlavors(id);
	}
}
