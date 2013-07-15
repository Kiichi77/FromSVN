package jeecg.test.controller.unicom;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.system.service.SystemService;
import jeecg.test.entity.unicom.UnicomNoticeEntity;
import jeecg.test.service.unicom.UnicomNoticeServiceI;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 发布公告
 * @author zhangdaihao
 * @date 2013-05-24 14:23:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/unicomNoticeController")
public class UnicomNoticeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UnicomNoticeController.class);

	@Autowired
	private UnicomNoticeServiceI unicomNoticeService;
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
	 * 发布公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "unicomNotice")
	public ModelAndView unicomNotice(HttpServletRequest request) {
		return new ModelAndView("jeecg/test/unicom/unicomNoticeList");
	}

	/**
	 * 查看公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "unicomNoticeLook")
	public ModelAndView unicomNoticeLook(HttpServletRequest request) {
		return new ModelAndView("jeecg/test/unicom/unicomNoticeLook");
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
	public void datagrid(UnicomNoticeEntity unicomNotice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UnicomNoticeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, unicomNotice);
		this.unicomNoticeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发布公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(UnicomNoticeEntity unicomNotice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		unicomNotice = systemService.getEntity(UnicomNoticeEntity.class, unicomNotice.getId());
		message = "删除成功";
		unicomNoticeService.delete(unicomNotice);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发布公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(UnicomNoticeEntity unicomNotice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		 HttpSession session =  request.getSession();
		 String fileName = (String)session.getAttribute("fileName");
		 if(fileName!=null&&!"".equals(fileName)){
			 System.out.println("save->fileName:"+fileName);
			 unicomNotice.setDownload(fileName);
		 }
		if (StringUtil.isNotEmpty(unicomNotice.getId())) {
			message = "更新成功";
			UnicomNoticeEntity t = unicomNoticeService.get(UnicomNoticeEntity.class, unicomNotice.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(unicomNotice, t);
				unicomNoticeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			unicomNotice.setCreateTime(new Date());
			unicomNoticeService.save(unicomNotice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 发布公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(UnicomNoticeEntity unicomNotice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(unicomNotice.getId())) {
			unicomNotice = unicomNoticeService.getEntity(UnicomNoticeEntity.class, unicomNotice.getId());
			req.setAttribute("unicomNoticePage", unicomNotice);
		}
		return new ModelAndView("jeecg/test/unicom/unicomNotice");
	}
	
	@RequestMapping(params = "lookById")
	public ModelAndView lookById(UnicomNoticeEntity unicomNotice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(unicomNotice.getId())) {
			unicomNotice = unicomNoticeService.getEntity(UnicomNoticeEntity.class, unicomNotice.getId());
			req.setAttribute("unicomNoticePage", unicomNotice);
		}
		return new ModelAndView("jeecg/test/unicom/unicomNoticeInfo");
	}
	
	  /**
	    * 上传文件
	    * 
	    * @param request
	    * @return
	    * @throws Exception
	    */
	   @RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	   @ResponseBody
	   public AjaxJson saveFiles(HttpServletRequest request) throws Exception {

	           // 把压缩包里面的jar拷贝到工程的lib下面即可 主要方法介绍 
	           //  新建一个SmartUpload对象 
	           AjaxJson j = new AjaxJson();

	           String fileName = null;
	           UploadFile uploadFile = new UploadFile(request);
	           String ctxPath = request.getSession().getServletContext().getRealPath("upload");
	           File file = new File(ctxPath);
	           if (!file.exists()) {
	                   file.mkdir();// 创建文件根目录
	           }
	           MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
	           Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	           for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
	                   MultipartFile mf = entity.getValue();// 获取上传文件对象
	                   fileName = System.currentTimeMillis()+mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."), mf.getOriginalFilename().length());// 获取文件名
	                   String savePath = file.getPath() + "/" + fileName;// 上传后的文件绝对路径
	                   System.out.println("上传后路径："+savePath);
	                   File savefile = new File(savePath);
	                   try {
	                           FileCopyUtils.copy(mf.getBytes(), savefile);
	                   } catch (IOException e) {
	                           e.printStackTrace();
	                   }
	           }
	           HttpSession session =  request.getSession();
	           session.setAttribute("fileName", fileName);
	           System.out.println("upload->fileName:"+fileName);
	           message = "上传成功";
	           j.setMsg(message);
	           return j;
	   }
}
