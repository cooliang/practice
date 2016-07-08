package net.cooliang.dubbo.provider.service.impl;

import org.springframework.stereotype.Service;

import net.cooliang.dubbo.api.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
