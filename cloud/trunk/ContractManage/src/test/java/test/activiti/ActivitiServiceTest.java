package test.activiti;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.contractmanage.dao.ProcessUserDAO;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.impl.ActivitiService;


public class ActivitiServiceTest{
	public ActivitiService activitiService;
	public ProcessUserDAO processUserDAO;
	private ApplicationContext ctx;

	public ActivitiServiceTest(){
		ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		processUserDAO = (ProcessUserDAO) ctx.getBean("processUserDAO");
		activitiService = (ActivitiService) ctx.getBean("activitiService");
	}
	/**
	 * 流程发布测试.发布一次,数据库有数据就可以了,明白吗?
	 * 发布模板审核流程
	 */
	public void deployActiviti(){
		activitiService.processDefinitionDeploy("bpmn/ModeAudit.bpmn20.xml", "模板审核流程TEST");
	}
	/**
	 * 模拟审核模板的流程
	 * @throws InterruptedException 
	 */
	@Test
	public void sometest() throws InterruptedException{
		String processDefindId="1";
		String userid="东哥";
		String processId="";
		ProcessInstance processInstance=activitiService.startProcessByUser(processDefindId, userid);
		/**
		 * 把流程ID与用户ID关联起来
		 */
		TbProcessUser tbProcessUser=new TbProcessUser();
		tbProcessUser.setProcessId(processInstance.getProcessInstanceId());
		tbProcessUser.setProcessName("启动模板审核流程");
		tbProcessUser.setUserId(1l);
		processUserDAO.save(tbProcessUser);
		//************************************************************//
		System.out.println(userid+"启动模板审核流程.................");
		System.out.println(userid+"的流程启动成功");
		List<HistoricProcessInstance> hpilist=activitiService.getHistoryProcessInstanceList("东哥");
		for(HistoricProcessInstance hpi:hpilist){

		}
		/**
		 * processId应该是读取TbProcessUser实体列表在页面选择的,此处为了方便直接引用上面的
		 */
		processId=processInstance.getProcessInstanceId();
		Map map = new HashMap();
		activitiService.taskClaim(processId, userid, map);
		System.out.println("东哥在系统中创建机型配置模板任务开始.请等待3秒");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		activitiService.finishTheTask(processId, userid, map);
		System.out.println("东哥在系统中创建机型配置模板任务完成.");

		//接下来是销售审核模板,审核后是财务审核,之后是总经理审核,然后任务完成
		activitiService.taskClaim(processId, "销售小东", map);
		System.out.println("东哥把任务交给'销售小东'审核,模板的业务的ID与任务TASKID关联起来.");
		System.out.println("销售小东在页面下载模板查看,请等待3秒");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		System.out.println("确认没问题,点击审核通过");
		activitiService.finishTheTask(processId, "销售小东", map);
		
		activitiService.taskClaim(processId, "财务啊花", map);
		System.out.println("销售小东把任务交给'财务啊花'审核,模板的业务的ID与任务TASKID关联起来.");
		System.out.println("财务啊花在页面下载模板查看,请等待3秒");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		System.out.println("确认没问题,点击审核通过");
		activitiService.finishTheTask(processId, "财务啊花", map);
		
		activitiService.taskClaim(processId, "总经理啊宝", map);
		System.out.println("财务啊花把任务交给'总经理啊宝'审核,模板的业务的ID与任务TASKID关联起来.");
		System.out.println("总经理啊宝在页面下载模板查看,请等待3秒");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		Thread.sleep(1000);System.out.println(".........");
		System.out.println("确认没问题,点击审核通过");
		activitiService.finishTheTask(processId, "总经理啊宝", map);
		
		System.out.println("到此,任务完成了");
		
		
	}

}
