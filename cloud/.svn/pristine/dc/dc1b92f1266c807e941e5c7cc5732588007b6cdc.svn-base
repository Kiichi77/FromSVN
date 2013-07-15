package cn.com.kxcomm.contractmanage.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ProjectDAO;
import cn.com.kxcomm.contractmanage.dao.TaskDAO;
import cn.com.kxcomm.contractmanage.dao.UserDAO;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbTask;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ITaskService;
import cn.com.kxcomm.contractmanage.vo.CheckedVo;
import cn.com.kxcomm.contractmanage.vo.TaskVo;

/**
 * 
* 功能描述: 任务管理
* @author chenliang 新增日期：2013-4-2
* @since ContractManage
 */
@Service("taskService")
public class TaskService extends CommonService<TbTask> implements ITaskService{
	
	private static final Logger LOGGER = Logger.getLogger(TaskService.class);

	@Autowired(required=true)
	private TaskDAO taskDAO;
	@Autowired(required=true)
	private UserDAO userDAO;
	@Autowired(required=true)
	private ProjectDAO projectDAO;
	@Autowired(required=true)
	private ContractService contractService;
	@Autowired(required=true)
	private ContractTemporaryService contractTemporaryService;
	
	@Override
	public CommonDAO<TbTask> getBindDao() {
		return taskDAO;
	}

	/**
	 * 我的代办任务
	 */
	@Override
	public PageInfo<TaskVo> pageInfo(TbTask entity,PageInfo<TbTask> pageInfo) {
		String hql = " from TbTask where state=? and personInChargeId.id=? order by allocationTime desc ";
		//分页查询
		PageInfo<TbTask> pageInfoList = taskDAO.findByPage(pageInfo,hql, entity.getState(),entity.getPersonInChargeId().getId());
		List<TaskVo> volist = new ArrayList<TaskVo>();
		List<TbTask> taskList = pageInfoList.getRows();
		for (int i = 0; i < taskList.size(); i++) {
			TbTask tbtask = taskList.get(i);
			TaskVo target = new TaskVo();
			EntityToVoUtil.copyObjValue(tbtask, target);
			target.setTaskID(""+tbtask.getId());
			target.setSponsorName(tbtask.getSponsorId().getUserName());
			target.setPersonInChargeName(tbtask.getPersonInChargeId().getUserName());
			if(!BlankUtil.isBlank(tbtask.getProject())){
				target.setProjectName(tbtask.getProject().getProjectName());
			}
			target.setRoleId(""+tbtask.getPersonInChargeId().getTbRole().getId());
			volist.add(target);
		}
		PageInfo<TaskVo> taskVoPage = new PageInfo<TaskVo>(pageInfo.getCurrentPage(),pageInfoList.getTotal(),pageInfo.getPageSize(),volist);
		return taskVoPage;
	}

