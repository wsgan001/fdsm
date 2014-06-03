package old;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import algo.BipartiteGraph;
import algo.CooccFkt;
import util.ColumnComparator;
import util.MyBitSet;
import util.Text;

public class Zscore {

	public static int numberOfSampleGraphs = 10;

	public static String inputDatabase = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	// public static String outputPath = info.dataReadWrite.outputPath +
	// "Zscore"
	// + System.lineSeparator();
	public static String outputPath = info.dataReadWrite.outputPath + "Zscore/";

	// public static String OriginDataPath = "C:/ying/netflixDaten/";
	// public static String outputPath = OriginDataPath + "Zscore/";
	//
	// public static String inputDatabase = OriginDataPath
	// + "selectedEntriesSecondaryId_Model_1.txt";

	// OutputFiles
	public static String pValueTXT = outputPath + "pValue.txt";// topright
	public static String sumCooccTXT = outputPath + "sumCoocc.txt";// lowerleft
	public static String CooccTXT = outputPath + "Coocc.txt";// topright
	public static String sumVarianceTXT = outputPath + "sumVariance.txt";// topright
	public static String CooccFDSMTXT = outputPath + "CooccFDSM.txt";// topright
	public static String standardDeviationTXT = outputPath
			+ "standardDeviation.txt";// lowerleft
	public static String zScoreTXT = outputPath + " zScore.txt";

	public static int seed = 3306;

	public static void creatPath(String outputPath) {
		File file = new File(outputPath);
		boolean createPath = file.mkdirs();

		System.out.println(createPath);

	}

	
	/**
	 * Write the Co-occurence, CoocurenceFDSM, sum of the Co-occurence of the sample graphs
	 */
	public static void run1() {

		creatPath(outputPath);

		BipartiteGraph bG = new BipartiteGraph(inputDatabase);

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		// Matrix coocc records the coocc from the original graphs and the whole
		// random sample graphs.

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		System.out.println("read original cooccurence");

		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);

		System.out.println("generate edges");

		Random generator_edges = new Random(seed);

