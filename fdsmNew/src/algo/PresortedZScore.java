package algo;

import java.io.File;

public class PresortedZScore {

	public static int numberOfRandomSample = 5000;

	int seed = 3306;

	public static String inputFile = "Example/Output/selectedEntriesSecondaryId_Model_1.txt";

	public static String outputPath = "Example/Output/" + "PresortedZScore/"
			+ numberOfRandomSample + "/";

	public static String PresortedZScore_GL_TXT = outputPath
			+ "PearsonCorrelation_GL.txt";

	public static String PresortedZScore_LL_TXT = outputPath
			+ "PearsonCorrelation_LL.txt";

	public static void checkPValue() {

		File file1 = new File(outputPath);
		
		
		int number1 = outputPath.indexOf("PresortedZScore");
		int number2 = outputPath.lastIndexOf("PresortedZScore");
		System.out.println(number1+","+number2);
		
	}

	public static void main(String[] args) {

		checkPValue();
	}

}
