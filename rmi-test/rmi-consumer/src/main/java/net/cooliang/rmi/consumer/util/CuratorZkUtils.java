package net.cooliang.rmi.consumer.util;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event;

public class CuratorZkUtils {

	private static final int SESSION_TIMEOUT = 5000;

	private static final int CONNECT_TIMEOUT = 5000;

	private static final String NODE_PATH = "/registry";

	private List<String> urlList = new ArrayList<String>();

	private String registryAddress;

	public CuratorZkUtils(String registryAddress) {
		this.registryAddress = registryAddress;
		CuratorFramework client = connectServer();
		client.start();
		watchNode(client);
	}

	private CuratorFramework connectServer() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		return CuratorFrameworkFactory.builder()
				.connectString(registryAddress)
				.sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT)
				.retryPolicy(retryPolicy)
				.build();
	}

	private void watchNode(final CuratorFramework client) {
		try {
			List<String> nodeList = client.getChildren().usingWatcher(new CuratorWatcher() {
				@Override
				public void process(WatchedEvent event) throws Exception {
					if (event.getType() == Event.EventType.NodeChildrenChanged) {
						watchNode(client);
					}
				}
			}).forPath(NODE_PATH);
			List<String> dataList = new ArrayList<String>(); // 用于存放/registry所有子节点中的数据
			for (String node : nodeList) {
				byte[] data = client.getData().forPath(NODE_PATH + "/" + node);
				dataList.add(new String(data));
			}
			urlList = dataList; // 更新最新的 RMI 地址
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找 RMI 服务
	public <T> T lookup() {
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
