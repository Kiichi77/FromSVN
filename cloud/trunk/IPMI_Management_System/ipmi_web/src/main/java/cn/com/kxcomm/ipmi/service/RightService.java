package cn.com.kxcomm.ipmi.service;

import java.util.List;
import cn.com.kxcomm.ipmi.entity.TbRight;
import cn.com.kxcomm.ipmi.vo.MenuEntity;

public interface RightService extends CommonService<TbRight> {
	
	/**
	 * 查找菜单
	 * @return
	 */
	public List<MenuEntity> queryMenu();

}
