package net.cooliang.niotest.javanio.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.cooliang.niotest.constant.CommonConstant;
import net.cooliang.niotest.javanio.server.handler.NioTimeServerHandler;

public class NioTimeServer {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		NioTimeServerHandler handler = new NioTimeServerHandler(CommonConstant.PORT);
		executor.submit(handler);
		executor.shutdown();
	}
}
