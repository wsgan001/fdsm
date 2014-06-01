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
	
//	public ArrayList<double[]> getBasicValues(){
//		
//		
//	}
	
	public static ArrayList<double[]> getBasicValues(int[][] coocc){
		ArrayList<double[]> basicValues = new ArrayList<double[]>();
		
		BipartiteGraph bG = new BipartiteGraph(inputFile);
		
		int length = bG.numberOfPrimaryIds;
		
		MyBitSet adjM[]	= bG.toSecBS();
		
		//read original co-occurrence
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);
		
		//The first long swap walks
		int[][] edges = bG.generateEdges();
		Random generator_edge = new Random(seed);
		
		CooccFkt.swap(4*bG.numberOfEdges, edges, adjM, generator_edge);
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);
		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);
		
		//set the normal length of swap walks
		int lengthOfWalks = (int)(bG.numberOfSamples * Math.log(bG.numberOfSamples));
		
		for(int i=0; i<numberOfSampleGraphs; i++){
			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);
			
			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);
			
					
		}
		
		for(int i=0; i<length; i++){
			for(int j=i+1; j<length; j++){
				if(coocc[i][j] > 0){
					int levFDSM = coocc[i][j] - coocc[j][i];
					if(levFDSM > 0){
						
					}
					
					
				}
				
				
			}
			
			
		}
		
		
		
		
		
		
		return basicValues;
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		
	}

}
