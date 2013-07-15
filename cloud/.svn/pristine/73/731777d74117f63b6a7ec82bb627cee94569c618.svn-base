package cn.com.kxcomm.woyun.web.action.servers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.ServerService;
import cn.com.kxcomm.woyun.vo.ServerForCreateVO;
import cn.com.kxcomm.woyun.vo.ServerVo;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.ServerForCreate;
import cn.com.woyun.nova.model.Server.Addresses;
import cn.com.woyun.nova.model.ServerForCreate.SecurityGroup;

public class ServersAction extends BaseAction<ServerVo, String> {

	private static Logger LOGGER = Logger.getLogger(ServersAction.class);
	
	@Autowired(required=true)
	private ServerService serverService;
	//用于返回前台的对象
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	//实例id
	public String serverId;
	//实例地址
	public Addresses addresses;
	//创建实例用的实例对象
	public ServerForCreate serverForCreate;
	//重建实例的对象
	public Rebuild rebuild;
	
	public Rebuild getRebuild() {
		return rebuild;
	}

	public void setRebuild(Rebuild rebuild) {
		this.rebuild = rebuild;
	}

	public ServerForCreate getServerForCreate() {
		return serverForCreate;
	}

	public void setServerForCreate(ServerForCreate serverForCreate) {
		this.serverForCreate = serverForCreate;
	}

	public Addresses getAddresses() {
		return addresses;
	}

	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * 
	* 方法用途和描述: 获取所有的实例
	* @return
	* @author chenliang 新增日期：2012-11-27
	* @since WoYun_Web
	 */
	public String listServers(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<ServerVo> serverList = serverService.listServers(access, false);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows", serverList);
				map.put("total", serverList.size());
				result = JSONObject.fromObject(map);
				LOGGER.info("serverList.size:"+serverList.size()+",result:"+result.toString());
				return successInfo(null);
			}else{
				LOGGER.info("listServers access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listServers ERROR:",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 获取某个实例详情
	* @return
	* @author chenliang 新增日期：2012-11-27
	* @since WoYun_Web
	 */
	public String showServerDetails(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					ServerForCreateVO serverForCreateVO = serverService.showServerDetail(access, this.serverId);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("model", serverForCreateVO);
					result = JSONObject.fromObject(map);
					return successInfo(null);
				}else{
					LOGGER.debug(" showServerDetailsaccess is null.");
				}
			}else{
				LOGGER.debug("showServerDetails isAuthen is error.");
			}
		} catch (Exception e) {
			LOGGER.error("showServerDetails ERROR:",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述:获取某个实例的地址
	* @return
	* @author chenliang 新增日期：2012-11-27
	* @since WoYun_Web
	 */
	public String showServerAddresses(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					this.addresses = serverService.showServerAddresses(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("showServerAddresses access is null.");
				}
			}else{
				LOGGER.debug("showServerAddresses isAuthen is error.");
			}
		} catch (Exception e) {
			LOGGER.error("showServerAddresses ERROR.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 创建实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String createServer(){
		ServerVo servervo = null;
		try {
			LOGGER.info("serverForCreate.toString():"+serverForCreate.toString());
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				if(!BlankUtil.isBlank(this.getIds())){
					List<SecurityGroup> securityGrouplist = new ArrayList<ServerForCreate.SecurityGroup>();
					String[] groupIds = ids[0].split(",");  //每次都只有第一个有值，且以“，”分开
					for (String string : groupIds) {
						SecurityGroup securityGroup = new SecurityGroup();
						securityGroup.setName(string);
						securityGrouplist.add(securityGroup);
					}
					this.serverForCreate.setSecurityGroups(securityGrouplist);
				}
				Server server = serverService.createServer(access, this.serverForCreate);
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(!BlankUtil.isBlank(server)){
					map.put("msg", "ok");
				}else{
					map.put("msg", "no");
				}
				result = JSONObject.fromObject(map);
				return successInfo(null);
			}else{
				LOGGER.debug("createServer access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("createServer ERROR.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 修改实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String updateServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.updateServer(access, serverForCreate, this.serverId);
				}else{
					LOGGER.debug("updateServer access is null.");
				}
			}else{
				LOGGER.debug("updateServer tenantId is null");
			}
		} catch (Exception e) {
			LOGGER.error("updateServer error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 删除实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String deleteServer(){
		try {
			System.out.println("serverId"+Json.toJson(serverId));
			if(!BlankUtil.isBlank(serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					String[] serverIds = serverId.split(",");
					for (String strId : serverIds) {
						serverService.deleteServer(access, strId);
					}
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("msg", "ok");
					result = JSONObject.fromObject(map);
					return successInfo(null);
				}else{
					LOGGER.debug("deleteServer access is null.");
				}
			}else{
				LOGGER.debug("deleteServer tentantid or serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("deleteServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 重启实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String reBootServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.reBootServer(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("rebootServer access is null.");
				}
			}else{
				LOGGER.debug("rebootServer serverid is null.");
			}
		} catch (Exception e) {
			LOGGER.error("reBootServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 重建实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String reBuildServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.reBuildServer(access, this.serverId, rebuild);
					return successInfo(null);
				}else{
					LOGGER.debug("reBuildServer access is null.");
				}
			}else{
				LOGGER.debug("reBuildServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("reBuildServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 暂停实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String pausesServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.pausesServer(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("pausesServer access is null.");
				}
			}else{
				LOGGER.debug("pausesServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("pausesServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 取消暂停实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	@JSON(serialize=false)
	public String unPausesServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.unPauseServer(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("unPausesServer serverid is null.");
				}
			}else{
				LOGGER.debug("unPausesServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.debug("unPausesServer error.");
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 开始实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String startServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.startServer(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("startServer access is null.");
				}
			}else{
				LOGGER.debug("startServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("startServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 停止实例
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String stopServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.stopServer(access, this.serverId);
					return successInfo(null);
				}else{
					LOGGER.debug("stopServer access is null.");
				}
			}else{
				LOGGER.debug("stopServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("stopServer error.",e);
		}
		return successInfo("no");
	}
	
	/**
	 * 
	* 方法用途和描述: 修改实例密码
	* @return
	* @author chenliang 新增日期：2012-11-28
	* @since WoYun_Web
	 */
	public String changePasswordServer(){
		try {
			if(!BlankUtil.isBlank(this.serverId)){
				Access access = getAccess();
				if(!BlankUtil.isBlank(access)){
					serverService.changePasswordServer(access, this.serverId,getModel().getAdminPass());
					return successInfo(null);
				}else{
					LOGGER.debug("changePasswordServer access is null.");
				}
			}else{
				LOGGER.debug("changePasswordServer serverId is null.");
			}
		} catch (Exception e) {
			LOGGER.error("changePasswordServer error.",e);
		}
		return successInfo("no");
	}
	
	@Override
	public CommonService getCommonService() {
		return serverService;
	}

	@Override
	public ServerVo getModel() {
		if(null == this.model){
			this.model = new ServerVo();
		}
		return model;
	}

	@Override
	public void setModel(ServerVo model) {
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
