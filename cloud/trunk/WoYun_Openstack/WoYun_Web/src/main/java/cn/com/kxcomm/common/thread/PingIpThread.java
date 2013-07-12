package cn.com.kxcomm.common.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
/**
 * 用于检测没有设置防PING功能的节点是否接入了 网络
 * @author DongGod
 *
 */

public class PingIpThread  implements Callable  {
	private String ip;
	public PingIpThread(String ipp){
		ip=ipp;
	} 

	@Override
	public String call() throws Exception {
		try {
			if(!InetAddress.getByName(ip).isReachable(3000))
				return "";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ip;
	}

}
