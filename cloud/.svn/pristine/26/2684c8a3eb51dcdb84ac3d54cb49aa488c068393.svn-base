package cn.com.kxcomm.ipmi.web.action.system;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.Page;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.SystemOperLogService;
import cn.com.kxcomm.ipmi.vo.SystemoperlogEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;

public class SystemOperLogAction extends BaseAction<TbSystemoperlog, Integer> {
	
	@Autowired(required=true)
	private SystemOperLogService systemOperLogService;
	
	public SystemoperlogEntity entity; // 实体集合 
	public Page<SystemoperlogEntity> pageEntityList;   //结果集合
	
	public SystemoperlogEntity getEntity() {
		return entity;
	}

	public void setEntity(SystemoperlogEntity entity) {
		this.entity = entity;
	}

	public Page<SystemoperlogEntity> getPageEntityList() {
		return pageEntityList;
	}

	public void setPageEntityList(Page<SystemoperlogEntity> pageEntityList) {
		this.pageEntityList = pageEntityList;
	}

	/**
	 * Action函数,显示Entity列表界面. 建议return SUCCESS.
	 */
	public String findpage() throws Exception {
		try {
			log.debug("list getEntity=" + getEntity().toString());
			this.pageEntityList = systemOperLogService.findByPageEntity(getEntity(),getPageInfo());
			log.debug("list size=" + this.pageEntityList.getResult().size());
			return successInfo(null);
		} catch (Exception e) {
			log.error("list error." + e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public CommonService getCommonService() {
		return systemOperLogService;
	}

	@Override
	public TbSystemoperlog getModel() {
		if(this.model == null){
			this.model = new TbSystemoperlog();
		}
		return this.model;
	}

	@Override
	public void setModel(TbSystemoperlog model) {
		this.model = model;
	}

	@Override
	public Integer[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

}
