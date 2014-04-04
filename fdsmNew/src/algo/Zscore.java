package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

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
	public static String pValueTXT = outputPath + "pValue.txt";
	public static String sumCooccTXT = outputPath + "sumCoocc.txt";
	public static String Coocc5000TXT = outputPath + "Coocc5000.txt";
	public static String CooccFDSM = outputPath + "CooccFDSM.txt";

	public static int seed = 3306;

	public static void creatPath(String outputPath) {
		File file = new File(outputPath);
		boolean createPath = file.mkdirs();

		System.out.println(createPath);

	}

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

		CooccFkt.swap(4 * bG.numberOfSamples, edges, adjM, generator_edges);

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

		write2ListFromMatrix(coocc, Coocc5000TXT, sumCooccTXT, CooccFDSM);

		writeTopRightMatrix(pValue, pValueTXT);

	}

	public static void write2ListFromMatrix(int[][] coocc, String TopRightFile,
			String LowerLeftFile, String levFile) {

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

					if (coocc[i][j] - coocc[j][i] > 0) {
						bw.write(i + "," + j + "," + coocc[i][j]
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
	
	public static void testValue(){
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(pValueTXT));
			
			String line = br.readLine();
			
			int[] abc = new int[11];
			
			
			while(line != null){
				StringTokenizer st = new StringTokenizer(line, ",");
				st.nextToken();
				st.nextToken();
				
				int value = Integer.parseInt(st.nextToken());
				
				abc[value]++;
				
				line = br.readLine();
				
			}
			
			br.close();
			
			for(int i = 0; i<11; i++){
				System.out.println(i+" , "+abc[i]);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}

	public static void main(String[] args) {
		// Text.textReader2(inputDatabase, 0, 10);

//		run1();

//		Text.textReader2(pValueTXT, 10000, 10010);
		
		testValue();
	}

}
