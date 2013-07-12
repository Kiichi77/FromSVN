package cn.com.kxcomm.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 功能描述:验证用户登录拦截器
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author zhaoj 新增日期：2008-11-14
 * @author 你的姓名 修改日期：2008-11-14
 * @since wapportal_manager version(2.0)
 */

@Controller
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4994877859122219609L;

	final static public String TIMEOUT = "timeout";

	private static Logger log = Logger.getLogger(BaseAction.class);

	/**
	 * 
	 * 方法用途和描述: 数据权限拦截器，将获取到的数据权限设置给action
	 * @author dongshen
	 * @return
	 * @since dongshen
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		log.debug("---AuthenticationInterceptor----" + invocation.getAction()
				+ "!" + invocation.getResultCode());

		Object action = invocation.getAction();
		if(action instanceof BaseAction){
			@SuppressWarnings("rawtypes")
			BaseAction baseAction = (BaseAction)action;
			HttpServletRequest request = (HttpServletRequest)actionContext.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
			HttpSession session = request.getSession();
			UserRightEntity tbUser = (UserRightEntity)session.getAttribute(SessionUtils.USER);//SessionUtils.getUser();	
			if(tbUser == null)
				return invocation.invoke();
			//将登录用户账号回传给页面
			baseAction.setAdminname(tbUser.getUserEntity().getAccouont());
			baseAction.setAdminrole(tbUser.getUserEntity().getTbRole().getNote());
		}
		log.debug("拦截器通过!");
		return invocation.invoke();
	}

}
