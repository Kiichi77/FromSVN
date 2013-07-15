package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderRelationship;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderRelationshipVo;

public interface IPurchaseOrderRelationshipService extends ICommonService<TbPurchaseOrderRelationship>{
	public List<PurchaseOrderRelationshipVo>  queryAllConfig(String purchaseOrderId);

	/**
	 * 
	* 方法用途和描述: 根据采购订单id查询采购订单详情
	* @param purchaseOrderId
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public List<PurchaseOrderRelationshipVo> findDetailByOrderPkid(String purchaseOrderId);

	/**
	 * 
	* 方法用途和描述: 根据采购订单详情id查询采购订单详情对应的全通用单元
	* @param purchaseOrderDetailId
	* @author chenliang 新增日期：2013-3-19
	* @since ContractManage
	 */
	public List<CurrentUnitVo> findUnitDetailByOrderDetailPkid(String purchaseOrderDetailId);
}