		System.out.println("The first long swap walks...");

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edges);

		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		// Matrix pValue is used to records the pValues and every coocurence.
		short[][] pValue = new short[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			long t1 = System.currentTimeMillis();

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edges);

			CooccFkt.readCooccSecAddLowerLeft(adjM, pValue);

			CooccAndPValueCal(coocc, pValue);

			long t2 = System.currentTimeMillis();

			double t3 = (double) (t2 - t1) / (double) 1000;

			System.out.println("Round " + i + " takes " + t3 + " seconds");

		}

		write2ListFromMatrix(coocc, CooccTXT, sumCooccTXT, CooccFDSMTXT);

		writeTopRightMatrix(pValue, pValueTXT);

	}

	public static void write2ListFromMatrix(int[][] coocc, String TopRightFile,
			String LowerLeftFile, String levFile) {

		//write the top right Matrix, it is co-occurence
		try {
			int length = coocc.length;

			// Write the values in top right Matrix
			System.out.println("Write File: " + TopRightFile);
			BufferedWriter bw = new BufferedWriter(new FileWriter(TopRightFile));
			for (int i = 0; i < length; i++) {

				for (int j = i + 1; j < length; j++) {

					if (coocc[i][j] > 0) {

						bw.write(i + "," + j + "," + coocc[i][j]
								+ System.lineSeparator());
					}

				}

			}

			bw.close();

			// Write the values in lower left Matrix

			System.out.println("Write File: " + LowerLeftFile);

			bw = new BufferedWriter(new FileWriter(LowerLeftFile));

			for (int i = 0; i < length; i++) {

				for (int j = 0; j < i; j++) {

					if (coocc[i][j] > 0) {
						bw.write(i + "," + j + "," + coocc[i][j]
								+ System.lineSeparator());

					}

				}

			}

			bw.close();

			// Write the difference between top right Matrix and lower left
			// Matrix, the result at this place is the leverage FDSM

			System.out.println("Write File: " + levFile);

			bw = new BufferedWriter(new FileWriter(levFile));

			for (int i = 0; i < length; i++) {
				for (int j = i + 1; j < length; j++) {
					int cooccFDSM = coocc[i][j] * numberOfSampleGraphs
							- coocc[j][i];
					if (cooccFDSM > 0) {
						bw.write(i + "," + j + "," + cooccFDSM
								+ System.lineSeparator());

					}

				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void writeTopRightMatrix(short[][] pValue, String outputFile) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
			int length = pValue.length;
			for (int i = 0; i < length; i++) {
				for (int j = i + 1; j < length; j++) {

					if (pValue[i][j] > 0) {
						bw.write(i + "," + j + "," + pValue[i][j]
								+ System.lineSeparator());
					}

				}

			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void CooccAndPValueCal(int[][] coocc, short[][] pValue) {
		int length = coocc.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {

				if (coocc[i][j] > pValue[j][i]) {

					pValue[i][j]++;

				}

				coocc[j][i] = coocc[j][i] + pValue[j][i];

				pValue[j][i] = 0;

			}

		}

	}

	/**
	 * Calculate the variance, write down the sum[(X-E(X))^2], and the standard
	 * deviation.
	 */
	public static void run2() {
		BipartiteGraph bg = new BipartiteGraph(inputDatabase);
		double[][] cooccDouble = new double[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];

		// Read the E(X)
		System.out.println("begin to read the E(X)");
		readEX(cooccDouble, sumCooccTXT);

		int length = bg.numberOfPrimaryIds;
		int[][] edges = bg.generateEdges();
		Random generator_edges = new Random(seed);

		MyBitSet[] adjM = bg.toSecBS();
		CooccFkt.swap(4 * bg.numberOfSamples, edges, adjM, generator_edges);

		int lengthOfWalks = (int) (bg.numberOfSamples * Math
				.log(bg.numberOfSamples));

		// Matrix pValue is used to records the pValues and every coocurence.
		short[][] pValue = new short[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];

		// calculate the sum [(X-E(X))^2]
		for (int round = 0; round < numberOfSampleGraphs; round++) {

			long t1 = System.currentTimeMillis();
			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edges);
			CooccFkt.readCooccSecAddLowerLeft(adjM, pValue);

			for (int i = 0; i < length; i++) {
				for (int j = 0; j < i; j++) {

					double value = (double) pValue[i][j] - cooccDouble[i][j];
					value = value * value;

					cooccDouble[j][i] += value;

					pValue[i][j] = 0;
				}

			}

			long t2 = System.currentTimeMillis();

			long t3 = t2 - t1;

			System.out.println("Round " + round + " takes " + (t3 / 1000)
					+ " seconds!");

		}

		// calculate the deviation
		System.out.println("begin to calculate the standard deviation");
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {

				cooccDouble[j][i] = util.General.doubleRound(
						Math.sqrt(cooccDouble[i][j]
								/ (double) numberOfSampleGraphs), 4);

			}

		}

		// Write the difference
		System.out
				.println("begin to write the difference from top right Matrix");

		writeTopRightMatrix(cooccDouble, sumVarianceTXT);

		System.out
				.print("begin to write standard deviation from the lower left Matrix");

		writeLowerLeftMatrix(cooccDouble, standardDeviationTXT);

	}

	public static void writeTopRightMatrix(double[][] cooccDouble,
			String outputFile) {
		int length = cooccDouble.length;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			for (int i = 0; i < length; i++) {
				for (int j = i + 1; j < length; j++) {

					if (cooccDouble[i][j] > 0) {

						bw.write(i + "," + j + "," + cooccDouble[i][j]
								+ System.lineSeparator());

					}

				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void writeLowerLeftMatrix(double[][] cooccDouble,
			String outputFile) {
		int length = cooccDouble.length;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			for (int i = 0; i < length; i++) {
				for (int j = 0; j < i; j++) {

					if (cooccDouble[i][j] > 0) {

						bw.write(i + "," + j + "," + cooccDouble[i][j]
								+ System.lineSeparator());

					}

				}

			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void readEX(double[][] cooccDouble, String sumCooccTXT) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(sumCooccTXT));
			String line = br.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				double value = (double) Integer.parseInt(st.nextToken())
						/ (double) numberOfSampleGraphs;
				cooccDouble[p1][p2] = value;

				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 * Write the zscore
	 */
	public static void run3() {

	}

	/**
	 * read the CooccFDSM.txt into a double[][] Matrix, which will be placed in
	 * top right matrix.
	 * 
	 * @param coocc
	 */
	public static void readCooccFDSMTXT(double[][] coocc) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(CooccFDSMTXT));

			String line = br.readLine();

			while (line != null) {

				StringTokenizer st = new StringTokenizer(line, ",");

				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				coocc[p1][p2] = value;

				line = br.readLine();

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void readStandardDeviationTXT(double[][] coocc) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					standardDeviationTXT));

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void testValue() {
		try {

			BufferedReader br = new BufferedReader(new FileReader(pValueTXT));

			String line = br.readLine();

			int[] abc = new int[11];

			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				st.nextToken();
				st.nextToken();

				int value = Integer.parseInt(st.nextToken());

				abc[value]++;

				line = br.readLine();

			}

			br.close();

			for (int i = 0; i < 11; i++) {
				System.out.println(i + " , " + abc[i]);

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void testCardi() {
		BipartiteGraph bg = new BipartiteGraph();

		MyBitSet[] adjMPrim = bg.toPrimBS();

		int[][] cardi = new int[adjMPrim.length][2];
		for (int i = 0; i < adjMPrim.length; i++) {
			cardi[i][0] = i;
			cardi[i][1] = adjMPrim[i].cardinality();

		}

		Arrays.sort(cardi, 0, cardi.length, new ColumnComparator(-2));
		;

		for (int i = 0; i < 10; i++) {
			System.out.println(cardi[i][0] + "," + cardi[i][1]);

		}

	}

	public static void findVar0() {
		BipartiteGraph bg = new BipartiteGraph(inputDatabase);
		double[][] cooccDouble = new double[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];

		String var0TXT = outputPath + "Var0.txt";
		String var0CooccFDSMTXT = outputPath + "Var0CooccFDSM.txt";
		String var0CooccFDSM0TXT = outputPath + "Var0CooccFDSM0.txt";

		try {

			// read CooccFDSM topright Matrix
			BufferedReader br = new BufferedReader(new FileReader(CooccFDSMTXT));
			String line = br.readLine();

			while (line != null) {

				StringTokenizer st = new StringTokenizer(line, ",");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				cooccDouble[p1][p2] = value;

				line = br.readLine();
			}

			br.close();

			// read StandardDevation

			br = new BufferedReader(new FileReader(standardDeviationTXT));
			line = br.readLine();

			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");

				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				double value = Double.parseDouble(st.nextToken());

				cooccDouble[p1][p2] = value;

				line = br.readLine();
			}

			br.close();

			// write all the var <= 0

			BufferedWriter bw = new BufferedWriter(new FileWriter(
					var0CooccFDSMTXT));
			int length = bg.numberOfPrimaryIds;
			int cnt = 0;// count the number of var0CooccFDSM
			for (int i = 0; i < length; i++) {
				for (int j = i + 1; j < length; j++) {
					if (cooccDouble[i][j] > 0 && cooccDouble[j][i] <= 0) {
						bw.write(i + "," + j + "," + cooccDouble[i][j] + ","
								+ cooccDouble[j][i] + System.lineSeparator());
						cnt++;
					}
				}
			}
			System.out.println("the number of var = 0 and cooccFDSM > 0 is "
					+ cnt);

			bw.close();

			// write all the

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);

		}

	}

	public static void test(int breaknumber) {

		// creatPath(outputPath);

		BipartiteGraph bG = new BipartiteGraph(inputDatabase);

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		// Matrix coocc records the coocc from the original graphs and the whole
		// random sample graphs.

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		System.out.println("read original cooccurence");

		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

//		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);

		int cnt = 1;

		out:
		for (int i = 0; i < coocc.length; i++) {

			for (int j = i + 1; j < coocc.length; j++) {

				if (coocc[i][j] > 0) {

					System.out.println(i + "," + j + "," + coocc[i][j]);
					
					cnt++;
					
					if(cnt>breaknumber){
						break out;
					}

				}

			}

		}

	}
	


	public static void main(String[] args) {
		// Text.textReader2(inputDatabase, 0, 10);

		 run1();
		//
		// Text.textReader2(sumVarianceTXT, 0, 20);
		// System.err.println();
		// System.err.println("huhu......");
		// Text.textReader2(standardDeviation, 0, 20);

		 Text.textReader2(CooccTXT, 0, 11);
		 System.out.println();
		 Text.textReader2(sumCooccTXT, 0, 11);
		 System.out.println();
		
		 Text.textReader2(CooccFDSMTXT, 0, 11);
		 System.out.println();
		 
		
		
		
//		test(10);
		// run2();

	}

}
