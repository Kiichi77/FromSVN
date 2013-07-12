

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import com.sun.tools.hat.internal.server.HistogramQuery;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.dao.HistoryServerInfoDAO;
import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbServerBelong;
import cn.com.kxcomm.ipmi.entity.TbServerBelongId;
import cn.com.kxcomm.ipmi.service.HistoryServerInfoService;
import cn.com.kxcomm.ipmi.service.ServerService;
import cn.com.kxcomm.ipmi.vo.ResultServerListEntity;

/**
 * 
* 功能描述：服务器信息管理测试类
* @author chenl 新增日期：2012-8-8
* @since ipmi_web
 */
public class ServerTest {
	
	private static Logger log = Logger.getLogger(ServerTest.class);
	private ApplicationContext ctx;
	private ServerService serverService;
	private HistoryServerInfoService historyServerInfoService;
	private HistoryServerInfoDAO historyServerInfoDao;
	
	public ServerTest(){
	ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
	if (ctx != null)
		System.out.println("success spring");
		serverService = (ServerService) ctx.getBean("serverService");
		historyServerInfoService = (HistoryServerInfoService) ctx.getBean("historyServerInfoService");
		historyServerInfoDao = (HistoryServerInfoDAO) ctx.getBean("historyServerInfoDAO");
	}
	
