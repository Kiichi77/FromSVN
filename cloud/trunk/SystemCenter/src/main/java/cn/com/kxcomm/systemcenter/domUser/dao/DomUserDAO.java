package cn.com.kxcomm.systemcenter.domUser.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.com.kxcomm.common.base.CommonDAO;
import cn.com.kxcomm.common.db.IbatisClientManager;
import cn.com.kxcomm.systemcenter.domUser.entity.DomUserEntity;

/**
 * 
* 功能描述:域用户业务处理层
* @author chenliang 新增日期：2013-6-24
* @since system_center_sdk
 */
public class DomUserDAO extends CommonDAO {

	private static final Logger LOGGER = Logger.getLogger(DomUserDAO.class);
	
	private static DomUserDAO domUserDAO;
	
	/**
	 * 
	* 方法用途和描述: 单例模式
	* @return
	* @author chenliang 新增日期：2013-6-24
	* @since system_center_sdk
	 */
	public static DomUserDAO getInstance(){
		if(null==domUserDAO){
			domUserDAO = new DomUserDAO();
		}
		return domUserDAO;
	}
	
	/**
	 * 
	* 方法用途和描述: 查询域用户
	* @return
	* @author chenliang 新增日期：2013-6-24
	* @since system_center_sdk
	 */
	public List<DomUserEntity> listEntity(){
		try {
			SqlMapClient sqlmap = IbatisClientManager.getSqlMapClient(); 
			List<DomUserEntity> domUserList = sqlmap.queryForList(DomUserEntity.class.getName()+".queryAll");
			return domUserList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("listEntity error",e);
		}
		return null;
	}
}
