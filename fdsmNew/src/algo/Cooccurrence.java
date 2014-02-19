package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.MyBitSet;

public class Cooccurrence {
	
	public static MyBitSet[] bipartiteGraphSecBS(String inputTXT){
		
		File file = new File(inputTXT);
		
		MyBitSet[] adjM;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			HashMap<String, String> hs = new HashMap<String, String>();
			hs = util.Text.readLineInfos(hs, line);
			int numberOfSamples = Integer.parseInt(hs.get("numberOfSamples"));
			
			adjM = new MyBitSet[numberOfSamples];
			
			
			br.readLine();
			
			line = br.readLine();

			while(line != null){
				String[] lineInfos = line.split(",");
				
				
				
				
				
				line = br.readLine();
			}
			
			
			
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
		return adjM;
		
	}
	
	
	
	public static void main(String[] args) {
		
	}

}
