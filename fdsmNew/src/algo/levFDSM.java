package algo;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import util.MyBitSet;

public class levFDSM {

	static int numberOfSampleGraphs = 5000;

	static String inputFile = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;

	static String outputPath = info.DataSource.outputPath + File.separatorChar
			+ "levFDSM" + File.separator;

	static int seed = 3306;

	public static int[][] calculate() {

		BipartiteGraph bG = new BipartiteGraph(inputFile);

		MyBitSet[] adjM = bG.toSecBS();

		int[][] edges = bG.generateEdges();

		int[][] coocc = new int[bG.numberOfPrimaryIds][bG.numberOfPrimaryIds];

		CooccFkt.readCooccSecAddTopRight(adjM, coocc);

		CooccFkt.multipleMatrixTopRight(coocc, numberOfSampleGraphs);

		Random generator_edges = new Random(seed);

		CooccFkt.swap(4 * bG.numberOfEdges, edges, adjM, generator_edges);

		int lengthOfWalks = (int) (bG.numberOfSamples * Math
				.log(bG.numberOfSamples));

		for (int i = 0; i < numberOfSampleGraphs; i++) {

			CooccFkt.swap(lengthOfWalks, edges, adjM, generator_edges);

			CooccFkt.readCooccSecSubstractTopRight(adjM, coocc);

		}

		return coocc;
	}

	public static void test() {

	}

	public static void main(String[] args) {
	}

}
