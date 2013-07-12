package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseInvoice;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IPurchaseInvoiceService;
import cn.com.kxcomm.contractmanage.vo.PurchaseInvoiceVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;

public class PurchaseInvoiceAction extends BaseAction<TbPurchaseInvoice, String>{
	private static Logger LOGGER = Logger.getLogger(PurchaseInvoiceAction.class);
	@Autowired(required=true)
	private IPurchaseInvoiceService purchaseInvoiceService;
	
	private File upFile;
	private String upFileFileName;
	private TbPurchaseInvoice purchaseInvoice;
	private String purchaseContractId;
	
	
	
	public String getPurchaseContractId() {
		return purchaseContractId;
	}

	public void setPurchaseContractId(String purchaseContractId) {
		this.purchaseContractId = purchaseContractId;
	}

	public TbPurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	public void setPurchaseInvoice(TbPurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
	}

	public IPurchaseInvoiceService getPurchaseInvoiceService() {
		return purchaseInvoiceService;
	}

	public void setPurchaseInvoiceService(
			IPurchaseInvoiceService purchaseInvoiceService) {
		this.purchaseInvoiceService = purchaseInvoiceService;
	}

	public File getUpFile() {
		return upFile;
	}

	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}

	public String getUpFileFileName() {
		return upFileFileName;
	}

	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}
	/**
	 * 
	* 方法用途和描述: 根据采购合同查询进项发票
	* @return
	* @author lujia 新增日期：2013-4-27
	* @since ContractManage
	 */
	public String findByPurchaseContractId() {
		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+purchaseContractId);
			PageInfo<PurchaseInvoiceVo> page = new PageInfo<PurchaseInvoiceVo>(ServletActionContext.getRequest());
		    PageInfo<PurchaseInvoiceVo> list=this.purchaseInvoiceService.findByPurchaseContractId(page, Integer.parseInt(purchaseContractId));
		    this.result = JSONObject.fromObject(list);
		} catch (Exception e) {
			LOGGER.error("queryAllPurchaseCompany ERROR:", e);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 方法用途和描述: 保存进项发票文件，上传文件
	 * @return
	 * @author lujia 新增日期：2013-4-27
	 * @since ContractManage
	 */
	 public String savePurchaseInvoice(){
		try {
		 if(null!=upFile){
				HttpServletRequest req = this.getHttpRequest();
				String bakPath = req.getSession().getServletContext().getRealPath("download/purchaseOrderFile/")+File.separator;
				LOGGER.info("bakPath:"+bakPath);
				String FileName = System.currentTimeMillis()+upFileFileName.substring(upFileFileName.lastIndexOf("."), upFileFileName.length());//上传到服务器的文件名
				FileUtil.bakTemplatesDoc(upFile, bakPath, FileName, false,0);
				purchaseInvoiceService.ftpUploadpPurchaseInvoiceFileSync(bakPath,FileName);
				//保存文件 
				  //供应商
				TbSuppliers suppliers=new TbSuppliers();
				suppliers.setId(purchaseInvoice.getSuppliers().getId());
				purchaseInvoice.setSuppliers(suppliers);
				 //采购公司
				TbPurchaseCompany purchaseCompany=new TbPurchaseCompany();
				purchaseCompany.setId(purchaseInvoice.getPurchaseCompany().getId());
				purchaseInvoice.setPurchaseCompany(purchaseCompany);
				purchaseInvoice.setPathName(FileName);
				purchaseInvoice.setCreatetime(new Date());
				purchaseInvoiceService.save(purchaseInvoice);
				this.result = JSONObject.fromObject(successInfo("ok"));
				}
			
		} catch (Exception e) {
			LOGGER.error("savePurchaseInvoice error.",e);
		}
		return SUCCESS;
	 }
	
	@Override
	public ICommonService getCommonService() {
		return purchaseInvoiceService;
	}

	@Override
	public TbPurchaseInvoice getModel() {
		if (this.model == null) {
			this.model = new TbPurchaseInvoice();
		}
		return this.model;
	}

	@Override
	public void setModel(TbPurchaseInvoice model) {
		this.model=model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids=ids;
	}

}
