package springcontrollers.excel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

	//@Test
	public void test() {
		for(int i = 0; i < 100; i++) {
			logger.info("aaa");
			logger.error("bbb");
			logger.debug("ccc");
		}
		
	}
	
	@Test
	public void test01() {
		int s = 6; //1 1 2
		System.out.println(1 ^ 1);
		System.out.println(1 ^ 1);
		System.out.println(2 ^ 0);
	}

}
