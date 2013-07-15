package cn.com.kxcomm.ipmi.service;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.AlertEntity;
import cn.com.kxcomm.ipmi.vo.AlertVO;

public interface AlertService extends CommonService<AlertEntity> {

	/**
	 * 
	* 方法用途和描述: 重写分页方法
	* @param alertvo
	* @param pageinfo
	* @return
	* @author chenl 新增日期：2012-8-30
	* @since ipmi_web
	 */
	public Page<AlertVO> findByPage(AlertVO alertvo,Page<AlertEntity> pageinfo);
	
	/**
	 * 
	* 方法用途和描述:修改
	* @param alertVo
	* @return
	* @author chenl 新增日期：2012-8-31
	* @since ipmi_web
	 */
	public boolean updateAlert(AlertVO alertVo);
}