	/**
	 * 提交任务，修改任务状态为1
	 */
	@Override
	public void submitTask(TbTask model) throws Exception {
		try {
			model = getByPk(model.getId());
			model.setState(1);
			model.setFinishTime(new Date());
			taskDAO.merge(model);
			LOGGER.info("submitTask success.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 转交任务,修改负责人
	 * @throws Exception 
	 */
	@Override
	public void forwardedTask(TaskVo vo) throws Exception {
		try {
			long pkid=0;
			long personInChargeId=0;
			if(BlankUtil.isBlank(vo)){
				LOGGER.info("forwardedTask fail.");
				throw new NullPointerException("VO is NULL Exception:");
			}
			if(!BlankUtil.isBlank(vo.getTaskID())){
				pkid = Long.parseLong(vo.getTaskID());
			}
			if(!BlankUtil.isBlank(vo.getPersonInChargeId())){
				personInChargeId = Long.parseLong(vo.getPersonInChargeId());
			}
			TbTask entity = getByPk(pkid);
			if(BlankUtil.isBlank(entity)){
				LOGGER.info("GetByPk entity is NULL.");
				throw new NullPointerException("entity is NULL:");
			}
			TbUser user = userDAO.findById(personInChargeId);
			entity.setPersonInChargeId(user);  //设置负责人
			taskDAO.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	* 方法用途和描述: 启动一个任务，给默认的处理接口人进行消息提示
	* @param taskType  任务类型，可以为空
	* @param sponsorid  发起人
	* @param taskName  任务名称
	* @param projectId  项目
	* @param forwardType 跳转类型（该类型已经定义为常量）
	* @param personInChargeId 负责人
	* @param remark 备注
	* @param param 参数
	* @throws Exception  
	* @author chenliang 新增日期：2013-4-2
	* @since ContractManage
	 */
	@Override
	public void startTask(String taskType, Long sponsorid, String taskName,
			Integer projectId,Long personInChargeId, Integer forwardType,String remark,String param,String paramPkid) throws Exception {
		try {
			TbUser user = null;
			TbTask task = new TbTask();
			task.setTaskType(taskType); //任务类型
			if(!BlankUtil.isBlank(sponsorid)){
				user = new TbUser();  
				user.setId(sponsorid);
				task.setSponsorId(user);  //发起人
			}
			task.setTaskName(taskName); //任务名称
			if(!BlankUtil.isBlank(projectId)){
				TbProject p = new TbProject();
				p.setProjectId(projectId);
				task.setProject(p);//项目
			}
			if(!BlankUtil.isBlank(personInChargeId)){
				user = new TbUser();
				user.setId(personInChargeId);
				task.setPersonInChargeId(user); //负责人
			}
			task.setAllocationTime(new Date()); //分配时间
			task.setState(0); //任务状态
			task.setRemark(remark); //备注
			LOGGER.info("BusinessConstants.task_url"+BusinessConstants.task_url.get(forwardType));
			String url = BusinessConstants.task_url.get(forwardType);
			if(!BlankUtil.isBlank(param)){
				url += param;
			}
			task.setUrl(url); //请求地址
			task.setParamPkid(paramPkid);//参数主键id
			taskDAO.save(task);
			LOGGER.info("save success");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	/**
	 * 根据项目查询项目的流程
	 * @param projectId  项目ID
	 * @throws Exception 
	 */
	@Override
	public  PageInfo<TaskVo> listTask(PageInfo<TaskVo> pageInfo,Integer projectId)
	{
		StringBuffer sb=new StringBuffer();
		StringBuffer counthql=new StringBuffer();
		sb.append(" from TbTask ta where 1=1 ");
		counthql.append(" select count(*) from TbTask ta  where 1=1 ");
		if(!BlankUtil.isBlank(projectId))
		{
			sb.append(" and ta.project.projectId =?");
			counthql.append(" and ta.project.projectId =?");
		}
		PageInfo<TbTask> p = new PageInfo<TbTask>();
		p.setCurrentPage(pageInfo.getCurrentPage());
		p.setPageSize(pageInfo.getPageSize());
		List<TbTask> list=taskDAO.pageInfoQuery(sb.toString(), p, projectId);
		int count = taskDAO.findTotalCount(counthql.toString(),projectId);
		List<TaskVo> listTaskVo=new ArrayList<TaskVo>();
		for(TbTask task:list)
		{
			TaskVo taskVo=new TaskVo();
			taskVo.setTaskID(""+task.getId());
			taskVo.setTaskName(task.getTaskName());
			taskVo.setProjectName(task.getProject().getProjectName());
			taskVo.setSponsorName(task.getSponsorId().getUserName());
			taskVo.setState(""+task.getState());
			taskVo.setRemark(task.getRemark());
			listTaskVo.add(taskVo);
		}
		PageInfo<TaskVo> page = new PageInfo<TaskVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listTaskVo);
        return page;
	}

	@Override
	public void checkContract(CheckedVo checkVo) {
		Integer type = Integer.parseInt(checkVo.getType());
		Integer contractId =  Integer.parseInt(checkVo.getId());
		String describe = checkVo.getDescribe();
		String result = checkVo.getResult();
		if(type==null || contractId==null ||describe==null ||result==null){
			return ; 
		}
		//正式合同
		try{
			if(type==1){
				contractService.checkContract(checkVo);
			}else if(type==2){//临时合同
				contractTemporaryService.checkContract(checkVo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void finishTask(TaskVo taskVo) {
		if(null==taskVo){
			LOGGER.info("FinishTask method the taskVo is null.");
			return;
		}
		//根据主键id查询任务
		String hql = "update TbTask set state=1,finishTime=NOW() where paramPkid=? ";
		taskDAO.executeByHQL(hql, taskVo.getParamPkid());
//		TbTask taskEntity = taskDAO.findByField("paramPkid", taskVo.getParamPkid());
//		if(null!=taskEntity){
//			taskEntity.setState(1);
//			taskEntity.setFinishTime(new Date());
//			taskDAO.merge(taskEntity);
//		}
		LOGGER.info("FinishTask success.");
	}
}
