package cn.com.kxcomm.systemcenter.domUser.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import cn.com.kxcomm.systemcenter.domUser.dao.DomUserDAO;
import cn.com.kxcomm.systemcenter.domUser.entity.DomUserEntity;
import cn.com.kxcomm.systemcenter.domUser.service.DomUserService;
import cn.com.kxcomm.systemcenter.domUser.service.ExecuteService;

/**
 * 
* 功能描述:域用户业务处理实现类
* @author chenliang 新增日期：2013-6-24
* @since system_center_sdk
 */
public class DomUserServiceImpl implements DomUserService {

	private static final Logger LOGGER = Logger.getLogger(DomUserServiceImpl.class);
	
	private static DomUserServiceImpl domUserServiceImpl;
	private ExecuteService execute = CommonServiceImpl.getInstance();
	private DomUserDAO domUserDAO = DomUserDAO.getInstance();
	
	/**
	 * 
	* 方法用途和描述: 单例
	* @return
	* @author chenliang 新增日期：2013-6-24
	* @since system_center_sdk
	 */
	public static DomUserServiceImpl getInstance(){
		if(null == domUserServiceImpl){
			domUserServiceImpl = new DomUserServiceImpl();
		}
		return domUserServiceImpl;
	}

	@Override
	public void listDomUser() {
		String powerShell = "powershell get-process p*";
		List<DomUserEntity> list = domUserDAO.listEntity();
		System.out.println("list"+list);
		execute.execute(powerShell);
	}
	
}
