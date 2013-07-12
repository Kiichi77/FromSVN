package cn.com.kxcomm.ipmi.web.action.serverInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBelong;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.BelongService;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;
 
/**
 * 
* 功能描述:服务器所属集合表数据展现层
* @author chenl 新增日期：2012-8-10
* @since ipmi_web
 */
public class BelongAction extends BaseAction<TbBelong, String> {

	private static final Logger log = Logger.getLogger(BelongAction.class);
	
	@Autowired(required=true)
	public BelongService belongService;
	
	public List<BelongEntity> belongEntityList;  //服务器所属集合表数据层级集合
	public Page<BelongEntity> pageBelongList;  //分页的集合表数据层级集合
	private String parid;
	private java.util.List<Map<String, Object>> setList;
	
	public String getParid() {
		return parid;
	}

	public void setParid(String parid) {
		this.parid = parid;
	}



	public java.util.List<Map<String, Object>> getSetList() {
		return setList;
	}

	public void setSetList(java.util.List<Map<String, Object>> setList) {
		this.setList = setList;
	}

	public Page<BelongEntity> getPageBelongList() {
		return pageBelongList;
	}

	public void setPageBelongList(Page<BelongEntity> pageBelongList) {
		this.pageBelongList = pageBelongList;
	}

	public List<BelongEntity> getBelongEntityList() {
		return belongEntityList;
	}

	public void setBelongEntityList(List<BelongEntity> belongEntityList) {
		this.belongEntityList = belongEntityList;
	}
	
	/**
	 * 
	* 方法用途和描述: 重写保存方法
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public String save(){
		log.debug("getMode:"+getModel().toString());
		Long id = belongService.findMaxId();
		getModel().setId(id);
		log.debug("id:"+id);
		log.debug("+++++++"+getModel().toString());
		belongService.save(getModel());
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器位置:"+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("保存服务器位置");      // 备注:一般为添加的数据项名称 
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);	
	}

	/**
	 * 
	* 方法用途和描述: 根据菜单级别查询上一级菜单的对象(用于添加服务器所属集合表信息)
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public String findParentBylevel(){
		Short level = getModel().getLevel();
		log.debug("BelongAction level:"+level);
		this.list = belongService.findParentBylevel(level);
		log.debug("list:"+this.list.toString());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 查询第一级菜单，数据中心这级别数据
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public String findMenu(){
		Long parentId = getModel().getParId();
		log.debug("parentId:"+parentId);
		belongEntityList = belongService.findMenu(parentId);
		log.debug("belongEntityList:"+belongEntityList.size());
		return successInfo(null);
	}
	
	public String findMenuWithServer(){
		Long parentId = getModel().getParId();
		log.debug("parentId:"+parentId);
		belongEntityList = belongService.findMenuWithServer(parentId);
		log.debug("belongEntityList:"+belongEntityList.size());
		return successInfo(null);
	}
	
//	public String test(){
//		Long id = getModel().getId();
//		log.debug("id:"+id);
//		belongEntityList = belongService.test(id);
//		log.debug("belongEntityList:"+belongEntityList.size());
//		return successInfo(null);
//	}
	
	/**
	 * 
	* 方法用途和描述: 根据主键查询
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public String findById(){
		Long belongId = getModel().getId();
		log.debug("belongId:"+belongId);
		this.model = belongService.getByPk(belongId);
		log.debug("belong model:"+this.model.toString());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 根据等级，查询该等级下所有的集合列表
	* @return
	* @author chenl 新增日期：2012-8-16
	* @since ipmi_web
	 */
	public String findListByLevel(){
		log.debug("json model:"+getModel().toString());
		pageBelongList = belongService.findListByLevel(getModel(),getPageInfo());
		log.debug("pageBelongList:"+pageBelongList.toString());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 获取所有的位置以菜单对象返回
	* @return
	* @author chenl 新增日期：2012-8-29
	* @since ipmi_web
	 */
	public String getBelongMenu(){
		Long parentId = getModel().getParId();
		log.debug("parentId:"+parentId);
		belongEntityList = belongService.getAllBelongMenu(parentId);
		log.debug("belongEntityList:"+belongEntityList.toString());
		return successInfo(null);
	}
	
	/**
	 * 重写delete方法
	 */
	public String delete(){
		log.debug("ids:"+this.ids);
		String[] idss=(String[])getIds();
		Long[] idslong = new Long[idss.length];
		for(int i=0;i<idslong.length;i++){
			idslong[i]=Long.parseLong(idss[i]);
		}
		boolean result = belongService.deleteAlls(idslong);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器位置： "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("删除服务器位置.");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		if(result){
			return successInfo("ok");
		}else{
			return successInfo("no");
		}
	}
	
	@Override
	public CommonService getCommonService() {
		return belongService;
	}

	@Override
	public TbBelong getModel() {
		if(this.model==null){
			this.model = new TbBelong();
		}
		return this.model;
	}

	@Override
	public void setModel(TbBelong model) {
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
