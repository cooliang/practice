package net.cooliang.niotest.javaaio.server.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServerHandler implements Runnable {

	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;

	public AsyncTimeServerHandler(int port) {
		try {
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
			System.out.println("The time server is start in port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * (non-javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doAccept() {
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}

}
