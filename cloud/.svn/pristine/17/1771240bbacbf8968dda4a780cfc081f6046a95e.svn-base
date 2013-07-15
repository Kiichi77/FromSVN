package cn.com.woyun.keystone;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.User;

public class TenantTest extends BaseTest{
	String tenantId = "231bd40342c747399f62b51ce08816d0";
	String userId = "";
	
	
//	@Test (groups={"tenant"})
	public void createTenant(){
		Tenant tenant = new Tenant();
		tenant.setDescription("testNG create Tenant");
		tenant.setEnabled(true);
		tenant.setName("testng");
		Tenant tt;
		try {
			tt = tenantApi.createTenant(tokenLogin.getAccess().getToken().getId(), tenant);
			tenantId = tt.getId();
			System.out.println(tt.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test (groups={"tenant"},dependsOnMethods={"createTenant"})
	public void updateTenant() throws InterruptedException{
		Tenant tenant = new Tenant();
		tenant.setId(tenantId);
		tenant.setDescription("testNG update Tenant");
		tenant.setEnabled(true);
		tenant.setName("testngUpdate");
		Tenant tt;
		try {
			tt = tenantApi.updateTenant(tokenLogin.getAccess().getToken().getId(), tenant);
			System.out.println(tt.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (groups={"tenant"},dependsOnMethods={"createTenant"})
	public void listTenants(){
		List<Tenant> tenantsList;
		try {
			tenantsList = tenantApi.listTenants(tokenLogin.getAccess().getToken().getId());
			for (Tenant tenant : tenantsList) {
				System.out.println("2:new tenant+------" + tenant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
//	@Test (groups={"tenant"},dependsOnMethods={"updateUser"})
	public void listUsersOnTenant(){
		List<User> ll;
		try {
			ll = tenantApi.listUsersOnTenant(tokenLogin.getAccess().getToken().getId(), tenantId);
			for (User u : ll) {
				System.out.println("new tenant+------"+u.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (groups={"tenant"},dependsOnMethods={"deleteUser"})
	public void deleteTenant(){
		try {
			tenantApi.deleteTenant(tokenLogin.getAccess().getToken().getId(), tenantId);
			System.out.println("删除完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
