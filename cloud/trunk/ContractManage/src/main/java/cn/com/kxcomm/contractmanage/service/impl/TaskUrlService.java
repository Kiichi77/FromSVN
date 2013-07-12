package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.TaskUrlDAO;
import cn.com.kxcomm.contractmanage.entity.TbTaskUrl;
@Service("taskUrlService")
public class TaskUrlService extends CommonService<TbTaskUrl>{
	@Autowired(required=true)
	private TaskUrlDAO taskUrlDAO;
	@Override
	public CommonDAO<TbTaskUrl> getBindDao() {
		return taskUrlDAO;
	}

}
