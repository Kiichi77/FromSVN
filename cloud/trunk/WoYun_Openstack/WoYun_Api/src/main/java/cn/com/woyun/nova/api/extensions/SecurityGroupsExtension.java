package cn.com.woyun.nova.api.extensions;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.SecurityGroup;
import cn.com.woyun.nova.model.SecurityGroupForCreate;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;
import cn.com.woyun.nova.model.SecurityGroups;

public class SecurityGroupsExtension {
	
	public static class ListSecurityGroups implements NovaCommand<SecurityGroups>{

		@Override
		public SecurityGroups execute(WebTarget target) throws Exception {
			try {
				return target.path("os-security-groups").request(MediaType.APPLICATION_JSON).get(SecurityGroups.class);
			} catch (Exception e) {
				throw e;
			}
		}

	}

	public static class CreateSecurityGroup implements NovaCommand<SecurityGroup> {

		private SecurityGroupForCreate securityGroupForCreate;
		
		public CreateSecurityGroup(SecurityGroupForCreate securityGroupForCreate) {
			this.securityGroupForCreate = securityGroupForCreate;
		}

		@Override
		public SecurityGroup execute(WebTarget target) throws Exception {
			try {
				return target.path("os-security-groups").request(MediaType.APPLICATION_JSON).post(Entity.json(securityGroupForCreate), SecurityGroup.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class ShowSecurityGroup implements NovaCommand<SecurityGroup> {

		private Integer id;
		
		public ShowSecurityGroup(Integer id) {
			this.id = id;
		}

		@Override
		public SecurityGroup execute(WebTarget target) throws Exception {
			try {
				return target.path("os-security-groups").path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(SecurityGroup.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class DeleteSecurityGroup implements NovaCommand<Void> {

		private Integer id;
		
		public DeleteSecurityGroup(Integer id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("os-security-groups").path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class CreateSecurityGroupRule implements NovaCommand<SecurityGroup.Rule> {

		private SecurityGroupRuleForCreate securityGroupRuleForCreate;
		
		public CreateSecurityGroupRule(SecurityGroupRuleForCreate securityGroupRuleForCreate) {
			this.securityGroupRuleForCreate = securityGroupRuleForCreate;
		}

		@Override
		public SecurityGroup.Rule execute(WebTarget target) throws Exception {
			try {
				return target.path("os-security-group-rules").request(MediaType.APPLICATION_JSON).post(Entity.json(securityGroupRuleForCreate), SecurityGroup.Rule.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class DeleteSecurityGroupRule implements NovaCommand<Void> {

		private Integer id;
		
		public DeleteSecurityGroupRule(Integer id) {
			this.id = id;
		}

		@Override
		public Void execute(WebTarget target) throws Exception {
			try {
				target.path("os-security-groups-rules").path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
				return null;
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static class showSecurityGroupByServerId implements NovaCommand<SecurityGroups>{

		private String serverId;
		
		public showSecurityGroupByServerId(String serverId) {
			this.serverId = serverId;
		}
		
		@Override
		public SecurityGroups execute(WebTarget target) throws Exception {
			try {
				return target.path("servers").path(serverId).path("os-security-groups").request(MediaType.APPLICATION_JSON).get(SecurityGroups.class);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}
	
	public static ListSecurityGroups listSecurityGroups() throws Exception {
		return new ListSecurityGroups();
	}
	
	public static CreateSecurityGroup createSecurityGroup(String name, String description) throws Exception {
		return new CreateSecurityGroup(new SecurityGroupForCreate(name, description));
	}
	
	public static CreateSecurityGroup createSecurityGroup(String name) throws Exception {
		return createSecurityGroup(name, null);
	}
	
	public static ShowSecurityGroup showSecurityGroup(Integer id) throws Exception {
		return new ShowSecurityGroup(id);
	}
	
	public static DeleteSecurityGroup deleteSecurityGroup(Integer id) throws Exception {
		return new DeleteSecurityGroup(id);
	}
	
	public static CreateSecurityGroupRule createSecurityGroupRule(Integer parentSecurityGroupId, String ipProtocol, Integer fromPort, Integer toPort, String cidr) throws Exception {
		SecurityGroupRuleForCreate securityGroupRuleForCreate = new SecurityGroupRuleForCreate(parentSecurityGroupId, ipProtocol, fromPort, toPort, cidr);
		return new CreateSecurityGroupRule(securityGroupRuleForCreate);
	}
	
	public static CreateSecurityGroupRule createSecurityGroupRule(Integer parentSecurityGroupId, String ipProtocol, Integer fromPort, Integer toPort, Integer sourceGroupId) throws Exception {
		SecurityGroupRuleForCreate securityGroupRuleForCreate = new SecurityGroupRuleForCreate(parentSecurityGroupId, ipProtocol, fromPort, toPort, sourceGroupId);
		return new CreateSecurityGroupRule(securityGroupRuleForCreate);
	}
	
	public static DeleteSecurityGroupRule deleteSecurityGroupRule(Integer id) throws Exception {
		return new DeleteSecurityGroupRule(id);
	}
	
	public static showSecurityGroupByServerId showSecurityGroupByServerId(String serverId){
		return new showSecurityGroupByServerId(serverId);
	}

}
