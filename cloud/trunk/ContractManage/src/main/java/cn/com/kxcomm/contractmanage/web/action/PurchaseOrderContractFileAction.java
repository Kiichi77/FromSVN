package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbOverPayAccount;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IOverPayAccountService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class PurchaseOrderContractFileAction extends BaseAction<TbPurchaseOrderContractFile, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseOrderContractFileAction.class);
	@Autowired(required=true)
	private IPurchaseOrderContractFileService purchaseOrderContractFileService;
	@Autowired(required=true)
	private IOverPayAccountService overPayAccountService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	private PurchaseOrderContractFileVo purchaseOrderContractFileVo;

	private TbPurchaseOrderContractFile purchaseOrderContractFile;

	private String purchaseCompanyId;  //采购公司

	private String purchaseContractId;  //采购合同文件Id

	private String checkNo;  //支票号
	private String paymentTime; //付款时间
	private File purchaseContract;//上传文件
	private String purchaseContractFileName; //文件名称   根据上传文件（purchaseContract）后面加上FileName命名
    private String processId;
    private String userId;
   
    private String contratFileId;
    
    
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

	public String getContratFileId() {
		return this.contratFileId;
	}

	public void setContratFileId(String contratFileId) {
		this.contratFileId = contratFileId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IOverPayAccountService getOverPayAccountService() {
		return overPayAccountService;
	}

	public void setOverPayAccountService(
			IOverPayAccountService overPayAccountService) {
		this.overPayAccountService = overPayAccountService;
	}

	public String getPurchaseCompanyId() {
		return purchaseCompanyId;
	}

	public void setPurchaseCompanyId(String purchaseCompanyId) {
		this.purchaseCompanyId = purchaseCompanyId;
	}

	public String getPurchaseContractId() {
		return purchaseContractId;
	}

	public void setPurchaseContractId(String purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}

	public String getPurchaseContractFileName() {
		return purchaseContractFileName;
	}

	public void setPurchaseContractFileName(String purchaseContractFileName) {
		this.purchaseContractFileName = purchaseContractFileName;
	}

	private Parameters para = Parameters.getInstance();


	public TbPurchaseOrderContractFile getPurchaseOrderContractFile() {
		return purchaseOrderContractFile;
	}

	public void setPurchaseOrderContractFile(
			TbPurchaseOrderContractFile purchaseOrderContractFile) {
		this.purchaseOrderContractFile = purchaseOrderContractFile;
	}

	public File getPurchaseContract() {
		return purchaseContract;
	}

	public void setPurchaseContract(File purchaseContract) {
		this.purchaseContract = purchaseContract;
	}

	public PurchaseOrderContractFileVo getPurchaseOrderContractFileVo() {
		return purchaseOrderContractFileVo;
	}

	public void setPurchaseOrderContractFileVo(
			PurchaseOrderContractFileVo purchaseOrderContractFileVo) {
		this.purchaseOrderContractFileVo = purchaseOrderContractFileVo;
	}

	public IPurchaseOrderContractFileService getPurchaseOrderContractFileService() {
		return purchaseOrderContractFileService;
	}

	public void setPurchaseOrderContractFileService(
			IPurchaseOrderContractFileService purchaseOrderContractFileService) {
		this.purchaseOrderContractFileService = purchaseOrderContractFileService;
	}

	/**
	 * 
	 * 方法用途和描述: 分页查询采购合同文件
	 * @return
	 * @author lujia 新增日期：2013-3-12
	 * @since ContractManage
	 */
	public String queryAll(){
		PageInfo<PurchaseOrderContractFileVo> pageInfo = new PageInfo<PurchaseOrderContractFileVo>(ServletActionContext.getRequest());
		if(BlankUtil.isBlank(purchaseOrderContractFileVo)){
			purchaseOrderContractFileVo = new PurchaseOrderContractFileVo();
		}
		LOGGER.info("purchaseOrderContractFileVo:"+purchaseOrderContractFileVo);
		PageInfo<PurchaseOrderContractFileVo> pagelist = purchaseOrderContractFileService.listPurchaseContractFile(pageInfo,this.contratFileId);
		this.result = JSONObject.fromObject(pagelist);
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 保存采购合同文件，上传文件
	 * @return
	 * @author lujia 新增日期：2013-3-12
	 * @since ContractManage
	 */
	public String savePurchaseContract()
	{
		Long userIdsession = null;
		try {
			if(null!=purchaseContract){
				//上传文件
//				String bakPath = para.purchaseWordPath; //文件上传到服务器的地址
				HttpServletRequest req = this.getHttpRequest();
				String bakPath = req.getSession().getServletContext().getRealPath("download/purchaseOrderFile/")+File.separator;
				System.out.println("bakPath:"+bakPath);
				LOGGER.info("bakPath:"+bakPath);
				String FileName = System.currentTimeMillis()+purchaseContractFileName.substring(purchaseContractFileName.lastIndexOf("."), purchaseContractFileName.length());//上传到服务器的文件名
				FileUtil.bakTemplatesDoc(purchaseContract, bakPath, FileName, false,0);
				purchaseOrderContractFileService.ftpUploadpPurchaseOrderFileSync(bakPath,FileName);
				//保存文件
				HttpSession session = getHttpSession();
				userIdsession = ((Long) session.getAttribute(SessionUtils.USER_ID));
				TbUser user=new TbUser();
				user.setId(userIdsession);
				purchaseOrderContractFile.setCreate(user);    //创建者
				purchaseOrderContractFile.setIsPay(0);     //待付款
				purchaseOrderContractFile.setCreatetime(new Date());
				purchaseOrderContractFile.setPathName(FileName);    //文件地址
				purchaseOrderContractFile.setPayment(0.0);
				purchaseOrderContractFile.setProcessId(this.processId);		//任务ID
				purchaseOrderContractFileService.save(purchaseOrderContractFile);
				//保存TbOverPayAccount
				TbOverPayAccount overPayAccount=new TbOverPayAccount();
				TbPurchaseCompany purchaseCompany=new TbPurchaseCompany();
				purchaseCompany.setId(Integer.parseInt(purchaseCompanyId));
				overPayAccount.setPurchaseCompany(purchaseCompany);
				TbSuppliers suppliers=new TbSuppliers();
				suppliers.setId(purchaseOrderContractFile.getSuppliers().getId());
				overPayAccount.setSuppliers(suppliers);
				overPayAccount.setTotalOverPayMoney(0.0);
				this.overPayAccountService.save(overPayAccount);
				//先完成本次任务
			//	activitiService.finishTheTask(this.processId, ""+userIdsession, null);
				//分配下一个任务
		//		activitiService.taskClaim(this.processId, userId, null);
				result = JSONObject.fromObject(successInfo("ok"));
			}else{
				result = JSONObject.fromObject(successInfo("nook"));
			}
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("savePurchaseContract error.",e);
		}finally{

		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据采购合同文件Id查询采购付款明细
	 * @return
	 * @author luj 新增日期：2013-3-13
	 * @since ContractManage
	 */
	public String queryByPurchaseContractId(){
		
		LOGGER.info("purchaseContractId:  "+this.purchaseContractId);
		purchaseOrderContractFileVo=this.purchaseOrderContractFileService.queryByPurchaseContractId(Integer.parseInt(purchaseContractId));
		Map map = new HashMap();
		map.put("purchaseOrderContractFile",purchaseOrderContractFileVo);
		this.result = JSONObject.fromObject(map);
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 根据采购合同文件Id查询
	 * @return
	 * @author luj 新增日期：2013-4-26
	 * @since ContractManage
	 */
	public String findById()
	{
		LOGGER.info("purchaseContractId:  "+this.purchaseContractId);
		purchaseOrderContractFileVo=this.purchaseOrderContractFileService.findByContractId(purchaseContractId);
		Map map = new HashMap();
		map.put("purchaseOrderContractFile",purchaseOrderContractFileVo);
		this.result = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
    /**
	    * 
	   * 方法用途和描述: 更新付款信息
	   * @return
	   * @author lujia 新增日期：2013-4-26
	   * @since ContractManage
	    */
	public String updatePurchaseContract()
	{
//		if(!BlankUtil.isBlank(purchaseContractId)){
//			purchaseOrderContractFile=purchaseOrderContractFileService.getByPk(Integer.parseInt(purchaseContractId));
//			purchaseOrderContractFile.setCheckNo(checkNo);
//			purchaseOrderContractFile.setPaymentTime(paymentTime);
//			this.purchaseOrderContractFileService.update(purchaseOrderContractFile);
//		result = JSONObject.fromObject(successInfo("ok"));
//		}else {
//			result = JSONObject.fromObject(successInfo("no"));
//		}
		return SUCCESS;
	}
	/**
	 * 
	* 方法用途和描述: 下拉框查询所有采购合同
	* @return
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public String findAll(){
		List<TbPurchaseOrderContractFile> contractFile = purchaseOrderContractFileService.findAll();
		List<PurchaseOrderContractFileVo> fileList = new ArrayList<PurchaseOrderContractFileVo>();
		for (TbPurchaseOrderContractFile tbPurchaseOrderContractFile : contractFile) {
			PurchaseOrderContractFileVo fileVo = new PurchaseOrderContractFileVo();
			EntityToVoUtil.copyObjValue(tbPurchaseOrderContractFile, fileVo);
			fileList.add(fileVo);
		}
		PageInfo<PurchaseOrderContractFileVo> pageInfo = new PageInfo<PurchaseOrderContractFileVo>();
		pageInfo.setRows(fileList);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return purchaseOrderContractFileService;
	}

	@Override
	public TbPurchaseOrderContractFile getModel() {
		if (this.model == null) {
			this.model = new TbPurchaseOrderContractFile();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPurchaseOrderContractFile model) {
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
