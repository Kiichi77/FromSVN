package cn.com.kxcomm.contractmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbQuotations;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;
@Repository
public class QuotationsDAO extends CommonDAO<TbQuotations> {
private static final Logger log =  Logger.getLogger(QuotationsDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author luj 新增日期：2012-12-13
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(String hql,PageInfo<QuotationsVo> pageInfo ,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(hql);
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
	 * 获取所有报价单的集合
	 * @author luj 新增日期：2012-12-14
	 * @param sql  执行的   
	 * @return
	 */
	public List queryAllQuotations(String sql,Object ...objects) {
		Session sessionUse = this.getSession();
		SQLQuery sq=sessionUse.createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] instanceof Integer){
				sq.setInteger(0, Integer.parseInt(objects[i].toString()));
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	/**
	 * 
	 * 根据合同id查询该合同选中的报价表，并且没有被合同选中的报价表
	 * @author luj 新增日期：2012-12-14
	 * @param hql  执行的   
	 * @return
	 */
	public List<QuotationsVo> query(String hql,Object ...objects) {
		List<QuotationsVo> volist = new ArrayList<QuotationsVo>();
		Session  sessionUse = this.getSession();
		Connection conn =null; 
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			conn = sessionUse.connection();
			pre = conn.prepareStatement(hql);
			int j=1;
			for (int i = 0; i < objects.length; i++) {
				log.debug("objects["+i+"]"+objects[i]);
				if(null!=objects[i] && !"".equals(objects[i])){
					pre.setInt(j, Integer.parseInt(""+objects[i]));
					j++;
				}
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				QuotationsVo vo = new QuotationsVo();
				vo.setCreatetime(""+rs.getDate("create_time"));
				vo.setCreatorName(""+rs.getString("user_name"));
				vo.setDownUrl(""+rs.getString("down_url"));
				vo.setId(""+rs.getInt("aid"));
				vo.setProjectName(""+rs.getString("project_name"));
				vo.setStatus(""+rs.getInt("status"));
				vo.setTitle(""+rs.getString("title"));
				vo.setContractQuotationId(""+rs.getInt("bid"));
				volist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(null!=rs){
					rs.close();
				}
				if(null!=pre){
					pre.close();
				}
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return volist;
	}
	
}
