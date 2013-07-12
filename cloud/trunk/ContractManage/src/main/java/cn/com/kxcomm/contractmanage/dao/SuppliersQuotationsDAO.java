package cn.com.kxcomm.contractmanage.dao;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbSuppliersQuotations;

@Repository
public class SuppliersQuotationsDAO extends CommonDAO<TbSuppliersQuotations> {

	private static final Logger LOGGER = Logger.getLogger(SuppliersQuotationsDAO.class);
	
	/**
	 * 
	 * 获取总记录数
	 * @author luj 新增日期：2012-12-13
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public int findCount(String countHql ,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq = sessionUse.createSQLQuery(countHql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			LOGGER.debug("objects1["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[i]);
				j++;
			}
		}
		int count = (Integer) sq.addScalar("count", Hibernate.INTEGER).uniqueResult();
		return count;
	}
}
