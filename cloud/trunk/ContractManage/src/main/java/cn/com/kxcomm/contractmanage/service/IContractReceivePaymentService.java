package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentVo;
import cn.com.kxcomm.contractmanage.vo.ContractReceivePaymentVo;

public interface IContractReceivePaymentService extends ICommonService<TbContractReceivePayment>{
	/**
	 * 
	 * 返回合同收款列表
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public List<ContractPaymentVo> contractPaymentList(String contractId,Long userId); 
	
	/**
	 * 
	 * 返回合同收款下的明细列表
	 * @param contractReceivePaymentId 当为空：则查询全部
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public  PageInfo<ContractPaymentDataVo> contractPaymentDataList(PageInfo<ContractPaymentDataVo> page,Integer contractReceivePaymentId,Integer contractId);
	
	/**
	 * 
	 * 返回合同的收款项
	 * 
	 * @param contractId
	 * @return ContractReceivePaymentVo.id=0,为新增，=1为更新
	 * @author zhangjh 新增日期：2013-3-16
	 * @since ContractManage
	 */
	public ContractReceivePaymentVo getcontractReceivePayment(Integer contractId);
	
	/**
	 * 
	 * 保存或更新合同收款信息
	 * 
	 * @param entity
	 * @author zhangjh 新增日期：2013-3-16
	 * @since ContractManage
	 */
	public void saveOrUpdate(TbContractReceivePayment entity);
	/**
	 * 根据合同ID查询合同金额
	 * @param contractDocId 合同选中文件id
	 */
	public Double totleMoneyByContractId(Integer contractId);
	/**
	 * 查询付款金额
	 * @param contractStatus 合同状态
	 */
	public Double findPayDatamoney(int contractId,int contractStatus);
	
}
