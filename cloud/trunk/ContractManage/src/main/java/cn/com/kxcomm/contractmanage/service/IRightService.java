package cn.com.kxcomm.contractmanage.service;

import java.util.List;

import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.vo.RightVo;

public interface IRightService extends ICommonService<TbRight> {
	
	/**
	 * 查找菜单
	 * @return
	 */
	public List<MenuEntity> queryMenu();
	
	/**
	 * 
	 * 查看所有权限
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-3-21
	 * @since ContractManage
	 */
	public List<RightVo> queryAllRight();

}
