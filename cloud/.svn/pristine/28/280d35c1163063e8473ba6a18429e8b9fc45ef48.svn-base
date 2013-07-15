package cn.com.woyun.keystone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.exception.Errors;
import cn.com.woyun.exception.SeriousError;
import cn.com.woyun.keystone.model.User;

public class UserTest extends BaseTest {
	private String tmpId ;
	
//	@Test
	public void createUser() {
		User u = new User();
		u.setEmail("testng@hotmail.com");
		u.setEnabled(true);
		u.setName("aas");
		u.setTenantId(tokenLogin.getTenantId());
		u.setUsername("可删");
		u.setPassword("123456");
		try{
			User t = userApi.createUser(tokenLogin.getAccess().getToken().getId(), u);
			if (t != null) {
				tmpId = t.getId();
				System.out.println("创建用户成功:"+t.toString());
			}
		}catch(Exception e){
			Errors errors = new Errors();
			errors.setCode(11);
			errors.setMsg("用户已创建，请删除后操作");
			throw new SeriousError(errors);
		}
		
	}
	
//	@Test (groups={"user"},dependsOnMethods={"createUser"})
	public void updateUser() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("可操作此ID："+tmpId);
		System.out.println("[输入提示]*************************请输入需要修改的用户ID(回车则选择以上ID)：*********************************");
		String userId = in.readLine();
		if("".equals(userId)||null == userId){
			userId = tmpId;
		}
			
		User u = new User();
		u.setEmail("op@hotmail.com");
		u.setEnabled(true);
		u.setName("aas");
		u.setTenantId(tokenLogin.getTenantId());
		u.setUsername("可删");
		u.setPassword("123456");
		User tt;
		try {
			tt = userApi.updateUser(tokenLogin.getAccess().getToken().getId(),userId, u);
			System.out.println("更新用户成功:"+tt.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (groups={"user"},dependsOnMethods={"updateUser"})
	public void deleteUser() throws Exception{
		System.out.println("可操作此ID："+tmpId);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入需要修改的用户ID(回车则选择以上ID)：*********************************");
		String userId = in.readLine();
		if("".equals(userId)||null == userId){
			userId = tmpId;
		}
		userApi.deleteUser(tokenLogin.getAccess().getToken().getId(), userId);
		System.out.println("删除用户成功");
	}
	
//	@Test //(groups={"user"},dependsOnMethods={"deleteUser"})
	public void listUsers() throws Exception{
		List<User> ll = userApi.listUsers(tokenLogin.getAccess().getToken().getId());
		for (User u : ll) {
			System.out.println("new tenant+------"+u.toString());
		}
	}
	
//	@Test
	public void showUser() throws Exception{
		List<User> ll = userApi.listUsers(tokenLogin.getAccess().getToken().getId());
		for (User user : ll) {
			System.out.println("user:"+user);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入需要查询的用户ID(回车则选择以上ID)：*********************************");
		String userId = in.readLine();
		User user = userApi.showUser(tokenLogin.getAccess().getToken().getId(), userId);
		System.out.println("user1111:"+user);
	}
}
