package jeecg.demo.controller.test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.demo.entity.test.JpPersonEntity;
import jeecg.demo.service.test.JpPersonServiceI;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.excel.ExcelExportUtil;
import org.jeecgframework.core.util.excel.ExcelUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: Excel导出
 * @author zhangdaihao
 * @date 2013-03-23 21:45:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jpPersonController")
public class JpPersonController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JpPersonController.class);

	@Autowired
	private JpPersonServiceI jpPersonService;
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
	 * Excel导出列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jpPerson")
	public ModelAndView jpPerson(HttpServletRequest request) {
		return new ModelAndView("jeecg/demo/test/jpPersonList");
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(HttpServletRequest request, HttpServletResponse response) {
		 HttpSession session = request.getSession();
	        // 生成提示信息，
	        response.setContentType("application/vnd.ms-excel");
	        String codedFileName = null;
	        OutputStream fOut = null;
	        try
	        {
	            // 进行转码，使其支持中文文件名
	            codedFileName = java.net.URLEncoder.encode("用户信息", "UTF-8");
	            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
	            // 产生工作簿对象
	            HSSFWorkbook workbook = null ;
	            List<JpPersonEntity> jpPersons = this.jpPersonService.loadAll(JpPersonEntity.class);
	            workbook =  ExcelExportUtil.exportExcel("导出信息", JpPersonEntity.class, jpPersons);
	            fOut = response.getOutputStream();
	            workbook.write(fOut);
	        }
	        catch (UnsupportedEncodingException e1)
	        {
	            
	        }
	        catch (Exception e)
	        {
	            
	        }
	        finally
	        {
	            try
	            {
	                fOut.flush();
	                fOut.close();
	            }
	            catch (IOException e)
	            {
	                
	            }
	        }
	    }
	
	//----------------------------------------------------------------
	//update-begin--Author:shiyanping  Date:20130327 for：excel导入
	//update-end--Author:shiyanping  Date:20130327 for：excel导入
	//----------------------------------------------------------------
	@RequestMapping(params = "goImplXls")
    public ModelAndView goImplXls(HttpServletRequest request) {
	    return  new ModelAndView("jeecg/demo/test/upload");
    }
	
	@RequestMapping(params = "implXls")
	@ResponseBody
    public AjaxJson implXls(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mulRequest.getFile("filedata");
        List<JpPersonEntity> listPersons;
        try {
            listPersons = (List<JpPersonEntity>) ExcelUtil.importExcelByIs(file.getInputStream(), JpPersonEntity.class);
            for (JpPersonEntity person : listPersons) {
                person.setId(UUID.randomUUID().toString());
                jpPersonService.save(person);
            }
            j.setMsg("文件导入成功！");    
        } catch (IOException e) {
            j.setMsg("文件导入失败！");            
            logger.error(ExceptionUtil.getExceptionMessage(e));     
        }
        return j;
    }
	    @RequestMapping("check")
	    public void check(HttpServletRequest request, HttpServletResponse response)
	    {
	        try
	        {
	            if ("open".equals(request.getSession().getAttribute("state")))
	            {
	                request.getSession().setAttribute("state", null);
	                response.getWriter().write("true");
	                response.getWriter().flush();
	            }
	            else
	            {
	                response.getWriter().write("false");
	                response.getWriter().flush();
	            }
	        }
	        catch (IOException e)
	        {}
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
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JpPersonEntity.class, dataGrid);
		this.jpPersonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Excel导出
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JpPersonEntity jpPerson, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jpPerson = systemService.getEntity(JpPersonEntity.class, jpPerson.getId());
		message = "删除成功";
		jpPersonService.delete(jpPerson);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Excel导出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JpPersonEntity jpPerson, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jpPerson.getId())) {
			message = "更新成功";
			jpPersonService.saveOrUpdate(jpPerson);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jpPersonService.save(jpPerson);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Excel导出列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JpPersonEntity jpPerson, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jpPerson.getId())) {
			jpPerson = jpPersonService.getEntity(JpPersonEntity.class, jpPerson.getId());
			req.setAttribute("jpPersonPage", jpPerson);
		}
		return new ModelAndView("jeecg/demo/test/jpPerson");
	}
}
