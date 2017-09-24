package springcontrollers.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LamTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LamTest.class);
	//@Test 
	public void test() {
		List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
		list.parallelStream().forEach(System.out::println);
		
		Function<Integer, String> f = (a) -> a + "aaa";
		System.out.println(f.apply(3));
		f = f.andThen((b) -> b + "bb");
		System.out.println(f.apply(4));
		
		Consumer<String> c = (s) -> System.out.println(s);
		
		
		c = c.andThen((s -> System.out.println("sss" + s)));
		c.accept("ccc");
	}
	
	//@Test
	public void test01() {
		int sum = IntStream.range(0, 10).reduce(Integer::sum).getAsInt();
		System.out.println("sum: " + sum);
		IntStream.rangeClosed(0, 100).forEach(System.out::println);
	
		List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		System.out.println(list);
		
		List<String> strs = IntStream.range(0, 100).boxed().map(m -> m + "aaa").collect(Collectors.toList());
		strs.parallelStream().forEach(System.out::println);
		
	}
	
	//@Test
	public void test02() {
		int size = 1000;
		long stime = System.currentTimeMillis();
		IntStream.range(0, size).forEach(i -> {
			CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
				return "" + i;
			});
			CompletableFuture<Void> f1 = f.thenAcceptAsync(s -> System.out.println(s));
//			try {
//				f1.get();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
			CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
				return 40;
			});
			
			f.thenCombineAsync(f2, (x, y) -> Integer.valueOf(x) + y);
		});
		long etime = System.currentTimeMillis();
		System.out.println("total time: " + (etime - stime));
		
	}
	
	//@Test
	public void test03() {
		long t1 = System.currentTimeMillis();
		int size = 60000;
		List<People> list = getList(size);
		long t2 = System.currentTimeMillis();
		logger.info("t2 - t1：" + (t2 - t1));
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet("aaa");
		
		//List<Cell[]> cells = getCells(sheet, size);
		//Map<Integer, Cell[]> map = getCellsOfMap(sheet, size);
		
		CompletableFuture<List<Cell[]>> f = CompletableFuture.supplyAsync(() -> {
			return getCells(sheet, size);
		});
		
		/*CompletableFuture<Map<Integer, Cell[]>> f = CompletableFuture.supplyAsync(() -> {
			return getCellsOfMap(sheet, size);
		});*/
		
		long t3 = System.currentTimeMillis();
		logger.info("t3 - t2：" + (t3 - t2));
		logger.info("t3 - t1：" + (t3 - t1));
		
		CompletableFuture<Void> f2 = f.thenAcceptAsync((cellList) -> {
			list.stream().forEach(p -> {
				Cell[] cell = cellList.get(p.index);
				cell[0].setCellValue(p.name);
				cell[1].setCellValue(p.sex);
				cell[2].setCellValue(p.age);
			});
		}); 
		
//		list.stream().forEach(p -> {
//			Cell[] cell = cells.get(p.index);
//			cell[0].setCellValue(p.name);
//			cell[1].setCellValue(p.sex);
//			cell[2].setCellValue(p.age);
//		});
		long t4 = System.currentTimeMillis();
		logger.info("t4 - t3：" + (t4 - t3));
		logger.info("t4 - t1：" + (t4 - t1));
		try {
			File file = new File("C:\\Users\\zhanghong\\Desktop\\person.xls");
			FileOutputStream out = new FileOutputStream(file);
			System.out.println("aaa");
			//f2.get();
			System.out.println("bbb");
			workBook.write(out);
			workBook.close();
			out.flush();
			out.close();
			long t5 = System.currentTimeMillis();
			logger.info("t5 - t4：" + (t5 - t4));
			logger.info("t5 - t1：" + (t5 - t1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<People> getList(int size) {
		List<People> list = new ArrayList<People>(size);
		IntStream.range(0, size).forEach(i -> {
			list.add(new People(i, "aaa" + i, "m", i));
		});
		return list;
	}
	
	private static List<Cell[]> getCells(Sheet sheet, int size) {
		List<Cell[]> list = new ArrayList<Cell[]>(size);
		IntStream.range(0, size).forEach(i -> {
			Row row = sheet.createRow(i);
			list.add(new Cell[]{row.createCell(0), row.createCell(1), row.createCell(2)});
		});
		return list;
	}
	
	private static Map<Integer, Cell[]> getCellsOfMap(Sheet sheet, int size) {
		Map<Integer, Cell[]> map = new ConcurrentHashMap<Integer, Cell[]>(size);
		IntStream.range(0, size).parallel().forEach(i -> {
			Row row = sheet.createRow(i);
			map.put(i, new Cell[]{row.createCell(0), row.createCell(1), row.createCell(2)});
		});
		return map;
	}
	
	//@Test
	public void test04() {
		List<People> list = new ArrayList<People>(10);
		IntStream.range(0, 10).forEach(i -> {
			String sex = i % 2 == 0 ? "m" : "f";
			int age = 20 + i % 4;
			list.add(new People(i, "aaa" + i, sex, age));
		});
		Map<Integer, List<People>> mp1 = list.stream().collect(Collectors.groupingBy(People::getAge));
		for(Map.Entry<Integer, List<People>> m : mp1.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
		
		Map<String, Map<Integer, List<People>>> mp2 = list.stream().collect(Collectors.groupingBy(People::getSex, Collectors.groupingBy(People::getAge)));
		
		for(Map.Entry<String, Map<Integer, List<People>>> m : mp2.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}
	
	@Test
	public void test05() {
		String str = "aaa bbb ccc ddd";
		//Stream<Character> s = IntStream.range(0, str.length()).mapToObj(str::charAt);
		//countWords(s.parallel());
		Spliterator<Character> spliterator = new CounterSpliter(str);
		Stream<Character> s = StreamSupport.stream(spliterator, true);
		countWords(s);
	}
	
	private static void countWords(Stream<Character> s) {
		Counter counter = s.reduce(new Counter(0, true), Counter::accumulate, Counter::combine);
		System.out.println("count:" +counter.getCounter());
	}
	
	
}

class People {
	public int index;
	public String name;
	public String sex;
	public int age;
	
	public People() {
	}

	public People(int index, String name, String sex, int age) {
		this.index = index;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "People [index=" + index + ", name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}

class Counter {
	
	private final int counter;
	private final boolean lastSpace;
	
	public Counter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	public Counter accumulate(Character c) {
		if(Character.isWhitespace(c)) {
			return lastSpace ? this : new Counter(this.counter, true);
		} else {
			return lastSpace ? new Counter(this.counter + 1,  false) : this;
		}
	}
	
	public Counter combine(Counter counter) {
		return new Counter(this.counter + counter.counter, counter.lastSpace);
	}
	
	public int getCounter() {
		return this.counter;
	}
	
}

class CounterSpliter implements Spliterator<Character> { 
	
	private final String string;
	private int currentChar = 0;
	
	public CounterSpliter(String string) {
		this.string = string;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(string.charAt(currentChar++));
		return currentChar < string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length() - currentChar;
		if(currentSize < 10) return null;
		for(int i = currentSize / 2 + currentChar; i < string.length(); i++) {
			if(Character.isWhitespace(string.charAt(i))) {
				Spliterator<Character> spliterator = new CounterSpliter(string.substring(currentChar, i));
				currentChar = i;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
	
}






