package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.JobdutiesDAO;
import cn.com.kxcomm.contractmanage.entity.TbJobduties;
import cn.com.kxcomm.contractmanage.service.IJobdutiesService;

@Service("jobdutiesService")
public class JobdutiesService extends CommonService<TbJobduties> implements IJobdutiesService{
	@Autowired(required=true)
	private JobdutiesDAO jobdutiesDAO;
	@Override
	public CommonDAO<TbJobduties> getBindDao() {
		return jobdutiesDAO;
	}

}
