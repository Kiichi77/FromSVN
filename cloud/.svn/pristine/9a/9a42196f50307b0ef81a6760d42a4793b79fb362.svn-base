package test.dao;

import org.testng.annotations.Test;

import test.BaseTest;
import cn.com.kxcomm.contractmanage.entity.TbRight;
import cn.com.kxcomm.contractmanage.entity.TbRightRole;
import cn.com.kxcomm.contractmanage.entity.TbRightRoleId;
import cn.com.kxcomm.contractmanage.entity.TbRole;

public class ContractReceivePaymentDAOTest extends BaseTest {

	//	@Test
	public void sumPayment(){
		Double total = contractReceivePaymentDataDAO.sumReceiveMoney(1);
		System.out.println(total);
	}
	@Test
	public void ll(){
		Long roleId = 8l;
		//当前的权限
		TbRight right = this.rightDAO.findById(21);
		System.out.println(right.toString());
		//判断二级菜单的父级菜单是否保存关系
		TbRightRole query =rightRoleDAO.query(roleId, right.getParid());
		Long parId = Long.parseLong(right.getParid().toString());
		if(query==null){
			TbRightRoleId rightRole = new TbRightRoleId();
			rightRole.setRoleId(roleId);
			rightRole.setRightId(parId);
			TbRightRole rightRole2 = new TbRightRole();
			rightRole2.setId(rightRole);
			this.roleDAO.updateRoleRight(rightRole2);
			System.out.println("add");
		}else{
			System.out.println(query.getId());
		}
		
	}
}
