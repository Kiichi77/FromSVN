package cn.com.kxcomm.woyun.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import cn.com.kxcomm.woyun.vo.UserVo;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.User;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;

/**
 * 系统用户管理业务逻辑处理接口
 * @author chenliang
 *
 */
public interface UserService extends CommonService<PasswordCredentials> {

	/**
	 * 登陆检查一
	 * @param userentity
	 * @return tokenId
	 */
	public String checkLogin(PasswordCredentials credentials);
	
	/**
	 * 查询该用户下的所有租户
	 * @param Tenant
	 * @return List tenantsList 
	 */
	public List<Tenant> queryAllTenants(String tokenId) throws Exception;
	/**
	 * 二次认证(帐号、密码、租户)
	 * @param passwordCredentials,tenantId
	 * @return Access 
	 */
	public Access queryAccess(PasswordCredentials passwordCredentials, String tenantId)  throws Exception;
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
	public List<User> queryAllUser(String tokenId) throws Exception;
	
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
	public UserVo queryUserRoleById(User user,String token,String tenantId);
	
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
	public String submitUserInfo(String tokenId, String userId, UserVo newuser);
	
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
	public String deleteUserById(String tokenId,String userId);
	
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
	public String saveUserInfo(String tokenId,UserVo newuser);
}
