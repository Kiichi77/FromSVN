package cn.com.kxcomm.contractmanage.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IProjectService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.impl.ActivitiService;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

public class ProjectAction extends BaseAction<TbProject, String>{
	private static Logger LOGGER = Logger.getLogger(ProjectAction.class);
    
	@Autowired(required = true)
	private IProjectService projectService;
	@Autowired(required = true)
	private ActivitiService activitiService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	private ProjectVo projectVo;
	
	private TbProject project;
	
	private String userName;
	
	private String customerId;
	
	private String id;
	
	private String user_id;
	
	private String process_id;
	
	public String getProcess_id() {
		return process_id;
	}


	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public TbProject getProject() {
		return project;
	}


	public void setProject(TbProject project) {
		this.project = project;
	}


	public IProjectService getProjectService() {
		return projectService;
	}


	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}


	public ProjectVo getProjectVo() {
		return projectVo;
	}


	public void setProjectVo(ProjectVo projectVo) {
		this.projectVo = projectVo;
	}


	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}


	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}


	/**
	   * 
	  * 方法用途和描述: 分页获取所有的客户
	  * @return
	  * @author lujia 新增日期：2013-2-25
	  * @since ContractManage
	   */
	public String listProject(){
		 try {
		   PageInfo<ProjectVo> page =  new PageInfo<ProjectVo>(ServletActionContext.getRequest());
		   PageInfo<ProjectVo> projectList=projectService.listProject(page,null);
		   result = JSONObject.fromObject(projectList);
		} catch (Exception e) {
			LOGGER.error("listProject ERROR:",e);
		}
	    return SUCCESS;
	}
	
	/**
	 * 
	 * 返回我的项目
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-23
	 * @since ContractManage
	 */
	public String myProject(){
		 try {
		   PageInfo<ProjectVo> page =  new PageInfo<ProjectVo>(ServletActionContext.getRequest());
		   HttpSession session = getHttpSession();
		   Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		   PageInfo<ProjectVo> projectList=projectService.listProject(page,userId);
		   result = JSONObject.fromObject(projectList);
		} catch (Exception e) {
			LOGGER.error("listProject ERROR:",e);
		}
	    return SUCCESS;
	}
	
	   /**
	    * 
	   * 方法用途和描述: 添加项目
	   * @return
	   * @author lujia 新增日期：2013-2-25
	   * @since ContractManage
	    */
	public String addProject()
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
	 try {
		project.setCreatetime(new Date());
		TbUser user=new TbUser();
		user.setId(userId);
        project.setCreator(user);	
        
        TbCustomer customer=new TbCustomer();
        customer.setCustomerId(project.getCustomer().getCustomerId());
        project.setCustomer(customer);
		projectService.save(project);
		systemLogService.writeTheLog(SystemLogType.task_submit, userId, "成功", null, "项目ID为："+project.getProjectId());
		result = JSONObject.fromObject(successInfo("ok"));
		  }catch(Exception e)
		  {
			  LOGGER.error("addProject ERROR:", e);
			  try {
				systemLogService.writeTheLog(SystemLogType.task_submit, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		  }
		  return SUCCESS;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个项目信息
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
    public String queryProjectById() {
    	LOGGER.info("modelId:  "+this.id);
    	 projectVo = this.projectService.queryProjectById(id);
    	Map map = new HashMap();
		map.put("project",projectVo);
		this.result = JSONObject.fromObject(map);//
    	return SUCCESS;
    }
	
    /**
	    * 
	   * 方法用途和描述: 修改项目信息
	   * @return
	   * @author lujia 新增日期：2013-2-26
     * @throws ParseException 
	   * @since ContractManage
	    */
	public String updateProject() 
	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		if(!BlankUtil.isBlank(project)){
			Integer id = project.getProjectId();
			TbProject tbProject = this.projectService.getByPk(id);
		//	System.out.println("userName:"+tbProject.getCreator().getUserName());
			tbProject.setDescription(project.getDescription());
			tbProject.setProjectName(project.getProjectName());
			TbCustomer customer = new TbCustomer();
			customer.setCustomerId(project.getCustomer().getCustomerId());
			tbProject.setCustomer(customer);
			projectService.merge(tbProject);
			systemLogService.writeTheLog(SystemLogType.task_submit, userId, "成功", null, "项目ID为："+id);
			result = JSONObject.fromObject(successInfo("ok"));
		}else {
			result = JSONObject.fromObject(successInfo("no"));
		}
		} catch (Exception e) {
			try {
				systemLogService.writeTheLog(SystemLogType.task_submit, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 获取所有的项目
	* @return
	* @author lujia 新增日期：2013-2-26
	* @since ContractManage
	 */
	public String queryAllProject() {
		try {
			PageInfo<ProjectVo> page = new PageInfo<ProjectVo>(ServletActionContext.getRequest());
			List list = new ArrayList();
			list=this.projectService.queryAllProject();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			LOGGER.error("queryAllProject ERROR:", e);
		}
		return SUCCESS;
	}
	@Override
	public ICommonService getCommonService() {
		return projectService;
	}

	@Override
	public TbProject getModel() {
		if(this.model==null){
			 this.model = new TbProject();
		}
		return this.model;
	}

	@Override
	public void setModel(TbProject model) {
		this.model=model;
		
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
		
	}

}
