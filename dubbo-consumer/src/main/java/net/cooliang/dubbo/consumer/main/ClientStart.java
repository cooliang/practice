package net.cooliang.dubbo.consumer.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.cooliang.dubbo.api.service.DemoService;

public class ClientStart {

	private static final Logger logger = Logger.getLogger(ClientStart.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure(ClientStart.class.getClassLoader().getResource("log4j.properties").getPath());
		logger.info("log4j.properties loaded.");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		DemoService service = context.getBean(DemoService.class);
		logger.info(service.sayHello("Cooliang"));
		context.close();
		System.exit(0);
	}

}
