package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.exception.OperationException;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipDataService;
import cn.com.kxcomm.contractmanage.service.IContractOrderRelationshipService;
import cn.com.kxcomm.contractmanage.service.IContractOrderService;
import cn.com.kxcomm.contractmanage.service.IQuotationsDataService;
import cn.com.kxcomm.contractmanage.service.IactivitiService;
import cn.com.kxcomm.contractmanage.service.impl.ProcessUserService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.CheckinquiryOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderRelationshipVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

/**
 * 
* 功能描述:销售订单action
* @author chenliang 新增日期：2013-3-18
* @since ContractManage
 */
public class ContractOrderAction extends BaseAction<TbContractOrder, String>{
	private static final long serialVersionUID = -5145547357997832679L;
	private static Logger LOGGER = Logger.getLogger(ContractOrderAction.class);
	@Autowired(required = true)
	private IContractOrderService contractOrderService;
	@Autowired(required = true)
	private IContractOrderRelationshipService contractOrderRelationshipService;
	@Autowired(required = true)
	private IQuotationsDataService quotationsDataService;
	@Autowired(required=true)
	private ProcessUserService processUserService;
	@Autowired(required=true)
	private IactivitiService activitiService;
	@Autowired(required=true)
	private IContractOrderRelationshipDataService contractOrderRelationshipDataService;

