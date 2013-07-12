package cn.com.kxcomm.systemcenter.domUser.service.impl;

import cn.com.kxcomm.systemcenter.domUser.service.ExecuteService;

/**
 * 
* 功能描述:公用的业务接口实现类
* @author chenliang 新增日期：2013-6-24
* @since system_center_sdk
 */
public class CommonServiceImpl implements ExecuteService{
	
	private static CommonServiceImpl commonServiceImpl;
	
	public static CommonServiceImpl getInstance(){
		if(null == commonServiceImpl){
			commonServiceImpl = new CommonServiceImpl();
		}
		return commonServiceImpl;
	}

	@Override
	public void execute(String powerShell) {
		
	}
}
