package cn.com.kxcomm.ipmi.web.action.serverInfo;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.BlackNameService;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.ServerService;
import cn.com.kxcomm.ipmi.vo.BlackNameEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;

public class BlackNameAction extends BaseAction<TbBlackName, String> {

	@Autowired(required=true)
	private BlackNameService blackNameService;
	@Autowired(required=true)
	private ServerService serverService;
	
	private BlackNameEntity blackNameEntity; //黑名单vo实体
	private Page<BlackNameEntity> blackNamePageList;   //分页对象
	
	public BlackNameEntity getBlackNameEntity() {
		return blackNameEntity;
	}

	public void setBlackNameEntity(BlackNameEntity blackNameEntity) {
		this.blackNameEntity = blackNameEntity;
	}

	public Page<BlackNameEntity> getBlackNamePageList() {
		return blackNamePageList;
	}

	public void setBlackNamePageList(Page<BlackNameEntity> blackNamePageList) {
		this.blackNamePageList = blackNamePageList;
	}

	/**
	 * 分页查询重写父类分页方法
	 */
	public String list(){
		log.debug("blackNameAction page list."+getBlackNameEntity().toString());
		blackNamePageList = blackNameService.queryPage(getBlackNameEntity(), getPageInfo());
		log.debug("pageList size:"+blackNamePageList.getResult().size());
		return successInfo(null);
	}
	
	
	/**
	 * 
	* 方法用途和描述: 根据主键id查找对象
	* @return
	* @author chenl 新增日期：2012-8-10
	* @since ipmi_web
	 */
	public String findById(){
		Long blackNameId = getModel().getId();
		log.debug("blackNameAction getmode id:"+blackNameId);
		this.model = blackNameService.getByPk(blackNameId);
		log.debug("blackNameAction successful.");
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 重新检测
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	public String toDetection(){
		log.debug("blackNameEntity:"+this.blackNameEntity);
		TbServer server = serverService.getByPk(blackNameEntity.getServiceId());
		log.debug("toDetection getIdracIp:"+server.getIdracIp()+",user:"+server.getIdracUser());
		boolean flat = blackNameService.toDetection(server,blackNameEntity.getId());
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器id： "+blackNameEntity.getServiceId()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("重新检测黑名单.");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		if(flat){
			return successInfo("重新检测到该服务器,已经把该服务器移除黑名单!");
		}else{
			return successInfo("未检测到该服务器,请联系管理员！");
		}
	}
	
	@Override
	public CommonService getCommonService() {
		return blackNameService;
	}

	@Override
	public TbBlackName getModel() {
		if(this.model == null){
			this.model = new TbBlackName();
		}
		return this.model;
	}

	@Override
	public void setModel(TbBlackName model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
