package cn.com.woyun.keystone;

import javax.ws.rs.client.WebTarget;

public interface KeystoneCommand<R> {

	R execute(WebTarget endpoint) throws Exception;
	
}
