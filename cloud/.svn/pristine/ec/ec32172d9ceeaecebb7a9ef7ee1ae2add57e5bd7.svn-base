package cn.com.kxcomm.woyun.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.ImagesService;
import cn.com.kxcomm.woyun.vo.ImageVO;
import cn.com.woyun.glance.model.Image;
import cn.com.woyun.glance.service.GlanceApi;
import cn.com.woyun.keystone.model.Access;

/**
 * 
* 功能描述:模板实现类
* @author chenliang 新增日期：2012-11-30
* @since WoYun_Web
 */
@Service("imagesService")
public class ImagesServiceImpl extends
		CommonServiceImpl<ImageVO> implements ImagesService {

	private GlanceApi glanceApi = GlanceApi.getInstance();
	
	@Override
	public List<ImageVO> listImages(Access access,boolean isDetail)
			throws Exception {
		try {
			List<Image> imageList = glanceApi.listImages(access,isDetail);
			List<ImageVO> voList = new ArrayList<ImageVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			for (Image image : imageList) {
				ImageVO vo = new ImageVO();
				vo.setId(image.getId());
				vo.setUri(image.getUri());
				vo.setName(image.getName());
				vo.setDiskFormat(image.getDiskFormat());
				vo.setContainerFormat(image.getContainerFormat());
				vo.setSize(image.getSize());
				vo.setChecksum(image.getChecksum());
				if(!BlankUtil.isBlank(image.getCreatedAt()))
					vo.setCreatedAt(sdf.format(image.getCreatedAt().getTime()));
				if(!BlankUtil.isBlank(image.getUpdatedAt()))
					vo.setUpdatedAt(sdf.format(image.getUpdatedAt().getTime()));
				if(!BlankUtil.isBlank(image.getDeletedAt()))
					vo.setDeletedAt(sdf.format(image.getDeletedAt().getTime()));
				vo.setStatus(image.getStatus());
				vo.setPublic(image.isPublic());
				vo.setMinDisk(image.getMinDisk());
				vo.setMinRam(image.getMinRam());
				vo.setOwner(image.getOwner());
				vo.setDeleted(image.isDeleted());
				vo.setProtected(image.isProtected());
				voList.add(vo);
			}
			return voList;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	* 方法用途和描述:删除镜像
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	@Override
	public String deleteImageById(Access access,String id) {
		String end = "ok";
		try {
			if(0 > id.indexOf(",")) {
				this.glanceApi.deleteImage(access,id);
			} else {
				String[] user = id.split(",");
				for(int i = 0;i < user.length; i++) {
					this.glanceApi.deleteImage(access,user[i]);
				}
			}
		} catch (Exception exception) {
			 end = "error";
		}
		return end;
	}
}
