package net.cooliang.dubbo.provider.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRmiServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-rpc.xml");
		context.start();
	}

}
