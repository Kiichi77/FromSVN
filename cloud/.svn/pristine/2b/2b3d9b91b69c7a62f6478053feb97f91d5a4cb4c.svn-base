package cn.com.kxcomm.contractmanage.web.action;

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
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.entity.TbInvoice;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IInvoiceService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.status.S_Invoice;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.InvoiceVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class InvoiceAction extends BaseAction<TbInvoice, String>{
	private static Logger LOGGER = Logger.getLogger(InvoiceAction.class);
     
	@Autowired(required = true)
	private IInvoiceService invoiceService;
	
	private String id;
	
	private TbInvoice invoice;
	
	private InvoiceVo invoiceVo;
	
	private CheckedVo checkedVo; //审核vo
	
	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public InvoiceVo getInvoiceVo() {
		return invoiceVo;
	}

	public void setInvoiceVo(InvoiceVo invoiceVo) {
		this.invoiceVo = invoiceVo;
	}

	public TbInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(TbInvoice invoice) {
		this.invoice = invoice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IInvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(IInvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	/**
	   * 
	  * 方法用途和描述: 分页获取所有的发票
	  * @return
	  * @author lujia 新增日期：2013-3-6
	  * @since ContractManage
	   */
	public String listInvoice(){
		 try {				
		   String processId = "";
		   if(!BlankUtil.isBlank(checkedVo)){
			   processId = checkedVo.getProcess_id();
		   }
		   if(BlankUtil.isBlank(this.invoiceVo)){
			   this.invoiceVo = new InvoiceVo();
		   }
		   PageInfo<InvoiceVo> page =  new PageInfo<InvoiceVo>(ServletActionContext.getRequest());
		   PageInfo<InvoiceVo> invoiceList=invoiceService.listInvoice(page,processId,null,invoiceVo);
		   result = JSONObject.fromObject(invoiceList);
		} catch (Exception e) {
			LOGGER.error("listInvoice ERROR:",e);
		}
	    return SUCCESS;
	}
	
	/**
	 * 
	 * 我的发票申请(销售人员)
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myInvoice(){
		 try {
			 HttpSession session = getHttpSession();
			 Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
				
		   String processId = "";
		   if(!BlankUtil.isBlank(checkedVo)){
			   processId = checkedVo.getProcess_id();
		   }
		   if(BlankUtil.isBlank(this.invoiceVo)){
			   this.invoiceVo = new InvoiceVo();
		   }
		   PageInfo<InvoiceVo> page =  new PageInfo<InvoiceVo>(ServletActionContext.getRequest());
		   PageInfo<InvoiceVo> invoiceList=invoiceService.listInvoice(page,processId,userId,invoiceVo);
		   result = JSONObject.fromObject(invoiceList);
		} catch (Exception e) {
			LOGGER.error("listInvoice ERROR:",e);
		}
	    return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:删除发票
	* @return
	* @author lujia 新增日期：2013-3-6
	* @since ContractManage
	 */
	public String deleteInvoice()
	{
		 try {
				LOGGER.debug("id:"+id);
				String[] ids = id.split(",");
				for (String strId : ids) {
					invoiceService.deleteByPk(Integer.parseInt(strId));
				}
				result = JSONObject.fromObject(successInfo("ok"));
			} catch (Exception e) {
				LOGGER.error("deleteInvoice ERROR:", e);
			}
			return SUCCESS;
	}
	
	  /**
	    * 
	   * 方法用途和描述: 申请发票
	   * @return
	   * @author lujia 新增日期：2013-3-7
	   * @since ContractManage
	    */
	public String addInvoice()
	{
	 try {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
	
       	double amountdd = 0;
       	if(!BlankUtil.isBlank(invoiceVo)){
       		String amount = invoiceVo.getAmount();
       		amount = amount.replace("￥", "");
       		amount = amount.replace(",", "");
       		amountdd = Double.parseDouble(amount);
       	}
		invoiceService.addInvoice(invoice, userId, amountdd);
		
		result = JSONObject.fromObject(successInfo("ok"));
		  }catch(Exception e)
		  {
			  LOGGER.error("addProject ERROR:", e);
		  }
		  return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个发票信息
	* @return
	* @author luj 新增日期：2013-3-7
	* @since ContractManage
	*/
    public String queryInvoiceById() {
    	LOGGER.info("modelId:  "+this.id);
    	 invoiceVo = this.invoiceService.queryInvoiceById(id);
    	Map map = new HashMap();
		map.put("invoice",invoiceVo);
		this.result = JSONObject.fromObject(map);//
    	return SUCCESS;
    }
    /**
	* 
	* 方法用途和描述: 修改发票状态
	* @return
	* @author luj 新增日期：2013-3-7
	* @since ContractManage
	*/
    public String updateStauts()
    {
    	int invoiceId=0;
    	if(!BlankUtil.isBlank(id))
    	{
    		invoiceId=Integer.parseInt(id);
    	}
    	TbInvoice invoice=invoiceService.getByPk(invoiceId);
    	invoice.setStatus(S_Invoice.INVOICE_RECEIVE);
    	invoiceService.merge(invoice);
    	result = JSONObject.fromObject(successInfo("ok"));
    	return SUCCESS;
    }
    /**
	    * 
	   * 方法用途和描述: 修改发票信息
	   * @return
	   * @author lujia 新增日期：2013-3-7
	   * @since ContractManage
	    */
	public String updateInvoice() 
	{
		if(!BlankUtil.isBlank(invoice)){
			Integer id = invoice.getId();
			TbInvoice tbInvoice = this.invoiceService.getByPk(id);
		     tbInvoice.setAmount(invoice.getAmount());
		     TbPurchaseCompany purchaseCompany=new TbPurchaseCompany();
		     purchaseCompany.setCompanyName(invoice.getPurchaseCompany().getCompanyName());
		    tbInvoice.setPurchaseCompany(purchaseCompany);
		     TbContract contract=new TbContract();
		     contract.setId(invoice.getContract().getId());
		     tbInvoice.setContract(contract);
			TbCustomer customer = new TbCustomer();
			customer.setCustomerId(invoice.getCustomer().getCustomerId());
			tbInvoice.setCustomer(customer);
			tbInvoice.setTaxType(invoice.getTaxType());
			invoiceService.merge(tbInvoice);
			result = JSONObject.fromObject(successInfo("ok"));
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	  /**
	    * 
	   * 方法用途和描述: 修改发票状态
	   * @return
	   * @author lujia 新增日期：2013-3-7
	   * @since ContractManage
	    */
	public String updateByStauts() 
	{
		if(!BlankUtil.isBlank(id)){
			TbInvoice tbInvoice = this.invoiceService.getByPk(Integer.parseInt(id));
		    tbInvoice.setStatus(1);
			invoiceService.merge(tbInvoice);
			result = JSONObject.fromObject(successInfo("ok"));
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	
	/**
	 * 
	* 方法用途和描述: 审核发票
	* @return
	* @author chenliang 新增日期：2013-3-21
	* @since ContractManage
	 */
	public String checkedAuditInvoice(){
		if(!BlankUtil.isBlank(checkedVo)){
			HttpSession session = getHttpSession();
			Long user_id = (Long) session.getAttribute(SessionUtils.USER_ID);
			LOGGER.info("user_id"+checkedVo.getUser_id()+",process_id:"+checkedVo.getProcess_id()+",reject_process_id:"+checkedVo.getReject_process_id());
			//更新数据库
			invoiceService.checked(checkedVo);
		}else{
			LOGGER.info("checkedvo is null.");
		}
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	
	@Override
	public ICommonService getCommonService() {
		return invoiceService;
	}

	@Override
	public TbInvoice getModel() {
		if(this.model==null){
			 this.model = new TbInvoice();
		}
		return this.model;
	}

	@Override
	public void setModel(TbInvoice model) {
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
