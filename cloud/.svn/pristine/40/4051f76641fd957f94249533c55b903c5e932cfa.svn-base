package cn.com.kxcomm.contractmanage.activiti.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.impl.ProcessUserService;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 工作流流程页面调用方法
 * @author DongGod
 *
 */
public class ActivitiTools{
	private String processid;//发布的流程的ID
	private String msg;//返回给页面的信息
	private List<ProcessDefinition> pdList;//流程定义实体列表
	private List<Task> userTasks;//当前登录用户任务列表
	private List<HistoricProcessInstance> hpiList;//当前用户历史任务列表
	private ProcessDefinition processDefinition;//流程定义实体
	private String user_id;//用户ID


	public void processDefinitionDeploy(String xmlpath, String processname) {
		//发布流程
		ProcessCustomService.getRepositoryService().createDeployment().
		name(processname).addClasspathResource(xmlpath).deploy();
	}


	public void removeDeployedDefinition(String processid) {
		ProcessCustomService.getRepositoryService().deleteDeploymentCascade(processid);
	}


	public ProcessInstance startProcessByUser(String processid, String userid) {
		ProcessDefinition pd =ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().deploymentId(processid).list().get(0);
		RuntimeService runtimeService = ProcessCustomService.getRuntimeService();
		//设置启动人的ID
		ProcessCustomService.getIdentityService().setAuthenticatedUserId(userid);
		return runtimeService.startProcessInstanceById(pd.getId());
	}


	public void taskClaim(String processid, String userid,Map map) {
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(processid).list(); 
		for (Task task : taskList) {  
			ProcessCustomService.getTaskService().claim(task.getId(), userid); 
		}
	}


	public void finishTheTask(String processid, String userid,Map map) {
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(processid).taskAssignee(userid).list(); 
		for (Task task : taskList) {
			System.out.println("完成"+task.getName()+"任务");
			ProcessCustomService.getTaskService().complete(task.getId(),map); 
		}
	}

	public List<Task> getTheCurrentTask(String userid) {
		return ProcessCustomService.getTaskService().createTaskQuery().taskAssignee(userid).list(); 

	}


	public ProcessDefinition getProcessDefinitionByProcessid(String processid) {

		return null;
	}


	public List<HistoricProcessInstance> getHistoryProcessInstanceList(String userid) {
		return ProcessCustomService.getHistoryService().createHistoricProcessInstanceQuery()
		.startedBy(userid).list();
	}

}
