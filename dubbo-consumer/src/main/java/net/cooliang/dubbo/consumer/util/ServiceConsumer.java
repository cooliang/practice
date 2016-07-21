package net.cooliang.dubbo.consumer.util;

import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.jboss.netty.util.internal.ThreadLocalRandom;

import net.cooliang.dubbo.consumer.constant.ZkConstant;

public class ServiceConsumer {

	private CountDownLatch latch = new CountDownLatch(1);

	private String registryAddress;

	private volatile List<String> urlList = new ArrayList<String>();

	public ServiceConsumer(String registryAddress) {
		this.registryAddress = registryAddress;
		ZooKeeper zk = connectServer();
		if (zk != null) {
			watchNode(zk); // 观察 /registry 节点的所有子节点并更新 urlList 成员变量
		}
	}

	private ZooKeeper connectServer() {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(registryAddress, ZkConstant.ZK_SESSION_TIMEOUT, new Watcher() {
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

	private void watchNode(final ZooKeeper zk) {
		try {
			List<String> nodeList = zk.getChildren(ZkConstant.ZK_REGISTRY_PATH, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getType() == Event.EventType.NodeChildrenChanged) {
						watchNode(zk);
					}
				}
			});
			List<String> dataList = new ArrayList<String>(); // 用于存放/registry所有子节点中的数据
			for (String node : nodeList) {
				byte[] data = zk.getData(ZkConstant.ZK_REGISTRY_PATH + "/" + node, false, null); // 获取/registry的子节点中的数据
				dataList.add(new String(data));
			}
			urlList = dataList; // 更新最新的 RMI 地址
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找 RMI 服务
	public <T extends Remote> T lookup() {
		T service = null;
		int size = urlList.size();
		if (size > 0) {
			String url;
			if (size == 1) {
				url = urlList.get(0); // 若urlList中只有一个元素，则直接获取该元素
			} else {
				url = urlList.get(ThreadLocalRandom.current().nextInt(size)); // 若urlList中存在多个元素，则随机获取一个元素
			}
			service = lookupService(url); // 从 JNDI 中查找 RMI 服务
		}
		return service;
	}

	@SuppressWarnings("unchecked")
	private <T> T lookupService(String url) {
		T remote = null;
		try {
			remote = (T) Naming.lookup(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remote;
	}
}
