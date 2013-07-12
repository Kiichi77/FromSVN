package cn.com.woyun.nova.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.NovaClient;
import cn.com.woyun.nova.api.extensions.FloatingIpPoolsExtension;
import cn.com.woyun.nova.api.extensions.FloatingIpsExtension;
import cn.com.woyun.nova.model.FloatingIp;
import cn.com.woyun.nova.model.FloatingIpPools;
import cn.com.woyun.nova.model.FloatingIpPools.FloatingIpPool;
import cn.com.woyun.nova.model.FloatingIps;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
* 功能描述:浮动ip管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class FloatingIpApi {
private static FloatingIpApi floatingIpApi;
	
	private FloatingIpApi(){
	}
	
	public static FloatingIpApi getInstance(){
		if(null == floatingIpApi){
			floatingIpApi = new FloatingIpApi();
		}
		return floatingIpApi;
	}
	
	/**
	 * 
	 * 返回浮动IP池列表
	 * 
	 * @param access
	 * @param isDetail
	 * @return
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public List<FloatingIpPool> listFloatingIpPools(Access access) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			FloatingIpPools list = novaClient.execute(FloatingIpPoolsExtension.listFloatingIpPools());
			return list.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 返回浮动IP列表
	 * 
	 * @param access
	 * @return
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public List<FloatingIp> listFloatingIps(Access access) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			FloatingIps list = novaClient.execute(FloatingIpsExtension.listFloatingIps());
			return list.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 申请浮动IP
	 * 
	 * @param access
	 * @param pool
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void allocateFloatingIp(Access access,String pool) throws Exception {
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(FloatingIpsExtension.allocateFloatingIp(pool));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 释放浮动IP
	 * 
	 * @param access
	 * @param id
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void deallocateFloatingIp(Access access,String id) throws Exception {
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(FloatingIpsExtension.deallocateFloatingIp(id));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 分配浮动IP到虚拟机
	 * 
	 * @param access
	 * @param serverId
	 * @param floatingIpAddress
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void associateFloatingIp(Access access,String serverId, String floatingIpAddress) throws Exception {
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(FloatingIpsExtension.associateFloatingIp(serverId,floatingIpAddress));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 删掉虚拟机的浮动IP
	 * 
	 * @param access
	 * @param serverId
	 * @param floatingIpAddress
	 * @author zhangjh 新增日期：2012-11-23
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void disassociateFloatingIp(Access access,String serverId, String floatingIpAddress) throws Exception {
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(FloatingIpsExtension.disassociateFloatingIp(serverId,floatingIpAddress));
		} catch (Exception e) {
			throw e;
		}
	}
}
