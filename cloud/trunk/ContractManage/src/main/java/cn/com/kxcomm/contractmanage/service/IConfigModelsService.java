package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbConfigModels;
import cn.com.kxcomm.contractmanage.vo.ConfigModelsVo;
import cn.com.kxcomm.contractmanage.vo.DataRecordVo;

/**
 * 
 * 机型配置单总表
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IConfigModelsService extends ICommonService<TbConfigModels>  {
	public PageInfo<ConfigModelsVo> listConfigModels(PageInfo<ConfigModelsVo> pageInfo,Long useId);
	
	//生成导出文件，根据标配主表id查询
	public List<List<DataRecordVo>> listConfigModelByIds(String[] modelId) throws Exception;
	//根据标配主表id查询标配文件
	public List<DataRecordVo> listStanded(int id,int sourceId) throws Exception;
	//新增配置
		public String saveConfiguration(TbConfigModels configModels);
		/**
		* 
		* 方法用途和描述: 根据ID查询某个配置模板信息
		* @return
		* @author lizl 新增日期：2012-12-14
		* @since ContractManage
		*/
		public ConfigModelsVo queryModelsById(String id);
		//获取标配信息
		public List<DataRecordVo> getStandard(int sourceId);
		
	/**
	 * 
	* 方法用途和描述: 根据模板id查询机型配置
	* @param id
	* @return
	* @author chenliang 新增日期：2013-3-8
	* @since ContractManage
	 */
	public List<DataRecordVo> listConfigModelById(int id,String typeId);
}
