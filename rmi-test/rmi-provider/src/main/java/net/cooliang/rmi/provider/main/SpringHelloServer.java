package net.cooliang.rmi.provider.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloServer {

	private static final Logger logger = Logger.getLogger(SpringHelloServer.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		PropertyConfigurator.configure(SpringHelloServer.class.getClassLoader().getResource("log4j.properties").getPath());
		logger.info("log4j.properties loaded.");
		String[] paths = new String[] { "classpath:spring/spring-rpc.xml" };
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(paths);
		context.registerShutdownHook(); // 非web项目，关闭Spring IoC容器
		logger.info("server is starting.");
	}

}
