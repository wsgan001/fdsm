package algo;

import java.util.ArrayList;

import org.apache.commons.math3.util.CombinatoricsUtils;

import util.MyBitSet;

public class HypergeometricCoefficient {

	public static String inputFile = "Example/Output/selectedEntriesSecondaryId_Model_1.txt";

	public static String outputPath = "Example/Output/"
			+ "HypergeometricCoefficient/";

	public static String HypergeometricCoefficient_GL_TXT = outputPath
			+ "HypergeometricCoefficient_GL.txt";
	public static String HypergeometricCoefficient_LL_TXT = outputPath
			+ "HypergeometricCoefficient_LL.txt";

	public static ArrayList<int[]> calculate() {
		ArrayList<int[]> globalList = new ArrayList<int[]>();

		BipartiteGraph bG = new BipartiteGraph(inputFile);

		int length = bG.numberOfPrimaryIds;

		int n_R = bG.numberOfSamples;

		int[][] coocc = new int[length][length];

		MyBitSet[] adjMPrimary = bG.toPrimBS();

		CooccFkt.readCooccPrimaryAddTopRight(adjMPrimary, coocc);

		double hyp;

		int[] degree = new int[length];

		for (int i = 0; i < length; i++) {
			degree[i] = adjMPrimary[i].cardinality();
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				
				if(coocc[i][j]==0){
					continue;
				}
				
				int minDegree = Math.min(degree[i], degree[j]);
				
				for(int c = coocc[i][j]; c<=minDegree; c++){
					
					
					
					
					
				}
				
				
			}

		}

		return globalList;

	}

	public static void main(String[] args) {


	}

}
