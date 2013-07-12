package cn.com.kxcomm.woyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.ServerService;
import cn.com.kxcomm.woyun.vo.AddressVO;
import cn.com.kxcomm.woyun.vo.FlavorsVO;
import cn.com.kxcomm.woyun.vo.ImageVO;
import cn.com.kxcomm.woyun.vo.ServerForCreateVO;
import cn.com.kxcomm.woyun.vo.ServerVo;
import cn.com.woyun.glance.model.Image;
import cn.com.woyun.glance.service.GlanceApi;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.Server.Addresses;
import cn.com.woyun.nova.model.Server.Addresses.Address;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.ServerForCreate;
import cn.com.woyun.nova.service.ServersApi;

/**
 * 
* 功能描述: 实例业务处理类
* @author chenliang 新增日期：2012-11-27
* @since WoYun_Web
 */
@Service("serverService")
public class ServersServiceImpl extends CommonServiceImpl<ServerVo> implements
		ServerService {
	
	public ServersApi serversApi = ServersApi.getInstance();
	public GlanceApi glanceApi = GlanceApi.getInstance();

	@Override
	public List<ServerVo> listServers(Access access,boolean isDetail) throws Exception {
		List<Server> serverList = serversApi.listServer(access, true);
		List<ServerVo> serverVoList = new ArrayList<ServerVo>();
		for (Server server : serverList) {
			List<Address> privateIPlist = server.getAddresses().getPrivateList();
			List<Address> publicIPlist = server.getAddresses().getPublicList();
			ServerVo servervo = new ServerVo();
			servervo.setServerId(server.getId());
			servervo.setName(server.getName());
			servervo.setAdminPass(server.getAdminPass());
			servervo.setState(server.getStatus());
			if(null!=privateIPlist && privateIPlist.size()>0){
				servervo.setPrivateIP(privateIPlist.get(0).getAddr());
			}
			if(null!=publicIPlist && publicIPlist.size()>0){
				servervo.setPublicIP(publicIPlist.get(0).getAddr());
			}
			servervo.setInstanceName(server.getInstanceName());
			servervo.setWorkState(server.getTaskState()==null?"None":server.getTaskState());
			servervo.setProgress(server.getProgress());
			servervo.setPowerState(server.getPowerState());
			servervo.setVmState(server.getVmState());
			servervo.setUpdatedTime(server.getUpdated());
			servervo.setCreatedTime(server.getCreated());
			servervo.setUserId(server.getUserId());
			servervo.setTenantId(server.getTenantId());
			serverVoList.add(servervo);
		}
		return serverVoList;
	}

	@Override
	public ServerForCreateVO showServerDetail(Access access, String id) throws Exception {
		Server server = serversApi.showServerDetail(access, id);
		ServerForCreateVO serverForCreateVO = new ServerForCreateVO();
		serverForCreateVO.setServerId(server.getId());
		serverForCreateVO.setName(server.getName());
		serverForCreateVO.setAdminPass(server.getAdminPass());
		serverForCreateVO.setImageRef(server.getImage().getId());
		serverForCreateVO.setImageName(getImage(access, server.getImage().getId()).getName());
		serverForCreateVO.setFlavorRef(server.getFlavor().getId());
		serverForCreateVO.setAccessIPv4(server.getAccessIPv4());
		serverForCreateVO.setAccessIPv6(server.getAccessIPv6());
		serverForCreateVO.setMax(server.getProgress());
		serverForCreateVO.setDiskConfig(server.getDiskConfig());
		serverForCreateVO.setKeyName(server.getKeyName()==null?"None":server.getKeyName());
		serverForCreateVO.setSecurityGroupList(server.getSecurityGroups());
		serverForCreateVO.setState(server.getStatus());
		
		List<Address> addressList = server.getAddresses().getBr100();
		if(!BlankUtil.isBlank(addressList)){
			List<AddressVO> voList = new ArrayList<AddressVO>();
			for (Address address : addressList) {
				AddressVO addressvo = new AddressVO();
				addressvo.setAddr(address.getAddr());
				addressvo.setVersion(address.getVersion());
				voList.add(addressvo);
			}
			serverForCreateVO.setAddresses(voList);
		}
		FlavorsVO flavorsVo = new FlavorsVO();
		flavorsVo.setDisk(server.getFlavor().getDisk());
		flavorsVo.setEphemeral(server.getFlavor().getEphemeral());
		flavorsVo.setId(server.getFlavor().getId());
		flavorsVo.setName(server.getFlavor().getName());
		flavorsVo.setRam(server.getFlavor().getRam());
		flavorsVo.setVcpus(server.getFlavor().getVcpus());
		serverForCreateVO.setFlavor(flavorsVo);
		return serverForCreateVO;
	}
	
	/**
	 * 
	* 方法用途和描述:根据镜像id获取镜像详情
	* @param access
	* @param imageId
	* @return
	* @throws Exception
	* @author chenliang 新增日期：2012-12-5
	* @since WoYun_Web
	 */
	public Image getImage(Access access,String imageId) throws Exception{
		Image image = glanceApi.showImage(access, imageId);
		if(!BlankUtil.isBlank(image)){
			return image;
		}
		return new Image();
	}

	@Override
	public Addresses showServerAddresses(Access access, String id)
			throws Exception {
		return serversApi.showServerAddresses(access, id);
	}

	@Override
	public Server createServer(Access access, ServerForCreate serverForCreate)
			throws Exception {
		if(!BlankUtil.isBlank(serverForCreate)){
			Server server = serversApi.createServer(access, serverForCreate);
			return server;
		}else{
			return null;
		}
	}

	@Override
	public void updateServer(Access access, ServerForCreate serverForCreate,
			String id) throws Exception {
		serversApi.updateServer(access, serverForCreate, id);
	}

	@Override
	public void deleteServer(Access access, String id) throws Exception {
		serversApi.deleteServer(access, id);
	}

	@Override
	public void reBootServer(Access access, String id) throws Exception {
		serversApi.reBootServer(access, id);
	}

	@Override
	public void reBuildServer(Access access, String id, Rebuild rebuild)
			throws Exception {
		serversApi.reBuildServer(access, id, rebuild);
	}

	@Override
	public void pausesServer(Access access, String id) throws Exception {
		serversApi.pausesServer(access, id);
	}

	@Override
	public void unPauseServer(Access access, String id) throws Exception {
		serversApi.unPauseServer(access, id);
	}

	@Override
	public void startServer(Access access, String id) throws Exception {
		serversApi.startServer(access, id);
	}

	@Override
	public void stopServer(Access access, String id) throws Exception {
		serversApi.stopServer(access, id);
	}

	@Override
	public void changePasswordServer(Access access, String id, String adminPass)
			throws Exception {
		serversApi.changePasswordServer(access, id, adminPass);
	}

}
