package cn.com.kxcomm.contractmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.ArrivalProvedDAO;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ContractDAO;
import cn.com.kxcomm.contractmanage.entity.TbArrivalProved;
import cn.com.kxcomm.contractmanage.entity.TbContract;
import cn.com.kxcomm.contractmanage.service.IArrivalProvedService;
import cn.com.kxcomm.contractmanage.status.S_Contract;
import cn.com.kxcomm.contractmanage.vo.ArrivalProvedVo;

@Service("arrivalProvedService")
public class ArrivalProvedService extends
CommonService<TbArrivalProved> implements IArrivalProvedService{
	@Autowired(required=true)
	private ArrivalProvedDAO arrivalProvedDAO;
	@Autowired(required=true)
	private ContractDAO contractDAO;
	
	@Override
	public CommonDAO<TbArrivalProved> getBindDao() {
		return arrivalProvedDAO;
	}

	@Override
	public PageInfo<ArrivalProvedVo> queryAllArrivalProved(PageInfo<ArrivalProvedVo> pageInfo,String contractId) {
		Integer contractPkid = null;
		if(!BlankUtil.isBlank(contractId) && !"null".equals(contractId) ){
			contractPkid = Integer.parseInt(contractId);
		}
		return arrivalProvedDAO.queryAllArrivalProved(pageInfo,contractPkid);
	}
  
	 /**
	  * 
	 * 方法用途和描述: 上传到货、初验、终验文件
	 * @return
	 * @author lujia 新增日期：2013-3-19
	 * @since ContractManage
	  */
	@Override
	public int uploadProved(int provedId,String contractId,int type,String FileName)
	{
		Integer t_provedId = 0;
		//保存文件
		if(provedId==0)
		{
			TbArrivalProved arrivalProved=new TbArrivalProved();
			TbContract contract=contractDAO.findById(Integer.parseInt(contractId));			
			arrivalProved.setContract(contract);
			 if(type==1)
			  {
				  arrivalProved.setGoodsProvedfile(FileName);
				  arrivalProved.setGoodstime(new Date());
				contract.setStatus(S_Contract.CONTRACT_TO_ARRIVAL);
				  contractDAO.merge(contract);
			  }else if(type==2)
			  {
				  arrivalProved.setBeginProvedfile(FileName);
				  arrivalProved.setBegintime(new Date());
				  contract.setStatus(S_Contract.CONTRACT_TO_INITIAL);
				  contractDAO.merge(contract);
			  }else if(type==3)
			  {
				  arrivalProved.setFinalProvedfile(FileName);
				  arrivalProved.setFinaltime(new Date());
				  contract.setStatus(S_Contract.CONTRACT_TO_FINAL);
				  contractDAO.merge(contract);
			  }
			 t_provedId = (Integer) this.arrivalProvedDAO.save(arrivalProved);
			 arrivalProved.setId(t_provedId);
			 contract.setArrivalProved(arrivalProved);
			 contractDAO.saveOrUpdate(contract);
			 return arrivalProved.getId();
		}else{
		//修改文件
		  TbArrivalProved arrivalProved=this.getByPk(provedId);
		  TbContract contract=contractDAO.findById(Integer.parseInt(contractId));		
		  if(type==1)
		  {
			  arrivalProved.setGoodsProvedfile(FileName);
			  arrivalProved.setGoodstime(new Date());
			  contract.setStatus(S_Contract.CONTRACT_TO_ARRIVAL);
			  contractDAO.merge(contract);
		  }else if(type==2)
		  {
			  arrivalProved.setBeginProvedfile(FileName);
			  arrivalProved.setBegintime(new Date());
			  contract.setStatus(S_Contract.CONTRACT_TO_INITIAL);
			  contractDAO.merge(contract);
		  }else if(type==3)
		  {
			  arrivalProved.setFinalProvedfile(FileName);
			  arrivalProved.setFinaltime(new Date());
			  contract.setStatus(S_Contract.CONTRACT_TO_FINAL);
			  contractDAO.merge(contract);
		  }
		  this.update(arrivalProved);
		  return arrivalProved.getId();
		}
	}
}
