package springcontrollers.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {

	public static void main(String[] args) {
		// test01();
		test02();

	}

	private static void test01() {
		List<Person> list = getList();
		// list.parallelStream().forEach(System.out::println);
		List<SplitList<Person>> splitList = getSplitList(list, list.size());
		splitList.stream().forEach((sp) -> {
			sp.list.parallelStream().forEach(s -> printObj(s));
		});

	}

	private static void test02() {
		List<Person> list = getList();
		List<SplitList<Person>> splitList = getSplitList(list, list.size());
		ExcelUtils.createXlsExcel(splitList);
	}

	public static <T> void printObj(T t) {
		System.out.println(t);
	}

	private static List<Person> getList() {
		List<Person> list = new ArrayList<Person>(65535);
		int size = 65535;
		Person person = null;
		for (int i = 0; i < size; i++) {
			person = new Person();
			person.seq = i;
			person.name = "aaa" + i;
			person.age = 20 + (i % 30);
			list.add(person);
		}
		return list;
	}

	private static <T> List<SplitList<T>> getSplitList(List<T> list, int page) {
		List<SplitList<T>> splitList = new ArrayList<SplitList<T>>();
		int size = list.size();
		page = page == 0 ? 40000 : page;
		int count = size / page;
		count = size % page == 0 ? count : count + 1;
		SplitList<T> split = null;
		int start = 0;
		int end = 0;
		for (int i = 0; i < count; i++) {
			split = new SplitList<T>();
			start = i * page;
			end = (i + 1) * page;
			end = end >= size ? size : end;
			split.seq = start;
			split.list = list.subList(start, end);
			splitList.add(split);
		}
		return splitList;
	}

}

class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public int seq;
	public String name;
	public int age;

	@Override
	public String toString() {
		return "Person [seq=" + seq + ", name=" + name + ", age=" + age + "]";
	}

}

class SplitList<T> {

	public int seq;
	public List<T> list;

}

class ExcelUtils {

	public static void createXlsExcel(List<SplitList<Person>> splitList) {
		try {
//			final Workbook workBook = new HSSFWorkbook();
			File file = new File("C:\\Users\\Administrator\\Desktop\\java\\person.xlsx");
			file.mkdir();
			FileOutputStream out = new FileOutputStream(file);
			 final Workbook workBook = new XSSFWorkbook();

			createExcel(workBook, splitList);

			workBook.write(out);
			out.flush();
			out.close();
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createExcel(Workbook workBook, List<SplitList<Person>> splitList) throws Exception {
		long sTime = new Date().getTime();
		Sheet sheet = workBook.createSheet("sheet");
	
		splitList.stream().map(sp -> {
			try {
				return CompletableFuture.runAsync(() -> {
					sp.list.stream().forEach(p -> {
						createRow(sheet, p);
					});
				});
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList()).stream().forEach(f -> {
			try {
				f.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		long eTime = new Date().getTime();
		System.out.println("total time: " + (eTime - sTime));
	}

	private static void createRow(Sheet sheet, Person p) {
		Row row = sheet.createRow(p.seq);
		Cell c0 = row.createCell(0);
		Cell c1 = row.createCell(1);
		Cell c2 = row.createCell(2);
		c0.setCellValue(p.name);
		c1.setCellValue(p.age);
		c2.setCellValue(p.seq);
	}

}
