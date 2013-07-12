package cn.com.kxcomm.contractmanage.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.AppConfig;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FtpUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.PurchaseOrderContractFileDAO;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractBill;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractBillVo;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;
import cn.com.kxcomm.contractmanage.web.util.FtpSync;

@Service("purchaseOrderContractFileService")
public class PurchaseOrderContractFileService extends CommonService<TbPurchaseOrderContractFile> implements IPurchaseOrderContractFileService{
	private static final Logger log = Logger.getLogger(PurchaseOrderContractFileService.class);

	@Autowired(required=true)
	private PurchaseOrderContractFileDAO purchaseOrderContractFileDAO;
	
	/**
	* 
	* 方法用途和描述: 分页查询所有的采购合同文件
	* @return
	* @author luj 新增日期：2013-3-12
	* @since ContractManage
	*/
	@Override
	public PageInfo<PurchaseOrderContractFileVo> listPurchaseContractFile(PageInfo<PurchaseOrderContractFileVo> pageInfo,String contratFileId) 
	{
		Integer id=null;
		if(!BlankUtil.isBlank(contratFileId) && !"null".equals(contratFileId)) {
			id=Integer.parseInt(contratFileId);
		}
		StringBuffer hql=new StringBuffer();
		String where = "";
		hql.append("select po from TbPurchaseOrderContractFile po ");
		if(id!=null){
			where="where po.id = ? ";
		}
		hql.append(where);
		PageInfo<TbPurchaseOrderContractFile> p = new PageInfo<TbPurchaseOrderContractFile>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List<TbPurchaseOrderContractFile> list=purchaseOrderContractFileDAO.pageInfoQuery(hql.toString(), p, id);
		hql.delete(0, hql.length());
		hql.append("select count(*) from TbPurchaseOrderContractFile po "+where);
		int count = purchaseOrderContractFileDAO.findTotalCount(hql.toString(),id);
		List<PurchaseOrderContractFileVo> listContractFile=new ArrayList<PurchaseOrderContractFileVo>();
		for(TbPurchaseOrderContractFile purchaseOrderContractFile:list) {
			PurchaseOrderContractFileVo purchaseOrderContractFileVo=new PurchaseOrderContractFileVo();
			purchaseOrderContractFileVo.setId(""+purchaseOrderContractFile.getId());
			purchaseOrderContractFileVo.setTitle(purchaseOrderContractFile.getTitle());
			purchaseOrderContractFileVo.setSuppliers(purchaseOrderContractFile.getSuppliers().getCompanyName());
			purchaseOrderContractFileVo.setPathName(purchaseOrderContractFile.getPathName());
			purchaseOrderContractFileVo.setCreate(purchaseOrderContractFile.getCreate().getUserName());
			purchaseOrderContractFileVo.setContract(purchaseOrderContractFile.getContract().getTitle());
			NumberFormat nf = NumberFormat.getNumberInstance();
	        nf.setMaximumFractionDigits(2);
			purchaseOrderContractFileVo.setContractMoney(""+nf.format(purchaseOrderContractFile.getContractMoney()));
			purchaseOrderContractFileVo.setProjectName(purchaseOrderContractFile.getContract().getProject().getProjectName());
			purchaseOrderContractFileVo.setIsPay(""+purchaseOrderContractFile.getIsPay());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
			purchaseOrderContractFileVo.setCreatetime(sdf.format(purchaseOrderContractFile.getCreatetime()));
		    purchaseOrderContractFileVo.setPaymentType(purchaseOrderContractFile.getPaymentType());
//			purchaseOrderContractFileVo.setCheckNo(purchaseOrderContractFile.getCheckNo());
//			purchaseOrderContractFileVo.setPaymentTime(purchaseOrderContractFile.getPaymentTime());
			listContractFile.add(purchaseOrderContractFileVo);
		}
		PageInfo<PurchaseOrderContractFileVo> page = new PageInfo<PurchaseOrderContractFileVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listContractFile);
		return page;
	}
    
	/**
	* 
	* 方法用途和描述: 根据采购合同文件Id查询采购付款明细
	* @return
	* @author luj 新增日期：2013-3-13
	* @since ContractManage
	*/
	@Override
	public PurchaseOrderContractFileVo queryByPurchaseContractId(int id){
		PurchaseOrderContractFileVo purchaseOrderContractFileVo=new PurchaseOrderContractFileVo();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT id,project_name,ct,pct,is_pay,contract_money,scn,payment,total_overpay_money,oaid,purchase_company_id, pmcn,pctype from ");
		sql.append(" (SELECT DISTINCT pc.id,p.project_name,c.title ct,pc.title pct,pc.is_pay,pc.contract_money,s.company_name scn,pc.payment,oa.total_overpay_money,oa.id oaid,oa.purchase_company_id,pm.company_name pmcn,pc.payment_type pctype");
		sql.append(" from tb_purchase_order_contractfile pc,tb_contract c,tb_project p,tb_suppliers s,tb_overpay_account oa,tb_purchase_company pm ");
		sql.append(" WHERE pc.suppliers_id=s.id  AND pc.contract_id=c.id AND c.project_id=p.project_id ");
		sql.append(" AND pc.suppliers_id=oa.suppliers_id AND oa.purchase_company_id=pm.id AND pc.id=? ");
		sql.append(" ) tt");
		List list=purchaseOrderContractFileDAO.findById(sql.toString(), id);
		Object[] obj = new Object[list.size()];	
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			purchaseOrderContractFileVo.setId(obj[0].toString());
			purchaseOrderContractFileVo.setProjectName(obj[1].toString());
			purchaseOrderContractFileVo.setContract(obj[2].toString());
			purchaseOrderContractFileVo.setTitle(obj[3].toString());
			purchaseOrderContractFileVo.setIsPay(obj[4].toString());
			purchaseOrderContractFileVo.setContractMoney(obj[5].toString());
			purchaseOrderContractFileVo.setSuppliers(obj[6].toString());
		//	NumberFormat nf = NumberFormat.getNumberInstance();
	  //      nf.setMaximumFractionDigits(2);
			purchaseOrderContractFileVo.setTotalpayment(obj[7].toString());
			purchaseOrderContractFileVo.setTotalOverPayMoney(obj[8].toString());
			purchaseOrderContractFileVo.setOverPayId(obj[9].toString());
			purchaseOrderContractFileVo.setPurchaseCompanyId(obj[10].toString());
			purchaseOrderContractFileVo.setPurchaseCompany(obj[11].toString());
			purchaseOrderContractFileVo.setPaymentType(obj[12].toString());
		}
		return purchaseOrderContractFileVo;
	}
	/**
	* 
	* 方法用途和描述: 修改实际付款数
	* @return
	* @author luj 新增日期：2013-3-14
	* @since ContractManage
	*/
	@Override
	public void merge(TbPurchaseOrderContractFile purchaseOrderContractFile) {
		purchaseOrderContractFileDAO.merge(purchaseOrderContractFile);
	}
	@Override
	public CommonDAO<TbPurchaseOrderContractFile> getBindDao() {
		return purchaseOrderContractFileDAO;
	}

	/**
	 * 
	 * 方法用途和描述: 根据采购合同文件Id查询
	 * @return
	 * @author luj 新增日期：2013-4-26
	 * @since ContractManage
	 */
	@Override
	public PurchaseOrderContractFileVo findByContractId(String id)
	{
	 TbPurchaseOrderContractFile purchaseOrderContractFile=purchaseOrderContractFileDAO.findById(Integer.parseInt(id));
	 PurchaseOrderContractFileVo purchaseOrderContractFileVo=new PurchaseOrderContractFileVo();
	 purchaseOrderContractFileVo.setId(""+purchaseOrderContractFile.getId());
	 purchaseOrderContractFileVo.setTitle(purchaseOrderContractFile.getTitle());
//	 purchaseOrderContractFileVo.setCheckNo(purchaseOrderContractFile.getCheckNo());
	 NumberFormat nf = NumberFormat.getNumberInstance();
	     nf.setMaximumFractionDigits(2);
	 purchaseOrderContractFileVo.setContractMoney(""+nf.format(purchaseOrderContractFile.getContractMoney()));
	 purchaseOrderContractFileVo.setPaymentType(purchaseOrderContractFile.getPaymentType());
	 purchaseOrderContractFileVo.setSuppliers(purchaseOrderContractFile.getSuppliers().getCompanyName());
	 purchaseOrderContractFileVo.setAccounts(purchaseOrderContractFile.getSuppliers().getAccounts());
	 purchaseOrderContractFileVo.setOpenBank(purchaseOrderContractFile.getSuppliers().getOpenBank());
	 purchaseOrderContractFileVo.setTaxpayerNumber(purchaseOrderContractFile.getSuppliers().getTaxpayerNumber());
	 purchaseOrderContractFileVo.setSuppliersId(""+purchaseOrderContractFile.getSuppliers().getId());
	 return purchaseOrderContractFileVo;
	}
	/**
	* 
	* 方法用途和描述: 上传采购合同
	* @return
	* @author luj 新增日期：2013-3-25
	* @since ContractManage
	*/
	@Override
	public void ftpUploadpPurchaseOrderFileSync(String localPath,String fileName) {
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
