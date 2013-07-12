package cn.com.kxcomm.woyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.woyun.service.ProjectsService;
import cn.com.kxcomm.woyun.vo.ProjectsVO;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.keystone.model.Tenant;
import cn.com.woyun.keystone.service.TenantApi;

/**
 * 
* 功能描述:服务管理实现类
* @author lizl 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("projectsService")
public class ProjectsServiceImpl extends
		CommonServiceImpl<ProjectsVO> implements ProjectsService {

	private TenantApi tenantApi = TenantApi.getInstance();
	
	/**
	 * 
	* 方法用途和描述:获得所有的项目
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	@Override
	public List<ProjectsVO> listProjects(String tokenId) throws Exception {
		List<ProjectsVO> projectsVOs = new ArrayList<ProjectsVO>();
		List<Tenant> tenants = this.tenantApi.listTenants(tokenId);
		if(0 < tenants.size()) {
			ProjectsVO projectsVO = null;
			for(int i = 0;i < tenants.size(); i++) {
				projectsVO = new ProjectsVO();
				projectsVO.setId(tenants.get(i).getId());
				projectsVO.setName(tenants.get(i).getName());
				projectsVO.setEnabled(tenants.get(i).getEnabled());
				projectsVO.setDescription(tenants.get(i).getDescription());
				projectsVOs.add(projectsVO);
			}
		}
		return projectsVOs;
	}
	
	/**
	 * 
	* 方法用途和描述:根据ID获得对应项目
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	@Override
	public ProjectsVO queryProjectById(String tokenId,String tenantId) throws Exception {
		ProjectsVO projectsVO = new ProjectsVO();
		Tenant tenant = this.tenantApi.showTenant(tokenId,tenantId);
		if(null != tenant) {
			projectsVO.setId(tenant.getId());
			projectsVO.setName(tenant.getName());
			projectsVO.setEnabled(tenant.getEnabled());
			projectsVO.setDescription(tenant.getDescription());
		}
		return projectsVO;
	}
	
	/**
	 * 
	* 方法用途和描述:修改项目
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	@Override
	public String updateProject(String tokenId,ProjectsVO projectsVO){
		String end = "ok";
		Tenant tenant = new Tenant();
		tenant.setId(projectsVO.getId());
		tenant.setDescription(projectsVO.getDescription());
		tenant.setEnabled(projectsVO.getEnabled());
		tenant.setName(projectsVO.getName());
		try {
			this.tenantApi.updateTenant(tokenId,tenant);
		} catch (Exception exception) {
			exception.printStackTrace();
			end = "error";
		}
		return end;
	}
}
