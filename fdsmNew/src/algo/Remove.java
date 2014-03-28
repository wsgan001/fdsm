package algo;

import gnu.trove.list.array.TIntArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.MyBitSet;

public class Remove {

	public static MyBitSet lowDegrees(int[] degrees, int minDegree, String inputFile) {
		MyBitSet lowDeg = new MyBitSet();
		for (int i = 0; i < degrees.length; i++) {
			if (degrees[i] < minDegree) {
				lowDeg.set(i);
			}

		}

		return lowDeg;
	}

	public static void rewriteList(String inputFile, String outputFile, int minDegree){
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			HashMap<String, String> hm = util.Text.readLineInfos(line);
			String type = hm.get("type");
			
			
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	public static void rewriteGlobal(BufferedReader br, String outputFile, MyBitSet lowDegrees) throws IOException{
		String line = br.readLine();
		while(line != null){
			StringTokenizer st = new StringTokenizer(line, ",");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			
			
			
		}
		
		
	}
	
	public static void test(){
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("huhu", "aa");
		
		hm.put("haha", "bb");
		
		System.out.println(hm.get("huhu"));
		System.out.println(hm.get("haha"));
		System.out.println(hm.get("heha"));
		
		
		
	}
	
	public static void main(String[] args) {

	}

}
