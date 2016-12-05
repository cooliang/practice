package net.cooliang.niotest.javanio.server.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import net.cooliang.niotest.constant.CommonConstant;

public class NioTimeServerHandler implements Runnable {

	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean stop;

	/**
	 * 初始化多路复用器、绑定监听端口
	 * 
	 * @param port
	 */
	public NioTimeServerHandler(int port) {
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		this.stop = true;
	}

	/**
	 * (non-javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (IOException e) {
						if (null != key) {
							key.cancel();
							if (null != key.channel()) {
								key.channel().close();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动·去注册(注销)·并关闭，所以不需要重复释放资源
		if (selector != null) {
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws Exception {
		// server: accept -> read
		if (key.isValid()) {
			// 处理新接入的请求消息
			if (key.isAcceptable()) {
				// Accept the new connection
				doAccept(key);
			}
			if (key.isReadable()) {
				// Read the data
				doRead(key);
			}
		}
	}

	private void doAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		// Add the new connection to the selector
		sc.register(selector, SelectionKey.OP_READ);
		ssc.register(selector, SelectionKey.OP_ACCEPT);
	}

	private void doRead(SelectionKey key) throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		int readBytes = sc.read(readBuffer);
		if (readBytes > 0) {
			readBuffer.flip();
			byte[] bytes = new byte[readBuffer.remaining()];
			readBuffer.get(bytes);
			String body = new String(bytes, "utf-8");
			System.out.println("The time server receive order: " + body);
			String currentTime = CommonConstant.QUERY_TIME_ORDER.equalsIgnoreCase(body)
					? new java.util.Date().toString() : "BAD ORDER";
			doWrite(sc, currentTime);
		} else if (readBytes < 0) {
			// 对端链路关闭
			key.cancel();
			sc.close();
		} else {
			// 读到0字节，忽略
		}
	}

	private void doWrite(SocketChannel channel, String message) throws IOException {
		if (null != message && message.trim().length() > 0) {
			byte[] bytes = message.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
		}
	}

}
