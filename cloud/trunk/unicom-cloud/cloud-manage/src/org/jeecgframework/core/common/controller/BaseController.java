package org.jeecgframework.core.common.controller;

import java.util.Date;

import org.jeecgframework.core.interceptors.DateConvertEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * 
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd hh:mm:ss");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, true));
		
		//-----update-begin---- author:zhangdaihao  date:20130227 for:时间转换问题
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		//-----update-end---- author:zhangdaihao  date:20130227 for:时间转换问题
	}

}
