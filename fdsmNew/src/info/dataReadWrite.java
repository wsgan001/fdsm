package info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javolution.util.FastBitSet;
import javolution.util.FastTable;
import javolution.util.Index;
import gnu.trove.*;

import org.apache.lucene.*;
import org.apache.lucene.util.OpenBitSet;
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
		
		hs.add("宝宝图");
		hs.add("我们");
		hs.add("5666");
		hs.add("84");
		hs.add("441");
		
		Iterator<String> it = hs.iterator();
		
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
		String[] myarray = 	(String[])hs.toArray(new String[0]);
		
		Arrays.sort(myarray);
		
//		for(String i : myarray){
//			System.out.println(i);
//		}
		
		
		FastBitSet fbs = new FastBitSet();
		
		fbs.set(100);
		fbs.set(102);
		
//		int[] arr = fbs.toArray(new int[0]);
		
		Index abc = fbs.max();
		
//		System.out.println(abc);
		
		BitSet bs = new BitSet();
		
		bs.set(100);
		bs.set(200);
		bs.set(56);
		
//		String bst = bs.toString();
		
//		System.out.println(bst);
		
		long[] larr = bs.toLongArray();
		
		
		
		System.out.println(larr.length);
		
for(int i = 0; i<larr.length;i++){
	System.out.println(larr[i]);
}

	Set<Integer> tset = new TreeSet<Integer>();
	
	tset.add(6);
	
	tset.add(2);
	
	tset.add(3);
	


	
	
		
		
//		OpenBitSet obs = new OpenBitSet();
		
				
		
		
		
	}
	
	

	public static void main(String[] args) {
		test();

	}

}
