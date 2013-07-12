package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IDataRecordService;
import cn.com.kxcomm.contractmanage.service.IDataSourceService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.ContractDocTypeVo;
import cn.com.kxcomm.contractmanage.vo.DataSourceVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class DataSourceAction extends BaseAction<TbDataSource, String> {
	private static Logger LOGGER = Logger.getLogger(DataSourceAction.class);


	@Autowired(required = true)
	private IDataSourceService idataSourceService;
	@Autowired(required=true)
	private IDataRecordService idataRecordService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	
	private String id;
	private File dataSource;  //文件
	private String dataSourceFileName; //文件名称
	private String dataSourceContentType;//文件类型
	private String typeId;
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public File getDataSource() {
		return dataSource;
	}

	public void setDataSource(File dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceFileName() {
		return dataSourceFileName;
	}

	public void setDataSourceFileName(String dataSourceFileName) {
		this.dataSourceFileName = dataSourceFileName;
	}

	public String getDataSourceContentType() {
		return dataSourceContentType;
	}

	public void setDataSourceContentType(String dataSourceContentType) {
		this.dataSourceContentType = dataSourceContentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IDataSourceService getIdataSourceService() {
		return idataSourceService;
	}

	public void setIdataSourceService(IDataSourceService idataSourceService) {
		this.idataSourceService = idataSourceService;
	}

	/**
	 * 
	 * 方法用途和描述: 获取所有的数据源
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-04
	 * @since ContractManage
	 */
	public String listDataSource() {
		try {
			PageInfo<DataSourceVo> page = new PageInfo<DataSourceVo>(ServletActionContext.getRequest());
			PageInfo<DataSourceVo> pagevo=idataSourceService.pageDataSource(page);
			result = JSONObject.fromObject(pagevo);
		} catch (Exception e) {
			LOGGER.error("listDataSource ERROR:", e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述:删除数据源
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-04
	 * @since ContractManage
	 */
	public String deleteDataSource() {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			LOGGER.debug("id:"+id);
			String[] ids = id.split(",");
			for (String strId : ids) {
				idataSourceService.deleteByPk(Integer.parseInt(strId));
				systemLogService.writeTheLog(SystemLogType.del_tb_data_source, userId, "成功", null, "数据源ID为:"+strId);
			}
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			LOGGER.error("deleteDataSource ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.del_tb_data_source, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 方法用途和描述:上传数据源
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-04
	 * @since ContractManage
	 */
	public String uploadDataSource() {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			//先保存数据源主表信息
			model.setStatus(2);
			model.setCreatetime(new Date());
			Integer id = (Integer) idataSourceService.save(model);
			XSSFWorkbook workbook = null;
			workbook = new XSSFWorkbook(new FileInputStream(dataSource));
			int rr = idataRecordService.uploadDataSource(workbook,model);
			if(rr>0){
				result = JSONObject.fromObject(successInfo("ok"));
				systemLogService.writeTheLog(SystemLogType.add_tb_data_source, userId, "成功", null, "数据源ID为:"+id);
			}else{
				result = JSONObject.fromObject(successInfo("no"));
				systemLogService.writeTheLog(SystemLogType.add_tb_data_source, userId, "失败", null, null);
			}
		} catch (Exception e) {
			LOGGER.error("uploadDataSource ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.add_tb_data_source, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法用途和描述:查询所有单元数据集合
	 * 
	 * @return
	 * @author lizl 新增日期：2012-12-12
	 * @since ContractManage
	 */
	public String queryAllDataSource() {
		try {
			PageInfo<DataSourceVo> page = new PageInfo<DataSourceVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.idataSourceService.queryAllDataSource();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("listDataSource ERROR:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取所有的数据源
	* @return
	* @author chenliang 新增日期：2013-1-10
	* @since ContractManage
	 */
	public String getAllDataSource(){
		try {
			List<TbDataSource> typeList = idataSourceService.findAll();
			List<ContractDocTypeVo> volist = new ArrayList<ContractDocTypeVo>();
			for (TbDataSource tbDataSource : typeList) {
				ContractDocTypeVo vo = new ContractDocTypeVo();
				vo.setId(""+tbDataSource.getId());
				vo.setName(tbDataSource.getName());
				volist.add(vo);
			}
			PageInfo<ContractDocTypeVo> pageinfo = new PageInfo<ContractDocTypeVo>();
			pageinfo.setRows(volist);
			result = JSONObject.fromObject(pageinfo);
		} catch (Exception e) {
			LOGGER.error("getAllDataSource ERROR",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据数据源分类获取所有的数据源
	* @return
	* @author luj 新增日期：2013-2-20
	* @since ContractManage
	 */
	public String queryAllDataSourceByType(){
		try {
			PageInfo<DataSourceVo> page = new PageInfo<DataSourceVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			System.out.println("*****************"+typeId);
			list=this.idataSourceService.queryAllDataSourceByType(Integer.parseInt(typeId));
			
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllDataSourceByType ERROR:", e);
		}
		return SUCCESS;
	}
	/**
	 * 
	* 方法用途和描述: 查看详情
	* @return
	* @author chenliang 新增日期：2012-12-11
	* @since ContractManage
	 */
	public String detailDataSource(){
		
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return idataSourceService;
	}

	@Override
	public TbDataSource getModel() {
		if(this.model==null){
			 this.model = new TbDataSource();
		}
		return this.model;
	}

	@Override
	public void setModel(TbDataSource model) {
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
