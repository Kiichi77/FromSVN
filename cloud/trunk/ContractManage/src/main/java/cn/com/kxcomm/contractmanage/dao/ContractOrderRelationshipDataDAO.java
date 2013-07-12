package cn.com.kxcomm.contractmanage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbContractOrderRelationshipData;
import cn.com.kxcomm.contractmanage.vo.CurrentUnitVo;

@Repository
public class ContractOrderRelationshipDataDAO extends CommonDAO<TbContractOrderRelationshipData>{
	/**
	 * 
	* 返回合同订单下的数据
	* @return
	* @author zhangjh 新增日期：2013-3-19
	* @since ContractManage
	 */
	public List<CurrentUnitVo> queryContractOrderRelationshipData(Integer contractOrderRelationshipId){
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select data.id,data.modelData.id,data.orderQuantity," +
				"data.modelData.dataRecord.productType.name,data.modelData.dataRecord.productType.category.name," +
				"data.modelData.dataRecord.productDesc,data.modelData.dataRecord.productOrderNo" +
				" from TbContractOrderRelationshipData data where data.contractOrderRelationship.id=?" );
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderRelationshipId);
		List<Object[]> list = query.list();
		System.out.println("list.size():"+list.size());
		List<CurrentUnitVo> rs = new ArrayList();
		for(Object[] obj : list){
			CurrentUnitVo vo = new CurrentUnitVo();
			vo.setId(obj[0]==null?"":obj[0].toString());
			vo.setConfigModelDataId(obj[1]==null?"":obj[1].toString());
			vo.setQuantity(obj[2]==null?"":obj[2].toString());
			vo.setTypeName(obj[3]==null?"":obj[3].toString());
			vo.setCategoryName(obj[4]==null?"":obj[4].toString());
			vo.setProductDesc(obj[5]==null?"":obj[5].toString());
			vo.setProductOrderNo(obj[6]==null?"":obj[6].toString());
			Long ordered = countContractOrderRelationshipData(contractOrderRelationshipId,Integer.parseInt(obj[1].toString()));
			if(ordered==null){
				ordered = 0L;
			}
			System.out.println(ordered);
			vo.setOrdered(ordered.toString());
			System.out.println(vo.toString());
			rs.add(vo);
		}
		return rs;
	}
	
	private Long countContractOrderRelationshipData(Integer contractOrderRelationshipId,Integer modelDataId){
		Long orderNum = 0L;
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select sum(purchaseOrderShip.orderQuantity) from TbPurchaseOrderRelationshipData purchaseOrderShip where " +
				"purchaseOrderShip.purchaseOrderRelationship.contractOrderRelationship.id = ? and purchaseOrderShip.modelData.id=?" );
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderRelationshipId);
		query.setInteger(1, modelDataId);
		List list = query.list();
		System.out.println("list.size():"+list.size());
		for(Object obj : list){
			System.out.println(obj);
			orderNum =(Long) obj;
		}
		return orderNum;
	}
}