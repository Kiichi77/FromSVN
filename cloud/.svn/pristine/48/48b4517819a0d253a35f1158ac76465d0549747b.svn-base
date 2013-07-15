package cn.com.kxcomm.contractmanage.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import cn.com.kxcomm.contractmanage.vo.TaskVo;
/**
 * activiti流程图业务调用接口
 * @author DongGod
 *
 */
public interface IactivitiService {
	/**
	 * 流程发布
	 * @param xmlpath  流程图的相对路径
	 * @param processname  流程名称
	 * @return
	 */
	public void processDefinitionDeploy(String xmlpath,String processname);
	/**
	 * 根据进程ID删除已经发布的流程
	 * @param processid
	 */
	public void removeDeployedDefinition(String processid);
	/**
	 * 用户启动流程
	 * @param processid
	 * @param userid
	 */
	public ProcessInstance startProcessByUser(String userid,String typeId);
	/**
	 * 分配任务给用户
	 * @param processid
	 * @param userid
	 */
	public void taskClaim(String processid,String userid,Map map);
	/**
	 * 用户完成任务
	 * @param processid
	 * @param userid
	 */
	public void finishTheTask(String processid,String userid,Map map);
	/**
	 * 获取当前session用户的所有任务
	 * 我的待办任务
	 * @param userid
	 */
	public List<TaskVo> getTheCurrentTask(String userid,int start,int end);
	/**
	 * 根据进程ID获取进程定义实体	
	 * @param processid
	 */
	public ProcessDefinition getProcessDefinitionByProcessid(String processid);
	/**
	 * 查询该用户的历史流程实例实体列表
	 * @param userid
	 * @return
	 */
	public List<HistoricProcessInstance>  getHistoryProcessInstanceList(String userid);
	
	/**
	 * 
	* 方法用途和描述: 我的历史任务
	* @param userId
	* @return
	* @author chenliang 新增日期：2013-2-25
	* @since ContractManage
	 */
	public List<TaskVo> myHistoryTask(String userId,int start,int end);
	
	/**
	 * 
	* 方法用途和描述: 驳回
	* @param taskId  任务id
	* @param activityId  驳回的步骤id
	* @param variables  参数
	* @author chenliang 新增日期：2013-3-4
	* @since ContractManage
	 */
	public void backProcess(String taskId,String activityId,Map<String, Object> variables);
	
}
