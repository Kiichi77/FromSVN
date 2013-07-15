package cn.com.woyun.nova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.nova.model.PersonalityFile;
import cn.com.woyun.nova.model.Server;
import cn.com.woyun.nova.model.Server.Addresses;
import cn.com.woyun.nova.model.ServerAction.Rebuild;
import cn.com.woyun.nova.model.ServerForCreate;

public class ServersTest extends BaseTest {

	/**
	 * 
	* 方法用途和描述: 获取所有的服务器信息
	* @author chenliang 新增日期：2012-11-21
	* @since WoYun_Api
	* @return 
	* @param
	 */
//	@Test
	public void listServers(){
		try {
			serversApi.listServer(tokenLogin.getAccess(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 根据实例id获取实例详情
	* @throws IOException
	* @author chenliang 新增日期：2012-11-23
	* @since WoYun_Api
	 */
//	@Test
	public void showServerDetail() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入租户id：*********************************");
			id= in.readLine();
			Server server = serversApi.showServerDetail(tokenLogin.getAccess(), id);
			System.out.println("获取成功："+server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述:根据实例id获取地址
	* @throws IOException
	* @author chenliang 新增日期：2012-11-23
	* @since WoYun_Api
	 */
//	@Test
	public void showServerAddress() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入租户id：*********************************");
			id= in.readLine();
			Addresses addresses = serversApi.showServerAddresses(tokenLogin.getAccess(), id);
			System.out.println("获取成功："+addresses);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 创建实例 
	* @author chenliang 新增日期：2012-11-23
	* @since WoYun_Api
	 */
//	@Test
	public void createServer(){
		try {
			ServerForCreate serverForCreate = new ServerForCreate();
			serverForCreate.setName("server-1");
			serverForCreate.setImageRef("35ce19de-3a6d-49f6-9f90-057235be501f");
			serverForCreate.setFlavorRef("1");
			Server server;
			server = serversApi.createServer(tokenLogin.getAccess(), serverForCreate);
			System.out.println("创建成功："+server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 修改实例
	* @author chenliang 新增日期：2012-11-23
	* @since WoYun_Api
	 */
	//TODO
//	@Test
	public void updateServer(){
		try {
			ServerForCreate serverForCreate = new ServerForCreate();
	//		serverForCreate.setName("server_testupdate_2");
			serverForCreate.setDiskConfig("AUTO"); //"OS-DCF:diskConfig": "AUTO"
			serversApi.updateServer(tokenLogin.getAccess(), serverForCreate,"6ec1b736-b53e-40ed-80ab-9ef12ef7b1f7");
			System.out.println("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 删除实例
	* @author chenliang 新增日期：2012-11-23
	* @since WoYun_Api
	 */
	//@Test
	public void deleteServer(){
		try {
			serversApi.deleteServer(tokenLogin.getAccess(), "b19967b9-4b9a-45ad-93c6-27d4e42b697c");
			System.out.println("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 重启实例
	* @author chenliang 新增日期：2012-11-26
	 * @throws IOException 
	* @since WoYun_Api
	 */
//	@Test
	public void reBootServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要重启的实例id：*********************************");
			id= in.readLine();
			serversApi.reBootServer(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述:重建实例
	* @author chenliang 新增日期：2012-11-26
	 * @throws IOException 
	* @since WoYun_Api
	 */
//	@Test
	public void reBuildServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要重建的实例id：*********************************");
			id= in.readLine();
			Rebuild rebuild = new Rebuild();
			rebuild.setName("foobar");
			rebuild.setAdminPass("seekr3t");
			rebuild.setAccessIPv4("");
			rebuild.setAccessIPv6("");
			Map<String, String> metadata = new HashMap<String, String>();
			metadata.put("meta val", "meta val");
			rebuild.setMetadata(metadata);
			List<PersonalityFile> personList = new ArrayList<PersonalityFile>();
			PersonalityFile person = new PersonalityFile();
			person.setPath("/etc/banner.txt");
			person.setContents("ICAgICAgDQoiQSBjbG91ZCBkb2VzIG5vdCBrbm93IHdoeSBp dCBtb3ZlcyBpbiBqdXN0IHN1Y2ggYSBkaXJlY3Rpb24gYW5k IGF0IHN1Y2ggYSBzcGVlZC4uLkl0IGZlZWxzIGFuIGltcHVs c2lvbi4uLnRoaXMgaXMgdGhlIHBsYWNlIHRvIGdvIG5vdy4g QnV0IHRoZSBza3kga25vd3MgdGhlIHJlYXNvbnMgYW5kIHRo ZSBwYXR0ZXJucyBiZWhpbmQgYWxsIGNsb3VkcywgYW5kIHlv dSB3aWxsIGtub3csIHRvbywgd2hlbiB5b3UgbGlmdCB5b3Vy c2VsZiBoaWdoIGVub3VnaCB0byBzZWUgYmV5b25kIGhvcml6 b25zLiINCg0KLVJpY2hhcmQgQmFjaA==");
			personList.add(person);
			rebuild.setPersonality(personList);
			serversApi.reBuildServer(tokenLogin.getAccess(), id, rebuild);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 暂停服务
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void pausesServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要暂停的实例id：*********************************");
			id= in.readLine();
			serversApi.pausesServer(tokenLogin.getAccess(),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 取消暂停
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void unPausesServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要取消暂停的实例id：*********************************");
			id= in.readLine();
			serversApi.unPauseServer(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 停止服务
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void stopServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要终止的实例id：*********************************");
			id= in.readLine();
			serversApi.stopServer(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* 方法用途和描述: 启动服务
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void startServer() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,输入需要启动的实例id：*********************************");
			id= in.readLine();
			serversApi.startServer(tokenLogin.getAccess(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	* 方法用途和描述: 修改密码
	* @throws IOException
	* @author chenliang 新增日期：2012-11-26
	* @since WoYun_Api
	 */
//	@Test
	public void changePassword() throws IOException{
		String id = "";
		List<Server> serverList;
		try {
			serverList = serversApi.listServer(tokenLogin.getAccess(), false);
			for (Server server : serverList) {
				System.out.println("[server]"+server.toString());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("[输入提示]*************************根据以上列表,请输入实例id：*********************************");
			id= in.readLine();
			serversApi.changePasswordServer(tokenLogin.getAccess(), id, "foo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
