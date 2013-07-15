package cn.com.kxcomm.contractmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
@Repository
public class DataRecordDAO extends CommonDAO<TbDataRecord> {

	private static final Logger log = Logger.getLogger(DataRecordDAO.class);
	
	/**
	 * 
	 * 执行hql
	 * @author chenl 新增日期：2012-1-22
	 * @param criteria 带查询条件的DetachedCriteria
	 * @param hql  执行的   
	 * @return
	 */
	public List findByPage(String hql,Object ...objects){
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
		List lista = sq.list();
		return lista;
	}
	
	/**
	 * 
	* 方法用途和描述: 批量添加
	* @param volist
	* @param sql
	* @author chenliang 新增日期：2012-12-12
	 * @throws SQLException 
	* @since ContractManage
	 */
	public int batchSave(List<TbDataRecord> volist,String sql) throws SQLException{
		
		int[] result;
		Session session = this.getSession();
		Connection conn = session.connection();
		conn.setAutoCommit(false);
		PreparedStatement pre = conn.prepareStatement(sql);
		for (int i = 0; i < volist.size(); i++) {
			TbDataRecord vo = volist.get(i);
			pre.setInt(1, vo.getProductType().getId());
			pre.setInt(2, vo.getDataSource().getId());
			pre.setString(3, vo.getProductOrderNo());
			pre.setString(4, vo.getProductDesc());
			pre.setDouble(5, vo.getQuantity());
			pre.setDouble(6, vo.getUnitPrice());
			pre.setDouble(7, vo.getDiscountRate());
			pre.setDouble(8, vo.getOtherRates());
			pre.setDouble(9, vo.getInstallServiceCharge());
			pre.setDouble(10, vo.getFirstYear());
			pre.setDouble(11, vo.getSecondYear());
			pre.setDouble(12, vo.getThirdYear());
			pre.setString(13, vo.getRemark());
			pre.addBatch();
		}
		result = pre.executeBatch();
		conn.commit();
		log.info("batchSave result:"+result);
		if(result!=null){
			return result.length;
		}
		return 0;
	}
	
	/** 
	 * 按HQL分页查询. 
	 *  
	 * @param page 
	 *            页面对象 
	 * @param hql 
	 *            HQL语句 
	 * @param values 
	 *            可变参数列表 
	 * @return 分页数据 
	 */  
	public List<TbDataRecord> findPageInfo(final PageInfo<DataRecordVo> page, final String hql, 
			final Object... values) {  
		try {  
			final String counthql = "select count(*) "+hql;
			if (log.isDebugEnabled()) {  
				log.debug("开始查找指定HQL分页数据," + hql);  
			}  
			return (List<TbDataRecord>) getHibernateTemplate().execute(  
					new HibernateCallback() {  
						public Object doInHibernate(Session s)  
						throws HibernateException, SQLException {  
							Query query = createQuery(s, hql, values);  
							if ((page.getCurrentPage() !=0 && page.getPageSize() !=0)  ) {  
								query.setFirstResult((page.getCurrentPage()-1)*page.getPageSize()).setMaxResults(page.getPageSize());
							}  
							return query.list();
						}  
					});  
		} catch (RuntimeException e) {  
			log.error("分页查询异常，HQL：" + hql, e);  
			throw e;  
		}  
	}  
}
