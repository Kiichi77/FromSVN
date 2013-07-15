package cn.com.kxcomm.contractmanage.web.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbSuppliersQuotations;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.ISuppliersQuotationsService;
import cn.com.kxcomm.contractmanage.vo.SuppliersQuotationsVo;
import cn.com.kxcomm.contractmanage.web.util.FileUtil;
import cn.com.kxcomm.contractmanage.web.util.Parameters;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;

/**
 * 
* 功能描述:供应商action
* @author chenliang 新增日期：2013-3-18
* @since ContractManage
 */
public class SuppliersQuotationsAction extends BaseAction<TbSuppliersQuotations, String>{
	private static Logger LOGGER = Logger.getLogger(SuppliersQuotationsAction.class);

	@Autowired(required = true)
	private ISuppliersQuotationsService suppliersQuotationsService;
	
	private File suppliersQuotations;  //文件
	private String suppliersQuotationsFileName; //文件名
	private String suppliersQuotationsContentType; //文件类型
	private Parameters para = Parameters.getInstance();
	
	private String id; //主键id
	private List<SuppliersQuotationsVo> volist; 
	
	/**
	 * 
	* 方法用途和描述: 根据供应商id查找该供应商下的所有报价单
	* @return
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public String querySuppliersQuotationsBySuppId(){
		try {
			volist = suppliersQuotationsService.querySuppliersQuotationsBySuppId(id);
			jsonArray = JSONArray.fromObject(volist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsonArray";
	}
	
	/**
	 * 
	* 方法用途和描述: 批量删除供应商报价表
	* @return
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	public String deleteSupplierQuotations(){
		try {
			if(!BlankUtil.isBlank(id)){
				String[] pkids = id.split(",");
				Integer[] quotationIds = new Integer[pkids.length];
				for (int i = 0; i < pkids.length; i++) {
					quotationIds[i] = Integer.parseInt(pkids[i]);
				}
				suppliersQuotationsService.deleteAllByPks(quotationIds);
			}
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改供应商报价表
	* @return
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public String updateSupplierQuotations(){
		try {
			suppliersQuotationsService.updateSupplierQuotations(getModel());
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 上传供应商报价表
	* @return
	* @author chenliang 新增日期：2013-4-27
	* @since ContractManage
	 */
	public String saveSuppliersQuotations(){
		try {
			//上传供应商报价表
			//上传到服务器的文件地址
			String savePath = para.supplierQuotationsPath;
			//上传文件名
			String fileName = System.currentTimeMillis()+suppliersQuotationsFileName.substring(suppliersQuotationsFileName.lastIndexOf("."), suppliersQuotationsFileName.length());
			FileUtil.bakTemplatesDoc(suppliersQuotations, savePath, fileName, true, 0);
			getModel().setUrl(fileName);
			HttpSession session = getHttpSession();
			Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
			TbUser creator = new TbUser();
			creator.setId(userId);
			getModel().setCreator(creator);
			suppliersQuotationsService.saveSuppliersQuotations(getModel());
			result = JSONObject.fromObject(successInfo("ok"));
		} catch (Exception e) {
			e.printStackTrace();
			result = JSONObject.fromObject(successInfo("no"));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 分页查询供应商报价表
	* @return
	* @author chenliang 新增日期：2013-4-19
	* @since ContractManage
	 */
	public String listSuppliersQuotations(){
		 try {
		   PageInfo<TbSuppliersQuotations> page =  new PageInfo<TbSuppliersQuotations>(ServletActionContext.getRequest());
		   PageInfo<SuppliersQuotationsVo> pageInfo = suppliersQuotationsService.queryPage(page,getModel());
		   result = JSONObject.fromObject(pageInfo);
		} catch (Exception e) {
			LOGGER.error("listSuppliers ERROR:",e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 根据主键id查找实体对象
	* @return
	* @author chenliang 新增日期：2013-4-28
	* @since ContractManage
	 */
	public String querySuppliersById(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			SuppliersQuotationsVo vo = suppliersQuotationsService.querySuppliersById(id);
			map.put("vo", vo);
			map.put("msg", "ok");
			map.put("success", true);
			result = JSONObject.fromObject(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "no");
			map.put("success", true);
			result = JSONObject.fromObject(map);
		}
		return SUCCESS;
	}
	
	public List<SuppliersQuotationsVo> getVolist() {
		return volist;
	}

	public void setVolist(List<SuppliersQuotationsVo> volist) {
		this.volist = volist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getSuppliersQuotations() {
		return suppliersQuotations;
	}

	public void setSuppliersQuotations(File suppliersQuotations) {
		this.suppliersQuotations = suppliersQuotations;
	}

	public String getSuppliersQuotationsFileName() {
		return suppliersQuotationsFileName;
	}

	public void setSuppliersQuotationsFileName(String suppliersQuotationsFileName) {
		this.suppliersQuotationsFileName = suppliersQuotationsFileName;
	}

	public String getSuppliersQuotationsContentType() {
		return suppliersQuotationsContentType;
	}

	public void setSuppliersQuotationsContentType(
			String suppliersQuotationsContentType) {
		this.suppliersQuotationsContentType = suppliersQuotationsContentType;
	}

	@Override
	public ICommonService getCommonService() {
		return suppliersQuotationsService;
	}

	@Override
	public TbSuppliersQuotations getModel() {
		if(null==model){
			model = new TbSuppliersQuotations();
		}
		return model;
	}

	@Override
	public void setModel(TbSuppliersQuotations model) {
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
