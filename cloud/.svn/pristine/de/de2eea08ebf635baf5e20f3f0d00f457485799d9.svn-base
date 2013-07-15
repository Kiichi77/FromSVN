package cn.com.kxcomm.ipmi.web.action.serverInfo;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.entity.AlertServerEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.AlertServerService;
import cn.com.kxcomm.ipmi.service.AlertService;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.vo.AlertVO;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;
 
/**
 * 
* 功能描述:告警数据展现层
* @author chenl 新增日期：2012-8-10
* @since ipmi_web
 */
public class AlertAction extends BaseAction<AlertEntity, String> {

	private static final Logger log = Logger.getLogger(AlertAction.class);

	@Autowired(required=true)
	private AlertService alertService;
	@Autowired(required=true)
	private AlertServerService alertServerService;
	
	private AlertVO alertVo;
	private Page<AlertVO> alertPageInfo;

	private List<AlertServerEntity> alertServerEntitys;
	
	public List<AlertServerEntity> getAlertServerEntitys() {
		return alertServerEntitys;
	}

	public void setAlertServerEntitys(List<AlertServerEntity> alertServerEntitys) {
		this.alertServerEntitys = alertServerEntitys;
	}

	
	public AlertVO getAlertVo() {
		return alertVo;
	}

	public void setAlertVo(AlertVO alertVo) {
		this.alertVo = alertVo;
	}

	public Page<AlertVO> getAlertPageInfo() {
		return alertPageInfo;
	}

	public void setAlertPageInfo(Page<AlertVO> alertPageInfo) {
		this.alertPageInfo = alertPageInfo;
	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询
	* @return
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public String pageList(){
		log.info("alertVo tostring:"+Json.toJson(alertVo));
		alertPageInfo = alertService.findByPage(alertVo, getPageInfo());
		log.info("alertPageInfo result:a"+alertPageInfo.getResult().size());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 保存警告信息
	* @return
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public String saveAlert(){
		if(!BlankUtil.isBlank(this.ids)&&this.ids.length>0){
			log.debug("alertVo:"+alertVo.toString()+",ids:"+this.ids.toString());
			AlertEntity entity = new AlertEntity();
			entity.setSeriousNum(alertVo.getSeriousNum());
			entity.setSeriousEnable((short) 1);
			entity.setWarningNum(alertVo.getWarningNum());
			entity.setWarningEnable((short) 1);
			entity.setFilterType(alertVo.getFilterType());
			entity.setUserId(alertVo.getTbUsers().getId());
			alertService.save(entity);
			for (int i = 0; i < this.ids.length; i++) {
				AlertServerEntity alertServerEntity = new AlertServerEntity();
				alertServerEntity.setAlert(entity);
				TbServer server = new TbServer();
				server.setId(Long.parseLong(ids[i]));
				alertServerEntity.setServer(server);
				alertServerService.save(alertServerEntity);
			}
		}else{
			log.debug("this.ids is null.");
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("告警信息id:"+getModel().getAlertId()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("保存告警信息");      // 备注:一般为添加的数据项名称	
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("保存成功！");
	}
	
	/**
	 * 
	* 方法用途和描述: 保存警告信息
	* @return
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public String updateAlert(){
		log.debug("alertVo:"+alertVo.toString()+",ids:"+this.ids.toString());
		//修改告警信息
		boolean flat = alertService.updateAlert(alertVo);
		//修改告警信息关联的服务器
		if(!BlankUtil.isBlank(this.ids)&&this.ids.length>0){
			int alertId = alertVo.getAlertId();
			Integer[] alertids = new Integer[1];
			alertids[0] = alertId;
			//删除该告警信息关联的所有服务器
			flat = alertServerService.deleteByAlertID(alertids);
			//添加告警信息id和服务器的关系
			for (int i = 0; i < this.ids.length; i++) {
				AlertServerEntity alertServerEntity = new AlertServerEntity();
				AlertEntity alertEntity = new AlertEntity();
				alertEntity.setAlertId(alertId);
				alertServerEntity.setAlert(alertEntity);
				TbServer server = new TbServer();
				server.setId(Long.parseLong(ids[i]));
				alertServerEntity.setServer(server);
				alertServerService.save(alertServerEntity);
			}
		}
		if(flat){
			return successInfo("修改成功！");
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("告警信息id:"+getModel().getAlertId()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("修改告警信息");      // 备注:一般为添加的数据项名称	
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("修改失败！");
	}
	
	/**
	 * Action通用函数,删除列表.传递参数为ids
	 */
	public String delete() throws Exception {
		String[] idss=(String[])getIds();
		Integer[] idslong = new Integer[idss.length];
		for(int i=0;i<idslong.length;i++){
			idslong[i]=Integer.parseInt(idss[i]);
		}
		//先删除告警信息与服务器信息之间的关系
		alertServerService.deleteByAlertID(idslong);
		//删除告警信息
		alertService.deleteAllByPks(idslong);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("告警信息id "+idss); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("删除告警信息");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * Action通用函数,根据报警ID查询策略所关联的服务器ID
	 */
	public String queryServersByAlertId() {
		log.info("ids tostring:"+Json.toJson(this.alertVo.getAlertId()));
		alertServerEntitys = alertServerService.findListByField("alert.alertId",this.alertVo.getAlertId());
		System.out.println("********size:  "+alertServerEntitys.size());
		return successInfo(null);
	}

	@Override
	public CommonService getCommonService() {
		return alertService;
	}

	@Override
	public AlertEntity getModel() {
		if(null==this.model){
			this.model= new AlertEntity(); 
		}
		return this.model;
	}

	@Override
	public void setModel(AlertEntity model) {
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