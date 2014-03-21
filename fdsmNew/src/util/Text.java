package util;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.hash.TDoubleObjectHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import info.DataSource;
import info.dataReadWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Text {

	public static void textReader(String inputTXT) {

		ArrayList<String> list = new ArrayList<String>();

		try {

			Scanner s = new Scanner(new File(inputTXT));

			while (s.hasNext()) {
				list.add(s.nextLine());
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner read = new Scanner(System.in);

		read.useDelimiter(DataSource.lineSeparate);

		String line = "0";

		while (10 > 0) {

			System.out.println("This file has " + list.size()
					+ " lines. You can give a number beween 0 and "
					+ (list.size() - 1));

			System.out
					.println("Please give the number of the lines, which you want to show, please use the \",\" to split the numbers ");

			line = read.nextLine();

			if (line.trim().equals("exit") || line.trim().equals("")) {
				break;
			}

			String[] lineInfo = line.split(",");

			for (String e : lineInfo) {

				System.out.print("line " + e + System.lineSeparator());

				System.out.println(list.get(Integer.parseInt(e)));

			}

			System.out.println();

		}

		read.close();
	}

	/**
	 * Read Text into an ArrayList<String>
	 * 
	 * @param inputTXT
	 * @return
	 */
	public static ArrayList<String> textToList(String inputTXT) {
		ArrayList<String> arrL = new ArrayList<String>();
		try {

			Scanner s = new Scanner(new File(inputTXT));

			while (s.hasNext()) {
				arrL.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return arrL;
	}

	/**
	 * Read the Information from info.txt
	 * 
	 * @param infoTXT
	 * @return
	 */
	public static HashMap<String, String> readInfoTXT(String infoTXT) {

		File file = new File(infoTXT);

		if (!file.exists()) {
			System.err.println(infoTXT + " doesn't exist");
			System.exit(-1);
		}

		HashMap<String, String> hm = new HashMap<String, String>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();

			while (line != null) {

				if (!line.startsWith("#") && !line.trim().isEmpty()) {

					String[] lineInfo = line.split("=");

					if (lineInfo.length != 2) {
						System.err.println(infoTXT + " has wrong Format!");
						System.exit(-1);
					}

					hm.put(lineInfo[0].trim(), lineInfo[1].trim());

				}

				line = br.readLine();

			}

			br.close();
		} catch (IOException e) {
			System.err.println(infoTXT + " doesn't exist!");

			System.exit(-1);

		}

		return hm;
	}

	/**
	 * take the random Sample from a
	 * 
	 * @param items
	 * @param m
	 * @param seed
	 * @return
	 */
	public static List<Integer> randomSample2(List<Integer> items, int m,
			long seed) {

		if (m > items.size()) {
			System.err.println("m should smaller than the items.size():"
					+ items.size());
			System.exit(-1);
		}

		Random rnd = new Random(seed);
		for (int i = 0; i < m; i++) {
			int pos = i + rnd.nextInt(items.size() - i);
			Integer tmp = items.get(pos);
			items.set(pos, items.get(i));
			items.set(i, tmp);
		}

		return items.subList(0, m);
	}

	public static HashMap<String, String> readLineInfos(String lineInfos) {
		HashMap<String, String> hs = new HashMap<String, String>();

		String line;

		if (lineInfos.startsWith("#")) {
			line = lineInfos.substring(1);
		} else {
			line = lineInfos;
		}

		String[] lineArr = line.split(",");

		for (int i = 0; i < lineArr.length; i++) {

			String[] parameters = lineArr[i].split("=");

			if (parameters.length != 2) {
				System.err.println("The Format of the Infomation is wrong!");
				System.exit(-1);
			}

			hs.put(parameters[0].trim(), parameters[1].trim());

		}

		return hs;
	}

	public static void writeList(List<int[]> list, String outputFile, String measure, String type, String extra,
			boolean sort) {

		if (sort == true) {
			Collections.sort(list, new ColumnComparator(-3, 1, 2));
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			int length = list.size();

			bw.write("#length = " + length +", measure = "+measure+", "+ "sort = "+sort+", "+"type = "+type+","+extra+ System.lineSeparator());

			for (int i = 0; i < length; i++) {

				int[] cooccInfo = list.get(i);

				for (int j = 0; j < cooccInfo.length; j++) {

					bw.write(cooccInfo[j] + ",");

				}

				bw.newLine();

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public static void writeListDouble(List<double[]> list, String outputFile, String measure, String extra,
			boolean sort) {

		if (sort == true) {
			Collections.sort(list, new ColumnComparatorDouble(-3, 1, 2));
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			int length = list.size();

			bw.write("#length = " + length +", measure = "+measure+", "+ "sort = "+sort+","+extra+ System.lineSeparator());

			for (int i = 0; i < length; i++) {

				double[] cooccInfo = list.get(i);
			
				bw.write((int)cooccInfo[0]+","+(int)cooccInfo[1]+","+cooccInfo[2]);

				bw.newLine();

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void writeList(int[][] list, String outputFile, boolean sort) {

		if (sort == true) {

			Arrays.sort(list, new ColumnComparator(-3, 1, 2));
		}

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			int iLength = list.length;
			int jLength = list[0].length;

			bw.write("#length = " + iLength + System.lineSeparator());

			for (int i = 0; i < iLength; i++) {

				for (int j = 0; j < jLength; j++) {

					bw.write(list[i][j] + ",");

				}

				bw.newLine();

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void writeLocalList(int[][] list, String outputFile) {

		TIntObjectHashMap<ArrayList<int[]>> hm = new TIntObjectHashMap<ArrayList<int[]>>();

		for (int i = 0; i < list.length; i++) {

			if (hm.containsKey(list[i][0])) {
				hm.get(list[i][0]).add(new int[] { list[i][1], list[i][2] });
			} else {
				hm.put(list[i][0], new ArrayList<int[]>());
				hm.get(list[i][0]).add(new int[] { list[i][1], list[i][2] });
			}

			if (hm.containsKey(list[i][1])) {
				hm.get(list[i][1]).add(new int[] { list[i][0], list[i][2] });
			} else {
				hm.put(list[i][1], new ArrayList<int[]>());
				hm.get(list[i][1]).add(new int[] { list[i][0], list[i][2] });
			}

		}

		int[] primaryIds = hm.keys();

		Arrays.sort(primaryIds);

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			for (int i = 0; i < primaryIds.length; i++) {

				Collections.sort(hm.get(primaryIds[i]), new ColumnComparator(
						-2, 1));

				ArrayList<int[]> friends = hm.get(primaryIds[i]);

				bw.write("#primaryId = " + primaryIds[i] + ","
						+ " numberOfFriends = " + friends.size()
						+ System.lineSeparator());

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void writeLocalList(ArrayList<int[]> list, String outputFile, String measure, String type, String extra) {

		TIntObjectHashMap<ArrayList<int[]>> hm = new TIntObjectHashMap<ArrayList<int[]>>();

		int length = list.size();

		for (int i = 0; i < length; i++) {

			if (hm.containsKey(list.get(i)[0])) {
				hm.get(list.get(i)[0]).add(
						new int[] { list.get(i)[1], list.get(i)[2] });
			} else {
				hm.put(list.get(i)[0], new ArrayList<int[]>());
				hm.get(list.get(i)[0]).add(
						new int[] { list.get(i)[1], list.get(i)[2] });
			}

			if (hm.containsKey(list.get(i)[1])) {
				hm.get(list.get(i)[1]).add(
						new int[] { list.get(i)[0], list.get(i)[2] });
			} else {
				hm.put(list.get(i)[1], new ArrayList<int[]>());
				hm.get(list.get(i)[1]).add(
						new int[] { list.get(i)[0], list.get(i)[2] });
			}

		}

		int[] primaryIds = hm.keys();

		Arrays.sort(primaryIds);

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
			
			bw.write("measure = "+measure+", "+"type = "+type+","+extra+System.lineSeparator());

			for (int i = 0; i < primaryIds.length; i++) {

				Collections.sort(hm.get(primaryIds[i]), new ColumnComparator(
						-2, 1));

				ArrayList<int[]> friends = hm.get(primaryIds[i]);

				bw.write("#primaryId = " + primaryIds[i] + ","
						+ " numberOfFriends = " + friends.size()
						+ System.lineSeparator());
				
				for(int[] friend : friends){
					bw.write(friend[0]+","+friend[1]+System.lineSeparator());
					
					
				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public static void writeLocalListDouble(ArrayList<double[]> list, String outputFile) {

		TDoubleObjectHashMap<ArrayList<double[]>> hm = new TDoubleObjectHashMap<ArrayList<double[]>>();

		int length = list.size();
		
		System.out.println("leng = "+list.size());
		
		//create the hashmaps for local list.
		System.out.println("create the hashmaps for local list.");

		for (int i = 0; i < length; i++) {
			
			System.out.println(i+"," +list.get(i)[1]+","+list.get(i)[2]);

			if (hm.containsKey(list.get(i)[0])) {
				hm.get(list.get(i)[0]).add(
						new double[] { list.get(i)[1], list.get(i)[2] });
			} else {
				hm.put(list.get(i)[0], new ArrayList<double[]>());
				hm.get(list.get(i)[0]).add(
						new double[] { list.get(i)[1], list.get(i)[2] });
			}

			if (hm.containsKey(list.get(i)[1])) {
				hm.get(list.get(i)[1]).add(
						new double[] { list.get(i)[0], list.get(i)[2] });
			} else {
				hm.put(list.get(i)[1], new ArrayList<double[]>());
				hm.get(list.get(i)[1]).add(
						new double[] { list.get(i)[0], list.get(i)[2] });
			}

		}
		

		double[] primaryIds = hm.keys();
		
		//sort the primary keys of the hashmap of local list
		
		System.out.println("sort the primary keys of the hashmap of local list");

		Arrays.sort(primaryIds);
		
		//begin write the local list file.
		
		System.out.println("begin write the local list file.");

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			for (int i = 0; i < primaryIds.length; i++) {

				Collections.sort(hm.get(primaryIds[i]), new ColumnComparatorDouble(
						-2, 1));

				ArrayList<double[]> friends = hm.get(primaryIds[i]);

				bw.write("#primaryId = " + (int)primaryIds[i] + ","
						+ " numberOfFriends = " + friends.size()
						+ System.lineSeparator());
				
				for(double[] friend : friends){
					bw.write((int)friend[0]+","+friend[1]+System.lineSeparator());
					
					
				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void serObjectWrite_wrap(String outPutFile, Object obj) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(outPutFile));
			os.writeObject(obj);
			os.close();
			os = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Object serObjectRead_wrap(String serFile) {

		Object ob = new Object();

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					serFile));
			ob = is.readObject();
			is.close();
			is = null;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ob;

	}

	public static int[][] readMeasureFromTXT(String inputTXT) {

		int[][] measure = null;

		File file = new File(inputTXT);

		if (!file.exists()) {

			System.err.println("File " + inputTXT + " does not exist!");
			System.exit(-1);
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			
			HashMap<String, String> hm = new HashMap<String, String>();
			
			
			
			
			
			
			
			

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return measure;
	}

	public static void test() {


	}

	public static void main(String[] args) {

		// HashMap<String, String> hs = new HashMap<String, String>();
		//
		// hs.put("huhu", "haha");
		//
		// String line = "#numberOfSamples = 20000, from = 1, to = 20000";
		//
		// hs = readLineInfos(hs, line);
		//
		// Iterator<Entry<String, String>> it = hs.entrySet().iterator();
		//
		// while(it.hasNext()){
		// Entry<String, String> entry = it.next();
		// System.out.println("hs.size = "+hs.size());
		//
		// System.out.println(entry.getKey()+","+entry.getValue());
		// }

		// textReader(dataReadWrite.selectedEntriesSecondaryIdTXT);

		// textReader(dataReadWrite.selectedEntriesSecondaryId_Model_1TXT);


	}

}
