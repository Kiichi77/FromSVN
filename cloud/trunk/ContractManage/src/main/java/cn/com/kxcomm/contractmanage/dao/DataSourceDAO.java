package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.vo.DataSourceVo;

/**
 * 
 * 数据源
 * 
 * @author zhangjh 新增日期：2012-12-4
 * @since ContractManage
 */
@Repository
public class DataSourceDAO extends CommonDAO<TbDataSource> {

	private static final Logger log =  Logger.getLogger(DataSourceDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(String hql,PageInfo<DataSourceVo> pageInfo ,Object ...objects){
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
	 * 获取所有单元数据的集合
	 * @author lizl 新增日期：2012-1-22
	 * @param hql  执行的   
	 * @return
	 */
	public List queryAllDataSource(String hql,Object ...objects) {
		Session sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof Integer){
				sq.setInteger(0, Integer.parseInt(objects[i].toString()));
			}
		}
		List lista = sq.list();
		return lista;
	}
}
