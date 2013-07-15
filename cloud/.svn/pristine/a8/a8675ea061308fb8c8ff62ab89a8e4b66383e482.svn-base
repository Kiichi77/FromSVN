package cn.com.kxcomm.contractmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
@Repository
public class ContractTemplatesDocDAO extends CommonDAO<TbContractTemplatesDoc> {

	private static final Logger log =  Logger.getLogger(ContractTemplatesDocDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-12-12
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(boolean flat,String hql,PageInfo<ContractTemplatesDocVo> pageInfo,Object ...objects){
		Session  sessionUse = this.getSession();
		SQLQuery sq = sessionUse.createSQLQuery(hql);
		int j=0;
		for (int i = 0; i < objects.length; i++) {
			log.debug("objects["+i+"]"+objects[i]);
			if(null!=objects[i] && !"".equals(objects[i])){
				if(objects[i] instanceof Integer){
					sq.setInteger(j, Integer.parseInt(objects[i].toString()));
				}else if(objects[i] instanceof String){
					sq.setString(j, objects[i].toString());
				}
				j++;
			}
		}
		if(flat){
			if ((pageInfo.getCurrentPage() !=0 && pageInfo.getPageSize() !=0)  ) {  
				sq.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPageSize()).setMaxResults(pageInfo.getPageSize());
			}
		}
		List lista = sq.list();
		return lista;
	}
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-12-12
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public HashMap<String, Object> findByPageSql(String hql,PageInfo<ContractTemplatesDocVo> pageInfo,Object ...objects){
		HashMap<String, Object> mapa = new HashMap<String, Object>();
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
					if(objects[i] instanceof Integer){
						pre.setInt(j, Integer.parseInt(""+objects[i]));
					}else if(objects[i] instanceof String){
						pre.setString(j, ""+objects[i]);
					}
					j++;
				}
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				ContractTemplatesDocVo temDocvo = new ContractTemplatesDocVo();
				temDocvo.setTemplateDocTypeId(""+rs.getInt("did")); //合同模板文件和合同模板中间表id，没有则为空
				temDocvo.setId(""+rs.getInt("cid"));  //合同文件的id
				temDocvo.setbVariable(""+rs.getInt("b_variable")); //是否设置变量
				temDocvo.setCreatetime(""+rs.getDate("create_time")); // 创建时间
				temDocvo.setDocname(""+rs.getString("doc_name"));  //文件名
				temDocvo.setPath(""+rs.getString("path")); // 地址
				temDocvo.setTypeName(""+rs.getString("name"));
				mapa.put(temDocvo.getId(), temDocvo);
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
		return mapa;
	}
	
	/**
	 * 
	 * 获取总记录数
	 * @author luj 新增日期：2012-12-12
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
	
}
