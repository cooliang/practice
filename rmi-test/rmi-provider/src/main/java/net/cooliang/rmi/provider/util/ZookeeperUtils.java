package net.cooliang.rmi.provider.util;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperUtils {

	private static final int SESSION_TIMEOUT = 5000;

	private static final String NODE_PATH = "/registry/register";

	private String registryAddress;

	public ZookeeperUtils(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public void register(String data) {
		if (data != null) {
			ZooKeeper zk = connectServer();
			if (zk != null) {
				createNode(zk, data);
			}
		}
	}

	private ZooKeeper connectServer() {
		ZooKeeper zk = null;
		try {
			final CountDownLatch latch = new CountDownLatch(1);
			zk = new ZooKeeper(registryAddress, SESSION_TIMEOUT, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown();
					}
				}
			});
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zk;
	}

	private void createNode(ZooKeeper zk, String data) {
		try {
			zk.create(NODE_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
