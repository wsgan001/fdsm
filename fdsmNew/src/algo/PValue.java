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

		// initialize the basic Informations about this Bipartite Graph
		BipartiteGraph bG = new BipartiteGraph();

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];
		short[][] pValues = new short[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		// read the original Co-occurrence
		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		// begin the first long swap walks
		Random generator_edge = new Random(seed);

		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edge);

		// set the normal swap walks
		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			long t1 = System.currentTimeMillis();

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edge);

			CooccFkt.readCooccSecAddLowerLeft(adjM, coocc);

			pSecAddTopRightClear(coocc, pValues);

			long t2 = System.currentTimeMillis();

			long t3 = t2 - t1;

			System.out.println("Round " + i + " takes " + (t3 / 1000)
					+ " seconds...");

		}

		System.out.println("Begin to write the pValue List...");

		ArrayList<short[]> pMeasures = pValueMeasure(pValues);
		long t1 = System.currentTimeMillis();

		Text.writeListShort(pMeasures, pValue_TXT, "pValue", "list", "", false);

		long t2 = System.currentTimeMillis();

		System.out.println("Write the pValue List takes " + (t2 - t1)/1000
				+ " milliseconds");

		System.out.println("Begin to write the pValue Global List...");
		t1 = System.currentTimeMillis();
		Text.writeListShort(pMeasures, pValue_GL_TXT, "pValue", "global list",
				"", true);
		t2 = System.currentTimeMillis();

		System.out.println("Write the pValue Global List takes " + (t2 - t1)/1000
				+ " milliseconds");

	}

	/**
	 * Read the pValues from coocc Matrix into a pValue Matrix and clear the
	 * lowerleft of the coocc at the same time.
	 * 
	 * @param coocc
	 * @param pValues
	 * @param adjM
	 */
	public static void pSecAddTopRightClear(int[][] coocc, short[][] pValue) {
		int length = coocc.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (coocc[i][j] > coocc[j][i]) {
					pValue[i][j]++;
				}
				coocc[j][i] = 0;
			}

		}

	}

	public static ArrayList<short[]> pValueMeasure(short[][] pValue) {
		ArrayList<short[]> pMeasures = new ArrayList<short[]>();

		int length = pValue.length;

		for (int i = 0; i < length; i++) {

			for (int j = i + 1; j < length; j++) {

				if (pValue[i][j] > 0) {

					pMeasures.add(new short[] { (short) i, (short) j,
							pValue[i][j] });

				}

			}

		}

		return pMeasures;

	}

	/**
	 * if the value of coocc bigger than the random Sample Graph, then I will
	 * take it as one point.
	 * 
	 * @param coocc
	 * @param priAdjM
	 *            Primary Bipartite Graph(PrimaryId: SecondaryId1,
	 *            SecondaryIds2, ...)
	 */
	public static void pPriAddLowerleft(int[][] coocc, MyBitSet[] priAdjM) {

		int length = priAdjM.length;

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int cooccValue = priAdjM[i].myand(priAdjM[j]).cardinality();

				if (cooccValue < coocc[i][j]) {
					coocc[j][i]++;

				}

			}

		}

	}

	/**
	 * if the value of coocc bigger than the random Sample Graph, then I will
	 * take it as one point.
	 * 
	 * @param coocc
	 * @param priAdjM
	 *            Primary Bipartite Graph(PrimaryId: SecondaryId1,
	 *            SecondaryIds2, ...)
	 */
	public static void pPriAddLowerleft(int[][] coocc, TIntHashSet[] priAdjM) {
		int length = priAdjM.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int cooccValue = util.General.THSIntersectSize(priAdjM[i],
						priAdjM[j]);

				if (coocc[i][j] > cooccValue) {
					coocc[j][i]++;
				}

			}

		}

	}

	public static void calculate2() {

		BipartiteGraph bg = new BipartiteGraph();
		MyBitSet[] priAdjM = bg.toPrimBS();

		int[][] edges = bg.generateEdgesPrimarySecondary();
		Random generator_edge = new Random(seed);

		System.out.println("the first long long swap...");

		CooccFkt.swap(4 * bg.numberOfEdges, edges, priAdjM, generator_edge);

		int lengthOfWalks = (int) (bg.numberOfSamples * Math
				.log(bg.numberOfSamples));

		int[][] coocc = CooccFkt.readOriginalCoocurrence();

		for (int i = 0; i < numberOfSampleGraphs; i++) {
			System.out.println("round " + i + " ...");
			long t1 = System.currentTimeMillis();
			CooccFkt.swap(lengthOfWalks, edges, priAdjM, generator_edge);
			pPriAddLowerleft(coocc, priAdjM);
			long t2 = System.currentTimeMillis();
			long t3 = (t2 - t1) / 1000;
			System.out.println("This round takes " + t3 + " seconds");
		}

		System.out.println("Write the PValue Gloable List");
		ArrayList<int[]> measures = CooccFkt.positiveMeasureLowerLeft(coocc);

		Text.writeList(measures, pValue_TXT, "pValue", "list", "", true);
		
	}
	
	

	public static void test() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculate();

	}

}
