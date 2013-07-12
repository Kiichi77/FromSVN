package cn.com.kxcomm.ipmi.service.impl;

import java.sql.Timestamp;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.common.util.ReflectionUtils;
import cn.com.kxcomm.ipmi.dao.CommonDAO;
import cn.com.kxcomm.ipmi.dao.SystemEventLogDAO;
import cn.com.kxcomm.ipmi.entity.TbSystemEventLog;
import cn.com.kxcomm.ipmi.service.SystemEventLogService;
import cn.com.kxcomm.ipmi.vo.BlackNameEntity;

/**
 * 系统日志采集业务逻辑处理类
 * @author chenliang
 *
 */
@Service("systemEventLogService")
public class SystemEventLogServiceImpl extends CommonServiceImpl<TbSystemEventLog> implements
		SystemEventLogService {
	
	@Autowired(required=true)
	private SystemEventLogDAO systemEventLogDAO;

	@Override
	public CommonDAO<TbSystemEventLog> getBindDao() {
		return systemEventLogDAO;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<TbSystemEventLog> findByPage(TbSystemEventLog entity, Page<TbSystemEventLog> pageInfo, Timestamp endtt) {
		StringBuffer hql =  new StringBuffer(" from TbSystemEventLog tu where 1=1 ");
		if(!BlankUtil.isBlank(entity.getServerIp())){
			hql.append(" and tu.serverIp like ? ");
			entity.setServerIp("%"+entity.getServerIp()+"%");
		}
		if(!BlankUtil.isBlank(entity.getCreateTime())){
			hql.append(" and tu.createTime > ? and  tu.createTime < ? ");
		}
//		if(entity.getDateTime()==entity.getEndTime()){
//			hql.append(" and tu.dateTime=? ");
//		}
		if(entity.getServerIp().equals("")&&entity.getServerIp()!=null)
			entity.setServerIp(null);
		return systemEventLogDAO.findByPage(pageInfo, hql.toString(), entity.getServerIp(),entity.getCreateTime(),endtt);
	}
	
	/**
	 * 分页查询,表没外键要自己写HQL
	 */
	@Override
	public Page<TbSystemEventLog> queryPage(TbSystemEventLog model,
			Page<TbSystemEventLog> pageInfo, Timestamp endtt){
		Page<TbSystemEventLog> pagelist = new Page<TbSystemEventLog>();
		
		return pagelist;
	}

}
