package algo;

import java.util.ArrayList;
import java.util.Random;

import util.MyBitSet;

public class ZscoreNew {

	static String inputFile = "Example/Output/selectedEntriesSecondaryId_Model_1.txt";

	static String outputPath = "Example/Output/" + "ZScore/";

	static int numberOfSampleGraphs = 5000;

	public static int seed = 3306;

	// output file name for the levFDSM result:global list
	public static String levFDSM_GL_TXT = outputPath + "ZScore_GL.txt";
	public static String levFDSM_LL_TXT = outputPath + "ZScore_LL.txt";

	// public ArrayList<double[]> getBasicValues(){
	//
	//
	// }

	public static ArrayList<double[]> getBasicValues() {
		
		// measaures has 3 fields, [(double)i][(double)j][(double)zscore]
		ArrayList<double[]> measures = new ArrayList<double[]>();
		ArrayList<double[]> levFDSM_Means = new ArrayList<double[]>();

		BipartiteGraph bG = new BipartiteGraph(inputFile);

		int length = bG.numberOfPrimaryIds;

		MyBitSet adjM[] = bG.toSecBS();
		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];
		// read original co-occurrence
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		// The first long swap walks
		int[][] edges = bG.generateEdges();
		Random generator_edge = new Random(seed);

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edge);

		// set the normal length of swap walks
		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		for (int i = 0; i < numberOfSampleGraphs; i++) {
			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);

			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);

		}

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (coocc[i][j] > 0) {

					double mean = (double) coocc[j][i]
							/ (double) numberOfSampleGraphs;
					double levFDSM = (double) coocc[i][j] - mean;
					if (levFDSM > 0) {
						
						measures.add(new double[]{i,j});

						levFDSM_Means.add(new double[]{levFDSM, mean});

					}

				}

			}

		}
		
		double[] standarddivation = new double[measures.size()];

		int toCalLength = standarddivation.length;
		
		CooccFkt.matrixClearLeftDown(coocc);

		bG = new BipartiteGraph(inputFile);

		adjM = null;

		generator_edge = new Random(seed);

		adjM = bG.toSecBS();

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edge);

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);
			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);
			
			for(int j=0; j<toCalLength; j++){
				
				double[] pos = measures.get(j);
				double mean = levFDSM_Means.get(j)[1];
				
				standarddivation[j] += Math.pow((coocc[(int)pos[1]][(int)pos[0]] - mean),2);
				
			}
			
			CooccFkt.matrixClearLeftDown(coocc);

		}
		
		for(int i = 0; i<toCalLength; i++){
			
			standarddivation[i] = Math.sqrt(standarddivation[i]/numberOfSampleGraphs);
			
		}
		
		for(int i=0; i<toCalLength; i++){
			
			double[] value = measures.get(i);
			
			if(standarddivation[i] == 0){
				
				value[2] = -1;
				measures.set(i, value);
				continue;
			}
			
			value[2] = levFDSM_Means.get(i)[0]/standarddivation[i];
			
		}
		
		

		return measures;
	}

	public static void main(String[] args) {

		double abc = Math.pow(5, 2);
		System.out.println(abc);
	}

}
