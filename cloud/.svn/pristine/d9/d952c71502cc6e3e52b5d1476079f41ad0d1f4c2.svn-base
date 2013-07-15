package cn.com.kxcomm.task.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.entity.TbSystemEventLog;
import cn.com.kxcomm.ipmitool.Sel;
import cn.com.kxcomm.ipmitool.entity.SerInfo;
import cn.com.kxcomm.task.dao.SystemEventLogDAO;
import cn.com.kxcomm.task.dao.TbServerDAO;

/**
 * 
 * SEL日志入库
 * <p>每天入库一次,入库之后清空服务器日志</p>
 * @author zhangjh 新增日期：2012-9-10
 * @since ipmi_task
 */
@Service("selLogService")
public class SelLogService {
	@Autowired(required = true)
	private TbServerDAO tbServerDAO;
	@Autowired(required = true)
	private SystemEventLogDAO systemEventLogDAO;
	
	private Sel sel = Sel.getSingleInstance();
	
	public void collectionSelLog() throws ParseException{
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy  HH:mm:ss"); 
		  
		@SuppressWarnings("unchecked")
		List<TbServer> serverList = tbServerDAO.queryNormalServer();
		for(TbServer server:serverList){
			SerInfo ser = new SerInfo();
			ser.setHost(server.getIdracIp());
			ser.setUser(server.getIdracUser());
			ser.setPasswd(server.getIdracPassword());
			
			List<String> serLog = sel.querySelLog(ser);
			if(serLog.size()<=0){
				return ;
			}
			//清空日志
			sel.clear(ser);
			// 把日志录入到数据库中
			for(String sLog:serLog){
				String aa[] = StringUtils.split(sLog,"|"); 
				String createTime = aa[1]+aa[2];
				String selMsg = aa[3]+","+aa[4];
				String state = "";
				if(aa.length >= 6){
					state = aa[5];
				}
				TbSystemEventLog eventLog = new TbSystemEventLog();
				Date date = format.parse(createTime);  
				eventLog.setCreateTime(date);
				eventLog.setSelMsg(selMsg);
				eventLog.setServerIp(ser.getHost());
				eventLog.setState(state);
				systemEventLogDAO.save(eventLog);
			}
		}
	}
}
