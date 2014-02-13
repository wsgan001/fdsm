package info;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntIntHashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

import javolution.util.FastBitSet;
import javolution.util.Index;

public class dataReadWrite {

	static String outputPath = DataSource.outputPath;
	static String primaryIndexTXT = outputPath + "PrimaryIndex.txt";
	static String secondaryIndexTXT = outputPath + "SecondaryIndex.txt";
	static String toker = ",";
	static String selectedEntriesSceondaryIdTXT = DataSource.outputPath
			+ "selectedEntriesSecondaryId.txt";
	static String infoTXT = outputPath + "info.txt";

	/**
	 * generate the computer indexes for the data, the outputs are two computer
	 * indexes for the primary column and secondary column.
	 * 
	 * @param inputData
	 * @param primaryColumn
	 *            native number of the goal Column
	 * @param secondaryColumn
	 *            native number of the Column
	 */
	public static void dataIndex(String inputData, int primaryColumn,
			int secondaryColumn) {

		File outputPath = new File(info.DataSource.outputPath);

		File file = new File(inputData);

		if (!file.exists()) {

			System.err.println("Sorry, I can't find the input File! :-/ ");
			System.exit(-1);

		}

		if (!outputPath.exists()) {
			outputPath.mkdir();
		}

		FastBitSet fbsPrimaryList = new FastBitSet();
		FastBitSet fbsSecondaryList = new FastBitSet();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();

			StringTokenizer st = new StringTokenizer(toker);

			while (line != null) {

				String[] lineInfo = line.split(toker);

				fbsPrimaryList.set(Integer
						.parseInt(lineInfo[primaryColumn - 1]));
				fbsSecondaryList.set(Integer
						.parseInt(lineInfo[secondaryColumn - 1]));

				line = br.readLine();

			}

			br.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		Index[] indPrimary = fbsPrimaryList.toArray(new Index[0]);
		Index[] indSecondary = fbsSecondaryList.toArray(new Index[0]);

		int cnt = 0;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					primaryIndexTXT));

			for (Index ind : indPrimary) {

				bw.write(cnt + "," + ind + DataSource.lineSeparate);

				cnt++;
			}

			bw.close();

			cnt = 0;

			bw = new BufferedWriter(new FileWriter(secondaryIndexTXT));

			for (Index ind : indSecondary) {

				bw.write(cnt + "," + ind + DataSource.lineSeparate);

				cnt++;

			}

			bw.close();

			bw = new BufferedWriter(new FileWriter(infoTXT));

			bw.write("#Number of PrimaryIds:" + System.lineSeparator());
			bw.write("numberOfPrimaryIds=" + indPrimary.length
					+ System.lineSeparator());
			bw.write("#Number Of SecondaryIds:" + System.lineSeparator());
			bw.write("numberOfSecondaryIds=" + indSecondary.length
					+ System.lineSeparator());

			bw.close();
		} catch (IOException e) {
			System.exit(-1);

		}

	}

	/**
	 * output the data which satisfy the criterion. The output file names
	 * "selectedEntriesSecondaryId.txt".
	 * 
	 * selectedEntiresSecondaryId.txt Formatx: Computer_secondaryId:sum of the
	 * Computer_primaryIds:Computer_PrimaryId,...,
	 */
	public static void selectEntries() {

		File file = new File(DataSource.data3user);

		TIntIntHashMap primaryIdList_HM = readIndexToHM(primaryIndexTXT, 2, 1,
				",");

		TIntIntHashMap secondaryIdList_HM = readIndexToHM(secondaryIndexTXT, 2,
				1, ",");

		TIntArrayList[] selectedEntries = new TIntArrayList[secondaryIdList_HM
				.size()];

		for (int i = 0; i < selectedEntries.length; i++) {

			selectedEntries[i] = new TIntArrayList();

		}

		System.out.println("selectedEntires[] fertig and begin read...");

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();

			int cnt = 0;

			while (line != null) {

				String[] lineInfo = line.split(toker);

				int rating = Integer
						.parseInt(lineInfo[DataSource.ratingColumn - 1]);

				if (rating <= DataSource.ratingCriterion) {

					line = br.readLine();

					continue;

				}

				if (cnt % 100000 == 0) {
					System.out.println("reading line" + cnt + ": " + line);
				}

				int secondaryId = secondaryIdList_HM.get(Integer
						.parseInt(lineInfo[DataSource.secondaryColumn - 1]));

				int primaryId = primaryIdList_HM.get(Integer
						.parseInt(lineInfo[DataSource.primaryColumn - 1]));

				selectedEntries[secondaryId].add(primaryId);

				line = br.readLine();

				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		try {

			System.out.println("begin to write...");
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					DataSource.outputPath + "selectedEntriesSecondaryId.txt"));

			for (int i = 0; i < selectedEntries.length; i++) {

				if (i % 10000 == 0) {
					System.out.println(i);
				}

				selectedEntries[i].sort();

				bw.write(i + ":" + selectedEntries[i].size() + ":");

				for (int j = 0; j < selectedEntries[i].size(); j++) {

					bw.write(selectedEntries[i].get(j) + ",");

				}

				bw.newLine();

			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 * read the index files into a HashMap, key is realId, value is the
	 * computerId
	 * 
	 * @param indexTXT
	 *            PrimaryIndex.txt and SecondaryIndex.txt
	 * @param keyColumn
	 *            default is 2
	 * @param valueComlumn
	 *            default is 1
	 * @param toker
	 *            default ","
	 * @return
	 */
	public static TIntIntHashMap readIndexToHM(String indexTXT, int keyColumn,
			int valueComlumn, String toker) {
		TIntIntHashMap hm = new TIntIntHashMap();

		File file = new File(indexTXT);

		if (!file.exists()) {
			System.err.println("File " + indexTXT + "doesn't existiert! ");
			System.exit(-1);
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(
					indexTXT)));

			String line = br.readLine();

			while (line != null) {

				String[] entrys = line.split(toker);

				hm.put(Integer.parseInt(entrys[keyColumn - 1]),
						Integer.parseInt(entrys[valueComlumn - 1]));

				line = br.readLine();

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hm;
	}

	/**
	 * Choose the Samples with select Model = 1
	 * 
	 * @param from
	 *            ComputerSecondaryId+1
	 * @param to
	 *            ComputerSecondaryId+1
	 */
	public static void selectedEntries_Model_2(int from, int to) {

		File file = new File(selectedEntriesSceondaryIdTXT);

	}

	public static HashMap<String, String> readInfoTXT() {

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
//					System.out.println("a");
//					System.out.println("line = " + line);

					String[] lineInfo = line.split("=");

					hm.put(lineInfo[0].trim(), lineInfo[1].trim());

//					System.out.println(line);
//					System.out.println(lineInfo[0] + "," + lineInfo[1]);

				}

				// System.out.println(line);

				line = br.readLine();

			}

			br.close();
		} catch (IOException e) {
			System.err.println(infoTXT + " doesn't exist!");

			System.exit(-1);

		}

		return hm;
	}

	public static void test() {

		HashMap<String, String> hm = readInfoTXT();

		System.out.println(hm.size());

	}

	public static void main(String[] args) {

		test();

	}

}
