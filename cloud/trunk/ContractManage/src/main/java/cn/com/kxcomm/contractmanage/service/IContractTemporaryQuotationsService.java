package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.contractmanage.entity.TbContractTemporary;
import cn.com.kxcomm.contractmanage.entity.TbContractTemporaryQuotations;

public interface IContractTemporaryQuotationsService extends ICommonService<TbContractTemporaryQuotations>{

	/**
	 * 
	 * 根据报价表ID，获取临时合同
	 * 
	 * @param quotationId
	 * @return
	 * @author zhangjh 新增日期：2013-4-19
	 * @since ContractManage
	 */
	public Integer getContractTemporaryByQuotationId(Integer quotationId);
}
