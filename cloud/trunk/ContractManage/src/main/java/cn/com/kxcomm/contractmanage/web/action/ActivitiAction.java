package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.entity.TbTaskUrl;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ContractOrderService;
import cn.com.kxcomm.contractmanage.service.impl.ContractService;
import cn.com.kxcomm.contractmanage.service.impl.ContractTemplatesService;
import cn.com.kxcomm.contractmanage.service.impl.TaskUrlService;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * 
 * 功能描述:流程管理
 * @author chenliang 新增日期：2013-2-25
 * @since ContractManage
 */
public class ActivitiAction extends BaseAction<Task, String> {

	private static Logger LOGGER = Logger.getLogger(ActivitiAction.class);

	@Autowired(required = true)
	private IProcessUserService processUserService;

	@Autowired(required=true)
	private IactivitiService activitiService;

	@Autowired(required=true)
	private TaskUrlService taskUrlService;
	@Autowired(required=true)
	private ContractService contractService;
	@Autowired(required=true)
	private ContractOrderService contractOrderService;
	@Autowired(required=true)
	private ContractTemplatesService contractTemplatesService;
	private List<TaskVo> rows;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	//当前任务能驳回的任务列表
	private List<ActivityImpl> activityImplList;

	private int start;

	private int end;

	//用户id
	private String user_id;
	//流程名称
	private String process_name;
	//进程id
	private String process_id;
	//流程类型
	private String typeId;
	//任务URL
	private TbTaskUrl taskUrl;

	private String projectId;
	//订单ID
	private String contractOrderId;
	private int contractModeId;//合同模板的ID,送审核时与流程绑定起来.
	public int getContractModeId() {
		return contractModeId;
	}

