package net.cooliang.niotest.javanio.client.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import net.cooliang.niotest.constant.CommonConstant;

public class NioTimeClientHandler implements Runnable {

	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;

	public NioTimeClientHandler(String host, int port) {
		this.host = host == null ? "localhost" : host;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * (non-javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			doConnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				for (SelectionKey key : keys) {
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
				System.exit(1);
			}
		}
		// 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动·去注册(注销)·并关闭，所以不需要重复释放资源
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		// server: connect -> read
		if (key.isValid()) {
			// 判断是否连接成功
			if (key.isConnectable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else {
					System.exit(1); // 连接失败，进程退出
				}
			}
			if (key.isReadable()) {
				// Read the data
				doRead(key);
			}
		}
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
			System.out.println("Now is: " + body);
			this.stop = true;
		} else if (readBytes < 0) {
			// 对端链路关闭
			key.cancel();
			sc.close();
		} else {
			// 读到0字节，忽略
		}
	}

	private void doConnect() throws IOException {
		if (socketChannel.connect(new InetSocketAddress(host, port))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		} else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel sc) throws IOException {
		byte[] req = CommonConstant.QUERY_TIME_ORDER.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if (!writeBuffer.hasRemaining()) {
			System.out.println("Send order to server succeed");
		}
	}
}
