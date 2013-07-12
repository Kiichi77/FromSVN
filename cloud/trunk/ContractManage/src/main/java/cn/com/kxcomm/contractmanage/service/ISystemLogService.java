package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbSystemLog;
import cn.com.kxcomm.contractmanage.vo.SystemLogVo;

public interface ISystemLogService extends ICommonService<TbSystemLog>{
	/**
	* 
	* 方法用途和描述: 分页查询所有的日志
	* @return
	* @author luj 新增日期：2013-4-2
	* @since ContractManage
	*/
	public PageInfo<SystemLogVo> listSystemLog(PageInfo<SystemLogVo> pageInfo,SystemLogVo LogVo);
	
	/**
	 * 
	 * 写日志信息
	 * 
	 * @param logType 日志类型的定义见<code>SystemLogType</code>
	 * @param operater 操作人，当前登录的用户
	 * @param result 操作结果
	 * @param message 操作后的信息，如果操作失败则把失败原因放此，类似异常的e.getMessage()记录下来
	 * @param operatContent 操作内容，为敏感、重要、可扭转性的信息。例如：xx人员把合同的总额，原100万修改为1000万，<br/>
	 * 使得重要的操作可以靠日志记录变得理据，可追溯到某人何时修改什么内容导致系统哪些地方不合理
	 * 参考例子：
	 * writeTheLog (SystemLogType.add_tb_data_source,1,"失败","数据源文件为空xxx.xlsx","axsd.xlsx");
	 * @author zhangjh 新增日期：2013-4-2
	 * @since ContractManage
	 */
	public void writeTheLog (String logType,Long operaterId,String result,String message,String operatContent)throws Exception;
}
