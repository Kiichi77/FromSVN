

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmi.dao.UserDAO;
import cn.com.kxcomm.ipmi.entity.TbRole;
import cn.com.kxcomm.ipmi.entity.TbUser;



public class DaoTest {
	private ApplicationContext ctx;
	private UserDAO udao;
	private TbUser tbUser;
	public DaoTest(){
		ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		udao = (UserDAO) ctx.getBean("userDAO");


	} 

	public void testSave() {
		//		String hql = "from TbRightRole tbr where tbr.id.roleId = ?";
		//		List<TbRightRole> lister= udao.find(hql, 1l);
		//		for(int i = 0;i<lister.size();i++){
		//			System.out.println(lister.get(i).getTbRole().getRolename());
		//		}
		tbUser=new TbUser();
		tbUser.setPassword("202CB962AC59075B964B07152D234B70");
		tbUser.setPhoneNumber("132");
		tbUser.setEmail("123");
		tbUser.setUserName("垃圾人1");
		tbUser.setAccouont("admin3");
		TbRole r = new TbRole();r.setId(1l);
		tbUser.setTbRole(r);
		udao.save(tbUser);
	}
}