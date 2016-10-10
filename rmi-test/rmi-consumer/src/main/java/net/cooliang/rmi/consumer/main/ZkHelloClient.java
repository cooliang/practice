package net.cooliang.rmi.consumer.main;

import net.cooliang.rmi.api.service.HelloService;
import net.cooliang.rmi.util.CuratorZkClient;

public class ZkHelloClient {

	public static void main(String[] args) throws Exception {
		CuratorZkClient client = new CuratorZkClient("localhost:2181");
		HelloService service = client.lookup("HelloService");
		System.out.println(service.sayHello("Cooliang"));
	}

}
