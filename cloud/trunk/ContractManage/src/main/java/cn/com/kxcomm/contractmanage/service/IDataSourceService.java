package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.vo.DataSourceVo;

/**
 * 
 * 数据源总表
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IDataSourceService extends ICommonService<TbDataSource>{

	public PageInfo<DataSourceVo> pageDataSource(PageInfo<DataSourceVo> pageInfo);
	
	public List<DataSourceVo> queryAllDataSource();
	
	public List<DataSourceVo> queryAllDataSourceByType(int id);
}
