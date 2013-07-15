package cn.com.kxcomm.ipmi.web.action.policy;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.PolicyServerService;
import cn.com.kxcomm.ipmi.service.PolicyService;
import cn.com.kxcomm.ipmi.vo.PolicyServerEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;
import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 
 * 功能描述:策略数据展现类
 * @author chenl 新增日期：2012-8-9
 * @since ipmi_web
 */
public class PolicyAction extends BaseAction<TbPolicy, String> {
	private String down;

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	@Autowired(required=true)
	private PolicyService policyService;
	@Autowired(required=true)
	private PolicyServerService policyServerService;

	public PolicyServerEntity policyServerEntity; //策略与服务器关系对应关系实体对象
	public TbPolicy tbpolicy;//不知道baseAction的model为什么不能用,时间关系自己建立一个对象接收参数



	public TbPolicy getTbpolicy() {
		return tbpolicy;
	}

	public void setTbpolicy(TbPolicy tbpolicy) {
		this.tbpolicy = tbpolicy;
	}

	public PolicyServerEntity getPolicyServerEntity() {
		return policyServerEntity;
	}

	public void setPolicyServerEntity(PolicyServerEntity policyServerEntity) {
		this.policyServerEntity = policyServerEntity;
	}

	/**
	 * 
	 */
	public String list() throws Exception {
		try {
			log.debug("list model=" + getTbpolicy().toString()+",tbpolicyEntity:"+tbpolicy);
			this.pageList = this.getCommonService().findByPage(getTbpolicy(),
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
	 * 不知道为什么model失灵 故重写save()方法
	 */
	public String save() throws Exception {
		int downint=0;
		if(down!=null&&!down.equals("")){
			downint = Integer.parseInt(down);
			if(downint<0||downint>15){
				return successInfo("百分比请控制为0%-15%的整数");
			}
			tbpolicy.setPolicyBumber(downint);
		}
		getCommonService().save(tbpolicy);
		//系统日志
		try{
			HttpSession session = getHttpSession();
			UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("策略名称： "+tbpolicy.getPolicyName()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("添加策略");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("增加成功");
	}


	/**
	 * 重写update()方法
	 */
	public String update() throws Exception {
		int downint=0;
		if(down!=null&&!down.equals("")){
			downint = Integer.parseInt(down);
			if(downint<0||downint>15){
				return successInfo("百分比请控制为0%-15%的整数");
			}
			tbpolicy.setPolicyBumber(downint);
		}

		//修改策略，需要调用张建华那边的接口，同步发送命令修改每台服务器的策略。
		//根据策略id查找管理服务器的服务器策略id
		//		Long id = tbpolicy.getId();
		//		List<TbPolicySever> serverPolicyMan = policyServerService.findByPolicyId(id);
		//		log.debug("serverPolicyMan:"+serverPolicyMan.toString());
		//		boolean flat = true;
		//		//如果该策略关联了服务器，则需要先调用接口修改服务器的策略
		//		if(!BlankUtil.isBlank(serverPolicyMan) && serverPolicyMan.size()>0){
		//			//调用张建华更新服务器策略的接口，只能修改阀值，所以需要把tbpolicy传入过去,serverPolicyMan是策略与策略id与服务器之间的关系数据
		//			flat = policyService.updateSetPolicy(serverPolicyMan,tbpolicy);
		//			log.info("call interface is "+flat);
		//		}
		//		//修改本地数据库
		//		if(flat){

		/**
		 * 暂时改为直接修改QUARTZ的定时任务
		 */
		boolean flat=true;
		policyService.update(tbpolicy);
		//		}else{
		////			return successInfo("修改失败");
		//			log.info("local database update error.call interface is "+flat);
		//		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short)(flat?1:0)); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("策略id "+tbpolicy.getId()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("修改策略");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("修改成功");
	}
	/**
	 * 
	 * 方法用途和描述:根据主键查询对象
	 * @return
	 * @author chenl 新增日期：2012-8-9
	 * @since ipmi_web
	 */
	public String findById(){
		log.debug("findById getModel id:"+getModel().getId());
		this.model = policyService.getByPk(getModel().getId());
		log.debug("model:"+Json.toJson(this.model));
		return successInfo(null);
	}

	/**
	 * 
	 * 方法用途和描述: 根据策略id设置策略与服务器的关系
	 * @return
	 * @author chenl 新增日期：2012-8-9
	 * @since ipmi_web
	 */
	public String saveSetPolicy(){
		short result = 0;
		if(!BlankUtil.isBlank(policyServerEntity)){
			policyService.saveOrUpdateSetPolicy(policyServerEntity);
			result = 1;
			log.info("setPolicy success.");
		}else{
			log.info("setPolicy fail,policyServerEntity is null.");
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
			sysLog.setOperationResult(result); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("策略id： "+policyServerEntity.getPolicyId()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("设置策略与服务器的关系");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("操作成功");
	}

	/**
	 *  删除策略
	 */
	public String delete(){
		String[] idss=(String[])getIds();
		Long[] idslong = new Long[idss.length];
		int i=0;
		for(String id :idss){
			idslong[i]=Long.parseLong(idss[i]);
			if(policyServerService.findByField("tbPolicy.id", idslong[i])!=null)
				return successInfo("所选的策略中含有已经绑定服务器的策略,删除操作回滚");
			i++;
		}
		getCommonService().deleteAllByPks(idslong);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("策略对象： "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("批量删除服务器上绑定的策略");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	@Override
	public CommonService getCommonService() {
		return policyService;
	}

	@Override
	public String[] getIds() {
		return ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public TbPolicy getModel() {
		if(this.model==null){
			this.model = new TbPolicy();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPolicy model) {
		this.model= model;
	}

}
