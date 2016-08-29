package net.cooliang.rmi.consumer.main;

import java.rmi.Naming;

import net.cooliang.rmi.api.constant.RmiConstant;
import net.cooliang.rmi.api.service.HelloService;

public class HelloClient {

	public static void main(String[] args) throws Exception {
		HelloService service = (HelloService) Naming.lookup(RmiConstant.RMI_PATH_HELLO_SERVICE);
		System.out.println(service.sayHello("Cooliang"));
	}

}
