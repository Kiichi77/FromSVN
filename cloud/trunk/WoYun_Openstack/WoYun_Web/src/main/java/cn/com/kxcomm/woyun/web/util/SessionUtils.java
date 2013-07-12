package cn.com.kxcomm.woyun.web.util;

public class SessionUtils {

	public static final String USER_ACCESS = "userAccess";   //第二次登陆验证之后的access验证对象
	public static final String TENANTS_LIST = "userTenants";   //第一验证之后查看租户，获取的租户对象
	public static final String NOW_USER = "nowUse"; //当前的租户id
	public static final String VERIFICATION_CODE = "VERIFICATION_CODE"; //验证码
}
