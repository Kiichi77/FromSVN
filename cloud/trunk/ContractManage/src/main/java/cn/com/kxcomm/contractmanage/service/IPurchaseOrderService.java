package cn.com.kxcomm.contractmanage.service;

import java.util.List;
import java.util.Map;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderVo;

/**
 * 
* 功能描述:采购订单业务接口
* @author chenliang 新增日期：2013-3-16
* @since ContractManage
 */
public interface IPurchaseOrderService extends ICommonService<TbPurchaseOrder>{
	public PageInfo<PurchaseOrderVo> listPurchaseOrder(PageInfo<PurchaseOrderVo> pageInfo,PurchaseOrderVo purchaseOrderVo);
	public List<PurchaseOrderVo> listByContractOrderId(int contractOrderId);
	public void merge(TbPurchaseOrder purchaseOrder);
	
	/**
	 * 
	* 方法用途和描述: 生成采购订单
	* @param purchaseOrderVo
	* @author chenliang 新增日期：2013-3-16
	* @since ContractManage
	 */
	public Map savePurchaseOrder(PurchaseOrderVo purchaseOrderVo);
	/**
	 * 
	* 方法用途和描述: 修改采购订单
	* @param purchaseOrderVo
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public void updatePurchaseOrder(PurchaseOrderVo purchaseOrderVo);
	
	/**
	 * 
	 * 修改订单下的采购单为已到货
	 * 
	 * @param contractOrderId
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public boolean confirmAllReceipt(Integer contractOrderId);
	
	/**
	 * 
	 * 根据销售订单判断该销售订单是否已经全部采购完成
	 * 
	 * @param contractOrderId
	 * @return
	 * @author lizl 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public boolean checkContractOrderNum(Integer contractOrderId);
	
	
	/**
	 * 
	* 方法用途和描述: 根据进程id查询采购订单
	* @param processId
	* @return
	* @author chenliang 新增日期：2013-3-27
	* @since ContractManage
	 */
	public PurchaseOrderVo findByProcessId(String processId);
	
	public List<PurchaseOrderVo> listByOrderId(int contractOrderId);
	
	public String UpdateOrder(PurchaseOrderVo jsonBeanList);
	public List<PurchaseOrderVo> listByOrderInfos(int contractOrderId,int parasid);
}
