package com.netty.curator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

public class RoundRobinLoadBalance {

	public static final String NAME = "roundrobin";

	private static final AtomicInteger sequences = new AtomicInteger();

	public static String doSelect(List<String> callList) {
		// 取模轮循
		return callList.isEmpty() ? null : callList.get(sequences.getAndIncrement() % callList.size());
	}

	public static void main(String[] args) throws Exception {

		List<String> dataList = new ArrayList<String>();
		dataList.add("19.21.2.1");
		dataList.add("12.34.33.12");
		dataList.add("21.1.235.22");
		dataList.add("6.12.36.233");
		dataList.add("71.12.36.233");

		String ZK_ADDRESS = "127.0.0.1:8060";
		String Node = "IM";
		String path = "/zk_cluster/IM";
		// Connect to zk
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
		client.start();

		List<String> children = client.getChildren().forPath(path);
		System.out.println("size:" + children.size());
		// test
		ServerCurator zkList = new ServerCurator(client);
		// 先订阅
		zkList.subscribe(Node);
		// 添加节点
		dataList.stream().forEach(ip -> {
			try {
				zkList.register(Node, ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// 初始化一个统计的map,来检测每个节点被分配的次数
		Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
		dataList.stream().forEach(ip -> {
			try {
				map.putIfAbsent(ip, new AtomicInteger(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// Map
		for (int i = 0; i < 20000; i++) {
			String node = zkList.selectNode(Node);
			map.get(node).incrementAndGet();
		}

		for (Map.Entry<String, AtomicInteger> m : map.entrySet()) {
			System.out.println(String.format("[%s]一共被执行了:%s次", m.getKey(), m.getValue().get()));
		}
	}

}
