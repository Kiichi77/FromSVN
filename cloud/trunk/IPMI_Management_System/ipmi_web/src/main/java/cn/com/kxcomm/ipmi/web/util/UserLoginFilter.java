package cn.com.kxcomm.ipmi.web.util;

import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.json.Json;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.InitDatabase;
import cn.com.kxcomm.ipmi.entity.TbRightRole;
import cn.com.kxcomm.ipmi.vo.RightEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;

public class UserLoginFilter implements javax.servlet.Filter {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(UserLoginFilter.class);
	@SuppressWarnings("unused")
	private FilterConfig config;


	/**
	 * 不需要过滤的JSP页面文件名，在web.xml中配置
	 * 目前为登录界面
	 */
	private String logon_page;

	/**
	 * 登录页面文件名
	 */
	private String loginPageName;

	/**
	 * 登录提交action
	 */
	private String loginActionName;

	/**
	 * 用户登录后的主界面
	 */
	private String main_page;

	/**
	 * 无权限访问跳转后的页面
	 */
	private String error_page;

	/**
	 * 无需校验的页面地址通配符
	 */
	private String common_page_prefix;

	/**
	 * 无需校验的action列表，以","分割。
	 */
	private String common_action_rights;

	/**
	 * 无需校验的page列表，以","分割。
	 */
	private String common_page_rights;

	/**
	 * 验证码请求
	 */
	private String verificationCodeUrl;

	public static  String  DATA_RIGHT_NAME = "childrenRightList";
	public static String DATA_RIGHT_ADUIT_NAME = "aduit";
	public static String DATA_RIGHT_CREATOR_NAME="creator";
	public static String DATA_RIGHT_TAG_ID="parentId";


	public void destroy() {
		config = null;

	}

	public void init(FilterConfig filterconfig) throws ServletException {
		//初始化数据
		InitDatabase initDatabse = InitDatabase.getSingleInstance();
		initDatabse.writeData();
		// 从部署描述符中获取登录页面和首页的URI
		config = filterconfig;
		log.debug("config:" + config);
		logon_page = filterconfig.getInitParameter("logon_page");
		loginPageName = filterconfig.getInitParameter("loginPageName");
		loginActionName = filterconfig.getInitParameter("loginActionName");
		main_page = filterconfig.getInitParameter("main_page");
		error_page = filterconfig.getInitParameter("error_page");
		common_page_prefix = filterconfig.getInitParameter("common_page_prefix");
		common_action_rights = filterconfig.getInitParameter("common_action_rights");
		common_page_rights = filterconfig.getInitParameter("common_page_rights");
		verificationCodeUrl = filterconfig.getInitParameter("verificationCodeUrl");

		log.debug("[logon_page,loginPageName,loginActionName,main_page]:" + logon_page+","+loginPageName+","+loginActionName+","+main_page);
		if (null == logon_page||null==loginPageName) {
			throw new ServletException("没有找到登录页面...");
		}
	}

	/**
	 * 
	 * 方法用途和描述: 当用户登录后，判断用户访问的URl是否在公有权限列表中
	 * @param url
	 * @return true  or  false , if true:无需鉴权，放行访问。
	 * @author chenhui 新增日期：2010-6-29
	 * @author 你的姓名 修改日期：2010-6-29
	 * @since zte_ipmi_bi
	 */
	private boolean isInCommonRight(String url){
		boolean inCommonRight = false;
		try{
			if(BlankUtil.isBlank(url)){
				inCommonRight = false;
			}
			if(main_page.equals(url)||logon_page.equals(url)||error_page.equals(url)||url.startsWith(common_page_prefix)){

				log.debug(url+"页面无需鉴权.");
				inCommonRight = true;
			}
			if(!BlankUtil.isBlank(common_action_rights)){
				String[] actionAry = common_action_rights.split(",");
				if(!BlankUtil.isBlank(actionAry)){
					for(String action:actionAry){
						if(url.startsWith(action)){

							log.debug(url+",action无需鉴权.");
							inCommonRight = true;
							break;
						}
					}
				}
			}
			if(!BlankUtil.isBlank(common_page_rights)){
				String[] pageAry = common_page_rights.split(",");
				if(!BlankUtil.isBlank(pageAry)){
					for(String page:pageAry){
						if(url.startsWith(page)){

							log.debug(url+",page无需鉴权.");
							inCommonRight = true;
							break;
						}
					}
				}
			}else{

			}
		}catch(Exception ex){
			log.error("公有权限对比异常:",ex);
		}

		return inCommonRight;
	}

