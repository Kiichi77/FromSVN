package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbModelData;
import cn.com.kxcomm.contractmanage.vo.QuotationDataInfoBean;

/**
 * 
 * 配置单数据
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IModelDataService extends  ICommonService<TbModelData>{
	/**
	 * 保存、修改模板信息.
	 *
	 * @author lizl 新增日期：2012-12-18
	 * @param jsonBeanList
	 * @return
	 */
	public String saveAndUpdateQuotation(List<QuotationDataInfoBean> jsonBeanList);
	
	/**
	 * 
	* 方法用途和描述: 保存标配信息
	* @param jsonBeanList
	* @return
	* @author chenliang 新增日期：2012-12-26
	* @since ContractManage
	 */
	public String saveStandard(List<QuotationDataInfoBean> jsonBeanList,int sourceDataId,Long creatorId);
}
