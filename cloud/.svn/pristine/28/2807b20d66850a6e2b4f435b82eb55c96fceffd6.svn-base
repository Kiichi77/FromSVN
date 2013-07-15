package cn.com.kxcomm.contractmanage.web.action;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.vo.UserRightEntity;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbTask;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;


/**
 * 
 * 功能描述:任务管理
 * @author chenliang 新增日期：2013-2-25
 * @since ContractManage
 */
public class TaskAction extends BaseAction<TbTask, String> {

	private static Logger LOGGER = Logger.getLogger(TaskAction.class);
	
	private TaskVo taskVo;
	@Autowired(required=true)
	private ITaskService taskService;
	
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	
	private CheckedVo checkedVo;
	
	private String projectId;
	
	
	public String getProjectId() {
		return projectId;
	}
	
	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * 
	* 方法用途和描述: 我的代办任务分页查询
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public String myCurrentTask(){
		HttpSession session = getHttpSession();
		UserRightEntity ueInSession = (UserRightEntity) session.getAttribute(SessionUtils.USER);
		getModel().setState(0);  //设置0：只查询未完成的任务
		getModel().setPersonInChargeId(ueInSession.getUserEntity()); //设置负责人
		PageInfo<TaskVo> pageInfo = taskService.pageInfo(getModel(), getPageInfo());
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 已完成的任务
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public String finishTask(){
		HttpSession session = getHttpSession();
		UserRightEntity ueInSession = (UserRightEntity) session.getAttribute(SessionUtils.USER);
		getModel().setState(1);  //设置1：只查询以完成的任务
		getModel().setPersonInChargeId(ueInSession.getUserEntity()); //设置负责人
		PageInfo<TaskVo> pageInfo = taskService.pageInfo(getModel(), getPageInfo());
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 提交任务
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public String submitTask(){
		HttpSession session = getHttpSession();
		Long userIdLong = ((Long) session.getAttribute(SessionUtils.USER_ID));
		String msg = "ok";
		try {
			taskService.submitTask(getModel());
			systemLogService.writeTheLog(SystemLogType.task_submit, userIdLong, "成功", null, "任务名称："+getModel().getTaskName());
		} catch (Exception e) {
			msg = "no";
			e.printStackTrace();
			try {
				systemLogService.writeTheLog(SystemLogType.task_submit, userIdLong, "失败", e.getMessage(), "任务名称："+getModel().getTaskName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		this.result = JSONObject.fromObject(successInfo(msg));
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 任务转交
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public String forwardedTask(){
		HttpSession session = getHttpSession();
		Long userIdLong = ((Long) session.getAttribute(SessionUtils.USER_ID));
		String msg = "ok";
		try {
			taskService.forwardedTask(taskVo);
			systemLogService.writeTheLog(SystemLogType.task_forward, userIdLong, "成功", null, "任务名称："+taskVo.getTaskName()+",转给："+taskVo.getPersonInChargeId());
		} catch (Exception e) {
			msg = "no";
			e.printStackTrace();
			try {
				systemLogService.writeTheLog(SystemLogType.task_forward, userIdLong, "失败", e.getMessage(), "任务名称："+taskVo.getTaskName()+",转给："+taskVo.getPersonInChargeId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		this.result = JSONObject.fromObject(successInfo(msg));
		return SUCCESS;
	}
	
	/**
	 * 根据项目查询项目的流程
	 * @param projectId  项目ID
	 * @throws Exception 
	 */
	public String getTaskByProjectId()
	{
		 try {
			   PageInfo<TaskVo> page =  new PageInfo<TaskVo>(ServletActionContext.getRequest());
			   Integer project=null;
			   if(!BlankUtil.isBlank(projectId))
			   {
				   project=Integer.parseInt(projectId);
			   }
			   PageInfo<TaskVo> list=taskService.listTask(page,project);
			   result = JSONObject.fromObject(list);
			} catch (Exception e) {
				LOGGER.error("getTaskByProjectId ERROR:",e);
			}
		    return SUCCESS;
	}
	
	 public String checkContract(){
		 String msg = "ok";
		 System.out.println("Action:"+checkedVo.toString());
		 HttpSession session = getHttpSession();
		 Long userIdLong = ((Long) session.getAttribute(SessionUtils.USER_ID));
		 checkedVo.setUser_id(""+userIdLong);
		 taskService.checkContract(checkedVo);
		 this.result = JSONObject.fromObject(successInfo(msg));
		 return SUCCESS;
	 }
	@Override
	public ICommonService getCommonService() {
		return taskService;
	}

	@Override
	public TbTask getModel() {
		if(null == this.model){
			this.model = new TbTask();
		}
		return this.model;
	}

	@Override
	public void setModel(TbTask model) {
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

	public TaskVo getTaskVo() {
		return taskVo;
	}

	public void setTaskVo(TaskVo taskVo) {
		this.taskVo = taskVo;
	}

}
