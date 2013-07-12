package cn.com.kxcomm.ipmi.web.action.serverInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.thread.IprangeService;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;
import cn.com.kxcomm.ipmi.entity.TbServerBelongId;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.ServerService;
import cn.com.kxcomm.ipmi.vo.ResultServerListEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;
import cn.com.kxcomm.ipmitool.Sel;
import cn.com.kxcomm.ipmitool.SupportTool;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
/**
 * 服务器信息管理
 * @author chenliang
 *
 */
public class ServerAction extends BaseAction<TbServer, String> {

	@Autowired(required=true)
	private ServerService serverService;

	public int stateType; //类型判断关机还是开机
	public Long belongId; //服务器所属集合表数据
	public ResultServerListEntity resultList; // 服务器信息摘要详情列表集合
	private List<SELPo> sellist;
	public int getStateType() {
		return stateType;
	}

	public List<SELPo> getSellist() {
		return sellist;
	}

	public void setSellist(List<SELPo> sellist) {
		this.sellist = sellist;
	}

	public void setStateType(int stateType) {
		this.stateType = stateType;
	}

	public ResultServerListEntity getResultList() {
		return resultList;
	}

	public void setResultList(ResultServerListEntity resultList) {
		this.resultList = resultList;
	}

	public Long getBelongId() {
		return belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	/**
	 * 保存服务器信息
	 */
	public String save(){
		log.debug("save model:"+this.getModel().toString());
		SerInfo serinfo=new SerInfo();
		serinfo.setHost(getModel().getIdracIp());
		serinfo.setPasswd(getModel().getIdracPassword());
		serinfo.setUser(getModel().getIdracUser());
		/**
		 * 赵宝东加入ipmiIP是否跟已存在数据冲突验证
		 */
		TbServer server = serverService.findByField("idracIp", getModel().getIdracIp());
		if(server!=null){
			return successInfo("已经存在此IDRACIP");
		}
		SupportTool aa = SupportTool.getSingleInstance();
		if(!aa.checkSucessIpmi(serinfo)){
			return successInfo("您输入的Idrac信息没能通过验证,请输入正确的Idrac信息");
		}
		//保存服务器信息
		serverService.save(getModel());
		//保存服务器与服务器所属集合列表的关系
		Long serverId = getModel().getId();
		log.debug("serverId:"+serverId+",belongId"+belongId);
		TbServerBelongId id = new TbServerBelongId();
		id.setBelongId(belongId);
		id.setServerId(serverId);
		TbServerBelong serverBelong = new TbServerBelong();
		serverBelong.setId(id);
		serverService.insertServerBelong(serverBelong);
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器对象： "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("保存服务器信息.");      // 备注:一般为添加的数据项名称 
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * 修改服务器信息
	 */
	public String update(){
		log.debug("update model:"+this.getModel().toString());
		//保存服务器信息
		serverService.update(getModel());
		//保存服务器与服务器所属集合列表的关系
		Long serverId = getModel().getId();
		log.debug("serverId:"+serverId+",belongId"+belongId);
		TbServerBelongId id = new TbServerBelongId();
		id.setBelongId(belongId);
		id.setServerId(serverId);
		TbServerBelong serverBelong = new TbServerBelong();
		serverBelong.setId(id);
		serverService.updateServerBelong(serverBelong);
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器对象 "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("修改服务器信息");      // 备注:一般为添加的数据项名称 
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * 
	 * 方法用途和描述: 删除服务器信息
	 * @return
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	public String delete(){
		//先删除服务器与服务器所属集合列表的关系
		log.debug("deleteServerBelong ids:"+ids.toString());
		Long[] keyId = new Long[this.ids.length];
		if(!BlankUtil.isBlank(this.ids) && !"".equals(this.ids)){
			for(int i=0;i<ids.length;i++){
				keyId[i]=Long.parseLong(ids[i]);
			}
		}
		String message=""; 
		short result = 1;
		if(serverService.findByServerId(keyId)){
			boolean flat = false;
			flat=serverService.deleteServerBelong(keyId);
			//再删除服务器采集数据表数据
			flat=serverService.deleteHistoryServerInfo(keyId);
			//删除关联告警信息数据
			flat=serverService.deleteAlertServer(keyId);
			//删除关联黑名单数据
			flat=serverService.deleteBlackNameServer(keyId);
			//删除与策略关系的数据
			flat=serverService.deletePolicyServer(keyId);
			//再删除服务器信息表数据
			serverService.deleteAllByPks(keyId);
			if(flat){
				message = "ok";
			}else{
				message = "no";
				result = 0;
			}
		}else{
			log.debug("有服务器关联了策略，不能删除。");
			message="havePolicy";
			result=0;
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult(result); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_delete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("服务器信息： "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("删除服务器");      // 备注:一般为添加的数据项名称 
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(message);
	}

	/**
	 * 
	 * 方法用途和描述: 根据主键查询
	 * @return
	 * @author chenl 新增日期：2012-8-10
	 * @since ipmi_web
	 */
	public String findById(){
		Long serverId = getModel().getId();
		log.debug("serverId:"+serverId);
		this.model = serverService.getByPk(serverId);
		//		log.debug("findById model:"+Json.toJson(this.model));
		return successInfo(null);
	}

	/**
	 * 
	 * 方法用途和描述: 获取服务器实施详情数据
	 * @return
	 * @author chenl 新增日期：2012-8-17
	 * @since ipmi_web
	 */
	public String getServerRealTimeData(){
		log.info("getServerRealTimeData getModel id:"+getModel().getId());
		//根据服务器id查询服务器信息
		if(!BlankUtil.isBlank(getModel().getId())){
			TbServer tbServer = serverService.getByPk(getModel().getId());
			log.debug("tbServer "+tbServer);
			if(null!=tbServer){
				resultList=serverService.getServerRealTimeData(tbServer);
			}
		}else{
			log.info("model id is null.");
		}
		return "result";
	}

	/**
	 * 
	 * 方法用途和描述: 开机,关机
	 * @return
	 * @author chenl 新增日期：2012-8-23
	 * @since ipmi_web
	 */
	public String openOrShutdown(){
		log.debug("openShutdown id:"+getModel().getId());
		//根据服务器id查找服务器信息
		if(!BlankUtil.isBlank(getModel().getId())){
			TbServer tbServer = serverService.getByPk(getModel().getId());
			log.debug("tbServer:"+tbServer);
			if(null!=tbServer){
				boolean flat = serverService.openShutdown(tbServer,stateType);
				log.debug("openOrShutdown flat:"+flat);
			}
		}
		//系统操作日志
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_delete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam(stateType==1?"开机":"关机"); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("开关机操作.");      // 备注:一般为添加的数据项名称 
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * 获取本机系统日志
	 * @return
	 */
	public String SELList(){
		log.debug("SELList:"+getModel().getId());
		TbServer ts=serverService.getByPk(getModel().getId());
		sellist=new ArrayList<SELPo>();//初始化 列表
		//命令接口
		Sel aa = Sel.getSingleInstance();
		//服务器 实体
		SerInfo ser = new SerInfo();
		ser.setHost(ts.getIdracIp());
		ser.setUser(ts.getIdracUser());
		ser.setPasswd(ts.getIdracPassword());
		List<String> list = aa.querySelLog(ser);
		//解释返回的list
		for(int i=0;i<list.size();i++){
			String[] sel = list.get(i).toString().split("\\|");
			SELPo selpo = new SELPo();
			selpo.setId(sel[0].trim());
			selpo.setTime(sel[1].trim()+" "+sel[2].trim());
			selpo.setEvent(sel[3].trim());
			selpo.setEventResult(sel[4].trim());
			if(sel.length==6)
				selpo.setSsert(sel[5]);
			//			System.out.println(selpo.toString());
			sellist.add(selpo);//加到页面要显示的列表中
		}
		return successInfo(null);
	}

	public String clearsel(){
		TbServer ts=serverService.getByPk(getModel().getId());
		Sel aa = Sel.getSingleInstance();
		//服务器 实体
		SerInfo ser = new SerInfo();
		ser.setHost(ts.getIdracIp());
		ser.setUser(ts.getIdracUser());
		ser.setPasswd(ts.getIdracPassword());
		/**
		 * 清空服务器系统日志的 先注释掉 ,不然只有一台 就没得看功能了..
		 */
		//		aa.clear(ser);
		return successInfo(null);
	}

	public String checkIdracInfo(){
		SerInfo serinfo=new SerInfo();
		serinfo.setHost(getModel().getIdracIp());
		serinfo.setPasswd(getModel().getIdracPassword());
		serinfo.setUser(getModel().getIdracUser());
		/**
		 * 赵宝东加入ipmiIP是否跟已存在数据冲突验证
		 */
		TbServer server = serverService.findByField("idracIp", getModel().getIdracIp());
		if(server!=null){
			return successInfo("已经存在此IDRACIP");
		}
		SupportTool aa = SupportTool.getSingleInstance();
		if(!aa.checkSucessIpmi(serinfo)){
			return successInfo("您输入的Idrac信息没能通过验证,请输入正确的Idrac信息");
		}
		return successInfo(null);
	}

	public String iprange(){
		//FOR之前首先用JAVA代码检查看传来的字符串到底及格不及格,不然各种为止的错?
		List<SerInfo> list=null;
		IprangeService iprangeping=new IprangeService(getModel().getIdracIp(),getModel().getIdracUser(),getModel().getIdracPassword());
		list=iprangeping.iprangezhiipping();
		IprangeService iprangeipmi=new IprangeService(list);
		list=iprangeipmi.iprangezhiipmicheck();
		//入库
		List<TbServer> listServer = new ArrayList();
		for(SerInfo ser:list){
			TbServer tt=new TbServer();
			tt.setIdracIp(ser.getHost());
			tt.setIdracPassword(ser.getPasswd());
			tt.setIdracUser(ser.getUser());
			tt.setOperateSystem("自动导入");
			tt.setQuickServerCode("自动导入");
			tt.setServerIpIn("自动导入");
			tt.setServerIpOut("自动导入");
			tt.setServerName("自动导入"+ser.getHost());
			//去重
			TbServer server = serverService.findByField("idracIp", ser.getHost());
			if(server==null){
				listServer.add(tt);
			}
		}
		serverService.littleBatchSave(listServer);
		return successInfo(null);
	}

	@Override
	public CommonService getCommonService() {
		return serverService;
	}

	@Override
	public TbServer getModel() {
		if(this.model == null ){
			this.model = new TbServer();
		}
		return this.model;
	}

	@Override
	public void setModel(TbServer model) {
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
