package net.cooliang.dubbo.provider.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {

	public static void main(String[] args) throws InterruptedException {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-context.xml", "spring/spring-rpc.xml" })) {
			while (true) {
				Thread.sleep(300 * 1000);
			}
		}
	}

}
