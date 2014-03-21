package algo;

import java.io.File;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;

import util.ColumnComparatorDouble;
import util.MyBitSet;
import util.Text;

public class Jaccard {

	static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	static String outputPath = info.DataSource.outputPath + File.separator
			+ "Jaccard" + File.separator;

	// output file name for the levFDSM result:global list
	public static String Jaccard_TXT = outputPath + "Jaccard.txt";
	public static String Jaccard_GL_TXT = outputPath + "Jaccard_GL.txt";
	public static String Jaccard_LL_TXT = outputPath + "Jaccard_LL.txt";

	public static int seed = 3306;

	public static int[] readDegree(MyBitSet[] adjMPrimary) {
		int[] degrees = new int[adjMPrimary.length];
		int length = degrees.length;
		for (int i = 0; i < length; i++) {
			degrees[i] = adjMPrimary[i].cardinality();
		}

		return degrees;
	}

	public static ArrayList<double[]> calculate() {

		BipartiteGraph bg = new BipartiteGraph();

		ArrayList<double[]> measures = new ArrayList<double[]>();

		int[][] coocc = new int[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];

		int length = bg.numberOfPrimaryIds;

		MyBitSet[] adjMPrimary = bg.toPrimBS();

		int[] degrees = readDegree(adjMPrimary);

		CooccFkt.readCooccPrimaryAddTopRight(adjMPrimary, coocc);
		
		System.out.println(degrees[41]+", "+degrees[334]+", "+coocc[41][334]);

		for (int i = 0; i < length; i++) {

			for (int j = i + 1; j < length; j++) {

				if (coocc[i][j] > 0) {

					double measureValue = Math
							.round((double) coocc[i][j]
									* 10000.0
									/ ((double) degrees[i]
											+ (double) degrees[j] - (double) coocc[i][j])) / 10000.0;
					// double measureValue2 = Math.round((double)coocc[i][j] *
					// 1000
					// / ((double)degrees[i] + (double)degrees[j] -
					// (double)coocc[i][j]));

					if (measureValue > 0) {
						measures.add(new double[] { i, j, measureValue });
					}

					// System.out.println("coocc[" + i + "][" + j + "] = "
					// + coocc[i][j] + "," + "degree[" + i + "] = "
					// + degrees[i] + ", degree[" + j + "] = "
					// + degrees[j] + ", measureValue = " + measureValue);

				}

			}

		}

		return measures;
	}

	public static void run() {

		File file = new File(outputPath);

		if (!file.exists()) {
			file.mkdirs();
		}

		ArrayList<double[]> measures = calculate();
		System.out.println("measures without sort");
		for (int i = 0; i < 10; i++) {
			System.out.println(measures.get(i)[0] + ", " + measures.get(i)[1]
					+ ", " + measures.get(i)[2]);
		}

		Collections.sort(measures, new ColumnComparatorDouble(-3, 1, 2));

		System.out.println();
		System.out.println("measures without sort");
		for (int i = 0; i < 10; i++) {
			System.out.println(measures.get(i)[0] + ", " + measures.get(i)[1]
					+ ", " + measures.get(i)[2]);
		}

		System.out.println("Write the Jaccard List");
		Text.writeListDouble(measures, Jaccard_TXT, "Jaccard", "", false);

		System.out.println("Write the Jaccard Gloable List");
		Text.writeListDouble(measures, Jaccard_GL_TXT, "Jaccard", "", true);

		// System.out.println("Write the Jaccard Local List");
		// Text.writeLocalListDouble(measures, Jaccard_LL_TXT);

	}

	public static void test() {

		int a = 1;
		int b = 19;
		int c = 58;
		double e = a / (b + c - a);
		double f = Math.round((double) a * 10000
				/ ((double) b + (double) c - (double) a)) / 10000.0;
		System.out.println(e);
		System.out.println(f);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 run();
		// test();

	}

}
