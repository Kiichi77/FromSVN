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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.kxcomm.base.vo.MenuEntity;
import cn.com.kxcomm.base.vo.RightEntity;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.common.util.EntityToVoUtil;
import cn.com.kxcomm.common.util.PageInfo;
import cn.com.kxcomm.contractmanage.entity.TbJobPlace;
import cn.com.kxcomm.contractmanage.entity.TbJobduties;
import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.service.ICommonService;
import cn.com.kxcomm.contractmanage.service.IJobPlaceService;
import cn.com.kxcomm.contractmanage.service.IJobdutiesService;
import cn.com.kxcomm.contractmanage.service.IRightService;
import cn.com.kxcomm.contractmanage.service.IRoleService;
import cn.com.kxcomm.contractmanage.service.ISystemLogService;
import cn.com.kxcomm.contractmanage.service.IUserService;
import cn.com.kxcomm.contractmanage.vo.RoleVo;
import cn.com.kxcomm.contractmanage.web.util.SessionUtils;
import cn.com.kxcomm.contractmanage.web.util.SystemLogType;

import com.googlecode.jsonplugin.annotations.JSON;

public class RoleAction extends BaseAction<TbRole,String> {
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(RoleAction.class);
	@Autowired(required=true)
	private ISystemLogService systemLogService;
	public String roleIds;
	
	public String getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	@Autowired(required = true)
	private IRoleService roleService;
	
	@Autowired(required = true)
	private IJobdutiesService jobdutiesService;
	
	@Autowired(required = true)
	private IJobPlaceService jobPlaceService;

	@Autowired(required = true)
	private IRightService rightService;

	@Autowired(required=true)
	private IUserService userService;

	public Long[] rightIds; //权限集合
	private List<Map<String, Object>> root = new ArrayList<Map<String, Object>>(); //存储菜单列表

