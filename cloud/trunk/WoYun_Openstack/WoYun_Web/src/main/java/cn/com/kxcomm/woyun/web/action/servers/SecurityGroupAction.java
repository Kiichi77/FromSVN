package cn.com.kxcomm.woyun.web.action.servers;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.SecurityGroupService;
import cn.com.kxcomm.woyun.vo.SecurityGroupVO;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;

public class SecurityGroupAction extends BaseAction<SecurityGroupVO, String> {

	private static Logger LOGGER = Logger.getLogger(SecurityGroupAction.class);
	
	@Autowired(required=true)
	private SecurityGroupService securityGroupService;
	
	private SecurityGroupRuleForCreate securityGroupRuleForCreate; //创建安全组规则的对象 
	private String serverId;  //实例id
	private Integer id;  //安全组id
	private String param; //crid获取parentId
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public SecurityGroupRuleForCreate getSecurityGroupRuleForCreate() {
		return securityGroupRuleForCreate;
	}

	public void setSecurityGroupRuleForCreate(
			SecurityGroupRuleForCreate securityGroupRuleForCreate) {
		this.securityGroupRuleForCreate = securityGroupRuleForCreate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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
	* 方法用途和描述:获取安全组集合
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	public String listSecurityGroup(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<SecurityGroupVO> volist = securityGroupService.listSecurityGroup(access);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows", volist);
				map.put("total", volist.size());
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
	* 方法用途和描述: 根据实例id查询该实例安全组
	* @return
	* @author chenliang 新增日期：2012-12-5
	* @since WoYun_Web
	 */
	public String showSecurityGroupByServerId(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<SecurityGroupVO> volist = securityGroupService.showSecurityGroupByServerId(access,serverId);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows", volist);
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("showSecurityGroupByServerId access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("showSecurityGroupByServerId error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String createSecurityGroup(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				boolean flat = securityGroupService.createSecurityGroup(access, model);
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(flat){
					map.put("msg", "ok");
				}else{
					map.put("msg", "no");
				}
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("createSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("createSecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 删除安全组
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String deleteSecurityGroup(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				boolean flat = securityGroupService.deleteSecurityGroup(access, id);
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(flat){
					map.put("msg", "ok");
				}else{
					map.put("msg", "no");
				}
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("deleteSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("deleteSecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 获取某个安全组
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String showSecurityGroup(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				SecurityGroupVO securityGroupVO = securityGroupService.showSecurityGroup(access, id);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows", securityGroupVO);
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("showSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("showSecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 获取某个安全组规则的集合
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String listSecurityGroupRule(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				SecurityGroupVO securityGroupVO = securityGroupService.showSecurityGroup(access, id);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows", securityGroupVO.getRuleList());
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("listSecurityGroupRule access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listSecurityGroupRule error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组规则
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String createSecurityGroupRule(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				if(!BlankUtil.isBlank(param)){
					int pp = Integer.parseInt(param);
					if(0==pp){
						this.securityGroupRuleForCreate.setCidr("CIDR");
					}else{
						this.securityGroupRuleForCreate.setGroupId(pp);
						this.securityGroupRuleForCreate.setCidr(null);
					}
				}
				System.out.println("securityGroupRuleForCreate:"+securityGroupRuleForCreate.toString());
				securityGroupService.createSecurityGroupRule(access, securityGroupRuleForCreate);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg", "ok");
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("createSecurityGroupRule access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("createSecurityGroupRule error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 删除安全组规则
	* @return
	* @author chenliang 新增日期：2012-12-7
	* @since WoYun_Web
	 */
	public String deleteSecurityGroupRule(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				securityGroupService.deleteSecurityGroupRule(access, id);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg", "ok");
				result = JSONObject.fromObject(map);
			}else{
				LOGGER.debug("deleteSecurityGroupRule access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("deleteSecurityGroupRule error.",e);
		}
		return successInfo(null);
	}
	
	@Override
	public CommonService getCommonService() {
		return securityGroupService;
	}

	@Override
	public SecurityGroupVO getModel() {
		if(null == this.model){
			this.model = new SecurityGroupVO();
		}
		return this.model;
	}

	@Override
	public void setModel(SecurityGroupVO model) {
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
