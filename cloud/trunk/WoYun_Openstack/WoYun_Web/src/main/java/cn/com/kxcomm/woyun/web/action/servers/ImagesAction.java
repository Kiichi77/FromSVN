package cn.com.kxcomm.woyun.web.action.servers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.kxcomm.woyun.service.ImagesService;
import cn.com.kxcomm.woyun.vo.ImageVO;
import cn.com.kxcomm.woyun.vo.NowUseTentidVo;
import cn.com.kxcomm.woyun.web.action.BaseAction;
import cn.com.woyun.keystone.model.Access;

public class ImagesAction extends BaseAction<ImageVO, String> {

	private static Logger LOGGER = Logger.getLogger(ImagesAction.class);
	
	@Autowired(required=true)
	private ImagesService imagesService;
	
	private String imageId;
	
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	//用于返回前台的对象
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	/**
	 * 
	* 方法用途和描述:获取安全组集合
	* @return
	* @author chenliang 新增日期：2012-11-30
	* @since WoYun_Web
	 */
	public String listImages(){
		try {
			Access access = getAccess();
			if(!BlankUtil.isBlank(access)){
				List<ImageVO> volist = imagesService.listImages(access,true);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("total", volist.size());
				map.put("rows", volist);
				result = JSONObject.fromObject(map);
				LOGGER.debug(""+result.toString());
			}else{
				LOGGER.debug("listSecurityGroup access is null.");
			}
		} catch (Exception e) {
			LOGGER.error("listsecurityGroup error.",e);
		}
		return successInfo(null);
	}

	/**
	 * 
	* 方法用途和描述:删除镜像
	* @return
	* @author chenliang 新增日期：2012-12-05
	* @since WoYun_Web
	 */
	public String deleteImageById() {
		System.out.println("ImageID:   "+this.imageId);
		HttpSession session = getHttpSession();
		NowUseTentidVo tentidVo = (NowUseTentidVo) session.getAttribute("nowUse");
		Access access = tentidVo.getAccess();
		String end = this.imagesService.deleteImageById(access,this.imageId);
		Map map = new HashMap();
		map.put("msg",end);
		this.result = JSONObject.fromObject(map);
		log.info("End:   "+this.result.toString());
		return successInfo(null);
	}
	
	@Override
	public CommonService getCommonService() {
		return imagesService;
	}

	@Override
	public ImageVO getModel() {
		if(null == this.model){
			this.model = new ImageVO();
		}
		return model;
	}

	@Override
	public void setModel(ImageVO model) {
		this.model=model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	

}
