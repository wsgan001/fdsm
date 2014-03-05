package util;

import java.util.Arrays;
import java.util.Comparator;

public class ReverseComparator<T extends Number> implements Comparator<T[]> {

	int col;

	public ReverseComparator(int col) {

		this.col = col;

	}

	@Override
	public int compare(T[] a, T[] b) {

//		if (a[col - 1] > b[col - 1]) {
//			return -1;
//		} else if (a[col - 1] < b[col - 1]) {
//			return 1;
//		} else {
//			return 0;
//		}

	}


	public static void main(String[] args) {

		int[][] abc = new int[][] { { 1, 2, 5 }, { 2, 5, 8 }, { 3, 9, 2 } };

		Arrays.sort(abc, new ReverseComparator(3));

		util.Functions.arrayPrintTwoDimensions(abc);

	}

}
