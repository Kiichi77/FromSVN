package cn.com.kxcomm.contractmanage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ProductTypeDAO;
import cn.com.kxcomm.contractmanage.entity.TbProductType;
import cn.com.kxcomm.contractmanage.service.IProductTypeService;

@Service("productTypeService")
public class ProductTypeService extends CommonService<TbProductType>
		implements IProductTypeService {

	@Autowired(required = true)
	private ProductTypeDAO productTypeDAO;

	@Override
	public CommonDAO<TbProductType> getBindDao() {
		return productTypeDAO;
	}

}
