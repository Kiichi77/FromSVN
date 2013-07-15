package cn.com.kxcomm.contractmanage.web.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
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
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	/**
	 * 说明清楚此属性的业务含义
	 */
	private static final long serialVersionUID = 1L;
	
	 //用于返回前台对象
    public JSONObject result;
    //
    public JSONArray jsonArray;
	
	protected PageInfo<T> pageList;
	public boolean success;
	public String msg;
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 实体对象,用于显示和接收数据
	 */
	protected T model;
	/**
	 * 主键ID列表
	 */
	protected PK[] ids;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	/**
	 * Action通用函数,新增Entity,保存.参数为model
	 */
	public String save() throws Exception {
		try{
			getCommonService().save(model);
			result = JSONObject.fromObject(successInfo("ok"));
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return SUCCESS;
	}

	/**
	 * action调用函数，修改entity，参数model
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		try{
			getCommonService().update(model);
			result = JSONObject.fromObject(successInfo("ok"));
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return SUCCESS;
	}

	/**
	 * Action通用函数,删除列表.传递参数为ids
	 */
	public String delete() throws Exception {
		try{
			String[] idss=(String[])getIds();
			Long[] idslong = new Long[idss.length];
			for(int i=0;i<idslong.length;i++){
				idslong[i]=Long.parseLong(idss[i]);
			}
			getCommonService().deleteAllByPks(idslong);
			result = JSONObject.fromObject(successInfo("ok"));
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return SUCCESS;
	}


	/*************************************
	 *  fast return methods
	 ***********************************/

	public PageInfo<T> getPageInfo(){
		return new PageInfo<T>(ServletActionContext.getRequest());
	}
	
	/**
	 * 设置并返回成功信息,如:{success:true,msg:"操作成功"}
	 * @param msg 为null时.返回默认值"操作成功"  
	 */
	public PageInfo<T> successInfo(String msg){
		PageInfo<T> pageInfo = getPageInfo();
		pageInfo.setMsg(msg==null?"操作成功":msg);
		return pageInfo;
	}

	public String successInfoStr(String msg){
		this.success = true;		
		this.msg = msg==null ? "操作成功" : msg;
		return SUCCESS;
	}
	
	/**
	 * 设置并返回失败信息,如:{success:false,msg:"操作失败"}
	 * @param msg 为null时.返回默认值"操作失败"  
	 */
	public PageInfo<T> failInfo(String msg){
		PageInfo<T> pageInfo = getPageInfo();
		pageInfo.setMsg(msg==null?"操作失败":msg);
		return pageInfo;
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
	public abstract ICommonService getCommonService();

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
