package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractBillVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;

public interface IPurchaseOrderContractFileService extends ICommonService<TbPurchaseOrderContractFile>{
	public PageInfo<PurchaseOrderContractFileVo> listPurchaseContractFile(PageInfo<PurchaseOrderContractFileVo> pageInfo,String contratFileId) ;
	public PurchaseOrderContractFileVo queryByPurchaseContractId(int id);
	public void merge(TbPurchaseOrderContractFile purchaseOrderContractFile);
	public void ftpUploadpPurchaseOrderFileSync(String localPath,String fileName);
	public void ftpDownloadSync(String localPath);
	public PurchaseOrderContractFileVo findByContractId(String id);
}
