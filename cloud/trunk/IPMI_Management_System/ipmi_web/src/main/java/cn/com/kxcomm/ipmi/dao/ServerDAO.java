package cn.com.kxcomm.ipmi.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;

/**
 * 
* 功能描述:服务器数据交互管理类
* @author chenl 新增日期：2012-8-10
* @since ipmi_web
 */
@Repository
public class ServerDAO extends CommonDAO<TbServer> {

	public static final Logger log = Logger.getLogger(ServerDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByHql(String hql,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i]) && 0!=Integer.parseInt(objects[i].toString())){
				sq.setParameter(j, objects[j]);
				j++;
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	/**
	 * 插入服务器与服务器所属集合列表的关系
	 */
	public void insertServerBelong(TbServerBelong entity){
		try {  
			getHibernateTemplate().save(entity);  
			if (log.isDebugEnabled()) {  
				log.debug("保存实体类成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			log.error("保存实体异常," + entity.getClass().getName(), e);  
			throw e;  
		}  
	}
	
	/**
	 * 修改服务器与服务器所属集合列表的关系
	 */
	public void updateServerBelong(TbServerBelong entity){
		try {  
			getHibernateTemplate().saveOrUpdate(entity);  
			if (log.isDebugEnabled()) {  
				log.debug("保存实体类成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			log.error("保存实体异常," + entity.getClass().getName(), e);  
			throw e;  
		}  
	}

	/**
	 * 删除服务器与服务器所属集合列表的关系
	 * @param roleId
	 */
	public void deleteServerBelong(TbServerBelong entity) {
		try {  
			getHibernateTemplate().delete(entity);  
			if (log.isDebugEnabled()) {  
				log.debug("删除实体类成功," + entity.getClass().getName());  
			}  
		} catch (RuntimeException e) {  
			log.error("删除实体异常", e);  
			throw e;  
		}  
	}
	
}
