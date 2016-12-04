package net.cooliang.niotest.javaaio.server;

import net.cooliang.niotest.constant.CommonConstant;
import net.cooliang.niotest.javaaio.server.handler.AsyncTimeServerHandler;

public class AsyncTimeServer {

	public static void main(String[] args) {
		AsyncTimeServerHandler handler = new AsyncTimeServerHandler(CommonConstant.PORT);
		new Thread(handler, "AIO-AsyncTimeServerHandler-001").start();
	}

}
