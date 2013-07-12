package cn.com.kxcomm.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;


/**
 * 
 * 功能描述:Ftp工具类
 * <p>版权所有：金鹏科技
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author chenxinwei 新增日期：2010-7-30
 * @author 你的姓名 修改日期：2010-7-30
 * @since zte_crbt_bi
 */
public class FtpUtil {
	private static final Logger log = Logger.getLogger(FtpUtil.class);

	public static int deleteFile(String host,int port,String user,String passwd,String remoteDir,String listNames[]) throws IOException{
		FTPClient ftp = FtpUtil.connentFtp(host, port, user, passwd);
		ftp.changeWorkingDirectory(remoteDir);
		int result = 0;
		for (int i = 0; i < listNames.length; i++) {
			boolean flag = ftp.deleteFile(remoteDir + listNames[i]);
			if(flag)
				result++;
			log.info("delete file:"+remoteDir + listNames[i]);
		}
		return result;
	}
	
	public static String[] listNames(String host,int port,String user,String passwd,String remoteDir) throws IOException{
		FTPClient ftp = FtpUtil.connentFtp(host, port, user, passwd);
		ftp.changeWorkingDirectory(remoteDir);
		return ftp.listNames();
	}

	/**
	 * 方法用途和描述: 从FTP指定的目录下载制定扩展名的文件
	 * @param host 
	 * @param port
	 * @param remoteDir 远程目录
	 * @param localDir 本地目录
	 * @param fileType 文件类型，如果为NULL则下载所有文件
	 * @param tempDir 下载时使用的临时目录，如果为null则不使用临时目录
	 * @param delRemoteFile 下载后是否删除FTP服务器上的文件
	 * @return
	 * @throws IOException
	 * @author chenxinwei 新增日期：2010-7-31
	 * @author 你的姓名 修改日期：2010-7-31
	 * @since zte_crbt_bi
	 */
	public static int downFiles(String host,int port,String user,String passwd,
			String remoteDir,String localDir,String fileType,
			String tempDir,boolean delRemoteFile) throws IOException{
		int downFiles = 0;
		String[] ftpFilesNames = null;
		File downDir = null;
		if(tempDir!=null) downDir = new File(localDir+File.separator+tempDir);
		else downDir = new File(localDir);
		if(!downDir.exists()){
			if(!downDir.mkdirs()){
				log.error("Fail mkdirs=" +downDir.getPath()+".Can't down files!");
				return 0;
			}
		}
		String downPath = downDir.getPath();
		FileOutputStream fos = null;
		FTPClient ftp = FtpUtil.connentFtp(host, port, user, passwd);
		if(!ftp.isConnected()){
			log.error("The ftp is no connected!Stop to down files!");
			return 0;
		}
		boolean isChange = ftp.changeWorkingDirectory(remoteDir);
		if(!isChange)
			log.info("changeWorkingDirectory:"+isChange+".dir:"+remoteDir);
		ftpFilesNames = ftp.listNames();
		boolean downResult = false;
		File file = null;
		
		for(int i=0;i<ftpFilesNames.length;i++){
			if(fileType==null||ftpFilesNames[i].endsWith(fileType)){
				try{
					log.info("Downing " + ftpFilesNames[i]);
					downResult = false;
					file = new File(downPath+File.separator+ftpFilesNames[i]);
					fos = new FileOutputStream(file);
					downResult = ftp.retrieveFile(ftpFilesNames[i], fos);
					fos.flush();
					log.info("Downed " +ftpFilesNames[i] +" to "
							+ file.getPath() + "." + downResult);
					if(delRemoteFile){
						log.info("Deleted " +ftpFilesNames[i] +" on server." + ftp.deleteFile(ftpFilesNames[i]));
					}
					downFiles++;
				}catch(Exception e){
					log.error("Fail to down file:"+ftpFilesNames[i],e);
				}finally{
					if(fos!=null){
						try {
							fos.close();
							if(tempDir!=null) FileUtil.moveFile(file, new File(localDir+File.separator+file.getName()));
						} catch (IOException e) {
							log.error(e.getMessage());
						}
					}
				}
			}
		}
		if(tempDir!=null){
			String[] files = downDir.list();
			if(files==null||files.length==0)downDir.delete();
		}
		ftp.disconnect();
		return downFiles;
	}
	
