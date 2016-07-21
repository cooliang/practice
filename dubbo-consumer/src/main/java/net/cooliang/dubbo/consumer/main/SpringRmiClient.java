package net.cooliang.dubbo.consumer.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.cooliang.dubbo.api.service.DemoService;

public class SpringRmiClient {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-rpc.xml");
		DemoService service = (DemoService) context.getBean("demoService");
		System.out.println(service.sayHello("Cooliang"));
		context.close();
		context = null;
	}

}
