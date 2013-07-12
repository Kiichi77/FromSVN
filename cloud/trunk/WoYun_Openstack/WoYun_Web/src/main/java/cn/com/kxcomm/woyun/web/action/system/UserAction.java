package cn.com.kxcomm.woyun.web.action.system;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.UserService;
import cn.com.kxcomm.woyun.vo.NowUseTentidVo;
import cn.com.kxcomm.woyun.vo.UserVo;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.model.Authentication.PasswordCredentials;
import cn.com.woyun.keystone.model.User;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * 用户管理模块.
 *
 * @author 林洒玛.
 *         Created 2012-11-27.
 */
public class UserAction extends BaseAction<UserVo, String> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 所有租户的结果集
	 */
	public List<Tenant> tenants;
	/**
	 * 用户VO
	 */
	private JSONObject result;

	public JSONObject getResult() {
		return this.result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public List<Tenant> getTenants() {
		return this.tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}
	
	@Autowired(required=true)
	public UserService userService;

	private List<User> userList;

	public List<User> getUserList() {
		return this.userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	private UserVo userVo;
	/**
	 * 用户ID
	 * */
	private String usersid;
	
	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public UserVo getUserVo() {
		return this.userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	/**
	 * 
	 * 方法用途和描述: 用户登录
	 * @return String
	 * @author lizl 新增日期：2012-11-27
	 * @author 你的姓名 修改日期：2012-11-27
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	public String login() throws SQLException, ServletException, IOException {
		log.info("username:"+getModel().getUsername()+"    pwd:  "+getModel().getPassword());
		PasswordCredentials ce = new PasswordCredentials();
		ce.setUsername(getModel().getUsername());
		ce.setPassword(getModel().getPassword());
		HttpSession session = getHttpSession();
		String tokenId = "";
		String basePath = "";
		HttpServletRequest request = getHttpRequest();// Struts2Util.getHttpRequest();
		String path = request.getContextPath();
		try {
			log.debug("[=============userService=============]第一次登陆验证"+this.userService);
			tokenId = this.userService.checkLogin(ce);
		} catch (Exception ex) {
			//跳转界面
			session.setAttribute("msg","pwdError");
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/login.jsp";
			getHttpResponse().sendRedirect(basePath);
		}
		log.info("tengId:    "+tokenId);
		if (null == tokenId || "".equals(tokenId.trim()) || "error".equals(tokenId.trim())) {
			log.debug("用户名不存在,登陆失败...");
			session.setAttribute("msg","noProject");
			//跳转界面
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/login.jsp";
			getHttpResponse().sendRedirect(basePath);
		} else {
			try {
				this.tenants = this.userService.queryAllTenants(tokenId);
				HashMap<String, Access> hashMap = new HashMap<String, Access>();
				NowUseTentidVo nowUse = null;
				Access access = null;
				for(int i=0;i < this.tenants.size(); i++) {
					access = new Access();
					access = this.userService.queryAccess(ce,this.tenants.get(i).getId());
					if(0 == i) {
						nowUse = new NowUseTentidVo();
						nowUse.setTenantId(this.tenants.get(i).getId());
						nowUse.setAccess(access);
					}
					hashMap.put(this.tenants.get(i).getId(),access);
				}
				//将用户信息存到session中
				session.setAttribute("userTengId",tokenId);
				session.setAttribute("userTenants",this.tenants);
				session.setAttribute("userAccess",hashMap);
				session.setAttribute("userCe",ce);
				session.setAttribute("nowUse",nowUse);
			} catch (Exception e) {
				//跳转界面
				session.setAttribute("msg","longinEcxp");
				basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/login.jsp";
				getHttpResponse().sendRedirect(basePath);
			}
			//跳转界面
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/index.jsp";
			getHttpResponse().sendRedirect(basePath);
		}
		return successInfo("ok");
	}

	/**
	 * 
	 * 方法用途和描述: 用户注销
	 * @return String
	 * @author lizl 新增日期：2012-11-27
	 * @author 你的姓名 修改日期：2012-11-27
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	public String loginOut() throws IOException, ServletException {
		HttpSession session = getHttpSession();
		HttpServletRequest request = getHttpRequest();// Struts2Util.getHttpRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
		getHttpResponse().sendRedirect(basePath);
		return NONE;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryAllUser() throws Exception{
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		List<User> queryuserLists = this.userService.queryAllUser(access.getToken().getId());
		Map map = new HashMap();
		map.put("rows",queryuserLists);
		map.put("total",queryuserLists.size());
		this.result = JSONObject.fromObject(map);//
		return successInfo("ok");
	}
	
	/**
	 * 
	 * 方法用途和描述: 切换项目
	 * @return String
	 * @author lizl 新增日期：2012-11-27
	 * @author 你的姓名 修改日期：2012-11-27
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	public String changeProject() {
		HttpSession session = getHttpSession();
		
		return successInfo("ok");
	}
	
	/**
	 * 
	 * 方法用途和描述:根据ID修改用户的信息
	 * @return String
	 * @author lizl 新增日期：2012-11-28
	 * @author 你的姓名 修改日期：2012-11-28
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	public String queryUserRoleById() throws Exception {
		log.info("userID:   "+this.usersid);
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		List<User> queryuserLists = this.userService.queryAllUser(access.getToken().getId());
		User user = new User();
		for(int i = 0;i < queryuserLists.size();i++) {
			if(this.usersid.equals(queryuserLists.get(i).getId())) {
				user = queryuserLists.get(i);
				break;
			}
		}
		this.model = this.userService.queryUserRoleById(user,access.getToken().getId(),tentidVo.getTenantId());
		Map map = new HashMap();
		map.put("uservo",model);
		this.result = JSONObject.fromObject(map);//
		log.info("End:   "+this.model.toString());
		return successInfo("null");
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
	public String submitUserInfo() throws Exception {
		System.out.println("username:    "+getModel().getUsername());
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.userService.submitUserInfo(access.getToken().getId(),getModel().getId(),getModel());
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo("null");
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据ID删除某个用户的信息
	 * @return String
	 * @author lizl 新增日期：2012-11-28
	 * @author 你的姓名 修改日期：2012-11-28
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since woyun
	 */
	public String deleteUserById() {
		log.info("userID:   "+this.usersid);
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.userService.deleteUserById(access.getToken().getId(),this.usersid);
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo("null");
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
	public String saveUserInfo() {
		System.out.println("username:    "+getModel().getUsername());
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.userService.saveUserInfo(access.getToken().getId(),getModel());
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo("null");
	}
	
	@Override
	@JSON(serialize=false)
	public CommonService getCommonService() {
		return userService;
	}

	@Override
	public void setModel(UserVo model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public UserVo getModel() {
		if(null == this.model){
			this.model = new UserVo();
		}
		return this.model;
	}
}
