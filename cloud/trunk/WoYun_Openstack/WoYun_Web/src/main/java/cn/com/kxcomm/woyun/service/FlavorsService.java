package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.kxcomm.woyun.vo.FlavorsVO;
import cn.com.woyun.keystone.model.Access;
/**
 * 
* 功能描述: 模板
* @author chenliang 新增日期：2012-11-27
* @since WoYun_Web
 */
public interface FlavorsService extends CommonService<FlavorsVO> {

	//获取所有模板
	public List<FlavorsVO> listFlavors(Access access,boolean isDetail) throws Exception;
	
	//根据ID删除模板
	public String deleteFlavorsById(Access access,String id);
	
	//新建模板
	public String saveFlavorInfo(Access access,FlavorsVO flavorsVO);

	//根据id查询模板信息
	public FlavorsVO showFlavorsDetail(Access access, String flavorId) throws Exception ;
}
