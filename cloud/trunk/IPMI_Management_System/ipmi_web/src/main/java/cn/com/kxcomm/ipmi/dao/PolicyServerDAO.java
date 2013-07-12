package cn.com.kxcomm.ipmi.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import cn.com.kxcomm.ipmi.entity.TbPolicySever;

/**
 * 策略数据层处理
 * @author chenliang
 *
 */
@Repository
public class PolicyServerDAO extends CommonDAO<TbPolicySever> {

	private static final Logger log = Logger.getLogger(BlackNameDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findbyHql(String hql, Object ...objects) {
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[j]);
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}

}
