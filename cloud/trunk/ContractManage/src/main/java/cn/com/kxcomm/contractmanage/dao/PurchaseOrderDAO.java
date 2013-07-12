package cn.com.kxcomm.contractmanage.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrder;
@Repository
public class PurchaseOrderDAO extends CommonDAO<TbPurchaseOrder>{
	private static final Logger log = Logger.getLogger(PurchaseOrderDAO.class);
	
	/**
	 * 
	 * 确认订单下的采购订单所有都到货
	 * 
	 * @param contractOrderId
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public boolean confirmAllReceipt(Integer contractOrderId){
		Session session = getSession();
		String hql = "update TbPurchaseOrder  set status = 7, receivertime=? where  contractOrder.id=? and status = 6";
		Query query = session.createQuery(hql);
        query.setDate(0,new Date());
        query.setInteger(1,contractOrderId);
        query.executeUpdate();
		return true;
	}
	
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(String hql,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}
	
}
