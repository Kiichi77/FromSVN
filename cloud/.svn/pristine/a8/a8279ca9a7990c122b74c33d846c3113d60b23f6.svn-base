package cn.com.kxcomm.ipmi.web.action;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.SystemOperLogService;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
/**
 * 
 * 功能描述:Action基类,所有的业务Action类应继承该基类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T,PK extends Serializable> extends ActionSupport {

	protected  static final Logger log = Logger.getLogger(BaseAction.class);

	/**
	 * 说明清楚此属性的业务含义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	public final String TOTAL_ROWS = "totalRows";

	/**
	 * 查询全部
	 */
	public static final String LIST = "list";

	/**
	 * 管理员名称
	 */
	public String adminname;
	/**
	 * 管理员角色
	 */
	public String adminrole;

	public String getAdminrole() {
		return adminrole;
	}


	public void setAdminrole(String adminrole) {
		this.adminrole = adminrole;
	}


	public String getAdminname() {
		return adminname;
	}


	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	@Autowired(required=true)
	public SystemOperLogService systemOperLogService;
	public boolean success;
	public String msg;
	/**
	 * 分页对象
	 */
	protected Page<T> pageList;
	/**
	 * 实体对象,用于显示和接收数据
	 */
	protected T model;
	/**
	 * 主键ID列表
	 */
	protected PK[] ids;
	/**
	 * 数据列表.用户查询列表数据用.如:combobox需要的数据.
	 */
	protected List<T> list;

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

	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}

	public Page<T> getPageList() {
		return pageList;
	}


	public void setPageList(Page<T> pageList) {
		this.pageList = pageList;
	}


	/**
	 * Action函数,显示Entity列表界面. 建议return SUCCESS.
	 */
	public String list() throws Exception {
		try {
			log.debug("list model=" + Json.toJson(getModel()));
			this.pageList = this.getCommonService().findByPage(getModel(),
					getPageInfo());
			log.debug("list size=" + this.pageList.getResult().size());
			this.setSuccess(true);
			this.setMsg("成功");
			return "list";
		} catch (Exception e) {
			log.error("list error." + e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * Action通用函数,新增Entity,保存.参数为model
	 */
	public String save() throws Exception {
		getCommonService().save(model);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("操作对象："+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("添加操作");      // 备注:一般为添加的数据项名称		
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * action调用函数，修改entity，参数model
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		getCommonService().update(model);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("修改对象："+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("修改操作");      // 备注:一般为添加的数据项名称		
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * Action通用函数,删除列表.传递参数为ids
	 */
	public String delete() throws Exception {
		String[] idss=(String[])getIds();
		Long[] idslong = new Long[idss.length];
		for(int i=0;i<idslong.length;i++){
			idslong[i]=Long.parseLong(idss[i]);
		}
		getCommonService().deleteAllByPks(idslong);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("操作对象："+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("批量删除操作");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * Action通用函数,列表查询全部,RETURN FIND_ALL
	 */
	public String findAll() throws Exception{
		list = getCommonService().findAll();
		successInfo(null);
		return SUCCESS;
	}

	/**
	 * 从request中取分页信息
	 * 
	 * @return
	 */
	protected Page<T> getPageInfo() {
		return new Page(ServletActionContext.getRequest());
	}

	/*************************************
	 *  fast return methods
	 ***********************************/

	/**
	 * 
	 * 设置success和msg属性并返回成功
	 * 
	 * @author chenxinwei 新增日期：2012-3-4
	 * @return
	 */
	@JSON(serialize = false)
	public String setAndReturnSuccess() {
		this.success = true;
		this.msg = "操作成功";
		return SUCCESS;
	}
	/**
	 * 设置并返回信息,如:{success:true,msg:"成功"}
	 * @param success
	 * @param msg 
	 */
	public String info(boolean success,String msg){
		this.success = success;
		this.msg = msg;
		return SUCCESS;
	}

	/**
	 * 设置并返回成功信息,如:{success:true,msg:"操作成功"}
	 * @param msg 为null时.返回默认值"操作成功"  
	 */
	public String successInfo(String msg){
		this.success = true;		
		this.msg = msg==null ? "操作成功" : msg;
		return SUCCESS;
	}

	/**
	 * 设置并返回失败信息,如:{success:false,msg:"操作失败"}
	 * @param msg 为null时.返回默认值"操作失败"  
	 */
	public String failInfo(String msg){
		this.success = false;		
		this.msg = msg==null ? "操作失败" : msg;
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 从object数组的list中获取第一个元素
	 * @param sourceList
	 * @return
	 * @author chuzq 新增日期：2008-11-24
	 * @author 你的姓名 修改日期：2008-11-24
	 * @since wapportal_manager version(2.0)
	 */
	public List getFirstElementOfList(List<Object[]> sourceList) {

		List result = new ArrayList();
		if (sourceList == null || sourceList.size() == 0)
			return result;
		for (Object[] objArr : sourceList) {
			result.add(objArr[0]);
		}
		return result;

	}

	/*************************************
	 *  abstract methods
	 ***********************************/

	/**
	 * 返回绑定实体的相关Service
	 * 
	 * @return
	 */
	public abstract CommonService getCommonService();

	/**
	 * 在子类中具体化,这样struts组装时,类型才能正确!!<br>
	 * <p style='color:red'>注意:该方法不要返回空对象</p>.如参考如下示例写法:
	 * <pre>
		if(this.model==null){
			this.model = new CmsRight();
		}
		return this.model;
	 * </pre>
	 * @return
	 */
	public abstract T getModel();

	/**
	 * 在子类中具体化,这样struts组装时,类型才能正确!!
	 * @param model
	 */
	public abstract void setModel(T model);

	/**
	 * 在子类中具体化,这样struts组装时,类型才能正确!!
	 * @return
	 */
	public abstract PK[] getIds();

	/**
	 * 在子类中具体化,这样struts组装时,类型才能正确!!
	 * @param ids
	 */
	public abstract void setIds(PK[] ids);

	/**
	 * 
	 * 方法用途和描述:添加系统日志
	 * @author chenl 新增日期：2012-8-21
	 * @since ipmi_web
	 */
	public void addSystemLog(TbSystemoperlog operLog){
		log.debug("add SystemLog!");
		/**
		 * 添加使用该类的主机IP
		 */
		operLog.setIp(this.getHttpRequest().getRemoteAddr());
		systemOperLogService.save(operLog);
		log.debug("add SystemLog successful!");
	}

	/**
	 * 获取HttpRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	private final String getClientAddress(HttpServletRequest request) {    

		String address = request.getHeader("X-Forwarded-For");    

		if (address != null ) {    

			return address;    

		}    

		return request.getRemoteAddr();    

	}   

	/**
	 * 获取HttpSession对象
	 * 
	 * @return
	 */
	public static HttpSession getHttpSession() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		return session;
	}

	/**
	 * 获取HttpServletResponse对象
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}
}
