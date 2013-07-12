package cn.com.kxcomm.contractmanage.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.OverPayAccountDAO;
import cn.com.kxcomm.contractmanage.entity.TbOverPayAccount;
import cn.com.kxcomm.contractmanage.service.IOverPayAccountService;
@Service("overPayAccountService")
public class OverPayAccountService extends CommonService<TbOverPayAccount> implements IOverPayAccountService{
	private static final Logger LOGGER = Logger.getLogger(OverPayAccountService.class);
	@Autowired(required=true)
	private OverPayAccountDAO overPayAccountDAO;
	@Override
	public CommonDAO<TbOverPayAccount> getBindDao() {
		return overPayAccountDAO;
	}
	
	@Override
	public void merge(TbOverPayAccount overPayAccount) {
		overPayAccountDAO.merge(overPayAccount);
	}
}
