package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbInvoice;
@Repository
public class InvoiceDAO extends CommonDAO<TbInvoice>{
	private static final Logger log = Logger.getLogger(InvoiceDAO.class);

	/**
	 * 
	 * 获取总记录数
	 * @author luj 新增日期：2013-3-6
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param sql  执行的   
	 * @return
	 */
	public int findCount(String countSql ,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq = sessionUse.createSQLQuery(countSql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects1["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i]) && -1!=Integer.parseInt(objects[i].toString())){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		int count = (Integer) sq.addScalar("count", Hibernate.INTEGER).uniqueResult();
		return count;
	}
	
	
	/**
	 * 
	 * 查询
	 * @author luj 新增日期：2013-3-6
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param sql  执行的   
	 * @return
	 */
	public List<TbInvoice> findByHql(String hql ,Object ...objects){
		Session  sessionUse = this.getSession();
		Query sq = this.createQuery(sessionUse, hql, objects);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects1["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		List<TbInvoice> list = sq.list();
		return list;
	}
}
