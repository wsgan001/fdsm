package info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class dataReadWrite {
	
	String outputPath = DataPad.outputPath;
	String primaryIndexFile = outputPath+"PrimaryIndex.txt";
	String secondaryIndexFile = outputPath+"SecondaryIndex.txt";
	
	
	
	public static void dataIndex(String inputData, String outputPath, String toker, int primaryColumn, int secondaryColumn){
		
		File file = new File(inputData);
		
		if(!file.exists()){
		
			System.err.println("Sorry, I can't find the input File! :-/ ");
			System.exit(-1);
			
		}
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			
		}catch(IOException e){
			
		}
		
		
		
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
