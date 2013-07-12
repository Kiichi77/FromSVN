package cn.com.woyun.nova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.nova.model.SecurityGroup;
import cn.com.woyun.nova.model.SecurityGroup.Rule;
import cn.com.woyun.nova.model.SecurityGroupRuleForCreate;

public class SecurityGroupTest extends BaseTest {

	/**
	 * 
	* 方法用途和描述: 获取所有安全组集合
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void listSecurityGroup(){
		List<SecurityGroup> securityGroupList;
		try {
			securityGroupList = securityGroupApi.listSecurityGroup(tokenLogin.getAccess());
			for (SecurityGroup securityGroup : securityGroupList) {
				System.out.println("[securityGroup]"+securityGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据安全组id获取安全组信息
	* @throws NumberFormatException
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void showSecurityGroup() throws NumberFormatException, IOException{
		try {
			Integer id = 0;
			List<SecurityGroup> securityGroupList;
				securityGroupList = securityGroupApi.listSecurityGroup(tokenLogin.getAccess());
			for (SecurityGroup securityGroup : securityGroupList) {
				System.out.println("[securityGroup]"+securityGroup);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入安全组id：*********************************");
			id= Integer.valueOf(in.readLine().toString());
			SecurityGroup securityGroup = securityGroupApi.showSecurityGroup(tokenLogin.getAccess(), id);
			System.out.println("[securityGroup]"+securityGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void CreateSecurityGroup(){
		SecurityGroup securityGroup;
		try {
			securityGroup = securityGroupApi.createSecurityGroup(tokenLogin.getAccess(), "name1", "description");
			System.out.println("[securityGroup]"+securityGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除安全组
	* @throws NumberFormatException
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void deleteSecurityGroup() throws NumberFormatException, IOException{
		Integer id = 0;
		List<SecurityGroup> securityGroupList;
		try {
			securityGroupList = securityGroupApi.listSecurityGroup(tokenLogin.getAccess());
			for (SecurityGroup securityGroup : securityGroupList) {
				System.out.println("[securityGroup]"+securityGroup);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入安全组id：*********************************");
			id= Integer.valueOf(in.readLine().toString());
			securityGroupApi.deleteSecurityGroup(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建安全组规则
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void createSecrityGroupRule() throws IOException{
		try {
			SecurityGroupRuleForCreate ruleForCreateGroupId = new SecurityGroupRuleForCreate(5, "tcp", 1, 65535, "");
			SecurityGroupRuleForCreate ruleForCreateCidr = new SecurityGroupRuleForCreate(6, "tcp", 1, 65535,"0.0.0.0/0");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************选择需要创建的规则，1.根据groupid创建，2.根据cidr创建*********************************");
			String id= in.readLine().toString();
			Rule rule = null;
			if("1".equals(id)){
					rule = securityGroupApi.createSecurityGroupRule(tokenLogin.getAccess(), ruleForCreateGroupId, true);
			}else if("2".equals(id)){
				rule = securityGroupApi.createSecurityGroupRule(tokenLogin.getAccess(), ruleForCreateCidr, false);
			}
			System.out.println("[rule]"+rule);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void deleteSecrityGroupRule() throws NumberFormatException, IOException{
		List<SecurityGroup> securityGroupList;
		try {
			securityGroupList = securityGroupApi.listSecurityGroup(tokenLogin.getAccess());
			for (SecurityGroup securityGroup : securityGroupList) {
				System.out.println("[securityGroup]"+securityGroup);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************选择需要创建的规则，输入安全组规则id*********************************");
			Integer id= Integer.parseInt(in.readLine().toString());
			securityGroupApi.deleteSecurityGroupRule(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
