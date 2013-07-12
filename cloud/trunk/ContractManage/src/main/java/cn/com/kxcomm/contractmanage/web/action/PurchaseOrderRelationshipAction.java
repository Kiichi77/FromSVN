package cn.com.kxcomm.contractmanage.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderRelationship;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderRelationshipService;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderRelationshipVo;

/**
 * 
* 功能描述:采购订单详情
* @author chenliang 新增日期：2013-3-18
* @since ContractManage
 */
public class PurchaseOrderRelationshipAction extends BaseAction<TbPurchaseOrderRelationship, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseOrderRelationshipAction.class);
	@Autowired(required = true)
	private IPurchaseOrderRelationshipService purchaseOrderRelationshipService;
	private String purchaseOrderId;   //采购订单Id
	private String purchaseOrderDetailId;//采购订单详情id

	public String getPurchaseOrderDetailId() {
		return purchaseOrderDetailId;
	}
	public void setPurchaseOrderDetailId(String purchaseOrderDetailId) {
		this.purchaseOrderDetailId = purchaseOrderDetailId;
	}
	public IPurchaseOrderRelationshipService getPurchaseOrderRelationshipService() {
		return purchaseOrderRelationshipService;
	}
	public void setPurchaseOrderRelationshipService(
			IPurchaseOrderRelationshipService purchaseOrderRelationshipService) {
		this.purchaseOrderRelationshipService = purchaseOrderRelationshipService;
	}
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据采购订单id查询销售订单详情
	* @return
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public String findOrderDetailByPurchaseId(){
		List<PurchaseOrderRelationshipVo> list = purchaseOrderRelationshipService.queryAllConfig(purchaseOrderId);
		PageInfo<PurchaseOrderRelationshipVo> pageinfo = new PageInfo<PurchaseOrderRelationshipVo>();
		pageinfo.setRows(list);
		this.result = JSONObject.fromObject(pageinfo);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据采购订单id查询采购订单详情
	* @return
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public String findDetailbyOrderPkid(){
		List<PurchaseOrderRelationshipVo> volist = purchaseOrderRelationshipService.findDetailByOrderPkid(purchaseOrderId);
		PageInfo<PurchaseOrderRelationshipVo> pageInfo = new PageInfo<PurchaseOrderRelationshipVo>();
		pageInfo.setRows(volist);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	/**
	 *
	* 方法用途和描述: 根据采购订单详情id查询采购订单详情关联的全通用单元数据
	* @return
	* @author chenliang 新增日期：2013-3-19
	* @since ContractManage
	 */
	public String findUnitDetailByOrderDetailPkid(){
		List<CurrentUnitVo> volist = purchaseOrderRelationshipService.findUnitDetailByOrderDetailPkid(purchaseOrderDetailId);
		PageInfo<CurrentUnitVo> pageInfo = new PageInfo<CurrentUnitVo>();
		pageInfo.setRows(volist);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	
	@Override
	public ICommonService getCommonService() {
		return purchaseOrderRelationshipService;
	}
	@Override
	public TbPurchaseOrderRelationship getModel() {
		if (this.model == null) {
			this.model = new TbPurchaseOrderRelationship();
		}
		return model;
	}
	@Override
	public void setModel(TbPurchaseOrderRelationship model) {
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
