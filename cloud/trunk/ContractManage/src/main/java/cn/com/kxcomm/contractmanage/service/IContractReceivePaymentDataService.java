package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePaymentData;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;

public interface IContractReceivePaymentDataService extends ICommonService<TbContractReceivePaymentData>{
	/**
	 * 
	 * 添加合同付款明细
	 * 
	 * @param vo
	 * @return boolean
	 * @author zhangjh 新增日期：2013-3-16
	 * @since ContractManage
	 */
	public boolean addPaymentData(ContractPaymentDataVo vo);
	
	/**
	 * 
	 * 返回合同收款的所有明细列表
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public  PageInfo<ContractPaymentDataVo> contractAllPaymentDataList(PageInfo<ContractPaymentDataVo> page,String contractId);
	
	/**
	 * 
	 * 财务确认收款
	 * 
	 * @param vo
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public boolean confirMoney(ContractPaymentDataVo vo);
}
