package net.cooliang.dubbo.provider.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.cooliang.dubbo.api.facade.HelloFacade;
import net.cooliang.dubbo.api.rpc.Result;
import net.cooliang.dubbo.provider.service.HelloService;

@Component("helloFacade")
public class HelloFacadeImpl implements HelloFacade {

	@Autowired
	private HelloService helloService;

	@Override
	public Result<String> sayHello(String name) {
		return Result.createResult(helloService.sayHello(name));
	}

}
