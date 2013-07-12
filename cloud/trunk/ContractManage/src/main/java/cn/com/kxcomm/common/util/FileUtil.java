package cn.com.kxcomm.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.log4j.Logger;

public class FileUtil {
	public static Logger log = Logger.getLogger(FileUtil.class);
	private static FileUtil instance;
	
	private FileUtil() {
	}

	public static synchronized FileUtil getSingletonInstance() {
		if (instance == null) {
			instance = new FileUtil();
		}
		return instance;
	}
	
	/**
	 * 
	* 方法用途和描述: 移动文件(支持跨系统)
	* @param fold
	* @param fnew
	* @return
	* @author zhangjh 新增日期：2011-1-4
	* @author 你的姓名 修改日期：2011-1-4
	* @since zte_crbt_bi
	 */
	public static boolean moveFile(File fold,File fnew){
		boolean isSuccess = false;
		try{
		isSuccess = fold.renameTo(fnew);
		if(!isSuccess){
			isSuccess = mvFile(fold.getPath(),fnew.getPath());
		}
		}catch(Exception e){
			log.warn("移动文件失败",e);
		}
		return isSuccess;
	}
	
	/**
	 * 
	* 方法用途和描述: linux下移动文件方法(java移动文件失败后备用)
	* @param srcFile
	* @param descDir
	* @return
	* @author zhangjh 新增日期：2010-8-27
	* @author 你的姓名 修改日期：2010-8-27
	* @since zte_crbt_bi
	 */
	private static boolean mvFile(String srcFile, String descDir) {
		InputStreamReader ir = null;
		LineNumberReader input = null;
		String command = "";
		try {
			if("/".equals(File.separator)){
				command = "mv ";
			}else{
				return false;
			}
			Process process = Runtime.getRuntime().exec(
					command+srcFile+" "+descDir);
			
			ir = new InputStreamReader(process.getInputStream());
			input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				log.info("Runtime:" + line);
			}
		} catch (IOException ioe) {
			log.error("",ioe);
			return false;
		} finally {
			try {
				if (ir != null) {
					ir.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				log.error(command+srcFile+" "+descDir,e);
			}
		}
		return true;
	}

	/**
	 * 
	* 方法用途和描述: 检查文件夹是否以字符结束和创建该文件夹
	* @param path
	* @return
	* @author zhangjh 新增日期：2011-3-1
	* @author 你的姓名 修改日期：2011-3-1
	* @since zte_crbt_bi
	 */
	@SuppressWarnings("static-access")
	public static String checkDirectory(String path){
		File file = new File(path);
		if(!path.endsWith(file.separator)){
			path+=file.separator;
		}
		if(!file.isDirectory()){
			file.delete();
			file.mkdir();
		}
		return path;
	}
	
	/**
	 * 方法用途和描述: 测试移动文件的方法
	 * @param args
	 * @author zhangjh 新增日期：2011-1-4
	 * @author 你的姓名 修改日期：2011-1-4
	 * @since zte_crbt_bi
	 */
	public static void main(String[] args) {
		FileUtil ss = new FileUtil();
//		File fold = new File("D:\\sa.txt");
//		System.out.println(fold.getPath());
		ss.checkDirectory("D:\\sa");
	}

}
