package cn.com.woyun.glance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.exception.Errors;
import cn.com.woyun.exception.SeriousError;
import cn.com.woyun.glance.model.Image;
import cn.com.woyun.glance.model.ImageForUpload;


public class GlanceTest extends BaseTest{
	private String id;
	private List<Image> list;
	private  Image tmpImage;
	public GlanceTest () throws IOException {
	}
	
	//TODO 上传报错，看不懂的错。
	//@Test
	public void uploadImage() throws FileNotFoundException{
		File file = new File("E:/windowsserver2008.img");
		InputStream in = new FileInputStream(file);
		ImageForUpload imageForUpload = new ImageForUpload();
		imageForUpload.setChecksum("79dc70c9cfd55b5af6af779c8824a768");
		imageForUpload.setContainerFormat("ovf");
		imageForUpload.setDiskFormat("qcow2");
		imageForUpload.setInputStream(in);
		imageForUpload.setName("testChenl");
		imageForUpload.setOwner("fb7664243a7942e8af580a8e71a8735f");
		imageForUpload.setPublic(true);
		imageForUpload.setSize(1967456256);
		try {
			glanceApi.uploadImage(tokenLogin.getAccess(), imageForUpload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 更新镜像
	 * 
	 * @param passwordCredentials
	 * @param image
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @since WoYun_Api
	 */
	//TODO 出现一下错误There was a conflict when trying to complete your request.
//	@Test (groups={"image"},dependsOnMethods={"showImage"})
	public void updateImage() throws IOException{
		tmpImage.setName("testchenl");
		try {
			glanceApi.updateImage(tokenLogin.getAccess(), tmpImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 查看镜像详情
	 * 
	 * @param passwordCredentials
	 * @param id
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws IOException 
	 * @since WoYun_Api
	 */
//	@Test (groups={"image"},dependsOnMethods={"listImages"})
	public void showImage() throws IOException {
		for (Image u : list) {
			System.out.println("Image:"+u.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入查看的镜像ID：*********************************");
		id = in.readLine();
		if("".equals(id)||null == id){
			Errors errors = new Errors();
			errors.setCode(11);
			errors.setMsg("请输入查看的镜像ID.");
			throw new SeriousError(errors);
		}
		try {
			tmpImage = glanceApi.showImage(tokenLogin.getAccess(), id);
			System.out.println(tmpImage.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 删除镜像
	 * 
	 * @param passwordCredentials
	 * @param id
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws IOException 
	 * @since WoYun_Api
	 */
//	@Test (groups={"image"},dependsOnMethods={"showImage"})
	public void deleteImage() throws IOException {
		for (Image u : list) {
			System.out.println("Image:"+u.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入删除的镜像ID：*********************************");
		id = in.readLine();
		if("".equals(id)||null == id){
			Errors errors = new Errors();
			errors.setCode(11);
			errors.setMsg("请输入删除的镜像ID.");
			throw new SeriousError(errors);
		}
		try {
			glanceApi.deleteImage(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 所有镜像列表
	 * 
	 * @param passwordCredentials
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @since WoYun_Api
	 */
//	@Test
	public void listImages() {
		try {
			list =glanceApi.listImages(tokenLogin.getAccess(),true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
