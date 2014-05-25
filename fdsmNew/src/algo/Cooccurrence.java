package algo;

import java.io.File;
import java.util.ArrayList;

import util.MyBitSet;

public class Cooccurrence {
	
	public static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	public static String outputPath = info.DataSource.outputPath
			+ File.separator + "Cooccurrence" + File.separator;

	// public static int seed = 3306;

	public static String Coocc_GL_TXT = outputPath + "Coocc_GL.txt";
	public static String Coocc_LL_TXT = outputPath + "Coocc_LL.txt";
	
	public static ArrayList<int[]> calculate(){
		ArrayList<int[]> globalList = new ArrayList<>();
		
		BipartiteGraph bG = new BipartiteGraph(inputFile);
		
		int length = bG.numberOfPrimaryIds;
		
		int[][] coocc = new int[length][length];
		
		MyBitSet[] adjMPrimary = bG.toPrimBS();
		
		
		return globalList;
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
