package cn.com.kxcomm.contractmanage.service;

import java.io.Serializable;
import java.util.List;

import cn.com.kxcomm.common.util.PageInfo;

public interface ICommonService<T> {
	/**
	 * 分页查询
	 * 
	 * @param model
	 *            查询条件
	 * @param pageInfo
	 *            分页对象
	 * @return 分页数据
	 */
	public PageInfo<T> findByPage(T entity, PageInfo<T> pageInfo);
	
	/**
	 * 取所有数据.
	 * 
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据主键取对象
	 * 
	 * @param pk
	 *            主键
	 * @return
	 */
	public T getByPk(Serializable pk);
	
	/***
	 * 根据某个字段的值查询实体
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public T findByField(String fieldName,Object fieldValue);

	/**
	 * 删除列表
	 * 
	 * @param pks
	 *            主键数组
	 */
	public void deleteAllByPks(Serializable[] pks);
	
	/**
	 * 根据主键删除
	 * 
	 * @param pk
	 */
	public void deleteByPk(Serializable pk);
	
	/**
	 * 删除实体 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public Serializable save(T entity);
	
	/**
	 * 修改
	 * @param entity
	 */
	public void update(T entity);
	
}
