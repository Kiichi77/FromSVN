package cn.com.kxcomm.contractmanage.web.action;

import java.util.Date;
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
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IConfigModelsService;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

public class ConfigModelsAction extends BaseAction<TbConfigModels, String> {
	private static Logger LOGGER = Logger.getLogger(ConfigModelsAction.class);

	@Autowired(required = true)
	private IConfigModelsService configModelsService;

	private String id;
    
	private String typeId;
	
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IConfigModelsService getConfigModelsService() {
		return configModelsService;
	}

	public void setConfigModelsService(IConfigModelsService configModelsService) {
		this.configModelsService = configModelsService;
	}

	/**
	 * 
	 * 方法用途和描述: 获取所有的配置单
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-07
	 * @since ContractManage
	 */

	public String listConfigModels() {
		try {
			PageInfo<ConfigModelsVo> page = new PageInfo<ConfigModelsVo>(
					ServletActionContext.getRequest());
			PageInfo<ConfigModelsVo> configModelsList = this.configModelsService
					.listConfigModels(page,null);
			this.result = JSONObject.fromObject(configModelsList);
		} catch (Exception e) {
			LOGGER.error("listConfigModels ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 返回我的机型配置
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myConfigModels() {
		try {
			   HttpSession session = getHttpSession();
				Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			PageInfo<ConfigModelsVo> page = new PageInfo<ConfigModelsVo>(
					ServletActionContext.getRequest());
			PageInfo<ConfigModelsVo> configModelsList = this.configModelsService
					.listConfigModels(page,userId);
			this.result = JSONObject.fromObject(configModelsList);
		} catch (Exception e) {
			LOGGER.error("listConfigModels ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述: 删除选中的配置单
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-07
	 * @since ContractManage
	 */
	public String deleteConfigModels() {
		try {
			LOGGER.debug("id:" + this.id);
			String[] ids = this.id.split(",");
			for (String strId : ids) {
				this.configModelsService.deleteByPk(Integer.parseInt(strId));
			}
			this.result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("deleteConfigModels ERROR:", e);
		}
		return SUCCESS;
	}
	
	private String configurationName;
	
	private String dataSouceId;
	
	   public String getConfigurationName() {
		return this.configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public String getDataSouceId() {
		return this.dataSouceId;
	}

	public void setDataSouceId(String dataSouceId) {
		this.dataSouceId = dataSouceId;
	}
	/**
	 * 
	* 方法用途和描述: 根据模板id查询详细信息
	* @param id
	* @return
	* @author chenliang 新增日期：2013-3-15
	* @since ContractManage
	 */
	public String listConfigModelById()
	{
		try {
			PageInfo<DataRecordVo> page = new PageInfo<DataRecordVo>();
			List<DataRecordVo> configModelsList=this.configModelsService.listConfigModelById(Integer.parseInt(id),typeId);
		   	page.setRows(configModelsList);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("listConfigModelById ERROR:", e);
		}
		return SUCCESS;
	}
	/**
		* 
		* 方法用途和描述: 根据ID查询某个配置模板信息
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
	    public String queryModelsById() {
	    	LOGGER.info("modelId:  "+this.id);
	    	ConfigModelsVo configModelsVo = this.configModelsService.queryModelsById(this.id);
	    	Map map = new HashMap();
			map.put("configmodel",configModelsVo);
			this.result = JSONObject.fromObject(map);//
	    	return SUCCESS;
	    }

	    /**
		* 
		* 方法用途和描述: 新增配置
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
	   public String saveConfiguration() {
		   LOGGER.info(this.id+"  utilDataID: "+this.dataSouceId+"   ConfigurationName: "+this.configurationName);
		   TbConfigModels configModels = new TbConfigModels();
		   HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			TbUser user=new TbUser();
			user.setId(userId);
		   configModels.setCreator(user);
		   configModels.setName(this.configurationName);
		   configModels.setStatus(1);
		   configModels.setType(1);
		   if(!BlankUtil.isBlank(this.id)) {
			   configModels.setId(Integer.parseInt(this.id));
		   } else {
			   configModels.setCatalogTotalprice(0.0);
			   configModels.setAfterDiscountNowPrice(0.0);
			   configModels.setAfterDiscountPrice(0.0);
			   configModels.setTotalPrice(0.0);
		   }
		   configModels.setCreatetime(new Date());
		   TbDataSource dataSource = new TbDataSource();
		   dataSource.setId(Integer.parseInt(this.dataSouceId));
		   configModels.setDataSource(dataSource);
		   String end = this.configModelsService.saveConfiguration(configModels);
		   this.result = JSONObject.fromObject(successInfo(end));
		   return SUCCESS;
	   }
	   
	   /**
		* 
		* 方法用途和描述: 根据配置ID查询某个配置模板信息
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
	   public String queryModelsInfoById() {
		   
		   return SUCCESS;
	   }

	@Override
	public ICommonService getCommonService() {
		return configModelsService;
	}

	@Override
	public TbConfigModels getModel() {
		if (this.model == null) {
			this.model = new TbConfigModels();
		}
		return null;
	}

	@Override
	public void setModel(TbConfigModels model) {
		this.model = model;
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
