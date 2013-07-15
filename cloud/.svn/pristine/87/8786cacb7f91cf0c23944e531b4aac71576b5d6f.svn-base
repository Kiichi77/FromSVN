package cn.com.kxcomm.ipmi.web.action.policy;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.ipmi.entity.TbPolicy;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.PolicyServerService;
import cn.com.kxcomm.ipmi.service.PolicyService;
import cn.com.kxcomm.ipmi.service.ServerService;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;
import cn.com.kxcomm.ipmitool.Policy;
import cn.com.kxcomm.ipmitool.entity.PolicyEntity;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.ipmitool.util.Parameter;

/**
 * 
 * 功能描述:策略分配数据返回
 * @author chenl 新增日期：2012-8-9
 * @since ipmi_web
 */
@SuppressWarnings("serial")
public class PolicyServerAction extends BaseAction<TbPolicySever, String> {
	private String ip;
	private String policyid;
	private String policyid2;
	private String treeid;
	private String policytype;

	public String getPolicytype() {
		return policytype;
	}
	public void setPolicytype(String policytype) {
		this.policytype = policytype;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPolicyid() {
		return policyid;
	}
	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}
	public String getPolicyid2() {
		return policyid2;
	}
	public void setPolicyid2(String policyid2) {
		this.policyid2 = policyid2;
	}
	public String getTreeid() {
		return treeid;
	}
	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	@Autowired(required=true)
	private PolicyServerService policyServerService;
	@Autowired(required=true)
	private ServerService serverService;
	@Autowired(required=true)
	private PolicyService policyService;
	@Override
	public CommonService getCommonService() {
		return policyServerService;
	}
	@Override
	public TbPolicySever getModel() {
		if(this.model == null){
			this.model = new TbPolicySever();
		}
		return this.model;	
	}


