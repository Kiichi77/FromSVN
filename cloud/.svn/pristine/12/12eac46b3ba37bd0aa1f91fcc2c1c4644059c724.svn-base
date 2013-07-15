package cn.com.kxcomm.contractmanage.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.base.vo.RightEntity;
import cn.com.kxcomm.base.vo.UserRightEntity;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.MD5;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbJobPlace;
import cn.com.kxcomm.contractmanage.entity.TbJobduties;
import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbTaskUrl;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.IUserService;
import cn.com.kxcomm.contractmanage.service.impl.TaskUrlService;
import cn.com.kxcomm.contractmanage.vo.RoleVo;
import cn.com.kxcomm.contractmanage.vo.UserVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

import com.googlecode.jsonplugin.annotations.JSON;

public class UserAction extends BaseAction<TbUser, String> {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	private String uid;
	private String roleid;
	private String userTaskUrlid;
    private String oldPasswd;
    
    private String status; //合同模板状态
    private String checkResult; //是否通过，0通过，1不通过
    
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldPasswd() {
	return oldPasswd;
}

public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

public void setOldPasswd(String oldPasswd) {
	this.oldPasswd = oldPasswd;
}

	public String getUserTaskUrlid() {
		return userTaskUrlid;
	}

	public void setUserTaskUrlid(String userTaskUrlid) {
		this.userTaskUrlid = userTaskUrlid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	} 


	@Autowired(required=true)
	public IUserService userService;
	@Autowired(required=true)
	public TaskUrlService taskUrlService;
	public List<MenuEntity> menulist;

