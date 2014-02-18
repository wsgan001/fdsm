package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Text {

	public static void textReader(String inputTXT) {

		ArrayList<String> list = new ArrayList<String>();

		try {

			Scanner s = new Scanner(new File(inputTXT));

			while (s.hasNext()) {
				list.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner read = new Scanner(System.in);

		String line = "1";

		while (10 > 0) {

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

			read.close();

		}

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
	



	public static void main(String[] args) {

		// textReader(info.dataReadWrite.selectedEntriesSecondaryIdTXT);
		
		List<Integer> items = new ArrayList<Integer>();
		
		for(int i= 0; i<20; i++){
			items.add(i);
		}
		
		items = randomSample2(items, 20, 3306);
		
		System.out.println(items.toString());
		

	}

}
