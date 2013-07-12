package cn.com.kxcomm.woyun.web.action.servers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.FlavorsService;
import cn.com.kxcomm.woyun.vo.FlavorsVO;
import cn.com.kxcomm.woyun.vo.NowUseTentidVo;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;

public class FlavorsAction extends BaseAction<FlavorsVO, String> {

	private static Logger LOGGER = Logger.getLogger(FlavorsAction.class);
	
	@Autowired(required=true)
	private FlavorsService flavorsService;
	
	private String flavorId;
	
	public String getFlavorId() {
		return this.flavorId;
	}

	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	//用于返回前台的对象
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	/**
	 * 
	* 方法用途和描述:获取模板集合
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	public String listFlavors(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<FlavorsVO> volist = this.flavorsService.listFlavors(access,true);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows",volist);
				map.put("total",volist.size());
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("listSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listsecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述:删除模板
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	public String deleteFlavorsById() {
		System.out.println("FlavorID:   "+this.flavorId);
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.flavorsService.deleteFlavorsById(access,this.flavorId);
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述:新增模板
	* @return
	* @author chenliang 新增日期：2012-12-04
	* @since WoYun_Web
	 */
	public String saveFlavorInfo() {
		log.info("FlavorID:   "+getModel().getName());
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.flavorsService.saveFlavorInfo(access,getModel());
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 根据id查询模板信息
	* @return
	* @author chenliang 新增日期：2012-12-5
	* @since WoYun_Web
	 */
	public String showFlavorsDetail(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				FlavorsVO vo = this.flavorsService.showFlavorsDetail(access,flavorId);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows",vo);
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("listSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listsecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	@Override
	public CommonService getCommonService() {
		return flavorsService;
	}

	@Override
	public FlavorsVO getModel() {
		if(null == this.model){
			this.model = new FlavorsVO();
		}
		return this.model;
	}

	@Override
	public void setModel(FlavorsVO model) {
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
