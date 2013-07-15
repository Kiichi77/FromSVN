package cn.com.kxcomm.contractmanage.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractReceivePaymentDataService;
import cn.com.kxcomm.contractmanage.service.IContractReceivePaymentService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentVo;
import cn.com.kxcomm.contractmanage.vo.ContractReceivePaymentVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class ContractPaymentAction extends BaseAction<TbContractReceivePayment, String>{
	private static Logger LOGGER = Logger.getLogger(ContractPaymentAction.class);
	private static final long serialVersionUID = -2690590900548557309L;
	private TbContractReceivePayment contractReceivePayment;
	@Autowired(required = true)
	private IContractReceivePaymentService icontractReceivePaymentService;
	@Autowired(required = true)
	private IContractReceivePaymentDataService contractReceivePaymentDataService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	
	@Autowired(required = true)
	private IProcessUserService processUserService;
	
	private PageInfo pageInfo;
	private String id;
	private ContractPaymentDataVo payData;
	private CheckedVo checkedVo; //审核vo
	private String contractId;
	private String contractStatus;
	
	
	
	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	@Override
	public ICommonService getCommonService() {
		return icontractReceivePaymentService;
	}


	public ContractPaymentDataVo getPayData() {
		return payData;
	}


	public void setPayData(ContractPaymentDataVo payData) {
		this.payData = payData;
	}


	@Override
	public TbContractReceivePayment getModel() {
		if (this.model == null) {
			this.model = new TbContractReceivePayment();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractReceivePayment model) {
		this.model=model;
	}

	public String getId() {
		return id;
	}

	public TbContractReceivePayment getContractReceivePayment() {
		return contractReceivePayment;
	}

	public void setContractReceivePayment(
			TbContractReceivePayment contractReceivePayment) {
		this.contractReceivePayment = contractReceivePayment;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	/**
	 * 
	* 方法用途和描述: 提价财务
	* @return
	* @author chenliang 新增日期：2013-3-22
	* @since ContractManage
	 */
	public String sumbitPaymentCaiwu(){
		
		return SUCCESS;
	}

	/**
	 * 
	* 方法用途和描述: 根据合同ID查询付款项
	* @return
	* @author lujia 新增日期：2013-3-15
	* @since ContractManage
	 */
	public String contractPaymentBycontractId()
	{
		try {
			ContractReceivePaymentVo vo =this.icontractReceivePaymentService.getcontractReceivePayment(Integer.parseInt(id));
	    	System.out.println("vo:"+Json.toJson(vo));
			Map map = new HashMap();
			map.put("contractReceivePayment",vo);
			this.result = JSONObject.fromObject(map);//
		} catch (Exception e) {
			LOGGER.error("contractPaymentBycontractId ERROR:",e);
		}
		return SUCCESS;
	}
	
	public String contractPaymentList(){
		List<ContractPaymentVo> list = icontractReceivePaymentService.contractPaymentList(id,null);
		PageInfo<ContractPaymentVo> pageInfo = new PageInfo<ContractPaymentVo>();
		pageInfo.setRows(list);
		pageInfo.setTotal(list.size());
		result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 返回销售的合同收款
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String paymentListBySales(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		List<ContractPaymentVo> list = icontractReceivePaymentService.contractPaymentList(id,userId);
		PageInfo<ContractPaymentVo> pageInfo = new PageInfo<ContractPaymentVo>();
		pageInfo.setRows(list);
		pageInfo.setTotal(list.size());
		result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	public String contractPaymentDataList(){
		if(this.id==null && "".equals(id.trim())){
			return ERROR;
		}
		Integer pk = Integer.parseInt(id);
		 try {
			   PageInfo<ContractPaymentDataVo> page =  new PageInfo<ContractPaymentDataVo>(ServletActionContext.getRequest());
			  
			   PageInfo<ContractPaymentDataVo> rs=icontractReceivePaymentService.contractPaymentDataList(page,pk,null);
			   result = JSONObject.fromObject(rs);
			} catch (Exception e) {
				LOGGER.error("listProject ERROR:",e);
			}
		    return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改收款项
	* @return
	* @author lujia 新增日期：2013-3-15
	* @since ContractManage
	 */
	public String updatePayment(){
		if(contractReceivePayment==null){
			this.msg="传入对象为空";
			return ERROR;
		}
		icontractReceivePaymentService.saveOrUpdate(contractReceivePayment);
		this.result=JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 合同收款
	* @return
	* @author chenliang 新增日期：2013-3-26
	* @since ContractManage
	 */
	public String addPaymentData(){
		if(payData==null || checkedVo==null){
			return NONE;
		}
		System.out.println(this.payData.toString());
		//1、添加合同收款明细
		payData.setFinancialId(Long.parseLong(checkedVo.getUser_id()!=null?checkedVo.getUser_id():"0"));
		boolean end = this.contractReceivePaymentDataService.addPaymentData(payData);
		System.out.println("end:  "+end);
		
		this.result=JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	public String paymentDataList(){
		try {
			   PageInfo<ContractPaymentDataVo> page =  new PageInfo<ContractPaymentDataVo>(ServletActionContext.getRequest());
			  
			   PageInfo<ContractPaymentDataVo> list=contractReceivePaymentDataService.contractAllPaymentDataList(page,id);
			   result = JSONObject.fromObject(list);
			} catch (Exception e) {
				LOGGER.error("paymentDataList ERROR:",e);
			}
		    return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 确认收款
	* @return
	* @author zhangjh 新增日期：2013-3-19
	* @editor chenliang 修改日期:2013-3-23
	* @since ContractManage
	 */
	public String confirMoney(){
		System.out.println("===============confirMoney"+payData.toString()+",checkedVo:"+checkedVo.toString());
		if(!BlankUtil.isBlank(payData) && !BlankUtil.isBlank(checkedVo)){
			contractReceivePaymentDataService.confirMoney(payData);
			this.result=JSONObject.fromObject(successInfo("ok"));
		}else{
			this.result=JSONObject.fromObject(successInfo("no"));
		}
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
	   Double totle=icontractReceivePaymentService.totleMoneyByContractId(contract);
	   result = JSONObject.fromObject(successInfo(totle.toString()));
		return SUCCESS;
	}
	
	public String findPayDatamoney()
	{
		int contract = 0;
		if(!BlankUtil.isBlank(contractId))
		{
			contract=Integer.parseInt(contractId);
		}
		Double money=icontractReceivePaymentService.findPayDatamoney(contract, Integer.parseInt(contractStatus));
	  result=JSONObject.fromObject(successInfo(money+""));
	  return SUCCESS;
	}
}
