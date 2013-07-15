package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbArrivalProved;
import cn.com.kxcomm.contractmanage.vo.ArrivalProvedVo;

public interface IArrivalProvedService extends  ICommonService<TbArrivalProved>{
	/**
	 * 
	 * 查询所有收货、初验、终验证明
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-18
	 * @since ContractManage
	 */
	public PageInfo<ArrivalProvedVo> queryAllArrivalProved(PageInfo<ArrivalProvedVo> pageInfo,String id);
	
	 /**
	  * 
	 * 方法用途和描述: 上传到货、初验、终验文件
	 * @return
	 * @author lujia 新增日期：2013-3-19
	 * @since ContractManage
	  */
	public int uploadProved(int provedId,String contractId,int type,String FileName);
}
