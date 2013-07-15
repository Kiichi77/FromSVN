package cn.com.woyun.keystone;

import javax.ws.rs.client.WebTarget;

import cn.com.woyun.base.AbstractOpenStackClient;
import cn.com.woyun.base.OpenStack;

public class KeystoneClient extends AbstractOpenStackClient {
	
	public KeystoneClient(String endpointURL, String token) {
		super(endpointURL, token);
	}
	
	public KeystoneClient(String endpointURL) {
		super(endpointURL, null);
	}

	public <R> R execute(KeystoneCommand<R> command) throws Exception {
		WebTarget endpoint = OpenStack.CLIENT.target(endpointURL);
		if(token != null) {
			endpoint.configuration().register(tokenFilter);
		}
		return command.execute(endpoint);
	}

}

