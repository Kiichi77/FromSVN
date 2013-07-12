package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.vo.CustomerVo;

public interface ICustomerService extends ICommonService<TbCustomer>{

	public PageInfo<CustomerVo> listCustomer(PageInfo<CustomerVo> pageInfo);
	public CustomerVo queryCustomerById(String id);
	public List<CustomerVo> queryAllCustomer(); 

}
