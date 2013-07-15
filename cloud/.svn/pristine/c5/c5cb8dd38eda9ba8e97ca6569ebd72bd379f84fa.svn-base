package cn.com.kxcomm.common.db;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisClientManager {
	private static SqlMapClient sqlMap;
	private static final Logger log = Logger.getLogger(IbatisClientManager.class);
	
	/**
	 * 创建SqlMapClient对象，永驻内存，为dao层服务
	 */
	private synchronized static void init() {
		try {
			String resource = "sqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader (resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException ("Error initializing MyAppSqlConfig class. Cause: "+e);
		}
	}
	
	/**
	* 
	* 方法用途和描述: 打印当前连接池信息
	* @author chenliang 新增日期：2013-11-16
	* @author 你的姓名 修改日期：2013-11-16
	* @since system_center_sdk	
	 */
	public static void printPoolStats(String jobName) {
		try{
			BasicDataSource dataSource  = (BasicDataSource)sqlMap.getDataSource();
			log.debug("jobName:"+jobName+"\tNumActive:"+dataSource.getNumActive() +",NumIdle:"+dataSource.getNumIdle() +",InitialSize:"+dataSource.getInitialSize() +",MaxActive:"+dataSource.getMaxActive() +",MaxIdle:"+dataSource.getMaxIdle() +",MaxOpenPreparedStatements:"+dataSource.getMaxOpenPreparedStatements() +",MaxWait"+dataSource.getMaxWait() +",MinEvictableIdleTimeMillis:"+dataSource.getMinEvictableIdleTimeMillis() +",MinIdle:"+dataSource.getMinIdle() +",RemoveAbandoned:"+dataSource.getRemoveAbandoned() +",RemoveAbandonedTimeout:"+dataSource.getRemoveAbandonedTimeout());
		}catch(Exception e){
			
		}
	}  
	
	/**
	 * 
	* 方法用途和描述: 获取SqlMapClient对象
	* @return
	* @author chenxinwei 新增日期：2013-6-9
	* @author 你的姓名 修改日期：2013-6-9
	* @since system_center_sdk
	 */
	public static SqlMapClient getSqlMapClient(){
		if (IbatisClientManager.sqlMap == null){
			init();
		}
		return IbatisClientManager.sqlMap;
	}
	
	/**
	 * 
	* 方法用途和描述: 开启线程事务，并返回事务创建的连接
	* 只要调用了该方法，在调用endTransaction之前的方法内，同一个线程内通过getSqlMapClient().getCurrentConnection()获取到的连接都是同一个连接，保证事务一致性
	* @return
	* @throws SQLException
	* @author chenliang 新增日期：2013-7-15
	* @author 你的姓名 修改日期：2013-7-15
	* @since system_center_sdk
	 */
	public static Connection startTransaction() throws SQLException{
		IbatisClientManager.getSqlMapClient().startTransaction();
		Connection conn = IbatisClientManager.getSqlMapClient().getCurrentConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	/**
	 * 
	* 方法用途和描述:提交事务
	* @param conn 如果jdbc操作，则需要传入connection对象
	* @throws SQLException
	* @author chenliang 新增日期：2013-7-15
	* @author 你的姓名 修改日期：2013-7-15
	* @since system_center_sdk
	 */
	public static void commitTransaction() throws SQLException{
		getSqlMapClient().commitTransaction();
		getCurrentConnection().commit();
	}
	
	/**
	 * 
	* 方法用途和描述:回滚事务,需要放在finally里
	* @param conn
	* @throws SQLException
	* @author chenliang 新增日期：2013-7-15
	* @author 你的姓名 修改日期：2013-7-15
	* @since system_center_sdk
	 */
	public static void endTransaction() throws SQLException{
		getCurrentConnection().rollback();
		IbatisClientManager.getSqlMapClient().endTransaction();
	}
	
	/**
	 * 
	* 方法用途和描述: 提交并结束事务
	* @throws SQLException
	* @author chenliang 新增日期：2013-8-5
	* @author 你的姓名 修改日期：2013-8-5
	* @since system_center_sdk
	 */
	public static void commitAndEndTransaction() throws SQLException{
		commitTransaction();
		endTransaction();
	}
	
	/**
	 * 
	* 方法用途和描述:获取被startTransaction()开启的连接
	* @return
	* @throws SQLException
	* @author chenliang 新增日期：2013-7-17
	* @author 你的姓名 修改日期：2013-7-17
	* @since system_center_sdk
	 */
	public static Connection getCurrentConnection() throws SQLException{
		return IbatisClientManager.getSqlMapClient().getCurrentConnection();
	}
	
	
}
