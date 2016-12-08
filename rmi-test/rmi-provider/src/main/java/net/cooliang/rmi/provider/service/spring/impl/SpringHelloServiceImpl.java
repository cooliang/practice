package net.cooliang.rmi.provider.service.spring.impl;

import net.cooliang.rmi.api.entity.User;
import net.cooliang.rmi.api.service.spring.SpringHelloService;

public class SpringHelloServiceImpl implements SpringHelloService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

	@Override
	public boolean valid(User user) {
		if (user != null && "lisi".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
