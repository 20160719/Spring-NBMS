package springcontrollers.excel;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	private Jedis redis;

	//@Before
	public void setUp() throws Exception {
		redis = new Jedis("192.168.234.133", 6379);
		System.out.println(redis.ping());
		//redis.auth("admin");
	}

	//@Test
	public void test() {
		redis.set("name", "aaa");
		String name =redis.get("name");
		System.out.println("name:"  + name);
	}

}
