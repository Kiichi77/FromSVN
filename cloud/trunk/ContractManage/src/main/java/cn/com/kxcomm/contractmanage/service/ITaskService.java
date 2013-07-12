package cn.com.kxcomm.contractmanage.service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbTask;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;

/**
 * 
* 功能描述:任务管理
* @author chenliang 新增日期：2013-4-1
* @since ContractManage
 */
public interface ITaskService extends ICommonService<TbTask>{

	/**
	 * 
	* 方法用途和描述: 我的代办任务，我的已完成任务
	* @param entity
	* @param pageInfo
	* @return
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public PageInfo<TaskVo> pageInfo(TbTask entity,PageInfo<TbTask> pageInfo) ;

	/**
	 * 
	* 方法用途和描述: 提交任务
	* @param model
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public void submitTask(TbTask model)  throws Exception;
	
	/**
	 * 
	* 方法用途和描述: 转交任务
	* @param model
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public void forwardedTask(TaskVo vo) throws Exception;
	
	/**
	 * 
	* 方法用途和描述: 启动一个任务，给默认的处理接口人进行消息提示
	* @param taskType  任务类型，可以为空
	* @param sponsorid  发起人
	* @param taskName  任务名称
	* @param projectId  项目
	* @param forwardType 跳转类型（该类型已经定义为常量）
	* @param personInChargeId 负责人
	* @param param 参数
	* @throws Exception  
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	public void startTask(String taskType,Long sponsorid,String taskName,Integer projectId,Long personInChargeId,Integer forwardType,String remark,String param,String paramPkid) throws Exception;
	/**
	 * 根据项目查询项目的流程
	 * @param projectId  项目ID
	 * @throws Exception 
	 */
	public  PageInfo<TaskVo> listTask(PageInfo<TaskVo> pageInfo,Integer projectId);
	
	/**
	 * 
	 * 合同审核(type:1 正式合同 type：2 临时合同)
	 * result:0审核同意 1：审核不同意
	 * @param checkVo
	 * @author zhangjh 新增日期：2013-4-28
	 * @since ContractManage
	 */
	public void checkContract(CheckedVo checkVo);
	
	/**
	 * 
	* 方法用途和描述: 自动完成任务，把待办事项任务状态改为已处理
	* @author chenliang 新增日期：2013-6-7
	* @since ContractManage
	 */
	public void finishTask(TaskVo taskVo);
}
