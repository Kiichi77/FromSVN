package jeecg.test.controller.unicom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import jeecg.system.pojo.base.TSDepart;
import jeecg.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import jeecg.test.entity.unicom.UnicomgdCloudResourcesEntity;
import jeecg.test.service.unicom.UnicomgdCloudResourcesServiceI;

/**   
 * @Title: Controller
 * @Description: 云平台资源管理
 * @author zhangdaihao
 * @date 2013-05-24 14:47:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/unicomgdCloudResourcesController")
public class UnicomgdCloudResourcesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UnicomgdCloudResourcesController.class);

	@Autowired
	private UnicomgdCloudResourcesServiceI unicomgdCloudResourcesService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 云平台资源管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "unicomgdCloudResources")
	public ModelAndView unicomgdCloudResources(HttpServletRequest request) {
		return new ModelAndView("jeecg/test/unicom/unicomgdCloudResourcesList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(UnicomgdCloudResourcesEntity unicomgdCloudResources,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UnicomgdCloudResourcesEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, unicomgdCloudResources);
		this.unicomgdCloudResourcesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除云平台资源管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(UnicomgdCloudResourcesEntity unicomgdCloudResources, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		unicomgdCloudResources = systemService.getEntity(UnicomgdCloudResourcesEntity.class, unicomgdCloudResources.getId());
		message = "删除成功";
		unicomgdCloudResourcesService.delete(unicomgdCloudResources);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加云平台资源管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(UnicomgdCloudResourcesEntity unicomgdCloudResources, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(unicomgdCloudResources.getId())) {
			message = "更新成功";
			UnicomgdCloudResourcesEntity t = unicomgdCloudResourcesService.get(UnicomgdCloudResourcesEntity.class, unicomgdCloudResources.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(unicomgdCloudResources, t);
				unicomgdCloudResourcesService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			unicomgdCloudResourcesService.save(unicomgdCloudResources);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 云平台资源管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(UnicomgdCloudResourcesEntity unicomgdCloudResources, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(unicomgdCloudResources.getId())) {
			unicomgdCloudResources = unicomgdCloudResourcesService.getEntity(UnicomgdCloudResourcesEntity.class, unicomgdCloudResources.getId());
			req.setAttribute("unicomgdCloudResourcesPage", unicomgdCloudResources);
		}
		return new ModelAndView("jeecg/test/unicom/unicomgdCloudResources");
	}
}
