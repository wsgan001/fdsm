package info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

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
			
			
			
			br.close();
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		
		
		
		
	}
	
	public static void test(){
		
		HashSet<String> hs = new HashSet<String>();
		
		hs.add("123");
		hs.add("123");
		hs.add("443");
		
		Iterator<String> it = hs.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();

	}

}
