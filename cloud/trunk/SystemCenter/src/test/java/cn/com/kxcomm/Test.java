package cn.com.kxcomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.com.kxcomm.systemcenter.domUser.service.DomUserService;
import cn.com.kxcomm.systemcenter.domUser.service.impl.DomUserServiceImpl;

public class Test {

	private DomUserService domUserService = DomUserServiceImpl.getInstance();
	
	/**
	 * 方法用途和描述:测试
	 * @param args
	 * @author chenliang 新增日期：2013-5-27
	 * @throws IOException 
	 * @since system_center_sdk
	 */
	public static void main(String[] args) throws IOException {
//		String cmdLine = "powershell get-process p*";
////		String cmdLine = "powershell -file \""+this.scriptPath+"\" get-performance";
//		String powerShell = "powershell -file C:/eclipse/test.ps1";
//		Process ps = Runtime.getRuntime().exec(cmdLine);
//		ps.getOutputStream().close(); // It seems that powershell first reads all input from it's input stream before going.
//		String rs = "", line;
//		BufferedReader rd = new BufferedReader(new InputStreamReader(ps.getInputStream()));
//		try{
//		    while((line = rd.readLine()) != null){
//		        rs += line+"\n";
//		    }
//		}finally{
//		    rd.close();
//		}
		new Test().listDomUser();
		//System.out.println("rs:"+rs);
	}
	
	
	public void listDomUser(){
		domUserService.listDomUser();
	}

}
