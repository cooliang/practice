package net.cooliang.niotest.javanio.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.cooliang.niotest.constant.CommonConstant;
import net.cooliang.niotest.javanio.client.handler.NioTimeClientHandler;

public class NioTimeClient {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		NioTimeClientHandler handler = new NioTimeClientHandler("localhost", CommonConstant.PORT);
		executor.submit(handler);
		executor.shutdown();
	}
}
