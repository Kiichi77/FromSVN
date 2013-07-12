package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.contractmanage.entity.TbOverPayAccount;

public interface IOverPayAccountService extends ICommonService<TbOverPayAccount>{
	public void merge(TbOverPayAccount overPayAccount);
}
