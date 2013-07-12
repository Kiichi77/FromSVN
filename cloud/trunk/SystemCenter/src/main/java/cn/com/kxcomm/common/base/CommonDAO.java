package cn.com.kxcomm.common.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import cn.com.kxcomm.common.db.IbatisClientManager;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

/**
 * 
 * 功能描述:dao类的父类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author chenliang 新增日期：2013-6-8
 * @author 你的姓名 修改日期：2013-6-8
 * @since system_center_sdk
 */
public class CommonDAO {

	private static final Logger logger = Logger
			.getLogger(CommonDAO.class);
	
	public static CommonDAO commonDAO;
	
	private static CommonDAO getInstance(){
		if(null == commonDAO){
			commonDAO = new CommonDAO(); 
		}
		return commonDAO;
	}
	
	/**
	 * 
	 * 方法用途和描述: 插入一个对象
	 * 
	 * @param entity
	 * @return
	 * @author chenliang 新增日期：2010-6-9
	 * @author 你的姓名 修改日期：2010-6-9
	 * @throws SQLException
	 * @since system_center_sdk
	 */
	public synchronized void save(Object entity) throws SQLException {
		IbatisClientManager.getSqlMapClient().insert(
				entity.getClass().getName() + ".insert", entity);
	}

	/**
	 * 
	 * 方法用途和描述: 修改一个对象
	 * 
	 * @param entity
	 * @return
	 * @author chenliang 新增日期：2010-6-9
	 * @author 你的姓名 修改日期：2010-6-9
	 * @throws SQLException
	 * @since system_center_sdk
	 */
	public synchronized void update(Object entity) throws SQLException {
		IbatisClientManager.getSqlMapClient().update(
				entity.getClass().getName() + ".update", entity);
	}

	/**
	 * 
	 * 方法用途和描述: 根据ID获取实体对象
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 * @author chenliang 新增日期：2010-6-1
	 * @author 你的姓名 修改日期：2010-6-1
	 * @throws SQLException
	 * @since system_center_sdk
	 */
	@SuppressWarnings("unchecked")
	public Object getById(Class entityClass, Serializable id)
			throws SQLException {
		return IbatisClientManager.getSqlMapClient().queryForObject(
				entityClass.getName() + ".selectByID", id);
	}

	/**
	 * 
	 * 方法用途和描述: 修改一个对象
	 * 
	 * @param entity
	 * @return
	 * @author chenliang 新增日期：2010-6-9
	 * @author 你的姓名 修改日期：2010-6-9
	 * @throws SQLException
	 * @since system_center_sdk
	 */
	public synchronized void delete(Object entity) throws SQLException {
		IbatisClientManager.getSqlMapClient().delete(
				entity.getClass().getName() + ".delete", entity);
	}
	
	/**
	 * 关闭连结， 释放资源
	 * 
	 * @param pstmt
	 * @param rs
	 * @param conn
	 * @author chenliang 新增日期：2013-11-1
	 * @author 你的姓名 修改日期：2013-11-1
	 * @since gdunicom_crbt_bi
	 */
	public static void releaseResoure(PreparedStatement pstmt,
			Statement statement, ResultSet rs, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {

			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
			}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	protected SqlMapSession getSqlSession() throws SQLException {
		SqlMapClient sqlmap = IbatisClientManager.getSqlMapClient();
		SqlMapSession session = sqlmap.openSession();
		session.startTransaction();
		return session;
	}

	
	/**
	 * 数据操作
	 * 
	 * @author chenliang 新增日期：2013-9-21
	 * @author 你的姓名 修改日期：2013-9-21
	 * @param isBatch
	 *            是否为批量执行
	 * @param sql
	 *            需用执行的SQL集合及每条SQL执行的参数对象
	 * @since system_center_sdk
	 */
	public boolean execute(boolean isBatch) {
		SqlMapClient sqlmap = IbatisClientManager.getSqlMapClient();
		SqlMapSession session = sqlmap.openSession();
		try {
			session.startTransaction();
			
			session.commitTransaction();
		} catch (SQLException e) {
			logger.error("Fail to export data,:" + e.getMessage(), e);
			return false;
		} finally {
			try {
				session.endTransaction();
				session.close();
			} catch (SQLException e) {
				logger.error("Job run is fail,connection close exception", e);
			}
		}
		return true;
	}
	

	protected void closeSession(SqlMapSession session) throws SQLException {
		session.endTransaction();
		session.close();
	}
}
