package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.kxcomm.woyun.vo.ServerForCreateVO;
import cn.com.kxcomm.woyun.vo.ServerVo;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.Server.Addresses;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.ServerForCreate;

/**
 * 
* 功能描述: 实例
* @author chenliang 新增日期：2012-11-27
* @since WoYun_Web
 */
public interface ServerService extends CommonService<ServerVo> {

	//获取所有实例
	public List<ServerVo> listServers(Access access,boolean isDetail) throws Exception;
	
	//根据id获取实例信息
	public ServerForCreateVO showServerDetail(Access access,String id) throws Exception;
	
	//根据id获取实例地址
	public Addresses showServerAddresses(Access access,String id) throws Exception;
	
	//创建实例
	public Server createServer(Access access,ServerForCreate serverForCreate) throws Exception;
	
	//修改实例
	public void updateServer(Access access,ServerForCreate serverForCreate,String id) throws Exception;
	
	//删除实例
	public void deleteServer(Access access,String id) throws Exception;
	
	//重启实例
	public void reBootServer(Access access,String id) throws Exception;
	
	//重建实例
	public void reBuildServer(Access access,String id,Rebuild rebuild) throws Exception;
	
	//暂停实例
	public void pausesServer(Access access,String id) throws Exception;

	//取消暂停实例
	public void unPauseServer(Access access,String id) throws Exception;
	
	//启动实例
	public void startServer(Access access,String id) throws Exception;
	
	//停止实例
	public void stopServer(Access access,String id) throws Exception;
	
	//修改实例密码
	public void changePasswordServer(Access access,String id,String adminPass) throws Exception;
}
