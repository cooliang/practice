package net.cooliang.dubbo.provider.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rpc.xml");
		context.start();
		while (true) {
			Thread.sleep(300 * 1000);
		}
	}

}
