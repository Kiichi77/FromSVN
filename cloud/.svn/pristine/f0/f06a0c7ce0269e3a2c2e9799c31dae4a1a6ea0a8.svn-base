package cn.com.kxcomm.contractmanage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.FormulaUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ConfigModelsDAO;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.service.IConfigModelsService;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

@Service("configModelsService")
public class ConfigModelsService extends CommonService<TbConfigModels>
		implements IConfigModelsService {

	@Autowired(required = true)
	private ConfigModelsDAO configModelsDAO;

	// 查询所有的机型配置
	@Override
	public PageInfo<ConfigModelsVo> listConfigModels(PageInfo<ConfigModelsVo> pageInfo,Long useId)
	{
		List<ConfigModelsVo> listConfigModelsVo=new ArrayList<ConfigModelsVo>();
		String hql = " from TbConfigModels tm where tm.type=1 and tm.creator.id=? order by tm.createtime desc";
		PageInfo<TbConfigModels> p = new PageInfo<TbConfigModels>();
		p.setPageSize(pageInfo.getPageSize());
		p.setCurrentPage(pageInfo.getCurrentPage());
		p = configModelsDAO.findByPage(p, hql, useId);
		List<TbConfigModels> list=p.getRows();
		int count = configModelsDAO.findTotalCount("select count(*) from TbConfigModels tm where tm.type=1 and tm.creator.id=?",useId);
		for (TbConfigModels configModels:list) {
			ConfigModelsVo configModelsVo=new ConfigModelsVo();
			configModelsVo.setId(configModels.getId().toString());
			configModelsVo.setName(configModels.getName());
			configModelsVo.setTotalPrice(""+configModels.getTotalPrice());
			configModelsVo.setAfterDiscountNowPrice(""+configModels.getAfterDiscountNowPrice());
			configModelsVo.setAfterDiscountPrice(""+configModels.getAfterDiscountPrice());
			configModelsVo.setCatalogTotalprice(""+configModels.getCatalogTotalprice());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
			configModelsVo.setCreatetime(sdf.format(configModels.getCreatetime()));
			configModelsVo.setStatus(configModels.getStatus().toString());
			configModelsVo.setDataSourceName(configModels.getDataSource().getName());
			configModelsVo.setDataSourceType(configModels.getDataSource().getDataSourceType().getTypeName());
			configModelsVo.setCreatorName(configModels.getCreator().getUserName());
			configModelsVo.setDataSourceId(""+configModels.getDataSource().getId());
			listConfigModelsVo.add(configModelsVo);
		}
		PageInfo<ConfigModelsVo> page = new PageInfo<ConfigModelsVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listConfigModelsVo);
		 return page;
	}
	
	@Override
	public CommonDAO<TbConfigModels> getBindDao() {
		return configModelsDAO;
	}

	@Override
	public List<List<DataRecordVo>> listConfigModelByIds(String[] modelId) throws Exception {
		List<List<DataRecordVo>> volist = new ArrayList<List<DataRecordVo>>();
		for (String tmp : modelId) {
			if(tmp==null||"".equals(tmp)){
				continue;
			}
			int id = Integer.parseInt(tmp);
			List<DataRecordVo> datavolist = listConfigModelById(id,null);
			volist.add(datavolist);
		}
		return volist;
	}

	/**
	 * 
	* 方法用途和描述: 根据模板id查询详细信息
	* @param id
	* @return
	* @author chenliang 新增日期：2012-12-26
	* @since ContractManage
	 */
	@Override
	public List<DataRecordVo> listConfigModelById(int id,String typeId){
		List<DataRecordVo> datavoList = new ArrayList<DataRecordVo>();
		List list = new ArrayList();
		StringBuffer hql = new StringBuffer(); //as typeName  ， as dataRecordId ,as typeid,as configModel 
		hql.append(" select tcm.dataRecord.productOrderNo,tcm.dataRecord.productType.name ,tcm.dataRecord.productDesc,tcm.quantity,tcm.dataRecord.unitPrice,tcm.discountRate,");
		hql.append(" tcm.dataRecord.otherRates,tcm.dataRecord.InstallServiceCharge,tcm.firstYear,tcm.secondYear,tcm.thirdYear,tcm.dataRecord.remark, ");
		hql.append(" tcm.dataRecord.id ,tcm.dataRecord.productType.id ,tcm.dataRecord.productType.category.id,tcm.id,tcm.dataRecord.productType.category.name ");
		hql.append(" from TbModelData tcm  where  tcm.configModel.id = ? ");
		   if(null != typeId && !"".equals(typeId) &&  !"null".equals(typeId)){
			   int type=Integer.parseInt(typeId);
		   hql.append(" and tcm.dataRecord.productType.category.id=?");
		   list=configModelsDAO.find(hql.toString(), id,type);
		   }else{
			list=configModelsDAO.find(hql.toString(), id);
		   }
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			DataRecordVo vo = new DataRecordVo();
			vo.setProductOrderNo(obj[0]==null?"":obj[0].toString());
			vo.setName(obj[1]==null?"":obj[1].toString());
			vo.setProductDesc(obj[2]==null?"":obj[2].toString());
			vo.setQuantity(obj[3]==null?0d:Double.parseDouble(obj[3].toString()));
			BigDecimal bg = new BigDecimal(obj[4].toString());
			vo.setUnitPrice(obj[4]==null?0d:bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			vo.setDiscountRate(obj[5]==null?0d:Double.parseDouble(obj[5].toString()));
			vo.setOtherRates(obj[6]==null?0d:Double.parseDouble(obj[6].toString()));
			vo.setInstallServiceCharge(obj[7]==null?0d:Double.parseDouble(obj[7].toString()));
			vo.setFirstYear(obj[8]==null?0d:Double.parseDouble(obj[8].toString()));
			vo.setSecondYear(obj[9]==null?0d:Double.parseDouble(obj[9].toString()));
			vo.setThirdYear(obj[10]==null?0d:Double.parseDouble(obj[10].toString()));
			vo.setRemark(obj[11]==null?"":obj[11].toString());
			vo.setDataRecordId(obj[12]==null?"":obj[12].toString());
			vo.setTypeId(obj[13]==null?"":obj[13].toString());
			vo.setCategoryId(obj[14]==null?"":obj[14].toString());
			vo.setConfiModeId(obj[15]==null?"":obj[15].toString());
			vo.setCategoryName(obj[16]==null?"":obj[16].toString());
			vo.setTotalprice(FormulaUtil.planTotalPrice(vo));
			vo.setRebateprice(FormulaUtil.planRabateprice(vo));
			vo.setRebateafter(FormulaUtil.planRateafter(vo));
			vo.setTotal(FormulaUtil.planTotal(vo));
			datavoList.add(vo);
		}
		return datavoList;
	}

	 /**
		* 
		* 方法用途和描述: 新增模板
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
		@Override
		public String saveConfiguration(TbConfigModels configModels) {
			String end = "ok";
			try {
				if(BlankUtil.isBlank(configModels.getId())) {
					   end = ""+this.configModelsDAO.save(configModels);
				   } else {
					   this.configModelsDAO.merge(configModels);
					   end = ""+configModels.getId();
				   }
			} catch (Exception e) {
				end = "error";
			}
			return end;
		}

		/**
		* 
		* 方法用途和描述: 根据ID查询某个配置模板信息
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
		@Override
		public ConfigModelsVo queryModelsById(String id) {
			TbConfigModels configModels = this.configModelsDAO.findById(Integer.parseInt(id));
			ConfigModelsVo configModelsVo = new ConfigModelsVo();
			configModelsVo.setId(""+configModels.getId());
			configModelsVo.setDataSourceId(""+configModels.getDataSource().getId());
			configModelsVo.setName(configModels.getName());
			configModelsVo.setDataSourceName(configModels.getDataSource().getName());
			return configModelsVo;
		}
		
		/**
		 * 
		* 方法用途和描述: 获取标配信息
		* @param configModelId 配置模板id
		* @param sourceId  数据源id
		* @return
		* @author chenliang 新增日期：2012-12-26
		* @since ContractManage
		 */
		@Override
		public List<DataRecordVo> getStandard(int sourceId){
			StringBuffer hql = new StringBuffer();
			hql.append(" select t2.productOrderNo,t4.name,t2.productDesc,t1.quantity,t2.unitPrice,t1.discountRate, ");
			hql.append(" t2.otherRates,t2.InstallServiceCharge,t1.first_year,t1.second_year,t1.third_year,t2.remark, ");
			hql.append(" t2.id,t4.id,t4.category_id,t5.id as configModel,t1.id ");
			hql.append(" from tb_configmodel_data t1,tb_data_record t2,tb_data_source t3,tb_product_type t4,tb_config_models t5 ");
			hql.append(" where t1.data_record_id = t2.id ");
			hql.append(" and t2.data_source_id =  t3.id ");
			hql.append(" and t2.product_type_id = t4.id ");
			hql.append(" and t1.config_model_id = t5.id ");
			hql.append(" and t5.type = 0 ");
			hql.append(" and t5.data_source_id = ? ");
			List lists = configModelsDAO.findByPage(hql.toString(),sourceId);
			Object[] obj = new Object[lists.size()];
			List<DataRecordVo> volist = new ArrayList<DataRecordVo>();
			for (int i = 0; i < lists.size(); i++) {
				obj = (Object[]) lists.get(i);
				DataRecordVo vo = new DataRecordVo();
				vo.setProductOrderNo(obj[0]==null?"":obj[0].toString());
				vo.setName(obj[1]==null?"":obj[1].toString());
				vo.setProductDesc(obj[2]==null?"":obj[2].toString());
				vo.setQuantity(obj[3]==null?0d:Double.parseDouble(obj[3].toString()));
				vo.setUnitPrice(obj[4]==null?0d:Double.parseDouble(obj[4].toString()));
				vo.setDiscountRate(obj[5]==null?0d:Double.parseDouble(obj[5].toString()));
				vo.setOtherRates(obj[6]==null?0d:Double.parseDouble(obj[6].toString()));
				vo.setInstallServiceCharge(obj[7]==null?0d:Double.parseDouble(obj[7].toString()));
				vo.setFirstYear(obj[8]==null?0d:Double.parseDouble(obj[8].toString()));
				vo.setSecondYear(obj[9]==null?0d:Double.parseDouble(obj[9].toString()));
				vo.setThirdYear(obj[10]==null?0d:Double.parseDouble(obj[10].toString()));
				vo.setRemark(obj[11]==null?"":obj[11].toString());
				vo.setDataRecordId(obj[12]==null?"":obj[12].toString());
				vo.setTypeId(obj[13]==null?"":obj[13].toString());
				vo.setCategoryId(obj[14]==null?"":obj[14].toString());
				vo.setConfiModeId(obj[15]==null?"":obj[15].toString());
				vo.setId(""+obj[16].toString());
				vo.setTotalprice(FormulaUtil.planTotalPrice(vo));
				vo.setRebateprice(FormulaUtil.planRabateprice(vo));
				vo.setRebateafter(FormulaUtil.planRateafter(vo));
				vo.setTotal(FormulaUtil.planTotal(vo));
				volist.add(vo);
			}
			return volist;
		}

		@Override
		/**
		 * 
		 * 根据模板id查询配置信息
		 *
		 */
		public List<DataRecordVo> listStanded(int id,int sourceId) throws Exception {
			//先根据模板idc查询配置信息
			List<DataRecordVo> volist = listConfigModelById(id,null);
			if(null==volist || volist.size()<=0){ //如果没有配置信息，则查询标配信息
				volist = getStandard(sourceId);
			}else{
				LOGGER.info("configModeDetail and StandedInfo is null.");
			}
			return volist;
		}
}
