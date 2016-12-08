package net.cooliang.rmi.api.service.spring;

import net.cooliang.rmi.api.entity.User;

public interface SpringHelloService {

	public String sayHello(String name);

	public boolean valid(User user);

}
