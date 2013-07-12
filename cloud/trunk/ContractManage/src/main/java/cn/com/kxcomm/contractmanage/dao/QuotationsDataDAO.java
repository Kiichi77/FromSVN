package cn.com.kxcomm.contractmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.kxcomm.contractmanage.entity.TbQuotationsData;
@Repository
public class QuotationsDataDAO extends CommonDAO<TbQuotationsData> {
	/**
	 * 
	 * 返回报价表下的机型配置
	 * 
	 * @param id
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public List<TbQuotationsData> queryDataById( Integer id){
		return this.find("select data from TbQuotationsData data where data.quotations.id=?", id);
	}
}
