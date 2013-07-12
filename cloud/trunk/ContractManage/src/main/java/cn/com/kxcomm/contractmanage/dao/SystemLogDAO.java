package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbSystemLog;
@Repository
public class SystemLogDAO extends CommonDAO<TbSystemLog>{
	/**
	 * 
	 * 返回所有的日志
	 * 
	 * @return
	 * @author lujia 新增日期：2013-4-2
	 * @since ContractManage
	 */
	public List<TbSystemLog> querySystemLog(){
		String hqlString=" from TbSystemLog log ";
		return this.find(hqlString);
	}

}