	/**
	 * 系统用户访问控制过滤器
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		javax.servlet.http.HttpSession session = req.getSession();

		try {
			req.setCharacterEncoding("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//		获取用户登录信息及授权的session
		UserRightEntity tbUser = (UserRightEntity)session.getAttribute(SessionUtils.USER);
		if(tbUser!=null){
			log.debug("[用户登录后的session信息实体tbUser:]:" + tbUser.toString());		
		}
        
		String request_uri = req.getRequestURI();// 得到用户请求的URI
		log.debug("rquest_uri"+request_uri);
		String ctxPath = req.getContextPath();// 得到web应用程序的上下文路径
		log.debug("ctxPath"+ctxPath);
		String url = request_uri.substring(ctxPath.length()); // 去除上下文路径，得到剩余部分的路径

		log.debug("[=========url=========]:"+url);
		try{
			log.debug("进入过滤器"+tbUser);
			//1 用户session不存在，即用户未登录
			if(tbUser == null){
				//1-1  用户访问登录界面或者提交登录请求
				if(url.equals(loginActionName)||request_uri.equals(ctxPath+"/")|| url.equals(verificationCodeUrl)){
					chain.doFilter(request, response);// 放行，让用户进入登录流程
				}else{
					//1-2 非登录请求，重定向到首页的登录界面
					java.io.PrintWriter out = response.getWriter();   
					out.println("<html>");   
					out.println("<script>");
					out.println("window.open('"+ctxPath+"','_top');");   
					out.println("</script>");   
					out.println("</html>");   
					out.close();
					//rsp.sendRedirect(ctxPath+ logon_page);
					log.debug(url+",session不存在或已丢失，重定向到:"+ctxPath);
					return;
				}
				//2 用户session存在	,流程：将企图访问uri与用户session中的授权信息进行验证 
			}else{
				log.debug("用户session存在，进行访问与授权对比,访问url:"+url);
				List<RightEntity> rightList = tbUser.getUserRightList();
				log.debug("权限列表rightList"+rightList.size());
				//2-1 拥有访问权限，继续访问 
				boolean flag =  AuthenticationUtil.isAuthorized(url,rightList);
				if(isInCommonRight(url)||flag){
					chain.doFilter(request, response);
				}else{ //2-2 无权访问，重定向到登录后的主界面
					log.debug("当前访问:rul:"+url+"非法访问，重定向地址:"+ctxPath+ error_page);
					req.getRequestDispatcher(error_page).forward(req, rsp);
					return;
				}
			}

		}catch(Exception e){
			log.error("过滤器异常:",e);
		}

	}

	/**
	 * 
	 * 方法用途和描述: 从request中获取属性值
	 * @param req 
	 * @param name request中的属性名
	 * @return -1:没有获取到，其他值：对应的属性值
	 * @author libu 新增日期：2011-5-7
	 * @author 你的姓名 修改日期：2011-5-7
	 * @since zte_ipmi_bi
	 */
	private int getRequestIntValue(HttpServletRequest req,String name){
		int returnValue = -1;
		String value = req.getParameter(name);
		if(value != null){
			returnValue = Integer.parseInt(value);
		}else{
			Integer attributeValue = (Integer)req.getAttribute(name);
			returnValue = attributeValue == null ? -1 : attributeValue;
		}
		return returnValue;
	}

	/**
	 * 
	 * 方法用途和描述: 从request中获取属性值
	 * @param req
	 * @param name request中的属性名
	 * @return  属性值，如果没有则返回null
	 * @author libu 新增日期：2011-5-7
	 * @author 你的姓名 修改日期：2011-5-7
	 * @since zte_ipmi_bi
	 */
	public String getRequestStringValue(HttpServletRequest req,String name){
		String returnValue = req.getParameter(name);
		if(returnValue == null){
			String attributeValue = (String)req.getAttribute(name);
			returnValue = attributeValue;
		}
		return returnValue;
	}

	public void setCommon_page_prefix(String common_page_prefix) {
		this.common_page_prefix = common_page_prefix;
	}

	public String getCommon_page_prefix() {
		return common_page_prefix;
	}

	public void setError_page(String error_page) {
		this.error_page = error_page;
	}

	public String getError_page() {
		return error_page;
	}

	public void setCommon_action_rights(String common_action_rights) {
		this.common_action_rights = common_action_rights;
	}

	public String getCommon_action_rights() {
		return common_action_rights;
	}

	public void setCommon_page_rights(String common_page_rights) {
		this.common_page_rights = common_page_rights;
	}

	public String getCommon_page_rights() {
		return common_page_rights;
	}
}