package cn.com.kxcomm.ipmi.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.dao.AlertServiceDAO;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.entity.AlertServerEntity;
import cn.com.kxcomm.ipmi.service.AlertServerService;

@Service("alertServerService")
public class AlertServerServiceImpl extends
		CommonServiceImpl<AlertServerEntity> implements AlertServerService {

	private static final Logger log = Logger.getLogger(AlertServerServiceImpl.class);
	
	@Autowired(required=true)
	private AlertServiceDAO alertServiceDAO;
	
	@Override
	public CommonDAO<AlertServerEntity> getBindDao() {
		return alertServiceDAO;
	}

	/**
	 * 根据告警信息id批量删除
	 */
	@Override
	public boolean deleteByAlertID(Serializable[] ids) {
		try {
			String hql = "delete AlertServerEntity where alert.id = ?";
			int result = 0;
			for (Serializable id : ids) {
				result += alertServiceDAO.executeByHQL(hql, id);
			}
			log.info("result:"+result);
			if(result>0){
				log.info("batch delete AlertServerEntity successful.");
			}
			return true;
		} catch (Exception e) {
			log.error("deleteByAlertID error."+e);
			return false;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据告警信息id查询策略所关联的服务器ID
	* @param fieldName,fieldValue
	* @return
	* @author lizl 新增日期：2012-9-20
	* @since ipmi_web
	 */
	@Override
	public List<AlertServerEntity> findListByField(String fieldName, int fieldValue) {
		List<AlertServerEntity> alertServerEntities = new ArrayList<AlertServerEntity>();
		try {
			String hql = "";
			alertServerEntities = alertServiceDAO.findByProperty(AlertServerEntity.class, fieldName, fieldValue);
			log.info("result:"+alertServerEntities.size());
		} catch (Exception e) {
			log.error("deleteByAlertID error."+e);
		}
		return alertServerEntities;
	}
}
