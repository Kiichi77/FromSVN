package jeecg.system.controller.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jeecg.system.pojo.base.TSConfig;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.pojo.base.TSVersion;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.jeecgframework.core.common.model.common.SessionInfo;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * 登陆初始化控制器
 * 
 * 
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
	private SystemService systemService;
	private UserService userService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {

		this.userService = userService;
	}
	
	
	@RequestMapping(params = "goPwdInit")
	public String goPwdInit() {
		return "login/pwd_init";
	}
	
	/**
	 * admin账户密码初始化
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		TSUser user = new TSUser();
		user.setUserName("admin");
		String newPwd = "123456";
		userService.pwdInit(user,newPwd);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}
	

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(TSUser user, HttpServletRequest req) {
		HttpSession session = ContextHolderUtils.getSession();
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		AjaxJson j = new AjaxJson();
		TSUser u = userService.checkUserExits(user);
		if (u != null) {
			//update-begin----author:zhangdaihao ------- date:20130318-------for:注释掉U盾的校验
			//if (user.getUserKey().equals(u.getUserKey())) {
			if (true) {
			//update-begin----author:zhangdaihao ------- date:20130318-------for:注释掉U盾的校验
				message = "用户: " + user.getUserName() + "登录成功";
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo.setUser(u);
				session.setMaxInactiveInterval(60 * 30);
				session.setAttribute(Globals.USER_SESSION, sessionInfo);
				// 添加登陆日志
				systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);

			} else {
				j.setMsg("请检查U盾是否正确");
				j.setSuccess(false);
			}
		} else {
			j.setMsg("用户名或密码错误!");
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "login")
	public String login(HttpServletRequest request) {
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		TSUser user = ResourceUtil.getSessionUserName();
		String roles = "";
		if (user != null) {
			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleName() + ",";
			}
			request.setAttribute("roleName", roles);
			request.setAttribute("userName", user.getRealName());
			return "main/main";
		} else {
			return "login/login";
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelAndView = null;

		HttpSession session = ContextHolderUtils.getSession();
		String versionCode = oConvertUtils.getString(request.getParameter("versionCode"));
		TSUser user= ResourceUtil.getSessionUserName();
		// 根据版本编码获取当前软件版本信息
		TSVersion version = systemService.findUniqueByProperty(TSVersion.class, "versionCode", versionCode);
		
		//update-begin--Author:chenxu  Date:20130322 for：左侧菜单信息放入到session中
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			session.removeAttribute(role.getId());
		}
		//update-end--Author:chenxu  Date:20130322 for：左侧菜单信息放入到session中
		
		// 判断用户是否为空不为空则清空session中的用户object
		session.removeAttribute(Globals.USER_SESSION);// 注销该操作用户
		systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));

		return modelAndView;
	}
	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		String roles = "";
		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
		//update-begin--Author:chenxu  Date:20130322 for：左侧菜单信息放入到session中
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			roles += role.getRoleName() + ",";
			List<TSRoleFunction> roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
			if (roleFunctionList == null) {
				HttpSession session = ContextHolderUtils.getSession();
				session.setMaxInactiveInterval(60 * 30);
				roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
				session.setAttribute(role.getId(), roleFunctionList);
			}
			for (TSRoleFunction roleFunction : roleFunctionList) {
				TSFunction function = (TSFunction) roleFunction.getTSFunction();
				loginActionlist.add(function);
			}
		}
		//update-end--Author:chenxu  Date:20130322 for：左侧菜单信息放入到session中
		
			List<TSFunction> bigActionlist = new ArrayList<TSFunction>();// 一级权限菜单
			List<TSFunction> smailActionlist = new ArrayList<TSFunction>();// 二级权限菜单
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
			Collections.sort(bigActionlist, new NumberComparator());
			Collections.sort(smailActionlist, new NumberComparator());
			String logString = ListtoMenu.getEasyuiMenu(bigActionlist, smailActionlist);
			request.setAttribute("loginMenu", logString);
			request.setAttribute("parentFun", bigActionlist);
			request.setAttribute("roleName", roles);
			request.setAttribute("userName", user.getRealName());
			request.setAttribute("childFun", smailActionlist);
			request.setAttribute("userName", user.getRealName());
			List<TSConfig> configs = userService.loadAll(TSConfig.class);
			for (TSConfig tsConfig : configs) {

				request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
			}
			return new ModelAndView("main/left");
	}
	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
			return new ModelAndView("main/home");
	}


}
