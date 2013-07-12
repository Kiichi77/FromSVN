package cn.com.kxcomm.contractmanage.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionUtils {

	private static Log log = LogFactory.getLog(SessionUtils.class);
	public static final String USER_NAME = "USER_NAME";   //用户名
	public static final String USER_ID = "USER_ID";   //用户id
	public static final String USER_ROLE_ID = "USER_ROLE_ID";  //用户角色id
	public static final String USER = "USER";    //用户权限实体对象--UserRightEntity
	public static final String USER_RIGHTS = "USER_RIGHTS";  //用户权限
	public static final String REAL_NAME = "REAL_NAME";  //用户真实名
	public static final String VERIFICATION_CODE = "VERIFICATION_CODE"; //验证码
}
