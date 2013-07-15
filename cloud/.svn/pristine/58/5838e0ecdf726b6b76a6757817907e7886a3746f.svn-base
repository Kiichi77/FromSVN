package cn.com.kxcomm.woyun.web.action.servers;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.FloatIpService;
import cn.com.kxcomm.woyun.vo.JsonBean;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.FloatingIp;
import cn.com.woyun.nova.model.FloatingIpPools.FloatingIpPool;

public class FloatIpAction extends BaseAction<FloatingIp, String> {

	private static Logger LOGGER = Logger.getLogger(FloatIpAction.class);
	
	@Autowired(required=true)
	private FloatIpService floatIpService;
	
	private String serverId;  //实例id
	private String id;    //浮动ip的id
	private String ip;    //浮动ip
	private String pools; //储备池
	private String json; //json字符串
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPools() {
		return pools;
	}

	public void setPools(String pools) {
		this.pools = pools;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	* 方法用途和描述: 获取所有的浮动ip集合
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String listFloatingIp(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<FloatingIp> volist = this.floatIpService.listFloatingIp(access);
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
	* 方法用途和描述: 获取所有的浮动ip池集合
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String listFloatingIpPools(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<FloatingIpPool> volist = this.floatIpService.listFloatingIpPools(access);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows",volist);
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
	* 方法用途和描述: 配给ip给实例
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String setFloatIpServer(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				this.floatIpService.setServerFloatingIp(access, serverId, ip);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg","ok");
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
	* 方法用途和描述: 删除ip与实例的关联
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String disassociateFloatingIp(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				this.floatIpService.disassociateFloatingIp(access, serverId, ip);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg","ok");
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("DeallocatesFloatingIp access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("DeallocatesFloatingIp error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 释放ip
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String deallocateFloatingIp(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				this.floatIpService.deallocateFloatingIp(access, id);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg","ok");
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("DeallocatesFloatingIp access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("DeallocatesFloatingIp error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述:  申请ip
	* @return
	* @author chenliang 新增日期：2012-12-6
	* @since WoYun_Web
	 */
	public String allocateFloatingIp(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				this.floatIpService.allocateFloatingIp(access, pools);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg","ok");
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("DeallocatesFloatingIp access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("DeallocatesFloatingIp error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 批量释放浮动ip
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String batchLiberateIp(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				if(!BlankUtil.isBlank(json)){
					JSONArray jsonArray = JSONArray.fromObject(json);
					List<JsonBean> jsonBeanList = JSONArray.toList(jsonArray,JsonBean.class);
					for (JsonBean jsonBean : jsonBeanList) {
						System.out.println("jsonbean:"+jsonBean.toString());
						//当serverid与ip不为空的时候，该ip已经绑定了实例，此时释放浮动ip是指删除ip与实例的关联。
						if(!BlankUtil.isBlank(jsonBean.getServerId()) && !BlankUtil.isBlank(jsonBean.getIp())){
							this.floatIpService.disassociateFloatingIp(access, jsonBean.getServerId(),jsonBean.getIp());
						}else if(!BlankUtil.isBlank(jsonBean.getId())){  //如果serverid或者ip为空，则浮动ip未与实例关联，则直接删除。
							this.floatIpService.deallocateFloatingIp(access, jsonBean.getId());
						}
					}
				}else{
					LOGGER.debug("batchLiberateIp json is null.");
				}
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg","ok");
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("DeallocatesFloatingIp access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("DeallocatesFloatingIp error.",e);
		}
		return successInfo(null);
	}
	
	@Override
	public CommonService getCommonService() {
		return floatIpService;
	}

	@Override
	public FloatingIp getModel() {
		if(null == this.model){
			this.model = new FloatingIp();
		}
		return this.model;
	}

	@Override
	public void setModel(FloatingIp model) {
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
