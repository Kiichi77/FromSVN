package cn.com.kxcomm.contractmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocVariable;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVariableVo;
@Repository
public class ContractTemplatesDocVariableDAO extends
		CommonDAO<TbContractTemplatesDocVariable> {
private static final Logger log =  Logger.getLogger(ContractTemplatesDocVariableDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author luj 新增日期：2012-12-17
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(String hql,PageInfo<ContractTemplatesDocVariableVo> pageInfo ,Object ...objects){
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
	 * @author luj 新增日期：2012-12-17
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
	* 方法用途和描述: 关联删除
	* @param volist
	* @param sql
	* @author chenliang 新增日期：2012-12-12
	 * @throws SQLException 
	* @since ContractManage
	 */
	public int deleteBatch(String sql,int variableId) throws SQLException{
		int[] result;
		Session session = this.getSession();
		Connection conn = session.connection();
		conn.setAutoCommit(false);
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, variableId);
		result = pre.executeBatch();
		conn.commit();
		log.info("batchSave result:"+result);
		if(result!=null){
			return result.length;
		}
		return 0;
	}
}
