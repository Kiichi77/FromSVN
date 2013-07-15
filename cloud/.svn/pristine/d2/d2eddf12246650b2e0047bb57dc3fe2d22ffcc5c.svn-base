package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbPurchaseOrderContractFile;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IPurchaseOrderContractFileService;
import cn.com.kxcomm.contractmanage.service.ISuppliersService;
import cn.com.kxcomm.contractmanage.status.S_Suppliers;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

/**
 * 
* 功能描述:供应商action
* @author chenliang 新增日期：2013-3-18
* @since ContractManage
 */
public class SuppliersAction extends BaseAction<TbSuppliers, String>{
	private static Logger LOGGER = Logger.getLogger(SuppliersAction.class);

	@Autowired(required = true)
	private ISuppliersService suppliersService;
	@Autowired(required=true)
	private IPurchaseOrderContractFileService purchaseOrderContractFileService;
	public IPurchaseOrderContractFileService getPurchaseOrderContractFileService() {
		return purchaseOrderContractFileService;
	}

	public void setPurchaseOrderContractFileService(
			IPurchaseOrderContractFileService purchaseOrderContractFileService) {
		this.purchaseOrderContractFileService = purchaseOrderContractFileService;
	}
	private String id;
	
	private TbSuppliers suppliers;
	
	private CheckedVo checkedVo; //审核vo
	
	
	public TbSuppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(TbSuppliers suppliers) {
		this.suppliers = suppliers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ISuppliersService getSuppliersService() {
		return suppliersService;
	}

	public void setSuppliersService(ISuppliersService suppliersService) {
		this.suppliersService = suppliersService;
	}
	
	/**
	   * 
	  * 方法用途和描述: 分页获取所有的供应商
	  * @return
	  * @author lujia 新增日期：2013-3-5
	  * @since ContractManage
	   */
	public String pageQuerySuppliers(){
		 try {
			 if(!BlankUtil.isBlank(id) && !"null".equals(id)){
				 int pkid = Integer.parseInt(id);
				 getModel().setId(pkid);
			 }
		   PageInfo<TbSuppliers> page =  new PageInfo<TbSuppliers>(ServletActionContext.getRequest());
		   PageInfo<TbSuppliers> suppliersList=suppliersService.pageQuerySuppliers(page,getModel());
		   result = JSONObject.fromObject(suppliersList);
		} catch (Exception e) {
			LOGGER.error("listSuppliers ERROR:",e);
		}
	    return SUCCESS;
	}
 
	/**
	   * 
	  * 方法用途和描述: 获取所有已经审核通过的供应商
	  * @return
	  * @author lujia 新增日期：2013-3-5
	  * @since ContractManage
	   */
	public String listSuppliers(){
		 try {
		   PageInfo<TbSuppliers> page =  new PageInfo<TbSuppliers>(ServletActionContext.getRequest());
		   PageInfo<TbSuppliers> suppliersList=suppliersService.listSuppliers(page);
		   result = JSONObject.fromObject(suppliersList);
		} catch (Exception e) {
			LOGGER.error("listSuppliers ERROR:",e);
		}
	    return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:删除供应商
	* @return
	* @author lujia 新增日期：2013-3-5
	* @since ContractManage
	 */
	public String deleteSuppliers()
	{
		 try {
				LOGGER.debug("id:"+id);
				String[] ids = id.split(",");
				for (String strId : ids) {
					if(strId!=null)
					suppliersService.deleteByPk(Integer.parseInt(strId));
				}
				result = JSONObject.fromObject(successInfo("ok"));
			} catch (Exception e) {
				LOGGER.error("deleteSuppliers ERROR:", e);
			}
			return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述:添加供应商
	* @return
	* @author lujia 新增日期：2013-3-5
	* @since ContractManage
	 */
	public String addSuppliers()
	{
		try {
//			System.out.println("++++++++++++++++++++++");
//			System.out.println("*******************"+suppliers.getAccounts());
			if(BlankUtil.isBlank(suppliers)){
				suppliers = new TbSuppliers();
			}
			suppliers.setState(S_Suppliers.SUPPLIERSCODE_PENDING);
			suppliersService.save(suppliers);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("msg","ok");
			result = JSONObject.fromObject(map);
		} catch (Exception e) {
			LOGGER.error("addSuppliers ERROR:",e);
		}
		return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个供应商信息
	* @return
	* @author luj 新增日期：2013-3-5
	* @since ContractManage
	*/
    public String querySuppliersById() {
    	LOGGER.info("modelId:  "+this.id);
    	 suppliers = this.suppliersService.querySuppliersById(id);
    	Map map = new HashMap();
		map.put("suppliers",suppliers);
		this.result = JSONObject.fromObject(map);//
    	return SUCCESS;
    }
    /**
	    * 
	   * 方法用途和描述: 修改供应商信息
	   * @return
	   * @author lujia 新增日期：2013-3-5
	   * @since ContractManage
	    */
	public String updateSuppliers()
	{
		if(!BlankUtil.isBlank(suppliers)){
		suppliersService.update(suppliers);
		result = JSONObject.fromObject(successInfo("ok"));
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 下拉框查询所有的供应商
	* @return
	* @author chenliang 新增日期：2013-3-18
	* @since ContractManage
	 */
	public String findAll(){
		List<TbSuppliers> suppliersList = new ArrayList<TbSuppliers>();
		if(BlankUtil.isBlank(this.id)) {
			suppliersList = this.suppliersService.findAll();
		} else {
			TbPurchaseOrderContractFile purchaseOrderContractFile = this.purchaseOrderContractFileService.getByPk(Integer.parseInt(this.id));
			TbSuppliers supplierss = this.suppliersService.getByPk(purchaseOrderContractFile.getSuppliers().getId());
			suppliersList.add(supplierss);
		}
		PageInfo<TbSuppliers> pageInfo = new PageInfo<TbSuppliers>();
		pageInfo.setRows(suppliersList);
		this.result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 提交审核
	* @return
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public String submitCheck(){
		try {
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			if(BlankUtil.isBlank(checkedVo)){
				checkedVo = new CheckedVo();
			}
			checkedVo.setSnpnorId(""+userId);
			String end = this.suppliersService.submitChecked(checkedVo);
			this.result = JSONObject.fromObject(successInfo(end));
		} catch (Exception e) {
			LOGGER.error("updateRelationship ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 审核
	* @return
	* @author chenliang 新增日期：2013-5-15
	* @since ContractManage
	 */
	public String checkSuppliers(){
		try {
			suppliersService.checkSuppliers(checkedVo);
			this.result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("",e);
			this.result = JSONObject.fromObject(successInfo("ok"));
		}
		return SUCCESS;
	}
	
	public CheckedVo getCheckedVo() {
		return checkedVo;
	}

	public void setCheckedVo(CheckedVo checkedVo) {
		this.checkedVo = checkedVo;
	}

	@Override
	public ICommonService getCommonService() {
		return suppliersService;
	}

	@Override
	public TbSuppliers getModel() {
		if(this.model==null){
			 this.model = new TbSuppliers();
		}
		return this.model;
	}

	@Override
	public void setModel(TbSuppliers model) {
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
