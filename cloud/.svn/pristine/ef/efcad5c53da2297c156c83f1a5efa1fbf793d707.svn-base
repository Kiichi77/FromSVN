package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;

/**
 * 
 * 报价表数据
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IQuotationsDataService extends  ICommonService<TbQuotationsData>{
	public PageInfo<QuotationsVo> listQuotations(PageInfo<QuotationsVo> pageInfo);

	/**
	 * TODO 根据机型配置ID和报价表ID修改已采购数量.
	 *
	 * @param comfigId
	 * @param quotaId
	 * @param newOrder
	 */
	public void updateQuotationsData(String comfigId, String quotaId,Integer newOrder);

	/**
	 * 
	* 方法用途和描述: 根据报价表id查询标价表内容
	* @param pkid
	* @return
	* @author chenliang 新增日期：2013-4-9
	* @since ContractManage
	 */
	public List<QuotationsVo> queryByQuotationId(int pkid) throws Exception;
}
