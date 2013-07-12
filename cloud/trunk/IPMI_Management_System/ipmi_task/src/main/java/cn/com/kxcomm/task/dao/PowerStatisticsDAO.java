package cn.com.kxcomm.task.dao;

import java.util.List;

import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.task.base.CommonDAO;

public class PowerStatisticsDAO extends CommonDAO<FactServerInfoEntity> {
	
	public List findByHql(String hql,Object... objects) throws Exception{
		return this.createQuery(getSession(), hql, objects).list();
	}
	
	/**
	 * 
	 * 清空某天的统计数据,以免重复统计
	 * ,DimDateEntity tdd where fact.dimDate.dateId = tdd.dateId and date_format(tdd.fullDate,'%Y-%m-%d') = ?
	 * @param date
	 * @return
	 * @author zhangjh 新增日期：2012-9-21
	 * @since ipmi_task
	 */
	public void deleteYesterDayData(String date){
		String hql = "delete from FactServerInfoEntity fact where fact.dimDate.dateId in(from DimDateEntity tdd where fact.dimDate.dateId = tdd.dateId and date_format(tdd.fullDate,'%Y-%m-%d')= :yesterday)";
		this.getSession().createQuery(hql).setParameter("yesterday", date).executeUpdate();
	}
}
