package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.JobPlaceDAO;
import cn.com.kxcomm.contractmanage.entity.TbJobPlace;
import cn.com.kxcomm.contractmanage.service.IJobPlaceService;

@Service("jobPlaceService")
public class JobPlaceService extends CommonService<TbJobPlace> implements
		IJobPlaceService {
	@Autowired(required=true)
	private JobPlaceDAO jobPlaceDAO;
	@Override
	public CommonDAO<TbJobPlace> getBindDao() {
		return jobPlaceDAO;
	}

	

}
