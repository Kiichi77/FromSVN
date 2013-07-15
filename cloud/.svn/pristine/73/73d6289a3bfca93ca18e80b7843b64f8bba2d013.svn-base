package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.kxcomm.woyun.vo.ProjectsVO;

/**
 * 项目管理
 * @author lizl
 */
public interface ProjectsService extends CommonService<ProjectsVO> {

	//获取所有项目
	public List<ProjectsVO> listProjects(String tokenId) throws Exception;
	//根据ID获得对应项目
	public ProjectsVO queryProjectById(String tokenId,String tenantId) throws Exception;
	//修改项目
	public String updateProject(String tokenId,ProjectsVO projectsVO);
}
