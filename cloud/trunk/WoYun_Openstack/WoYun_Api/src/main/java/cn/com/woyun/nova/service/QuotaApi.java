package cn.com.woyun.nova.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.NovaClient;
import cn.com.woyun.nova.api.QuotaCore;
import cn.com.woyun.nova.model.QuotaSet;
import cn.com.woyun.nova.model.QuotaSets;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
 * 配额的管理
 * 
 * @author zhangjh 新增日期：2012-11-22
 * @since WoYun_Api
 */
public class QuotaApi {
	private static QuotaApi quotaApi = null;
	private QuotaApi(){
	}
	
	public static QuotaApi getInstance(){
		if(null==quotaApi){
			quotaApi = new QuotaApi();
		}
		return quotaApi;
	}
	
	/**
	 * 
	 * 某租户下配额的列表
	 * 
	 * @param access
	 * @param tenantId
	 * @return
	 * @author zhangjh 新增日期：2012-11-22
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public List<QuotaSet> listQuotas(Access access) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			QuotaSets quotas;
			quotas = novaClient.execute(QuotaCore.listQuotas(access.getToken().getTenant().getId()));
			return quotas.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 更新配额
	 * 
	 * @param access
	 * @param quota
	 * @return
	 * @author zhangjh 新增日期：2012-11-22
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public QuotaSet updateQuota(Access access,QuotaSet quota) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			QuotaSet r;
			r = novaClient.execute(QuotaCore.updateQuota(quota, access.getToken().getTenant().getId()));
			return r;
		} catch (Exception e) {
			throw e;
		}
	}

}
