package cn.com.kxcomm.common.util;

import java.lang.reflect.Field;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.kxcomm.contractmanage.entity.TbRole;
import cn.com.kxcomm.contractmanage.entity.TbUser;
import cn.com.kxcomm.contractmanage.vo.UserVo;
/**
 * 
* 功能描述:Bean相关操作处理工具类
* @author chenliang 新增日期：2013-2-19
* @since ContractManage
 */
public class EntityToVoUtil {

	private static final Logger LOGGER = Logger.getLogger(EntityToVoUtil.class);
	
	/**
	 * 根据传入的cls复制整个对象的属性值到另外一个对象的对应的同名和同类型的属性（只复制cls存在的且属同名和同类型的属性值，名为sguid的属性不拷贝）
	 * @param vo<Object> 源值对象
	 * @param target<Object> 目标对象
	 * @param cls<Class> 被拷贝值的对象类型
	 * @param not_copy<String> 用于指定不拷贝值的属性，可传多个属性名，之间用逗号隔开
	 * @return void
	 */
	private static void copyObjectValue(Object vo, Object target, Class cls, String not_copy, boolean isCopyNull){
		int flag=0;
		if(StringUtils.isNotBlank(not_copy)){
			not_copy=","+not_copy+",";//前后加逗号是为了后面能够准确的判断所包含的属性名称
		}
		
		try{
			String sname = "";
			Field[] fields = cls.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {	
				sname = fields[i].getName();

				//如果属性为id或属性名存在于not_copy指的属性名范围中，则不拷贝访属性值
				if(StringUtils.isNotBlank(not_copy) && not_copy.indexOf(","+sname+",")!=-1)
					continue;
				
				if(fields[i].getType().toString().startsWith("class") && !fields[i].getType().getName().equals("java.lang.String")){ //对象类型字段值拷贝
					try{
						BeanUtils.setProperty(target, sname,MethodUtils.invokeMethod(vo,"get"+sname.substring(0, 1).toUpperCase()+sname.substring(1),null));
					}catch(Exception ne){
						flag=1;
						continue;
					}			
				}else{ //基本类型字段值拷贝
					try{
						if(isCopyNull==false && BeanUtils.getProperty(vo, sname)==null){
							continue;
						}else{
							BeanUtils.setProperty(target, sname, BeanUtils.getProperty(vo, sname));
						}
					}catch(Exception ne){
						flag=1;
						continue;
					}	
				}
			}			
		}catch(Exception e){
			flag=1;
			e.printStackTrace();
		}
		
		if(flag==1){
			flag=0;
			System.gc();
		}
	}
	
	/**
	 * 复制整个对象的属性值到另外一个对象的对应的同名和同类型的属性
	 * @param vo<Object> 源值对象
	 * @param target<Object> 目标对象
	 * @return void
	 */
	public static void copyObjValue(Object vo, Object target){
		Class cls=vo.getClass();
		while(!cls.getName().equals("java.lang.Object")){
			copyObjectValue(vo, target, cls, null, false);
			cls=cls.getSuperclass();
		}
	}
	
	/**
	 * 复制整个对象的属性值到另外一个对象的对应的同名和同类型的属性
	 * @param vo<Object> 源值对象
	 * @param target<Object> 目标对象
	 * @param isCopyNull<boolean> 是否拷贝NULL值
	 * @return void
	 */
	public static void copyObjValue(Object vo, Object target, boolean isCopyNull){
		Class cls=vo.getClass();
		while(!cls.getName().equals("java.lang.Object")){
			copyObjectValue(vo, target, cls, null, isCopyNull);
			cls=cls.getSuperclass();
		}
	}
	
	/**
	 * 复制整个对象的属性值到另外一个对象的对应的同名和同类型的属性，但名为sguid的属性不拷贝
	 * @param vo<Object> 源值对象
	 * @param target<Object> 目标对象
	 * @param not_copy<String> 用于指定不拷贝值的属性，可传多个属性名，之间用逗号隔开
	 * @return void
	 */
	public static void copyObjValue(Object vo, Object target, String not_copy){
		Class cls=vo.getClass();
		while(!cls.getName().equals("java.lang.Object")){
			copyObjectValue(vo, target, cls, not_copy, false);
			cls=cls.getSuperclass();
		}
	}
	
	/**
	 * 复制整个对象的属性值到另外一个对象的对应的同名和同类型的属性，但名为sguid的属性不拷贝
	 * @param vo<Object> 源值对象
	 * @param target<Object> 目标对象
	 * @param not_copy<String> 用于指定不拷贝值的属性，可传多个属性名，之间用逗号隔开
	 * @param isCopyNull<boolean> 是否拷贝NULL值
	 * @return void
	 */
	public static void copyObjValue(Object vo, Object target, String not_copy, boolean isCopyNull){
		Class cls=vo.getClass();
		while(!cls.getName().equals("java.lang.Object")){
			copyObjectValue(vo, target, cls, not_copy, isCopyNull);
			cls=cls.getSuperclass();
		}
	}

	public static void main(String[] args){
		TbUser user = new TbUser();
		user.setAccouont("aa");
		user.setEmail("bb");
		user.setNote("dd");
		user.setPassword("123");
		user.setPhoneNumber("123456789");
		user.setUserName("你妹！");
		user.setId(1L);
		TbRole role = new TbRole();
		role.setId(1L);
		role.setNote("角色！");
		role.setRolename("管理员");
		EntityToVoUtil util = new EntityToVoUtil();
		UserVo vo = new UserVo();
		util.copyObjValue(user, vo);
		System.out.println("id:"+user.getId()+",accouont:"+vo.getAccouont()+",pwd:"+vo.getPassword()+",email:"+vo.getEmail()+",roleName:"+vo.getTbRole());
	}
	
}
