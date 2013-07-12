package cn.com.kxcomm.common.util;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;

/**
 * 
 * 功能描述:系统初始化，发布流程
 * @author chenliang 新增日期：2013-3-1
 * @since ContractManage
 */
public class PublishingProcess {

	private static final Logger LOGGER = Logger.getLogger(PublishingProcess.class);

	public static PublishingProcess publishingProcess = null;

	public static PublishingProcess getInstance(){
		if(null==publishingProcess){
			publishingProcess = new PublishingProcess();
		}
		return publishingProcess;
	}

	/**
	 * 
	 * 方法用途和描述: 发布流程	
	 * 发布流程,整个软件生命周期发布一次即可 
	 * @author chenliang 新增日期：2013-3-1
	 * @since ContractManage
	 */
	public void publishing(){
		//发布项目审核流程
		List<ProcessDefinition> list = ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/baojiash.bpmn20.xml").list();
		if(list==null||list.size()==0)
		ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/baojiash.bpmn20.xml").name("项目审核流程").deploy();
		//发布合同模板审核流程
        list = ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/ModeAudit.bpmn20.xml").list();
        if(list==null||list.size()==0)
		ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/ModeAudit.bpmn20.xml").name("模板审核流程").deploy();
        //发布询价流程
        list = ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/xunjiafukuan.bpmn20.xml").list();
        if(list==null||list.size()==0)
		ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/xunjiafukuan.bpmn20.xml").name("询价流程").deploy();
      //发布销售流程
        list = ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/dingdan.bpmn20.xml").list();
        if(list==null||list.size()==0)
		ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/dingdan.bpmn20.xml").name("订单到货流程").deploy();
      //发布销售流程
        list = ProcessCustomService.getRepositoryService().createProcessDefinitionQuery().processDefinitionResourceName("bpmn/shouhou.bpmn20.xml").list();
        if(list==null||list.size()==0)
		ProcessCustomService.getRepositoryService().createDeployment().addClasspathResource("bpmn/shouhou.bpmn20.xml").name("售后流程").deploy();
	}
	public static void main(String[] args){
		new PublishingProcess().publishing();
	}
}
