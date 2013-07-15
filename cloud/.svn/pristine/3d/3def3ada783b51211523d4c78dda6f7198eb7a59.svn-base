

import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import com.googlecode.jsonplugin.annotations.JSON;

import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.service.HistoryServerInfoService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;
import cn.com.kxcomm.ipmi.vo.HistoryServerInfoEntity;

/**
 * 
* 功能描述：服务器信息管理测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class HistoryServerInfoTest {
	
	private static Logger log = Logger.getLogger(HistoryServerInfoTest.class);
	private ApplicationContext ctx;
	private HistoryServerInfoService historyServerInfoService;
	
	public HistoryServerInfoTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		historyServerInfoService = (HistoryServerInfoService) ctx.getBean("historyServerInfoService");
	}
	
	@Test
	public void getGraphicsData(){
		FactServerInfoEntity factServerInfoEntity=new FactServerInfoEntity();
		DimDateEntity dateEntity=new DimDateEntity();
		dateEntity.setWeek((short)38);
		dateEntity.setYear((short)2012);
		factServerInfoEntity.setDimDate(dateEntity);
		factServerInfoEntity.setCollectType((short)1);
	    short dataTimeType=2;
		List<Hashtable> factServerInfoEntities1 =
				historyServerInfoService.getGraphicsData(factServerInfoEntity, dataTimeType);
		log.info("serverInfoEntity:"+Json.toJson(factServerInfoEntities1));
	}
	
	
	/**
	 * 服务器保存
	 * level:1 数据中心 2机房 3机架系 4机架 5机箱
	 */
	public void save(){
		Timestamp time = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity=new FactServerInfoEntity();
//		history.setDatetime(time);
//		history.setCollectType(1);//cup功耗
//		history.setCollectString("BRM");
//		history.setCollectNumber("155");
		TbServer server = new TbServer();
		server.setId(1L);
		history.setTbServer(server);
		historyServerInfoService.save(factServerInfoEntity);
		
		
		Timestamp time1 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history1 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity1=new FactServerInfoEntity();
//		history1.setDatetime(time1);
//		history1.setCollectType(2);//内存功耗
//		history1.setCollectString("SEL");
//		history1.setCollectNumber("164");
		TbServer server1 = new TbServer();
		server1.setId(1L);
		history1.setTbServer(server1);
		historyServerInfoService.save(factServerInfoEntity1);
		
		Timestamp time2 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history2 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity2=new FactServerInfoEntity();
//		history2.setDatetime(time2);
//		history2.setCollectType(3);//电源功耗
//		history2.setCollectString("FUL");
//		history2.setCollectNumber("198");
		TbServer server2 = new TbServer();
		server2.setId(1L);
		history2.setTbServer(server2);
		historyServerInfoService.save(factServerInfoEntity2);
		
		Timestamp time3 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history3 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity3=new FactServerInfoEntity();
//		history3.setDatetime(time3);
//		history3.setCollectType(3);//进风口功耗
//		history3.setCollectString("FUL");
//		history3.setCollectNumber("28");
		TbServer server3 = new TbServer();
		server3.setId(1L);
		history3.setTbServer(server3);
		historyServerInfoService.save(factServerInfoEntity3);
		
		
		//第二台服务器信息
		Timestamp time11 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history11 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity11=new FactServerInfoEntity();
//		history11.setDatetime(time11);
//		history11.setCollectType(1);//cup功耗
//		history11.setCollectString("BRM");
//		history11.setCollectNumber("168");
		TbServer server11 = new TbServer();
		server11.setId(2L);
		history11.setTbServer(server11);
		historyServerInfoService.save(factServerInfoEntity11);
		
		Timestamp time12 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history12 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity12=new FactServerInfoEntity();
//		history12.setDatetime(time12);
//		history12.setCollectType(2);//内存功耗
//		history12.setCollectString("SEL");
//		history12.setCollectNumber("190");
		TbServer server12 = new TbServer();
		server12.setId(2L);
		history12.setTbServer(server12);
		historyServerInfoService.save(factServerInfoEntity12);
		
		Timestamp time13 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history13 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity13=new FactServerInfoEntity();
//		history13.setDatetime(time13);
//		history13.setCollectType(3);//电源功耗
//		history13.setCollectString("FUL");
//		history13.setCollectNumber("186");
		TbServer server13 = new TbServer();
		server13.setId(2L);
		history13.setTbServer(server13);
		historyServerInfoService.save(factServerInfoEntity13);
		
		Timestamp time14 = new Timestamp(new Date().getTime());
		TbHistoryServerInfo history14 = new TbHistoryServerInfo();
		FactServerInfoEntity factServerInfoEntity14=new FactServerInfoEntity();
//		history14.setDatetime(time14);
//		history14.setCollectType(3);//进风口功耗
//		history14.setCollectString("FUL");
//		history14.setCollectNumber("24");
		TbServer server14 = new TbServer();
		server14.setId(2L);
		history14.setTbServer(server14);
		historyServerInfoService.save(factServerInfoEntity14);
		
	}
	
}

