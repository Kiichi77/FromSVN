package cn.com.kxcomm.contractmanage.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.entity.TbProcessUser;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractOrderService;
import cn.com.kxcomm.contractmanage.service.IContractService;
import cn.com.kxcomm.contractmanage.service.IProcessUserService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.status.S_ContractOrder;
import cn.com.kxcomm.contractmanage.vo.ContractOrderRelationshipVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

/**
 * 
* 功能描述:采购订单action
* @author chenliang 新增日期：2013-3-15
* @since ContractManage
 */
public class PurchaseOrderAction extends BaseAction<TbPurchaseOrder, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseOrderAction.class);
	@Autowired(required = true)
	private IPurchaseOrderService purchaseOrderService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	@Autowired(required=true)
	private IContractOrderService contractOrderService;
	private String contractOrderId;
	@Autowired(required=true)
	private IContractService contractService;
	@Autowired(required=true)
	private IProcessUserService processUserService;
	private PurchaseOrderVo purchaseOrderVo;  //采购订单vo
	private String id;
	private String processid;
	private Long userid;  //接受任务人的ID
	
	public String getId() {
		return id;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PurchaseOrderVo getPurchaseOrderVo() {
		return purchaseOrderVo;
	}

	public void setPurchaseOrderVo(PurchaseOrderVo purchaseOrderVo) {
		this.purchaseOrderVo = purchaseOrderVo;
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public IPurchaseOrderService getPurchaseOrderService() {
		return purchaseOrderService;
	}

	public void setPurchaseOrderService(IPurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	
	public IContractOrderService getContractOrderService() {
		return contractOrderService;
	}

	public void setContractOrderService(IContractOrderService contractOrderService) {
		this.contractOrderService = contractOrderService;
	}
	
	public IContractService getContractService() {
		return contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}
	
	public IProcessUserService getProcessUserService() {
		return processUserService;
	}

	public void setProcessUserService(IProcessUserService processUserService) {
		this.processUserService = processUserService;
	}

	/**
	   * 
	  * 方法用途和描述: 分页获取所有的收货列,分页查询采购订单
	  * @return
	  * @author lujia 新增日期：2013-3-7
	  * @since ContractManage
	   */
	public String listPurchaseOrder(){
		 try {
		   PageInfo<PurchaseOrderVo> page =  new PageInfo<PurchaseOrderVo>(ServletActionContext.getRequest());
		   if(BlankUtil.isBlank(purchaseOrderVo)){
				purchaseOrderVo = new PurchaseOrderVo();
			}
		   PageInfo<PurchaseOrderVo> PurchaseOrderList=purchaseOrderService.listPurchaseOrder(page,purchaseOrderVo);
		   result = JSONObject.fromObject(PurchaseOrderList);
		} catch (Exception e) {
			LOGGER.error("listProject ERROR:",e);
		}
	    return SUCCESS;
	}
	/**
	* 
	* 方法用途和描述: 根据合同订单Id获取采购订单的所有数据
	* @return
	* @author luj 新增日期：2013-3-11
	* @since ContractManage
	*/
	public String listByContractOrderId1()
	{
		 try {
			   PageInfo<PurchaseOrderVo> page =  new PageInfo<PurchaseOrderVo>();
			   List<PurchaseOrderVo> list=purchaseOrderService.listByOrderId(Integer.parseInt(contractOrderId));
			   PageInfo<PurchaseOrderVo> pageinfo = new PageInfo<PurchaseOrderVo>();
			   pageinfo.setRows(list);
			   result = JSONObject.fromObject(pageinfo);
			} catch (Exception e) {
				LOGGER.error("listProject ERROR:",e);
			}
		    return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据合同订单Id获取采购订单的所有数据
	* @return
	* @author luj 新增日期：2013-3-11
	* @since ContractManage
	*/
	public String listByContractOrderId()
	{
		 try {
			   PageInfo<PurchaseOrderVo> page =  new PageInfo<PurchaseOrderVo>();
			   List<PurchaseOrderVo> list=purchaseOrderService.listByContractOrderId(Integer.parseInt(contractOrderId));
			   PageInfo<PurchaseOrderVo> pageinfo = new PageInfo<PurchaseOrderVo>();
			   pageinfo.setRows(list);
			   result = JSONObject.fromObject(pageinfo);
			} catch (Exception e) {
				LOGGER.error("listProject ERROR:",e);
			}
		    return SUCCESS;
	}
	/**
	   * 
	  * 方法用途和描述: 修改采购订单状态
	  * @return
	  * @author lujia 新增日期：2013-3-18
	  * @since ContractManage
	   */
	public String updateStatus(){
		try {
			HttpSession session = getHttpSession();
			String useridService = ((Long) session.getAttribute(SessionUtils.USER_ID)).toString();
			Long role_id = (Long) session.getAttribute(SessionUtils.USER_ROLE_ID);  //角色id
			TbPurchaseOrder purchaseOrder=purchaseOrderService.getByPk(Integer.parseInt(id));
			purchaseOrder.setReceivertime(new Date());
			purchaseOrder.setStatus(7);
			purchaseOrderService.merge(purchaseOrder);
			this.contractOrderId = ""+purchaseOrder.getContractOrder().getId();
			List<PurchaseOrderVo> list = purchaseOrderService.listByContractOrderId(Integer.parseInt(contractOrderId));
			boolean end = true;
			for(int i = 0; i < list.size(); i++) {
				if(BlankUtil.isBlank(list.get(i).getStatus()) || 7 != Integer.parseInt(list.get(i).getStatus())) {
					end = false;
				} 
			}
			boolean endbo = this.purchaseOrderService.checkContractOrderNum(Integer.parseInt(contractOrderId));
			if(end && !endbo) {
				TbContractOrder tbContractOrder = contractOrderService.getByPk(Integer.parseInt(contractOrderId));
				//修改销售订单状态
				tbContractOrder.setStatus(S_ContractOrder.CONTRACTORDER_ARRIVAL);
				contractOrderService.update(tbContractOrder);
				
			}
		result = JSONObject.fromObject(successInfo("ok"));	
		} catch (Exception e) {
			LOGGER.error("updateStatus ERROR:", e);
		}
		  return SUCCESS;
	}
	/**
	 * 
	* 方法用途和描述: 生成采购订单
	* @return
	* @author chenliang 新增日期：2013-3-16
	* @since ContractManage
	 */
	public String savePurchaseOrder(){
		if(!BlankUtil.isBlank(id)){
			JSONArray jsonArray = JSONArray.fromObject(id);
			PurchaseOrderVo[] volist = (PurchaseOrderVo[]) JSONArray.toArray(jsonArray,PurchaseOrderVo.class);
			HttpSession session = getHttpSession();
			Map map = new HashMap();
			String useridService = ((Long) session.getAttribute(SessionUtils.USER_ID)).toString();
			for (PurchaseOrderVo purchaseOrderVo : volist) {
				purchaseOrderVo.setCreaterId(useridService);
				map = purchaseOrderService.savePurchaseOrder(purchaseOrderVo);
			}
			result = JSONObject.fromObject(successInfo("ok"));
		}else{
			log.info("id is null.");
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改采购订单
	* @return
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public String updatePurchaseOrder(){
		if(!BlankUtil.isBlank(id)){
			JSONArray jsonArray = JSONArray.fromObject(id);
			PurchaseOrderVo[] volist = (PurchaseOrderVo[]) JSONArray.toArray(jsonArray,PurchaseOrderVo.class);
			HttpSession session = getHttpSession();
			String userid = ((Long) session.getAttribute(SessionUtils.USER_ID)).toString();
			for (PurchaseOrderVo purchaseOrderVo : volist) {
				purchaseOrderVo.setCreaterId(userid);
				purchaseOrderService.updatePurchaseOrder(purchaseOrderVo);
				result = JSONObject.fromObject(successInfo("ok"));
			}
		}else{
			log.info("id is null.");
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	

	/**
	 * 
	 * 方法用途和描述: 修改采购订单的报价单号和采购订单编号.
	 * @return
	 * @author luj 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String UpdateOrder()
	{
		try {
			JSONArray jsonArray = JSONArray.fromObject(this.id);
			String end = this.purchaseOrderService.UpdateOrder(this.purchaseOrderVo);
			this.result = JSONObject.fromObject(successInfo(end));
		} catch (Exception e) {
			LOGGER.error("UpdateOrder ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据合同订单Id和订单ID获取采购订单的所有数据
	* @return
	* @author lizl 新增日期：2013-6-21
	* @since ContractManage
	*/
	public String listByContractOrderInfos(){
		try {
		   List<PurchaseOrderVo> list = this.purchaseOrderService.listByOrderInfos(Integer.parseInt(this.contractOrderId),Integer.parseInt(id));
		   PageInfo<PurchaseOrderVo> pageinfo = new PageInfo<PurchaseOrderVo>();
		   pageinfo.setRows(list);
		   this.result = JSONObject.fromObject(pageinfo);
		} catch (Exception e) {
			LOGGER.error("listProject ERROR:",e);
		}
	    return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return purchaseOrderService;
	}

	@Override
	public TbPurchaseOrder getModel() {
		if(this.model==null){
			 this.model = new TbPurchaseOrder();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPurchaseOrder model) {
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
