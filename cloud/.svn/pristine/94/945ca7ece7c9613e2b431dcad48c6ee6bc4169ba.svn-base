package cn.com.kxcomm.contractmanage.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ConfigModelsDAO;
import cn.com.kxcomm.contractmanage.dao.ModelDataDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.entity.TbDataRecord;
import cn.com.kxcomm.contractmanage.entity.TbDataSource;
import cn.com.kxcomm.contractmanage.entity.TbModelData;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.IModelDataService;
import cn.com.kxcomm.contractmanage.vo.QuotationDataInfoBean;

@Service("modelDataService")
public class ModelDataService  extends
CommonService<TbModelData> implements IModelDataService {
	@Autowired(required=true)
	private ModelDataDAO modelDataDAO;
	@Autowired(required=true)
	private ConfigModelsDAO configModelsDAO;
	
	@Override
	public CommonDAO<TbModelData> getBindDao() {
		return modelDataDAO;
	}
	
	/**
	 * 保存、修改模板信息.
	 *
	 * @author lizl 新增日期：2012-12-18
	 * @param jsonBeanList
	 * @return
	 */
	@Override
	public String saveAndUpdateQuotation(List<QuotationDataInfoBean> jsonBeanList) {
		String end = "ok";
		boolean checks = true;
		try {
			TbModelData data = null;
			TbConfigModels configModel = null;
			TbDataRecord dataRecord = null;
			if(jsonBeanList.size()>0){
				for(int i = 0;i < jsonBeanList.size(); i++) {
					data = new TbModelData();
					configModel = new TbConfigModels();
					dataRecord = new TbDataRecord();
					if(!BlankUtil.isBlank(jsonBeanList.get(i).getId())) {
						data.setId(Integer.parseInt(jsonBeanList.get(i).getId()));
					}
					if(checks) {
						this.modelDataDAO.deleteByConfigModelId(jsonBeanList.get(i).getConfigId());
						checks = false;
					}
					data.setQuantity(Double.parseDouble(jsonBeanList.get(i).getNumber()));
					data.setDiscountRate(Double.parseDouble(jsonBeanList.get(i).getDiscount()));
					data.setFirstYear(Double.parseDouble(jsonBeanList.get(i).getFirstYear()));
					data.setSecondYear(Double.parseDouble(jsonBeanList.get(i).getSecondYear()));
					data.setThirdYear(Double.parseDouble(jsonBeanList.get(i).getThirdYear()));
					configModel.setId(Integer.parseInt(jsonBeanList.get(i).getConfigId()));
					dataRecord.setId(Integer.parseInt(jsonBeanList.get(i).getSourceDataId()));
					data.setConfigModel(configModel);
					data.setDataRecord(dataRecord);
					data.setCatalogPrice(Double.parseDouble(jsonBeanList.get(i).getCatalogPrice()));
					data.setDiscountedPrice(Double.parseDouble(jsonBeanList.get(i).getDiscountedPrice()));
					data.setDiscountAfterPrice(Double.parseDouble(jsonBeanList.get(i).getDiscountAfterPrice()));
					data.setTotalPrice(Double.parseDouble(jsonBeanList.get(i).getTotalPrice()));
					this.modelDataDAO.save(data);
				}
				int configId = Integer.parseInt(jsonBeanList.get(0).getConfigId());
				int result = updateConfigModelState(configId);
				LOGGER.info("result:"+result);
			}
		} catch (Exception e) {
			end = "error";
			e.printStackTrace();
		}
		return end;
	}
	
	/**
	 * 
	* 方法用途和描述: 修改配置文件
	* @author chenliang 新增日期：2012-12-26
	* @since ContractManage
	 */
	private int updateConfigModelState(int configModelId){
		String hql = " update TbConfigModels set type=1 where id=? ";
		int result = modelDataDAO.executeByHQL(hql.toString(), configModelId);
		return result;
	}

	/**
	 * 保存标配信息 
	 */
	@Override
	public String saveStandard(List<QuotationDataInfoBean> jsonBeanList,int sourceDataId,Long creatorId) {
		if(jsonBeanList.size()>0){
			
			TbConfigModels models = null;
			
			//判断sourceDataId是否为空，如果为空则是添加，不为空则是修改，则先删除然后再添加
			if(!BlankUtil.isBlank(sourceDataId)) {
				String findModelHql = "from TbConfigModels s where type=0 and dataSource.id=?";
				List<TbConfigModels> listModels = this.configModelsDAO.find(findModelHql,sourceDataId);
				if(0 < listModels.size()) {
					models = listModels.get(0);
					if(!BlankUtil.isBlank(""+models.getId())) {
						this.modelDataDAO.deleteByConfigModelId(""+models.getId());
						this.configModelsDAO.delete(models);
					}
				}
			}
			TbUser tbUser = new TbUser();
			tbUser.setId(creatorId);
			models = new TbConfigModels();
			//先保存配置信息总表
			models.setCreatetime(new Date());
			TbDataSource tbsource = new TbDataSource();
			tbsource.setId(sourceDataId);
			models.setDataSource(tbsource);
			models.setName("标配");
			models.setStatus(1); 
			models.setType(0);
			models.setCreator(tbUser);
			configModelsDAO.save(models);
			
			//保存标配信息
			for (QuotationDataInfoBean quotationDataInfoBean : jsonBeanList) {
				TbModelData modelData = new TbModelData();
				modelData.setConfigModel(models);
				TbDataRecord dataRecord = new TbDataRecord();
				dataRecord.setId(Integer.parseInt(quotationDataInfoBean.getSourceDataId()));
				modelData.setDataRecord(dataRecord);
				modelData.setQuantity(Double.parseDouble(quotationDataInfoBean.getNumber()));
				modelData.setDiscountRate(Double.parseDouble(quotationDataInfoBean.getDiscount()));
				modelData.setFirstYear(Double.parseDouble(quotationDataInfoBean.getFirstYear()));
				modelData.setSecondYear(Double.parseDouble(quotationDataInfoBean.getSecondYear()));
				modelData.setThirdYear(Double.parseDouble(quotationDataInfoBean.getThirdYear()));
				modelData.setCatalogPrice(Double.parseDouble(quotationDataInfoBean.getCatalogPrice()));
				modelData.setDiscountedPrice(Double.parseDouble(quotationDataInfoBean.getDiscountedPrice()));
				modelData.setDiscountAfterPrice(Double.parseDouble(quotationDataInfoBean.getDiscountAfterPrice()));
				modelData.setTotalPrice(Double.parseDouble(quotationDataInfoBean.getTotalPrice()));
				this.modelDataDAO.save(modelData);
			}
		}else{
			LOGGER.info("jsonBeanList is null.");
		}
		return "ok";
	}
	
}
