package cn.com.kxcomm.contractmanage.web.action;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.service.ICommonService;

/**
 * 
 * @author Zhaobd
 * 简单权限验证,直接写死
 */
public class SimpleLoginAction extends BaseAction<TbQuotations, String> {
	private String user;
	private String password;
	private String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ICommonService getCommonService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbQuotations getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(TbQuotations model) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIds(String[] ids) {
		// TODO Auto-generated method stub

	}
	public String login(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(user.equals("kangxun")&&password.equals("kangxun@1234")){
            session.setAttribute("login", 1);
            msg="ok";
		}else{
			log.info("login fail...");
			msg="no";
		}
		return SUCCESS;
	}
}
