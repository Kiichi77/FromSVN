package cn.com.kxcomm.contractmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.dao.CommonDAO;
import cn.com.kxcomm.contractmanage.dao.ProjectDAO;
import cn.com.kxcomm.contractmanage.entity.TbCustomer;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.service.IProjectService;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;
import cn.com.kxcomm.contractmanage.vo.QuotationsVo;

@Service("projectService")
public class ProjectService extends CommonService<TbProject> implements IProjectService{
	@Autowired(required=true)
	private ProjectDAO projectDAO;
	
	@Override
	public CommonDAO<TbProject> getBindDao() {
		return projectDAO;
	}
	/**
	* 
	* 方法用途和描述: 分页查询所有的项目
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
	public PageInfo<ProjectVo> listProject(PageInfo<ProjectVo> pageInfo,Long loginUserId) {
		StringBuffer sql = new StringBuffer();
	    sql.append(" SELECT p.project_id,p.create_time,p.description,p.project_name,c.company_name,u.user_name ");
	    sql.append(" FROM tb_project p,tb_customer c,tb_user u ");
	    sql.append(" WHERE p.customer_id=c.id AND p.creator_id=u.id ");
	    if(loginUserId!=null){
	    	 sql.append(" and p.creator_id ="+loginUserId);
	    }
	   
	    StringBuffer countSql = new StringBuffer();
	    countSql.append(" select count(*) as count from ("+sql.toString()+") tt ");
	    int count = projectDAO.findCount(countSql.toString());
		List list = projectDAO.findByPage(sql.toString(), pageInfo);
		List<ProjectVo> listProject=new ArrayList<ProjectVo>();
		Object[] obj = new Object[list.size()];	
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ProjectVo projectVo=new ProjectVo();
			projectVo.setId(obj[0].toString());
		    projectVo.setCreatetime(obj[1].toString());
		    projectVo.setDescription(obj[2].toString());
		    projectVo.setProjectName(obj[3].toString());
		    projectVo.setCustomerName(obj[4].toString());
		    projectVo.setCreatorName(obj[5].toString());
			listProject.add(projectVo);
	}
		PageInfo<ProjectVo> page = new PageInfo<ProjectVo>(pageInfo.getCurrentPage(),count,pageInfo.getPageSize(),listProject);
		return page;
	}
	
	/**
	* 
	* 方法用途和描述: 根据ID查询某个项目信息
	* @return
	* @author luj 新增日期：2013-2-25
	* @since ContractManage
	*/
	@Override
	public ProjectVo queryProjectById(String id) {
		TbProject project = this.projectDAO.findById(Integer.parseInt(id));
		ProjectVo projectVo = new ProjectVo();
		projectVo.setId(""+project.getProjectId());
		projectVo.setCreatetime(""+project.getCreatetime());
		projectVo.setCreatorName(""+project.getCreator().getUserName());
		projectVo.setProjectName(""+project.getProjectName());
		projectVo.setDescription(""+project.getDescription());
		projectVo.setCustomerName(""+project.getCustomer().getCompanyName());
		return projectVo;
	}
	@Override
	public void merge(TbProject project) {
		projectDAO.merge(project);
	}
	
	/**
	* 
	* 方法用途和描述: 查询所有的项目
	* @return
	* @author luj 新增日期：2013-2-26
	* @since ContractManage
	*/
	public List<ProjectVo> queryAllProject() {
		StringBuffer sql = new StringBuffer();
		 sql.append(" SELECT p.project_id,p.create_time,p.description,p.project_name,c.company_name,u.user_name ");
		 sql.append(" FROM tb_project p,tb_customer c,tb_user u ");
		 sql.append(" WHERE p.customer_id=c.id AND p.creator_id=u.id ");
		List list = projectDAO.queryAllProject(sql.toString());
		List<ProjectVo> listProjectVo = new ArrayList<ProjectVo>();
		Object[] obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			ProjectVo projectVo=new ProjectVo();
			projectVo.setId(obj[0].toString());
		    projectVo.setCreatetime(obj[1].toString());
		    projectVo.setDescription(obj[2].toString());
		    projectVo.setProjectName(obj[3].toString());
		    projectVo.setCustomerName(obj[4].toString());
		    projectVo.setCreatorName(obj[5].toString());
			listProjectVo.add(projectVo);
		}
		return listProjectVo;
	}

	}

