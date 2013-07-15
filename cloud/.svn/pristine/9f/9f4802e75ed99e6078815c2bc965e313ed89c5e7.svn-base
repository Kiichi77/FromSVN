package cn.com.kxcomm.contractmanage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractReceivePaymentData;
import cn.com.kxcomm.contractmanage.vo.ContractPaymentDataVo;

@Repository
public class ContractReceivePaymentDataDAO  extends CommonDAO<TbContractReceivePaymentData>{
	/**
	 * 
	 * 返回合同已收到的金额
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 */
	public Double sumReceiveMoney(Integer contractReceivePaymentId){
		Session  session = this.getSession();
		String hql = "select sum(data.money) from TbContractReceivePaymentData data where data.contractReceivePayment.id = ? and data.status=1";
		Query query = session.createQuery(hql.toString());
		query.setParameter(0, contractReceivePaymentId);
		List list = query.list();
		System.out.println(list.get(0));
		Double total= (Double) list.get(0);
		return total;
	}
	
	/**
	 * 
	 * 返回合同收款下面的明细
	 * 
	 * @param contractReceivePaymentId 当为空：则查询全部
	 * @return
	 * @author zhangjh 新增日期：2013-3-15
	 * @since ContractManage
	 * TODO 新增合同收款明细ID，合同编号，合同名称，卖方公司，买方公司，项目名称
	 */
	public PageInfo<ContractPaymentDataVo> contractPaymentDataList(PageInfo<ContractPaymentDataVo> pageInfo,Integer contractReceivePaymentId,Integer contractId){
		StringBuilder hql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		//查询的字段
		hql.append("select receivePaymentData.buyerAccount,receivePaymentData.payType,receivePaymentData.money" +
				",receivePaymentData.receiveTime,receivePaymentData.accpetTime,receivePaymentData.financial.userName,receivePaymentData.status," +
				"receivePaymentData.Notes,receivePayment.id,receivePaymentData.financial.id,receivePaymentData.id,contract.contractNo,contract.title," +
				"project.projectName,project.customer.companyName,contract.id");
		//被查询的表
		hql.append(" from TbContractReceivePaymentData receivePaymentData," +
				"TbContractReceivePayment receivePayment,TbContract contract ,TbProject project");
		//表关联关系
		hql.append(" where receivePaymentData.contractReceivePayment = receivePayment " +
				"  and contract=receivePayment.contract and project=contract.project " );
		//筛选条件
		if(contractReceivePaymentId!=null){
			where.append(" and receivePayment.id=?");
		}
		if(contractId != null){
			where.append(" and contract.id=? ");
		}
		hql.append(where);
		//排序顺序
		hql.append(" order by receivePaymentData.receiveTime desc");
		PageInfo<TbContractReceivePaymentData> p =new PageInfo<TbContractReceivePaymentData>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List list=this.pageInfoQuery(hql.toString(), p,contractReceivePaymentId,contractId);
		int count = this.findTotalCount("select count(*) from TbContractReceivePaymentData receivePaymentData," +
				"TbContractReceivePayment receivePayment,TbContract contract ,TbProject project where receivePaymentData.contractReceivePayment = receivePayment " +
				"  and contract=receivePayment.contract and project=contract.project " +where,contractReceivePaymentId,contractId);

		Object[] obj = new Object[list.size()];	
		List<ContractPaymentDataVo> rs = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ContractPaymentDataVo vo =new ContractPaymentDataVo();
			vo.setBuyerAccount(obj[0]==null?"":obj[0].toString());
			vo.setPayType(obj[1]==null?0:Integer.parseInt(obj[1].toString()));
			Double money = obj[2]==null?0:Double.parseDouble(obj[2].toString());
			vo.setMoney(money);
			vo.setReceiveTime(obj[3]==null?"":obj[3].toString());
			vo.setAccpetTime(obj[4]==null?"":obj[4].toString());
			vo.setFinancialName(obj[5]==null?"":obj[5].toString());
			vo.setStatus(obj[6]==null?0:Integer.parseInt(obj[6].toString()));
			vo.setNotes(obj[7]==null?"":obj[7].toString());
			vo.setContractReceivePayment(obj[8]==null?0:Integer.parseInt(obj[8].toString()));
			vo.setFinancialId(obj[9]==null?0:Long.parseLong(obj[9].toString()));
			vo.setDataId(obj[10]==null?0:Integer.parseInt(obj[10].toString()));
			vo.setContractNo(obj[11]==null?"":obj[11].toString());
			vo.setContractName(obj[12]==null?"":obj[12].toString());
			vo.setProjectName(obj[13]==null?"":obj[13].toString());
			vo.setBuyerCompany(obj[14]==null?"":obj[14].toString());
			vo.setContractId(obj[15]==null?"":obj[15].toString());
			rs.add(vo);
		}
		PageInfo<ContractPaymentDataVo> page = new PageInfo<ContractPaymentDataVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),rs);
		return page;
	}
}
