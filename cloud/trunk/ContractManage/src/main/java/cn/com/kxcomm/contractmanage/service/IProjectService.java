package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbProject;
import cn.com.kxcomm.contractmanage.vo.ProjectVo;

public interface IProjectService extends ICommonService<TbProject>{
	public PageInfo<ProjectVo> listProject(PageInfo<ProjectVo> pageInfo,Long loginUserId);
	public ProjectVo queryProjectById(String id);
	public void merge(TbProject project);
	public List<ProjectVo> queryAllProject();
}
