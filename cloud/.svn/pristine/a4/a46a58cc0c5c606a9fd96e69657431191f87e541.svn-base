package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.base.exception.OperationException;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.CheckinquiryOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;

public interface IContractOrderService extends ICommonService<TbContractOrder>{
	
	public PageInfo<ContractOrderVo> listContractOrder(PageInfo<ContractOrderVo> pageInfo,Long userId,ContractOrderVo orderVo);
	/**
	 * 保存合同订单
	 * @param contractId 合同序号
	 * @param note 备注
	 * @param orderNo 订单编号
	 * @param creatorId 创建人ID
	 * @param status 状态
	 * @param cauditId 审核人ID
	 * @param purchaseId 采购人ID
	 * @return boolean true:成功，false：失败
	 */
	public void save(Integer contractId,String note,String orderNo,Long creatorId,Long cauditId,Long purchaseId) throws OperationException;
	
	/**
	 * 修改合同订单
	 * @param contractOrderId 合同订单ID
	 * @param note 备注
	 * @param orderNo 订单编号
	 * @param purchaseId 采购人员
	 * @return
	 */
	public void edit(Integer contractOrderId,String note,String orderNo,Long purchaseId)throws OperationException;
  //根据ID查询订单
	public ContractOrderVo queryOrderById(int id);
	
	/**
	 * 
	* 方法用途和描述: 根据销售订单id查询销售订单详情
	* @param contractOrderId
	* @author chenliang 新增日期：2013-3-11
	* @since ContractManage
	 */
	public List<ContractQuotationsVo> findOrderDetailByContractId(int contractOrderId,String suppliersId);
	/**
	 * 
	* 方法用途和描述: 根据合同id和配置单id查询全通用单元的数据
	* @author chenliang 新增日期：2013-3-13
	* @since ContractManage
	 */
	public PageInfo<CurrentUnitVo> findCurrentUnit(Integer contractId,String configModelId);
	
	/**
	 * 
	* 方法用途和描述: 根据订单详情查询对应的全通用单元
	* @param orderShipId
	* @author chenliang 新增日期：2013-3-13
	* @since ContractManage
	 */
	public List<CurrentUnitVo> findCurrentUnitDetail(String orderShipId);
	
	/**
	 * 
	* 方法用途和描述: 查询审核询价
	* @param contractOrderId
	* @author chenliang 新增日期：2013-3-15
	* @since ContractManage
	 */
	public PageInfo<CheckinquiryOrderVo> checkInquiryOrder(PageInfo<CheckinquiryOrderVo> pageInfo,Integer contractOrder);
	
	/**
	 * 
	* 方法用途和描述: 查询审核询价单详情
	* @param contractOrderId
	* @author chenliang 新增日期：2013-3-15
	* @since ContractManage
	 */
	public List<CheckinquiryOrderVo> checkInquiryOrderDetail(String contractOrderId);
	
	/**
	 * 
	* 方法用途和描述: 审核询价单
	* @param contractOrderVo
	* @author chenliang 新增日期：2013-3-15
	* @since ContractManage
	 */
	public boolean checkedInquiriy(CheckedVo checkedVo);
	/**
	 * 
	* 方法用途和描述: 售后查询所有状态为已收货和待收货的销售订单
	* @param contractOrderVo
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public PageInfo<ContractOrderVo> listContractOrderByStauts(PageInfo<ContractOrderVo> pageInfo,ContractOrderVo cOrder,String stauts);
	
	/**
	 * 
	 * 确认某订单下所有的采购单已经收货
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public boolean confirmAllReceipt(Integer contractOrderId);
	
	/**
	 * 
	 * 查询所有合同订单下的机型配置
	 * 
	 * @param contractOrderId
	 * @return
	 * @author zhangjh 新增日期：2013-3-19
	 * @since ContractManage
	 */
	public List<ContractQuotationsVo> querySaleOrder(Integer contractOrderId,Integer suppliersId);
	
	/**
	 * 
	 * 新增正式合同订单
	 * 
	 * @param contractOrderVo
	 * @param contractOrderId
	 * @param createUserId
	 * @return
	 * @author zhangjh 新增日期：2013-4-9
	 * @since ContractManage
	 */
	public boolean saveSalOrder(ContractOrderVo contractOrderVo,String contractOrderId,Long createUserId);
	
	/**
	 * 
	 * 新增临时合同订单
	 * 
	 * @param contractOrderVo
	 * @param contractOrderId
	 * @param createUserId
	 * @return
	 * @author zhangjh 新增日期：2013-4-16
	 * @since ContractManage
	 */
	public boolean saveSalTemporaryOrder(ContractOrderVo contractOrderVo,String contractOrderId,Long createUserId);
	
	/**
	 * 
	* 方法用途和描述: 生成销售订单号
	* @author chenliang 新增日期：2013-4-24
	* @since ContractManage
	 */
	public String createOrderNO(Integer contractId,Integer typeId) throws Exception;
	/**
	 * 
	* 方法用途和描述: 查询询价单详情
	* @author chenliang 新增日期：2013-4-24
	* @since ContractManage
	 */
	public List<ContractQuotationsVo> findOrderByContractId(String contractOrderId);
}
