package cn.com.kxcomm.woyun.service.impl;

import java.io.Serializable;
import java.util.List;


import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.woyun.service.CommonService;
import cn.com.woyun.keystone.service.TokenApi;

public abstract class CommonServiceImpl<T> implements CommonService<T> {
	
	/**
	 * 返回api对象.
	 * 
	 * @return tokenapi
	 */
	public TokenApi api = TokenApi.getInstance();
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<T> findByPage(T entity, Page<T> pageInfo) {
		return null;
	}

	/**
	 * 根据主键查询
	 */
	@Override
	public T getByPk(Serializable pk) {
		return null;
	}
	
	/**
	 * 查找所有
	 */
	@Override
	public List<T> findAll() {
		return null;
	}

	/**
	 * 保存
	 */
	@Override
	public void save(T entity) {
		
	}

	/**
	 * 修改
	 */
	@Override
	public void update(T entity) {
		
	}
	
	/**
	 * 删除多个实体对象
	 */
	@Override
	public void deleteAllByPks(Serializable[] pks) {
		
	}

	/**
	 * 根据主键删除
	 */
	@Override
	public void deleteByPk(Serializable pk) {
		
	}
	
	/**
	 * 根据对象删除
	 */
	@Override
	public void delete(T entity) {
		
	}

	/**
	 * 根据字段查询
	 */
	@Override
	public T findByField(String fieldName, Object fieldValue) {
		return null;
	}
}
