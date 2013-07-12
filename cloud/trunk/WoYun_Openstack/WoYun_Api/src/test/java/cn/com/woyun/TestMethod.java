package cn.com.woyun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

public class TestMethod extends BaseTest {
//	@Test
	public void testMethod1() {
		System.out.println("in testMethod1");
	}

//	@Test(dependsOnMethods = "testMethod1")
	public void testMethod2() {
		System.out.println("in testMethod2");
	}

//	@Test
	public void testApi() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入一行字符：");
		String temp = in.readLine();
		System.out.println(temp);
	}
}
