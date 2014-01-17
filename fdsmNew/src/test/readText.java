package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import info.*;

public class readText {
	
	
	/**
	 * Free memory
	 * 
	 * @param document file with Path
	 * @param start int, the number of start line, nature number (that means the first linenumber is equal 1)
	 * @param numberOflines how many lines do you want to see
	 * @return
	 * @throws Exception
	 */
	public static void readTextline(String document,int start, int numberOflines){
		
		File file = new File(document);
		
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			for(int i=0; i<start-1; i++){
				br.readLine();
				
			}
			
			for(int i=start-1; i<start+numberOflines-1; i++){
				line = br.readLine();
				
				System.out.println(line);
				
			}
			
			br.close();
		}catch(FileNotFoundException e){
			
			e.printStackTrace();
			System.exit(-1);
			
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		
		
	}
	
	

	public static void main(String[] args) {
		String document = "testText";
		readTextline(document, 2, 3);

		
		
	}
}
