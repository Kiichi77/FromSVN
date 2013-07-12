package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.MoneyToChinese;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractQuotations;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractQuotationsService;
import cn.com.kxcomm.contractmanage.service.IContractService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IQuotationsService;
import cn.com.kxcomm.contractmanage.service.impl.ActivitiService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractDetailVo;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.vo.ContractVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class ContractAction extends BaseAction<TbContract, String>{
	private static Logger LOGGER = Logger.getLogger(ContractAction.class);

	@Autowired(required=true)
	private IContractService contractService;
	@Autowired(required=true)
	private IQuotationsService quotationsService;
	@Autowired(required=true)
	private IContractQuotationsService icontractQuotationsService;
	@Autowired(required=true)
	private ActivitiService activitiService;
	@Autowired(required = true)
	private IProcessUserService processUserService;
	public IContractQuotationsService getIcontractQuotationsService() {
		return icontractQuotationsService;
	}

	public void setIcontractQuotationsService(
			IContractQuotationsService icontractQuotationsService) {
		this.icontractQuotationsService = icontractQuotationsService;
	}

	private ContractVo contractVo; //合同vo
	private String processId;
	private String id; 
   private String money;    //金额
   private String projectId;
   private CheckedVo checkedVo; //审核vo
   
	public String getProjectId() {
	return projectId;
}

public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

public void setProjectId(String projectId) {
	this.projectId = projectId;
}

	public String getMoney() {
	return money;
      }

    public void setMoney(String money) {
	this.money = money;
     }

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public ContractVo getContractVo() {
		return contractVo;
	}

	public void setContractVo(ContractVo contractVo) {
		this.contractVo = contractVo;
	}

	public IQuotationsService getQuotationsService() {
		return quotationsService;
	}

	public void setQuotationsService(IQuotationsService quotationsService) {
		this.quotationsService = quotationsService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public IContractService getContractService() {
		return contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}
	
	/**
	 * 
	* 方法用途和描述: 生成正式合同编号
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String officialContractNo(){
		String contractNo = contractService.officialContractNo(null);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contractNo", contractNo);
		map.put("msg", "ok");
		map.put("success", "true");
		result = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 生成临时合同编号
	* @return
	* @author chenliang 新增日期：2013-4-15
	* @since ContractManage
	 */
	public String temporaryContractNo(){
		String contractNo = contractService.temporaryContractNo(null);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contractNo", contractNo);
		map.put("msg", "ok");
		map.put("success", "true");
		result = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述:根据合同id查询合同
	 * @return
	 * @author chenliang 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String getContractById(){
		int pkid = 0;
		if(!BlankUtil.isBlank(pkid)){
			pkid = Integer.parseInt(id);
		}
		TbContract contract = contractService.getByPk(pkid);
		ContractVo vo = new ContractVo();
		vo.setId(""+contract.getId());
		vo.setContractNo(contract.getContractNo());
		vo.setContractTemplatesId(""+contract.getContractTemplates().getId());
		vo.setContractTemplatesName(contract.getContractTemplates().getName());
		vo.setCreateTime(""+contract.getCreatetime());
		vo.setCreatorId(""+contract.getCreator().getId());
		vo.setCreatorName(contract.getCreator().getUserName());
		vo.setDownPath(contract.getDownloadUrl());
		vo.setProjectId(""+contract.getProject().getProjectId());
		vo.setProjectName(contract.getProject().getProjectName());
		vo.setStatus(""+contract.getStatus());
		vo.setTitle(contract.getTitle());
		vo.setContractNumber(contract.getContractNumber());
		String quotations = "";
		String quoid = "";
		Set set = contract.getQuotationsMembers();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			TbContractQuotations tbcq = (TbContractQuotations) iter.next();
			quotations += tbcq.getQuotations().getTitle()+",";
			quoid += tbcq.getQuotations().getId()+",";
		}
		if(!BlankUtil.isBlank(quotations)){
			quotations = quotations.substring(0, quotations.length()-1);
			quoid = quoid.substring(0, quoid.length()-1);
		}
		vo.setQuotationsTitle(quotations);
		vo.setQuotationsId(quoid);
		this.result = JSONObject.fromObject(vo);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 分页获取所有的合同
	 * @return
	 * @author lujia 新增日期：2012-12-11
	 * @since ContractManage
	 */
	public String listContract(){
		try {
			
			PageInfo<ContractVo> page =  new PageInfo<ContractVo>(ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractVo)){
				contractVo = new ContractVo();
			}
			Integer aa=null;
			if(id!=null && !"null".equals(id)){
				System.out.println("id:"+id);
               aa=Integer.parseInt(id);				
			}
			PageInfo<ContractVo> contractList=contractService.listContract(page,contractVo,aa,null);
			result = JSONObject.fromObject(contractList);
		} catch (Exception e) {
			LOGGER.error("listConfigModels ERROR:",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 我的合同
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myContract(){
		try {
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			
			PageInfo<ContractVo> page =  new PageInfo<ContractVo>(ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractVo)){
				contractVo = new ContractVo();
			}
			Integer aa=null;
			if(id!=null){
				System.out.println("id:"+id);
				try{
					 aa=Integer.parseInt(id);			
				}catch(NumberFormatException ee){}
			}
			PageInfo<ContractVo> contractList=contractService.listContract(page,contractVo,aa,userId);
			result = JSONObject.fromObject(contractList);
		} catch (Exception e) {
			LOGGER.error("listConfigModels ERROR:",e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述:删除合同
	 * @return
	 * @author lujia 新增日期：2012-12-11
	 * @since ContractManage
	 */
	public String deleteContract()
	{
		try {
			LOGGER.debug("id:"+id);
			String[] ids = id.split(",");
			for (String strId : ids) {
				contractService.deleteByPk(Integer.parseInt(strId));
			}
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("deleteContract ERROR:", e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述:添加合同
	 * @return
	 * @author chenliang 新增日期：2012-12-17
	 * @since ContractManage
	 */
	public String addOrUpdateContract()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if(BlankUtil.isBlank(contractVo)){
				contractVo = new ContractVo();
			}
			HttpSession session = getHttpSession();
			String userid = ((Long) session.getAttribute(SessionUtils.USER_ID)).toString();
			contractVo.setCreatorId(userid); //获取当前用户的id
			//根据id判断是添加还是修改
			Integer contractId = 0;
			if(!BlankUtil.isBlank(contractVo.getId()) && !"null".equals(contractVo.getId())){
				contractId = contractService.updateContractByState(contractVo);
			}else{
				contractId = contractService.saveContract(contractVo);
			}
			map.put("contractId", ""+contractId);
			map.put("msg", "ok");
			map.put("success", "true");
			result = JSONObject.fromObject(map);
		} catch (Exception e) {
			map.put("contractId", "0");
			map.put("msg", "no");
			map.put("success", "true");
			result = JSONObject.fromObject(map);
			LOGGER.error("addContract ERROR:", e);
		}
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 合同模板详情内容
	 * @return
	 * @author chenliang 新增日期：2012-12-20
	 * @since ContractManage
	 */
	public String contractDetail(){
		try {
			if(!BlankUtil.isBlank(id)){
				int contractId = Integer.parseInt(id);
				List<ContractDetailVo> detailVolist = contractService.listContractDetail(contractId);
				PageInfo<ContractDetailVo> pageInfo = new PageInfo<ContractDetailVo>();
				pageInfo.setRows(detailVolist);
				pageInfo.setTotal(detailVolist.size());
				result = JSONObject.fromObject(pageInfo);
			}
		} catch (Exception e) {
			LOGGER.error("contractDetail error.",e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据合同id查询相应的合同文件
	 * @return
	 * @author chenliang 新增日期：2012-12-21
	 * @since ContractManage
	 */
	public String listContractTemplates(){
		try {
			if(!BlankUtil.isBlank(id)){
				int contractId = Integer.parseInt(id);
				PageInfo<ContractTemplatesDocVo> pageinfo = contractService.listContractTemplates(contractId);
				result = JSONObject.fromObject(pageinfo);
			}else{
				LOGGER.info("id is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listContractTemplates error.",e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 获取所有的合同
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryAllContract() {
		try {
			PageInfo<ContractVo> page = new PageInfo<ContractVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.contractService.queryAllContract();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllContract ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 获取所有的合同
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String findAll() {
		try {
			PageInfo<ContractVo> page = new PageInfo<ContractVo>(ServletActionContext.getRequest());
			List<TbContract> list = new ArrayList();
			list=this.contractService.findAll();
			List<ContractVo> volist = new ArrayList<ContractVo>();
			for (TbContract tbContract : list) {
				ContractVo vo = new ContractVo();
				vo.setId(""+tbContract.getId());
				vo.setTitle(tbContract.getTitle());
				volist.add(vo);
			}
			page.setRows(volist);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllContract ERROR:", e);
		}
		return SUCCESS;
	}

	public String submitCheck()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			contractService.submitCheck(checkedVo);
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("checkRapaport error.",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 小写数字转成大写数字
	 * @return
	 * @author lujia 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String toChineseCharacter()
	{
	  try {
		String chineseCharacterMoney=MoneyToChinese.toChineseCharacter(Double.parseDouble(money));
		result = JSONObject.fromObject(successInfo(chineseCharacterMoney));
	  }catch (Exception e) {
		LOGGER.error("toChineseCharacter ERROR:", e);
	}
	  return SUCCESS;
	}
	@Override
	public ICommonService getCommonService() {
		return contractService;
	}

	@Override
	public TbContract getModel() {
		if (this.model == null) {
			this.model = new TbContract();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContract model) {
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
