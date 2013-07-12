package org.jeecgframework.core.interceptors;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.system.pojo.base.TSUser;
import jeecg.system.pojo.base.TSVersion;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.SessionInfo;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * 权限拦截器
 * 
 * @author
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	private SystemService systemService;
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		HttpSession session = ContextHolderUtils.getSession();
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
		if (excludeUrls.contains(requestPath)) {
			return true;
		} else {
			if (sessioninfo != null) {
				return true;
			} else {
				//update-begin--Author:anchao  Date:20130325 for：session 失效跳转
				//forword(request);
				//update-end--Author:anchao  Date:20130325 for：session 失效跳转
				//update-begin--Author:zhaojunfu  Date:20130330 for：session 失效跳转(要进行2次跳转，才能将主页面一起跳转)
				forward(request, response);
				//update-end--Author:zhaojunfu  Date:20130330 for：session 失效跳转(要进行2次跳转，才能将主页面一起跳转)
				
				return false;
			}

		}
		// String requestPath = ContextHolderUtils.getRequestPath(request);// 用户访问的资源地址
		/*
		 * if (sessionInfo == null) {// 没有登录系统，或登录超时 forward("您没有登录或登录超时，请重新登录！", request, response); return false; }
		 */
		/*
		 * String requestPath = ContextHolderUtils.getRequestPath(request);// 用户访问的资源地址
		 * 
		 * List<TFunction> functions=systemService.findByProperty(TFunction.class,"functionurl",requestPath); if ( functions.size()<1) { forward("请修复数据库！数据库缺失【" + requestPath + "】资源！", request, response); return false; } TUser user = sessionInfo.getUser();
		 * 
		 * // 验证当前用户是否有权限访问此资源 List<TFunction> loginActionlist = new ArrayList();//已有权限菜单 List<TRoleUser> TRoleUsers=user.getTRoleUsers(); for (TRoleUser ru:TRoleUsers) { TRole role =ru.getTRole(); Set<TRoleFunction> roleFunctionList=role.getTRoleFunctions(); if(roleFunctionList.size()>0){ for(TRoleFunction roleFunction :roleFunctionList) { TFunction function=(TFunction)roleFunction.getTFunction(); loginActionlist.add(function); } } } boolean b=false; if (loginActionlist.size()>0) { for (TFunction f:loginActionlist) { if(f.getFunctionurl().equals(requestPath)){ b = true; break; } } if (b) { return true;// 当前访问资源地址是不需要验证的资源 } }else{ forward("您没有【" + requestPath + "】权限，请联系管理员给您赋予相应权限！", request, response); return false; }
		 */
	}

	/**
	 * 转发
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {

		return new ModelAndView(new RedirectView("loginController.do?login"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update-begin--Author:zhaojunfu  Date:20130330 for：session 失效跳转(要进行2次跳转，才能将主页面一起跳转)
		request.getRequestDispatcher("webpage/login/timeout.jsp").forward(request, response);
		//update-end--Author:zhaojunfu  Date:20130330 for：session 失效跳转(要进行2次跳转，才能将主页面一起跳转)
	}

}