	/**
	 * 
	* 方法用途和描述: XXXXXXX（可以分多行编写）
	* @param host
	* @param port
	* @param user
	* @param passwd
	* @param remoteDir
	* @param localDir
	* @param fileType 上传的文件类型
	* @param tempDir 上传到远程服务器上的临时目录,null则不使用临时目录
	* @param tempFileType 上传时使用的临时扩展名传完后再改回原来的扩展名
	* @param bakDir 备份的目录，使用绝对路径如果为null则不备份文件直接删除
	* @return
	* @author chenxinwei 新增日期：2010-8-2
	* @author 你的姓名 修改日期：2010-8-2
	 * @throws IOException 
	* @since zte_crbt_bi
	 */
	public static int upFiles(String host,int port,String user,String passwd,
			String remoteDir,String localDir,String fileType,
			String tempDir,String tempFileType,String bakDir) throws IOException{
		int files = 0;
		File local = new File(localDir);
		File[] localFiles = local.listFiles();
		if(localFiles==null||localFiles.length<1){
			log.warn("There are no files in " + localDir);
			return 0;
		}
		FTPClient ftp = FtpUtil.connentFtp(host, port, user, passwd);
		if(!ftp.isConnected()){
			log.error("The ftp is no connected!Stop to down files!");
			return 0;
		}
		boolean isDir = ftp.changeWorkingDirectory(remoteDir);
		log.info("changeWorkingDirectory is "+isDir);
		if(!isDir){
			boolean reslut =ftp.makeDirectory(remoteDir);
			if(!reslut)
				log.error("Cann't Make Directory");
			else
				ftp.changeWorkingDirectory(remoteDir);
		}
		if(tempDir != null){
			if(ftp.makeDirectory(tempDir)){
				ftp.makeDirectory(tempDir);
			}
		}
		if(bakDir!=null){
			
			File bak = new File(bakDir);
			if(!bak.exists()) bak.mkdirs();
		}
		File file = null;
		String tempName = null;
		for(int i=0;i<localFiles.length;i++){
			file = localFiles[i];
			if(file.getName().endsWith(fileType)){
				tempName = file.getName();
				if(tempFileType!=null){
					tempName = tempName.substring(0,file.getName().lastIndexOf(".")) + "."+tempFileType ;
				}
				if(tempDir!=null){
					tempName = tempDir+ "/" + tempName;
				}
				if(putFile(ftp,tempName,file.getPath())) files++;
				ftp.rename(tempName, file.getName());
				if(bakDir==null) file.delete();
				else{
					boolean result = FileUtil.moveFile(file, new File(bakDir+File.separator+file.getName()));
					log.debug("rename " + bakDir+File.separator+file.getName() + "."+result);
				}
			}
		}
		if(tempDir!=null) ftp.removeDirectory(tempDir);
		ftp.disconnect();
		return files;
	}
	
	
	public static boolean putFile(FTPClient client, String upPath, String filePath){
		boolean result = false;
		log.info("Uping file:"+filePath);
		File filetoUpLoad=new File(filePath);
		if(!filetoUpLoad.exists())log.error("没有这个文件好吗");
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			result = client.storeFile(upPath,in);
			in.close();
		} catch (FileNotFoundException e) {
			log.error("Can't find file:"+filePath+"."+e.getMessage());
		} catch (IOException e) {
			log.error("FilePath:"+upPath + "."+e.getMessage());
			e.printStackTrace();
		}
		log.info("Put file to ftp.File:"+ upPath + "," + result);
		return result;
	}

	public static FTPClient connentFtp(String host,int port,String user,String passwd) throws IOException{
		FTPClient ftp=new FTPClient();
		ftp.setControlEncoding("gbk"); 
		log.info("ftp.setControlEncoding==gbk");
		ftp.connect(host, port);
		boolean result = ftp.login(user, passwd);
		ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		if(result){
			log.info("Connected ftp:"+host);
		}else{
			ftp.disconnect();
			log.error("Fail to login to ftp!Please check the ftp setting!");
		}
		return ftp;
	}



	public static void main(String args[]){
		try {
			FTPClient c = FtpUtil.connentFtp("192.168.1.11", 21, "kxcomm", "kxcomm123");
			c.setFileType(FTPClient.BINARY_FILE_TYPE);
			boolean isDir = c.changeWorkingDirectory("/home/kxcomm/contractDocFile");
			log.info("	 is "+isDir);
			FtpUtil.putFile(c, "/home/kxcomm/contractDocFile/nishuone.doc", "E://kangxun//hahaha.doc");
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
