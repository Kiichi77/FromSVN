package cn.com.kxcomm.task.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.task.dao.DimDateDAO;
import cn.com.kxcomm.task.dao.PowerStatisticsDAO;

@Service("powerStatisticsService")
public class PowerStatisticsService {
	
	private static final Logger log = Logger.getLogger(PowerStatisticsService.class);

	@Autowired(required=true)
	private PowerStatisticsDAO powerStatisticsDAO;
	@Autowired(required=true)
	private DimDateDAO dimDateDAO;

	/**
	 * 
	 * 方法用途和描述:功耗统计
	 * 
	 * @author chenl 新增日期：2012-9-20
	 * @since ipmi_task
	 */
	public void powerStatitics(){
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date beforeDate = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String printDateStr = sdf.format(beforeDate);
			log.debug("printDateStr:"+printDateStr);
			powerStatisticsDAO.deleteYesterDayData(printDateStr);
			//新增当天时间维度
			addNowDate(beforeDate);
			//统计cpu功耗
			log.debug("Start CPU Power statistics.");
			dayStatistics(printDateStr,(short)2);
			log.debug("Finish CPU Power statistics.");
			//统计内存功耗
			log.debug("Start Memory Power statistics.");
			dayStatistics(printDateStr,(short)3);
			log.debug("Finish Memory power statistics.");
			//统计系统功耗
			log.debug("Start System Power statistics.");
			dayStatistics(printDateStr,(short)1);
			log.debug("Finish System Power statistics.");
			//统计出风口温度
			log.debug("Start exhaustTemp statistics.");
			dayStatistics(printDateStr,(short)4);
			log.debug("Finish exhaustTemp statistics.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" powerStaitics error:"+e);
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 新增当天时间维度
	* @param dimDate
	* @author chenl 新增日期：2012-9-20
	* @since ipmi_task
	 */
	private void addNowDate(Date date){
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int week = cal.get(Calendar.WEEK_OF_YEAR);
			int quarter = getQuarter(month);
			log.info(year + "年" + month + "月" + day + "日" + week + "周" + quarter+ "季度");
			DimDateEntity entity = new DimDateEntity();
			entity.setFullDate(cal.getTime());
			entity.setDay((short) day);
			entity.setMonth((short) month);
			entity.setQuarter((short) quarter);
			entity.setWeek((short) week);
			entity.setYear((short) year);
			List hasEntity = dimDateDAO.selectDimDateByFullDate(entity);
			log.info("hasEntity.size:"+hasEntity.size());
			if (hasEntity == null || hasEntity.size()<=0) {
				dimDateDAO.save(entity);
			} else {
				log.debug("日期已经存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 根据月份返回季度
	 * 
	 * @param month
	 * @return
	 * @author zhangjh 新增日期：2010-7-9
	 * @author 你的姓名 修改日期：2010-7-9
	 * @since zte_crbt_bi
	 */
	private int getQuarter(int month) {
		int quarter = 0;
		if (month >= 1 && month <= 3)
			quarter = 1;
		if (month >= 4 && month <= 6)
			quarter = 2;
		if (month >= 7 && month <= 9)
			quarter = 3;
		if (month >= 10 && month <= 12)
			quarter = 4;
		return quarter;
	}
	
	/**
	 * 
	 * 方法用途和描述: CPU功耗统计
	 * @param collectType 采集类型(1.系统功耗 ,2.CPU功耗,3.内存功耗,4.出风口温度)
	 * 
	 * @author chenl 新增日期：2012-9-21
	 * @throws Exception
	 * @since ipmi_task
	 */
	private void  dayStatistics(String beforeDate,Short collectType) throws Exception{
		String cc = "";
		switch (collectType) {
		case 1:
			cc = "th.systemPower";
			break;
		case 2:
			cc = "th.cpuPower";
			break;
		case 3:
			cc = "th.memoryPower";
			break;
		case 4:
			cc = "th.exhaustTemp";
			break;
		default:
			return;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" select max("+cc+") as maxNum,min("+cc+") as minNum,ceil(avg("+cc+")) as avgNum,tdd.dateId as dateId,ts.id as serverId ");
		sb.append(" from TbHistoryServerInfo th,TbServer ts,DimDateEntity tdd ");
		sb.append(" where th.tbServer.id = ts.id ");
		sb.append(" and date_format(th.createTime,'%Y-%m-%d') = date_format(tdd.fullDate,'%Y-%m-%d') ");
		sb.append(" and date_format(tdd.fullDate,'%Y-%m-%d')=? ");
		sb.append(" and ts.id is not null ");
		sb.append(" group by tdd.dateId,ts.id ");
		List<Object[]> list = powerStatisticsDAO.findByHql(sb.toString(), beforeDate);
		log.info("cpuPowerStatictics list size:"+list.size());
		for(Object[] obj:list){
			FactServerInfoEntity factServerInfoEntity = new FactServerInfoEntity();
			factServerInfoEntity.setCollectType((short) collectType);
			short max = 0;
			if(obj[0]!=null){
				max = (Short) obj[0];
			}
			factServerInfoEntity.setMax(max);
			short min = 0;
			if(obj[1]!=null){
				min = (Short) obj[1];
			}
			factServerInfoEntity.setMin(min);
			short average = 0;
			if(obj[2]!=null){
				average = Short.valueOf(obj[2].toString()) ;
			}
			factServerInfoEntity.setAverage(average);
			DimDateEntity dimDateEntity = new DimDateEntity();
			dimDateEntity.setDateId((Long)obj[3]);
			factServerInfoEntity.setDimDate(dimDateEntity);
			TbServer server = new TbServer();
			server.setId((Long) obj[4]);
			factServerInfoEntity.setServer(server);
			log.info(" obj[3]:"+factServerInfoEntity.getAverage()+",obj[2]:"+obj[2]);
			powerStatisticsDAO.save(factServerInfoEntity);
		}
		log.debug("CPU Power statistics successful.");
	}
	
	
}
