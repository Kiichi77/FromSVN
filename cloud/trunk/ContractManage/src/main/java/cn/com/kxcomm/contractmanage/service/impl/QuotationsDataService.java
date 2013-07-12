package cn.com.kxcomm.contractmanage.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.QuotationsDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.service.IQuotationsDataService;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;

@Service("quotationsDataService")
public class QuotationsDataService extends
CommonService<TbQuotationsData> implements IQuotationsDataService {
	@Autowired(required=true)
	private QuotationsDataDAO quotationsDataDAO;

	@Override
	public CommonDAO<TbQuotationsData> getBindDao() {
		return quotationsDataDAO;
	}
	//查询所有的报价单
		public PageInfo<QuotationsVo> listQuotations(PageInfo<QuotationsVo> pageInfo)
		{
			List<QuotationsVo> listQuotationsVos=new ArrayList<QuotationsVo>();
			List<TbQuotationsData> list=quotationsDataDAO.findAll();
			int count=quotationsDataDAO.findTotalCount("select count(*) from TbQuotations ");
			for(TbQuotationsData quotationsData:list)
			{
				QuotationsVo quotationsVo=new QuotationsVo();
				quotationsVo.setId(quotationsData.getQuotations().getId().toString());
				quotationsVo.setTitle(quotationsData.getQuotations().getTitle());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
				quotationsVo.setCreatetime(sdf.format(quotationsData.getQuotations().getCreatetime()));
				quotationsVo.setStatus(quotationsData.getQuotations().getStatus().toString());
				quotationsVo.setDataSourceName(quotationsData.getConfigModels().getDataSource().getName());
				listQuotationsVos.add(quotationsVo);
			}
			PageInfo<QuotationsVo> page = new PageInfo<QuotationsVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listQuotationsVos);
			return page;
		}
		
		/**
		 *  根据机型配置ID和报价表ID修改已采购数量.
		 *
		 * @param comfigId
		 * @param quotaId
		 * @param newOrder
		 */
		@Override
		public void updateQuotationsData(String comfigId, String quotaId,Integer newOrder) {
			String hql = "update TbQuotationsData set ordered=(ordered+?) where configModels.id=? and quotations.id = ? ";
			quotationsDataDAO.executeByHQL(hql, newOrder,comfigId,quotaId);
		}

		@Override
		public List<QuotationsVo> queryByQuotationId(int pkid) throws Exception {
			List<QuotationsVo> volist = new ArrayList<QuotationsVo>();
			try {
				String hql = "from TbQuotationsData tq where tq.quotations.id=? ";
				List<TbQuotationsData> dataList = quotationsDataDAO.find(hql, pkid);
				
				double totalPrice = 0;
				double afterDiscontNowPrice = 0;
				double aftnterDiscoutPrice = 0;
				double catalogTotalPrice = 0;
				int number = 0;
				
				//钱转换
				NumberFormat nf = NumberFormat.getNumberInstance();
		        nf.setMaximumFractionDigits(2);
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
				for (TbQuotationsData tbQuotationsData : dataList) {
					QuotationsVo vo = new QuotationsVo();
					vo.setId(""+tbQuotationsData.getConfigModels().getId());   //配置单ID
					vo.setTitle(tbQuotationsData.getConfigModels().getName()); //配置单名称
					Date date = tbQuotationsData.getConfigModels().getCreatetime(); 
					vo.setCreatetime(sdf.format(date!=null?date:new Date())); //创建时间
					vo.setStatus(""+tbQuotationsData.getConfigModels().getStatus()); //状态
					vo.setDataSourceName(tbQuotationsData.getConfigModels().getDataSource().getName()); //数据源名称
					vo.setDataSourceType(tbQuotationsData.getConfigModels().getDataSource().getDataSourceType().getTypeName()); //数据源类型
					vo.setCreatorName(tbQuotationsData.getConfigModels().getCreator().getUserName());//创建者
					totalPrice = tbQuotationsData.getConfigModels().getTotalPrice();//总价
					afterDiscontNowPrice = tbQuotationsData.getConfigModels().getAfterDiscountNowPrice(); //折扣后目录现场价
					aftnterDiscoutPrice = tbQuotationsData.getConfigModels().getAfterDiscountPrice();//折扣后目录价
					catalogTotalPrice = tbQuotationsData.getConfigModels().getCatalogTotalprice();//目录合价
					number = tbQuotationsData.getQuantity();
					vo.setAfterDiscountNowPrice(nf.format(afterDiscontNowPrice*number));
					vo.setAfterDiscountPrice(nf.format(aftnterDiscoutPrice*number));
					vo.setCatalogTotalPrice(nf.format(catalogTotalPrice*number));
					vo.setTotalPrice(nf.format(totalPrice*number));
					vo.setQuantity(""+number);
					vo.setProjectName(""+tbQuotationsData.getProjectName());
					volist.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return volist;
		}
}
