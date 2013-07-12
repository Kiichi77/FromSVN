package cn.com.kxcomm.contractmanage.web.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbContractDocType;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractDocTypeService;
import cn.com.kxcomm.contractmanage.vo.ContractDocTypeVo;

public class ContractDocTypeAction extends BaseAction<TbContractDocType, String>{
	private static Logger LOGGER = Logger.getLogger(ContractDocTypeAction.class);

	@Autowired(required=true)
	private IContractDocTypeService contractDocTypeService;
	
	private String id;

	private TbContractDocType contractDocType;
	

	public TbContractDocType getContractDocType() {
		return contractDocType;
	}

	public void setContractDocType(TbContractDocType contractDocType) {
		this.contractDocType = contractDocType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	   * 
	  * 方法用途和描述: 获取所有的分类
	  * @return
	  * @author lujia 新增日期：2012-12-10
	  * @since ContractManage
	   */
	  	public String listContractDocType(){
	  		 try {
	  		   PageInfo<ContractDocTypeVo> page =  new PageInfo<ContractDocTypeVo>(ServletActionContext.getRequest());
	  		   PageInfo<ContractDocTypeVo> contractDocTypeList=contractDocTypeService.listContractDocType(page);
	  		   result = JSONObject.fromObject(contractDocTypeList);
	  		} catch (Exception e) {
	  			LOGGER.error("listConfigModels ERROR:",e);
	  		}
	  	    return SUCCESS;
	  	}
	  	
	  	/**
		 * 
		* 方法用途和描述:删除分类
		* @return
		* @author lujia 新增日期：2012-12-04
		* @since ContractManage
		 */
		public String deleteContractDocType()
		{
			 try {
					LOGGER.debug("id:"+id);
					String[] ids = id.split(",");
					for (String strId : ids) {
						contractDocTypeService.deleteByPk(Integer.parseInt(strId));
					}
					result = JSONObject.fromObject(successInfo("ok"));
				} catch (Exception e) {
					LOGGER.error("deleteContractDocType ERROR:", e);
				}
				return SUCCESS;
		}
	
		/**
		 * 
		* 方法用途和描述:添加分类
		* @return
		* @author lujia 新增日期：2012-12-04
		* @since ContractManage
		 */
		public String addContractDocType()
		{
			try {
				System.out.println("*******************"+contractDocType.getName());
			contractDocTypeService.save(contractDocType);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("msg","ok");
			result = JSONObject.fromObject(map);
//			return successInfo(null);
			} catch (Exception e) {
				LOGGER.error("addContractDocType ERROR:",e);
			}
//			return successInfo("no");
			return SUCCESS;
		}
	
		/**
		 * 
		* 方法用途和描述: 获取所有的类型
		* @return
		* @author chenliang 新增日期：2012-12-17
		* @since ContractManage
		 */
	public String findAll() {
		try {
			List<TbContractDocType> docTypeList = contractDocTypeService.findAll();
			PageInfo<TbContractDocType> pageinfo = new PageInfo<TbContractDocType>(getHttpRequest());
			pageinfo.setRows(docTypeList);
			result = JSONObject.fromObject(pageinfo);
		} catch (Exception e) {
			LOGGER.error("findAll error.",e);
		}
		return SUCCESS;
	  }
	  
	  
	
	public IContractDocTypeService getContractDocTypeService() {
		return contractDocTypeService;
	}

	public void setContractDocTypeService(
			IContractDocTypeService contractDocTypeService) {
		this.contractDocTypeService = contractDocTypeService;
	}

	@Override
	public ICommonService getCommonService() {
		return contractDocTypeService;
	}

	@Override
	public TbContractDocType getModel() {
		if (this.model == null) {
			this.model = new TbContractDocType();
		}
		return null;
	}

	@Override
	public void setModel(TbContractDocType model) {
		this.model=model;
		
	}

	@Override
	public String[] getIds() {
	return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids=ids;
		
	}

}
