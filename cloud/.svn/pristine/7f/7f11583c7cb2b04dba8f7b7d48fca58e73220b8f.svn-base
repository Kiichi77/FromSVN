package cn.com.kxcomm.ipmi.service;

import java.util.Hashtable;
import java.util.List;

import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;
import cn.com.kxcomm.ipmi.vo.BelongEntity;
import cn.com.kxcomm.ipmi.vo.HistoryServerInfoEntity;

/**
 * 历史服务器业务逻辑处理接口
 * @author chenliang
 *
 */
public interface HistoryServerInfoService extends CommonService<FactServerInfoEntity> 
{

	/**
	 * 
	* 方法用途和描述: 图形化报表查询
	* @param entity   传入时间和采集类型
	*       belongEntity 传入服务器所属集合id和等级
	* @return
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
//	public List<HistoryServerInfoEntity> getGraphicsData(HistoryServerInfoEntity entity,BelongEntity belongEntity) ;
	public List<Hashtable> getGraphicsData(FactServerInfoEntity factserverInfoEntity,short dateTimeType);
  
}
