package cn.com.kxcomm.ipmi.service;

import java.sql.Timestamp;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbSystemEventLog;

/**
 * 
* 功能描述:系统日志采集
* @author chenl 新增日期：2012-8-13
* @since ipmi_web
 */
public interface SystemEventLogService extends CommonService<TbSystemEventLog> {

	Page<TbSystemEventLog> findByPage(TbSystemEventLog model,
			Page<TbSystemEventLog> pageInfo, Timestamp endtt);
	Page<TbSystemEventLog> queryPage(TbSystemEventLog model,
			Page<TbSystemEventLog> pageInfo, Timestamp endtt);
}
