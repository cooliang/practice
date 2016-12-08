package net.cooliang.rmi.consumer.main;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.cooliang.rmi.api.entity.User;
import net.cooliang.rmi.api.service.spring.SpringHelloService;

public class SpringHelloClient {

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(SpringHelloClient.class.getClassLoader().getResource("log4j.properties").getPath());
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-rpc.xml");
		SpringHelloService service = context.getBean(SpringHelloService.class);
		System.out.println(service.valid(new User("lisi", "123456")));
		context.close();
		context = null;
	}

}
