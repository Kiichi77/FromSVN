package cn.com.kxcomm.woyun.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.woyun.service.FloatIpService;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.FloatingIp;
import cn.com.woyun.nova.model.FloatingIpPools.FloatingIpPool;
import cn.com.woyun.nova.service.FloatingIpApi;

/**
 * 
* 功能描述:浮动ip实现类
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("floatIpService")
public class FloatIpServiceImpl extends
		CommonServiceImpl<FloatingIp> implements FloatIpService {

	private FloatingIpApi floatingIpApi = FloatingIpApi.getInstance();
	
	@Override
	public List<FloatingIp> listFloatingIp(Access access) throws Exception {
		try {
			List<FloatingIp> floatingipList = this.floatingIpApi.listFloatingIps(access);
			return floatingipList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void setServerFloatingIp(Access access, String serverId,
			String ip) throws Exception {
		try {
			this.floatingIpApi.associateFloatingIp(access, serverId, ip);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void disassociateFloatingIp(Access access,String serverId,String ip)
			throws Exception {
		try {
			this.floatingIpApi.disassociateFloatingIp(access, serverId, ip);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deallocateFloatingIp(Access access, String id) throws Exception {
		try {
			this.floatingIpApi.deallocateFloatingIp(access, id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void allocateFloatingIp(Access access, String pool) throws Exception {
		try {
			this.floatingIpApi.allocateFloatingIp(access, pool);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FloatingIpPool> listFloatingIpPools(Access access) throws Exception {
		try {
			List<FloatingIpPool> floatingIpPoolList = this.floatingIpApi.listFloatingIpPools(access);
			return floatingIpPoolList;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
