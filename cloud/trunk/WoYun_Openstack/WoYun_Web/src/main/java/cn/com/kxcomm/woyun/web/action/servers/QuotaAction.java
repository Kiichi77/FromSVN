package cn.com.kxcomm.woyun.web.action.servers;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.QuotaService;
import cn.com.kxcomm.woyun.vo.QuotaSetVo;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;

/**
 * 
* 功能描述:配额
* @author chenliang 新增日期：2012-12-3
* @since WoYun_Web
 */
public class QuotaAction extends BaseAction<QuotaSetVo, String> {

	private static Logger LOGGER = Logger.getLogger(QuotaAction.class);
	
	@Autowired(required=true)
	private QuotaService quotaService;
	
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
	* 方法用途和描述:获取安全组集合
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	public String listQuotaSet(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<QuotaSetVo> volist = quotaService.listQuota(access);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("volist", volist);
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
		return quotaService;
	}

	@Override
	public QuotaSetVo getModel() {
		if(null==this.model){
			this.model = new QuotaSetVo();
		}
		return this.model;
	}

	@Override
	public void setModel(QuotaSetVo model) {
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
