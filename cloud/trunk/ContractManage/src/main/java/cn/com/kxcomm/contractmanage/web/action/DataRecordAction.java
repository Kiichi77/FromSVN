package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbProductType;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IDataRecordService;
import cn.com.kxcomm.contractmanage.service.IProductTypeService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.ContractDocTypeVo;
import cn.com.kxcomm.contractmanage.vo.DataBean;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class DataRecordAction extends BaseAction<TbDataRecord, String>{
	private static Logger LOGGER = Logger.getLogger(DataRecordAction.class);
	
	@Autowired(required=true)
	private IDataRecordService idataRecordService;
	private IProductTypeService productTypeService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	//数据源id
	private String dataSourceId; 
	//类型id
	private String typeId;
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public IProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 
	* 方法用途和描述: 根据数据源id查询详情
	* @return
	* @author chenliang 新增日期：2012-12-11
	* @since ContractManage
	 */
	public String listDetailDataRecord(){
		try {
			if(!BlankUtil.isBlank(dataSourceId)){
				int dataid = Integer.parseInt(dataSourceId);
				List<DataBean> volist = idataRecordService.listDetailDataRecord(dataid);
				PageInfo<DataBean> pageInfo = new PageInfo<DataBean>();
				pageInfo.setRows(volist);
				if(!BlankUtil.isBlank(volist)){
					pageInfo.setTotal(volist.size());
				}
				pageInfo.setMsg("ok");
				result = JSONObject.fromObject(pageInfo);
			}
		} catch (Exception e) {
			log.error("listDetailDataRecord error.",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据类型id查询详情
	* @return
	* @author chenliang 新增日期：2012-12-11
	* @since ContractManage
	 */
	public String getDetail(){
		try {
			if(!BlankUtil.isBlank(typeId) && !BlankUtil.isBlank(dataSourceId)){
				int type = Integer.parseInt(typeId);
				int sourceId = Integer.parseInt(dataSourceId);
				PageInfo<DataRecordVo> page = new PageInfo<DataRecordVo>(ServletActionContext.getRequest());
				PageInfo<DataRecordVo> volist = idataRecordService.getDetail(type,sourceId,page);
				result = JSONObject.fromObject(volist);
			}
		} catch (Exception e) {
			log.error("listDetailDataRecord error.",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取产品分类
	* @return
	* @author chenliang 新增日期：2013-1-10
	* @since ContractManage
	 */
	public String getProductType(){
		try {
			List<TbProductType> typeList = productTypeService.findAll();
			List<ContractDocTypeVo> volist = new ArrayList<ContractDocTypeVo>();
			for (TbProductType tbProductType : typeList) {
				ContractDocTypeVo vo = new ContractDocTypeVo();
				vo.setId(""+tbProductType.getId());
				vo.setName(tbProductType.getName());
				volist.add(vo);
			}
			PageInfo<ContractDocTypeVo> pageinfo = new PageInfo<ContractDocTypeVo>();
			pageinfo.setRows(volist);
			result = JSONObject.fromObject(pageinfo);
		} catch (Exception e) {
			log.error("getProductType error",e);
		}
		return SUCCESS;
	}
	
	public String save() throws Exception {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try{
			getCommonService().save(model);
			systemLogService.writeTheLog(SystemLogType.add_tb_data_source_data, userId, "成功", null, "数据源详情ID为："+model.getId());
			result = JSONObject.fromObject(successInfo("ok"));
		}catch(Exception ex){
			log.error("syslogError:",ex);
			systemLogService.writeTheLog(SystemLogType.add_tb_data_source_data, userId, "异常", ex.getMessage(), null);
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService<TbDataRecord> getCommonService() {
		return idataRecordService;
	}

	@Override
	public TbDataRecord getModel() {
		if(null == this.model){
			this.model = new TbDataRecord();
		}
		return this.model;
	}

	@Override
	public void setModel(TbDataRecord model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
