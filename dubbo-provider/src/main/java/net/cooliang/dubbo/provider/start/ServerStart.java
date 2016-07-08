package net.cooliang.dubbo.provider.start;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStart {

	private static final Logger logger = Logger.getLogger(ServerStart.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		PropertyConfigurator.configure(ServerStart.class.getClassLoader().getResource("log4j.properties").getPath());
		logger.info("log4j.properties loaded.");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		context.start();
		logger.info("server is starting.");
		while (true) {
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
			}
		}
	}
}
