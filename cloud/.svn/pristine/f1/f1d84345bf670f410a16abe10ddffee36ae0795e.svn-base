package cn.com.kxcomm.woyun.web.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.ProjectsService;
import cn.com.kxcomm.woyun.vo.NowUseTentidVo;
import cn.com.kxcomm.woyun.vo.ProjectsVO;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;

public class ProjectsAction extends BaseAction<ProjectsVO, String> {

	private static Logger LOGGER = Logger.getLogger(ProjectsAction.class);
	
	@Autowired(required=true)
	private ProjectsService projectsService;
	
	//用于返回前台的对象
	private JSONObject result;

	private String projectId;
	
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	/**
	 * 
	* 方法用途和描述:获取项目集合
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	public String listProjects(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<ProjectsVO> volist = this.projectsService.listProjects(access.getToken().getId());
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("rows",volist);
				map.put("total",volist.size());
				this.result = JSONObject.fromObject(map);
				LOGGER.debug(""+this.result.toString());
			}else{
				LOGGER.debug("listSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listsecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述:根据项目ID获取项目
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	public String queryProjectById() {
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				this.model = this.projectsService.queryProjectById(access.getToken().getId(),this.projectId);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("projectVo",this.model);
				this.result = JSONObject.fromObject(map);
				LOGGER.debug(""+this.result.toString());
			}else{
				LOGGER.debug("ProjectVo access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listsecurityGroup error.",e);
		}
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述:修改项目
	* @return
	* @author lizl 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	public String updateProject() {
		System.out.println(getModel().getEnabled()+"    Enabled:    "+this.projectId);
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		if("true".equals(this.projectId)) {
			getModel().setEnabled(true);
		} else {
			getModel().setEnabled(false);
		}
		String end = this.projectsService.updateProject(access.getToken().getId(),getModel());
			Map map = new HashMap();
			map.put("msg",end);
			this.result = JSONObject.fromObject(map);
			log.info("End:   "+this.result.toString());
		return successInfo("null");
	}
	
	@Override
	public CommonService getCommonService() {
		return projectsService;
	}

	@Override
	public ProjectsVO getModel() {
		if(null == this.model){
			this.model = new ProjectsVO();
		}
		return this.model;
	}

	@Override
	public void setModel(ProjectsVO model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
