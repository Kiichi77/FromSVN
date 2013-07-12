package cn.com.kxcomm.contractmanage.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.TemplateCheckedDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbTemplateChecked;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesService;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.service.ITemplateChecked;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;
import cn.com.kxcomm.contractmanage.web.util.TaskName;

@Service("templateChecked")
public class TemplateCheckedService extends CommonService<TbTemplateChecked> implements ITemplateChecked {

	private static final Logger LOGGER =  Logger.getLogger(TemplateCheckedService.class);
	
	@Autowired(required=true)
	private TemplateCheckedDAO templateCheckedDAO;
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Autowired(required=true)
	private ITaskService taskService;
	@Autowired(required=true)
	private IContractTemplatesService contractTemplatesService;
	
	@Override
	public CommonDAO<TbTemplateChecked> getBindDao() {
		return templateCheckedDAO;
	}

	@Override
	public void checked(CheckedVo checkedVo) throws Exception {
		Long uid = 0L;  //创建人员
		int status = 0 ;  //状态
		String param = ""; //请求参数
		Long userId = null; //负责人
		try {
			if(!BlankUtil.isBlank(checkedVo.getStatus())){
				status=Integer.parseInt(checkedVo.getStatus());
			}
			if(!BlankUtil.isBlank(checkedVo.getSnpnorId())){
				uid = Long.parseLong(checkedVo.getSnpnorId());
			}
			if(!BlankUtil.isBlank(checkedVo.getUser_id()) && !"null".equals(checkedVo.getUser_id())){
				userId  = Long.parseLong(checkedVo.getUser_id());
			}
			
			//完成销售,财务，经理的代办任务
			TaskVo taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.template_pkid+checkedVo.getContractTemplateId());
			taskService.finishTask(taskVo);
			
			//完成销售,财务，经理的打回的待办任务
			taskVo = new TaskVo();
			taskVo.setParamPkid(BusinessConstants.unTemplate_pkid+checkedVo.getContractTemplateId());
			taskService.finishTask(taskVo);
			
			//任务控制
			if(BusinessConstants.check_pass.equals(checkedVo.getResult())){  //审核通过
				//更新合同模板状态为已审核
				TbContractTemplates tbContractTemplates = contractTemplatesService.getByPk(Integer.parseInt(checkedVo.getContractTemplateId()));
				if(BusinessConstants.template_check_now == status || BusinessConstants.template_check_financialUnpass==status){  //未审核,销售审核
					param = "id="+checkedVo.getContractTemplateId()+"&status="+BusinessConstants.template_check_sellPass;
					//给财务任务通知
					taskService.startTask(null, uid, TaskName.audit_caiwu_templateCheck+"("+tbContractTemplates.getName()+")", null, 
							userId, BusinessConstants.task_caiwu_templateCheck, 
							checkedVo.getDescribe(),param,BusinessConstants.template_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：销售审核通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_sellPass);
					
				}else if(BusinessConstants.template_check_sellPass == status || BusinessConstants.template_check_generalManagerUnpass==status){  //财务审核
					param = "id="+checkedVo.getContractTemplateId()+"&status="+BusinessConstants.template_check_financialPass;
					//给经理任务通知
					taskService.startTask(null, uid, TaskName.audit_manager_templateCheck, null, userId, 
							BusinessConstants.task_generalManager_templateCheck, checkedVo.getDescribe(),
							param,BusinessConstants.template_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：财务审核通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_financialPass);
					
				}else if(BusinessConstants.template_check_financialPass == status){ //经理审核
					//不给销售任务通知，做报价表
					taskService.startTask(null, uid, TaskName.sale_make_quatitions, null, userId, 
							BusinessConstants.task_templateCheck_finish, checkedVo.getDescribe(),
							param,BusinessConstants.template_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：经理审核通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_generalManagerPass);
					
				}
				contractTemplatesService.update(tbContractTemplates);
				
			}else if(BusinessConstants.check_unpass.equals(checkedVo.getResult())){ //审核不通过,驳回
				//更新合同模板状态为审核不通过
				TbContractTemplates tbContractTemplates = contractTemplatesService.getByPk(Integer.parseInt(checkedVo.getContractTemplateId()));
				if(BusinessConstants.template_check_now == status || BusinessConstants.template_check_financialUnpass==status){  //未审核,销售审核不通过
					param = "id="+checkedVo.getContractTemplateId()+"&iscontext="+BusinessConstants.template_check_sellUnpass;
					//通知系统人员
					taskService.startTask(null, uid, TaskName.audit_contract_templates, null, userId, 
							BusinessConstants.task_sale_templateCheck_Unpass, checkedVo.getDescribe(),
							param,BusinessConstants.unTemplate_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：销售审核不通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_sellUnpass);
				}else if(BusinessConstants.template_check_sellPass == status || BusinessConstants.template_check_generalManagerUnpass==status){  //财务审核
					param = "id="+checkedVo.getContractTemplateId()+"&status="+BusinessConstants.template_check_financialUnpass;
					//给销售任务通知
					taskService.startTask(null, uid, TaskName.audit_contract_templates, null, userId, 
							BusinessConstants.task_caiwu_templateCheck_Unpass, checkedVo.getDescribe(),
							param,BusinessConstants.unTemplate_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：财务审核不通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_financialUnpass);
				}else if(BusinessConstants.template_check_financialPass == status){ //经理审核
					param = "id="+checkedVo.getContractTemplateId()+"&status="+BusinessConstants.template_check_generalManagerUnpass;
					//给财务任务通知
					taskService.startTask(null, uid, TaskName.audit_caiwu_templateCheck, null, userId, 
							BusinessConstants.task_generalmanager_templateCheck_Unpass, checkedVo.getDescribe(),
							param,BusinessConstants.unTemplate_pkid+tbContractTemplates.getId());
					//更新合同模板状态为：经理审核不通过
					tbContractTemplates.setStatus(BusinessConstants.template_check_generalManagerUnpass);
				}
				contractTemplatesService.update(tbContractTemplates);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
