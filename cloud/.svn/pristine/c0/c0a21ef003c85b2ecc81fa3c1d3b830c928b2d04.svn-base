package cn.com.kxcomm.woyun.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.ImagesService;
import cn.com.kxcomm.woyun.service.ServicesService;
import cn.com.kxcomm.woyun.vo.ImageVO;
import cn.com.kxcomm.woyun.vo.ServicesVO;
import cn.com.woyun.glance.model.Image;
import cn.com.woyun.glance.service.GlanceApi;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Services;
import cn.com.woyun.keystone.service.RoleApi;

/**
 * 
* 功能描述:服务管理实现类
* @author lizl 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("servicesService")
public class ServicesServiceImpl extends
		CommonServiceImpl<ServicesVO> implements ServicesService {

	private RoleApi roleApi = RoleApi.getInstance();
	
	/**
	 * 
	* 方法用途和描述:获得所有的服务
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	@Override
	public List<ServicesVO> listImages(String tokenId) throws Exception {
		Services services = this.roleApi.listServices(tokenId);
		List<ServicesVO> servicesVOs = new ArrayList<ServicesVO>();
		if(0 < services.getList().size()) {
			List<cn.com.woyun.keystone.model.Service> list = services.getList();
			ServicesVO servicesVO = null;
			for(int i = 0; i < list.size(); i++ ) {
				servicesVO = new ServicesVO();
				servicesVO.setId(list.get(i).getId());
				servicesVO.setName(list.get(i).getName());
				servicesVO.setType(list.get(i).getType());
				servicesVO.setDescription(list.get(i).getDescription());
				servicesVOs.add(servicesVO);
			}
		}
		return servicesVOs;
	}
}
