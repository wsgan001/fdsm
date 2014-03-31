package util;

import gnu.trove.iterator.TIntIntIterator;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.iterator.TIntShortIterator;
import gnu.trove.set.hash.TIntHashSet;

public class General {
	
	public static int THSIntersectSize(TIntHashSet ths1, TIntHashSet ths2){
		int counter = 0;
		
		if(ths1.size()<=ths2.size()){
			TIntIterator it = ths1.iterator();
			for(int i=ths1.size(); i-->0;){
				if(ths2.contains(it.next())){
					counter++;
				}
				
			}
			
		}else {
			TIntIterator it = ths2.iterator();
			for(int i= ths2.size(); i-->0;){
				if(ths1.contains(it.next())){
					counter++;
				}
			}
			
		}
		
		return counter;
	}

	public static void main(String[] args) {

		TIntHashSet ths1 = new TIntHashSet();
		ths1.add(1);
		ths1.add(2);
		ths1.add(3);
		ths1.add(4);
		
		TIntHashSet ths2 = new TIntHashSet();
		ths2.add(1);
		ths2.add(2);
//		ths2.add(3);
		ths2.add(4);
		
		int size = THSIntersectSize(ths1, ths2);

		System.out.println(size);
		System.out.println(ths1.size());
		System.out.println(ths2.size());
	}

}
