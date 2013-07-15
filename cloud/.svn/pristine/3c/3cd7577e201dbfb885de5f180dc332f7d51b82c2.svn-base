package cn.com.kxcomm.ipmi.service;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbBlackName;
import cn.com.kxcomm.ipmi.entity.TbServer;
import cn.com.kxcomm.ipmi.vo.BlackNameEntity;

/**
 * 黑名单业务逻辑处理类
 * @author chenliang
 *
 */
public interface BlackNameService extends CommonService<TbBlackName> {
	
	public Page<BlackNameEntity> queryPage(BlackNameEntity entity,Page<TbBlackName> pageInfo);

	/**
	 * 
	* 方法用途和描述: 重新检测
	* @author chenl 新增日期：2012-8-15
	* @since ipmi_web
	 */
	public boolean toDetection(TbServer server,Long id);
}
