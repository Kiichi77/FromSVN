package cn.com.kxcomm.ipmi.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.googlecode.jsonplugin.annotations.JSON;
import com.sun.tools.corba.se.idl.Factories;
import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.DateFormatUtil;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.HistoryServerInfoDAO;
import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.HistoryServerInfoService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;
import cn.com.kxcomm.ipmi.vo.HistoryServerInfoEntity;

/**
 * 历史服务器信息业务逻辑类
 * @author chenliang
 *
 */
@Service("historyServerInfoService")
public class HistoryServerInfoServiceImpl extends CommonServiceImpl<FactServerInfoEntity>
implements HistoryServerInfoService {

	private static final Logger log = Logger.getLogger(HistoryServerInfoServiceImpl.class);

	@Autowired(required=true)
	public HistoryServerInfoDAO historyServerInfoDAO;
	private List list=null;
	@Override
	public CommonDAO<FactServerInfoEntity> getBindDao() {
		return historyServerInfoDAO;
	}

	/**
	 * 图形化报表查询
	 */
	


	@Override
	public List<Hashtable> getGraphicsData(FactServerInfoEntity factserverEntity,short dateTimeType) {	  

		StringBuffer hql = new StringBuffer();
		hql.append( "select max(f.max),min(f.min),ceil(avg(f.average)),f.server.serverIpIn from FactServerInfoEntity f");
		if(dateTimeType ==1){ // 日
			hql.append(" where f.collectType=? and date_format(f.dimDate.fullDate,'%Y-%m-%d')=date_format(?,'%Y-%m-%d')");
			//factserverEntity=historyServerInfoDAO.findbyhql(hql.toString(),  factserverEntity.getCollectType(),factserverEntity.getDimDate().getFullDate());
			list=historyServerInfoDAO.find(hql.toString(), factserverEntity.getCollectType(),factserverEntity.getDimDate().getFullDate());
		}else if(2 == dateTimeType){ //周
			hql.append(" where f.collectType=? and f.dimDate.week=? and f.dimDate.year=?");
		    list=historyServerInfoDAO.find(hql.toString(), factserverEntity.getCollectType(),factserverEntity.getDimDate().getWeek(),factserverEntity.getDimDate().getYear());
		}else if(3 ==  dateTimeType){ //月
			hql.append(" where f.collectType=? and f.dimDate.month=? and f.dimDate.year=?");
		    list=historyServerInfoDAO.find(hql.toString(), factserverEntity.getCollectType(),factserverEntity.getDimDate().getMonth(),factserverEntity.getDimDate().getYear());
		}else if (4 ==dateTimeType) { //年
			hql.append(" where f.collectType=? and f.dimDate.year=?");
		     list=historyServerInfoDAO.find(hql.toString(), factserverEntity.getCollectType(),factserverEntity.getDimDate().getYear());
		    		 
		}
		log.debug(hql.toString());			
		log.info("cpuPowerStatictics list size:"+list.size());
		Object[] obj = new Object[4];
		List historyData=new ArrayList();
		for(int i=0;i<list.size();i++){
			obj = (Object[]) list.get(i);
			Hashtable table=new Hashtable();
			table.put("serverIpIn",BlankUtil.isBlank(obj[3])?"0":obj[3]);
			table.put("最大值",BlankUtil.isBlank(obj[0])?"0":obj[0]);
			table.put("最小值", BlankUtil.isBlank(obj[1])?"0":obj[1]);
			table.put("平均值", BlankUtil.isBlank(obj[2])?"0":obj[2]);
			table.put("isHaveData", BlankUtil.isBlank(obj[0])&&BlankUtil.isBlank(obj[1])&&BlankUtil.isBlank(obj[2])?"0":"1");
			historyData.add(table);
		}
		return historyData;


	}


}
