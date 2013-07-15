package cn.com.kxcomm.ipmi.web.action.serverInfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.ipmi.entity.TbSystemEventLog;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.SystemEventLogService;
import cn.com.kxcomm.ipmi.web.action.BaseAction;

/**
 * 
 * 功能描述:系统日志采集
 * @author chenl 新增日期：2012-8-13
 * @since ipmi_web
 */
public class SystemEventLogAction extends BaseAction<TbSystemEventLog, Integer> {

	@Autowired(required=true)
	private SystemEventLogService systemEventLogService;
	private String time;
	private String endtime;
	/**
	 * 
	 * 方法用途和描述: 根据主键查询
	 * @return
	 * @author chenl 新增日期：2012-8-13
	 * @since ipmi_web
	 */
	public String findById(){
		Long id = getModel().getId();
		log.debug("SystemEventLogAction id:"+id);
		this.model = systemEventLogService.getByPk(id);
		return successInfo(null);
	}

	public String list() throws Exception {
		Timestamp endtt=null;
		try {
			if(time!=null||!time.equals("")){
				if(time.length()>6){
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date datefromstr=format.parse(time );
					SimpleDateFormat endformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date endendfromstr=endformat.parse(endtime+" 23:59:59");
					Timestamp tt=new Timestamp(datefromstr.getTime());
					endtt =new Timestamp(endendfromstr.getTime()); 
//					log.info(tt);log.info(endtt);
					model.setCreateTime(tt);
				}
			}
			log.debug("list model=" + getModel().toString());
			if(BlankUtil.isBlank(getPageInfo().getOrderFiled())){
				getPageInfo().setOrderFiled("createTime");
				getPageInfo().setOrderDirection("desc");
			}
			this.pageList = this.systemEventLogService.findByPage(getModel(),
					getPageInfo(),endtt);
			log.debug("list size=" + this.pageList.getResult().size());
			this.setSuccess(true);
			this.setMsg("成功");
			return "list";
		} catch (Exception e) {
			log.error("list error." + e.getMessage(), e);
			throw e;
		}
	}
	
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public CommonService getCommonService() {
		return systemEventLogService;
	}

	@Override
	public TbSystemEventLog getModel() {
		if(this.model == null){
			this.model = new TbSystemEventLog();
		}
		return this.model;
	}

	@Override
	public void setModel(TbSystemEventLog model) {
		this.model = model;
	}

	@Override
	public Integer[] getIds() {
		return ids;
	}

	@Override
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

}
