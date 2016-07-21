package net.cooliang.dubbo.consumer.main;

import net.cooliang.dubbo.api.service.HelloService;
import net.cooliang.dubbo.consumer.util.ServiceConsumer;

public class ZkRmiClient {

	public static void main(String[] args) throws Exception {
		ServiceConsumer consumer = new ServiceConsumer("localhost:2181");
		HelloService service = consumer.lookup();
		System.out.println(service.sayHello("Cooliang"));
	}

}
