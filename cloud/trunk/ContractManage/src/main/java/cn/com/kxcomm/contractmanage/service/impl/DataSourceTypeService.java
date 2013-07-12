package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.DataSourceTypeDAO;
import cn.com.kxcomm.contractmanage.entity.TbDataSourceType;
import cn.com.kxcomm.contractmanage.service.IDataSourceTypeService;

@Service("dataSourceTypeService")
public class DataSourceTypeService extends
CommonService<TbDataSourceType> implements IDataSourceTypeService{
   
	@Autowired(required=true)
   private DataSourceTypeDAO sourceTypeDAO;

	/**
	 * 
	 * 获取所有分类的集合
	 * @author luj 新增日期：2013-2-20
	 */
	public List<TbDataSourceType> queryAllDataSourceType(){
		StringBuffer sql = new StringBuffer();
		sql.append("select type_id,type_name from tb_data_source_type ");
		List list = this.sourceTypeDAO.queryAllDataSource(sql.toString());
		List<TbDataSourceType> listDataSourceTypes = new ArrayList<TbDataSourceType>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			TbDataSourceType dataSourceType=new TbDataSourceType();
			dataSourceType.setId(Integer.parseInt(obj[0].toString()));
			dataSourceType.setTypeName(obj[1].toString());
			listDataSourceTypes.add(dataSourceType);
	} 
		return listDataSourceTypes;
}

	@Override
	public CommonDAO<TbDataSourceType> getBindDao() {
		return sourceTypeDAO;
	}
}
