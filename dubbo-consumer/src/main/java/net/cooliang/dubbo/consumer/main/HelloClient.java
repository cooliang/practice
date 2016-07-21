package net.cooliang.dubbo.consumer.main;

import java.rmi.Naming;

import net.cooliang.dubbo.api.service.HelloService;

public class HelloClient {

	public static void main(String[] args) throws Exception {
		HelloService service = (HelloService) Naming.lookup("rmi://localhost:8888/HelloService");
		System.out.println(service.sayHello("Cooliang"));
	}

}
