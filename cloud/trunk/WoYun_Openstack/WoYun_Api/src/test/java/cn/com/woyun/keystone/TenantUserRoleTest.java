package cn.com.woyun.keystone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.keystone.model.Role;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.User;

public class TenantUserRoleTest extends BaseTest {

	//TODO
//	@Test
	public void addUserToTenant() throws Exception{
		String tenantId = ""; //3bf36641ac6141fb9d10e9bbd7e82932
		List<Tenant> tenantList = tenantApi.getTenants(tokenLogin.getAccess().getToken().getId());
		for (Tenant tenant : tenantList) {
			System.out.println("[tenant]"+tenant);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入租户id：*********************************");
		tenantId= in.readLine();
		
		String userId = ""; //7b83931b66064db294c208d091f6b5c5
		List<User> userList = userApi.listUsers(tokenLogin.getAccess().getToken().getId());
		for (User user : userList) {
			System.out.println("[user]"+user);
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入用户id：*********************************");
		userId= in.readLine();
		
		String roleId = "";  //382b3734aaea46f6a3fd09db1682c4b9
		List<Role> roleList = roleApi.listRoles(tokenLogin.getAccess().getToken().getId());
		for (Role role : roleList) {
			System.out.println("[role]"+role);
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入角色id：*********************************");
		roleId= in.readLine();
		
		tenantUserRoleApi.addUserToTenant(tokenLogin.getAccess().getToken().getId(), tenantId, userId, roleId);
	}
	
//	@Test
	public void listUserRolesOnTenant() throws Exception{
		String tenantId = ""; 
		List<Tenant> tenantList = tenantApi.getTenants(tokenLogin.getAccess().getToken().getId());
		for (Tenant tenant : tenantList) {
			System.out.println("[tenant]"+tenant);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入租户id：*********************************");
		tenantId= in.readLine();
		
		String userId = ""; 
		List<User> userList = userApi.listUsers(tokenLogin.getAccess().getToken().getId());
		for (User user : userList) {
			System.out.println("[user]"+user);
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入用户id：*********************************");
		userId= in.readLine();
		
		List<Role> roleList = tenantUserRoleApi.listUserRoleOnTenant(tokenLogin.getAccess().getToken().getId(), tenantId, userId);
		for (Role role : roleList) {
			System.out.println(role.toString());
		}
	}
	
//	@Test
	public void removeUserFromTenants() throws Exception{
		String tenantId = "";
		List<Tenant> tenantList = tenantApi.getTenants(tokenLogin.getAccess().getToken().getId());
		for (Tenant tenant : tenantList) {
			System.out.println("[tenant]"+tenant);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入租户id：*********************************");
		tenantId= in.readLine();
		
		String userId = ""; 
		List<User> userList = userApi.listUsers(tokenLogin.getAccess().getToken().getId());
		for (User user : userList) {
			System.out.println("[user]"+user);
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入用户id：*********************************");
		userId= in.readLine();
		
		String roleId = "";  
		List<Role> roleList = roleApi.listRoles(tokenLogin.getAccess().getToken().getId());
		for (Role role : roleList) {
			System.out.println("[role]"+role);
		}
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************根据以上列表,输入角色id：*********************************");
		roleId= in.readLine();
		
		tenantUserRoleApi.removeUserFromTenant(tokenLogin.getAccess().getToken().getId(), tenantId, userId, roleId);
	}
}
