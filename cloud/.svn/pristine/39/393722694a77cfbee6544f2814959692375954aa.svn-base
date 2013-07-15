package cn.com.kxcomm.contractmanage.dao;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePayment;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentVo;

@Repository
public class ContractReceivePaymentDAO  extends CommonDAO<TbContractReceivePayment>{
	@Autowired(required=true)
	private ContractReceivePaymentDataDAO contractReceivePaymentDataDAO;

	/**
	 * 
	 * 获取合同收款对象<br/>
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public List<ContractPaymentVo> contractPaymentList(Integer contractId,Long userId){
		Session  session = this.getSession();
		StringBuilder hql = new StringBuilder();
		hql.append("select project.projectName,contract.contractNo,contract.title,project.customer.companyName");
		hql.append(",payment.contractTotalPrice,contract.creator.userName,payment.status,payment.id,contract.id");
		hql.append(" from TbProject project,TbContract contract ,TbContractReceivePayment payment");
		hql.append(" where project=contract.project and contract=payment.contract");
		if(!BlankUtil.isBlank(userId))
		{
			hql.append(" and contract.creator.id="+userId);
		}
		if(!BlankUtil.isBlank(contractId)){
			hql.append(" and contract.id = ?");
		}
		Query query = session.createQuery(hql.toString());
		if(!BlankUtil.isBlank(contractId)){
			query.setInteger(0, contractId);
		}
		List<Object[]> list = query.list();
		List<ContractPaymentVo> rs = new ArrayList();
		for(Object[] obj : list){
			ContractPaymentVo vo =new ContractPaymentVo();
			vo.setProjectName(obj[0]==null?"":obj[0].toString());
			vo.setContractNo(obj[1]==null?"":obj[1].toString());
			vo.setContractName(obj[2]==null?"":obj[2].toString());
			vo.setBuyerCompany(obj[3]==null?"":obj[3].toString());
			Double contractMoney = obj[4]==null?0:Double.parseDouble(obj[4].toString());
			vo.setContractMoney(contractMoney);
			vo.setSellerName(obj[5]==null?"":obj[5].toString());
			vo.setContractStatus(obj[6]==null?0:Integer.parseInt(obj[6].toString()));
			Double receiveMoney = contractReceivePaymentDataDAO.sumReceiveMoney(Integer.parseInt(obj[7].toString()));
			if(receiveMoney==null){
				receiveMoney = 0d;
			}
			vo.setReceiveMoney(receiveMoney);
			vo.setUnReceiveMoney(contractMoney - receiveMoney);
			vo.setContractPaymentId(Integer.parseInt(obj[7].toString()));
			vo.setContractId(obj[8]==null?"":obj[8].toString());
			rs.add(vo);
		}
		return rs;
	}
	

}
