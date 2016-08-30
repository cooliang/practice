package net.cooliang.rmi.provider.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import net.cooliang.rmi.api.constant.RmiConstant;
import net.cooliang.rmi.api.service.HelloService;
import net.cooliang.rmi.provider.service.impl.HelloServiceImpl;
import net.cooliang.rmi.provider.util.CuratorZkUtils;

public class ZkHelloServer {

	public static void main(String[] args) throws Exception {
		// 创建远程调用对象
		HelloService service = new HelloServiceImpl();
		// 创建8888端口号的本地注册表，用于远程调用对象的绑定
		LocateRegistry.createRegistry(8888);
		// 把远程调用对象注册到RMI注册服务器
		Naming.bind(RmiConstant.RMI_PATH_HELLO_SERVICE, service);
		// 发布注册到Zookeeper
		CuratorZkUtils registry = new CuratorZkUtils("localhost:2181");
		registry.register(RmiConstant.RMI_PATH_HELLO_SERVICE);
	}

}
