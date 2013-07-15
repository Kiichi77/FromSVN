package cn.com.kxcomm.woyun.web.action.system;

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
import cn.com.kxcomm.woyun.service.ServicesService;
import cn.com.kxcomm.woyun.vo.FlavorsVO;
import cn.com.kxcomm.woyun.vo.ServicesVO;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;

public class ServicesAction extends BaseAction<ServicesVO, String> {

	private static Logger LOGGER = Logger.getLogger(ServicesAction.class);
	
	@Autowired(required=true)
	private ServicesService servicesService;
	
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
	* 方法用途和描述:获取服务集合
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	public String listServices(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<ServicesVO> volist = this.servicesService.listImages(access.getToken().getId());
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
	
	@Override
	public CommonService getCommonService() {
		return servicesService;
	}

	@Override
	public ServicesVO getModel() {
		if(null == this.model){
			this.model = new ServicesVO();
		}
		return this.model;
	}

	@Override
	public void setModel(ServicesVO model) {
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
