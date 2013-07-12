package cn.com.kxcomm.contractmanage.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.SuppliersQuotationsDAO;
import cn.com.kxcomm.contractmanage.entity.TbSuppliers;
import cn.com.kxcomm.contractmanage.entity.TbSuppliersQuotations;
import cn.com.kxcomm.contractmanage.service.ISuppliersQuotationsService;
import cn.com.kxcomm.contractmanage.status.S_SuppliersQuotations;
import cn.com.kxcomm.contractmanage.vo.SuppliersQuotationsVo;

@Service("suppliersQuotationsService")
public class SuppliersQuotationsService extends CommonService<TbSuppliersQuotations> implements ISuppliersQuotationsService{

	private static final Logger LOG = Logger.getLogger(SuppliersQuotationsService.class);
    
	@Autowired(required=true)
	private SuppliersQuotationsDAO suppliersQuotationsDAO;

	@Override
	public CommonDAO<TbSuppliersQuotations> getBindDao() {
		return suppliersQuotationsDAO;
	}

	@Override
	public PageInfo<SuppliersQuotationsVo> queryPage(PageInfo<TbSuppliersQuotations> page, TbSuppliersQuotations model) {
		StringBuffer hql = new StringBuffer();
		hql.append("from TbSuppliersQuotations a where 1=1 ");
		String where = "";
		Integer suppliers = null;
		if(null!=model.getSuppliers()){
			if(null!=model.getSuppliers().getId()){
				suppliers = model.getSuppliers().getId();
				where=" and a.suppliers.id = ? ";
			}
		}
		hql.append(where);
		StringBuffer countHql = new StringBuffer();
		countHql.append("select count(*) as count from tb_suppliers_quotations a where 1=1 "+where);
		int count = suppliersQuotationsDAO.findCount(countHql.toString(), suppliers);
		List<TbSuppliersQuotations> listQuotations = suppliersQuotationsDAO.pageInfoQuery(hql.toString(),page, suppliers);
		List<SuppliersQuotationsVo> listvo = new ArrayList<SuppliersQuotationsVo>();
		for (TbSuppliersQuotations tbSuppliersQuotations : listQuotations) {
			SuppliersQuotationsVo suquovo = new SuppliersQuotationsVo();
			EntityToVoUtil.copyObjValue(tbSuppliersQuotations, suquovo);
			suquovo.setCreator(tbSuppliersQuotations.getCreator().getUserName());
			suquovo.setCreatorID(""+tbSuppliersQuotations.getCreator().getId());
			suquovo.setSuppliers(tbSuppliersQuotations.getSuppliers().getCompanyName());
			suquovo.setSuppliersID(""+tbSuppliersQuotations.getSuppliers().getId());
			listvo.add(suquovo);
		}
		PageInfo<SuppliersQuotationsVo> pageInfo = new PageInfo<SuppliersQuotationsVo>(page.getCurrentPage(),count,page.getPageSize(),listvo);
		return pageInfo;
	}

	@Override
	public void saveSuppliersQuotations(TbSuppliersQuotations model) {
		TbSuppliersQuotations entity = new TbSuppliersQuotations();
		entity.setState(S_SuppliersQuotations.enable);
		entity.setCreatetime(new Date());
		entity.setCreator(model.getCreator());
		entity.setDescription(model.getDescription());
		entity.setSuppliersQuotationsName(model.getSuppliersQuotationsName());
		entity.setUrl(model.getUrl());
		TbSuppliers suppliers = new TbSuppliers();
		suppliers.setId(model.getSuppliers().getId());
		entity.setSuppliers(suppliers);
		suppliersQuotationsDAO.save(entity);
	}

	@Override
	public SuppliersQuotationsVo querySuppliersById(String id) {
		int pkid;
		SuppliersQuotationsVo vo = new SuppliersQuotationsVo();
		if(!BlankUtil.isBlank(id)){
			pkid = Integer.parseInt(id);
			TbSuppliersQuotations entity = suppliersQuotationsDAO.findById(pkid);
			vo.setCreatetime(""+entity.getCreatetime());
			vo.setCreator(entity.getCreator().getUserName());
			vo.setCreatorID(""+entity.getCreator().getId());
			vo.setDescription(entity.getDescription());
			vo.setSuperliersQuotationsId(entity.getSuperliersQuotationsId());
			vo.setSuppliers(entity.getSuppliers().getCompanyName());
			vo.setSuppliersID(""+entity.getSuppliers().getId());
			vo.setSuppliersQuotationsName(entity.getSuppliersQuotationsName());
			vo.setUrl(entity.getUrl());
		}
		return vo;
	}

	@Override
	public void updateSupplierQuotations(TbSuppliersQuotations model) {
		TbSuppliersQuotations entity = suppliersQuotationsDAO.findById(model.getSuperliersQuotationsId());
		if(!BlankUtil.isBlank(entity)){
			entity.setDescription(model.getDescription());
			TbSuppliers suppliers = new TbSuppliers();
			suppliers.setId(model.getSuppliers().getId());
			entity.setSuppliers(suppliers);
			entity.setSuppliersQuotationsName(model.getSuppliersQuotationsName());
			suppliersQuotationsDAO.merge(entity);
		}
	}

	@Override
	public List<SuppliersQuotationsVo> querySuppliersQuotationsBySuppId(String id) {
		Integer pkid=null;
		List<TbSuppliersQuotations> entitylist = null;
		//查询该供应商下面没有被其它供应商选择的
		String hql = "from TbSuppliersQuotations tb where tb.state = ? ";
		if(!BlankUtil.isBlank(id) && !"null".equals(id)){
			hql += " and tb.suppliers.id = ? ";
			pkid = Integer.parseInt(id);
			entitylist = suppliersQuotationsDAO.find(hql, S_SuppliersQuotations.enable,pkid);
		}else{
			entitylist = suppliersQuotationsDAO.find(hql, S_SuppliersQuotations.enable);
		}
		List<SuppliersQuotationsVo> volist = new ArrayList<SuppliersQuotationsVo>();
		for (int i = 0; i < entitylist.size(); i++) {
			TbSuppliersQuotations entity = entitylist.get(i);
			SuppliersQuotationsVo vo = new SuppliersQuotationsVo();
			vo.setCreatetime(""+entity.getCreatetime());
			vo.setCreator(entity.getCreator().getUserName());
			vo.setCreatorID(""+entity.getCreator().getId());
			vo.setDescription(entity.getDescription());
			vo.setSuperliersQuotationsId(entity.getSuperliersQuotationsId());
			vo.setSuppliers(entity.getSuppliers().getCompanyName());
			vo.setSuppliersID(""+entity.getSuppliers().getId());
			vo.setSuppliersQuotationsName(entity.getSuppliersQuotationsName());
			vo.setUrl(entity.getUrl());
			volist.add(vo);
		}
		return volist;
	}
	
}
