package algo;

import gnu.trove.map.hash.TDoubleObjectHashMap;
import gnu.trove.map.hash.TObjectDoubleHashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.rmi.CORBA.Util;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.Edge;

public class PresortedZScore {

	public static int numberOfSampleGraphs = 5;

	int seed = 3306;

	public static String inputFile = "Example/Output/selectedEntriesSecondaryId_Model_1.txt";

	public static String outputPath = "Example/Output/" + "PresortedZScore/"
			+ numberOfSampleGraphs + "/";

	public static String PresortedZScore_GL_TXT = outputPath
			+ "PearsonCorrelation_GL.txt";

	public static String PresortedZScore_LL_TXT = outputPath
			+ "PearsonCorrelation_LL.txt";

	public static void checkPValueZScore() {

		String outputRoot = outputPath
				.substring(0, outputPath.lastIndexOf("P"));

		String PValueFile = outputRoot + "/PValue/" + numberOfSampleGraphs
				+ "/PValue_GL.txt";

		String ZScoreFile = outputRoot + "/ZScore/" + numberOfSampleGraphs
				+ "/ZScore_GL.txt";

		File file1 = new File(PValueFile);
		File file2 = new File(ZScoreFile);

		if (!file1.exists()) {

			PValue.run(numberOfSampleGraphs, 1.0);

		}
		;

		if (!file2.exists()) {

			ZscoreNew.run(numberOfSampleGraphs);

		}

	}

	public static ArrayList<double[]> calculate() {

		ArrayList<double[]> measures = new ArrayList<double[]>();

		TDoubleObjectHashMap<ArrayList<double[]>> smallZscoreList = new TDoubleObjectHashMap<ArrayList<double[]>>();

		String outputRoot = outputPath
				.substring(0, outputPath.lastIndexOf("P"));

		String PValueFile = outputRoot + "/PValue/" + numberOfSampleGraphs
				+ "/PValue_GL.txt";

		String ZScoreFile = outputRoot + "/ZScore/" + numberOfSampleGraphs
				+ "/ZScore_GL.txt";

		TObjectDoubleHashMap<Edge> zScores = new TObjectDoubleHashMap<Edge>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(ZScoreFile));
			br.readLine();

			String line = br.readLine();

			while (line != null) {

				StringTokenizer st = new StringTokenizer(line, ",");

				zScores.put(
						new Edge(Integer.parseInt(st.nextToken()), Integer
								.parseInt(st.nextToken())), Double
								.parseDouble(st.nextToken()));

				line = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(PValueFile));

			BufferedWriter bw = new BufferedWriter(new FileWriter(
					PresortedZScore_GL_TXT));

			br.readLine();

			String line = br.readLine();

			int m1 = 0;
			int m2 = 0;
			double PValue_Old = -1;

			boolean mPosted = false;
			boolean nPosted = false;

			while (line != null) {

				StringTokenizer st = new StringTokenizer(line, ",");

				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				double pValue = Double.parseDouble(st.nextToken());

				if (pValue == PValue_Old) {

					if (mPosted == true) {
						double zscore = zScores.get(new Edge(n1, n2));
						smallZscoreList.get(pValue).add(
								new double[] { n1, n2, pValue, zscore });
						mPosted = true;
						m1 = n1;
						m2 = n2;
					} else {
						// smallZscoreList.put(pValue, new ArrayList<>());
						ArrayList<double[]> pValueArrayList = new ArrayList<double[]>();
						double zscore_old = zScores.get(new Edge(m1, m2));
						double[] m = new double[] { m1, m2, PValue_Old,
								zscore_old };
						double zscore = zScores.get(new Edge(n1, n2));
						double[] n = new double[] { n1, n2, PValue_Old, zscore };
						
						mPosted = true;
						m1 = n1;
						m2 = n2;

					}
					
					

				} else {
					mPosted = false;

				}

				line = br.readLine();

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return measures;
	}

	public static void main(String[] args) {

//		test();
		// calculate();
	}

}
