package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbDataSourceType;

/**
 * 
 * 数据源分类
 * 
 * @author luj 新增日期：2013-2-20
 * @since ContractManage
 */
public interface IDataSourceTypeService extends ICommonService<TbDataSourceType>{
	public List<TbDataSourceType> queryAllDataSourceType();
}