	private String suppliersId; //供应商id
	private ContractOrderVo contractOrderVo;
	private Integer contractId;  //合同Id
	private Integer typeId; //合同类型id
	private String note;  //备注
	private String orderNo; //订单号
	private Long creatorId; //创建者ID
	private Long cauditId;  //审核者ID
	private Long purchaseId;  //采购人员ID
	private String contractOrderId;  //销售订单id
	private TbContractOrder contractOrder;
	private String configModelId; //配置单id
	private String contractOrderShipId;// 销售订单详情id
	private String id;
	private String processname;//自定义流程名称
	private String processid;
	private CheckedVo checkedVo;  //提交审核
	
	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(String suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractOrderShipId() {
		return contractOrderShipId;
	}

	public void setContractOrderShipId(String contractOrderShipId) {
		this.contractOrderShipId = contractOrderShipId;
	}

	public String getConfigModelId() {
		return configModelId;
	}

	public void setConfigModelId(String configModelId) {
		this.configModelId = configModelId;
	}

	public Long getCauditId() {
		return cauditId;
	}

	public void setCauditId(Long cauditId) {
		this.cauditId = cauditId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public TbContractOrder getContractOrder() {
		return contractOrder;
	}

	public void setContractOrder(TbContractOrder contractOrder) {
		this.contractOrder = contractOrder;
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}


	public ContractOrderVo getContractOrderVo() {
		return contractOrderVo;
	}

	public void setContractOrderVo(ContractOrderVo contractOrderVo) {
		this.contractOrderVo = contractOrderVo;
	}

	public IContractOrderService getContractOrderService() {
		return contractOrderService;
	}

	public void setContractOrderService(IContractOrderService contractOrderService) {
		this.contractOrderService = contractOrderService;
	}

	public IContractOrderRelationshipService getContractOrderRelationshipService() {
		return contractOrderRelationshipService;
	}

	public void setContractOrderRelationshipService(IContractOrderRelationshipService contractOrderRelationshipService) {
		this.contractOrderRelationshipService = contractOrderRelationshipService;
	}

	public IQuotationsDataService getQuotationsDataService() {
		return quotationsDataService;
	}

	public void setQuotationsDataService(IQuotationsDataService quotationsDataService) {
		this.quotationsDataService = quotationsDataService;
	}
	
	/**
	 * 
	* 方法用途和描述:生成合同订单号
	* @return
	* @author chenliang 新增日期：2013-4-24
	* @since ContractManage
	 */
	public String generateSaleOrder(){
		try {
			String orderNO = contractOrderService.createOrderNO(contractId,typeId);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("orderNO", orderNO);
			map.put("msg", "ok");
			map.put("success", "true");
			result = JSONObject.fromObject(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 审核询价单
	 * @return
	 * @author chenliang 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String checkedInquiriy(){
		HttpSession session = getHttpSession();
		Long caudit = ((Long) session.getAttribute(SessionUtils.USER_ID));
		if(BlankUtil.isBlank(contractOrderVo)){
			contractOrderVo = new ContractOrderVo();
		}
		contractOrderVo.setCaudit(""+caudit);
		if(BlankUtil.isBlank(checkedVo)){
			checkedVo = new CheckedVo();
		}
		checkedVo.setUser_id(""+caudit);
		boolean flat = contractOrderService.checkedInquiriy(checkedVo);
		if(flat){
			this.result = JSONObject.fromObject(successInfo("ok"));
		}else{
			this.result = JSONObject.fromObject(successInfo("no"));
		}
		
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 查询审核询价单详情
	 * @return
	 * @author chenliang 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String checkInquiryOrderDetail(){
		List<CheckinquiryOrderVo> list = contractOrderService.checkInquiryOrderDetail(contractOrderId);
		PageInfo<CheckinquiryOrderVo> pageInfo = new PageInfo<CheckinquiryOrderVo>();
		pageInfo.setRows(list);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 查询询价单详情
	 * @return
	 * @author chenliang 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String findOrderByContractId(){
		List<ContractQuotationsVo> list = contractOrderService.findOrderByContractId(contractOrderId);
		PageInfo<ContractQuotationsVo> pageInfo = new PageInfo<ContractQuotationsVo>();
		pageInfo.setRows(list);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 查询审核询价单
	 * @return
	 * @author chenliang 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String checkInquiryOrder(){
		PageInfo<CheckinquiryOrderVo> page = new PageInfo<CheckinquiryOrderVo>(ServletActionContext.getRequest());
		Integer xixi = null;
		if(this.contractOrderId!=null && !"null".equals(this.contractOrderId))xixi=Integer.parseInt(contractOrderId);
		PageInfo<CheckinquiryOrderVo> pageInfo = contractOrderService.checkInquiryOrder(page,xixi);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据订单详情id查询全通用单元的数据
	 * @return
	 * @author chenliang 新增日期：2013-3-13
	 * @since ContractManage
	 */
	public String findCurrentUnitDetail(){
		List<CurrentUnitVo> volist = contractOrderService.findCurrentUnitDetail(contractOrderShipId);
		PageInfo<CurrentUnitVo> pageInfo = new PageInfo<CurrentUnitVo>();
		pageInfo.setRows(volist);
		this.result =  JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据合同id和机型配置单id查询全通用单元	
	 * @return
	 * @author chenliang 新增日期：2013-3-13
	 * @since ContractManage
	 */
	public String findCurrentUnit(){
		PageInfo<CurrentUnitVo> pageInfo = contractOrderService.findCurrentUnit(contractId, configModelId);
		this.result =  JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 根据销售订单id查询销售订单详情
	 * @return
	 * @author chenliang 新增日期：2013-3-11
	 * @since ContractManage
	 */
	public String findOrderDetailByContractId(){
		int pkid = 0;
		if(!BlankUtil.isBlank(contractOrderId)){
			pkid = Integer.parseInt(contractOrderId);
		}
		List<ContractQuotationsVo> volist = contractOrderService.findOrderDetailByContractId(pkid,null);
		PageInfo<ContractQuotationsVo> pageinfo = new PageInfo<ContractQuotationsVo>();
		pageinfo.setRows(volist);
		this.result = JSONObject.fromObject(pageinfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据销售订单id,供应商id，查询销售订单详情
	 * @return
	 * @author chenliang 新增日期：2013-3-11
	 * @since ContractManage
	 */
	public String findOrderDetailByContractIdAndSuppliersId(){
		int pkid = 0;
		if(!BlankUtil.isBlank(contractOrderId)){
			pkid = Integer.parseInt(contractOrderId);
		}
		List<ContractQuotationsVo> volist = contractOrderService.findOrderDetailByContractId(pkid,suppliersId);
		PageInfo<ContractQuotationsVo> pageinfo = new PageInfo<ContractQuotationsVo>();
		pageinfo.setRows(volist);
		this.result = JSONObject.fromObject(pageinfo);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 获取所有的订单
	 * 
	 * @return
	 * @author lujia 新增日期：2013-2-27
	 * @since ContractManage
	 */

	public String listContractOrder() {
		try {
			PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(
					ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractOrderVo)){
				contractOrderVo = new ContractOrderVo();
			}
			PageInfo<ContractOrderVo> contractOrderList = this.contractOrderService.listContractOrder(page,null,contractOrderVo);
			this.result = JSONObject.fromObject(contractOrderList);
		} catch (Exception e) {
			LOGGER.error("listContractOrder ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 我的销售订单
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myContractOrder() {
		try {
			 HttpSession session = getHttpSession();
			 Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));

			PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(
					ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractOrderVo)){
				contractOrderVo = new ContractOrderVo();
			}
			PageInfo<ContractOrderVo> contractOrderList = this.contractOrderService.listContractOrder(page,userId,contractOrderVo);
			this.result = JSONObject.fromObject(contractOrderList);
		} catch (Exception e) {
			LOGGER.error("listContractOrder ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 售后查询所有状态为待收货和已收货的销售订单
	 * 
	 * @return
	 * @author lujia 新增日期：2013-2-27
	 * @since ContractManage
	 */

	public String listContractOrderByStauts() {
		try {
			PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(
					ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractOrderVo)){
				contractOrderVo = new ContractOrderVo();
			}
			PageInfo<ContractOrderVo> contractOrderList = this.contractOrderService.listContractOrderByStauts(page,contractOrderVo,null);
			this.result = JSONObject.fromObject(contractOrderList);
		} catch (Exception e) {
			LOGGER.error("listContractOrder ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 询价
	 * 
	 * @return
	 * @author lujia 新增日期：2013-2-27
	 * @since ContractManage
	 */

	public String listByStauts() {
		try {
			PageInfo<ContractOrderVo> page = new PageInfo<ContractOrderVo>(
					ServletActionContext.getRequest());
			if(BlankUtil.isBlank(contractOrderVo)){
				contractOrderVo = new ContractOrderVo();
			}
			PageInfo<ContractOrderVo> contractOrderList = this.contractOrderService.listContractOrderByStauts(page,contractOrderVo,null);
			this.result = JSONObject.fromObject(contractOrderList);
		} catch (Exception e) {
			LOGGER.error("listContractOrder ERROR:", e);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述:添加合同订单
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String addContractOrder() {
		try {

			HttpSession session = getHttpSession();
			this.creatorId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			contractOrderService.save(contractId,note,orderNo,creatorId,cauditId,purchaseId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("msg","ok");
			result = JSONObject.fromObject(map);
		} catch (Exception e) {
			LOGGER.error("addContractOrder ERROR:",e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 修改项目信息
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @throws OperationException 
	 * @since ContractManage
	 */
	public String updateContractOrder() {
		if(!BlankUtil.isBlank(contractOrderId)){
			try {
				contractOrderService.edit(Integer.parseInt(contractOrderId), note, orderNo, purchaseId);
			} catch (OperationException e) {
				e.printStackTrace();
			}
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 修改销售订单状态同时修改采购订单状态
	 * @return
	 * @author lujia 新增日期：2013-2-28
	 * @throws OperationException 
	 * @since ContractManage
	 */
	public String updateOrderStauts() {
		if(!BlankUtil.isBlank(contractOrderId)){
		     contractOrderService.confirmAllReceipt(Integer.parseInt(contractOrderId));
			this.result=JSONObject.fromObject(successInfo("ok"));
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 根据ID查询某个订单信息
	 * @return
	 * @author luj 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String queryOrderById() {
		LOGGER.info("modelId:  "+this.contractOrderId);
		ContractOrderVo contractOrderVo = this.contractOrderService.queryOrderById(Integer.parseInt(contractOrderId));
		Map map = new HashMap();
		map.put("contractOrder",contractOrderVo);
		this.result = JSONObject.fromObject(map);//
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据ID查询某个订单信息
	 * @return
	 * @author chenliang 新增日期：2013-2-28
	 * @since ContractManage
	 */
	public String findById() {
		LOGGER.info("modelId:  "+this.contractOrderId);
		ContractOrderVo contractOrderVo = this.contractOrderService.queryOrderById(Integer.parseInt(contractOrderId));
		List<ContractOrderVo> list = new ArrayList<ContractOrderVo>();
		list.add(contractOrderVo);
		PageInfo<ContractOrderVo> pageInfo = new PageInfo<ContractOrderVo>();
		pageInfo.setRows(list);
		this.result = JSONObject.fromObject(pageInfo);//
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 生成并保存销售订单
	 * @return
	 * @author lizl 新增日期：2013-3-07
	 * @since ContractManage
	 */
	public String saveSalOrder() {
		LOGGER.info("modelId:  "+this.contractOrderId);
		LOGGER.info("contractOrderVo:  "+this.contractOrderVo.toString());
		String end = "ok";
		HttpSession session = getHttpSession();
		/**
		 *  启动一个采购询价流程1
		 */
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
		if(!BlankUtil.isBlank(this.contractOrderVo)) {
			boolean isSucess = contractOrderService.saveSalOrder(contractOrderVo, contractOrderId, useridsession);
			if(!isSucess){
				end = "no";
			}
		}
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 保存临时合同的销售订单
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	public String saveSalTemporaryOrder() {
		LOGGER.info("modelId:  "+this.contractOrderId);
		LOGGER.info("contractOrderVo:  "+this.contractOrderVo.toString());
		String end = "ok";
		HttpSession session = getHttpSession();
		/**
		 *  启动一个采购询价流程1
		 */
		Long useridsession =  (Long) session.getAttribute(SessionUtils.USER_ID); //用户id
		if(!BlankUtil.isBlank(this.contractOrderVo)) {
			boolean isSucess = contractOrderService.saveSalTemporaryOrder(contractOrderVo, contractOrderId, useridsession);
			if(!isSucess){
				end = "no";
			}
		}
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		return SUCCESS;
	}


	/**
	 * 
	 * 方法用途和描述: 更新询价单的价格和供应商
	 * @return
	 * @author luj 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public String updateRelationship()
	{
		try {
			JSONArray jsonArray = JSONArray.fromObject(this.id);
			List<ContractOrderRelationshipVo> jsonBeanList = JSONArray.toList(jsonArray,ContractOrderRelationshipVo.class);
			String end = this.contractOrderRelationshipService.UpdateShip(jsonBeanList);
			this.result = JSONObject.fromObject(successInfo(end));
		} catch (Exception e) {
			LOGGER.error("updateRelationship ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 询价单提交审核
	* @return
	* @author chenliang 新增日期：2013-5-6
	* @since ContractManage
	 */
	public String submitState(){
		try {
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			String end = this.contractOrderRelationshipService.submitState(checkedVo);
			this.result = JSONObject.fromObject(successInfo(end));
		} catch (Exception e) {
			LOGGER.error("updateRelationship ERROR:", e);
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return contractOrderService;
	}

	@Override
	public TbContractOrder getModel() {
		if (this.model == null) {
			this.model = new TbContractOrder();
		}
		return model;
	}

	@Override
	public void setModel(TbContractOrder model) {
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
	
	public String querySaleOrder(){
		System.out.println("id："+id+"    "+"suppliersId :"+suppliersId);
		if(this.id==null || "".equals(this.id)|| "null".equals(this.id)|| this.suppliersId==null || "".equals(this.suppliersId)){
			return SUCCESS;
		}
		try {
			Integer kid = Integer.parseInt(this.id);
			Integer ksuppliersId = Integer.parseInt(this.suppliersId);
			PageInfo<ContractQuotationsVo> page = new PageInfo<ContractQuotationsVo>(ServletActionContext.getRequest());
			List<ContractQuotationsVo> list =(List<ContractQuotationsVo>) this.contractOrderService.querySaleOrder(kid,  ksuppliersId);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
	
	public String queryContractOrderRelationshipData(){
		System.out.println("ID : "+this.id);
		if(this.id==null || "".equals(this.id)){
			return SUCCESS;
		}
		try {
			Integer kid = Integer.parseInt(this.id);
			PageInfo<CurrentUnitVo> page = new PageInfo<CurrentUnitVo>(ServletActionContext.getRequest());
			List<CurrentUnitVo> list =(List<CurrentUnitVo>) this.contractOrderRelationshipDataService.queryContractOrderRelationshipData(kid);
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
}
