package cn.com.kxcomm.ipmi.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.com.kxcomm.ipmi.entity.AlertServerEntity;

/**
 * 
* 功能描述:告警与服务器关系数据交互层
* @author chenl 新增日期：2012-9-20
* @since ipmi_web
 */
@Repository
public class AlertServiceDAO extends CommonDAO<AlertServerEntity> {

	private static final Logger LOGGER = Logger.getLogger(AlertServiceDAO.class);
	
	
	
}
