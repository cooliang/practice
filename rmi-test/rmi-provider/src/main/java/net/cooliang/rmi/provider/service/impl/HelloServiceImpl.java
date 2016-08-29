package net.cooliang.rmi.provider.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net.cooliang.rmi.api.service.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

	private static final long serialVersionUID = -4943113858239293782L;

	public HelloServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		return "Hello, " + name;
	}

}
