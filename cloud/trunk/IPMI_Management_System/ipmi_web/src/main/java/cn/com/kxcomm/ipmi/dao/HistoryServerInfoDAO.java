package cn.com.kxcomm.ipmi.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;

@Repository
public class HistoryServerInfoDAO extends CommonDAO<FactServerInfoEntity> {
	
	private static Logger log = Logger.getLogger(HistoryServerInfoDAO.class);

	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByHql(String hql,Object... objects){
		Session  sessionUse = this.getSession();
		Query queryObject = sessionUse.createQuery(hql);
	//	SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.info("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				queryObject.setParameter(j, objects[i]);
				j++;
			}
		}
		List lista = queryObject.list();
		return lista;
	}
	
	
	
	
}
