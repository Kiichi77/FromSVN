package cn.com.kxcomm.contractmanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.dao.ContractReceivePaymentDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePaymentData;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IContractReceivePaymentDataService;
import cn.com.kxcomm.contractmanage.status.S_Contract;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;

@Service("contractReceivePaymentDataService")
public class ContractReceivePaymentDataService extends CommonService<TbContractReceivePaymentData>
		implements IContractReceivePaymentDataService {
	@Autowired(required=true)
	private ContractReceivePaymentDataDAO contractReceivePaymentDataDAO;
	@Autowired(required=true)
	private UserServiceImpl userService;
	@Autowired(required=true)
	private ContractReceivePaymentService icontractReceivePaymentService;
	@Autowired(required=true)
	private ContractDAO contractDAO;
	@Override
	public CommonDAO<TbContractReceivePaymentData> getBindDao() {
		return contractReceivePaymentDataDAO;
	}
	

	public ContractDAO getContractDAO() {
		return contractDAO;
	}


	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}


	@Override
	public boolean addPaymentData(ContractPaymentDataVo vo) {
		if(vo == null){
			return false;
		}
		if(vo.getMoney()==null||vo.getReceiveTime()==null||vo.getPayType()==null||vo.getFinancialId()==null||vo.getContractReceivePayment()==null){
			return false;
		}
		try {
		TbContractReceivePaymentData data = new TbContractReceivePaymentData();
		data.setMoney(vo.getMoney());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date reTime = sdf.parse(vo.getReceiveTime());
		data.setReceiveTime(reTime);
		if(vo.getNotes()!=null){
			data.setNotes(vo.getNotes());
		}
		data.setPayType(vo.getPayType());
		TbUser financial = userService.getByPk(vo.getFinancialId());
		data.setFinancial(financial);
		data.setStatus(0);
		TbContractReceivePayment contractReceivePayment = icontractReceivePaymentService.getByPk(vo.getContractReceivePayment());
		data.setContractReceivePayment(contractReceivePayment);		
		//添加合同收款明细
		contractReceivePaymentDataDAO.save(data);
		//更新合同收款总表收款状态
		updatePaymentState(vo.getPayType(),vo.getContractReceivePayment());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public PageInfo<ContractPaymentDataVo> contractAllPaymentDataList(PageInfo<ContractPaymentDataVo> page,String contractId) {
		Integer contractPkid = null;
		if(!BlankUtil.isBlank(contractId) && !"null".equals(contractId)){
			contractPkid = Integer.parseInt(contractId);
		}
		return icontractReceivePaymentService.contractPaymentDataList(page,null,contractPkid);
	}

	@Override
	public boolean confirMoney(ContractPaymentDataVo vo){
		if(vo.getDataId()==null||vo.getAccpetTime()==null||vo.getBuyerAccount()==null){
			return false;
		}
		try{
			TbContractReceivePaymentData entity =contractReceivePaymentDataDAO.findById(vo.getDataId());
			entity.setId(vo.getDataId());
			entity.setStatus(1);
			entity.setBuyerAccount(vo.getBuyerAccount());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date accpetTime = sdf.parse(vo.getAccpetTime());
			entity.setAccpetTime(accpetTime);
			int contractId=0;
			if(!BlankUtil.isBlank(vo.getContractId()))
			{
				contractId=Integer.parseInt(vo.getContractId());
			}
			TbContract contract=contractDAO.findById(contractId);
			if(vo.getPayType()==11)
			{
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+vo.getPayType());
				contract.setStatus(S_Contract.CONTRACT_TO_ARRIVAL_MONEY);
			}else if(vo.getPayType()==12)
			{
				contract.setStatus(S_Contract.CONTRACT_TO_INITIAL_MONEY);
			}
			else if(vo.getPayType()==13)
			{
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@"+contract.getId());
				contract.setStatus(S_Contract.CONTRACT_TO_FINAL_MONEY);
			}
			contractDAO.merge(contract);
			contractReceivePaymentDataDAO.merge(entity);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	* 方法用途和描述:  更新合同收款状态
	* @param payType
	* @param pkid
	* @author chenliang 新增日期：2013-3-26
	* @since ContractManage
	 */
	private void updatePaymentState(int payType,int pkid) {
		int status = 1;
		if(11==payType){ //到货
			status = 2; //到货款收到，状态转为待收初验款
		}else if(12==payType){ //初验
			status = 3; //初验款收到，状态转为待收终验款
		}else if(13==payType){ //终验
			status = 0; //终验款收到，状态转为完成收款
		}
		String hql = "update TbContractReceivePayment t set t.status=? where id=? ";
		contractReceivePaymentDataDAO.executeByHQL(hql, status,pkid);
	}

}
