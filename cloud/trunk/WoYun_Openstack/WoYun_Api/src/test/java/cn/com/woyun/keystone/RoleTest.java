package cn.com.woyun.keystone;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.keystone.model.Role;
import cn.com.woyun.keystone.model.Service;
import cn.com.woyun.keystone.model.Services;

public class RoleTest extends BaseTest{
	String roleId = "";
	
//	@Test (groups={"role"})
	public void createRole(){
		Role role = new Role();
		role.setName("createRole");
		Role create;
		try {
			create = roleApi.createRole(tokenLogin.getAccess().getToken().getId(), role);
			roleId = create.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO
//	@Test
	public void updateRole(){
		Role req = new Role();
		req.setName("updateTestRole");
		req.setId("103b98e1ca26432c9a5b5bdfcaa6f2ad");
		try {
			roleApi.updateRole(tokenLogin.getAccess().getToken().getId(), req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (groups={"role"},dependsOnMethods={"createRole"})
	public void deleteRole(){
		try {
			roleApi.deleteRole(tokenLogin.getAccess().getToken().getId(), roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("删除完成");
	}
	
//	@Test (groups={"role"},dependsOnMethods={"deleteRole"})
	public void listRoles(){
		List<Role> r;
		try {
			r = roleApi.listRoles(tokenLogin.getAccess().getToken().getId());
			for (Role t : r) {
				System.out.println("list role+------"+t.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test (groups={"role"},dependsOnMethods={"createRole"})
	public void listServices(){
		Services ll;
		try {
			ll = roleApi.listServices(tokenLogin.getAccess().getToken().getId());
			for (Service u : ll) {
				System.out.println("new tenant+------"+u.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
