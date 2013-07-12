package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.FloatingIp;
import cn.com.woyun.nova.model.FloatingIpPools.FloatingIpPool;

public interface FloatIpService extends CommonService<FloatingIp> {

	//获取浮动ip集合
	public List<FloatingIp> listFloatingIp(Access access) throws Exception;
	
	//配给ip
	public void setServerFloatingIp(Access access,String serverId,String ip) throws Exception;
	
	//删除ip与实例的关联
	public void disassociateFloatingIp(Access access,String serverId,String ip) throws Exception;
	
	//释放ip
	public void deallocateFloatingIp(Access access,String id) throws Exception;
	
	//申请ip
	public void allocateFloatingIp(Access access,String pool) throws Exception;
	
	//浮动ip池列表
	public List<FloatingIpPool> listFloatingIpPools(Access access) throws Exception;
}
