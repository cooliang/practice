package net.cooliang.dubbo.consumer.client;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.cooliang.dubbo.api.facade.HelloFacade;
import net.cooliang.dubbo.api.rpc.Result;
import net.cooliang.dubbo.consumer.annotation.Loggable;
import net.cooliang.dubbo.consumer.annotation.Retryable;

@Component("helloClient")
public class HelloClient {

	@Resource(name = "helloFacade")
	private HelloFacade helloFacade;

	@Loggable
	@Retryable
	public String sayHello(String name) {
		Result<String> res = helloFacade.sayHello(name);
		if (Result.checkSuccess(res)) {
			return res.getData();
		}
		return null;
	}

}
