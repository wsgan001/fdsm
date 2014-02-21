package algo;

import gnu.trove.set.hash.TIntHashSet;
import info.DataSource;
import info.dataReadWrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

import util.MyBitSet;
import util.MyFastBitSet;

public class BipartiteGraph {

	public static MyBitSet[] bipartiteGraphSecBS(String inputTXT) {

		File file = new File(inputTXT);

		MyBitSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			HashMap<String, String> hs = new HashMap<String, String>();
			hs = util.Text.readLineInfos(hs, line);
			int numberOfSamples = Integer.parseInt(hs.get("numberOfSamples"));

			adjM = new MyBitSet[numberOfSamples];

			br.readLine();

			line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new MyBitSet();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].set(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public static MyFastBitSet[] bipartiteGraphSecFBS(String inputTXT) {

		File file = new File(inputTXT);

		MyFastBitSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			HashMap<String, String> hs = new HashMap<String, String>();
			hs = util.Text.readLineInfos(hs, line);
			int numberOfSamples = Integer.parseInt(hs.get("numberOfSamples"));

			adjM = new MyFastBitSet[numberOfSamples];

			br.readLine();

			line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new MyFastBitSet();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].set(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public static TreeSet<Integer>[] bipartiteGraphSecTS(String inputTXT) {
		File file = new File(inputTXT);

		TreeSet<Integer>[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			HashMap<String, String> hs = new HashMap<String, String>();
			hs = util.Text.readLineInfos(hs, line);
			int numberOfSamples = Integer.parseInt(hs.get("numberOfSamples"));

			adjM = (TreeSet<Integer>[]) Array.newInstance(TreeSet.class,
					numberOfSamples);

			br.readLine();

			line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new TreeSet<Integer>();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].add(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public static TIntHashSet[] bipartiteGraphSecTHS(String inputTXT) {

		File file = new File(inputTXT);

		TIntHashSet[] adjM = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			HashMap<String, String> hs = new HashMap<String, String>();
			hs = util.Text.readLineInfos(hs, line);
			int numberOfSamples = Integer.parseInt(hs.get("numberOfSamples"));

			adjM = new TIntHashSet[numberOfSamples];

			br.readLine();

			line = br.readLine();

			int cnt = 0;
			while (line != null) {
				String[] lineInfos = line.split(":");

				int cardinality = Integer.parseInt(lineInfos[2]);

				adjM[cnt] = new TIntHashSet();

				if (cardinality != 0) {

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[cnt].add(Integer.parseInt(primaryIds[i]));

					}
				}

				line = br.readLine();
				cnt++;
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public static MyBitSet[] bipartiteGraphPrimBS(String inputTXT) {

		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		MyBitSet[] adjM = new MyBitSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new MyBitSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].set(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}

	public static MyFastBitSet[] bipartiteGraphPrimFBS(String inputTXT) {

		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		MyFastBitSet[] adjM = new MyFastBitSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new MyFastBitSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].set(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;
	}

	public static TreeSet<Integer>[] bipartiteGraphPrimTS(String inputTXT) {

		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		// MyBitSet[] adjM = new MyBitSet[numberOfPrimaryIds];

		TreeSet<Integer>[] adjM = (TreeSet<Integer>[]) Array.newInstance(
				TreeSet.class, numberOfPrimaryIds);

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new TreeSet<Integer>();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].add(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;

	}
	
	public static TIntHashSet[] bipartiteGraphPrimTHS(String inputTXT){
		HashMap<String, String> infos = util.Text
				.readInfoTXT(dataReadWrite.infoTXT);

		File file = new File(inputTXT);

		int numberOfPrimaryIds = Integer.parseInt(infos
				.get("numberOfPrimaryIds"));

		TIntHashSet[] adjM = new TIntHashSet[numberOfPrimaryIds];

		for (int i = 0; i < numberOfPrimaryIds; i++) {

			adjM[i] = new TIntHashSet();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();

			br.readLine();

			String line = br.readLine();

			while (line != null) {
				String[] lineInfos = line.split(":");
				int cardinality = Integer.parseInt(lineInfos[2]);

				if (cardinality != 0) {

					int secondaryId = Integer.parseInt(lineInfos[0]);

					String[] primaryIds = lineInfos[3].split(",");
					for (int i = 0; i < cardinality; i++) {

						adjM[Integer.parseInt(primaryIds[i])].add(secondaryId);

					}
				}

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return adjM;
		
	}

	public static void main(String[] args) {

		// long t1 = System.currentTimeMillis();
		// MyBitSet[] bipGraph =
		// bipartiteGraphSecBS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// long t2 = System.currentTimeMillis();
		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// t1 = System.currentTimeMillis();
		// MyFastBitSet[] bipGraph1 =
		// bipartiteGraphSecFBS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// t2 = System.currentTimeMillis();
		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// t1 = System.currentTimeMillis();
		// TreeSet<Integer>[] bipGraph2 =
		// bipartiteGraphSecTS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// t2 = System.currentTimeMillis();
		//
		// System.out.println(bipGraph2[1].toString());

		// long t1 = System.currentTimeMillis();
		// TIntHashSet[] bipGraph4 =
		// bipartiteGraphSecTHS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		// long t2 = System.currentTimeMillis();
		// System.out.println("Time = " + (t2 - t1));
		//
		// System.out.println(bipGraph4[1].toString());

		long t1 = System.currentTimeMillis();
		TIntHashSet[] bipGraph5 = bipartiteGraphPrimTHS(info.dataReadWrite.selectedEntriesSecondaryId_Model_2TXT);
		long t2 = System.currentTimeMillis();
		System.out.println("Time = " + (t2 - t1));
		System.out.println(bipGraph5.length);
		int number = 1;
		System.out.println("cardinarity = " + bipGraph5[number].size() + " : "
				+ bipGraph5[number].toString());

		//
		// System.out.println("Time = " + (t2 - t1));
		//
		// int lineNumber = 1;
		// System.out.println("line = " + lineNumber + ": " + "Cardinality = "
		// + bipGraph2[lineNumber].size());
		// System.out.println(bipGraph[lineNumber]);

	}

}
