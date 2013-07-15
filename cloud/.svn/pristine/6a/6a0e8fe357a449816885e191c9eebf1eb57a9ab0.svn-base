package cn.com.kxcomm.contractmanage.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.AppConfig;
import cn.com.kxcomm.common.util.FtpUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseInvoiceDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseInvoice;
import cn.com.kxcomm.contractmanage.service.IPurchaseInvoiceService;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseInvoiceVo;
import cn.com.kxcomm.contractmanage.web.util.FtpSync;
@Service("purchaseInvoiceService")
public class PurchaseInvoiceService extends CommonService<TbPurchaseInvoice> implements IPurchaseInvoiceService{
	private static final Logger log = Logger.getLogger(PurchaseInvoiceService.class);
	@Autowired(required=true)
	private PurchaseInvoiceDAO purchaseInvoiceDAO;
	@Override
	public CommonDAO<TbPurchaseInvoice> getBindDao() {
		return purchaseInvoiceDAO;
	}
	
	/**
	* 
	* 方法用途和描述: 根据采购合同查询进项发票
	* @return
	* @author luj 新增日期：2013-3-25
	* @since ContractManage
	*/
	@Override
	public PageInfo<PurchaseInvoiceVo> findByPurchaseContractId(PageInfo<PurchaseInvoiceVo> pageInfo,Integer purchaseContractId)
	{
		List<PurchaseInvoiceVo> listPurchaseInvoice=new ArrayList<PurchaseInvoiceVo>();
		String hql = " from TbPurchaseInvoice pi where pi.purchaseOrderContractFile.id=?";
		PageInfo<TbPurchaseInvoice> p = new PageInfo<TbPurchaseInvoice>();
		p.setPageSize(pageInfo.getPageSize());
		p.setCurrentPage(pageInfo.getCurrentPage());
		p = purchaseInvoiceDAO.findByPage(p, hql,purchaseContractId);
		List<TbPurchaseInvoice> list=p.getRows();
		int count = purchaseInvoiceDAO.findTotalCount("select count(*) from TbPurchaseInvoice pi where pi.purchaseOrderContractFile.id=?",purchaseContractId);
	   for(TbPurchaseInvoice purchaseInvoice:list)
	   {
		   PurchaseInvoiceVo purchaseInvoiceVo=new PurchaseInvoiceVo();
		   purchaseInvoiceVo.setId(""+purchaseInvoice.getId());
		   purchaseInvoiceVo.setAmount(""+purchaseInvoice.getAmount());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
		   purchaseInvoiceVo.setCreatetime(sdf.format(purchaseInvoice.getCreatetime()));
	       purchaseInvoiceVo.setDateInvoice(purchaseInvoice.getDateInvoice());
	       purchaseInvoiceVo.setInvoiceNo(purchaseInvoice.getInvoiceNo());
	       purchaseInvoiceVo.setPathName(purchaseInvoice.getPathName());
	       purchaseInvoiceVo.setPurchaseAccount(purchaseInvoice.getPurchaseAccount());
	       purchaseInvoiceVo.setPurchaseBankOpen(purchaseInvoice.getPurchaseBankOpen());
	       purchaseInvoiceVo.setPurchaseCompany(purchaseInvoice.getPurchaseCompany().getCompanyName());
	       purchaseInvoiceVo.setPurchaseOrderContractFile(purchaseInvoice.getPurchaseOrderContractFile().getTitle());
	       purchaseInvoiceVo.setSuppliers(purchaseInvoice.getSuppliers().getCompanyName());
	       purchaseInvoiceVo.setTaxpayerNo(purchaseInvoice.getTaxpayerNo());
	       listPurchaseInvoice.add(purchaseInvoiceVo);
	   }
		PageInfo<PurchaseInvoiceVo> page = new PageInfo<PurchaseInvoiceVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listPurchaseInvoice);
		 return page;
	}
	/**
	* 
	* 方法用途和描述: 上传采购合同
	* @return
	* @author luj 新增日期：2013-3-25
	* @since ContractManage
	*/
	@Override
	public void ftpUploadpPurchaseInvoiceFileSync(String localPath,String fileName) {
		AppConfig config = AppConfig.getInstance();
		boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
		if(!isFtpSync)
			return ;

		FtpSync.ftpUploadSync(localPath, "purchaseOrderFile", fileName);
	}
	
	/**
	* 
	* 方法用途和描述: 下载采购合同
	* @return
	* @author luj 新增日期：2013-3-25
	* @since ContractManage
	*/
	@Override
	public void ftpDownloadSync(String localPath) {
		AppConfig config = AppConfig.getInstance();
		boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
		if(!isFtpSync)
			return ;
		String ftpHost=config.getString("ftpHost");
		String ftpUser=config.getString("ftpUser");
		String ftpPasswd=config.getString("ftpPasswd");
		String purchaseOrderFile=config.getString("purchaseOrderFile");
		try {
			FtpUtil.downFiles(ftpHost, 21, ftpUser, ftpPasswd, purchaseOrderFile, localPath, ".docx", null, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
