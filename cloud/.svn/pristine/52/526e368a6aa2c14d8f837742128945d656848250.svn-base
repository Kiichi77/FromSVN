package cn.com.kxcomm.exception;

import cn.com.kxcomm.ipmitool.entity.Errors;
import cn.com.kxcomm.ipmitool.exception.SeriousError;
import cn.com.kxcomm.ipmitool.exception.SeriousException;
import cn.com.kxcomm.ipmitool.util.ErrorCode;

/**
 * 
* 异常类测试
* @author zhangjh 新增日期：2012-8-23
* @since ipmi_sdk
 */
public class ExceptionTest {
	public static boolean testException() throws SeriousException{
		Errors e = new Errors(ErrorCode.HOST_NOT_EXIST,"Host Unreachable,Please check your network.");
		throw new SeriousException(e);
	}
	
	public static boolean testError() throws SeriousError{
		Errors e = new Errors(ErrorCode.HOST_NOT_EXIST,"Host Unreachable,Please check your network.");
		throw new SeriousError(e);
	}
	
	public static void main(String[] args){
		Errors er =null ;
		try{
			ExceptionTest.testException();
		}catch(SeriousException e ){
			System.out.println(e.getMessage());
			er = e.getError();
			
		}
		System.out.println(er.toString());
		
		ExceptionTest.testError();
		System.out.println(er.toString());
	}
}
