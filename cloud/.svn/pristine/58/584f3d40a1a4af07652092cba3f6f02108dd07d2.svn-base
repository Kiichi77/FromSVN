package cn.com.kxcomm.contractmanage.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.DateFormatUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractOrder;
import cn.com.kxcomm.contractmanage.vo.CheckinquiryOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractOrderVo;
import cn.com.kxcomm.contractmanage.vo.ContractQuotationsVo;

@Repository
public class ContractOrderDAO  extends CommonDAO<TbContractOrder>{
	private static final Logger log = Logger.getLogger(CustomerDAO.class);
	
	/**
	 * 
	 * 获取所有合同订单
	 * @author chenliang 新增日期：2013-2-28
	 * @param hql  执行的   
	 * @return
	 */
	public List query(String hql,PageInfo<ContractOrderVo> pageInfo,Object ...objects) {
		Session sessionUse = this.getSession();
		Query sq=sessionUse.createQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof Integer){
				sq.setInteger(j, Integer.parseInt(objects[i].toString()));
			}else if(objects[i] instanceof String){
				sq.setString(j,""+objects[i]);
			}else if(objects[i] instanceof Date){
				sq.setDate(j, DateFormatUtil.parseStrToDate(""+objects[i]));
			}
			j++;
		}
		if ((pageInfo.getCurrentPage() !=0 && pageInfo.getPageSize() !=0)  ) {  
			sq.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPageSize()).setMaxResults(pageInfo.getPageSize());
		}
		List lista = sq.list();
		return lista;
	}
	
	
	/**
	 * 
	 * 分页查询
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List queryPage(String hql,PageInfo<CheckinquiryOrderVo> pageInfo,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i]) && -1!=Integer.parseInt(objects[i].toString())){
				sq.setParameter(j, objects[j]);
				j++;
			}
		}
		if ((pageInfo.getCurrentPage() !=0 && pageInfo.getPageSize() !=0)  ) {  
			sq.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPageSize()).setMaxResults(pageInfo.getPageSize());
		}
		List lista = sq.list();
		return lista;
	}
	
	
	/**
	 * 
	 * 查询所有合同订单下的机型配置
	 * 
	 * @param contractOrderId
	 * @return
	 * @author zhangjh 新增日期：2013-3-19
	 * @since ContractManage
	 */
	public List<ContractQuotationsVo> querySaleOrder(Integer contractOrderId,Integer suppliers){
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select contractOrderShip.id," +
				"contractOrderShip.quotations.title,contractOrderShip.orderQuantity,contractOrderShip.configModels.name," +
				"contractOrderShip.configModels.id,contractOrderShip.quotations.id" );
		hql.append(" from TbContractOrderRelationship contractOrderShip where contractOrderShip.contractOrder.id=? " );
		if(suppliers!=null){
			hql.append(" and contractOrderShip.suppliers.id=?");
		}
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderId);
		if(suppliers!=null){
			query.setInteger(1, suppliers);
		}
		List<Object[]> list = query.list();
		List<ContractQuotationsVo> rs = new ArrayList();
		for(Object[] obj : list){
			ContractQuotationsVo vo = new ContractQuotationsVo();
			vo.setId(obj[0]==null?"":obj[0].toString());
			vo.setQuotationName(obj[1]==null?"":obj[1].toString());
			vo.setQuantity(obj[2]==null?"":obj[2].toString());
			vo.setConfigModelName(obj[3]==null?"":obj[3].toString());
			vo.setConfigModelId(obj[4]==null?"":obj[4].toString());
			Long ordered = contractOrdered(Integer.parseInt(obj[0].toString()));
			if(ordered==null){
				ordered = 0L;
			}
			System.out.println(ordered);
			vo.setOrdered(Long.toString(ordered));
			vo.setQuotationsId(obj[5]==null?"":obj[5].toString());
			System.out.println(vo.toString());
			rs.add(vo);
		}
		return rs;
	}
	
	/**
	 * 
	 * 计算销售订单+机型配置的采购数
	 * 
	 * @param contractOrderRelationshipId
	 * @return
	 * @author zhangjh 新增日期：2013-3-19
	 * @since ContractManage
	 */
	public Long contractOrdered(Integer contractOrderRelationshipId){
		Long orderNum = 0L;
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select sum(purchaseOrderShip.purchaseQuantity) from TbPurchaseOrderRelationship purchaseOrderShip where purchaseOrderShip.contractOrderRelationship.id = ?" );
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderRelationshipId);
		List list = query.list();
		System.out.println("list.size():"+list.size());
		for(Object obj : list){
			System.out.println(obj);
			orderNum =(Long) obj;
		}
		return orderNum;
	}
	
	/**
	 * 
	 * 返回合同订单下的已采购数
	 * 
	 * @param contractOrderId 销售订单ID
	 * @return 该销售订单已采购的总数
	 * @author zhangjh 新增日期：2013-3-22
	 * @since ContractManage
	 */
	public Long countContractOrderPurchaseOrder(Integer contractOrderId){
		Long orderNum = 0L;
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select sum(purchaseOrderShip.purchaseQuantity) from TbPurchaseOrderRelationship purchaseOrderShip where purchaseOrderShip.purchaseOrder.contractOrder.id = ?" );
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderId);
		List list = query.list();
		System.out.println("list.size():"+list.size());
		for(Object obj : list){
			System.out.println(obj);
			orderNum = (Long) obj;
		}
		return orderNum;
	}
	
	/**
	 * 
	 * 返回合同订单下的总采购数
	 * 
	 * @param contractOrderId 销售订单ID
	 * @return 该销售订单采购的总数
	 * @author zhangjh 新增日期：2013-3-22
	 * @since ContractManage
	 */
	public Long countContractOrderTotal(Integer contractOrderId){
		Long orderNum = 0L;
		Session  session = this.getSession();
		StringBuffer hql= new StringBuffer("select sum(ship.orderQuantity) from TbContractOrderRelationship ship where ship.contractOrder.id = ?" );
		Query query = session.createQuery(hql.toString());
		query.setInteger(0, contractOrderId);
		List list = query.list();
		System.out.println("list.size():"+list.size());
		for(Object obj : list){
			System.out.println(obj);
			orderNum = (Long) obj;
		}
		return orderNum;
	}
}
