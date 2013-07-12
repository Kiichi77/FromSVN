package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseInvoice;
import cn.com.kxcomm.contractmanage.vo.PurchaseInvoiceVo;

public interface IPurchaseInvoiceService extends ICommonService<TbPurchaseInvoice>{
	public void ftpUploadpPurchaseInvoiceFileSync(String localPath,String fileName);
	public void ftpDownloadSync(String localPath);
	public PageInfo<PurchaseInvoiceVo> findByPurchaseContractId(PageInfo<PurchaseInvoiceVo> pageInfo,Integer purchaseContractId);
}
