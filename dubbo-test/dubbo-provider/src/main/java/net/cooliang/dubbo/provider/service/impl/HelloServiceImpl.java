package net.cooliang.dubbo.provider.service.impl;

import net.cooliang.dubbo.api.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
