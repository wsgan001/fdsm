package algo;

import gnu.trove.set.hash.TIntHashSet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import util.MyBitSet;
import util.Text;

public class PValue {

	static int numberOfSampleGraphs = 5000;

	static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	static String outputPath = info.DataSource.outputPath + File.separator
			+ "PValue" + File.separator;

	// output file name for the levFDSM result:global list
	public static String pValue_TXT = outputPath + "PValue.txt";
	public static String pValue_GL_TXT = outputPath + "PValue_GL.txt";
	public static String pValue_LL_TXT = outputPath + "PValue_LL.txt";

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
	
	/**
	 * if the value of coocc bigger than the random Sample Graph, then I will take it as one point.
	 * @param coocc
	 * @param priAdjM
	 */
	public static void pReadAddLowerleft(int[][] coocc, MyBitSet[] priAdjM){

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
	
	public static void pReadAddLowerleft(int[][] coocc, TIntHashSet[] priAdjM){
		int length = priAdjM.length;
		for(int i= 0; i<length; i++){
			for(int j=i+1; j<length; j++){
				int cooccValue = util.General.THSIntersectSize(priAdjM[i], priAdjM[j]);
				
				if(coocc[i][j] > cooccValue){
					coocc[j][i]++;
				}
				
			}
			
		}
		
		
	}
	

	
	public static void calculate2(){
		
		BipartiteGraph bg = new BipartiteGraph();
		MyBitSet[] priAdjM = bg.toPrimBS();
		
		int[][] edges = bg.generateEdgesPrimarySecondary();
		Random generator_edge = new Random(seed);
		
		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bg.numberOfEdges, edges, priAdjM, generator_edge);

		int lengthOfWalks = (int)(bg.numberOfSamples * Math.log(bg.numberOfSamples));
		
		int[][] coocc = CooccFkt.readOriginalCoocurrence();
		
		for(int i=0; i<numberOfSampleGraphs; i++){
			
			CooccFkt.swap(lengthOfWalks, edges, priAdjM, generator_edge);
			pReadAddLowerleft(coocc, priAdjM);
		}
		
		 System.out.println("Write the PValue Gloable List");
		 ArrayList<int[]> measures = CooccFkt.positiveMeasureLowerLeft(coocc);
		 
		 
		 
		 Text.writeList(measures, pValue_TXT, "pValue", "list",
		 "",
		 false);
		
	}

	public static void test() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculate2();

	}

}
