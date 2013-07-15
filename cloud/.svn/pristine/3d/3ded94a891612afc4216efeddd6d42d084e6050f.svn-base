package cn.com.kxcomm.contractmanage.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbSystemLog;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.impl.SystemLogService;
import cn.com.kxcomm.contractmanage.vo.PurchaseOrderContractFileVo;
import cn.com.kxcomm.contractmanage.vo.SystemLogVo;

public class SystemLogAction extends BaseAction<TbSystemLog, String>{
	private static Logger LOGGER = Logger.getLogger(SystemLogAction.class);
	@Autowired(required = true)
	private SystemLogService systemLogService;
	
	private SystemLogVo systemLogVo;
	
	
	
	public SystemLogVo getSystemLogVo() {
		return systemLogVo;
	}

	public void setSystemLogVo(SystemLogVo systemLogVo) {
		this.systemLogVo = systemLogVo;
	}

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	* 
	* 方法用途和描述: 分页查询所有的日志
	* @return
	* @author luj 新增日期：2013-4-2
	* @since ContractManage
	*/
	public String listSystemLog(){
		 try {
		   PageInfo<SystemLogVo> page =  new PageInfo<SystemLogVo>(ServletActionContext.getRequest());
			if(BlankUtil.isBlank(systemLogVo)){
				systemLogVo = new SystemLogVo();
			}
			LOGGER.info("systemLogVo:"+systemLogVo);
		   PageInfo<SystemLogVo> list=systemLogService.listSystemLog(page,systemLogVo);
		   result = JSONObject.fromObject(list);
		} catch (Exception e) {
			LOGGER.error("listSystemLog ERROR:",e);
		}
	    return SUCCESS;
	}
	
	@Override
	public ICommonService getCommonService() {
		return systemLogService;
	}

	@Override
	public TbSystemLog getModel() {
		if(this.model==null){
			 this.model = new TbSystemLog();
		}
		return this.model;
	}

	@Override
	public void setModel(TbSystemLog model) {
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
