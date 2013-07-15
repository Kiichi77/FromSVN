package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.activiti.tools.ProcessCustomService;
import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.service.ITemplateChecked;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ProcessUserService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

public class ContractTemplatesAction extends BaseAction<TbContractTemplates, String>{
	private static final long serialVersionUID = -7927201329054253504L;
	private static Logger LOGGER = Logger.getLogger(ContractTemplatesAction.class);
	@Autowired(required=true)
	private IContractTemplatesService contractTemplatesService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	@Autowired(required=true)
	private ITemplateChecked templateChecked;
	@Autowired(required=true)
	private ProcessUserService processUserService;
	private TbContractTemplates contractTemplates;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	@Autowired(required=true)
	private ITaskService taskService;
	
	private String name;

	private String id;
	
	private String processId;
    
	private String templatesId;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getTemplatesId() {
		return templatesId;
	}

	public void setTemplatesId(String templatesId) {
		this.templatesId = templatesId;
	}

	//用于返回前台对象
	private JSONObject result;

	private CheckedVo checkedVo; //审核vo

	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}
	
	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TbContractTemplates getContractTemplates() {
		return contractTemplates;
	}

	public void setContractTemplates(TbContractTemplates contractTemplates) {
		this.contractTemplates = contractTemplates;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public IContractTemplatesService getContractTemplatesService() {
		return contractTemplatesService;
	}

	public void setContractTemplatesService(
			IContractTemplatesService contractTemplatesService) {
		this.contractTemplatesService = contractTemplatesService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	/**
	 * 
	 * 方法用途和描述: 获取所有的合同模版
	 * @return
	 * @author lujia 新增日期：2012-12-10
	 * @since ContractManage
	 */

	public String listContractTemplates(){
		try {
			PageInfo<ContractTemplatesVo> page =  new PageInfo<ContractTemplatesVo>(ServletActionContext.getRequest());
			PageInfo<ContractTemplatesVo> contractTemplatesList=this.contractTemplatesService.listContractTemplates(page);
			this.result = JSONObject.fromObject(contractTemplatesList);
		} catch (Exception e) {
			LOGGER.error("listConfigModels ERROR:",e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 删除选中的合同模版
	 * @return
	 * @author lujia 新增日期：2012-12-10
	 * @since ContractManage
	 */
	public String deleteContractTemplates()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			LOGGER.debug("id:"+id);
			String[] ids = id.split(",");
			for (String strId : ids) {
				contractTemplatesService.deleteByPk(Integer.parseInt(strId));
				systemLogService.writeTheLog(SystemLogType.del_contract_templates, userId, "成功", null, "合同模版ID为:"+strId);
			}
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("deleteContractTemplates ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.del_contract_templates, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 添加合同模版
	 * @return
	 * @author lujia 新增日期：2012-12-10
	 * @since ContractManage
	 */
	public String addContractTemplates(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			contractTemplates.setStatus(1);
			contractTemplates.setCreatetime(new Date());
			this.id = ""+contractTemplatesService.save(contractTemplates);
			this.result = JSONObject.fromObject(successInfo(""+this.id));
			systemLogService.writeTheLog(SystemLogType.add_contract_templates, userId, "成功", null, "合同模版ID为："+contractTemplates.getId());
		}catch(Exception e)
		{
			LOGGER.error("addContractTemplates ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.del_tb_data_source, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述:查询所有合同模版集合
	 * 
	 * @return
	 * @author luj 新增日期：2012-12-14
	 * @since ContractManage
	 */
	public String queryAllContractTemplates() {
		try {
			PageInfo<ContractTemplatesVo> page = new PageInfo<ContractTemplatesVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.contractTemplatesService.queryAllContractTemplates();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllContractTemplates ERROR:", e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 根据ID查询合同模版的信息
	 * @return
	 * @author lujia 新增日期：2012-12-18
	 * @since ContractManage
	 */
	public String queryTemplatesById()
	{
		LOGGER.info("id:  "+this.id);
		ContractTemplatesVo contractTemplatesVo = this.contractTemplatesService.queryTemplatesById(this.id);
		Map map = new HashMap();
		map.put("contractTemplates",contractTemplatesVo);
		this.result = JSONObject.fromObject(map);//
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 修改合同模版
	 * @return
	 * @author lujia 新增日期：2012-12-18
	 * @since ContractManage
	 */
	public String updateContractTemplates()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		contractTemplates=contractTemplatesService.getByPk(Integer.parseInt(id));
		contractTemplates.setId(Integer.parseInt(id));
		contractTemplates.setCreatetime(new Date());
		contractTemplates.setName(this.name);
		contractTemplatesService.update(contractTemplates);
		result = JSONObject.fromObject(successInfo("ok"));
		systemLogService.writeTheLog(SystemLogType.edit_contract_templates, userId, "成功", null, "合同模版ID为:"+id);
	} catch (Exception e) {
		LOGGER.error("updateContractTemplates ERROR:", e);
		try {
			systemLogService.writeTheLog(SystemLogType.edit_contract_templates, userId, "异常", e.getMessage(), null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 审核合同模板
	 * @return
	 * @author chenliang 新增日期：2013-3-1
	 * @since ContractManage
	 */
	public String checkContractTemplate(){
		try {
			HttpSession session = getHttpSession();
			Long uid = (Long) session.getAttribute(SessionUtils.USER_ID);
			if(!BlankUtil.isBlank(checkedVo)){
				LOGGER.info("user_id"+checkedVo.getUser_id()+",process_id:"+checkedVo.getProcess_id()+",reject_process_id:"+checkedVo.getReject_process_id());
				checkedVo.setSnpnorId(""+uid);
				//更新数据库
				templateChecked.checked(checkedVo);
				
			}else{
				LOGGER.info("checkedvo is null.");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 提交申请合同模板审核
	 * @return
	 * @author lizl 新增日期：2013-4-3
	 * @since ContractManage
	 */
	public String startMoBanShenHeProcess() {
		HttpSession session = getHttpSession();
		Long uid = (Long) session.getAttribute(SessionUtils.USER_ID);
		try {
			 
			TbContractTemplates tbContractTemplates=this.contractTemplatesService.getByPk(Integer.parseInt(this.templatesId));
			tbContractTemplates.setStatus(BusinessConstants.template_check_now);
			contractTemplatesService.update(tbContractTemplates);
			
			//申请提交审核合同模板任务
			String param = "id="+tbContractTemplates.getId()+"&status="+tbContractTemplates.getStatus();
			this.taskService.startTask(null,uid,TaskName.audit_contract_templates+"("+tbContractTemplates.getName()+")",null,
					Long.parseLong(this.id),BusinessConstants.task_sale_templateCheck,
					this.name,param,BusinessConstants.template_pkid+templatesId);
			
			//完成销售,财务，经理的打回的待办任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.unTemplate_pkid+tbContractTemplates.getId());
			taskService.finishTask(taskVo);
			
			result = JSONObject.fromObject(successInfo("ok"));
			systemLogService.writeTheLog(SystemLogType.submit_audit_contract_templates, uid, "成功", null, "合同模版ID为："+templatesId);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.systemLogService.writeTheLog(SystemLogType.submit_templateCheck, uid, "失败", e.getMessage(), TaskName.audit_contract_templates);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return contractTemplatesService;
	}

	@Override
	public TbContractTemplates getModel() {
		if (this.model == null) {
			this.model = new TbContractTemplates();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractTemplates model) {
		this.model=model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids=ids;

	}

	public void aa(){
		System.out.println("aa");
	}

}
