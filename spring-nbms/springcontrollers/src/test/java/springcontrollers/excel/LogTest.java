package springcontrollers.excel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

	@Test
	public void test() {
		logger.info("aaa");
		
		logger.error("bbb");
		logger.debug("ccc");
	}

}
