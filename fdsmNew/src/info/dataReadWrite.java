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

	public static void dataIndex(String inputData, int primaryColumn,
			int secondaryColumn) {

		File file = new File(inputData);

		if (!file.exists()) {

			System.err.println("Sorry, I can't find the input File! :-/ ");
			System.exit(-1);

		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));


			String line = br.readLine();

			StringTokenizer st = new StringTokenizer(toker);

			FastBitSet fbsPrimaryList = new FastBitSet();
			FastBitSet fbsSecondaryList = new FastBitSet();

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

		int cnt = 0;
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					primaryIndexFile));
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(
					secondaryIndexFile));
			
			
			
			
			
			bw.close();
			bw2.close();
		} catch (IOException e) {
			System.exit(-1);
		
		}
		
		
		
		
		
		
	}

	public static void hstest() {

	}

	public static void main(String[] args) {

		hstest();

	}

}
