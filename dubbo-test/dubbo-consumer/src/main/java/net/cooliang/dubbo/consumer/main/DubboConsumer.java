package net.cooliang.dubbo.consumer.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.cooliang.dubbo.consumer.client.HelloClient;

public class DubboConsumer {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-context.xml", "spring/spring-rpc.xml" })) {
			HelloClient client = context.getBean(HelloClient.class);
			System.out.println(client.sayHello("cooliang"));
		}
	}

}
