package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbInvoice;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.InvoiceVo;

public interface IInvoiceService extends ICommonService<TbInvoice>{

	public PageInfo<InvoiceVo> listInvoice(PageInfo<InvoiceVo> pageInfo,String processId,Long userId,InvoiceVo invoiceVo);
	public InvoiceVo queryInvoiceById(String id);
	public void merge(TbInvoice invoice);
	/**
	 * 
	* 方法用途和描述: 审核发票
	* @param checkedVo
	* @author chenliang 新增日期：2013-3-21
	* @since ContractManage
	 */
	public void checked(CheckedVo checkedVo);
	
	/**
	 * 
	 * 新建发票申请
	 * 
	 * @param invoice
	 * @param createUserId
	 * @param amountdd
	 * @author zhangjh 新增日期：2013-4-10
	 * @since ContractManage
	 */
	public void addInvoice(TbInvoice invoice, Long createUserId,double amountdd);
}
