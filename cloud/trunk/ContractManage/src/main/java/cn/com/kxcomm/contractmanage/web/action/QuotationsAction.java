package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IConfigModelsService;
import cn.com.kxcomm.contractmanage.service.IModelDataService;
import cn.com.kxcomm.contractmanage.service.IQuotationsDataService;
import cn.com.kxcomm.contractmanage.service.IQuotationsService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.impl.ActivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ProcessUserService;
import cn.com.kxcomm.contractmanage.vo.CheckedQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.vo.QuotationDataInfoBean;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;
import cn.com.kxcomm.contractmanage.web.util.ExportQuotations;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class QuotationsAction extends BaseAction<TbQuotations, String> {
	private static final long serialVersionUID = 3867583207762916551L;

	private static Logger LOGGER = Logger.getLogger(QuotationsAction.class);

	@Autowired(required=true)
	private IQuotationsService quotationsService;  //报价业务类
	@Autowired(required=true)
	private IQuotationsDataService quotationsDataService; //报价与机型配置数据业务类
	@Autowired(required=true)
	private IConfigModelsService configModelsService;//配置单总表
	@Autowired(required=true)
	private IModelDataService modelDataService;//配置单详情表
	@Autowired(required=true)
	private QuotationsDataDAO quotationsDataDAO;
	@Autowired(required = true)
	private ActivitiService activitiService;
	@Autowired(required = true)
	private ProcessUserService processUserService;
	private Parameters para = Parameters.getInstance();
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	private String id;   //模板id
	private String sourceId; //数据源id
	private String contractId;    //合同ID
	private String user_id;
	private String quotationsId; //报价表id
	private String projectId;
	private CheckedVo checkedVo; //审核vo
	private String process_name;
	private String catalogTotalprice; 	//目录合价
	private String afterDiscountPrice;	//折扣后价格
	private String afterDiscountNowPrice ;	//折扣后现场价
	private String totalPrice;	//合价
	private String baoJiaTable; 	//报价总表json数据信息
	
	public String getBaoJiaTable() {
		return this.baoJiaTable;
	}

	public void setBaoJiaTable(String baoJiaTable) {
		this.baoJiaTable = baoJiaTable;
	}

	public String getCatalogTotalprice() {
		return this.catalogTotalprice;
	}

	public void setCatalogTotalprice(String catalogTotalprice) {
		this.catalogTotalprice = catalogTotalprice;
	}

	public String getAfterDiscountPrice() {
		return this.afterDiscountPrice;
	}

	public void setAfterDiscountPrice(String afterDiscountPrice) {
		this.afterDiscountPrice = afterDiscountPrice;
	}

	public String getAfterDiscountNowPrice() {
		return this.afterDiscountNowPrice;
	}

	public void setAfterDiscountNowPrice(String afterDiscountNowPrice) {
		this.afterDiscountNowPrice = afterDiscountNowPrice;
	}

	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getQuotationsId() {
		return quotationsId;
	}

	public void setQuotationsId(String quotationsId) {
		this.quotationsId = quotationsId;
	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IQuotationsService getQuotationsService() {
		return quotationsService;
	}

	public void setQuotationsService(IQuotationsService quotationsService) {
		this.quotationsService = quotationsService;
	}

	public IQuotationsDataService getQuotationsDataService() {
		return quotationsDataService;
	}

	public void setQuotationsDataService(
			IQuotationsDataService quotationsDataService) {
		this.quotationsDataService = quotationsDataService;
	}

	public IConfigModelsService getConfigModelsService() {
		return configModelsService;
	}

	public void setConfigModelsService(IConfigModelsService configModelsService) {
		this.configModelsService = configModelsService;
	}

	public IModelDataService getModelDataService() {
		return this.modelDataService;
	}

	public void setModelDataService(IModelDataService modelDataService) {
		this.modelDataService = modelDataService;
	}

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 
	 * 方法用途和描述: 根据合同id查询报价表和未被合同选中的报价表
	 * @return
	 * @author chenliang 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryByContractId(){
		try {
			Integer inta=null;
			if(projectId!=null){
				inta=Integer.parseInt(projectId);
			}
			List<QuotationsVo> volist = quotationsService.listByContractId(contractId,inta);
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>();
			page.setRows(volist);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryByContractId error:",e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 查询所有没有被合同选中的报价表
	 * @return
	 * @author chenliang 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String listAll(){
		try {
			 HttpSession session = getHttpSession();
			 Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			List<QuotationsVo> volist = quotationsService.listAll(userId,contractId);
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>();
			page.setRows(volist);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("listAll error:",e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 获取所有的报价单
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-10
	 * @since ContractManage
	 */

	public String listQuotations() {
		try {
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(ServletActionContext.getRequest());
			PageInfo<QuotationsVo> quotationsList = quotationsService.listQuotations(page,null);
			result = JSONObject.fromObject(quotationsList);
		} catch (Exception e) {
			LOGGER.error("listQuotations ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 我的报价表
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myQuotations() {
		try {
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(ServletActionContext.getRequest());
			 HttpSession session = getHttpSession();
			 Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			PageInfo<QuotationsVo> quotationsList = quotationsService.listQuotations(page,userId);
			result = JSONObject.fromObject(quotationsList);
		} catch (Exception e) {
			LOGGER.error("listQuotations ERROR:", e);
		}
		return SUCCESS;
	}

	
	/**
	 * 
	 * 方法用途和描述: 删除选中的报价表
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-10
	 * @since ContractManage
	 */
	public String deleteQuotations() {
		try {
			LOGGER.debug("id:" + id);
			String[] ids = id.split(",");
			for (String strId : ids) {
				quotationsService.deleteByPk(Integer.parseInt(strId));
			}
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("deleteQuotations ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 保存报价表和报价表与配置单的关系，生成报价表
	 * @return
	 * @author chenliang 新增日期：2012-12-14
	 * @since ContractManage
	 */
	public String saveAuotions() {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));//用户id
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("baoJiaTable", baoJiaTable);
			map.put("userId", ""+userId);
			quotationsService.saveAuotions(map,getModel());
			//添加日志
			systemLogService.writeTheLog(SystemLogType.generate_quotations_sheet, userId, "成功", null, "报价表名称:"+getModel().getTitle());
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				systemLogService.writeTheLog(SystemLogType.generate_quotations_sheet, userId, "失败", e.getMessage(), "报价表名称:"+getModel().getTitle());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 审核报价表
	 * @return
	 * @author chenliang 新增日期：2012-12-14
	 * @since ContractManage
	 */
	public String checkRapaport()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			quotationsService.checkQuotation(quotationsId, checkedVo);
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("checkRapaport error.",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 提交审核
	 * @return
	 * @author chenliang 新增日期：2012-12-14
	 * @since ContractManage
	 */
	public String submitCheck()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			quotationsService.submitCheck(checkedVo);
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("checkRapaport error.",e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 重新生成报价表
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-12-27
	 * @since ContractManage
	 */
	public String redoAuotions(){
		try{
			if(id==null || "".equals(id))return SUCCESS;
			TbQuotations quotations = quotationsService.getByPk(Integer.parseInt(id));
			List<TbQuotationsData> dataList = quotationsDataDAO.queryDataById(quotations.getId());
			String[] ids = new String[dataList.size()];
			int i = 0;
			for (TbQuotationsData d : dataList) {
				ids[i] = d.getConfigModels().getId().toString();
				i++;
			}
			List<List<DataRecordVo>> volist = configModelsService
			.listConfigModelByIds(ids);
			String fileName = quotations.getDownUrl();
			String exportDoc = para.exportExcelPath + fileName;
			String templatesDoc = para.templatesExcelPath
			+ "quotations_templates.xlsx";
			ExportQuotations exportQuotations = new ExportQuotations();
			exportQuotations.export(templatesDoc, exportDoc, volist);
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 查询配置模板信息
	 * @return
	 * @author chenliang 新增日期：2012-12-17
	 * @since ContractManage
	 */
	public String queryQuotationInfo() {
		List<DataRecordVo> quotationsList = new ArrayList<DataRecordVo>();
		try {
			int configModelId = BlankUtil.isBlank(this.id)?0:Integer.parseInt(this.id);
			int dataSourceId = null==this.sourceId||"".equals(this.sourceId)?0:Integer.parseInt(this.sourceId);
			quotationsList = this.configModelsService.listStanded(configModelId,dataSourceId);
			PageInfo<DataRecordVo> pageInfo = new PageInfo<DataRecordVo>();
			pageInfo.setRows(quotationsList);
			this.result = JSONObject.fromObject(pageInfo);
		} catch (Exception e) {
			result = JSONObject.fromObject(successInfo("error"));
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 获取标配信息
	 * @return
	 * @author chenliang 新增日期：2012-12-26
	 * @since ContractManage
	 */
	public String getStandard(){
		try {
			int dataSourceId = null==this.sourceId||"".equals(this.sourceId)?0:Integer.parseInt(this.sourceId);
			List<DataRecordVo> quotationslist = this.configModelsService.getStandard(dataSourceId);
			PageInfo<DataRecordVo> pageInfo = new PageInfo<DataRecordVo>();
			pageInfo.setRows(quotationslist);
			this.result = JSONObject.fromObject(pageInfo);
		} catch (Exception e) {
			result = JSONObject.fromObject(successInfo("error"));
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述:查询所有报价集合
	 * 
	 * @return
	 * @author luj 新增日期：2012-12-14
	 * @since ContractManage
	 */
	public String queryAllQuotations() {
		try {
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.quotationsService.queryAllQuotations();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllQuotations ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据合同获取报价表
	 * @return
	 * @author luj 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryAllQuotationseByContract(){
		try {
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			log.debug("*****************"+contractId);
			list=this.quotationsService.queryAllQuotationseByContract(Integer.parseInt(contractId));

			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllQuotationseByContract ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述:保存、修改模板信息
	 * 
	 * @return
	 * @author lizl 新增日期：2012-12-18
	 * @since ContractManage
	 */
	public String saveAndUpdateQuotation() {
		JSONArray jsonArray = JSONArray.fromObject(this.id);
		List<QuotationDataInfoBean> jsonBeanList = JSONArray.toList(jsonArray,QuotationDataInfoBean.class);
		String end = this.modelDataService.saveAndUpdateQuotation(jsonBeanList);
		//修改目录总价，折扣后价格，折扣后现场价，合计
		TbConfigModels conModels = this.configModelsService.getByPk(Integer.parseInt(jsonBeanList.get(0).getConfigId()));
		conModels.setAfterDiscountNowPrice(Double.parseDouble(afterDiscountNowPrice));
		conModels.setAfterDiscountPrice(Double.parseDouble(afterDiscountPrice));
		conModels.setCatalogTotalprice(Double.parseDouble(catalogTotalprice));
		conModels.setTotalPrice(Double.parseDouble(totalPrice));
		configModelsService.update(conModels);
		this.result = JSONObject.fromObject(successInfo(end));
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 保存标准配置信息
	 * @return
	 * @author chenliang 新增日期：2012-12-26
	 * @since ContractManage
	 */
	public String saveStandard(){
		HttpSession session = getHttpSession();
		Long createId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			String end = "ok";
			if(!BlankUtil.isBlank(this.id)){
				int sourceDataId = BlankUtil.isBlank(sourceId)?0:Integer.parseInt(sourceId);
				JSONArray jsonArray = JSONArray.fromObject(this.id);
				List<QuotationDataInfoBean> jsonBeanList = JSONArray.toList(jsonArray,QuotationDataInfoBean.class);
				end = this.modelDataService.saveStandard(jsonBeanList,sourceDataId,createId);
				systemLogService.writeTheLog(SystemLogType.set_the_standard_config_models, createId, "成功", null, "数据源ID为:"+sourceDataId);
			}else{
				end = "no";
				systemLogService.writeTheLog(SystemLogType.set_the_standard_config_models, createId, "失败", null, null);
			}
			this.result = JSONObject.fromObject(successInfo(end));
		} catch (Exception e) {
			LOGGER.info("saveStandard error.",e);
			try {
				systemLogService.writeTheLog(SystemLogType.set_the_standard_config_models, createId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据报价表id查询该报价表的机型配置
	 * @return
	 * @author chenliang 新增日期：2013-3-8
	 * @since ContractManage
	 */
	public String queryByQuotationId(){
		int pkid=0;
		try {
			if(!BlankUtil.isBlank(quotationsId)){
				pkid = Integer.parseInt(quotationsId);
			}
			List<QuotationsVo> quotationsList = quotationsDataService.queryByQuotationId(pkid);
			PageInfo<QuotationsVo> pageinfo = new PageInfo<QuotationsVo>();
			pageinfo.setRows(quotationsList);
			this.result = JSONObject.fromObject(pageinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String queryQuotation(){
		int pkid=0;
		try {
			if(!BlankUtil.isBlank(quotationsId)){
				pkid = Integer.parseInt(quotationsId);
			}
			 TbQuotations  rs = this.quotationsService.getByPk(pkid);
			 if(rs!=null){
				 QuotationsVo v = new QuotationsVo();
				 v.setId(rs.getId().toString());
				 v.setTitle(rs.getTitle());
				 this.result = JSONObject.fromObject(v);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public ICommonService getCommonService() {
		return quotationsService;
	}

	@Override
	public TbQuotations getModel() {
		if(this.model == null){
			this.model = new TbQuotations();
		}
		return this.model;
	}

	@Override
	public void setModel(TbQuotations model) {
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
}
