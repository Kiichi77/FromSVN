package cn.com.kxcomm.contractmanage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.SystemLogDAO;
import cn.com.kxcomm.contractmanage.entity.TbSystemLog;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.SystemLogVo;

@Service("systemLogService")
public class SystemLogService extends CommonService<TbSystemLog> implements ISystemLogService{
	private static final Logger LOG = Logger.getLogger(SystemLogService.class);
	@Autowired(required=true)
	private SystemLogDAO systemLogDAO;
	/**
	* 
	* 方法用途和描述: 分页查询所有的日志
	* @return
	* @author luj 新增日期：2013-4-2
	* @since ContractManage
	*/
	@Override
	public PageInfo<SystemLogVo> listSystemLog(PageInfo<SystemLogVo> pageInfo,SystemLogVo LogVo) {
		StringBuffer sb=new StringBuffer();
		StringBuffer countHql=new StringBuffer();
		sb.append(" from TbSystemLog log where 1=1 ");
		countHql.append("select count(log.id) from TbSystemLog log where 1=1");
		if(!BlankUtil.isBlank(LogVo.getStarttime())){
			sb.append(" and date_format(log.operTime,'%Y-%m-%d') >= date_format('"+LogVo.getStarttime()+"','%Y-%m-%d') ");
			countHql.append(" and date_format(log.operTime,'%Y-%m-%d') >= date_format('"+LogVo.getStarttime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(LogVo.getEndtime())){
			sb.append(" and date_format(log.operTime,'%Y-%m-%d') <= date_format('"+LogVo.getEndtime()+"','%Y-%m-%d') ");
			countHql.append(" and date_format(log.operTime,'%Y-%m-%d') <= date_format('"+LogVo.getEndtime()+"','%Y-%m-%d') ");
		}
		if(!BlankUtil.isBlank(LogVo.getOperaterName())){
			sb.append(" and log.operater.userName like '"+LogVo.getOperaterName()+"' ");
			countHql.append(" and log.operater.userName like '%"+LogVo.getOperaterName()+"%'  ");
		}
		sb.append(" order by log.operTime desc ");
		PageInfo<TbSystemLog> p = new PageInfo<TbSystemLog>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List<TbSystemLog> list=systemLogDAO.pageInfoQuery(sb.toString(), p);
		int count = systemLogDAO.findTotalCount(countHql.toString());
		List<SystemLogVo> listSystemLog=new ArrayList<SystemLogVo>();
		for(TbSystemLog systemLog:list)
		{
			SystemLogVo systemLogVo=new SystemLogVo();
			systemLogVo.setId(""+systemLog.getId());
			systemLogVo.setLogType(""+systemLog.getLogType());
			systemLogVo.setMessage(systemLog.getMessage());
			systemLogVo.setOperatContent(systemLog.getOperatContent());
			systemLogVo.setOperaterName(systemLog.getOperater().getUserName());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
			systemLogVo.setOperTime(sdf.format(systemLog.getOperTime()));
			systemLogVo.setResult(""+systemLog.getResult());
			listSystemLog.add(systemLogVo);
		}
		PageInfo<SystemLogVo> page = new PageInfo<SystemLogVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listSystemLog);
        return page;
	}
	@Override
	public CommonDAO<TbSystemLog> getBindDao() {
		return systemLogDAO;
	}
	@Override
	public void writeTheLog(String logType, Long operaterId, String result,
			String message, String operatContent) throws Exception {
		if(logType==null||"".equals(logType)||operaterId==null ||result==null||"".equals(result)){
			 throw new Exception("日志参数不正确，请检查日志类型、操作人ID、操作结果是否为空");
		}
		TbSystemLog log = new TbSystemLog();
		log.setLogType(logType);
		log.setMessage(message);
		log.setOperatContent(operatContent);
		TbUser u = new TbUser();
		u.setId(operaterId);
		log.setOperater(u);
		log.setOperTime(new Date());
		log.setResult(result);
		this.save(log);
	}
}