	public List<Map<String, Object>> getRoot() {
		return root;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setRoot(List<Map<String, Object>> root) {
		this.root = root;
	}

	public Long[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Long[] rightIds) {
		this.rightIds = rightIds;
	}

	/**
	 * 保存角色
	 */
	public String saveRoleRight() {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		//保存角色
		log.debug("roleName:   "+this.model.getRolename());
		log.debug("rolenode:   "+this.model.getNote());
		log.debug("rightIds:   "+this.roleIds);
		this.roleService.save(this.model);
		//保存角色和权限的关系
		String[] ids = this.roleIds.split(",");
		Long roleId = this.model.getId();
		if(!BlankUtil.isBlank(ids) && ids.length>0){
			log.debug("ids:"+this.ids);
			this.roleService.insertRoleRight(roleId, ids);
			systemLogService.writeTheLog(SystemLogType.add_role, userId, "成功", null, "角色的ID为："+roleId);
		}
		} catch (Exception e) {
			log.error("saveRoleRight ERROR:", e);
			try {
				systemLogService.writeTheLog(SystemLogType.add_role, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 修改角色
	 */
	public String updateRoleRight()	{
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		try {
		String[] ids = this.roleIds.split(",");
		this.roleService.update(this.model);
		Long roleId = this.model.getId();
		if(!BlankUtil.isBlank(ids) && ids.length > 0){
			log.debug("roleIds:"+this.roleIds);
			this.roleService.updateRoleRight(roleId,ids);
			systemLogService.writeTheLog(SystemLogType.edit_role, userId, "成功", null, "角色ID为："+roleId);
		}
		} catch (Exception e) {
			try {
				log.error("updateRoleRight ERROR:", e);
				systemLogService.writeTheLog(SystemLogType.edit_role, userId, "异常", e.getMessage(), null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 查询所有角色
	 * @return 
	 * */
	public String queryAllRole() {
		 try {
			   PageInfo<TbRole> page =  new PageInfo<TbRole>(ServletActionContext.getRequest());
			   PageInfo<TbRole> contractList = this.roleService.findByPage(this.model, page);
			   List list = new ArrayList<RoleVo>();
			   for(int i = 0; i < contractList.getRows().size(); i++) {
				   RoleVo roleVo = new RoleVo();
				   roleVo.setId(contractList.getRows().get(i).getId());
				   roleVo.setNote(contractList.getRows().get(i).getNote());
				   roleVo.setRolename(contractList.getRows().get(i).getRolename());
				   list.add(roleVo);
			   }
			   contractList.setRows(list);
			   this.result = JSONObject.fromObject(contractList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return "list";
	}
	
	/**
	 * 修改角色查询
	 * @return
	 */
	public String selectRole() {
		List<MenuEntity> menuList = this.rightService.queryMenu();  //查询菜单
		List<MenuEntity> checkMenuList = null;
		if(getModel().getId()!=null){
			Long roleId = getModel().getId();
			checkMenuList = this.userService.queryMenuEntityByRoleId(roleId);
			log.info("select ringPolicy Entity id:" + roleId);
		}
		for (int i = 0; i < menuList.size(); i++) {  //遍历菜单，给该角色已有的权限赋值
			MenuEntity me = menuList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", getText(me.getMenuName()));
			map.put("state", "open");
			map.put("cls", "folder");
			map.put("id", me.getMenuId());
			List<Hashtable<String, Object>> l = new ArrayList<Hashtable<String, Object>>();
			if (me.getList().size() > 0) {
				for (int j = 0; j < me.getList().size(); j++) {
					RightEntity re = me.getList().get(j);
					Hashtable<String, Object> h = new Hashtable<String, Object>();
					h.put("text", getText(re.getRightName()));
					h.put("state", "open");
					//新增时得不到角色ID的修改为下;
					if(null!=checkMenuList){
						for (MenuEntity menuEntity : checkMenuList) {
							if(menuEntity.getMenuId().intValue()==me.getMenuId().intValue()){
								List<RightEntity> rightEntityList = menuEntity.getList();
								for (RightEntity rightEntity : rightEntityList) {
									if(rightEntity.getRightId().intValue() == re.getRightId().intValue()){
										h.put("checked", true);
										break;
									}else{
										h.put("checked", false);
									}
								}
							}
						}
						if (checkMenuList.size() < 1) {
							h.put("checked", false);
						}
					}else{
						h.put("checked", false);
					}
					//新增时得不到角色ID的修改为下;
					h.put("id", re.getRightId());
					l.add(h);
					map.put("children", l);
				}
			}
			this.root.add(map);
		}
		return "menu";
	}

	/**
	 * 
	 * 方法用途和描述: 根据主键查找对象
	 * @return
	 * @author chenl 新增日期：2012-8-8
	 * @since ipmi_web
	 */
	public String findById(){
		log.debug("model:"+getModel().toString());
		model = roleService.getByPk(getModel().getId());
		return SUCCESS;
	}


	/**
	 * 重写时有用户使用该角色时不让期删除
	 */
	@Override
	public String delete() throws Exception {
		HttpSession session = getHttpSession();
		Long userId = ((Long) session.getAttribute(SessionUtils.USER_ID));
		String msgs="";
		try {
		List<String> ls = new ArrayList<String>();
		if(-1 < this.roleIds.indexOf(",")) {
			String[] idss = this.roleIds.split(",");
			for(int j = 0; j < idss.length; j++) {
				ls.add(idss[j]);
			}
		} else {
			ls.add(this.roleIds);
		}
		List<String> idsList = new ArrayList<String>();
		for(int i = 0; i < ls.size(); i++){
			//获取角色对应的用户
			TbUser user = this.userService.findByField("tbRole.id", Long.parseLong(ls.get(i)));
			if(user==null){
				idsList.add(ls.get(i));
			}
		}
		if(idsList.size()==0){
			this.msg ="还存在使用该角色的用户,无法删除";
			systemLogService.writeTheLog(SystemLogType.del_role, userId, "失败", "还存在使用该角色的用户,无法删除", null);
			return SUCCESS;
		}		
		if(ls.size() > idsList.size()){
			msgs=",有用户占用的角色保留着";
		}
		Long[] idslong = new Long[idsList.size()];
		for(int i = 0;i < idsList.size();i++){
			idslong[i]=Long.parseLong(idsList.get(i).toString());
			systemLogService.writeTheLog(SystemLogType.del_role, userId, "成功", null, "角色ID为："+idslong[i]);
		}
		getCommonService().deleteAllByPks(idslong);
		this.msg = "删除成功"+msgs;
		} catch (Exception e) {
			log.error("delete ERROR:", e);
			systemLogService.writeTheLog(SystemLogType.del_role, userId, "异常", e.getMessage(), null);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法用途和描述: 查询所有的角色集合
	* @return
	* @author chenliang 新增日期：2013-2-19
	* @since ContractManage
	 */
	public String findAll(){
		try {
			PageInfo<RoleVo> page = new PageInfo<RoleVo>(ServletActionContext.getRequest());
			List<TbRole> list = new ArrayList<TbRole>();
			list=roleService.findAll();
			List<RoleVo> volist = new ArrayList<RoleVo>();
			for (TbRole role : list) {
				RoleVo vo = new RoleVo();
				EntityToVoUtil.copyObjValue(role, vo);
				volist.add(vo);
			}
			page.setRows(volist);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listAll error.",e);
		}
		return SUCCESS;
	}

	public String jobDuties(){
		try {
			PageInfo<TbJobduties> page = new PageInfo<TbJobduties>(ServletActionContext.getRequest());
			List<TbJobduties> list= jobdutiesService.findAll();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listAll error.",e);
		}
		return SUCCESS;
	}
	
	public String jobPlace(){
		try {
			PageInfo<TbJobPlace> page = new PageInfo<TbJobPlace>(ServletActionContext.getRequest());
			List<TbJobPlace> list= jobPlaceService.findAll();
			page.setRows(list);
			this.result = JSONObject.fromObject(page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("listAll error.",e);
		}
		return SUCCESS;
	}
	
	public String selectJobPlace(){
		List<TbJobPlace> list= jobPlaceService.findAll();
		this.jsonArray = JSONArray.fromObject(list);
//		this.result = JSONObject.fromObject(map);
		return "jsonArray";
	}

	@Override
	@JSON(serialize=false)
	public ICommonService<TbRole> getCommonService() {
		return roleService;
	}

	@Override
	public TbRole getModel() {
		if(null == this.model){
			this.model = new TbRole();
		}
		return this.model;
	}

	@Override
	public void setModel(TbRole model) {
		this.model = model;
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
