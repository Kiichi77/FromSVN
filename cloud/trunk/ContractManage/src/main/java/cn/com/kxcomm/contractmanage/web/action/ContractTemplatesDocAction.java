package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.com.kxcomm.contractmanage.entity.TbContractDocType;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplates;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesRelationship;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDocType;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesDocTypeService;
import cn.com.kxcomm.contractmanage.service.IContractTemplatesRelationShipService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class ContractTemplatesDocAction extends BaseAction<TbContractTemplatesDoc, String>{
	private static Logger LOGGER = Logger.getLogger(ContractTemplatesDocAction.class);
	
	@Autowired(required=true)
	private IContractTemplatesDocService contractTemplatesDocService;
	@Autowired(required=true)
	private IContractTemplatesDocTypeService contractTemplatesDocTypeService;
	@Autowired(required=true)
	private IContractTemplatesRelationShipService contractTemplatesRelationShipService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	private String contractTemplatesId;  //模板id
	private String contractTemplatesDocIds;  //合同模板文件id，用逗号分开
	private ContractTemplatesDocVo contractTemplatesDocVo;
	private String docTypeId; //模板文件类型
	private File templatesDoc;//上传文件
	private String templatesDocFileName; //文件名称
	private String templatesDocContentType;//文件类型
	private String id;
	private Parameters para = Parameters.getInstance();
	private String processId; //进程id
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractTemplatesId() {
		return contractTemplatesId;
	}

	public void setContractTemplatesId(String contractTemplatesId) {
		this.contractTemplatesId = contractTemplatesId;
	}
	
	public String getContractTemplatesDocIds() {
		return contractTemplatesDocIds;
	}

	public void setContractTemplatesDocIds(String contractTemplatesDocIds) {
		this.contractTemplatesDocIds = contractTemplatesDocIds;
	}

	public IContractTemplatesDocService getContractTemplatesDocService() {
		return contractTemplatesDocService;
	}

	public void setContractTemplatesDocService(
			IContractTemplatesDocService contractTemplatesDocService) {
		this.contractTemplatesDocService = contractTemplatesDocService;
	}
	
	public IContractTemplatesDocTypeService getContractTemplatesDocTypeService() {
		return contractTemplatesDocTypeService;
	}
	
	public void setContractTemplatesDocTypeService(
			IContractTemplatesDocTypeService contractTemplatesDocTypeService) {
		this.contractTemplatesDocTypeService = contractTemplatesDocTypeService;
	}

	public ContractTemplatesDocVo getContractTemplatesDocVo() {
		return contractTemplatesDocVo;
	}

	public void setContractTemplatesDocVo(
			ContractTemplatesDocVo contractTemplatesDocVo) {
		this.contractTemplatesDocVo = contractTemplatesDocVo;
	}

	public File getTemplatesDoc() {
		return templatesDoc;
	}

	public void setTemplatesDoc(File templatesDoc) {
		this.templatesDoc = templatesDoc;
	}

	public String getTemplatesDocFileName() {
		return templatesDocFileName;
	}

	public void setTemplatesDocFileName(String templatesDocFileName) {
		this.templatesDocFileName = templatesDocFileName;
	}

	public String getTemplatesDocContentType() {
		return templatesDocContentType;
	}

	public void setTemplatesDocContentType(String templatesDocContentType) {
		this.templatesDocContentType = templatesDocContentType;
	}

	public String getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}
	
	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 
	* 方法用途和描述: 保存合同模板与合同模板文件的关系
	* @return
	* @author chenliang 新增日期：2013-2-26
	* @since ContractManage
	 */
	public String saveRelationShip(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		log.info("contractTemplatesId:"+contractTemplatesId+",contractTemplatesDocIds:"+contractTemplatesDocIds);
		contractTemplatesRelationShipService.saveRelationShip(contractTemplatesId, contractTemplatesDocIds);
		this.result = JSONObject.fromObject(successInfo("ok"));
		systemLogService.writeTheLog(SystemLogType.save_contract_templates_doc, userId,"成功", null, "合同模版ID为："+contractTemplatesId);
		} catch (Exception e) {
			LOGGER.error("saveRelationShip ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.save_contract_templates_doc, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 
	* 方法用途和描述: 分页查询合同模板文件
	* @return
	* @author chenliang 新增日期：2013-2-26
	* @since ContractManage
	 */
	public String queryAll(){
		PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>(ServletActionContext.getRequest());
		if(BlankUtil.isBlank(contractTemplatesDocVo)){
			contractTemplatesDocVo = new ContractTemplatesDocVo();
		}
		LOGGER.info("contractTemplatesDocVo:"+contractTemplatesDocVo);
		PageInfo<ContractTemplatesDocVo> pagelist = contractTemplatesDocService.queryAll(pageInfo, contractTemplatesDocVo,true);
		this.result = JSONObject.fromObject(pagelist);
		return SUCCESS;
	}
	
	
	/**
	 * 
	* 方法用途和描述: 查询所有的合同模板文件
	* @return
	* @author chenliang 新增日期：2013-2-26
	* @since ContractManage
	 */
	public String list(){
		PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>(ServletActionContext.getRequest());
		if(BlankUtil.isBlank(contractTemplatesDocVo)){
			contractTemplatesDocVo = new ContractTemplatesDocVo();
		}
		PageInfo<ContractTemplatesDocVo> pagelist = contractTemplatesDocService.queryTemplateDoc(contractTemplatesDocVo);
		this.result = JSONObject.fromObject(pagelist);
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取某一个模版下所有的文件
	* @return	
	* @author lujia 新增日期：2012-12-10
	* @since ContractManage
	 */
	 public String listContractTemplatesDoc(){
			try {
				if(!BlankUtil.isBlank(contractTemplatesId)){
					int id = Integer.parseInt(contractTemplatesId);
					List<ContractTemplatesDocVo> volist = contractTemplatesDocService.listContractTemplatesDoc(id);
					PageInfo<ContractTemplatesDocVo> pageInfo = new PageInfo<ContractTemplatesDocVo>();
					pageInfo.setRows(volist);
					pageInfo.setMsg("ok");
					result = JSONObject.fromObject(pageInfo);
				}
			} catch (Exception e) {
				log.error("listContractTemplatesDoc error.",e);
			}
			return SUCCESS;
	   }
	 
	 /**
	 * 
	 * 方法用途和描述:删除模版里的文件
	 * 
	 * @return
	 * @author lujia 新增日期：2012-12-18
	 * @since ContractManage
	 */
		public String deleteContractTemplatesDoc() {
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			try {
				LOGGER.debug("id:"+id);
				String[] ids = id.split(",");
				for (String strId : ids) {
					int pkId = Integer.parseInt(strId);
					List<TbContractTemplatesRelationship> list = contractTemplatesRelationShipService.listByDocId(pkId);
					if(null!=list && list.size()>0){
						systemLogService.writeTheLog(SystemLogType.del_contract_templates_doc, userId, "失败", "有关联不能删除", null);
						log.info("有关联不能删除！");
					}else{
						//删除目录下的文件
						TbContractTemplatesDoc doc = contractTemplatesDocService.getByPk(pkId);
						String path = doc.getPath();
						FileUtil.deleteFile(path);
						//删除数据库中数据
						contractTemplatesDocService.deleteByPk(pkId);
						contractTemplatesDocService.ftpDeleteSync(path);
						systemLogService.writeTheLog(SystemLogType.del_contract_templates_doc, userId, "成功", null, "合同模版ID为："+pkId);
					}
				}
				result = JSONObject.fromObject(successInfo("ok"));
			} catch (Exception e) {
				LOGGER.error("deleteContractTemplatesDoc ERROR:", e);
				try {
					systemLogService.writeTheLog(SystemLogType.del_contract_templates_doc, userId, "异常", e.getMessage(), null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return SUCCESS;
		}
	
	 /**
	  * 
	 * 方法用途和描述: 保存合同模板文件，上传文件
	 * @return
	 * @author chenliang 新增日期：2012-12-18
	 * @since ContractManage
	  */
	public String saveTemplatesDoc(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(!BlankUtil.isBlank(contractTemplatesDocVo) && null!=templatesDoc){
				LOGGER.info("getModel():"+contractTemplatesDocVo.toString());
				//上传合同模板文件
				String bakPath = para.templatesWordPath; //文件上传到服务器的地址
				LOGGER.info("bakPath:"+bakPath);
				String FileName = System.currentTimeMillis()+templatesDocFileName.substring(templatesDocFileName.lastIndexOf("."), templatesDocFileName.length());//上传到服务器的文件名
				FileUtil.bakTemplatesDoc(templatesDoc, bakPath, FileName, true,0);
				contractTemplatesDocService.ftpUploadContractDocFileSync(bakPath,FileName);
				LOGGER.info("templatesDocContentType:"+templatesDocContentType);
				//保存合同模板文件
				getModel().setDocname(contractTemplatesDocVo.getDocname());  //模板名称
				getModel().setCreatetime(new Date());  //创建时间
				getModel().setPath(FileName);  //模板保存地址
				getModel().setbVariable(Integer.parseInt(contractTemplatesDocVo.getbVariable()==null?"0":contractTemplatesDocVo.getbVariable())); //是否设置变量
				TbContractTemplates templates = new TbContractTemplates();
				templates.setId(Integer.parseInt(BlankUtil.isBlank(contractTemplatesDocVo.getContractTemplatesId())?"0":contractTemplatesDocVo.getContractTemplatesId()));
//				getModel().setContractTemplates(templates);//模板总表对象
				this.save();
				systemLogService.writeTheLog(SystemLogType.add_contract_templates_doc, userId, "成功", null, "合同模版ID为："+getModel().getId());
				//保存合同文件与分类数据
				String typeId = contractTemplatesDocVo.getTypeName();
				if(!BlankUtil.isBlank(typeId)){
					String[] typeIds = typeId.split(",");
					System.out.println("typeIds.length:"+typeIds.length);
					for (String string : typeIds) {
						System.out.println("string:"+string);
						TbContractTemplatesDocType tempDocType = new TbContractTemplatesDocType();
						TbContractTemplatesDoc doc = new TbContractTemplatesDoc();
						doc.setId(model.getId());
						tempDocType.setDoc(doc);
						TbContractDocType docType = new TbContractDocType();
						docType.setId(Integer.parseInt(string));
						tempDocType.setDocType(docType);
						contractTemplatesDocTypeService.save(tempDocType);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("saveTemplatesDoc error.",e);
			try {
				systemLogService.writeTheLog(SystemLogType.add_contract_templates_doc, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改合同模板
	* @return
	* @author chenliang 新增日期：2012-12-19
	* @since ContractManage
	 */
	public String updateTemplatesDoc(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
			if(!BlankUtil.isBlank(contractTemplatesDocVo)) {
				//修改合同模板文件
				contractTemplatesDocService.updateTemplatesDoc(contractTemplatesDocVo);
				//修改合同模板与合同文件类型之间的关系
				contractTemplatesDocTypeService.updateTemplateDocType(contractTemplatesDocVo);
				systemLogService.writeTheLog(SystemLogType.edit_contract_templates_doc, userId, "成功", null, "合同模版ID为："+contractTemplatesDocVo.getId());
				LOGGER.info("修改完成！");
				result = JSONObject.fromObject(successInfo("ok"));
			}
		} catch (Exception e) {
			LOGGER.error("updateTemplatesDoc",e);
			try {
				systemLogService.writeTheLog(SystemLogType.edit_contract_templates_doc, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据id查询
	* @author chenliang 新增日期：2012-12-19
	* @since ContractManage
	 */
	public String findById(){
		try {
			if(!BlankUtil.isBlank(id)){
				Integer pkid = Integer.parseInt(id);
				PageInfo<ContractTemplatesDocVo> pageinfo = contractTemplatesDocService.getTemplatesDocByPk(pkid);
				result = JSONObject.fromObject(pageinfo);
			}
		} catch (Exception e) {
			LOGGER.error("findById error.",e);
		}
		return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return contractTemplatesDocService;
	}

	@Override
	public TbContractTemplatesDoc getModel() {
		if (this.model == null) {
			this.model = new TbContractTemplatesDoc();
		}
		return this.model;
	}

	@Override
	public void setModel(TbContractTemplatesDoc model) {
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
