package net.cooliang.rmi.provider.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class CuratorZkUtils {

	private static final int SESSION_TIMEOUT = 5000;

	private static final int CONNECT_TIMEOUT = 5000;

	private static final String NODE_PATH = "/registry/register";

	private String registryAddress;

	public CuratorZkUtils(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public void register(String data) {
		if (null != data) {
			CuratorFramework client = connectServer();
			client.start();
			createNode(client, data);
		}
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

	private void createNode(CuratorFramework client, String data) {
		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
					.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(NODE_PATH, data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
