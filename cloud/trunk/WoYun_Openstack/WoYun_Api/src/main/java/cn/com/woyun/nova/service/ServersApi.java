package cn.com.woyun.nova.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.NovaClient;
import cn.com.woyun.nova.api.ServersCore;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.Server.Addresses;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.ServerForCreate;
import cn.com.woyun.nova.model.Servers;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
* 功能描述:实例管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class ServersApi {

	public static ServersApi serversApi;
	
	private ServersApi(){
	}
	
	public static ServersApi getInstance(){
		if(null == serversApi){
			serversApi = new ServersApi();
		}
		return serversApi;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取所有实例信息
	* @param access 验证对象
	* @param isDetail  是否获取实例详情
	* @return
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @param @return List<Server>
	 * @throws Exception 
	 */
	public List<Server> listServer(Access access,boolean isDetail) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Servers servers;
			servers = novaClient.execute(ServersCore.listServers(isDetail));
			return servers.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据实例id获取某个实例详情
	* @param access  验证对象
	* @param id  实例id
	* @return
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @param @return Server
	 * @throws Exception 
	 */
	public Server showServerDetail(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Server server = novaClient.execute(ServersCore.showServer(id));
			return server;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 获取服务器地址
	* @param access  验证对象
	* @param id  实例id
	* @return
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return @return Addresses
	 * @throws Exception 
	 */
	public Addresses showServerAddresses(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Addresses addresses = novaClient.execute(ServersCore.showServerAddresses(id));
			return addresses;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建实例
	* @param access  验证对象
	* @param serverForCreate 实例对象
	* @return
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return @return Server
	 * @throws Exception 
	 */ 
	public Server createServer(Access access,ServerForCreate serverForCreate) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Server server = novaClient.execute(ServersCore.createServer(serverForCreate));
			return server;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 修改实例
	* @param access 验证对象
	* @param serverForCreate 修改实例的对象
	* @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	* @since WoYun_Api
	 */
	//TODO
	public void updateServer(Access access,ServerForCreate serverForCreate,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.updateServer(serverForCreate,id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据实例id删除实例
	* @param access 验证对象
	* @param id  实例id
	* @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void deleteServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.deleteServer(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 
	* 方法用途和描述: 重启实例
	* @param access 验证对象
	* @param id  实例id
	* @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void reBootServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.reboot(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 重建服务器
	* @param access 验证对象
	* @param id  实例id
	* @param rebuild  实例重建对象
	* @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void reBuildServer(Access access,String id,Rebuild rebuild) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.rebuild(id, rebuild));
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 
	* 方法用途和描述: 暂停服务器
	* @param access 验证对象
	* @param pause 暂停对象 
	* @param id  实例id
	* @author chenliang 新增日期：2012-11-23
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void pausesServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.pausesServer(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 取消暂停服务器
	* @param access 验证对象
	* @param unpause 取消暂停对象
	* @param id 实例id
	* @author chenliang 新增日期：2012-11-23
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void unPauseServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.unPausesServer(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 启动实例
	* @param access 验证对象
	* @param start 启动
	* @param id 实例id
	* @author chenliang 新增日期：2012-11-23
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void startServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.startServer(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 停止实例
	* @param access  验证对象
	* @param stop 停止对象
	* @param id 实例id
	* @author chenliang 新增日期：2012-11-23
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void stopServer(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.stopServer(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 修改实例密码
	* @param access 验证对象
	* @param id  实例id
	* @param adminPass  新密码
	* @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	* @since WoYun_Api
	 */
	public void changePasswordServer(Access access,String id,String adminPass) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(ServersCore.changePassword(id, adminPass));
		} catch (Exception e) {
			throw e;
		}
	}
}
