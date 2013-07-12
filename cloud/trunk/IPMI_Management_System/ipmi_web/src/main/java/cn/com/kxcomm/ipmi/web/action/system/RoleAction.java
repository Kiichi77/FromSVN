package cn.com.kxcomm.ipmi.web.action.system;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;

import cn.com.kxcomm.base.constants.BusinessConstants;
import cn.com.kxcomm.common.util.BlankUtil;
import cn.com.kxcomm.ipmi.entity.TbRightRole;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbSystemoperlog;
import cn.com.kxcomm.ipmi.entity.TbUser;
import cn.com.kxcomm.ipmi.service.CommonService;
import cn.com.kxcomm.ipmi.service.RightService;
import cn.com.kxcomm.ipmi.service.RoleService;
import cn.com.kxcomm.ipmi.service.UserService;
import cn.com.kxcomm.ipmi.vo.MenuEntity;
import cn.com.kxcomm.ipmi.vo.RightEntity;
import cn.com.kxcomm.ipmi.vo.UserRightEntity;
import cn.com.kxcomm.ipmi.web.action.BaseAction;
import cn.com.kxcomm.ipmi.web.util.SessionUtils;

public class RoleAction extends BaseAction<TbRole,String> {
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(RightAction.class);

	@Autowired(required = true)
	private RoleService roleService;

	@Autowired(required = true)
	private RightService rightService;

	@Autowired(required=true)
	private UserService userService;

	public Long[] rightIds; //权限集合
	private List<Map<String, Object>> root = new ArrayList<Map<String, Object>>(); //存储菜单列表

	public List<Map<String, Object>> getRoot() {
		return root;
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
		//保存角色
		roleService.save(this.model);
		Long roleId = this.model.getId();
		if(!BlankUtil.isBlank(rightIds) && rightIds.length>0){
			log.debug("rightIds:"+this.rightIds);
			roleService.insertRoleRight(roleId, rightIds);
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_add); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam(getModel().getRolename()); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("保存角色");      // 备注:一般为添加的数据项名称	
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * 修改角色
	 */
	public String updateRoleRight()	{
		//保存角色
		roleService.update(this.model);
		Long roleId = this.model.getId();
		if(!BlankUtil.isBlank(rightIds) && rightIds.length>0){
			log.debug("rightIds:"+this.rightIds);
			roleService.updateRoleRight(roleId, rightIds);
		}
		try{
			HttpSession session = getHttpSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_update); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("角色名称:"+getModel().getRolename()+",权限ids:"+rightIds); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("修改角色");      // 备注:一般为添加的数据项名称	
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo(null);
	}

	/**
	 * 修改角色查询
	 * @return
	 */
	public String selectRole() {
		List<MenuEntity> menuList = rightService.queryMenu();  //查询菜单
		List<RightEntity> rightList = null;
		if(getModel().getId()!=null){
			Long roleId = getModel().getId();
			rightList = userService.queryRoleRightListByRoleId(roleId);
			log.info("select ringPolicy Entity id:" + roleId);
		}
		for (int i = 0; i < menuList.size(); i++) {  //遍历菜单，给该角色已有的权限赋值
			MenuEntity me = menuList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", getText(me.getMenuName()));
			map.put("expanded", true);
			map.put("id", me.getMenuId());
			map.put("leaf", false);
			if(null!=rightList){
				for (RightEntity rightEntity : rightList) {
					if(rightEntity.getRightId().intValue()==me.getMenuId().intValue()){
						map.put("checked", true);
						break;
					}else{
						map.put("checked", false);
					}
				}
				if (rightList.size() < 1) {
					map.put("checked", false);
				}
			}else{
				map.put("checked", false);
			}

			List<Hashtable<String, Object>> l = new ArrayList<Hashtable<String, Object>>();
			if (me.getList().size() > 0) {
				for (int j = 0; j < me.getList().size(); j++) {
					RightEntity re = me.getList().get(j);
					Hashtable<String, Object> h = new Hashtable<String, Object>();
					h.put("text", getText(re.getRightName()));
					h.put("leaf", true);
					h.put("expanded", false);

					//新增时得不到角色ID的修改为下;
					if(null!=rightList){
						for (RightEntity rightEntity : rightList) {
							if(rightEntity.getRightId().intValue()==re.getRightId().intValue()){
								h.put("checked", true);
								break;
							}else{
								h.put("checked", false);
							}
						}
						if (rightList.size() < 1) {
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
			root.add(map);
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
		return successInfo(null);
	}


	/**
	 * 重写使之有用户使用该角色时不让期删除
	 */
	public String delete() throws Exception {
		String msg="";
		String[] idss=(String[])getIds();
		List idsList = new ArrayList();
		int a=0;
		for(String id:idss){
			//获取角色对应的用户
			TbUser user = userService.findByField("tbRole.id", Long.parseLong(idss[a]));
			a++;
			if(user==null){
				idsList.add(id);
			}
		}
		if(idsList.size()==0)		return successInfo("还存在该角色的用户,无法删除");
		if(idss.length>idsList.size())msg=",有用户占用的角色保留着";
		Long[] idslong = new Long[idsList.size()];
		for(int i=0;i<idsList.size();i++){
			idslong[i]=Long.parseLong(idsList.get(i).toString());
		}
		getCommonService().deleteAllByPks(idslong);
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			UserRightEntity entity = (UserRightEntity) session.getAttribute(SessionUtils.USER);
			TbSystemoperlog sysLog = new TbSystemoperlog();
			sysLog.setTbUser(entity.getUserEntity());   // 用户账号
			sysLog.setOperationResult((short) 1); //操作结果：1、成功，0、失败
			sysLog.setOperationType(BusinessConstants.syslog_batchDelete); //操作类型： 1、用户登录2、添加数据3、删除数据4、修改数据5、批量删除数据
			sysLog.setParam("角色对象： "+model); // 操作参数说明:一般为操作记录的key
			sysLog.setNote("批量删除角色");      // 备注:一般为添加的数据项名称			
			sysLog.setDateTime(new Timestamp(new Date().getTime()));
			this.addSystemLog(sysLog);
		}catch(Exception ex){
			log.error("syslogError:",ex);
		}
		return successInfo("删除成功"+msg);
	}


	@Override
	@JSON(serialize=false)
	public CommonService getCommonService() {
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
