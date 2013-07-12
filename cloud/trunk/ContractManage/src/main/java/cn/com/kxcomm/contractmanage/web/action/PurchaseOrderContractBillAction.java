package cn.com.kxcomm.contractmanage.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbOverPayAccount;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractBill;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IOverPayAccountService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractBillService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ActivitiService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractBillVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class PurchaseOrderContractBillAction extends BaseAction<TbPurchaseOrderContractBill, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseOrderContractBillAction.class);
	@Autowired(required = true)
	private IPurchaseOrderContractBillService purchaseOrderContractBillService;
	@Autowired(required = true)
	private IPurchaseOrderContractFileService purchaseOrderContractFileService;
	@Autowired(required = true)
	private IOverPayAccountService overPayAccountService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	@Autowired(required=true)
	private IProcessUserService processUserService;
    
	private String purchaseContractId;   //采购合同文件Id
	
	private TbPurchaseOrderContractBill purchaseOrderContractBill;
	
	private String contractMoney;  //合同金额
	
	private String paymentDouble;  //实际付款
	
	private String totalOverPayMoney;  //可对冲金额
	
	private String overPayId; //可对冲金额ID
	
	private String processid;  //任务id
  
	private String checkNo;   //支票号
	
	private String paymentTime;// 付款时间
	
	
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public String getOverPayId() {
		return overPayId;
	}

	public void setOverPayId(String overPayId) {
		this.overPayId = overPayId;
	}

	public String getTotalOverPayMoney() {
		return totalOverPayMoney;
	}

	public void setTotalOverPayMoney(String totalOverPayMoney) {
		this.totalOverPayMoney = totalOverPayMoney;
	}

	public IPurchaseOrderContractFileService getPurchaseOrderContractFileService() {
		return purchaseOrderContractFileService;
	}

	public void setPurchaseOrderContractFileService(
			IPurchaseOrderContractFileService purchaseOrderContractFileService) {
		this.purchaseOrderContractFileService = purchaseOrderContractFileService;
	}

	public IProcessUserService getProcessUserService() {
		return processUserService;
	}

	public void setProcessUserService(IProcessUserService processUserService) {
		this.processUserService = processUserService;
	}
	
	public IOverPayAccountService getOverPayAccountService() {
		return overPayAccountService;
	}

	public void setOverPayAccountService(
			IOverPayAccountService overPayAccountService) {
		this.overPayAccountService = overPayAccountService;
	}

	public String getPaymentDouble() {
		return paymentDouble;
	}

	public void setPaymentDouble(String paymentDouble) {
		this.paymentDouble = paymentDouble;
	}

	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	public TbPurchaseOrderContractBill getPurchaseOrderContractBill() {
		return purchaseOrderContractBill;
	}

	public void setPurchaseOrderContractBill(
			TbPurchaseOrderContractBill purchaseOrderContractBill) {
		this.purchaseOrderContractBill = purchaseOrderContractBill;
	}

	public IPurchaseOrderContractBillService getPurchaseOrderContractBillService() {
		return purchaseOrderContractBillService;
	}

	public void setPurchaseOrderContractBillService(
			IPurchaseOrderContractBillService purchaseOrderContractBillService) {
		this.purchaseOrderContractBillService = purchaseOrderContractBillService;
	}

	public String getPurchaseContractId() {
		return purchaseContractId;
	}

	public void setPurchaseContractId(String purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}

	/**
	* 
	* 方法用途和描述: 保存采购付款明细
	* @return
	* @author luj 新增日期：2013-3-13
	* @since ContractManage
	*/ 
	public String saveContractBill() {
		 try {
		//采购合同文件ID
       TbPurchaseOrderContractFile purchaseOrderContractFile=new TbPurchaseOrderContractFile();
       purchaseOrderContractFile.setId(Integer.parseInt(purchaseContractId));
       purchaseOrderContractBill.setPurchaseOrderContractFile(purchaseOrderContractFile);
       //添加支票号和付款时间
   //    TbPurchaseOrderContractFile purchaseOrderContractFile1=new TbPurchaseOrderContractFile();
    //   purchaseOrderContractFile1=purchaseOrderContractFileService.getByPk(Integer.parseInt(purchaseContractId));
    //   purchaseOrderContractFile1.setCheckNo(checkNo);
	//	purchaseOrderContractFile1.setPaymentTime(paymentTime);
	//	this.purchaseOrderContractFileService.update(purchaseOrderContractFile1);
       //付款人
       HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		TbUser user=new TbUser();
		user.setId(userId);
		purchaseOrderContractBill.setPayer(user);
		//时间
		purchaseOrderContractBill.setCreatetime(new Date());
		//采购公司
		TbPurchaseCompany purchaseCompany=new TbPurchaseCompany();
		purchaseCompany.setId(purchaseOrderContractBill.getPurchaseCompany().getId());
		purchaseOrderContractBill.setPurchaseCompany(purchaseCompany);
		
		//支票号
		purchaseOrderContractBill.setCheckNo(checkNo);
		//付款时间
		purchaseOrderContractBill.setPaymentTime(paymentTime);
		Double paymentDouble1 = Double.parseDouble(paymentDouble);  //实际付款
		Double contractMoney1 = Double.parseDouble(contractMoney);  //合同金额
		Double overPayMoney = paymentDouble1-contractMoney1;
		if(overPayMoney>0){
			purchaseOrderContractBill.setOverPayMoney(overPayMoney);
			purchaseOrderContractBill.setIsOverpay(1);
		}else {
			purchaseOrderContractBill.setOverPayMoney(0.0);
			purchaseOrderContractBill.setIsOverpay(0);
		}
		purchaseOrderContractBillService.save(purchaseOrderContractBill);
		//更新实际付款的金额
		  Double payment=purchaseOrderContractBill.getPayment();  //当前付款
		  Double paymentDouble2=payment+paymentDouble1;      //累加实际付款金额
	      TbPurchaseOrderContractFile purchaseOrder=this.purchaseOrderContractFileService.getByPk(Integer.parseInt(purchaseContractId));
		  purchaseOrder.setPayment(paymentDouble2);
		  purchaseOrderContractFileService.merge(purchaseOrder);
		//更新可对冲金额
		  if(purchaseOrderContractBill.getUseOverPay()==1){
		  Double totalOverPayMoney1=Double.parseDouble(totalOverPayMoney); //可对冲金额
		  Double userOverPayMoney=purchaseOrderContractBill.getUserOverPayMoney(); //对冲金额
		  Double totalOverPayMoney2=totalOverPayMoney1-userOverPayMoney;
			  TbOverPayAccount overPayAccount=this.overPayAccountService.getByPk(Integer.parseInt(overPayId));
		      overPayAccount.setTotalOverPayMoney(totalOverPayMoney2);
		      overPayAccountService.merge(overPayAccount);
		  }
		  int b=paymentDouble2.compareTo(contractMoney1);
		  //更新状态
		  if(b==0) {
			  TbPurchaseOrderContractFile purchaseOrder1=this.purchaseOrderContractFileService.getByPk(Integer.parseInt(purchaseContractId));
			  purchaseOrder1.setIsPay(1);
			  purchaseOrderContractFileService.merge(purchaseOrder1);
			  activitiService.finishTheTask(this.processid, userId.toString(), null);
		  }
		  //询价流程到财务付款后自动发起订单流程
		
		  if(!BlankUtil.isBlank(processid) && !"null".equals(processid))
		  {
			 
			  TbProcessUser  tbProcessUser = processUserService.findByField("processId", processid);
			  tbProcessUser.setState("已完成");
			  processUserService.update(tbProcessUser);
		  }
		
		result = JSONObject.fromObject(successInfo("ok"));
		  }catch(Exception e){
			  LOGGER.error("saveContractBill ERROR:", e);
		  }
		  return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询采购付款明细
	* @return
	* @author lujia 新增日期：2013-3-14
	* @since ContractManage
	 */
	public String queryAll(){
		try {
		PageInfo<PurchaseOrderContractBillVo> pageInfo = new PageInfo<PurchaseOrderContractBillVo>(ServletActionContext.getRequest());
		PageInfo<PurchaseOrderContractBillVo> pagelist = purchaseOrderContractBillService.listBill(pageInfo, Integer.parseInt(purchaseContractId));
		this.result = JSONObject.fromObject(pagelist);
		} catch (Exception e) {
			LOGGER.error("queryAll ERROR:",e);
		}
	    return SUCCESS;
	}
	@Override
	public ICommonService getCommonService() {
		return purchaseOrderContractBillService;
	}

	@Override
	public TbPurchaseOrderContractBill getModel() {
		if(this.model==null){
			 this.model = new TbPurchaseOrderContractBill();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPurchaseOrderContractBill model) {
		this.model=model;
		
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
