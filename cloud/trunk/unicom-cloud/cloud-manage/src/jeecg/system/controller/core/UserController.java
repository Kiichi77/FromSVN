package jeecg.system.controller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @ClassName: UserController
 * @Description: TODO(用户管理处理类)
 * @author jeecg
 */
@Controller
@RequestMapping("/userController")
public class UserController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserController.class);

	private UserService userService;
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 菜单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "menu")
	public void menu(HttpServletRequest request, HttpServletResponse response) {
		SetListSort sort = new SetListSort();
		TSUser u = ResourceUtil.getSessionUserName();
		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet();// 已有权限菜单
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					loginActionlist.add(function);
				}
			}
		}
		List<TSFunction> bigActionlist = new ArrayList();// 一级权限菜单
		List<TSFunction> smailActionlist = new ArrayList();// 二级权限菜单
		if (loginActionlist.size() > 0) {
			for (TSFunction function : loginActionlist) {
				if (function.getFunctionLevel() == 0) {
					bigActionlist.add(function);
				} else if (function.getFunctionLevel() == 1) {
					smailActionlist.add(function);
				}
			}
		}
		// 菜单栏排序
		Collections.sort(bigActionlist, sort);
		Collections.sort(smailActionlist, sort);
		String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
		// request.setAttribute("loginMenu",logString);
		try {
			response.getWriter().write(logString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "user")
	public String user(HttpServletRequest request) {
		//----------------------------------------------------------------
		//update-begin--Author:Quainty  Date:20130529 for：用户管理，添加按照部门过滤的功能
		// 给部门查询条件中的下拉框准备数据
		String departsReplace = "";
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		for (TSDepart depart : departList) {
			if (departsReplace.length() > 0) {
				departsReplace += ",";
			}
			departsReplace += depart.getDepartname() + "_" + depart.getId();
		}
		request.setAttribute("departsReplace", departsReplace);
		//update-end--Author:Quainty  Date:20130529 for：用户管理，添加按照部门过滤的功能
		//----------------------------------------------------------------
		return "system/user/userList";
		//return "system/user/userList-search-demo";
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "userinfo")
	public String userinfo(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/userinfo";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "changepassword")
	public String changepassword(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/changepassword";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "savenewpwd")
	@ResponseBody
	public AjaxJson savenewpwd(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password"));
		String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			j.setSuccess(false);
		} else {
			try {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.updateEntitie(user);
			j.setMsg("修改成功");

		}
		return j;
	}

	/**
	 * 得到角色列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	@ResponseBody
	public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSRole> roles = new ArrayList();
		if (StringUtil.isNotEmpty(id)) {
			List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", id);
			if (roleUser.size() > 0) {
				for (TSRoleUser ru : roleUser) {
					roles.add(ru.getTSRole());
				}
			}
		}
		List<TSRole> roleList = systemService.getList(TSRole.class);
		comboBoxs = TagUtil.getComboBox(roleList, roles, comboBox);
		return comboBoxs;
	}

	/**
	 * 得到部门列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "depart")
	@ResponseBody
	public List<ComboBox> depart(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSDepart> departs = new ArrayList();
		if (StringUtil.isNotEmpty(id)) {
			TSUser user = systemService.get(TSUser.class, id);
			if (user.getTSDepart() != null) {
				TSDepart depart = systemService.get(TSDepart.class, user.getTSDepart().getId());
				departs.add(depart);
			}
		}
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		comboBoxs = TagUtil.getComboBox(departList, departs, comboBox);
		return comboBoxs;
	}

	/**
	 * easyuiAJAX用户列表请求数据 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		
		//----------------------------------------------------------------
		//update-begin--Author:Quainty  Date:20130522 for：用户管理，添加按照部门过滤的功能
		// 追加部门条件查询 (如果是input型，传的是部门名；如果是combo型，传的是部门ID)
		String departname = oConvertUtils.getString(request.getParameter("TSDepart_departname"));
		if (!StringUtil.isEmpty(departname)) {
			DetachedCriteria dc = cq.getDetachedCriteria();
			DetachedCriteria dcDepart = dc.createCriteria("TSDepart");
			//dcDepart.add(Restrictions.like("departname", "%" + departname + "%"));// 部门名
			dcDepart.add(Restrictions.eq("id", departname));// 部门ID
		}
		//update-end--Author:Quainty  Date:20130522 for：用户管理，添加按照部门过滤的功能
		//----------------------------------------------------------------
		
//		String searchfield = oConvertUtils.getString(request.getParameter("searchfield"));//传入字段名称
//		String value=oConvertUtils.getString(request.getParameter(searchfield));//传入值
//		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN };
//		cq.in("status", userstate);
//		if (searchfield != null) {
//			cq.like(searchfield, value);//匹配查询
//		}
//		cq.add();
		
		
		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN };
		cq.in("status", userstate);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 用户信息录入和更新
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSUser user, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		
		//----------------------------------------------------------------
		//update-begin--Author:shiyanping  Date:20130319 for：admin账户不能删除
		if("admin".equals(user.getUserName())){
			message = "超级管理员[admin]不可删除";
			j.setMsg(message);
			return j;
		}
		//update-end--Author:shiyanping  Date:20130319 for：admin账户不能删除
		//----------------------------------------------------------------
		user = systemService.getEntity(TSUser.class, user.getId());
		List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (!user.getStatus().equals(Globals.User_ADMIN)) {
			if (roleUser.size()>0) {
				// 删除用户时先删除用户和角色关系表
				delRoleUser(user);
				userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			} else {
				userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
			}
		} else {
			message = "超级管理员不可删除";
		}

		j.setMsg(message);
		return j;
	}

	public void delRoleUser(TSUser user) {
		// 同步删除用户角色关联表
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
	}
	/**
	 * 检查用户名
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkUser")
	@ResponseBody
	public ValidForm checkUser(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String userName=oConvertUtils.getString(request.getParameter("param"));
		String code=oConvertUtils.getString(request.getParameter("code"));
		List<TSUser> roles=systemService.findByProperty(TSUser.class,"userName",userName);
		if(roles.size()>0&&!code.equals(userName))
		{
			v.setInfo("用户名已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
		AjaxJson j = new AjaxJson();
		// 得到用户的角色
		String roleid = oConvertUtils.getString(req.getParameter("roleid"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(user.getId())) {
			TSUser users = systemService.getEntity(TSUser.class, user.getId());
			users.setEmail(user.getEmail());
			users.setOfficePhone(user.getOfficePhone());
			users.setMobilePhone(user.getMobilePhone());
			users.setTSDepart(user.getTSDepart());
			users.setRealName(user.getRealName());
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(user.getActivitiSync());
			systemService.updateEntitie(users);
			List<TSRoleUser> ru = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			systemService.deleteAllEntitie(ru);
			message = "用户: " + users.getUserName() + "更新成功";
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleUser(users, roleid);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",user.getUserName());
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
			} else {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
				if (user.getTSDepart().equals("")) {
					user.setTSDepart(null);
				}
				user.setStatus(Globals.User_Normal);
				systemService.save(user);
				message = "用户: " + user.getUserName() + "添加成功";
				if (StringUtil.isNotEmpty(roleid)) {
					saveRoleUser(user, roleid);
				}
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}

		}
		j.setMsg(message);

		return j;
	}

	protected void saveRoleUser(TSUser user, String roleidstr) {
		String[] roleids = roleidstr.split(",");
		for (int i = 0; i < roleids.length; i++) {
			TSRoleUser rUser = new TSRoleUser();
			TSRole role = systemService.getEntity(TSRole.class, roleids[i]);
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);

		}
	}

	/**
	 * 用户选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roles")
	public String roles() {
		return "system/user/users";
	}

	/**
	 * 角色显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridRole")
	public void datagridRole(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			req.setAttribute("user", user);
			idandname(req, user);
		}
		return new ModelAndView("system/user/user");

	}

	public void idandname(HttpServletRequest req, TSUser user) {
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String roleId = "";
		String roleName = "";
		if (roleUsers.size() > 0) {
			for (TSRoleUser tRoleUser : roleUsers) {
				roleId += tRoleUser.getTSRole().getId() + ",";
				roleName += tRoleUser.getTSRole().getRoleName() + ",";
			}
		}
		req.setAttribute("id", roleId);
		req.setAttribute("roleName", roleName);

	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "choose")
	public String choose(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/checkuser";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseUser")
	public String chooseUser(HttpServletRequest request) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		request.setAttribute("roleid", roleid);
		request.setAttribute("departid", departid);
		return "system/membership/userlist";
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridUser")
	public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if (departid.length() > 0) {
			cq.eq("TDepart.departid", oConvertUtils.getInt(departid, 0));
			cq.add();
		}
		String userid = "";
		if (roleid.length() > 0) {
			List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TRole.roleid", oConvertUtils.getInt(roleid, 0));
			if (roleUsers.size() > 0) {
				for (TSRoleUser tRoleUser : roleUsers) {
					userid += tRoleUser.getTSUser().getId() + ",";
				}
			}
			cq.in("userid", oConvertUtils.getInts(userid.split(",")));
			cq.add();
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "roleDepart")
	public String roleDepart(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/roledepart";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseDepart")
	public ModelAndView chooseDepart(HttpServletRequest request) {
		String nodeid = request.getParameter("nodeid");
		ModelAndView modelAndView = null;
		if (nodeid.equals("role")) {
			modelAndView = new ModelAndView("system/membership/users");
		} else {
			modelAndView = new ModelAndView("system/membership/departList");
		}
		return modelAndView;
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridDepart")
	public void datagridDepart(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 测试
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String jString = request.getParameter("_dt_json");
		DataTables dataTables = new DataTables(request);
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataTables);
		String username = request.getParameter("userName");
		if (username != null) {
			cq.like("userName", username);
			cq.add();
		}
		DataTableReturn dataTableReturn = systemService.getDataTableReturn(cq, true);
		TagUtil.datatable(response, dataTableReturn, "id,userName,mobilePhone,TSDepart_departname");
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "index")
	public String index() {
		return "bootstrap/main";
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "main")
	public String main() {
		return "bootstrap/test";
	}

	/**
	 * 测试
	 * 
	 * @return
	 */
	@RequestMapping(params = "testpage")
	public String testpage(HttpServletRequest request) {
		return "test/test";
	}

	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("system/user/usersign");
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "savesign", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson savesign(HttpServletRequest req) {
		UploadFile uploadFile = new UploadFile(req);
		String id = uploadFile.get("id");
		TSUser user = systemService.getEntity(TSUser.class, id);
		uploadFile.setRealPath("signatureFile");
		uploadFile.setCusPath("signature");
		uploadFile.setByteField("signature");
		uploadFile.setBasePath("resources");
		uploadFile.setRename(false);
		uploadFile.setObject(user);
		AjaxJson j = new AjaxJson();
		message = user.getUserName() + "设置签名成功";
		systemService.uploadFile(uploadFile);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}

	//----------------------------------------------------------------
	//update-begin--Author:wangyang  Date:20130331 for：组合查询测试
	//----------------------------------------------------------------
	/**
	 * 测试组合查询功能
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "testSearch")
	public void testSearch(TSUser user, HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if(user.getUserName()!=null){
			cq.like("userName", user.getUserName());
		}
		if(user.getRealName()!=null){
			cq.like("realName", user.getRealName());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	//----------------------------------------------------------------
	//update-end--Author:wangyang  Date:20130331 for：组合查询测试
	//----------------------------------------------------------------
}