	public void setContractModeId(int contractModeId) {
		this.contractModeId = contractModeId;
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public List<ActivityImpl> getActivityImplList() {
		return activityImplList;
	}

	public void setActivityImplList(List<ActivityImpl> activityImplList) {
		this.activityImplList = activityImplList;
	}

	@JSON(serialize=true)
	public TbTaskUrl getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(TbTaskUrl taskUrl) {
		this.taskUrl = taskUrl;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public List<TaskVo> getTaskvo() {
		return rows;
	}

	public void setTaskvo(List<TaskVo> rows) {
		this.rows = rows;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	
	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 
	 * 方法用途和描述: 我的待办任务
	 * 
	 * @return
	 * @author chenliang 新增日期：2013-2-21
	 * @since ContractManage
	 */
	public String getTheCurrentTask() {
		PageInfo<Task> page = new PageInfo<Task>(ServletActionContext.getRequest());
		HttpSession session = getHttpSession();
		if(session.getAttribute(SessionUtils.USER_ID)==null){
			return SUCCESS;
		}
		String userid = ((Long) session.getAttribute(SessionUtils.USER_ID)).toString();
		List<TaskVo> taskList = activitiService.getTheCurrentTask(userid,start,end);
		PageInfo<TaskVo> pageInfovo = new PageInfo<TaskVo>();
		pageInfovo.setRows(taskList);
		this.result = JSONObject.fromObject(pageInfovo);
		return SUCCESS;
	}

	/**
	 * 获得项目的流程
	 * @return
	 */
	public String getProjectProcess() {
		PageInfo<TbProcessUser> pageget = new PageInfo<TbProcessUser>(ServletActionContext.getRequest());
		Long aa=null;
		if(projectId!=null)
			aa=Long.parseLong(projectId);
		PageInfo<TbProcessUser> page = processUserService.listTbProcessUser(pageget, aa);
		result = JSONObject.fromObject(page);
		return SUCCESS;
	}



	/**
	 * 
	 * 方法用途和描述: 启动流程
	 * @return
	 * @author chenliang 新增日期：2013-2-21
	 * @since ContractManage
	 */
	public String startAprocessByUser(){
		LOGGER.info("process_name:"+process_name+",user_id:"+user_id+",typeId:"+typeId);
		ProcessInstance processInstance=null;
		HttpSession session = getHttpSession();
		Long role_id = (Long) session.getAttribute(SessionUtils.USER_ROLE_ID);  //角色id
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
		//        System.out.println("typeId:"+typeId);
		//角色为管理员和销售人员才能启动流程
		if(role_id==1||role_id==4){
			processInstance = activitiService.startProcessByUser(""+useridsession,typeId);
		}else if(role_id==2){
			//启动请假流程 
		}

		LOGGER.info("role_id:"+role_id+",processInstance:"+processInstance);
		if(role_id!=null && processInstance!=null){
			// 把流程ID与用户ID关联起来
			TbProcessUser tbProcessUser=new TbProcessUser();
			tbProcessUser.setProcessId(processInstance.getProcessInstanceId());
			tbProcessUser.setProcessName(process_name);
			tbProcessUser.setUserId(useridsession);
			tbProcessUser.setState("进行中");
			if(BusinessConstants.publishProject.equals(typeId)){ 
				tbProcessUser.setProcessDefName("模板审核流程");
			}else if(BusinessConstants.templateProcess.equals(typeId)){
				tbProcessUser.setProcessDefName("报价表审核流程");
			}else if(BusinessConstants.caigouProcess.equals(typeId)){
				tbProcessUser.setProcessDefName("审核询价流程");
			}else if(BusinessConstants.orderarriveProcess.equals(typeId)){
				tbProcessUser.setProcessDefName("订单到货流程");
			}else if(BusinessConstants.shouhouProcess.equals(typeId)){
				tbProcessUser.setProcessDefName("售后流程");
			}
			//如果采购订单流程---流程与销售订单ID绑定起来
			if(typeId.equals("3")){
				tbProcessUser.setContractOrderId(Long.parseLong(this.contractOrderId));
				TbContractOrder tbContractOrder = contractOrderService.getByPk(Integer.parseInt(contractOrderId));
				TbContract tbContract=contractService.getByPk(tbContractOrder.getContract().getId());
				tbProcessUser.setProjectName(tbContract.getProject().getProjectName());
				tbProcessUser.setProjectId(Long.parseLong(""+tbContract.getProject().getProjectId()));
			}
			processUserService.save(tbProcessUser);
			//分配任务
			activitiService.taskClaim(processInstance.getProcessInstanceId(), user_id, null);
		}
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;		
	}
	/*
	 * 启动模板审核流程并提交给下一个人审核
	 */
	public String startMoBanShenHeProcess(){
		HttpSession session = getHttpSession();
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
		//如果process_id不是NULL证明是打回来的流程那么可以就提交任务
		try {
		if(this.process_id!=null && !this.process_id.equals("null") && this.process_id.length()>0){
			activitiService.finishTheTask(process_id,useridsession.toString() , null);
			//现在再分配就是模板审核的流程了
			activitiService.taskClaim(process_id,user_id , null);
			this.result = JSONObject.fromObject(successInfo("ok"));
			return SUCCESS;
		}
		
		//先判断一下模板是不是已经审核,已经审核过的不再发起流程
		TbContractTemplates tbContractTemplates = contractTemplatesService.getByPk(this.contractModeId);

		ProcessInstance processInstance=null;
		Long role_id = (Long) session.getAttribute(SessionUtils.USER_ROLE_ID);  //角色id
		//角色为管理员和销售人员才能启动流程
		if(role_id==1||role_id==4){
			processInstance = activitiService.startProcessByUser(""+useridsession,typeId);
			LOGGER.info("role_id:"+role_id+",processInstance:"+processInstance);
			if(role_id!=null && processInstance!=null){
				// 把流程ID与用户ID关联起来
				TbProcessUser tbProcessUser=new TbProcessUser();
				tbProcessUser.setProcessId(processInstance.getProcessInstanceId());
				tbProcessUser.setProcessName(tbContractTemplates.getName());
				tbProcessUser.setUserId(useridsession);
				tbProcessUser.setState("进行中");
				if(BusinessConstants.publishProject.equals(typeId)){ 
					tbProcessUser.setProcessDefName("模板审核流程");
				}else if(BusinessConstants.templateProcess.equals(typeId)){
					tbProcessUser.setProcessDefName("报价表审核流程");
				}else if(BusinessConstants.caigouProcess.equals(typeId)){
					tbProcessUser.setProcessDefName("审核询价流程");
				}else if(BusinessConstants.orderarriveProcess.equals(typeId)){
					tbProcessUser.setProcessDefName("订单到货流程");
				}else if(BusinessConstants.shouhouProcess.equals(typeId)){
					tbProcessUser.setProcessDefName("售后流程");
				}
				processUserService.save(tbProcessUser);

				contractTemplatesService.update(tbContractTemplates);
				//分配任务user_id
				activitiService.taskClaim(processInstance.getProcessInstanceId(),useridsession.toString() , null);
				//完成再分配
				activitiService.finishTheTask(processInstance.getProcessInstanceId(),useridsession.toString() , null);
				//现在再分配就是模板审核的流程了
				activitiService.taskClaim(processInstance.getProcessInstanceId(),user_id , null);
			}
			try {
				systemLogService.writeTheLog(SystemLogType.submit_audit_contract_templates, useridsession, "成功", null, "提交审核成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.result = JSONObject.fromObject(successInfo("ok"));
		}
		} catch (Exception e) {
			LOGGER.error("startAprocessByUser ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.submit_audit_contract_templates, useridsession, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;	
	}
	/**
	 * 
	 * 方法用途和描述: 完成本次任务，分配下一个任务
	 * @return
	 * @author chenliang 新增日期：2013-2-21
	 * @since ContractManage
	 */
	public String taskClaim(){
		LOGGER.info("process_id:"+process_id+",user_id:"+user_id);
		HttpSession session = getHttpSession();
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
		//先完成本次任务
		activitiService.finishTheTask(process_id, ""+useridsession, null);
		//分配下一个任务
		activitiService.taskClaim(process_id, user_id, null);
		this.result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 我发起的任务
	 * @return
	 * @author chenliang 新增日期：2013-2-25
	 * @since ContractManage
	 */
	public String myInitiatedTask(){
		HttpSession session = getHttpSession();
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID);
		List<TbProcessUser> processUserList = null;
		if(null!=useridsession){
			processUserList = processUserService.queryAllByUserId(useridsession,start,end);
		}
		PageInfo<TbProcessUser> pageInfo = new PageInfo<TbProcessUser>();
		pageInfo.setRows(processUserList);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 我的历史任务
	 * @return
	 * @author chenliang 新增日期：2013-2-25
	 * @since ContractManage
	 */
	public String myHistoryTask(){
		HttpSession session = getHttpSession();
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID);
		List<TaskVo> taskVoList = null;
		if(null!=useridsession){
			taskVoList = activitiService.myHistoryTask(""+useridsession,start,end);
		}
		PageInfo<TaskVo> pageInfo = new PageInfo<TaskVo>();
		pageInfo.setRows(taskVoList);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 获取流程到当前流程之前的流程，用于驳回操作
	 * @return
	 * @author chenliang 新增日期：2013-3-1
	 * @since ContractManage
	 */
	public String getPartentTask(){
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(this.process_id).list(); 
		for (Task task : taskList) {  
			try {
				activityImplList = ProcessCustomService.findBackAvtivity(task.getId());
			} catch (Exception e) {
				LOGGER.error("get par task fail!!!");
				e.printStackTrace();
			}
		}
		List<TaskVo> volist = new ArrayList<TaskVo>();
		for (ActivityImpl activity : activityImplList) {
			TaskVo vo = new TaskVo();
			vo.setTaskID(activity.getId());
			vo.setTaskName(""+activity.getProperty("name"));
			volist.add(vo);
		}
		PageInfo<TaskVo> pageInfo = new PageInfo<TaskVo>();
		pageInfo.setRows(volist);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}


	@Override
	public ICommonService getCommonService() {
		return processUserService;
	}

	@Override
	public Task getModel() {
		return this.model;
	}

	@Override
	public void setModel(Task model) {
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
