package algo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import util.MyBitSet;

public class LevFDSM {

	static int numberOfSampleGraphs = 10;

	static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	static String outputPath = info.DataSource.outputPath + File.separator
			+ "levFDSM" + File.separator;

	// output file name for the levFDSM result:global list
	public static String levFDSM_TXT = outputPath + "levFDSM.txt";
	public static String levFDSM_GL_TXT = outputPath + "levFDSM_GL.txt";
	public static String levFDSM_LL_TXT = outputPath + "levFDSM_LL.txt";

	public static int seed = 3306;

	public static int[][] calculate() {

		BipartiteGraph bG = new BipartiteGraph(inputFile);

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		System.out.println("read original cooccurence...");

		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		System.out.println("multiple Samples");

		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);

		System.out.println("generate edges...");

		Random generator_edges = new Random(seed);

		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edges);

		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			long t1 = System.currentTimeMillis();
			System.out.println("begin small swap, round " + i);

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edges);
			
			System.out.println("substact the coocc...");

			CooccFkt.readCooccSecSubstractTopRight(adjM, coocc);

			long t2 = System.currentTimeMillis();

			double t3 = (double)(t2 - t1) / (double)1000;

			System.out.println("Sample " + i + " takes " + t3 + " seconds");

		}

		return coocc;
	}

	public static void run() {

		File file = new File(outputPath);

		if (!file.exists()) {
			file.mkdirs();
		}

		int[][] coocc = calculate();

		ArrayList<int[]> lev = CooccFkt.positiveMeasureTopRight(coocc);

		System.out.println("Write the levFDSM");
		util.Text.writeList(lev, levFDSM_TXT,"levFDSM","global list","", false);
		System.out.println("Write the GL");
		util.Text.writeList(lev, levFDSM_GL_TXT, "levFDSM","global list", "" ,true);
		
		System.out.println("Write the LL");
		util.Text.writeLocalList(lev, levFDSM_LL_TXT, "levFDSM", "local list", "" );

	}

	public static void test() {

		File file = new File(outputPath + "aaa" + File.separator + "bbb.txt");

		if (!file.exists()) {

			System.out.println(file.getParentFile().mkdirs());

			try {
				System.out.println(file.createNewFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		run();
		
//		util.Text.textReader(levFDSM_LL_TXT);
		
//		util.Text.textReader(levFDSM_GL_TXT);
		
		

	}

}
