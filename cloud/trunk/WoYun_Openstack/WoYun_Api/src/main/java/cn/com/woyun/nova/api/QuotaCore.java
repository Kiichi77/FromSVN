package cn.com.woyun.nova.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.com.woyun.nova.NovaCommand;
import cn.com.woyun.nova.model.QuotaSet;
import cn.com.woyun.nova.model.QuotaSets;

public class QuotaCore {
	/**
	 * 
	 * 返回某个租户下的配额
	 * 
	 * @author zhangjh 新增日期：2012-11-22
	 * @since WoYun_Api
	 */
	public static class ListQuotas implements NovaCommand<QuotaSets>{
		private String tenantId;
		
		public ListQuotas(String tenantId) {
			this.tenantId = tenantId;
		}
		public ListQuotas() {
		}

		@Override
		public QuotaSets execute(WebTarget target) throws Exception {
			try {
				String path = "os-quota-sets";
				return target.path(path).path(tenantId).request(MediaType.APPLICATION_JSON).get(QuotaSets.class);
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	/**
	 * 
	 * 更新某租户下的配额
	 * 
	 * @author zhangjh 新增日期：2012-11-22
	 * @since WoYun_Api
	 */
	public static class UpdateQuota implements NovaCommand<QuotaSet> {

		private QuotaSet quota;
		private String tenantId;
		
		public UpdateQuota(QuotaSet quota,String tenantId) {
			this.quota = quota;
			this.tenantId = tenantId;
		}

		@Override
		public QuotaSet execute(WebTarget target) throws Exception {
			try {
				return target.path("os-quota-sets").path(tenantId).request(MediaType.APPLICATION_JSON).put(Entity.json(quota), QuotaSet.class);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	
	public static ListQuotas listQuotas(String tenantId) throws Exception {
		return new ListQuotas(tenantId);
	}
	
	public static UpdateQuota updateQuota(QuotaSet quota,String tenantId) throws Exception {
		return  new UpdateQuota(quota,tenantId);
	}
}
