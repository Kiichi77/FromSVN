package cn.com.kxcomm.contractmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.CustomerAddressInfoDAO;
import cn.com.kxcomm.contractmanage.entity.TbCustomerAddressInfo;
import cn.com.kxcomm.contractmanage.service.ICustomerAddressInfoService;

@Service("customerAddressInfoService")
public class CustomerAddressInfoService extends CommonService<TbCustomerAddressInfo> implements
ICustomerAddressInfoService{
	
	@Autowired(required=true)
	private CustomerAddressInfoDAO customerAddressInfoDAO;
	
	@Override
	public CommonDAO<TbCustomerAddressInfo> getBindDao() {
		return customerAddressInfoDAO;
	}

}
