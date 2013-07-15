package cn.com.woyun.nova;

import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.nova.model.QuotaSet;

public class QuotaTest extends BaseTest{
	/**
	 * 
	 * 列出配额
	 * 
	 * @author zhangjh 新增日期：2012-11-22
	 * @since WoYun_Api
	 */
//	@Test
	public void listQuotas(){
		List<QuotaSet> list;
		try {
			list = quotaApi.listQuotas(tokenLogin.getAccess());
			for (QuotaSet a : list) {
				System.out.println("[QuotaSet]:"+a.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 更新配额
	 * 
	 * @author zhangjh 新增日期：2012-11-22
	 * @since WoYun_Api
	 */
//	@Test
	public void updateQuota(){
		QuotaSet quota = new QuotaSet();
		quota.setCores(11);
		QuotaSet aa;
		try {
			aa = quotaApi.updateQuota(tokenLogin.getAccess(), quota);
			System.out.println("[QuotaSet]:"+aa.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