	public List<MenuEntity> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<MenuEntity> menulist) {
		this.menulist = menulist;
	}

	public TbRole roleEntity; //角色实体
	public List<TbRightRole> rightRoleList; //权限列表
	public String verificationCode;  //验证码
	public UserRightEntity userRightEntity; //用户权限信息（访问控制用）
	public List<Hashtable<String, String>> listMenu; //前台菜单数据结构

	public List<Hashtable<String, String>> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<Hashtable<String, String>> listMenu) {
		this.listMenu = listMenu;
	}

	public UserRightEntity getUserRightEntity() {
		return userRightEntity;
	}

	public void setUserRightEntity(UserRightEntity userRightEntity) {
		this.userRightEntity = userRightEntity;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public TbRole getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(TbRole roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<TbRightRole> getRightRoleList() {
		return rightRoleList;
	}

	public void setRightRoleList(List<TbRightRole> rightRoleList) {
		this.rightRoleList = rightRoleList;
	}


	/**
	 * 
	 * 方法用途和描述: 获取用户登录后的菜单信息
	 * @return
	 * @author chenhui 新增日期：2010-6-23
	 * @author 你的姓名 修改日期：2010-6-23
	 * @since zte_ipmi_bi
	 */
	public String queryMenuById() {
		HttpSession session = getHttpSession();
		UserRightEntity ueInSession = (UserRightEntity) session.getAttribute(SessionUtils.USER);

		if(ueInSession==null){
			return successInfoStr("error");
		}else{
			model = ueInSession.getUserEntity();
			String path = getHttpRequest().getContextPath(); //Struts2Util.getHttpRequest().getContextPath();
			listMenu = new ArrayList<Hashtable<String,String>>();
			menulist = ueInSession.getMenuList();
		}
		this.success = true;		
		this.msg = "操作成功";
		return "menu";
	}
	/**
	 * 
	 * 方法用途和描述: 获取用户登录后的用户信息
	 * @return
	 * @author chenliang 新增日期：2010-6-24
	 * @author 你的姓名 修改日期：2010-6-24
	 * @since zte_ipmi_bi
	 */
	public String queryConsumerEntity() {
		UserRightEntity userInSession = (UserRightEntity) getHttpSession().getAttribute(SessionUtils.USER);//SessionUtils.getUser();

		if(userInSession==null){
			msg="error"; return SUCCESS;
		}else{
			model = userInSession.getUserEntity();	
		}
		msg="ok"; return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 用户登录
	 * @return
	 * @author zbd 新增日期：2012-11-22
	 * @author lizl 修改日期：2013-1-21
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @since ContractManage
	 */
	public String login() throws SQLException, ServletException, IOException {
		this.success = true;
		HttpSession session = getHttpSession();
		//		log.debug("session verificationCode:"+session.getAttribute(SessionUtils.VERIFICATION_CODE));
		if (null == getModel().getAccouont() || null == getModel().getPassword()) {
			log.debug("输入的账号，密码为空，登录失败...");
			//			msg="error";
			msg="error"; return SUCCESS;
		} 
		String passwd = MD5.toMD5(getModel().getPassword());
		log.debug("[after MD5,account,passwd]:" +  getModel().getAccouont()+","+getModel().getPassword());
		TbUser ce = null;
		try {
			log.debug("[=============userService=============]"+userService);
			ce = userService.checkLogin(getModel().getAccouont());
		} catch (Exception ex) {
			log.error("checkLogin() Error:", ex);
		}
		log.debug("[in method login(),ce]:" + ce);
		if (null == ce) {
			log.debug("用户名不存在,登陆失败...");
			return successInfoStr("usererror");
			//			return failInfo("error");
		}
		if(ce.getPassword().equals(passwd)){
			userRightEntity = new UserRightEntity();
			userRightEntity.setUserEntity(ce);
			//将用户id信息放入session
			//			SessionUtils.setUserId(ce.getId());
			session.setAttribute(SessionUtils.USER_ID, ce.getId());
			// 1、 根据用户登录账号，查询用户角色
			TbRole roleEntity = userService.queryConsumerRole(ce.getId());
			log.debug("[用户角色列表roleEntity]:" + roleEntity);
			// 用户具有角色信息
			if (roleEntity != null) {				
				Long roleId = roleEntity.getId();
				//将用户角色id信息放入session
				//				SessionUtils.setUserRoleId(roleId);
				session.setAttribute(SessionUtils.USER_ROLE_ID, roleId);
				log.debug("[用户具有角色信息roleId]:" + roleId);

				//2、根据角色ID，获取角色具有的访问权限信息（功能level为1和2，和3的）
				List<RightEntity> rightList = userService.queryRoleRightListByRoleId(roleId);
				//				SessionUtils.setUserRights(rightList);
				session.setAttribute(SessionUtils.USER_RIGHTS, rightList);
				log.debug("[用户具有的角色访问权限信息****rightList size****]"+rightList.size()+"[rightlist]"+Json.toJson(rightList));

				//3、 根据角色ID，获取角色具有的访问权限信息（菜单）
				List<MenuEntity> menuList =userService.queryMenuEntityByRoleId(roleId);
				log.debug("[具有权限访问的菜单列表****menuList size****]"+menuList.size()+"[menulist]"+Json.toJson(menuList));

				userRightEntity.setUserRightList(rightList);
				userRightEntity.setMenuList(menuList);

			} else { // 用户不具有任何角色
				log.debug("不具备任何角色" + roleEntity);
			}
			// 4、 将用户授权信息放入session
			log.debug("将用户授权信息放入session,userRightEntity:"+userRightEntity);
			session.setAttribute(SessionUtils.USER,userRightEntity);
			session.setAttribute("username", userRightEntity.getUserEntity().getAccouont());
			try{
				//				TbSystemoperlog sysLog = new TbSystemoperlog();
				//				sysLog.setTbUser(ce);   // 用户账号
				//				sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
				//				sysLog.setOperationType(BusinessConstants.syslog_login); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
				//				sysLog.setParam("用户："+ce.getAccouont()); // 操作参数说明:一般为操作记录的key
				//				sysLog.setNote(ce.getAccouont()+"登陆");      // 备注:一般为添加的数据项名称	
				//				sysLog.setDateTime(new Timestamp(new Date().getTime()));	
				//				this.addSystemLog(sysLog);
			}catch(Exception ex){
				log.error("syslogError:",ex);
			}
			msg="ok"; 
		} else {
			msg = "passworderror";
		}
		this.result = JSONObject.fromObject(successInfo(msg));
		return SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * 方法用途和描述: 用户注销
	 * @return
	 * @author chenhui 新增日期：2010-6-23
	 * @author 你的姓名 修改日期：2010-6-23
	 * @throws IOException 
	 * @throws ServletException 
	 * @since zte_ipmi_bi
	 */
	public String loginOut() throws IOException, ServletException {
		HttpSession session = getHttpSession();
		UserRightEntity ceInSession = (UserRightEntity) session.getAttribute(SessionUtils.USER);
		if (null != ceInSession) {
			String account = ceInSession.getUserEntity().getAccouont();
			session.removeAttribute(SessionUtils.USER);
			ceInSession = null;
			log.debug("用户" + account + "成功退出系统");
		}
		HttpServletRequest request = getHttpRequest();// Struts2Util.getHttpRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path;
		getHttpResponse().sendRedirect(basePath);
		return NONE;
	}

	/**
	 * 旧密码验证
	 * @return
	 */
	public String checkPasswd(){
		String str = "ok";
		try{		
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			Long userId = entity.getUserEntity().getId();
			String oldPasswd = getModel().getPassword();
			boolean flag = userService.checkPasswd(userId, oldPasswd);
			if(flag){
				str = "ok";
			}else{
				str = "no";
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return successInfoStr(str);
	}

	/**
	 * 修改密码
	 * @return
	 */
	public String updatePasswd(){
		HttpSession session = getHttpSession();
		UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
		Long userId = entity.getUserEntity().getId();
		log.debug("userId:"+userId+"  getPassword"+getModel().getPassword());
		userService.updatePasswd(userId,getModel().getPassword());
		try{
			//			TbSystemoperlog sysLog = new TbSystemoperlog();
			//			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			//			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			//			sysLog.setOperationType(BusinessConstants.syslog_login); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			//			sysLog.setParam(entity.getUserEntity().getAccouont()); // 操作参数说明:一般为操作记录的key
			//			sysLog.setNote(entity.getUserEntity().getAccouont()+"修改密码");      // 备注:一般为添加的数据项名称	
			//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			//			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}

	/**
	 * 密码初始化
	 * @return
	 */
	public String initPasswd(){
		HttpSession session = getHttpSession();
		Long userIdLong = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try{
		Long userId = getModel().getId();
		log.debug("initPasswd id:"+userId);
		userService.initPasswd(userId);
		systemLogService.writeTheLog(SystemLogType.initialize_password_user, userIdLong, "成功", null, "用户ID为:"+userId);
			//			HttpSession session = getHttpSession();
			//			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			//			TbSystemoperlog sysLog = new TbSystemoperlog();
			//			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			//			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			//			sysLog.setOperationType(BusinessConstants.syslog_login); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			//			sysLog.setParam(entity.getUserEntity().getAccouont()); // 操作参数说明:一般为操作记录的key
			//			sysLog.setNote("初始化密码");      // 备注:一般为添加的数据项名称		
			//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			//			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("initPasswd:",ex);
			try {
				systemLogService.writeTheLog(SystemLogType.initialize_password_user, userIdLong, "异常", ex.getMessage(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}

	public String delete(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try{
		log.debug("delete id:" + uid);
		String[] userIds = uid.split(",");
		String idstr="";
		int i=0;
		Long[] idslong=new Long[userIds.length];
		for(String id:userIds){
			TbUser user = userService.getByPk(Long.parseLong(id));
			if(i>0)
				idstr+=",";
			idslong[i]=Long.parseLong(id);
			i++;
			if(user.getAccouont().equals("admin"))
				return successInfoStr("系统默认管理员不允许删除,操作回滚!"); 
			idstr+=id;
			systemLogService.writeTheLog(SystemLogType.del_user, userId, "成功", null, "用户ID为:"+uid);
		}
		userService.deleteAllByPks(idslong);
			//			HttpSession session = getHttpSession();
			//			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			//			TbSystemoperlog sysLog = new TbSystemoperlog();
			//			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			//			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			//			sysLog.setOperationType(BusinessConstants.syslog_delete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			//			sysLog.setParam(idstr); // 操作参数说明:一般为操作记录的key
			//			sysLog.setNote("删除用户");      // 备注:一般为添加的数据项名称			
			//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			//			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
			try {
				systemLogService.writeTheLog(SystemLogType.del_user, userId, "异常", ex.getMessage(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}



	/**
	 * 检查账户名是否存在
	 * @return
	 */
	public String checkAccount() {
		String str = "ok";
		String accouont = getModel().getAccouont();
		boolean temp = userService.checkAccount(accouont);
		if (!temp) {
			str = "ok";
		} else {
			str = "no";
		}
		return successInfoStr(str);
	}

	/**
	 * 根据用户id查找对应的角色
	 * @return
	 * @throws Exception
	 */
	public String queryConsumerRole() throws Exception {
		log.debug("getModel.id:"+getModel().getId());
		this.roleEntity = userService.queryConsumerRole(getModel().getId());
		return successInfoStr(null);
	}

	/**
	 * 
	 * 方法用途和描述:根据主键查询对象
	 * @return
	 * @author chenl 新增日期：2012-8-8
	 * @since ipmi_web
	 */
	public String findById(){
		log.debug("model:"+getModel().toString());
		model = userService.getByPk(getModel().getId());
		UserVo vo = new UserVo();
		EntityToVoUtil.copyObjValue(model, vo);
		RoleVo rolevo = new RoleVo();
		rolevo.setId(model.getTbRole().getId());
		rolevo.setRolename(model.getTbRole().getRolename());
		vo.setJobDuties(model.getJobDuties());
		vo.setJobPlace(model.getJobPlace());
		vo.setTbRole(rolevo);
		Map map = new HashMap();
		map.put("userEntity",vo);
		map.put("msg", "ok");
		this.result = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * Action通用函数,新增Entity,保存.参数为model
	 */
	public String saveUser() throws Exception {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try{
		log.debug("saveUser model:"+getModel().toString());
		MD5.toMD5(getModel().getPassword());
		TbUser tbUser=new TbUser();
		tbUser.setPassword(MD5.toMD5(model.getPassword()));
		tbUser.setPhoneNumber(model.getPhoneNumber());
		tbUser.setEmail(model.getEmail());
		tbUser.setUserName(model.getUserName());
		tbUser.setAccouont(model.getAccouont());
		tbUser.setNote(model.getNote());
		TbRole t=new TbRole();
		log.debug("roleid:"+roleid);
		t.setId(Long.parseLong(roleid));
		tbUser.setTbRole(t);
		tbUser.setIsInterface(model.getIsInterface());
		TbJobduties jobduties = new TbJobduties();
		jobduties.setId(model.getJobDuties().getId());
		tbUser.setJobDuties(jobduties);
		TbJobPlace jobplace = new TbJobPlace();
		jobplace.setId(model.getJobPlace().getId());
		tbUser.setJobPlace(jobplace);
		userService.save(tbUser);
		systemLogService.writeTheLog(SystemLogType.add_user, userId, "成功", null, "用户ID为"+model.getId());
			//			HttpSession session = getHttpSession();
			//			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			//			TbSystemoperlog sysLog = new TbSystemoperlog();
			//			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			//			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			//			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			//			sysLog.setParam("用户id:"+tbUser.getAccouont()+",角色id:"+roleid); // 操作参数说明:一般为操作记录的key
			//			sysLog.setNote("保存用户");      // 备注:一般为添加的数据项名称	
			//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			//			log.debug("save user systemLogOper "+sysLog.toString());
			//			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
			systemLogService.writeTheLog(SystemLogType.add_user, userId, "异常", ex.getMessage(), null);
		}
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}

	public String updateUser(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try{
		log.debug("updateUser model:"+getModel());
		TbUser tbUser=this.userService.getByPk(model.getId());
		tbUser.setPhoneNumber(model.getPhoneNumber());
		tbUser.setEmail(model.getEmail());
		tbUser.setUserName(model.getUserName());
		tbUser.setAccouont(model.getAccouont());
		tbUser.setNote(model.getNote());
		tbUser.setJobDuties(model.getJobDuties());
		tbUser.setJobPlace(model.getJobPlace());
		TbRole t=new TbRole();
		t.setId(Long.parseLong(roleid));
		tbUser.setTbRole(t);
		tbUser.setIsInterface(model.getIsInterface());
		TbJobduties jobduties = new TbJobduties();
		jobduties.setId(model.getJobDuties().getId());
		tbUser.setJobDuties(jobduties);
		TbJobPlace jobplace = new TbJobPlace();
		jobplace.setId(model.getJobPlace().getId());
		tbUser.setJobPlace(jobplace);
		userService.update(tbUser);
		systemLogService.writeTheLog(SystemLogType.edit_user, userId, "成功", null, "用户ID为："+model.getId());
			//			HttpSession session = getHttpSession();
			//			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			//			TbSystemoperlog sysLog = new TbSystemoperlog();
			//			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			//			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			//			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			//			sysLog.setParam("用户id:"+tbUser.getAccouont()+",角色id:"+roleid); // 操作参数说明:一般为操作记录的key
			//			sysLog.setNote("修改用户");      // 备注:一般为添加的数据项名称		
			//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			//			log.debug("update user systemLogOper "+sysLog.toString());
			//			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
			try {
				systemLogService.writeTheLog(SystemLogType.edit_user, userId, "异常", ex.getMessage(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	public String updateInfo()
	{
		Long userId=model.getId();
		boolean flag = userService.checkPasswd(userId, oldPasswd);
		if(flag){
			log.debug("updateUser model:"+getModel());
			TbUser tbUser=this.userService.getByPk(model.getId());
			tbUser.setPhoneNumber(model.getPhoneNumber());
			tbUser.setEmail(model.getEmail());
			tbUser.setUserName(model.getUserName());
			tbUser.setAccouont(model.getAccouont());
			tbUser.setNote(model.getNote());
			TbRole t=new TbRole();
			t.setId(Long.parseLong(roleid));
			tbUser.setTbRole(t);
			userService.update(tbUser);
			userService.updatePasswd(model.getId(),getModel().getPassword());
			result = JSONObject.fromObject(successInfo("ok"));
			return SUCCESS;
		}else{
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 用户分页查询列表
	 * @return
	 * @author chenliang 新增日期：2013-2-18
	 * @since ContractManage
	 */
	public String listUser(){
		try {
			System.out.println("用户查出来了不咯！真滴有味啦！"+model);
			if(null==model){
				model = new TbUser();
			}
			PageInfo<TbUser> page = new PageInfo<TbUser>(ServletActionContext.getRequest());
			PageInfo<UserVo> userList = userService.queryPage(model, page);
			this.result = JSONObject.fromObject(userList);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("listConfigModels ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 用户分页查询列表
	 * @return
	 * @author chenliang 新增日期：2013-2-18
	 * @since ContractManage
	 */
	public String findAll(){
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List<TbUser> list = new ArrayList<TbUser>();
			list=userService.findAll();
			List<UserVo> volist = new ArrayList<UserVo>();
			for (TbUser user : list) {
				UserVo vo = new UserVo();
				EntityToVoUtil.copyObjValue(user, vo);
				RoleVo rolevo = new RoleVo();
				rolevo.setId(user.getTbRole().getId());
				rolevo.setNote(user.getTbRole().getNote());
				rolevo.setRolename(user.getTbRole().getRolename());
				vo.setJobDuties(user.getJobDuties());
				vo.setJobPlace(user.getJobPlace());
				vo.setTbRole(rolevo);
				volist.add(vo);
			}
			page.setRows(volist);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listAll error.",e);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 获得所有的采购人员
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryAllPurchase() {
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryUserByRole(5L);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchase ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 获得所有的审核人员
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryAllCaudit() {
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryUserByRole(3L);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllCaudit ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 审核通过,根据合同模板状态判断，查询审核的人员
	* @return
	* @author chenliang 新增日期：2013-4-3
	* @since ContractManage
	 */
	public String queryUserCheck(){
		PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
		List<UserVo> list = userService.templateCheckQueryUser(status,checkResult);
		page.setRows(list);
		page.setMsg("ok");
		this.result = JSONObject.fromObject(page);
		return SUCCESS;
	}
	
    /**
     * 返回所有销售的列表
     * @return
     */
	public String queryUserListByrUserTaskUrlId()
	{
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			if(!BlankUtil.isBlank(userTaskUrlid)){
				TbTaskUrl ttu=taskUrlService.getByPk(Long.parseLong(userTaskUrlid));
				if(!BlankUtil.isBlank(ttu.getRole_id())){
					List list = new ArrayList();
					list=this.userService.queryUserByRole(ttu.getRole_id());
					page.setRows(list);
					page.setMsg("ok");
					this.result = JSONObject.fromObject(page);
				}else{
					page.setMsg("no");
					this.result = JSONObject.fromObject(page);
				}
			}else{
				page.setMsg("no");
				this.result = JSONObject.fromObject(page);
			}
		} catch (Exception e) {
			LOGGER.error("queryAllCaudit ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 查询所有财务人员
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String queryAllfinancials()
	{
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryUserByRole(6l);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllfinancials ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	* 方法用途和描述: 查询所有销售人员接口人
	* @return
	* @author chenliang 新增日期：2013-3-22
	* @since ContractManage
	 */
	public String queryAllsales()
	{
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryIpUserByRole(BusinessConstants.role_seller);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllCaudit ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 查询所有售后人员
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String queryAllAftersale()
	{
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryUserByRole(7l);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllfinancials ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据urluser表中的id查询，默认查询所有的销售人员
	* @return
	* @author chenliang 新增日期：2013-3-22
	* @since ContractManage
	 */
	public String queryByUserTaskIdOrDefaultSales()
	{
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			//userTaskUrlid为空则默认查询所有的销售
			if(!BlankUtil.isBlank(userTaskUrlid)){  
				TbTaskUrl ttu=taskUrlService.getByPk(Long.parseLong(userTaskUrlid));
				list=this.userService.queryUserByRole(ttu.getRole_id());
				page.setRows(list);
				page.setMsg("ok");
				this.result = JSONObject.fromObject(page);
			}else{
				list=this.userService.queryUserByRole(4l);
				page.setRows(list);
				page.setMsg("ok");
				this.result = JSONObject.fromObject(page);
			}
		} catch (Exception e) {
			LOGGER.error("queryAllCaudit ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 任务转交是，根据角色id查询对应角色下的用户
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public String forwordTaskFindUserByRole(){
		try {
			long rolePkid = 0;
			if(!BlankUtil.isBlank(roleid)){
				rolePkid = Long.parseLong(roleid);
			}
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List<UserVo> list = new ArrayList<UserVo>();
			list = this.userService.queryUserByRole(rolePkid);
			page.setRows(list);
			page.setMsg("ok");
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据角色查询所有销售接口人
	* @return
	* @author lizl 新增日期：2013-4-3
	* @since ContractManage
	 */
	public String queryAllSalesIP(){
		try {
			PageInfo<UserVo> page = new PageInfo<UserVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.userService.queryIpUserByRole(4l);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllCaudit ERROR:", e);
		}
		return SUCCESS;
	}
	
	@Override
	@JSON(serialize=false)
	public ICommonService<TbUser> getCommonService() {
		return userService;
	}

	@Override
	public TbUser getModel() {
		if(null==this.model){
			this.model = new TbUser();
		}
		return this.model;
	}

	@Override
	public void setModel(TbUser model) {
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


}
