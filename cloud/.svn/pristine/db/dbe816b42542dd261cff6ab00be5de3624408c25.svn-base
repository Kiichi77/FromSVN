package cn.com.kxcomm.contractmanage.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.FormulaUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractReceivePaymentDAO;
import cn.com.kxcomm.contractmanage.dao.ContractReceivePaymentDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseCompany;
import cn.com.kxcomm.contractmanage.service.IContractReceivePaymentService;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentVo;
import cn.com.kxcomm.contractmanage.vo.ContractReceivePaymentVo;

@Service("icontractReceivePaymentService")
public class ContractReceivePaymentService extends
CommonService<TbContractReceivePayment> implements IContractReceivePaymentService{
	@Autowired(required=true)
	private ContractReceivePaymentDAO contractReceivePaymentDAO;
	@Autowired(required=true)
	private ContractReceivePaymentDataDAO contractReceivePaymentDataDAO;
	@Autowired(required=true)
	private ContractService contractService;
	@Autowired(required=true)
	private PurchaseCompanyService purchaseCompanyService;
	@Override
	public CommonDAO<TbContractReceivePayment> getBindDao() {
		return contractReceivePaymentDAO;
	}
	@Override
	public List<ContractPaymentVo> contractPaymentList(String contractId,Long userId) {
		 List<ContractPaymentVo> ll = null;
		try{
			Integer pkid = null;
			try{
				if(!BlankUtil.isBlank(contractId)){
					pkid = Integer.parseInt(contractId);
				}
			}catch(NumberFormatException rr){
				this.LOGGER.error("contractPaymentList contractId 不能为空或NumberFormatException!");
			}
			ll = contractReceivePaymentDAO.contractPaymentList(pkid,userId);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return ll;
	}
	@Override
	public PageInfo<ContractPaymentDataVo> contractPaymentDataList(PageInfo<ContractPaymentDataVo> page,Integer contractReceivePaymentId,Integer contractId) {
			return contractReceivePaymentDataDAO.contractPaymentDataList(page,contractReceivePaymentId,contractId);
	}
	@Override
	public ContractReceivePaymentVo getcontractReceivePayment(Integer contractId) {
		ContractReceivePaymentVo vo = new ContractReceivePaymentVo();
		try {
			TbContractReceivePayment contractReceivePayment = this.findByField("contract.id", contractId);
			if(contractReceivePayment==null){
				TbContract contract = contractService.getByPk(contractId);
				if(contract == null)
					return null;
				contractReceivePayment = new TbContractReceivePayment();
				contractReceivePayment.setId(0);
				contractReceivePayment.setContract(contract);
				contractReceivePayment.setContractBeginPercent(0);
				contractReceivePayment.setContractBeginPrice(0d);
				contractReceivePayment.setContractBeginPriceText("零");
				contractReceivePayment.setContractFinalPercent(0);
				contractReceivePayment.setContractFinalPrice(0d);
				contractReceivePayment.setContractFinalPriceText("零");
				contractReceivePayment.setContractGoodsPercent(0);
				contractReceivePayment.setContractGoodsPrice(0d);
				contractReceivePayment.setContractGoodsPriceText("零");
				contractReceivePayment.setContractTotalPrice(0d);
				contractReceivePayment.setContractTotalPriceText("零");
				contractReceivePayment.setStatus(1);
//				TbPurchaseCompany purchaseCompany = purchaseCompanyService.getByPk(1);
//				contractReceivePayment.setPurchaseCompany(purchaseCompany);
				
			}
			EntityToVoUtil.copyObjValue(contractReceivePayment,vo);
//	    	vo.setPurchaseCompany(contractReceivePayment.getPurchaseCompany().getCompanyName());
	    	vo.setContract(contractReceivePayment.getContract().getTitle());
	    	vo.setCustomer(contractReceivePayment.getContract().getProject().getCustomer().getCompanyName());
		    vo.setContractId(""+contractReceivePayment.getContract().getId());
//		    vo.setPurchaseCompanyId(""+contractReceivePayment.getPurchaseCompany().getId());
		} catch (Exception e) {
			LOGGER.error("contractPaymentBycontractId ERROR:",e);
		}
		return vo;
	}
	
	/**
	 * 根据合同ID查询合同金额
	 * @param contractDocId 合同选中文件id
	 */
	@Override
	public Double totleMoneyByContractId(Integer contractId)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(" from TbContractReceivePayment crp where crp.contract.id=? ");
		List<TbContractReceivePayment> docvarList = contractReceivePaymentDataDAO.find(sb.toString(), contractId);
		for (int i = 0; i < docvarList.size(); i++) {
			TbContractReceivePayment contractReceivePayment = docvarList.get(i);
			return contractReceivePayment.getContractTotalPrice();
		}
		return 0d;
		
	}
	@Override
	public void saveOrUpdate(TbContractReceivePayment entity) {
		if(entity.getId()==0){
			entity.setId(null);
		}
		this.contractReceivePaymentDAO.saveOrUpdate(entity);
	}
	@Override
	public Double findPayDatamoney(int contractId,int contractStatus)
	{
		TbContractReceivePayment contractReceivePayment=new TbContractReceivePayment();
		Double money=0.0;
		StringBuffer sb=new StringBuffer();
		sb.append(" from TbContractReceivePayment crp where crp.contract.id=? ");
		contractReceivePayment=(TbContractReceivePayment)contractReceivePaymentDAO.findUnique(sb.toString(), contractId);
		if(contractStatus==1)
		{
			 money=contractReceivePayment.getContractGoodsPrice();
		}else if(contractStatus==2)
		{
			 money=contractReceivePayment.getContractBeginPrice();
		}else if(contractStatus==3)
		{
			 money=contractReceivePayment.getContractFinalPrice();
		}
		return money;
	}
}
