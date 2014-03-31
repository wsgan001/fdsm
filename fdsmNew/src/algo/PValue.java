package algo;

import gnu.trove.set.hash.TIntHashSet;

import java.io.File;
import java.util.HashSet;
import java.util.Random;

import util.MyBitSet;

public class PValue {

	static int numberOfSampleGraphs = 5000;

	static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	static String outputPath = info.DataSource.outputPath + File.separator
			+ "PValue" + File.separator;

	// output file name for the levFDSM result:global list
	public static String levFDSM_TXT = outputPath + "PValue.txt";
	public static String levFDSM_GL_TXT = outputPath + "PValue_GL.txt";
	public static String levFDSM_LL_TXT = outputPath + "PValue_LL.txt";

	public static int seed = 3306;

	public static void calculate() {

		// initialise the basic Informations about this Bipartite Graph
		BipartiteGraph bG = new BipartiteGraph();

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];
		int[][] pvalue = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		// read the original Cooccurence
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		// begin the first long swap walks
		Random generator_edge = new Random(seed);

		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edge);

		// set the normal swap walks
		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);

			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);

		}

		CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);

		
		
	}
	
	public static void pRead(int[][] coocc, MyBitSet[] priAdjM){

		int length = priAdjM.length;
		
		for(int i = 0; i<length; i++){
			for(int j=i+1; j<length; j++){
				int cooccValue = priAdjM[i].myand(priAdjM[j]).cardinality();
				
				if(cooccValue<coocc[i][j]){
					coocc[j][i]++;

				}
				
			}
			
		}
		
	}
	
	public static void pRead(int[][] coocc, TIntHashSet[] priAdjM){
		int length = priAdjM.length;
		for(int i= 0; i<length; i++){
			for(int j=i+1; j<length; j++){
				int cooccValue = util.General.THSIntersectSize(priAdjM[i], priAdjM[j]);
				
				if(cooccValue <coocc[i][j]){
					coocc[j][i]++;
				}
				
			}
			
		}
		
		
	}
	
	public static void calculate2(){
		
		BipartiteGraph bg = new BipartiteGraph();
		MyBitSet[] priAdjM = bg.toPrimBS();
		
		int[][] edges = bg.generateEdges();
		Random generator_edge = new Random(seed);
		
		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bg.numberOfEdges, edges, priAdjM, generator_edge);

		int lengthOfWalks = (int)(bg.numberOfSamples * Math.log(bg.numberOfSamples));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public static void test() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
