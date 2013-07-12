package cn.com.kxcomm.woyun.web.util;

import java.util.Map;


import cn.com.kxcomm.common.util.BlankUtil;

/**
 * 
 * 功能描述:登录验证util类
 * <p>
 * 版权所有：
 * <p>
 * 
 * 
 */
public class AuthenticationUtil {
	
	static final public String ACCOUNT_SESSION_KEY = "currentUserAccount";

	static final public String VALIDATE_CODE_KEY = "validateCode";

	static final public String NAME_SESSION_KEY = "currentUserName";

	static final public String ID_SESSION_KEY = "currentUserId";
	
	static final public String AUTHENTICATION_URL = "authenticationUrl";

	@SuppressWarnings("unchecked")
	static public String getCurrentUserAccount(Map session) {
		return (String) session.get(ACCOUNT_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserAccount(Map session, String account) {
		session.put(ACCOUNT_SESSION_KEY, account);

	}

	@SuppressWarnings("unchecked")
	static public String getCurrentUserId(Map session) {
		return (String) session.get(ID_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserId(Map session, String id) {
		session.put(ID_SESSION_KEY, id);

	}

	@SuppressWarnings("unchecked")
	static public String getCurrentUserName(Map session) {
		return (String) session.get(NAME_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserName(Map session, String name) {
		session.put(NAME_SESSION_KEY, name);

	}

	@SuppressWarnings("unchecked")
	static public boolean isSessionActivated(Map session) {
		return !BlankUtil.isBlank(session.get(ACCOUNT_SESSION_KEY));
	}

	@SuppressWarnings("unchecked")
	static public void clearCurrentUserAccount(Map session) {
		session.clear();
	}

	@SuppressWarnings("unchecked")
	static public boolean validateCode(Map session, String validateCode) {
		return !BlankUtil.isBlank(validateCode)
				&& validateCode.equalsIgnoreCase((String) session
						.get(VALIDATE_CODE_KEY));

	}
	
	
}
