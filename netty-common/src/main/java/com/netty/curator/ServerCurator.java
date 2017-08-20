package com.netty.curator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ServerCurator {
	
	public static final String ZK_CLUSTER_PATH = "/zk_cluster";
	
	private CuratorFramework cf;
	
	private Map<String, String> nodeToIp = new ConcurrentHashMap<String, String>();
	
	public ServerCurator() {
	}

	public ServerCurator(CuratorFramework cf) {
		this.cf = cf;
	}

	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	public void register(String nodeName, String ip) throws Exception {
		String path = ZK_CLUSTER_PATH + "/" + nodeName;
		Stat stat = cf.checkExists().forPath(path);
		if(null == stat) {
			cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
		}
		nodeToIp.put(nodeName, ip);
		cf.create().withMode(CreateMode.EPHEMERAL).forPath(path + "/" + ip, ip.getBytes());
	}
	
	public void subscribe(String nodeName) throws Exception {
		final String path = ZK_CLUSTER_PATH + "/" + nodeName;
		Stat stat = cf.checkExists().forPath(path);
		if(null == stat) {
			cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
		}
		PathChildrenCache cache = new PathChildrenCache(cf, path, true);
		cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener((CuratorFramework client, PathChildrenCacheEvent event) -> {
			List<String> children = client.getChildren().forPath(path);
			Collections.sort(children);
			map.put(nodeName, children);
		});
	}
	
	public String selectNode(String nodeName) {
		return nodeToIp.get(nodeName);
	}
	
}
