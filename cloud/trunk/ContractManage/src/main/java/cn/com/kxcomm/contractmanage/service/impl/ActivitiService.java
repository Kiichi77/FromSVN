package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.DateFormatUtil;
import cn.com.kxcomm.contractmanage.dao.ProcessUserDAO;
import cn.com.kxcomm.contractmanage.dao.ProjectDAO;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
/**
 * activiti流程图业务调用封装
 * @author DongGod
 *
 */
@Service("activitiService")
public class ActivitiService implements IactivitiService {

	@Autowired(required=true)
	private ProcessUserDAO processUserDAO;
	@Autowired(required=true)
	private ProjectDAO projectDAO;

	/**
	 * 流程发布
	 * @param xmlpath  流程图的相对路径
	 * @param processname  流程名称
	 * @return
	 */
	@Override
	public void processDefinitionDeploy(String xmlpath, String processname) {
		//发布流程
		ProcessCustomService.getRepositoryService().createDeployment().
		name(processname).addClasspathResource(xmlpath).deploy();
	}

	/**
	 * 根据进程ID删除已经发布的流程
	 * @param processid
	 */
	@Override
	public void removeDeployedDefinition(String processid) {
		ProcessCustomService.getRepositoryService().deleteDeploymentCascade(processid);
	}

	/**
	 * 用户启动流程
	 * @param processid
	 * @param userid
	 */
	@Override
	public ProcessInstance startProcessByUser(String userid,String typeId) {
		List<ProcessDefinition> list = null;
		if(BusinessConstants.publishProject.equals(typeId)){ 
			list=ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/ModeAudit.bpmn20.xml").list();
		}else if(BusinessConstants.templateProcess.equals(typeId)){
			list=ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/baojiash.bpmn20.xml").list();
		}else if(BusinessConstants.caigouProcess.equals(typeId)){
			list=ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/xunjiafukuan.bpmn20.xml").list();
		}else if(BusinessConstants.orderarriveProcess.equals(typeId)){
			list=ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/dingdan.bpmn20.xml").list();
		}else if(BusinessConstants.shouhouProcess.equals(typeId)){
			list=ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/shouhou.bpmn20.xml").list();
		}
		ProcessDefinition pd =list.get(0);
		RuntimeService runtimeService = ProcessCustomService.getRuntimeService();
		//设置启动人的ID
		ProcessCustomService.getIdentityService().setAuthenticatedUserId(userid);
		return runtimeService.startProcessInstanceById(pd.getId());
	}

	/**
	 * 分配任务给用户
	 * @param processid
	 * @param userid
	 */
	@Override
	public void taskClaim(String processid, String userid,Map map) {
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(processid).list(); 
		for (Task task : taskList) {  
			ProcessCustomService.getTaskService().claim(task.getId(), userid); 
		}
	}

	/**
	 * 用户完成任务
	 * @param processid
	 * @param userid
	 */
	@Override
	public void finishTheTask(String processid, String userid,Map map) {
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(processid).taskAssignee(userid).list(); 
		for (Task task : taskList) {
			ProcessCustomService.getTaskService().complete(task.getId()); 
		}
	}

	/**
	 * 获取当前session用户的所有任务
	 * 我的待办任务
	 * @param userid
	 */
	@Override
	public List<TaskVo> getTheCurrentTask(String userid,int start,int end) {
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().taskAssignee(userid).orderByTaskCreateTime().desc().listPage(start, end);
		List<TaskVo> taskVoList =  new ArrayList<TaskVo>();
		if(taskList.size()>0){
			for (int i = 0; i < taskList.size(); i++) {
				Task task = taskList.get(i);
				TaskVo vo = new TaskVo();
				vo.setDatetime(DateFormatUtil.longFormatDateTime(task.getCreateTime()));
				TbProcessUser processUser=processUserDAO.findByField("processId", task.getProcessInstanceId());
				vo.setProcessId(task.getProcessInstanceId());
				vo.setTaskID(task.getId());
				vo.setTaskName(task.getName());
				if(processUser!=null){
					if(processUser.getProcessName()!=null){
						vo.setProcessName(processUser.getProcessName());
					}
					Integer projetcId=null;
					if(processUser.getProjectId()!=null){
						projetcId=Integer.parseInt(processUser.getProjectId().toString());
						TbProject tbProject = projectDAO.findByField("projectId", projetcId);
						vo.setProjectName(tbProject.getProjectName());
					}
				}

				taskVoList.add(vo);
			}
		}
		return taskVoList; 

	}

	/**
	 * 根据进程ID获取进程定义实体	
	 * @param processid
	 */
	@Override
	public ProcessDefinition getProcessDefinitionByProcessid(String processid) {

		return null;
	}

	/**
	 * 查询该用户的历史流程实例实体列表
	 * @param userid
	 * @return
	 */
	@Override
	public List<HistoricProcessInstance> getHistoryProcessInstanceList(String userid) {
		return ProcessCustomService.getHistoryService().createHistoricProcessInstanceQuery()
		.startedBy(userid).list();
	}

	/**
	 * 我的历史任务
	 */
	@Override
	public List<TaskVo> myHistoryTask(String userId,int start,int end) {
		List<HistoricTaskInstance> list = ProcessCustomService.getHistoryService().createHistoricTaskInstanceQuery().finished().taskAssignee(userId).orderByHistoricTaskInstanceEndTime().desc().listPage(start, end);
		List<TaskVo> taskList = new ArrayList<TaskVo>();
		for (HistoricTaskInstance historicTaskInstance : list) {
			TaskVo vo = new TaskVo();
			vo.setDatetime(DateFormatUtil.longFormatDateTime(historicTaskInstance.getStartTime()));
			vo.setProcessId(historicTaskInstance.getProcessInstanceId());
			vo.setTaskID(historicTaskInstance.getId());
			vo.setTaskName(historicTaskInstance.getName());
			String hql = "select a from TbProcessUser a where a.processId=?";
			List<TbProcessUser> processUselist = processUserDAO.find(hql, historicTaskInstance.getProcessInstanceId());
			vo.setProcessName(processUselist.get(0).getProcessName());
			taskList.add(vo);
		}
		return taskList;
	}

	/**
	 * 
	 * 方法用途和描述: 驳回
	 * @param taskId  任务id
	 * @param activityId  驳回的步骤id
	 * @param variables  参数
	 * @author chenliang 新增日期：2013-3-4
	 * @since ContractManage
	 */
	@Override
	public void backProcess(String taskId,String activityId,Map<String, Object> variables){
		try {
			ProcessCustomService.backProcess(taskId, activityId, variables);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
