package cn.com.kxcomm.contractmanage.web.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractDocVariableService;
import cn.com.kxcomm.contractmanage.service.IContractQuotationsService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocVariableService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.ContractDocVariableVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVariableVo;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class ContractTemplatesDocVariableAction extends BaseAction<TbContractTemplatesDocVariable, String>{
	private static Logger LOGGER = Logger.getLogger(ContractTemplatesDocVariableAction.class);
	
	private IContractTemplatesDocVariableService contractTemplatesDocVariableService;
	private IContractDocVariableService contractDocVariableService;
	@Autowired(required=true)
	private IContractTemplatesDocService contractTemplatesDocService;
	@Autowired(required=true)
	private IContractQuotationsService icontractQuotationsService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
    private String templatesDocId;
    
    private String id;
    
    private TbContractTemplatesDocVariable contractTemplatesDocVariable;
    
    private String variableName;
    
    private String description;
    
    private String contractId;
    
	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public IContractQuotationsService getIcontractQuotationsService() {
		return icontractQuotationsService;
	}

	public void setIcontractQuotationsService(
			IContractQuotationsService icontractQuotationsService) {
		this.icontractQuotationsService = icontractQuotationsService;
	}

	public IContractTemplatesDocService getContractTemplatesDocService() {
		return contractTemplatesDocService;
	}

	public void setContractTemplatesDocService(
			IContractTemplatesDocService contractTemplatesDocService) {
		this.contractTemplatesDocService = contractTemplatesDocService;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TbContractTemplatesDocVariable getContractTemplatesDocVariable() {
		return contractTemplatesDocVariable;
	}

	public void setContractTemplatesDocVariable(
			TbContractTemplatesDocVariable contractTemplatesDocVariable) {
		this.contractTemplatesDocVariable = contractTemplatesDocVariable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getTemplatesDocId() {
		return templatesDocId;
	}

	public void setTemplatesDocId(String templatesDocId) {
		this.templatesDocId = templatesDocId;
	}

	public IContractTemplatesDocVariableService getContractTemplatesDocVariableService() {
		return contractTemplatesDocVariableService;
	}

	public void setContractTemplatesDocVariableService(
			IContractTemplatesDocVariableService contractTemplatesDocVariableService) {
		this.contractTemplatesDocVariableService = contractTemplatesDocVariableService;
	}
	
	public IContractDocVariableService getContractDocVariableService() {
		return this.contractDocVariableService;
	}

	public void setContractDocVariableService(
			IContractDocVariableService contractDocVariableService) {
		this.contractDocVariableService = contractDocVariableService;
	}

	/**
	 * 
	* 方法用途和描述: 获取一个文件下所有的变量
	* @return
	* @author lujia 新增日期：2012-12-17
	* @since ContractManage
	 */
	
	   public String listContractTemplatesDocVariable(){
			try {
				if(!BlankUtil.isBlank(templatesDocId)){
					int id = Integer.parseInt(templatesDocId);
					List<ContractTemplatesDocVariableVo> volist = contractTemplatesDocVariableService.listContractTemplatesDocVariable(id);
					PageInfo<ContractTemplatesDocVariableVo> pageInfo = new PageInfo<ContractTemplatesDocVariableVo>();
					pageInfo.setRows(volist);
					pageInfo.setMsg("ok");
					result = JSONObject.fromObject(pageInfo);
				}
			} catch (Exception e) {
				log.error("listContractTemplatesDocVariable error.",e);
			}
			return SUCCESS;
	   }
	   
	   /**
		 * 
		 * 方法用途和描述:保存、修改变量
		 * 
		 * @return
		 * @author luj 新增日期：2012-12-19
		 * @since ContractManage
		 */
	   public String saveAndUpdateVariable() {
		   HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		   try {
			JSONArray jsonArray = JSONArray.fromObject(this.id);
			List<ContractTemplatesDocVariableVo> jsonBeanList = JSONArray.toList(jsonArray,ContractTemplatesDocVariableVo.class);
			if(!BlankUtil.isBlank(jsonBeanList)){
				String templatesDocId = jsonBeanList.get(0).getTemplatesDocId();
				int docId=0;
				if(!BlankUtil.isBlank(templatesDocId)){
					docId = Integer.parseInt(templatesDocId);
				}
				TbContractTemplatesDoc doc = contractTemplatesDocService.getByPk(docId);
				Parameters para = Parameters.getInstance();
				String docPath = para.templatesWordPath+doc.getPath();
				LOGGER.info("docPath:"+docPath);
				String end = this.contractTemplatesDocVariableService.saveAndUpdateVariable(docPath,jsonBeanList);
				systemLogService.writeTheLog(SystemLogType.set_contract_templates_doc_variable, userId, "成功", null, "模版ID为："+templatesDocId);
				this.result = JSONObject.fromObject(successInfo(end));
			}else{
				LOGGER.info("jsonbeanList is null.");
				systemLogService.writeTheLog(SystemLogType.set_contract_templates_doc_variable, userId, "失败", "jsonbeanList is null", null);
			}
			} catch (Exception e) {
				LOGGER.info("saveAndUpdateVariable",e);
				try {
					systemLogService.writeTheLog(SystemLogType.set_contract_templates_doc_variable, userId, "异常", e.getMessage(), null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return SUCCESS;
		}
		
	   /**
		 * 
		 * 方法用途和描述:删除变量
		 * 
		 * @return
		 * @author lujia 新增日期：2012-12-17
		 * @since ContractManage
		 */
		public String deleteContractTemplatesDocVariable() {
			try {
				if(!BlankUtil.isBlank(id)){
				LOGGER.debug("id:"+id);
				String[] ids = id.split(",");
				for (String strId : ids) {
					contractTemplatesDocVariableService.deleteVariable(Integer.parseInt(strId));
				}
				result = JSONObject.fromObject(successInfo("ok"));
				}
			} catch (Exception e) {
				LOGGER.error("deleteContractTemplatesDocVariable ERROR:", e);
			}
			return SUCCESS;
		}

		/**
		 * 
		 * 方法用途和描述:修改变量内容
		 * 
		 * @return
		 * @author lizl 新增日期：2012-12-21
		 * @throws Exception 
		 * @since ContractManage
		 */
		public String saveUpdataContractDocVariable() throws Exception {
			LOGGER.debug(""+this.id);
			JSONArray jsonArray = JSONArray.fromObject(this.id);
			List<ContractDocVariableVo> volist = JSONArray.toList(jsonArray,ContractDocVariableVo.class);
			int contractId = 0;
			//根据合同id查询合同总价
			if(!BlankUtil.isBlank(templatesDocId)){
				contractId = Integer.parseInt(templatesDocId);
			}
			List<ContractQuotationsVo> contractQuotationlist = icontractQuotationsService.queryAllQuotations(contractId);
			String end = this.contractDocVariableService.saveUpdataContractDocVariable(volist,contractQuotationlist,contractId);
			this.result = JSONObject.fromObject(successInfo(end));
			return SUCCESS;
		} 
		
		/**
		 * 
		* 方法用途和描述: 查询合同选中文件的合同文件的变量值
		* @return
		* @author chenliang 新增日期：2013-1-17
		* @since ContractManage
		 */
		public String queryPrice(){
			int contractDocId = 0;
			//根据选中合同文件id查询选中合同文件的变量值
			if(!BlankUtil.isBlank(id)){
				contractDocId = Integer.parseInt(id);
			}
			List<ContractDocVariableVo> volist = contractDocVariableService.queryPrice(contractDocId);
			PageInfo<ContractDocVariableVo> pageInfo = new PageInfo<ContractDocVariableVo>();
			pageInfo.setRows(volist);
			pageInfo.setMsg("ok");
			result = JSONObject.fromObject(pageInfo);
			return SUCCESS;
		}
		
		/**
		 * 根据合同ID查询合同金额
		 * @param contractDocId 合同选中文件id
		 */
		public String totleMoneyByContractId()
		{
			int contract = 0;
			if(!BlankUtil.isBlank(contractId))
			{
				contract=Integer.parseInt(contractId);
			}
		   String totle=contractDocVariableService.totleMoneyByContractId(contract);
		   result = JSONObject.fromObject(successInfo(totle));
			return SUCCESS;
		}
	@Override
	public ICommonService getCommonService() {
	return contractTemplatesDocVariableService;
	}

	@Override
	public TbContractTemplatesDocVariable getModel() {
		if (this.model == null) {
			this.model = new TbContractTemplatesDocVariable();
		}
		return null;
	}

	@Override
	public void setModel(TbContractTemplatesDocVariable model) {
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

}
