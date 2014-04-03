package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.Text;

public class Zscore {

//	public static String inputDatabase = info.dataReadWrite.selectedEntriesSecondaryId_Model_1TXT;
	public static String inputDatabase = "C:/ying/netflixDaten/selectedEntriesSecondaryId_Model_1.txt";
	
//	public static String outputPath = info.dataReadWrite.outputPath + "Zscore"
//			+ System.lineSeparator();
	public static String OriginDataPath = "C:/ying/netflixDaten/";
	public static String outputPath = OriginDataPath+"Zscore"+System.lineSeparator();
	

	public static String pValueTXT = outputPath + "pValue.txt";
	public static String sumCooccTXT = outputPath + "sumCoocc.txt";
	public static String Coocc5000TXT = outputPath + "Coocc5000.txt";
	public static String CooccFDSM = outputPath + "CooccFDSM.txt";

	public static void creatPath(String outputPath) {
		File file = new File(outputPath);

		file.mkdirs();

	}
	
	
	
	
	public static void LevAndPValue(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputDatabase));
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void calculate() {

		creatPath("huhu/haha/");
		
		
		
		
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text.textReader2(inputDatabase, 0, 10);
			
	}

}
