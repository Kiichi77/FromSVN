package cn.com.kxcomm.base.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * 
* 功能描述:异常日志拦截器，用于对系统产生的异常信息进行日志文件记录
* <p>版权所有：金鹏科技
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author dengcd 新增日期：2008-10-14
* @author 你的姓名 修改日期：2008-10-14
* @since wapportal_manager version(2.0)
 */
public class ExceptionLoggerInterceptor{
	private static Logger _log = Logger.getLogger(ExceptionLoggerInterceptor.class);
    
	/**
	 * 
	* 方法用途和描述: 对异常进行写日志操作
	* @param joinPoint
	* @param throwable
	* @author dengcd 新增日期：2008-10-14
	* @author 你的姓名 修改日期：2008-10-14
	* @since wapportal_manager version(2.0)
	 */
    public void afterThrowing (JoinPoint joinPoint, Throwable throwable) {   
        System.out.println("Logging when throwing " + joinPoint.getSignature().getName());   
        _log.error("**********************begin***************************"); 
        _log.error("Logging when throwing " + joinPoint.getSignature().getName()); 
        _log.error("Error happened in class: " + joinPoint.getTarget().getClass().getName()); 
        _log.error("Error toString: " + throwable.toString()); 
        _log.error("Error getLocalizedMessage: " + getStackTrace(throwable.getStackTrace())); 
        _log.error("***********************end****************************"); 
    }   
    
    public String getStackTrace(StackTraceElement[] StackTraceElements){
    	StringBuilder sb = new StringBuilder();
    	for(StackTraceElement element:StackTraceElements){
    		sb.append(element.getClassName());
    		sb.append(".");
    		sb.append(element.getMethodName());
    		sb.append("(");
    		sb.append(element.getFileName());
    		sb.append(":");
    		sb.append(element.getLineNumber());
    		sb.append(")\n");
    	}
    	return sb.toString();
    }

   
    

}
