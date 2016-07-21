package net.cooliang.dubbo.provider.start;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import net.cooliang.dubbo.api.service.HelloService;
import net.cooliang.dubbo.provider.service.impl.HelloServiceImpl;
import net.cooliang.dubbo.provider.util.ServiceProvider;

public class ZkRmiServer {

	public static void main(String[] args) throws Exception {
		// 创建远程调用对象
		HelloService service = new HelloServiceImpl();
		// 创建8888端口号的本地注册表，用于远程调用对象的绑定
		LocateRegistry.createRegistry(8888);
		// 把远程调用对象注册到RMI注册服务器
		String url = "rmi://localhost:8888/HelloService";
		Naming.rebind(url, service);
		// 发布注册到Zookeeper
		ServiceProvider registry = new ServiceProvider("localhost:2181");
		registry.register(url);
	}

}
