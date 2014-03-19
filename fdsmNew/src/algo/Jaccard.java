package algo;

import java.util.BitSet;
import java.util.HashMap;

import util.MyBitSet;

public class Jaccard {

	public static int[] readDegree(MyBitSet[] adjMPrimary) {
		int[] degrees = new int[adjMPrimary.length];
		int length = degrees.length;
		for (int i = 0; i < length; i++) {
			degrees[i] = adjMPrimary[i].cardinality();
		}

		return degrees;
	}

	public static int[][] calculate(){
		
		BipartiteGraph bg = new BipartiteGraph();
		
		int[][] measures = new int[bg.numberOfPrimaryIds][bg.numberOfPrimaryIds];
		
		int length = bg.numberOfPrimaryIds;
		
		MyBitSet[] adjMPrimary = bg.toPrimBS();
		
		int[] degrees = readDegree(adjMPrimary);
		
		CooccFkt.readCooccPrimaryAddTopRight(adjMPrimary, measures);
		
		for(int i = 0; i<length; i++){
			
			for(int j = i+1; j<length; j++){
				
				if(measures[i][j] > 0){
					
					measures[i][j] = measures[i][j]/(degrees[i]+degrees[j]-measures[i][j]);
				}
				
			}
			
			
		}
		
		
		
		
		return measures;
	}
	
	public static void test(){
		
		HashMap<BitSet, Integer> abc = new HashMap<BitSet, Integer>();
		
		BitSet bs = new BitSet();
		
		bs.set(100);
		
		abc.put(bs, 100);
		
		abc.get(abc);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
