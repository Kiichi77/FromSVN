package cn.com.kxcomm.contractmanage.web.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import cn.com.kxcomm.common.util.AppConfig;
import cn.com.kxcomm.common.util.FtpUtil;

public class FtpSync {
	/**
	 * 
	 * 上传文件到服务器
	 * 
	 * @param localPath
	 * @param fileType 见<code>config.properties</code>
	 * @param fileName
	 * @author zhangjh 新增日期：2013-4-15
	 * @since ContractManage
	 */
	public static void ftpUploadSync(String localPath,String fileType,String fileName){
		AppConfig config = AppConfig.getInstance();
		boolean isFtpSync = config.getString("isFtpSync").equals("true")?true:false;
		if(!isFtpSync)
			return ;
		String ftpHost=config.getString("ftpHost");
		String ftpUser=config.getString("ftpUser");
		String ftpPasswd=config.getString("ftpPasswd");
		String filePath=config.getString(fileType);
		try {
			FTPClient  ftpClient  = FtpUtil.connentFtp(ftpHost, 21, ftpUser, ftpPasswd);
			if(!ftpClient.isConnected()){
				return ;
			}
			System.out.println(ftpClient.isConnected());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			boolean isChange = ftpClient.changeWorkingDirectory(filePath);
			if(!isChange){
				boolean isSucess = ftpClient.makeDirectory(filePath);
				if(!isSucess)
					return;
			}
			FtpUtil.putFile(ftpClient, filePath+fileName, localPath+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
