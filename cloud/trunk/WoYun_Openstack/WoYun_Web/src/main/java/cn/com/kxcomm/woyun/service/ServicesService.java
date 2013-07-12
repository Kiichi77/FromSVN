package cn.com.kxcomm.woyun.service;

import java.util.List;

import cn.com.kxcomm.woyun.vo.ImageVO;
import cn.com.kxcomm.woyun.vo.ServicesVO;

/**
 * 服务管理
 * @author lizl
 */
public interface ServicesService extends CommonService<ServicesVO> {

	//获取所有镜像
	public List<ServicesVO> listImages(String tokenId) throws Exception;
}
