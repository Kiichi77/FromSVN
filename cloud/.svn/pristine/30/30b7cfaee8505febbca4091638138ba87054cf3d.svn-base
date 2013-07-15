package cn.com.kxcomm.task.dao;

import java.util.List;

import org.hibernate.Query;

import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.task.base.CommonDAO;

public class AlertDAO extends CommonDAO<AlertEntity> {
	
	/**
	 * 
	 * 警报的去重查询服务器数据
	 * 
	 * @return
	 * @author zhangjh 新增日期：2012-8-30
	 * @since ipmi_task
	 */
	public List queryAlert(){
		String hql = "select alert,user from AlertEntity alert,TbUser user where alert.userId = user.id ";
		List list =  this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**
	 * 
	 * 查询某报警绑定的服务器列表
	 * 
	 * @param alertId
	 * @return
	 * @author zhangjh 新增日期：2012-8-30
	 * @since ipmi_task
	 */
	public List queryAlertServer(int alertId){
		String hql = "select tbserver from AlertEntity alert,AlertServerEntity alertServer,TbServer tbserver where alert.alertId = alertServer.alert.alertId and alertServer.server.id = tbserver.id  and alert.alertId=?";
		Query queryObject = this.getSession().createQuery(hql);
		queryObject.setInteger(0, alertId);
		return queryObject.list();
	}
}	
