package old;

import java.util.Random;

import structure.BipartiteGraph;
import structure.CooccFkt;
import util.MyBitSet;

public class Zscore2 {
	
	public static int numberOfSampleGraphs = 10;

	public static String inputDatabase = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	// public static String outputPath = info.dataReadWrite.outputPath +
	// "Zscore"
	// + System.lineSeparator();
	public static String outputPath = info.dataReadWrite.outputPath + "Zscore2/";

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
	
	
	public static void run1(){
		
		BipartiteGraph bg = new BipartiteGraph(inputDatabase);
		
		MyBitSet[] adjM = bg.toSecBS();
		
		int[][] coocc = new int[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];
		
		System.out.println("read original coocurence");
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);
		
		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);
		
		System.out.println("generate edges");
		
		Random generator_edges = new Random(seed);
		
		System.out.println("The first long swap walks");
		
		int[][] edges = bg.generateEdges();
		
		CooccFkt.swap(4*bg.numberOfEdges, edges, adjM, generator_edges);
		
		int lengthOfWalks = (int)(bg.numberOfSamples * Math.log(bg.numberOfSamples));
		
		short[][] pValue = new short[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
