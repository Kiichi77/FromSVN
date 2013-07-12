package cn.com.kxcomm.contractmanage.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.base.vo.RightEntity;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IRightService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.vo.RightVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

import com.googlecode.jsonplugin.annotations.JSON;

public class RightAction  extends BaseAction<TbRight,Integer>{
	
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(RightAction.class);
	
	@Autowired(required = true)
	private IRightService rightService;
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	private Integer id;
	
	private List<Map<String,Object>> rightList ; //菜单列表
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Map<String, Object>> getRightList() {
		return rightList;
	}
	
	public void setRightList(List<Map<String, Object>> rightList) {
		this.rightList = rightList;
	}
	
	public ISystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(ISystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	/**
	 * 
	* 方法用途和描述: 查询 菜单
	* @return
	* @author chenl 新增日期：2012-8-8
	* @since ipmi_web
	 */
	public String queryMenu(){
		this.rightList = new ArrayList<Map<String, Object>>();
		List<MenuEntity> menuList = this.rightService.queryMenu();
		for(int i = 0 ; i < menuList.size() ; i++){
			MenuEntity me = menuList.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("text", getText(me.getMenuName()));
			map.put("id", me.getMenuId());
			map.put("cls", "folder");
			List<Hashtable<String,Object>> l = new ArrayList<Hashtable<String,Object>>();
			if(me.getList().size()>0){
				for(int j = 0 ; j < me.getList().size() ; j++){
					RightEntity re = me.getList().get(j);
					Hashtable<String,Object> h = new Hashtable<String,Object>();
					h.put("text", getText(re.getRightName()));
					h.put("state", "open");
					h.put("id", re.getRightId());
					h.put("checked", false);
					l.add(h);
					map.put("children",l); 
				}
			}
			this.rightList.add(map);
		}
		JSONArray jsonArray = JSONArray.fromObject(this.rightList);
		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("list",jsonArray);
		this.result = jsonObject;
		return SUCCESS;
	} 
	
	/**
	 * 
	* 方法用途和描述:根据主键查询
	* @return
	* @author chenl 新增日期：2012-8-8
	* @since ipmi_web
	 */
	public String findById(){
		log.info("model:"+getModel().toString());
		model = rightService.getByPk(getModel().getId());
		return SUCCESS;
	}
	
	public String allList(){
		List<RightVo> list = rightService.queryAllRight();
		PageInfo<RightVo> pageInfo = new PageInfo<RightVo>();
		pageInfo.setRows(list);
		pageInfo.setTotal(list.size());
		result = JSONObject.fromObject(pageInfo);
		return SUCCESS;
	}
	
	public String delById(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		if(id!=null){
			rightService.deleteByPk(id);
			systemLogService.writeTheLog(SystemLogType.del_right, userId, "成功", null, "权限ID为："+id);
			this.result = JSONObject.fromObject(successInfo("ok"));
		}
		} catch (Exception e) {
			log.error("delById ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.del_right, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String save(){
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		if(model!=null){
			System.out.println(model.toString());
			rightService.save(model);
			systemLogService.writeTheLog(SystemLogType.add_right, userId, "成功", null, "保存成功");
			this.result = JSONObject.fromObject(successInfo("ok"));
		}
		} catch (Exception e) {
			log.error("save ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.add_right, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	

	
	@Override
	@JSON(serialize=false)
	public ICommonService<TbRight> getCommonService() {
		return rightService;
	}
	@Override
	public TbRight getModel() {
		if(null==this.model){
			this.model = new TbRight();
		}
		return this.model;
	}
	@Override
	public void setModel(TbRight model) {
		this.model = this.model;
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
