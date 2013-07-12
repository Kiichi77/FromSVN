package cn.com.kxcomm.ipmi.web.action.serverInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.ipmi.entity.DimDateEntity;
import cn.com.kxcomm.ipmi.entity.FactServerInfoEntity;
import cn.com.kxcomm.ipmi.entity.TbHistoryServerInfo;
import cn.com.kxcomm.ipmi.service.BelongService;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.HistoryServerInfoService;
import cn.com.kxcomm.ipmi.vo.BelongEntity;
import cn.com.kxcomm.ipmi.vo.HistoryServerInfoEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;

public class HistoryServerInfoAction extends BaseAction<FactServerInfoEntity, Integer> {

	@Autowired(required=true)
	private HistoryServerInfoService historyServerInfoSevice;
	@Autowired(required=true)
	private BelongService belongService;
	
//	public HistoryServerInfoEntity historyServerEntity;  //服务器数据实体
	public BelongEntity belongEntity; //服务器所属集合实体对象
	public List historyData;  //图标数据集合
    
	public FactServerInfoEntity factServerInfoEntity;
	public short dateTimeType;      //报表的类型
	public String dimTimeDate;       //日报时间
    




	public short getDateTimeType() {
		return dateTimeType;
	}

	public void setDateTimeType(short dateTimeType) {
		this.dateTimeType = dateTimeType;
	}
	public String getDimTimeDate() {
		return dimTimeDate;
	}

	public void setDimTimeDate(String dimTimeDate) {
		this.dimTimeDate = dimTimeDate;
	}

	public FactServerInfoEntity getFactServerInfoEntity() {
		return factServerInfoEntity;
	}

	public void setFactServerInfoEntity(FactServerInfoEntity factServerInfoEntity) {
		this.factServerInfoEntity = factServerInfoEntity;
	}

	public BelongEntity getBelongEntity() {
		return belongEntity;
	}

	public void setBelongEntity(BelongEntity belongEntity) {		
		this.belongEntity = belongEntity;
	}

	public List<Hashtable<String, Object>> getHistoryData() {
		return historyData;
	}

	public void setHistoryData(List<Hashtable<String, Object>> historyData) {
		this.historyData = historyData;
	}


	/**
	 * 
	* 方法用途和描述: 根据主键查询
	* @return
	* @author chenl 新增日期：2012-8-13
	* @since ipmi_web
	 */
	public String findById(){
		Long id = getModel().getId();
		log.debug("HistoryServerInfoAction id:"+id);
		historyServerInfoSevice.getByPk(id);
		return successInfo(null);
	}
	
	/**
	 * 
	* 方法用途和描述: 获取图形化报表的数据
	* @return
	* @author chenl 新增日期：2012-8-15
	 * @throws ParseException 
	* @since ipmi_web
	 */
	
	public String getGraphicsData() throws ParseException{
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Date date=sdf.parse(dimTimeDate);
	   DimDateEntity dateEntity=new DimDateEntity();
	    dateEntity.setFullDate(date);	 
	    dateEntity.setWeek(factServerInfoEntity.getDimDate().getWeek());
	    dateEntity.setMonth(factServerInfoEntity.getDimDate().getMonth());
	    dateEntity.setYear(factServerInfoEntity.getDimDate().getYear());
     
	    factServerInfoEntity.setDimDate(dateEntity);
	    
	    
	log.debug("getFactServerInfoEntity:"+this.factServerInfoEntity.toString()+",this.belongEntity"+this.belongEntity.toString());
		if(BlankUtil.isBlank(getFactServerInfoEntity())){
			this.factServerInfoEntity =new FactServerInfoEntity();
		}
		this.historyData=historyServerInfoSevice.getGraphicsData(factServerInfoEntity, dateTimeType);
		log.debug("hisotry:"+historyData.toString());
		return successInfo(null);
	}
	

	@Override
	public Integer[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	@Override
	public CommonService getCommonService() {
		return historyServerInfoSevice;
	}

	@Override
	public FactServerInfoEntity getModel() {
		if(null == factServerInfoEntity){
			factServerInfoEntity = new FactServerInfoEntity();
		}
		return factServerInfoEntity;
	}

	@Override
	public void setModel(FactServerInfoEntity model) {
		this.model = model;
	}

}
