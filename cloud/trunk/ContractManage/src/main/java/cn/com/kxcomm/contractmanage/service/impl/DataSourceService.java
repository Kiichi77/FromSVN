package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.DataSourceDAO;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.service.IDataSourceService;
import cn.com.kxcomm.contractmanage.vo.DataSourceVo;

@Service("dataSourceService")
public class DataSourceService  extends
CommonService<TbDataSource> implements IDataSourceService {
	@Autowired(required=true)
	private DataSourceDAO dataSourceDAO;
	
	@Override
	public CommonDAO<TbDataSource> getBindDao() {
		return dataSourceDAO;
	}
	
	//分页查询所有的数据源
	@Override
	public PageInfo<DataSourceVo> pageDataSource(PageInfo<DataSourceVo> pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select s.id,s.createtime,s.description,s.name,s.status,t.type_name from tb_data_source s,tb_data_source_type t WHERE s.type_id=t.type_id ");
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
		int count = dataSourceDAO.findCount(countSql.toString());
		List list = dataSourceDAO.findByPage(sql.toString(), pageInfo);
		List<DataSourceVo> listDataSourceVo=new ArrayList<DataSourceVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			DataSourceVo dataSourceVo=new DataSourceVo();
			dataSourceVo.setId(obj[0].toString());
			dataSourceVo.setCreatetime(obj[1].toString());
			dataSourceVo.setDescription(obj[2].toString());
			dataSourceVo.setName(obj[3].toString());
			dataSourceVo.setStatus(obj[4].toString());
			dataSourceVo.setTypeName(obj[5].toString());
			listDataSourceVo.add(dataSourceVo);
		}
		PageInfo<DataSourceVo> page = new PageInfo<DataSourceVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listDataSourceVo);
		return page;
	}
	
	//查询所有的数据源
	@Override
	public List<DataSourceVo> queryAllDataSource() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,createtime,description,name,status from tb_data_source ");
		List list = this.dataSourceDAO.queryAllDataSource(sql.toString());
		List<DataSourceVo> listDataSourceVo = new ArrayList<DataSourceVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			DataSourceVo dataSourceVo=new DataSourceVo();
			dataSourceVo.setId(obj[0].toString());
			dataSourceVo.setCreatetime(obj[1].toString());
			dataSourceVo.setDescription(obj[2].toString());
			dataSourceVo.setName(obj[3].toString());
			dataSourceVo.setStatus(obj[4].toString());
			listDataSourceVo.add(dataSourceVo);
		}
		return listDataSourceVo;
	}
	
	//根据数据源分类查询数据源
	@Override
	public List<DataSourceVo> queryAllDataSourceByType(int id) 
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select id,createtime,description,name,status from tb_data_source where type_id=? ");
		List list = this.dataSourceDAO.queryAllDataSource(sql.toString(),id);
		List<DataSourceVo> listDataSourceVo = new ArrayList<DataSourceVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			DataSourceVo dataSourceVo=new DataSourceVo();
			dataSourceVo.setId(obj[0].toString());
			dataSourceVo.setCreatetime(obj[1].toString());
			dataSourceVo.setDescription(obj[2].toString());
			dataSourceVo.setName(obj[3].toString());
			dataSourceVo.setStatus(obj[4].toString());
			listDataSourceVo.add(dataSourceVo);
		}
		return listDataSourceVo;
	}
}
