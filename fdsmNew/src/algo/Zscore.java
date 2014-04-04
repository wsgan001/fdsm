package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.MyBitSet;
import util.Text;

public class Zscore {

	public static int numberOfSampleGraphs = 5000;
	// public static String inputDatabase =
	// info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	// public static String outputPath = info.dataReadWrite.outputPath +
	// "Zscore"
	// + System.lineSeparator();
	public static String OriginDataPath = "C:/ying/netflixDaten/";
	public static String outputPath = OriginDataPath + "Zscore"
			+ System.lineSeparator();

	public static String inputDatabase = OriginDataPath
			+ "selectedEntriesSecondaryId_Model_1.txt";

	public static String pValueTXT = outputPath + "pValue.txt";
	public static String sumCooccTXT = outputPath + "sumCoocc.txt";
	public static String Coocc5000TXT = outputPath + "Coocc5000.txt";
	public static String CooccFDSM = outputPath + "CooccFDSM.txt";

	public static int seed = 3306;

	public static void creatPath(String outputPath) {
		File file = new File(outputPath);
		file.mkdirs();

	}

	public static void LevAndPValue() {

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
		
		//Matrix pValue is used to records the pValues and every coocurence.
		short[][] pValue = new short[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];
		
		for(int i=0; i<numberOfSampleGraphs; i++){
			
			long t1 = System.currentTimeMillis();

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edges);
			
			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);
			
			long t2 = System.currentTimeMillis();
			
			double t3 = (double)(t2 - t1)/(double)1000;
			
			
			
			
			
		}

	}

	public static void calculate() {

		creatPath("huhu/haha/");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text.textReader2(inputDatabase, 0, 10);

	}

}
