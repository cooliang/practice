package net.cooliang.rmi.provider.start;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import net.cooliang.rmi.api.service.HelloService;
import net.cooliang.rmi.provider.service.impl.HelloServiceImpl;

public class HelloServer {

	public static void main(String[] args) throws Exception {
		// 创建远程调用对象
		HelloService service = new HelloServiceImpl();
		// 创建8888端口号的本地注册表，用于远程调用对象的绑定
		LocateRegistry.createRegistry(8888);
		// 把远程调用对象注册到RMI注册服务器
		Naming.bind("rmi://localhost:8888/HelloService", service);
	}

}
