package cn.com.kxcomm.contractmanage.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbRole;

@Repository
public class RoleDAO  extends CommonDAO<TbRole> {
	public static Logger log = Logger.getLogger(RoleDAO.class);
	
	/**
	 * 插入角色和权限关系
	 */
	public void insertRoleRight(TbRightRole entity){
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
	 * 插入角色和权限关系
	 */
	public void updateRoleRight(TbRightRole entity){
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
	 * 删除角色和权限的关系
	 * @param roleId
	 */
	public void deleteRoleRight(TbRightRole entity) {
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
