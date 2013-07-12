package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataSourceType;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IDataSourceTypeService;

public class DataSourceTypeAction extends BaseAction<TbDataSourceType, String> {
	private static Logger LOGGER = Logger.getLogger(DataSourceTypeAction.class);
	
	@Autowired(required=true)
	private IDataSourceTypeService dataSourceTypeService;

	public IDataSourceTypeService getDataSourceTypeService() {
		return dataSourceTypeService;
	}

	public void setDataSourceTypeService(
			IDataSourceTypeService dataSourceTypeService) {
		this.dataSourceTypeService = dataSourceTypeService;
	}
	
	/**
	 * 
	 * 方法用途和描述:查询所有数据源分类
	 * 
	 * @return
	 * @author luj 新增日期：2013-2-20
	 * @since ContractManage
	 */
	public String queryAllDataSourceType() {
		try {
			PageInfo<TbDataSourceType> page = new PageInfo<TbDataSourceType>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.dataSourceTypeService.queryAllDataSourceType();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllDataSourceType ERROR:", e);
		}
		return SUCCESS;
	}

	@Override
	public ICommonService getCommonService() {
		return dataSourceTypeService;
	}

	@Override
	public TbDataSourceType getModel() {
		if(this.model==null){
			 this.model = new TbDataSourceType();
		}
		return this.model;
	}

	@Override
	public void setModel(TbDataSourceType model) {
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
