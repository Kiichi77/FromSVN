package cn.com.woyun.nova.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.NovaClient;
import cn.com.woyun.nova.api.FlavorsCore;
import cn.com.woyun.nova.model.Flavor;
import cn.com.woyun.nova.model.FlavorForCreate;
import cn.com.woyun.nova.model.Flavors;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
* 功能描述:模板api
* @author chenliang 新增日期：2012-11-21
* @since WoYun_Api
 */
public class FlavorsApi {
	private static FlavorsApi flavorsApi;
	
	private FlavorsApi(){
	}
	
	public static FlavorsApi getInstance(){
		if(null == flavorsApi){
			flavorsApi = new FlavorsApi();
		}
		return flavorsApi;
	}
	
	/**
	 * 
	 * 方法用途和描述: 获取所有的模板
	 * 
	 * @param access
	 *            验证对象
	 * @param isDetail
	 *            是否显示所有的详情
	 * @author chenliang 新增日期：2012-11-21
	 * @since WoYun_Api
	 * @return List<Flavor>
	 * @throws Exception 
	 */
	public List<Flavor> listFlavors(Access access,boolean isDetail) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Flavors flavors = novaClient.execute(FlavorsCore.listFlavors(isDetail));
			return flavors.getList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述: 根据id获取某个模板的详情
	 * 
	 * @param access
	 *            验证对象
	 * @param id
	 *            模板id
	 * @return
	 * @author chenliang 新增日期：2012-11-21
	 * @since WoYun_Api
	 * @return Flavor
	 * @throws Exception 
	 */
	public Flavor showFlavorsDetail(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			Flavor flavor = novaClient.execute(FlavorsCore.showFlavor(id));
			return flavor;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述: 创建模板
	 * 
	 * @param access
	 *            验证对象
	 * @param flavor
	 *            保存的模板对象
	 * @return
	 * @author chenliang 新增日期：2012-11-21
	 * @since WoYun_Api
	 * @return FlavorForCreate
	 * @throws Exception 
	 */
	public FlavorForCreate createFlavor(Access access,FlavorForCreate flavorForCreate) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			FlavorForCreate flavorData = novaClient.execute(FlavorsCore.createFlavor(flavorForCreate));
			return flavorData;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述: 删除模板
	 * 
	 * @param access
	 *            验证对象
	 * @param id
	 *            模板id
	 * @author chenliang 新增日期：2012-11-21
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void deleteFlavor(Access access,String id) throws Exception{
		try {
			NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.NOVA_TYPE_COMPUTE, null, Parameter.FACTING_PUBLIC), access.getToken().getId());
			novaClient.execute(FlavorsCore.deleteFlavor(id));
		} catch (Exception e) {
			throw e;
		}
	}
}
