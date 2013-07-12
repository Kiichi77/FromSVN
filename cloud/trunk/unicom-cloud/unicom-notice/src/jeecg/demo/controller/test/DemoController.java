package jeecg.demo.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jeecg.system.pojo.base.TSDemo;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @ClassName: demoController
 * @Description: TODO(演示例子处理类)
 * @author jeecg
 */
@Controller
@RequestMapping("/demoController")
public class DemoController extends BaseController {
	private static final Logger logger = Logger.getLogger(DemoController.class);
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * demo添加页面跳转
	 */
	@RequestMapping(params = "aorudemo")
	public ModelAndView aorudemo(TSDemo demo, HttpServletRequest request) {
		String type = oConvertUtils.getString(request.getParameter("type"));
		if (demo.getId() != null) {
			demo = systemService.getEntity(TSDemo.class, demo.getId());
			request.setAttribute("demo", demo);
		}
		if (type.equals("table")) {
			return new ModelAndView("demo/tabledemo");
		} else {
			return new ModelAndView("demo/demo");
		}

	}
	
	/**
	 * 父级DEMO下拉菜单
	 */
	@RequestMapping(params = "pDemoList")
	@ResponseBody
	public List<ComboTree> pDemoList(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		if (comboTree.getId() != null) {
			cq.eq("TSDemo.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSDemo");
		}
		cq.add();
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "demotitle", "tsDemos", "demourl");
		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null);
		return comboTrees;
	}

	/**
	 * demo页面跳转
	 */
	@RequestMapping(params = "demoIframe")
	public ModelAndView demoIframe(HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		cq.isNull("TSDemo.id");
		cq.add();
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		request.setAttribute("demoList", demoList);
		return new ModelAndView("demo/demoIframe");
	}

	/**
	 * demo页面跳转
	 */
	@RequestMapping(params = "demoList")
	public ModelAndView demoList(HttpServletRequest request) {
		return new ModelAndView("demo/demoList");
	}

	
	/**
	 * 权限列表
	 */
	@RequestMapping(params = "demoGrid")
	@ResponseBody
	public List<TreeGrid> demoGrid(HttpServletRequest request, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		if (treegrid.getId() != null) {
			cq.eq("TSDemo.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSDemo");
		}
		cq.add();
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("demotitle");
		treeGridModel.setParentText("TSDemo_demotitle");
		treeGridModel.setParentId("TSDemo_id");
		treeGridModel.setSrc("demourl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("tsDemos");
		List<TreeGrid> treeGrids = systemService.treegrid(demoList, treeGridModel);
		return treeGrids;
	}

	/**
	 * demoCode页面跳转
	 */
	@RequestMapping(params = "demoCode")
	public ModelAndView demoCode(TSDemo demo, HttpServletRequest request) {
		demo = systemService.getEntity(TSDemo.class, demo.getId());
		request.setAttribute("demo", demo);
		return new ModelAndView("demo/democode");
	}
	
	/**
	 * AJAX 示例下拉框
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getDemo")
	@ResponseBody
	public AjaxJson getDemo(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String id = StringUtil.getEncodePra(req.getParameter("id"));
		String floor = "";
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		cq.eq("TSFunction.id", id);
		cq.add();
		List<TSFunction> functions = systemService.getListByCriteriaQuery(cq, false);
		if (functions.size() > 0) {
			for (TSFunction function : functions) {
				floor += "<input type=\"checkbox\"  name=\"floornum\" id=\"floornum\" value=\"" + function.getId() + "\">" + function.getFunctionName() + "&nbsp;&nbsp;";
			}
		} else {
			floor += "没有子项目!";
		}

		j.setMsg(floor);
		return j;
	}

	
	/**
	 * 上传TABS跳转
	 */
	@RequestMapping(params = "uploadTabs")
	public ModelAndView uploadTabs(HttpServletRequest request) {
		return new ModelAndView("demo/upload/uploadTabs");
	}
	/**
	 * 图片预览TABS跳转
	 */
	@RequestMapping(params = "imgViewTabs")
	public ModelAndView imgViewTabs(HttpServletRequest request) {
		return new ModelAndView("demo/picview/imgViewTabs");
	}
	/**
	 * 表单验证TABS跳转
	 */
	@RequestMapping(params = "formTabs")
	public ModelAndView formTabs(HttpServletRequest request) {
		return new ModelAndView("demo/formvalid/formTabs");
	}
	/**
	 * 动态模板TABS跳转
	 */
	@RequestMapping(params = "templeteTabs")
	public ModelAndView templeteTabs(HttpServletRequest request) {
		return new ModelAndView("demo/template/templateiframe");
	}
	/**
	 * 上传演示
	 */
	@RequestMapping(params = "autoupload")
	public ModelAndView autoupload(HttpServletRequest request) {
		String turn=oConvertUtils.getString(request.getParameter("turn"));
		return new ModelAndView("demo/"+turn+"");
	}

	/**
	 *下拉联动跳转
	 */
	@RequestMapping(params = "select")
	public ModelAndView select(HttpServletRequest request) {
		// 新闻
		CriteriaQuery cq2 = new CriteriaQuery(TSFunction.class);
		cq2.eq("functionLevel",Globals.Function_Leave_ONE);
		cq2.add();
		List<TSFunction> funList = systemService.getListByCriteriaQuery(cq2, true);
		request.setAttribute("funList", funList);
		return new ModelAndView("demo/AJAX/select");
	}
}
