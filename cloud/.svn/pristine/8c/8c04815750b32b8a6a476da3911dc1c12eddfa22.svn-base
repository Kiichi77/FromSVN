package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbContractTemplatesDoc;
import cn.com.kxcomm.contractmanage.vo.ContractTemplatesDocVo;

/**
 * 
 * 合同模版文件
 * 
 * @author zhangjh 新增日期：2012-12-6
 * @since ContractManage
 */
public interface IContractTemplatesDocService extends ICommonService<TbContractTemplatesDoc>{
	public List<ContractTemplatesDocVo> listContractTemplatesDoc(int contractTemplatesId);
	//根据主键id查询，返回pageinfo对象
	public PageInfo<ContractTemplatesDocVo> getTemplatesDocByPk(int id) throws Exception;
	//修改模板
	public void updateTemplatesDoc(ContractTemplatesDocVo docvo) throws Exception;
	//根据模板ID查询相关模板文件
	public List<TbContractTemplatesDoc> queryConTempDocById(String tempId);
	/**
	 * 
	* 方法用途和描述: 查询合同模板分页
	* @param vo 参数vo
	* @param pageInfo 分页对象
	* @param flat 是否分页
	* @return
	* @author chenliang 新增日期：2013-2-25
	* @since ContractManage
	 */
	public PageInfo<ContractTemplatesDocVo> queryAll(PageInfo<ContractTemplatesDocVo> pageInfo,ContractTemplatesDocVo vo,boolean flat);
	
	/**
	 * 
	* 方法用途和描述: 查询合同模板关联的合同模板文件,如果该模板文件与模板有关联则关联查询出来
	* @return
	* @author chenliang 新增日期：2013-2-26
	* @since ContractManage
	 */
	public PageInfo<ContractTemplatesDocVo> queryTemplateDoc(ContractTemplatesDocVo vo);
	
	/**
	 * ftp同步上传合同模版文件到ftp，当config.properties的isFtpSync为true时触发
	 * 
	 * @param localPath 本地路径
	 * @param fileName 文件名称
	 * @author zhangjh 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public void ftpUploadContractDocFileSync(String localPath,String fileName);
	
	/**
	 * 
	 * ftp同步下载合同模版文件到本地，当config.properties的isFtpSync为true时触发
	 * @param localPath 本地路径
	 * @author zhangjh 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public void ftpDownloadSync(String localPath);
	
	/**
	 * 
	 * 删除ftp上的docx文件
	 * 
	 * @param fileName
	 * @author zhangjh 新增日期：2013-3-25
	 * @since ContractManage
	 */
	public void ftpDeleteSync(String fileName);
	
}
