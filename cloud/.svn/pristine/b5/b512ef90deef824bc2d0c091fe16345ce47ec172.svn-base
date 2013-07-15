package cn.com.woyun.nova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import cn.com.woyun.BaseTest;
import cn.com.woyun.nova.model.FloatingIp;
import cn.com.woyun.nova.model.FloatingIpPools.FloatingIpPool;

public class FloatingIpTest extends BaseTest {
	private String tmpId;
	
//	@Test
	public void listFloatingIpPools(){
		List<FloatingIpPool> l;
		try {
			l = floatingIpApi.listFloatingIpPools(tokenLogin.getAccess());
			for(FloatingIpPool aa:l){
				System.out.println(aa.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void listFloatingIps(){
		List<FloatingIp> l;
		try {
			l = floatingIpApi.listFloatingIps(tokenLogin.getAccess());
			for(FloatingIp aa:l){
				System.out.println(aa.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (dependsOnMethods={"listFloatingIpPools"})
	public void allocateFloatingIp() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入浮动IP池的名字：*********************************");
		tmpId = in.readLine();
		try {
			floatingIpApi.allocateFloatingIp(tokenLogin.getAccess(), tmpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test (dependsOnMethods={"allocateFloatingIp"})
	public void deallocateFloatingIp() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入释放浮动IP的序号：*********************************");
		tmpId = in.readLine();
		try {
			floatingIpApi.deallocateFloatingIp(tokenLogin.getAccess(), tmpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test (dependsOnMethods={"deallocateFloatingIp"})
	public void associateFloatingIp() throws IOException {
		String serverId;
		String floatingIpAddress;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入虚拟机ID：*********************************");
		serverId = in.readLine();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入浮动IP地址：*********************************");
		floatingIpAddress = in.readLine();
		
		try {
			floatingIpApi.associateFloatingIp(tokenLogin.getAccess(), serverId,floatingIpAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test (dependsOnMethods={"associateFloatingIp"})
	public void disassociateFloatingIp() throws IOException {
		String serverId;
		String floatingIpAddress;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入虚拟机ID：*********************************");
		serverId = in.readLine();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("[输入提示]*************************请输入浮动IP地址：*********************************");
		floatingIpAddress = in.readLine();
		
		try {
			floatingIpApi.disassociateFloatingIp(tokenLogin.getAccess(), serverId,floatingIpAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
