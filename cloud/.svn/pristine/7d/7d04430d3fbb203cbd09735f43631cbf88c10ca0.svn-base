

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import cn.com.kxcomm.ipmi.dao.UserDAO;
import cn.com.kxcomm.ipmi.entity.TbRightRole;
import cn.com.kxcomm.ipmi.entity.TbUser;



public class LabelLogTest {
	private ApplicationContext ctx;
	private UserDAO udao;
	private TbUser tbUser;
	public LabelLogTest(){
		ctx = new ClassPathXmlApplicationContext("modules/applicationContext.xml");
		if (ctx != null)
			System.out.println("success spring");
		udao = (UserDAO) ctx.getBean("userDAO");


	} 

	public void testSave() {
//		tbChassis=new TbChassis();
//		tbChassis.setCaseName("鑽�");	
//		dao.save(tbChassis);
//		Page<TbChassis> page = new Page<TbChassis>();
//		page.setPageSize(1);
//		page.setPageNo(1);
//		dao.findByPage(page, "from TbChassis", null);
		String hql = "from TbRightRole tbr where tbr.id.roleId = ?";
		List<TbRightRole> lister= udao.find(hql, 1l);
		for(int i = 0;i<lister.size();i++){
			System.out.println(lister.get(i).getTbRole().getRolename());
		}
	}
}