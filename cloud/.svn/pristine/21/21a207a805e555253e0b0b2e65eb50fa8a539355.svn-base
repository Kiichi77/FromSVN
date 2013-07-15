package cn.com.kxcomm.woyun.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.woyun.service.UserService;
import cn.com.kxcomm.woyun.vo.UserVo;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Role;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;
import cn.com.woyun.keystone.model.User;
import cn.com.woyun.keystone.service.TenantApi;
import cn.com.woyun.keystone.service.TenantUserRoleApi;
import cn.com.woyun.keystone.service.TokenApi;
import cn.com.woyun.keystone.service.UserApi;

/**
 * 系统用户管理业务逻辑处理类
 * @author chenliang
 *
 */
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<PasswordCredentials> implements UserService {
	private TokenApi apia = TokenApi.getInstance();
	private TenantApi tenantApi = TenantApi.getInstance();
	private UserApi userApi = UserApi.getInstance();
	private TenantUserRoleApi tenantUserRoleApi = TenantUserRoleApi.getInstance();
	
	/**
	 * 登陆检查一
	 * @param userentity
	 * @return tokenId
	 */
	@Override
	public String checkLogin(PasswordCredentials credentials) {
		String tokenId = "error";
		try {
			tokenId = this.apia.authToken(credentials);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenId;
	}
	
	/**
	 * 查询该用户下的所有租户
	 * @param Tenant
	 * @return List tenantsList 
	 * @throws Exception 
	 */
	@Override
	public List<Tenant> queryAllTenants(String tokenId) throws Exception {
		List<Tenant> tenantsList = this.tenantApi.getTenants(tokenId);
		return tenantsList;
	}
	
	/**
	 * 二次认证(帐号、密码、租户)
	 * @param passwordCredentials,tenantId
	 * @return Access 
	 * @throws Exception 
	 */
	@Override
	public Access queryAccess(PasswordCredentials passwordCredentials,String tenantId) throws Exception {
		Access access = this.apia.adminToken(passwordCredentials, tenantId);
		return access;
	}
	
	/**
	 * 
	 * 方法用途和描述: 获取所有用户
	 * @return String
	 * @author lizl 新增日期：2012-11-27
	 * @author 你的姓名 修改日期：2012-11-27
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	@Override
	public List<User> queryAllUser(String tokenId) throws Exception{
		List<User> users = this.userApi.listUsers(tokenId);
		return users;
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据TenantID查询用户角色
	 * @return String
	 * @author lizl 新增日期：2012-11-27
	 * @author 你的姓名 修改日期：2012-11-27
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	@Override
	public UserVo queryUserRoleById(User user,String token,String tenantId) {
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setEmail(user.getEmail());
		userVo.setEnabled(user.getEnabled());
		userVo.setName(user.getName());
		userVo.setPassword(user.getPassword());
		userVo.setTenantId(user.getTenantId());
		userVo.setUsername(user.getUsername());
		List<Role> roles;
		try {
			roles = this.tenantUserRoleApi.listUserRoleOnTenant(token,tenantId,user.getId());
			if(0 != roles.size()) {
				userVo.setRoleId(roles.get(0).getId());
				userVo.setRoleName(roles.get(0).getName());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userVo;
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据ID查询某个用户的信息
	 * @return String
	 * @author lizl 新增日期：2012-11-28
	 * @author 你的姓名 修改日期：2012-11-28
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	@Override
	public String submitUserInfo(String tokenId,String userId,UserVo newuser) {
		String end = "ok";
		try {
			User user = this.userApi.showUser(tokenId,userId);
			user.setUsername(newuser.getUsername());
			user.setEmail(newuser.getEmail());
			user.setTenantId(newuser.getTenantId());
			this.userApi.updateUser(tokenId,userId,user);
		} catch (Exception exception) {
			end = "error";
		}
		return end;
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据ID删除某个用户
	 * @return String
	 * @author lizl 新增日期：2012-11-28
	 * @author 你的姓名 修改日期：2012-11-28
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	@Override
	public String deleteUserById(String tokenId,String userId) {
		String end = "ok";
		try {
			if(0 > userId.indexOf(",")) {
				this.userApi.deleteUser(tokenId,userId);
			} else {
				String[] user = userId.split(",");
				for(int i = 0;i < user.length; i++) {
					this.userApi.deleteUser(tokenId,user[i]);
				}
			}
		} catch (Exception exception) {
			 end = "error";
		}
		return end;
	}
	
	/**
	 * 
	 * 方法用途和描述: 新增用户
	 * @return String
	 * @author lizl 新增日期：2012-11-30
	 * @author 你的姓名 修改日期：2012-11-30
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	@Override
	public String saveUserInfo(String tokenId,UserVo newuser) {
		String end = "ok";
		try {
			List<User> users = this.userApi.listUsers(tokenId);
			for(int i = 0;i < users.size(); i++) {
				if(users.get(i).getName().equals(newuser.getName())) {
					end = "ishave";
					return end;
				}
			}
			User user = new User();
			user.setUsername(newuser.getUsername());
			user.setEmail(newuser.getEmail());
			user.setPassword(newuser.getPassword());
			user.setEnabled(true);
			user.setName(newuser.getName());
			user.setTenantId(newuser.getTenantId());
			this.userApi.createUser(tokenId,user);
		} catch (Exception exception) {
			 end = "error";
		}
		return end;
	}
}