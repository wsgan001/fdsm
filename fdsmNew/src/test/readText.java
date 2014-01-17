package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.*;
import info.*;

public class readText {
	
	
	/**
	 * Free memory
	 * 
	 * @param inputFileWithPath file with Path
	 * @param start int, the number of start line, nature number (that means the first linenumber is equal 1)
	 * @param numberOflines how many lines do you want to see
	 * @return
	 * @throws Exception
	 */
	public static void readTextline(String inputFileWithPath,int start, int numberOflines){
		
		File file = new File(inputFileWithPath);
		
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
	
	
	public static void writeText(String outputFileWithPath){
		
		File file = new File(outputFileWithPath);
		
		try{
			
			BufferedWriter bw;
			if(file.exists()){
				System.out.println("file exist! The information will be appended");
				bw = new BufferedWriter(new FileWriter(file, true));
			}else{
				System.out.println("file not exist! It will create a new file!");
				bw = new BufferedWriter(new FileWriter(file));
			}
			
			bw.write("ahh");
			bw.newLine();
			bw.write("huhu");
			bw.write(File.pathSeparator);
			bw.write(File.pathSeparatorChar);
			bw.write("gege");
			

			bw.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		
	}
	
	
	public static void getSystemInfos(){
		
		Properties props = System.getProperties();
		
		System.out.println(props.getProperty("os.name"));
		
				
	}
	
	public static void sysIndependent(){
		// Anzeige aller Zeitzonen-IDs:
		String[] ssIDs = TimeZone.getAvailableIDs();
		for( int i=0; i<ssIDs.length; i++ )  System.out.println( ssIDs[i] );
		// Anzeige der aktuellen Default-Zeitzone:
		System.out.println( "Default-Zeitzone = " + TimeZone.getDefault().getID() );       // z.B. 'Europe/Berlin'
		System.out.println( "user.timezone = " + System.getProperty( "user.timezone" ) );  // z.B. 'Europe/Berlin'
		
	}
	
	public static void timeExample(){
		// Anzeige aller Zeitzonen-IDs:
				String[] ssIDs = TimeZone.getAvailableIDs();
				for( int i=0; i<ssIDs.length; i++ )  System.out.println( ssIDs[i] );
				// Anzeige der aktuellen Default-Zeitzone:
				System.out.println( "Default-Zeitzone = " + TimeZone.getDefault().getID() );       // z.B. 'Europe/Berlin'
				System.out.println( "user.timezone = " + System.getProperty( "user.timezone" ) );  // z.B. 'Europe/Berlin'
		
	}
	

	public static void main(String[] args) {

		sysIndependent();
		
		
	}
}
