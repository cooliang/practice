package net.cooliang.rmi.util;

import java.rmi.Naming;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CuratorZkClient {

	private static final int SESSION_TIMEOUT = 5000;

	private static final int CONNECT_TIMEOUT = 5000;

	private static final String NODE_PATH = "/registry";

	private CuratorFramework client;

	public CuratorZkClient(String registryAddress) {
		client = CuratorFrameworkFactory.builder().connectString(registryAddress).sessionTimeoutMs(SESSION_TIMEOUT)
				.connectionTimeoutMs(CONNECT_TIMEOUT).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();
	}

	public void register(String serviceName, String url) {
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
					.forPath(NODE_PATH + "/" + serviceName + "/" + serviceName, url.getBytes());
		} catch (Exception e) {
		}
	}

	// 查找 RMI 服务
	public <T> T lookup(String serviceName) {
		T service = null;
		try {
			String serviceRootNode = NODE_PATH + "/" + serviceName;
			List<String> services = client.getChildren().forPath(serviceRootNode);
			if (CollectionUtils.isNotEmpty(services)) {
				String path = null;
				int size = services.size();
				if (size == 1) {
					path = serviceRootNode + "/" + services.get(0);
				} else {
					path = serviceRootNode + "/" + services.get(ThreadLocalRandom.current().nextInt(size));
				}
				byte[] data = client.getData().forPath(path);
				service = lookupService(new String(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