	@Test 
	public void getdate(){
		FactServerInfoEntity factserverInfoEntity = new  FactServerInfoEntity();
		DimDateEntity dimDateEntity = new DimDateEntity();

		dimDateEntity.setWeek((short)38);
		dimDateEntity.setYear((short)2012);
		factserverInfoEntity.setDimDate(dimDateEntity);
		  factserverInfoEntity.setCollectType((short)2);
		short dateTimeType = 2;
		System.out.println(factserverInfoEntity.toString());
//	List<FactServerInfoEntity> list=historyServerInfoService.getGraphicsData(factserverInfoEntity, dateTimeType);
	
//   System.out.println(list.size());
//	for (FactServerInfoEntity fs:list) {
//		System.out.println(fs.toString());
//	}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据服务器id查找策略关联关系
	* @author chenl 新增日期：2012-9-7
	* @since ipmi_web
	 */
//	@Test
	public void findByServerId(){
		Long[] keyId = new Long[1];
		//keyId[0]=1L;
		keyId[0]=2L;
		boolean flat = serverService.findByServerId(keyId);
		log.info("flat:"+flat);
	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询
	* @author chenl 新增日期：2012-8-27
	* @since ipmi_web
	 */
	public void findByPage(){
		TbServer server = new TbServer();
		Page<TbServer> pageInfo = new Page<TbServer>();
		pageInfo.setPageSize(20);
		pageInfo.setPageNo(1);
		pageInfo.setStart(1);
		pageInfo.setEnd(20);
		Page<TbServer> page = serverService.findByPage(server, pageInfo);
		log.info("page:"+Json.toJson(page));
	}
	
	/**
	 * 
	* 方法用途和描述: 获取服务器实施详情数据
	* @author chenl 新增日期：2012-8-22
	* @since ipmi_web
	 */
	public void getServerRealTimeData(){
		TbServer server = new TbServer();
		server.setIdracIp("192.168.1.20");
		server.setIdracUser("test");
		server.setIdracPassword("123456");
		ResultServerListEntity resultList = serverService.getServerRealTimeData(server);
		log.info("resultList:"+Json.toJson(resultList));
	}
	
	/**
	 * 保存服务器与服务器所属集合列表的关系
	 */
	public void insertServerBelong() {
		TbServerBelongId id = new TbServerBelongId();
		id.setServerId(1L);
		id.setBelongId(500L);
		TbServerBelong serverBelong = new TbServerBelong();
		serverBelong.setId(id);
		serverService.insertServerBelong(serverBelong);
		
		TbServerBelongId id1 = new TbServerBelongId();
		id1.setServerId(2L);
		id1.setBelongId(501L);
		TbServerBelong serverBelong1 = new TbServerBelong();
		serverBelong1.setId(id1);
		serverService.insertServerBelong(serverBelong1);
		
		TbServerBelongId id2 = new TbServerBelongId();
		id2.setServerId(3L);
		id2.setBelongId(502L);
		TbServerBelong serverBelong2 = new TbServerBelong();
		serverBelong2.setId(id2);
		serverService.insertServerBelong(serverBelong2);
		
		TbServerBelongId id3 = new TbServerBelongId();
		id3.setServerId(4L);
		id3.setBelongId(503L);
		TbServerBelong serverBelong3 = new TbServerBelong();
		serverBelong3.setId(id3);
		serverService.insertServerBelong(serverBelong3);
	}
	
	/**
	 * 修改服务器与服务器所属集合列表的关系
	 */
	public void updateServerBelong() {
		TbServerBelongId id = new TbServerBelongId();
		id.setServerId(3L);
		id.setBelongId(502L);
		TbServerBelong serverBelong = new TbServerBelong();
		serverBelong.setId(id);
		serverService.updateServerBelong(serverBelong);
	}
	
	/**
	 * 删除服务器与服务器所属集合列表的关系
	 */
	public void deleteServerBelong() {
		Long[] ids = {1L,2L};
		serverService.deleteServerBelong(ids);
	}

	/**
	 * 服务器保存
	 */
	public void save(){
		TbServer server = new TbServer();
		server.setCangetCpuConsumption((short) 23);
		server.setCangetMemoryConsumption((short)1.3);
		server.setCangetPowerConsumption((short)50);
		server.setCangetWindTemperature((short)60);
		server.setCpuConsumptionPolicy((short) 100);
		server.setGroupId(1001L);
		server.setIdracIp("192.168.1.10");
		server.setIdracPassword("kxtxComm");
		server.setIdracUser("root");
		server.setMemoryConsumptionPolicy((short)1);
		server.setOperateSystem("dell");
		server.setPowerConsumptionPolicy((short)100);
		server.setServerDescription("server");
		server.setServerIpIn("192.168.1.10");
		server.setServerIpOut("192.168.1.20");
		server.setServerName("server_kxtx");
		serverService.save(server);
		
		TbServer server1 = new TbServer();
		server1.setCangetCpuConsumption((short) 23);
		server1.setCangetMemoryConsumption((short)1.3);
		server1.setCangetPowerConsumption((short)50);
		server1.setCangetWindTemperature((short)60);
		server1.setCpuConsumptionPolicy((short) 100);
		server1.setGroupId(1001L);
		server1.setIdracIp("192.168.1.11");
		server1.setIdracPassword("kxtxComm1");
		server1.setIdracUser("root");
		server1.setMemoryConsumptionPolicy((short)1);
		server1.setOperateSystem("dell1");
		server1.setPowerConsumptionPolicy((short)100);
		server1.setServerDescription("server1");
		server1.setServerIpIn("192.168.1.11");
		server1.setServerIpOut("192.168.1.21");
		server1.setServerName("server_kxtx1");
		serverService.save(server1);
		
		TbServer server2 = new TbServer();
		server2.setCangetCpuConsumption((short) 23);
		server2.setCangetMemoryConsumption((short)1.3);
		server2.setCangetPowerConsumption((short)50);
		server2.setCangetWindTemperature((short)60);
		server2.setCpuConsumptionPolicy((short) 100);
		server2.setGroupId(1001L);
		server2.setIdracIp("192.168.1.12");
		server2.setIdracPassword("kxtxComm2");
		server2.setIdracUser("root");
		server2.setMemoryConsumptionPolicy((short)1);
		server2.setOperateSystem("dell2");
		server2.setPowerConsumptionPolicy((short)100);
		server2.setServerDescription("server2");
		server2.setServerIpIn("192.168.1.12");
		server2.setServerIpOut("192.168.1.22");
		server2.setServerName("server_kxtx2");
		serverService.save(server2);
		
		TbServer server3 = new TbServer();
		server3.setCangetCpuConsumption((short) 23);
		server3.setCangetMemoryConsumption((short)1.3);
		server3.setCangetPowerConsumption((short)50);
		server3.setCangetWindTemperature((short)60);
		server3.setCpuConsumptionPolicy((short) 100);
		server3.setGroupId(1001L);
		server3.setIdracIp("192.168.1.13");
		server3.setIdracPassword("kxtxComm3");
		server3.setIdracUser("root");
		server3.setMemoryConsumptionPolicy((short)1);
		server3.setOperateSystem("dell3");
		server3.setPowerConsumptionPolicy((short)100);
		server3.setServerDescription("server3");
		server3.setServerIpIn("192.168.1.13");
		server3.setServerIpOut("192.168.1.23");
		server3.setServerName("server_kxtx3");
		serverService.save(server3);
	}
	
	//@Test
	public void testHql() {
		FactServerInfoEntity factserverEntity = new  FactServerInfoEntity();
		DimDateEntity dimDateEntity = new DimDateEntity();

		dimDateEntity.setWeek((short)38);
		dimDateEntity.setYear((short)2012);
		factserverEntity.setDimDate(dimDateEntity);
		factserverEntity.setCollectType((short)2);
		String hql = "select f.max,f.min,f.average from FactServerInfoEntity f where f.collectType=? and f.dimDate.week=? and f.dimDate.year=?";
		List ll = historyServerInfoDao.findByHql(hql,factserverEntity.getCollectType(),factserverEntity.getDimDate().getFullDate(),factserverEntity.getDimDate().getWeek(),factserverEntity.getDimDate().getMonth(),factserverEntity.getDimDate().getYear());
		System.out.println(ll.size());
	}
}

