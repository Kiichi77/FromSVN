package cn.com.woyun.nova;

import javax.ws.rs.client.WebTarget;

import cn.com.woyun.base.AbstractOpenStackClient;
import cn.com.woyun.base.OpenStack;


public class NovaClient extends AbstractOpenStackClient {
	
	public NovaClient(String endpointURL, String token) {
		super(endpointURL, token);
	}

	public <R> R execute(NovaCommand<R> command) throws Exception {
		WebTarget endpoint = OpenStack.CLIENT.target(endpointURL);
		if(token != null) {
			endpoint.configuration().register(tokenFilter);
		}
		return command.execute(endpoint);
	}

}
