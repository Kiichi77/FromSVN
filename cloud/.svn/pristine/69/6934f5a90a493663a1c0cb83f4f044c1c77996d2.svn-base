package cn.com.kxcomm.contractmanage.web.action;

import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbTaskUrl;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ContractOrderService;
import cn.com.kxcomm.contractmanage.service.impl.ProjectService;
import cn.com.kxcomm.contractmanage.service.impl.PurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.service.impl.PurchaseOrderService;
import cn.com.kxcomm.contractmanage.service.impl.TaskUrlService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderVo;

public class SingleEntityInquiresAction extends BaseAction<Task, String> {

	private static Logger LOGGER = Logger.getLogger(ActivitiAction.class);

	@Autowired(required = true)
	private IProcessUserService processUserService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	@Autowired(required=true)
	private TaskUrlService taskUrlService;
	@Autowired(required=true)
	private ProjectService projectService;
	@Autowired(required=true)
	private ContractOrderService contractOrderService;

	@Autowired(required=true)
	private IPurchaseOrderContractFileService purchaseOrderContractFileService;
	@Autowired(required=true)
	private IPurchaseOrderService purchaseOrderService;
	
	public IPurchaseOrderService getPurchaseOrderService() {
		return purchaseOrderService;
	}

	public void setPurchaseOrderService(IPurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	
	public IPurchaseOrderContractFileService getPurchaseOrderContractFileService() {
		return purchaseOrderContractFileService;
	}

	public void setPurchaseOrderContractFileService(IPurchaseOrderContractFileService purchaseOrderContractFileService) {
		this.purchaseOrderContractFileService = purchaseOrderContractFileService;
	}
	
	public IProcessUserService getProcessUserService() {
		return processUserService;
	}

	public void setProcessUserService(IProcessUserService processUserService) {
		this.processUserService = processUserService;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	//流程名称
	private String process_name;
	//进程id
	private String process_id;
	//任务URL
	private TbTaskUrl taskUrl;
	//任务id
	private String task_id;
	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	private TbProcessUser processUser;


	public TbProcessUser getProcessUser() {
		return processUser;
	}

	public void setProcessUser(TbProcessUser processUser) {
		this.processUser = processUser;
	}

	@Override
	public ICommonService getCommonService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Task model) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIds(String[] ids) {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回任务对应要转入的流程页面URL
	 * @return
	 */
	public String processjump(){
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(process_id).list(); 
		//		List<HistoricTaskInstance> hpiList = ProcessCustomService.getHistoryService().createHistoricTaskInstanceQuery().finished().processInstanceId(process_id).list();
		taskUrl=taskUrlService.findByField("userName", taskList.get(0).getTaskDefinitionKey());
		//销售生成合同
		if(taskList.get(0).getTaskDefinitionKey().equals("saleGenContract")){
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			TbProject tbProject = projectService.getByPk(Integer.parseInt(tbProcessUser.getProjectId()+""));
			url+="?projectid="+tbProject.getProjectId();
			url+="&projectname="+tbProject.getProjectName();
			url+="&processid=";
			taskUrl.setUrl(url);
		}
		//采购填询价单
		else if(taskList.get(0).getTaskDefinitionKey().equals("purchasingFillInquirySheet")||
				taskList.get(0).getTaskDefinitionKey().equals("managerApprovalFillInquirySheet")||
				taskList.get(0).getTaskDefinitionKey().equals("purchasingContract")||
				taskList.get(0).getTaskDefinitionKey().equals("financialPayThe")||
				taskList.get(0).getTaskDefinitionKey().equals("arrivalgoods")){
			String url=taskUrl.getUrl();
			TbContractOrder  tbContractOrder = contractOrderService.findByField("processId", process_id);
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			TbPurchaseOrderContractFile contractFile = purchaseOrderContractFileService.findByField("processId", process_id);
			PurchaseOrderVo purchaseOrder = purchaseOrderService.findByProcessId(process_id);
			TbProject tbProject = projectService.getByPk(Integer.parseInt(tbProcessUser.getProjectId()+""));
			url+="?projectid="+tbProject.getProjectId();
			url+="&projectname="+tbProject.getProjectName();
			if(taskList.get(0).getTaskDefinitionKey().equals("purchasingContract")){
				url+="&quotationsId="+tbContractOrder.getContract().getId();	
			} else if(taskList.get(0).getTaskDefinitionKey().equals("financialPayThe")){	//财务付款
				url+="&quotationsId="+contractFile.getId();
			} else if(taskList.get(0).getTaskDefinitionKey().equals("arrivalgoods")){	//售后确认到货
				
				url+="&quotationsId="+purchaseOrder.getContractOrderId();
			} else {
				url+="&quotationsId="+tbContractOrder.getId();
			}
			url+="&processid=";
			taskUrl.setUrl(url);
		}else if(taskList.get(0).getTaskDefinitionKey().equals("genbuyorder")){
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			url+="?contractOrderId="+tbProcessUser.getContractOrderId();
			url+="&projectname="+tbProcessUser.getProjectName();
			url+="&projcetid="+tbProcessUser.getProjectId();
			url+="&processid=";
			taskUrl.setUrl(url);
		}else if(taskList.get(0).getTaskDefinitionKey().equals("applyTheInvoice")){ //售后流程
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			url+="?contractId="+tbProcessUser.getContractId();
			url+="&customerId="+tbProcessUser.getCustomerId();
			url+="&processid=";
			taskUrl.setUrl(url);
		}else if(taskList.get(0).getTaskDefinitionKey().equals("saleGenOffer")){ //报价表审核流程--生成报价表
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			url+="?projectId="+tbProcessUser.getProjectId();
			url+="&processid=";
			taskUrl.setUrl(url);
		}else if(taskList.get(0).getTaskDefinitionKey().equals("beginTest") //售后流程，上传处到货证明
				|| taskList.get(0).getTaskDefinitionKey().equals("xiaoshoushoudaohuokuan") //售后流程，销售收到货款
				|| taskList.get(0).getTaskDefinitionKey().equals("querenshoudaohuokuan")//售后流程，确认到货收款
				|| taskList.get(0).getTaskDefinitionKey().equals("shangchuanchuyanzhengming") //售后流程，上传初验证明
				|| taskList.get(0).getTaskDefinitionKey().equals("xiaoshouchuyanshoukuan")//售后流程，销售收初验款
				|| taskList.get(0).getTaskDefinitionKey().equals("querenshouchuyankuan") //售后流程，确认收初验款
				|| taskList.get(0).getTaskDefinitionKey().equals("shangchuanzhongyanzhengming")  //售后流程，上传终验证明
				|| taskList.get(0).getTaskDefinitionKey().equals("xiaoshoushouzhongyankuan") //售后流程，销售收终验款
				|| taskList.get(0).getTaskDefinitionKey().equals("quedingshouzhongyankuan")){ //售后流程，确认收终验款
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			url+="?contractId="+tbProcessUser.getContractId();
			url+="&processid=";
			taskUrl.setUrl(url);
		}
		return SUCCESS;
	}

	/**
	 * 历史任务查询业务数据方法,详细待开发...
	 * @return
	 */
	public String historyprocessjump(){
		List<HistoricTaskInstance> hpiList = ProcessCustomService.getHistoryService().createHistoricTaskInstanceQuery().finished().taskId(this.task_id).processInstanceId(process_id).list();
		taskUrl=taskUrlService.findByField("userName", hpiList.get(0).getTaskDefinitionKey());
		if(hpiList.get(0).getTaskDefinitionKey().equals("saleGenContract")){
			String url=taskUrl.getUrl();
			TbProcessUser  tbProcessUser = processUserService.findByField("processId", process_id);
			if(tbProcessUser.getProjectId()==null)
				return SUCCESS;
			TbProject tbProject = projectService.getByPk(Integer.parseInt(tbProcessUser.getProjectId()+""));
			url+="?projectid="+tbProject.getProjectId();
			url+="&projectname="+tbProject.getProjectName();
			url+="&processid=";
			taskUrl.setUrl(url);
		}
		return SUCCESS;
	}
	
	public String processPicJump(){
		List<Task> taskList = ProcessCustomService.getTaskService().createTaskQuery().processInstanceId(process_id).list(); 
		taskUrl = new TbTaskUrl();
		taskUrl.setId(Long.parseLong(taskList.get(0).getId()));
		return SUCCESS;
	}

	public TbTaskUrl getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(TbTaskUrl taskUrl) {
		this.taskUrl = taskUrl;
	}

}
