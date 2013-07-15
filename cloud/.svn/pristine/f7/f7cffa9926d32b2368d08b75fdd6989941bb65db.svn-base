package cn.com.kxcomm.woyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.woyun.service.FlavorsService;
import cn.com.kxcomm.woyun.vo.FlavorsVO;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.nova.model.Flavor;
import cn.com.woyun.nova.model.FlavorForCreate;
import cn.com.woyun.nova.service.FlavorsApi;

/**
 * 
* 功能描述:模板实现类
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("flavorsService")
public class FlavorsServiceImpl extends
		CommonServiceImpl<FlavorsVO> implements FlavorsService {

	private FlavorsApi flavorsApi = FlavorsApi.getInstance();
	
	@Override
	public List<FlavorsVO> listFlavors(Access access,boolean isDetail) throws Exception {
		try {
			List<Flavor> flavorsList = this.flavorsApi.listFlavors(access, isDetail);
			List<FlavorsVO> volist = new ArrayList<FlavorsVO>();
			for (Flavor flavor : flavorsList) {
				FlavorsVO vo = new FlavorsVO();
				vo.setId(flavor.getId());
				vo.setName(flavor.getName());
				vo.setDisk(flavor.getDisk());
				vo.setEphemeral(flavor.getEphemeral());
				vo.setRam(flavor.getRam());
				vo.setVcpus(flavor.getVcpus());
				volist.add(vo);
			}
			return volist;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	* 方法用途和描述:删除模板
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	@Override
	public String deleteFlavorsById(Access access,String id) {
		String end = "ok";
		try {
			if(0 > id.indexOf(",")) {
				this.flavorsApi.deleteFlavor(access,id);
			} else {
				String[] user = id.split(",");
				for(int i = 0;i < user.length; i++) {
					this.flavorsApi.deleteFlavor(access,user[i]);
				}
			}
		} catch (Exception exception) {
			 end = "error";
		}
		return end;
	}
	
	/**
	 * 
	* 方法用途和描述:新增模板
	* @return
	* @author chenliang 新增日期：2012-12-04
	* @since WoYun_Web
	 */
	@Override
	public String saveFlavorInfo(Access access,FlavorsVO flavorsVO) {
		String end = "ok";
		FlavorForCreate flavorForCreate = new FlavorForCreate();
		flavorForCreate.setDisk(Integer.parseInt(flavorsVO.getDisk()));
		flavorForCreate.setId(Integer.parseInt(flavorsVO.getId()));
		flavorForCreate.setEphemeral(flavorsVO.getEphemeral());
		flavorForCreate.setName(flavorsVO.getName());
		flavorForCreate.setRam(flavorsVO.getRam());
		flavorForCreate.setVcpus(Integer.parseInt(flavorsVO.getVcpus()));
		try {
			this.flavorsApi.createFlavor(access,flavorForCreate);
		} catch (Exception exception) {
			end = "error";
			exception.printStackTrace();
		}
		return end;
	}

	@Override
	public FlavorsVO showFlavorsDetail(Access access, String flavorId) throws Exception {
		Flavor flavor = flavorsApi.showFlavorsDetail(access, flavorId);
		FlavorsVO vo = new FlavorsVO();
		vo.setId(flavor.getId());
		vo.setName(flavor.getName());
		vo.setDisk(flavor.getDisk());
		vo.setEphemeral(flavor.getEphemeral());
		vo.setRam(flavor.getRam());
		vo.setVcpus(flavor.getVcpus());
		return vo;
	}
}
