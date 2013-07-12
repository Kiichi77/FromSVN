package cn.com.kxcomm.activiti.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;

import cn.com.kxcomm.contractmanage.web.util.SessionUtils;



public class KangxunWorkFlowDemo {
	public static void main(String[] args){
		/**
		 * 发布流程,整个软件生命周期发布一次即可
		 */
//	    ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/KangxunWorkFlow.bpmn20.xml").deploy();
//		List<ProcessDefinition> pdList;//流程定义实体列表
		/**
		 * 获取流程实体
		 */
//		pdList =ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().deploymentId("1701").list();
//		ProcessDefinition pd=pdList.get(0);
//		System.out.println(pd.getId());
		/**
		 * 启动流程
		 */
//		RuntimeService runtimeService = ProcessCustomService.getRuntimeService();
//		Map map = new HashMap();
//		map.put("owner", "大神东");
//		runtimeService.startProcessInstanceById(pd.getId(),map);
		/**
		 * 分配任务
		 */
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId("401").taskAssignee("dong").list(); 
		for (Task task : taskList) {  
			System.out.println("分配任务给dong：name:"+task.getName()+",id:"+task.getId()+
					","+task.getOwner()+","+task.getParentTaskId()+","+task.getAssignee());
			ProcessCustomService.getTaskService().claim(task.getId(), "dong"); 
		}
	}
}
