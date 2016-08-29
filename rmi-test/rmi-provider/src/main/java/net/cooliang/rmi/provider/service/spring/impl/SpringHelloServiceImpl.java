package net.cooliang.rmi.provider.service.spring.impl;

import net.cooliang.rmi.api.service.spring.SpringHelloService;

public class SpringHelloServiceImpl implements SpringHelloService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
