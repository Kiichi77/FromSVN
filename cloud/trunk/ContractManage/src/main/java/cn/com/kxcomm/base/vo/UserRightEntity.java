package cn.com.kxcomm.base.vo;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import cn.com.kxcomm.contractmanage.entity.TbUser;

public class UserRightEntity implements Serializable {

	/**
	 * 用户基本信息
	 */
	private TbUser userEntity;

	/**
	 * 用户权限列表
	 */
	private List<RightEntity> userRightList;

	/**
	 * 用户菜单列表
	 */
	private List<Hashtable<String, String>> userMenuList;

	/**
	 * 菜单列表
	 */
	private List<MenuEntity> menuList;

	public List<MenuEntity> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuEntity> menuList) {
		this.menuList = menuList;
	}

	public TbUser getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(TbUser userEntity) {
		this.userEntity = userEntity;
	}

	public List<RightEntity> getUserRightList() {
		return userRightList;
	}

	public void setUserRightList(List<RightEntity> userRightList) {
		this.userRightList = userRightList;
	}

	public List<Hashtable<String, String>> getUserMenuList() {
		return userMenuList;
	}

	public void setUserMenuList(List<Hashtable<String, String>> userMenuList) {
		this.userMenuList = userMenuList;
	}

}
