package info;

import gnu.trove.set.hash.TIntHashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javolution.util.FastBitSet;
import javolution.util.FastSet;
import javolution.util.FastTable;
import javolution.util.Index;
//import gnu.trove.*;
//
//import org.apache.lucene.*;
//import org.apache.lucene.util.OpenBitSet;
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
			
			String line = br.readLine();
			
			StringTokenizer st = new StringTokenizer(toker);
			
			while(line != null){
				
				
				
				
				
				
			}
			
			
			br.close();
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		
		
		
		
	}
	

	
	public static void hstest(){
		
		FastSet<Integer> fs = new FastSet<Integer>();
		
		fs.add(100);
		fs.add(101);
		fs.add(22);
		fs.add(45);
		
		FastSet<Integer> fs2 = new FastSet<Integer>();
		
		fs2.add(300);
		fs2.add(600);
		fs2.add(10);
		fs2.add(34);
		
		fs.addAll(fs2);

		
		
		TIntHashSet tihs = new TIntHashSet();
		
		
		
		
		
	}
	
	

	public static void main(String[] args) {
		
		hstest();
		
	}

}
