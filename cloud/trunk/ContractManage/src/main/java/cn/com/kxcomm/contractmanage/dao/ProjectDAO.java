package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;

@Repository
public class ProjectDAO extends CommonDAO<TbProject>{
	private static final Logger log = Logger.getLogger(ProjectDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author luj 新增日期：2013-2-25
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param sql  执行的   
	 * @return
	 */
	public List findByPage(String sql,PageInfo<ProjectVo> pageInfo ,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(sql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i]) && -1!=Integer.parseInt(objects[i].toString())){
				sq.setParameter(j, objects[i]);
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
	 * 获取总记录数
	 * @author luj 新增日期：2013-2-25
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
				sq.setParameter(j, objects[j]);
				j++;
			}
		}
		int count = (Integer) sq.addScalar("count", Hibernate.INTEGER).uniqueResult();
		return count;
	}
	
	/**
	 * 
	 * 获取所有项目的集合
	 * @author luj 新增日期：2013-2-26
	 * @param hql  执行的   
	 * @return
	 */
	public List queryAllProject(String hql,Object ...objects) {
		Session sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		List lista = sq.list();
		return lista;
	}
}
