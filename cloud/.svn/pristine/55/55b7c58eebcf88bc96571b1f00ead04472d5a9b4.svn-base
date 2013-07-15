package cn.com.kxcomm.contractmanage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbArrivalProved;
import cn.com.kxcomm.contractmanage.vo.ArrivalProvedVo;

@Repository
public class ArrivalProvedDAO extends CommonDAO<TbArrivalProved>{

	/**
	 * 
	 * 查询所有合同下的收货、初验、终验
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public PageInfo<ArrivalProvedVo> queryAllArrivalProved(PageInfo<ArrivalProvedVo> pageInfo,Integer contractPkid) {
		Session  session = this.getSession();// left join TbArrivalProved arrival on contract.id = arrival.contract.id
		StringBuilder hql = new StringBuilder();
		String where = "";
		hql.append("select arrival.id,contract.project.projectName,contract.title,contract.project.customer.companyName,contract.creator.userName," +
				"arrival.goodstime,arrival.begintime,arrival.finaltime,arrival.goodsProvedfile, arrival.beginProvedfile,arrival.finalProvedfile," +
				"contract.id,contract.project.customer.customerId" +
				" from TbContract contract left  join contract.arrivalProved  arrival where 1=1 ");
		if(!BlankUtil.isBlank(contractPkid)){
			where = " and contract.id=? ";
		}
		where = " order by contract.createtime desc";
		PageInfo<TbArrivalProved> p =new PageInfo<TbArrivalProved>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List list=this.pageInfoQuery(hql+where, p,contractPkid);
		int count = this.findTotalCount("select count(*)  from TbContract contract left  join contract.arrivalProved  arrival where 1=1 " +where,contractPkid);
		Object[] obj = new Object[list.size()];	
/*		
		Query query = session.createQuery(hql.toString());
		if(!BlankUtil.isBlank(contractPkid)){
			query.setInteger(0, contractPkid);
		}
		List<Object[]> list = query.list();*/
		List<ArrivalProvedVo> rs = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ArrivalProvedVo vo = new ArrivalProvedVo();
			vo.setId(obj[0]==null?"":obj[0].toString());
			vo.setProjectName(obj[1]==null?"":obj[1].toString());
			vo.setContractName(obj[2]==null?"":obj[2].toString());
			vo.setCustomer(obj[3]==null?"":obj[3].toString());
			vo.setSell(obj[4]==null?"":obj[4].toString());
			vo.setGoodstime(obj[5]==null?"":obj[5].toString());
			vo.setBegintime(obj[6]==null?"":obj[6].toString());
			vo.setFinaltime(obj[7]==null?"":obj[7].toString());
			vo.setGoodsProvedfile(obj[8]==null?"":obj[8].toString());
			vo.setBeginProvedfile(obj[9]==null?"":obj[9].toString());
			vo.setFinalProvedfile(obj[10]==null?"":obj[10].toString());
			vo.setContractId(obj[11]==null?"":obj[11].toString());
			vo.setCustomerId(""+obj[12]);  //客户id
			rs.add(vo);
		}
		PageInfo<ArrivalProvedVo> page = new PageInfo<ArrivalProvedVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),rs);
		return page;
	}
}
