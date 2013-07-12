package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.TemporaryDataToFormalDAO;
import cn.com.kxcomm.contractmanage.entity.TbTemporaryDataToFormal;
import cn.com.kxcomm.contractmanage.service.ITemporaryDataToFormalService;

@Service("temporaryDataToFormalService")
public class TemporaryDataToFormalService  extends CommonService<TbTemporaryDataToFormal> implements ITemporaryDataToFormalService {

	@Autowired(required=true)
	private TemporaryDataToFormalDAO temporaryDataToFormalDAO;
	
	@Override
	public CommonDAO<TbTemporaryDataToFormal> getBindDao() {
		return temporaryDataToFormalDAO;
	}

}
