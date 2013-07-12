package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.entity.TbCustomerAddressInfo;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.ICustomerAddressInfoService;
import cn.com.kxcomm.contractmanage.service.ICustomerService;
import cn.com.kxcomm.contractmanage.vo.CustomerVo;

public class CustomerAction extends BaseAction<TbCustomer, String>{

	private static Logger LOGGER = Logger.getLogger(CustomerAction.class);
     
	@Autowired(required = true)
	private ICustomerService customerService;
	
	@Autowired(required = true)
	private ICustomerAddressInfoService customerAddressInfoService;
	
	private String id;
	
	private CustomerVo customerVo;
	
	private TbCustomer customer;
	
	
	
	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

	
	/**
	   * 
	  * 方法用途和描述: 分页获取所有的客户
	  * @return
	  * @author lujia 新增日期：2013-2-22
	  * @since ContractManage
	   */
  	public String listCustomer(){
 		 try {
 		   PageInfo<CustomerVo> page =  new PageInfo<CustomerVo>(ServletActionContext.getRequest());
 		   PageInfo<CustomerVo> CustomerList=customerService.listCustomer(page);
 		   result = JSONObject.fromObject(CustomerList);
 		} catch (Exception e) {
 			LOGGER.error("listCustomer ERROR:",e);
 		}
 	    return SUCCESS;
 	}
 	
	/**
	 * 
	* 方法用途和描述:删除客户
	* @return
	* @author lujia 新增日期：2013-2-22
	* @since ContractManage
	 */
	public String deleteCustomer()
	{
		 try {
				LOGGER.debug("id:"+id);
				String[] ids = id.split(",");
				for (String strId : ids) {
					customerService.deleteByPk(Integer.parseInt(strId));
				}
				result = JSONObject.fromObject(successInfo("ok"));
			} catch (Exception e) {
				LOGGER.error("deleteCustomer ERROR:", e);
			}
			return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:添加客户
	* @return
	* @author lujia 新增日期：2013-2-22
	* @since ContractManage
	 */
	public String addCustomer()
	{
		try {
		customerService.save(customer);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg","ok");
		result = JSONObject.fromObject(map);
		} catch (Exception e) {
			LOGGER.error("addCustomer ERROR:",e);
		}
		return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个配置模板信息
	* @return
	* @author luj 新增日期：2013-2-22
	* @since ContractManage
	*/
    public String queryCustomerById() {
    	LOGGER.info("modelId:  "+this.id);
    	 customerVo = this.customerService.queryCustomerById(this.id);
    	Map map = new HashMap();
		map.put("customer",customerVo);
		this.result = JSONObject.fromObject(map);//
    	return SUCCESS;
    }
	
    
    /**
	    * 
	   * 方法用途和描述: 修改客户信息
	   * @return
	   * @author lujia 新增日期：2013-2-22
	   * @since ContractManage
	    */
	public String updateCustomer()
	{
		customerService.update(customer);
		result = JSONObject.fromObject(successInfo("ok"));
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取所有的客户
	* @return
	* @author lujia 新增日期：2013-2-25
	* @since ContractManage
	 */
	public String queryAllCustomer() {
		try {
			PageInfo<CustomerVo> page = new PageInfo<CustomerVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.customerService.queryAllCustomer();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllCustomer ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 收货地址
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-4-9
	 * @since ContractManage
	 */
	public String listAddressInfo(){
		 try {
			List<TbCustomerAddressInfo> list= customerAddressInfoService.findAll();
			this.jsonArray = JSONArray.fromObject(list);
		} catch (Exception e) {
			LOGGER.error("listCustomer ERROR:",e);
		}
			return "jsonArray";
	}
	
	@Override
	public ICommonService getCommonService() {
		return customerService;
	}

	@Override
	public TbCustomer getModel() {
		if(this.model==null){
			 this.model = new TbCustomer();
		}
		return this.model;
	}

	@Override
	public void setModel(TbCustomer model) {
		this.model=model;
		
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
		
	}

}
