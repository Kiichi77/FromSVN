package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.CustomerDAO;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.service.ICustomerService;
import cn.com.kxcomm.contractmanage.vo.CustomerVo;

@Service("customerService")
public class CustomerService extends CommonService<TbCustomer> implements ICustomerService{
  
	private static final Logger LOG = Logger.getLogger(CustomerService.class);
	
	@Autowired(required=true)
	private CustomerDAO customerDAO;
	
	@Override
	public CommonDAO<TbCustomer> getBindDao() {
		return customerDAO;
	}
	
	//分页查询所有的客户
	public PageInfo<CustomerVo> listCustomer(PageInfo<CustomerVo> pageInfo) {
		StringBuffer sql = new StringBuffer();
	    sql.append(" SELECT a.id,a.address,a.company_name,a.contact,a.description,a.email,a.phone,a.job_place_id,b.job_place from tb_customer a,tb_job_place b where a.job_place_id = b.id ");
	    StringBuffer countSql = new StringBuffer();
	    countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
	    int count = customerDAO.findCount(countSql.toString());
		List list = customerDAO.findByPage(sql.toString(), pageInfo);
		List<CustomerVo> listCustomerVo=new ArrayList<CustomerVo>();
		Object[] obj = new Object[list.size()];	
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			CustomerVo customerVo=new CustomerVo();
			customerVo.setId(obj[0].toString());
			customerVo.setAddress(obj[1].toString());
			customerVo.setCompanyName(obj[2].toString());
			customerVo.setContact(obj[3].toString());
			customerVo.setDescription(obj[4].toString());
			customerVo.setEmail(obj[5].toString());
			customerVo.setPhone(obj[6].toString());
			customerVo.setJobPlaceId(obj[7].toString());
			customerVo.setJobPlaceName(obj[8].toString());
			listCustomerVo.add(customerVo);
	}
		PageInfo<CustomerVo> page = new PageInfo<CustomerVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listCustomerVo);
		return page;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个客户信息
	* @return
	* @author luj 新增日期：2013-2-22
	* @since ContractManage
	*/
	@Override
	public CustomerVo queryCustomerById(String id) {
		TbCustomer customer = this.customerDAO.findById(Integer.parseInt(id));
		CustomerVo customerVo = new CustomerVo();
		customerVo.setId(""+customer.getCustomerId());
		customerVo.setCompanyName(""+customer.getCompanyName());
		customerVo.setAddress(""+customer.getAddress());
		customerVo.setContact(""+customer.getContact());
		customerVo.setEmail(""+customer.getEmail());
		customerVo.setPhone(""+customer.getPhone());
		customerVo.setDescription(""+customer.getDescription());
		customerVo.setJobPlaceId(customer.getJobPlace().getId().toString());
		customerVo.setJobPlaceName(customer.getJobPlace().getJobPlaceName());
		return customerVo;
	}

	/**
	* 
	* 方法用途和描述: 查询所有的客户
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
	@Override
	public List<CustomerVo> queryAllCustomer() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.id,a.address,a.company_name,a.contact,a.description,a.email,a.phone,a.job_place_id,b.job_place from tb_customer a,tb_job_place b where a.job_place_id = b.id ");
		List list = this.customerDAO.queryAllCustomer(sql.toString());
		List<CustomerVo> listCustomerVo = new ArrayList<CustomerVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			CustomerVo customerVo=new CustomerVo();
			customerVo.setId(obj[0].toString());
			customerVo.setAddress(obj[1].toString());
			customerVo.setCompanyName(obj[2].toString());
			customerVo.setContact(obj[3].toString());
			customerVo.setDescription(obj[4].toString());
			customerVo.setEmail(obj[5].toString());
			customerVo.setPhone(obj[6].toString());
			customerVo.setJobPlaceId(obj[7].toString());
			customerVo.setJobPlaceName(obj[8].toString());
			listCustomerVo.add(customerVo);
		}
		return listCustomerVo;
	}
}
