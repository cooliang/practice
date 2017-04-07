package net.cooliang.dubbo.provider.service.impl;

import org.springframework.stereotype.Service;

import net.cooliang.dubbo.api.service.HelloService;

@Service("helloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
