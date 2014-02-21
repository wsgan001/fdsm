package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import javax.naming.spi.DirStateFactory.Result;

import info.*;

public class readText {

	/**
	 * Free memory
	 * 
	 * @param inputFileWithPath
	 *            file with Path
	 * @param start
	 *            int, the number of start line, nature number (that means the
	 *            first linenumber is equal 1)
	 * @param numberOflines
	 *            how many lines do you want to see
	 * @return
	 * @throws Exception
	 */
	public static void readTextline(String inputFileWithPath, int start,
			int numberOflines) {

		File file = new File(inputFileWithPath);

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;
			for (int i = 0; i < start - 1; i++) {
				br.readLine();

			}

			for (int i = start - 1; i < start + numberOflines - 1; i++) {
				line = br.readLine();

				System.out.println(line);

			}

			br.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-2);
		}

	}

	public static void writeText(String outputFileWithPath) {

		File file = new File(outputFileWithPath);

		try {

			BufferedWriter bw;
			if (file.exists()) {
				System.out
						.println("file exist! The information will be appended");
				bw = new BufferedWriter(new FileWriter(file, true));
			} else {
				System.out
						.println("file not exist! It will create a new file!");
				bw = new BufferedWriter(new FileWriter(file));
			}

			bw.write("haha");
			bw.newLine();
			bw.write("huhu");
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void getSystemInfos() {

		Properties props = System.getProperties();

		System.out.println(props.getProperty("os.name"));

	}

	public static void sysIndependent() {
		// Anzeige aller Zeitzonen-IDs:
		String[] ssIDs = TimeZone.getAvailableIDs();
		for (int i = 0; i < ssIDs.length; i++)
			System.out.println(ssIDs[i]);
		// Anzeige der aktuellen Default-Zeitzone:
		System.out.println("Default-Zeitzone = "
				+ TimeZone.getDefault().getID()); // z.B. 'Europe/Berlin'
		System.out.println("user.timezone = "
				+ System.getProperty("user.timezone")); // z.B. 'Europe/Berlin'

	}

	public static void timeExample() {
		// Anzeige aller Zeitzonen-IDs:
		String[] ssIDs = TimeZone.getAvailableIDs();
		for (int i = 0; i < ssIDs.length; i++)
			System.out.println(ssIDs[i]);
		// Anzeige der aktuellen Default-Zeitzone:
		System.out.println("Default-Zeitzone = "
				+ TimeZone.getDefault().getID()); // z.B. 'Europe/Berlin'
		System.out.println("user.timezone = "
				+ System.getProperty("user.timezone")); // z.B. 'Europe/Berlin'

	}

	public enum EnumExample {
		A, B, C, D
	}

	public enum Chara {
		A(1, 2), B(3, 4), C(5, 6), D(7, 8);

		private final int value1;
		private final int value2;

		private Chara(int value1, int value2) {
			this.value1 = value1;
			this.value2 = value2;
		}

		public int getValue1() {
			return value1;
		}

		public int getValue2() {
			return value2;
		}

	}

	/**
	 * random Sampling without repeat
	 * 
	 * @param items
	 *            A list with basic Data
	 * @param m
	 *            the number of desired Samples
	 * @param seed
	 *            the initial seed
	 * @return A list with obtained Samples
	 */
	public static <E> List<E> randomSample(List<E> items, int m, long seed) {

		if (m > items.size()) {
			System.err.println("m should smaller than the items.size():"
					+ items.size());
			System.exit(-1);
		}

		Random rnd = new Random(seed);
		for (int i = 0; i < m; i++) {
			int pos = i + rnd.nextInt(items.size() - i);
			E tmp = items.get(pos);
			items.set(pos, items.get(i));
			items.set(i, tmp);
		}

		return items.subList(0, m);
	}
	
	/**
	 * random Sampling without repeat
	 * 
	 * @param items
	 *            An array with basic Data
	 * @param m
	 *            the number of desired Samples
	 * @param seed
	 *            the initial seed
	 * @return A array with obtained Samples
	 */
	public static <E> E[] randomSample2(E[] items, int m, long seed){
		
		if (m > items.length) {
			System.err.println("m should smaller than the items.size():"
					+ items.length);
			System.exit(-1);
		}else if (items.length <=1) {
			System.err.println("items can't be empty"
					+ items.length);
			System.exit(-1);
		}
		
		Random rnd = new Random(seed);
		for (int i = 0; i < m; i++) {
			int pos = i + rnd.nextInt(items.length - i);
			E tmp = items[pos];
			items[pos] = items[i];
			items[i] = tmp;
		}
		
		E[] result = Arrays.copyOf(items, m);
		
		return result;
		
	}

	public static void main(String[] args) {

		// String inputFileWithPath = DataPad.data3user;

//		List<Integer> abc = new ArrayList<Integer>();
//
//		for (int i = 0; i < 20; i++) {
//			abc.add(i);
//
//		}
//
//		System.out.println(randomSample(abc, 20, 3306).toString());
		
		int length = 10;
//		int[] abc = new int[length];
		Integer[] abc = new Integer[length];
		
		for(int i = 0; i<length; i++){
			abc[i] = i;
		}
		Integer[] bbc = randomSample2(abc, length, 3306);
		
		for(Integer i : bbc){
			System.out.print(i+",");
		}
		

	}
}
