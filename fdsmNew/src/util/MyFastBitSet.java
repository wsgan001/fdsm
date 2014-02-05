package util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;

import javolution.util.FastBitSet;
import javolution.util.Index;

public class MyFastBitSet extends FastBitSet {
	
	public MyFastBitSet(){
		super();
	}
	
	public MyFastBitSet myand(MyFastBitSet b){
		
		MyFastBitSet a = this;		
		a.or(b);
		
		return a;
		
	}
	
	
	

	public static void main(String[] args) {

		MyFastBitSet mfbs = new MyFastBitSet();
		
		FastBitSet fbsBitSet = new FastBitSet();
		
		mfbs.set(100);
		mfbs.set(200);
		
		MyFastBitSet mfbs2 = new MyFastBitSet();
		
		mfbs2.set(200);
		mfbs2.set(300);
		
		mfbs2.myand(mfbs);
		
//		for(int i= mfbs2.nextSetBit(0); i>=0; i=mfbs2.nextSetBit(i+1)){
//			System.out.println(i);
//		}
		
		Index[] arr = mfbs2.toArray(new Index[0]);
		
		for(Index i : arr){
			System.out.println(i);
		}
		
	}

}
