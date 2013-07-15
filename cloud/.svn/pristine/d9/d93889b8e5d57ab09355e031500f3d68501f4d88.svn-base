package cn.com.kxcomm.woyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.woyun.service.QuotaService;
import cn.com.kxcomm.woyun.vo.QuotaSetVo;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.QuotaSet;
import cn.com.woyun.nova.service.QuotaApi;

/**
 * 
* 功能描述:模板实现类
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("quotaService")
public class QuotaServiceImpl extends
		CommonServiceImpl<QuotaSetVo> implements QuotaService {

	private QuotaApi quotaApi = QuotaApi.getInstance();
	
	@Override
	public List<QuotaSetVo> listQuota(Access access)
			throws Exception {
		try {
			List<QuotaSet> quotaSetlist = quotaApi.listQuotas(access);
			List<QuotaSetVo> voList = new ArrayList<QuotaSetVo>();
			for (QuotaSet quotaSet : quotaSetlist) {
				QuotaSetVo vo = new QuotaSetVo();
				vo.setId(quotaSet.getId());
				vo.setCores(quotaSet.getCores());
				vo.setFloatingIps(quotaSet.getFloatingIps());
				vo.setGigabytes(quotaSet.getGigabytes());
				vo.setInjectedFileContentBytes(quotaSet.getInjectedFileContentBytes());
				vo.setInjectedFile(quotaSet.getInjectedFiles());
				vo.setInstance(quotaSet.getInstances());
				vo.setMetadataItems(quotaSet.getMetadataItems());
				vo.setRam(quotaSet.getRam());
				vo.setSecurityGroupRules(quotaSet.getSecurityGroupRules());
				vo.setSecurityGroup(quotaSet.getSecurityGroups());
				vo.setVolumes(quotaSet.getVolumes());
				voList.add(vo);
			}  
			return voList;
		} catch (Exception e) {
			throw e;
		}
	}

	

}
