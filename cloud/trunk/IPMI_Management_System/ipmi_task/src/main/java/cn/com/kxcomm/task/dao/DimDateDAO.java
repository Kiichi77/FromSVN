package cn.com.kxcomm.task.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.task.base.CommonDAO;

/**
 * 
* 功能描述:时间数据层处理类
* @author chenl 新增日期：2012-9-20
* @since ipmi_task
 */
public class DimDateDAO extends CommonDAO<DimDateEntity> {

	private static final Logger log = Logger.getLogger(DimDateDAO.class);
	
	/**
	 * 
	* 方法用途和描述: 根据当天时间查询时间维度表
	* @author chenl 新增日期：2012-9-20
	* @since ipmi_task
	 */
	public List selectDimDateByFullDate(DimDateEntity entity){
		String hql = "from DimDateEntity where date_format(fullDate,'%Y-%m-%d')=date_format(?,'%Y-%m-%d')";
		List<DimDateEntity> list = (List<DimDateEntity>) this.find(hql, entity.getFullDate());
		return list;
	}
	
}
