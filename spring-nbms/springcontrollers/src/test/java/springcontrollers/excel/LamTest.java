package springcontrollers.excel;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LamTest {

	@Test 
	public void test() {
		List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
		list.parallelStream().forEach(System.out::println);
	}

}
