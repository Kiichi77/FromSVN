package cn.com.kxcomm.ipmi.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBelong;

/**
 * 
* 功能描述:服务器所属集合表数据业务类
* @author chenl 新增日期：2012-8-10
* @since ipmi_web
 */
@Repository
public class BelongDAO extends CommonDAO<TbBelong> {

	private static final Logger log = Logger.getLogger(BelongDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByHql(String hql,Page<TbBelong> pageInfo,Object ...objects){
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
		if(!BlankUtil.isBlank(pageInfo)){
			if ((pageInfo.getPageNo() !=0 && pageInfo.getPageSize() !=0)  ) {  
				sq.setFirstResult((pageInfo.getPageNo()-1)*pageInfo.getPageSize()).setMaxResults(pageInfo.getPageSize());
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	
	/**
	 * 
	 * 获取总记录数
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public int findCount(String countHql ,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq = sessionUse.createSQLQuery(countHql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects1["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				sq.setParameter(j, objects[j]);
				j++;
			}
		}
		int count = (Integer) sq.addScalar("count", Hibernate.INTEGER).uniqueResult();
		return count;
	}
	
	
	public List getServerByBelong(long belongid){
		return this.find("from TbServerBelong where id.belongId = ? ", belongid);
	}
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public int executeSql(String hql,Object ...objects){
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
		int result = sq.executeUpdate();
		return result;
	}
}
