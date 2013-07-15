package cn.com.woyun.nova;

import java.util.List;
import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.nova.model.Flavor;
import cn.com.woyun.nova.model.FlavorForCreate;
 
public class FlavorsTest extends BaseTest {

	/**
	 * 
	* 方法用途和描述: 获取模板集合
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return 
	* @param
	 */
//	@Test
	public void listFlavors(){
		List<Flavor> flavorsList;
		try {
			flavorsList = flavorsApi.listFlavors(tokenLogin.getAccess(),false);
			for (Flavor flavor : flavorsList) {
				System.out.println("[flavor]:"+flavor.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据id获取模板
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return 
	* @param
	 */
	//@Test
	public void showFlavorsDetail(){
		Flavor flavor;
		try {
			flavor = flavorsApi.showFlavorsDetail(tokenLogin.getAccess(), "1");
			System.out.println("[flavor]:"+flavor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建模板
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return 
	* @param
	 */
//	@Test
	public void createFlavor(){
		FlavorForCreate flavorCreate = new FlavorForCreate();
		flavorCreate.setName("flavor-6");
		flavorCreate.setRam(1024);
		flavorCreate.setVcpus(2);
		flavorCreate.setDisk(10);
		flavorCreate.setId(6);
		flavorCreate.setEphemeral(30);
		flavorCreate.setRxtxFactor(2);
		flavorCreate.setSwap(5);
		try {
			flavorCreate = flavorsApi.createFlavor(tokenLogin.getAccess(), flavorCreate);
			System.out.println("[flavorForCreate]"+flavorCreate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除模板
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return 
	* @param
	 */
//	@Test
	public void deleteFlavor(){
		try {
			flavorsApi.deleteFlavor(tokenLogin.getAccess(), "12");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
