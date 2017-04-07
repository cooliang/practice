package net.cooliang.dubbo.consumer.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.cooliang.dubbo.api.service.HelloService;

public class DubboConsumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rpc.xml");
		context.start();
		HelloService service = context.getBean(HelloService.class);
		System.out.println(service.sayHello("cooliang"));
		context.close();
	}

}