	@Override
	public void setModel(TbPolicySever model) {
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

	/**
	 * 接收IP字符串进行模糊 搜索,把所以匹配的服务器ID与策略绑定.
	 * @return
	 */
	public String searchandsave(){
		List<TbServer> servers=serverService.getServerIdByIp(ip);
		if(servers.size()==0)return successInfo("找不到对应的服务器");
		List<TbPolicySever> tbPolicySevers=new ArrayList();
		//定义一个变量看看看是否有重复的主机,有就在页面小提示一下;
		boolean ifthesame=false;
		for(int i=0;i<servers.size();i++){
			//先把已经绑定了主机筛选出去
			TbPolicySever testifhadit = policyServerService.findByPolicyIdandServerId(Long.parseLong(policyid),servers.get(i).getId());
			if(testifhadit!=null){
				ifthesame=true;
				continue;
			}
			int policyidin=policyServerService.getFreePolicyId(servers.get(i));
			if(policyidin>Parameter.maxPolicy)continue; 
			TbPolicySever tbPolicySever=new TbPolicySever();
			TbServer ts = new TbServer();ts.setId(servers.get(i).getId());
			tbPolicySever.setTbServer(ts);
			tbPolicySever.setPolicyidInServer(policyidin);
			tbPolicySever.setBiningState(2);
			tbPolicySever.setBiningServerType(1);
			TbPolicy tp=new TbPolicy();tp.setId(Long.parseLong(policyid));
			tbPolicySever.setTbPolicy(tp);
			tbPolicySevers.add(tbPolicySever);
			
			
			//系统日志
			try{
				HttpSession session = getHttpSession();
				UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
				TbSystemoperlog sysLog = new TbSystemoperlog();
				sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
				sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
				sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
				sysLog.setParam("服务器id "+servers.get(i).getId()); // 操作参数说明:一般为操作记录的key
				sysLog.setNote("绑定服务器与策略的关系");      // 备注:一般为添加的数据项名称			
				sysLog.setDateTime(new Timestamp(new Date().getTime()));
				this.addSystemLog(sysLog);
			}catch(Exception ex){
				log.error("syslogError:",ex);
			}
		}
		if(tbPolicySevers.size()==0&&ifthesame==true)
			return successInfo("所有主机已经绑定此策略,绑定主机数为0");
		if(tbPolicySevers.size()>0&&ifthesame==true)
			return successInfo("已经有主机已经绑定此策略,成功绑定主机数为"+tbPolicySevers.size());
		if(tbPolicySevers.size()==0&&ifthesame==false)
			return successInfo("绑定失败,系统故障");
		policyServerService.littleBatchSave(tbPolicySevers,policyid);
		return successInfo("操作成功");
	}

	/**
	 * 这边是树型空间添加服务器与策略的绑定关系,所以没台服务器都要通过服务器ID 把服务器的IDRAC相关参数查询出来
	 * @return
	 */
	public String saveByTree(){
		List<TbPolicySever> tbPolicySevers=new ArrayList();
		HttpServletRequest request=ServletActionContext.getRequest();
		policyid2=request.getParameter("policyid2");	
		String[] zu = this.treeid.split(",");
		List serverids = new ArrayList();
		for(int i=0;i<zu.length;i++){
			serverids.add(zu[i]);
		}
		if(serverids==null)return successInfo("找不到对应的服务器");
		//只有功耗策略
		int policyType=Parameter.policyPower;
		int domain=0;
		//根据策略ID获取策略详细
		TbPolicy tbPolicy=policyService.findByField("id", Long.parseLong(policyid2));
		//定义一个变量看看看是否有重复的主机,有就在页面小提示一下;
		boolean ifthesame=false;
		for(int i=0;i<serverids.size();i++){
			//先把已经绑定了主机筛选出去
			TbPolicySever testifhadit = policyServerService.findByPolicyIdandServerId(Long.parseLong(policyid2),Long.parseLong(serverids.get(i).toString()));
			if(testifhadit!=null){
				ifthesame=true;
				continue;
			}
			//先取得服务器的IDRAC相关参数
			TbServer server = serverService.getByPk(Long.parseLong(serverids.get(i).toString()));
			int policyidin=policyServerService.getFreePolicyId(server);
			if(policyidin>Parameter.maxPolicy)continue; 

			TbPolicySever tbPolicySever=new TbPolicySever();
			TbServer ts = new TbServer();ts.setId(server.getId());
			tbPolicySever.setTbServer(ts);
			tbPolicySever.setPolicyidInServer(policyidin);
			tbPolicySever.setBiningState(2);
			tbPolicySever.setBiningServerType(1);
			TbPolicy tp=new TbPolicy();tp.setId(Long.parseLong(policyid2));
			tbPolicySever.setTbPolicy(tp);
			tbPolicySevers.add(tbPolicySever);
			
			//系统日志
			try{
				HttpSession session = getHttpSession();
				UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
				TbSystemoperlog sysLog = new TbSystemoperlog();
				sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
				sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
				sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
				sysLog.setParam("服务器id "+server.getId()); // 操作参数说明:一般为操作记录的key
				sysLog.setNote("绑定服务器与策略的关系");      // 备注:一般为添加的数据项名称			
				sysLog.setDateTime(new Timestamp(new Date().getTime()));
				this.addSystemLog(sysLog);
			}catch(Exception ex){
				log.error("syslogError:",ex);
			}
		}
		if(tbPolicySevers.size()==0&&ifthesame==true)
			return successInfo("所有主机已经绑定此策略,绑定主机数为0");
		if(tbPolicySevers.size()>0&&ifthesame==true)
			return successInfo("已经有主机已经绑定此策略,成功绑定主机数为"+tbPolicySevers.size());
		if(tbPolicySevers.size()==0&&ifthesame==false)
			return successInfo("绑定失败,系统故障");
		policyServerService.littleBatchSave(tbPolicySevers,policyid2);

		return successInfo("操作成功");
	}

	/**
	 * 更新策略与服务器的关系就是换一个策略
	 */
	//	public String update(){
	//		TbPolicySever t=policyServerService.getByPk(Long.parseLong(treeid));
	//		TbPolicy tbPolicy = new TbPolicy();
	//		tbPolicy.setId(Long.parseLong(policyid2));
	//
	//		TbPolicySever testifhadit = policyServerService.findByPolicyIdandServerId(Long.parseLong(policyid2),t.getTbServer().getId());
	//		if(testifhadit!=null)
	//			return  successInfo("该主机已经绑定此策略");
	//		//先删除对应服务器策略ID的策略,再用此策略ID建立新的策略
	//		//张建华的IPMITOOL命令接口初始化;有问题找他QQ517888652
	//		Policy policy = Policy.getSingleInstance();
	//		//服务器实体SET值
	//		SerInfo serentity = new SerInfo();
	//		serentity.setHost(t.getTbServer().getIdracIp());
	//		serentity.setPasswd(t.getTbServer().getIdracPassword());
	//		serentity.setUser(t.getTbServer().getIdracUser());
	//		int domain=0;
	//		if(t.getTbPolicy().getPolicyType()==1||t.getTbPolicy().getPolicyType()==4)domain=Parameter.domainSystem;
	//		if(t.getTbPolicy().getPolicyType()==2||t.getTbPolicy().getPolicyType()==5)domain=Parameter.domainCpu;
	//		if(t.getTbPolicy().getPolicyType()==3||t.getTbPolicy().getPolicyType()==6)domain=Parameter.domainMemory;
	//		boolean flag=policy.removePolicyID(serentity, t.getPolicyidInServer(), domain);
	//		log.debug("server "+t.getTbServer().getIdracIp()+" delete policyid="+t.getPolicyidInServer()+",domain="+domain+",result:"+ flag);
	//		if(!flag)return  successInfo("删除服务器上的策略失败");
	//
	//		//删除后建立修改后的策略
	//		PolicyEntity entity = new PolicyEntity();
	//		//默认策略新增时为不启用
	//		boolean tempb=false;if(t.getBiningState()==1)tempb=true;
	//		entity.setPolicyEnabled(tempb);
	//		entity.setCorrectionTime(60000l);
	//		entity.setStatReportingPeriod(100);
	//		entity.setPolicyId(t.getPolicyidInServer());
	//		TbPolicy tbPolicymodify=policyService.getByPk(Long.parseLong(policyid2));
	//		entity.setPolicyLimit(tbPolicymodify.getPolicyLimit());
	//		entity.setPolicyTriggerLimit(tbPolicymodify.getPolicyBumber());
	//		int policyType=0;
	//		policyType=Parameter.policyPower;
	//		entity.setPolicyType(policyType);
	//		entity.setSendAlert(false);
	//		entity.setShutdown(false);
	//		domain=0;
	//		if(tbPolicymodify.getPolicyType()==1)domain=Parameter.domainSystem;
	//		if(tbPolicymodify.getPolicyType()==2)domain=Parameter.domainCpu;
	//		if(tbPolicymodify.getPolicyType()==3)domain=Parameter.domainMemory;
	//		entity.setComponent(domain);
	//		SupportTool supportTool = SupportTool.getSingleInstance();
	//		if(!supportTool.checkSucessIpmi(serentity)){
	//			TbAddorupdatefailrecord tbAddorupdatefailrecord = new TbAddorupdatefailrecord();
	//			tbAddorupdatefailrecord.setDomainInServer(domain);
	//			tbAddorupdatefailrecord.setFailReason(1);
	//			tbAddorupdatefailrecord.setOperat(2);
	//			tbAddorupdatefailrecord.setPolicyId(Long.parseLong(t.getPolicyidInServer()+""));
	//			tbAddorupdatefailrecord.setPolicyIdInServer(Integer.parseInt(t.getTbPolicy().getId()+""));
	//			tbAddorupdatefailrecord.setPolicyName(tbPolicy.getPolicyName());
	//			tbAddorupdatefailrecord.setServerId(t.getTbServer().getId());
	//			tbAddorupdatefailrecord.setServerIp(t.getTbServer().getIdracIp());
	//			tbAddorupdatefailrecord.setDatetime(new Timestamp(System.currentTimeMillis()));
	//			this.addorupdatefailrecordService.save(tbAddorupdatefailrecord);
	//		}
	//		flag=policy.setPolicy(serentity, entity);
	//		log.debug("server "+t.getTbServer().getIdracIp()+" add policyid="+t.getPolicyidInServer()+",domain="+domain+",result:"+ flag);
	//		short result = 0;
	//		if(!flag){
	//			TbAddorupdatefailrecord tbAddorupdatefailrecord = new TbAddorupdatefailrecord();
	//			tbAddorupdatefailrecord.setDomainInServer(domain);
	//			tbAddorupdatefailrecord.setFailReason(3);
	//			tbAddorupdatefailrecord.setOperat(2);
	//			tbAddorupdatefailrecord.setPolicyId(Long.parseLong(t.getPolicyidInServer()+""));
	//			tbAddorupdatefailrecord.setPolicyIdInServer(Integer.parseInt(t.getTbPolicy().getId()+""));
	//			tbAddorupdatefailrecord.setPolicyName(tbPolicy.getPolicyName());
	//			tbAddorupdatefailrecord.setServerId(t.getTbServer().getId());
	//			tbAddorupdatefailrecord.setServerIp(t.getTbServer().getIdracIp());
	//			tbAddorupdatefailrecord.setDatetime(new Timestamp(System.currentTimeMillis()));
	//			this.addorupdatefailrecordService.save(tbAddorupdatefailrecord);
	//			result = 1;
	//			return successInfo("服务器上修改策略失败");
	//		}else{
	//			t.setTbPolicy(tbPolicy);
	//			policyServerService.update(t);
	//			result = 0;
	//		}
	//
	//		//系统日志
	//		try{
	//			HttpSession session = getHttpSession();
	//			UserRightEntity userEntity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
	//			TbSystemoperlog sysLog = new TbSystemoperlog();
	//			sysLog.setTbUser(userEntity.getUserEntity());   // 用户账号
	//			sysLog.setOperationResult(result); //操作结果：1、成功，0、失败
	//			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
	//			sysLog.setParam("tbPolicy id "+policyid2); // 操作参数说明:一般为操作记录的key
	//			sysLog.setNote("update_server_policy");      // 备注:一般为添加的数据项名称			
	//			sysLog.setDateTime(new Timestamp(new Date().getTime()));
	//			this.addSystemLog(sysLog);
	//		}catch(Exception ex){
	//			log.error("syslogError:",ex);
	//		}
	//		return  successInfo("操作成功");
	//	}

	/**
	 * 重写删除方法,为了删除策略与服务器关系时调用接口把服务器上面的策略删掉
	 */
	public String delete(){
		String[] idss=(String[])getIds();
		Long[] idslong = new Long[idss.length];
		boolean flag=false;
		for(int i=0;i<idslong.length;i++){
			idslong[i]=Long.parseLong(idss[i]);
			TbPolicySever tbPolicySever = policyServerService.getByPk(idslong[i]);
					SerInfo serentity = new SerInfo();
					serentity.setHost(tbPolicySever.getTbServer().getIdracIp());
					serentity.setPasswd(tbPolicySever.getTbServer().getIdracPassword());
					serentity.setUser(tbPolicySever.getTbServer().getIdracUser());
					Policy policy = Policy.getSingleInstance();
					int domain=Parameter.domainSystem;
					Short temp = tbPolicySever.getTbPolicy().getPolicyType();
					if(temp==1||temp==4)domain=Parameter.domainSystem;
					if(temp==2)domain=Parameter.domainCpu;
					if(temp==3)domain=Parameter.domainMemory;
					PolicyEntity policyEntity = policy.getPolicyById(serentity, tbPolicySever.getPolicyidInServer(), domain);
					if(policyEntity!=null){//如果有先删 ,无就算直接删除数据库
						flag=policy.removePolicyID(serentity, tbPolicySever.getPolicyidInServer(), domain);
					}
		}

		getCommonService().deleteAllByPks(idslong);

		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("策略ids："+ids); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("批量删除服务器上的策略");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return  successInfo("操作成功");
	}

}
