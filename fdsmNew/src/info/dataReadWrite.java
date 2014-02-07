package info;

import gnu.trove.set.hash.TIntHashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

import javolution.lang.Immutable;
import javolution.util.FastBitSet;
import javolution.util.FastSet;
import javolution.util.FastSortedSet;
import javolution.util.FastTable;
import javolution.util.Index;
//import gnu.trove.*;
//
//import org.apache.lucene.*;
//import org.apache.lucene.util.OpenBitSet;

public class dataReadWrite {

	static String outputPath = DataPad.outputPath;
	static String primaryIndexFile = outputPath + "PrimaryIndex.txt";
	static String secondaryIndexFile = outputPath + "SecondaryIndex.txt";
	static String toker = ",";


	/**
	 * generate the computer indexes for the data, the outputs are two computer
	 * indexes for the primary column and secondary column.
	 * 
	 * @param inputData
	 * @param primaryColumn
	 *            native number of the goal Column
	 * @param secondaryColumn
	 *            native number of the Column
	 */
	public static void dataIndex(String inputData, int primaryColumn,
			int secondaryColumn) {
		
		File outputPath = new File(info.DataPad.outputPath);

		File file = new File(inputData);

		if (!file.exists()) {

			System.err.println("Sorry, I can't find the input File! :-/ ");
			System.exit(-1);

		}
		
		if (!outputPath.exists()){
			outputPath.mkdir();
		}

		FastBitSet fbsPrimaryList = new FastBitSet();
		FastBitSet fbsSecondaryList = new FastBitSet();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();

			StringTokenizer st = new StringTokenizer(toker);

			while (line != null) {

				String[] lineInfo = line.split(toker);

				fbsPrimaryList.set(Integer
						.parseInt(lineInfo[primaryColumn - 1]));
				fbsSecondaryList.set(Integer
						.parseInt(lineInfo[secondaryColumn - 1]));

				line = br.readLine();

			}

			br.close();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		Index[] indPrimary = fbsPrimaryList.toArray(new Index[0]);
		Index[] indSecondary = fbsSecondaryList.toArray(new Index[0]);

		int cnt = 0;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					primaryIndexFile));
			
			for(Index ind : indPrimary){
				
				bw.write(cnt + toker + ind);
				
				cnt++;
			}
			

			bw.close();

			cnt = 0;
			
			bw = new BufferedWriter(new FileWriter(secondaryIndexFile));
			
			for(Index ind : indSecondary){
				
				bw.newLine();
				bw.write(cnt + toker + ind + DataPad.lineSeparate); 
				
				cnt++;
				
			}

			bw.close();
		} catch (IOException e) {
			System.exit(-1);

		}

	}

	public static void hstest() {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath+"hallo.txt"));
			bw.write("aaa");
			
			bw.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		

	}

	public static void main(String[] args) {
		dataIndex(DataPad.data3user, 1, 2);
		
		
	}

}
