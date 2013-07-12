package cn.com.woyun.glance.service;

import java.util.List;

import cn.com.woyun.Parameter;
import cn.com.woyun.glance.GlanceClient;
import cn.com.woyun.glance.api.DeleteImage;
import cn.com.woyun.glance.api.ListImages;
import cn.com.woyun.glance.api.ShowImage;
import cn.com.woyun.glance.api.UpdateImage;
import cn.com.woyun.glance.api.UploadImage;
import cn.com.woyun.glance.model.Image;
import cn.com.woyun.glance.model.ImageForUpload;
import cn.com.woyun.glance.model.Images;
import cn.com.woyun.keystone.model.Access;
import cn.com.woyun.utils.KeystoneUtils;

/**
 * 
* 功能描述:镜像文件管理
* @author chenliang 新增日期：2012-11-26
* @since WoYun_Api
 */
public class GlanceApi {
	private static GlanceApi instance = null;

	private GlanceApi() {
	}

	public static GlanceApi getInstance() {
		if (instance == null) {
			instance = new GlanceApi();
		}
		return instance;
	}

	/**
	 * 
	 * 删除镜像
	 * 
	 * @param access
	 * @param id
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public void deleteImage(Access access,String id) throws Exception {
		try {
			String token = access.getToken().getId();
			GlanceClient glance  = new GlanceClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.GLANCE_TYPE_IMAGE, null, Parameter.FACTING_PUBLIC),token);
			glance.setToken(token);
			glance.execute(new DeleteImage(id));
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 所有镜像列表
	 * 
	 * @param access
	 * @param isDetail 是否显示详情
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public List<Image> listImages(Access access,boolean isDetail) throws Exception {
		try {
			String url = KeystoneUtils.findEndpointURL(access.getServiceCatalog(),Parameter.GLANCE_TYPE_IMAGE, null, Parameter.FACTING_PUBLIC);
			GlanceClient glance = new GlanceClient(url,access.getToken().getId());
			Images images = glance.execute(new ListImages(isDetail));
			return images.getList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 查看镜像详情
	 * 
	 * @param access
	 * @param id
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	public Image showImage(Access access,String id) throws Exception {
		try {
			GlanceClient glance = new GlanceClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.GLANCE_TYPE_IMAGE, null, Parameter.FACTING_PUBLIC),access.getToken().getId());
			Image image = glance.execute(new ShowImage(id));
			return image;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 更新镜像
	 * 
	 * @param access
	 * @param image
	 * @return
	 * @author zhangjh 新增日期：2012-11-16
	 * @throws Exception 
	 * @since WoYun_Api
	 */
	//TODO 出现一下错误There was a conflict when trying to complete your request.
	public Image updateImage(Access access,Image image) throws Exception {
		try {
			String url = KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.GLANCE_TYPE_IMAGE, null, Parameter.FACTING_PUBLIC);
			GlanceClient glance  = new GlanceClient(url,access.getToken().getId());
			glance.setToken(access.getToken().getId());
			Image t = glance.execute(new UpdateImage(image));
			return t;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法用途和描述: 上传镜像
	 * 
	 * @param access
	 * @param imageForUpload
	 * @return
	 * @author chenliang 新增日期：2012-11-20
	 * @since WoYun_Api
	 * @return @param passwordCredentials
	 * @return @param imageForUpload
	 * @return @return
	 * @param @param passwordCredentials
	 * @param @param imageForUpload
	 * @param @return
	 * @throws Exception 
	 */
	//TODO 仍未测试通过的方法
	public Image uploadImage(Access access,ImageForUpload imageForUpload) throws Exception{
		try {
			String url = KeystoneUtils.findEndpointURL(access.getServiceCatalog(), Parameter.GLANCE_TYPE_IMAGE, null, Parameter.FACTING_PUBLIC);
			GlanceClient glance  = new GlanceClient(url,access.getToken().getId());
			glance.setToken(access.getToken().getId());
			Image t = glance.execute(new UploadImage(imageForUpload));
			return t;
		} catch (Exception e) {
			throw e;
		}
	}
}
