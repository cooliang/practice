package net.cooliang.rmi.consumer.main;

import net.cooliang.rmi.api.service.HelloService;
import net.cooliang.rmi.consumer.util.CuratorZkUtils;

public class ZkHelloClient {

	public static void main(String[] args) throws Exception {
		CuratorZkUtils consumer = new CuratorZkUtils("localhost:2181");
		HelloService service = consumer.lookup();
		System.out.println(service.sayHello("Cooliang"));
	}

}
