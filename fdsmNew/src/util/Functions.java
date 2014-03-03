package util;

public class Functions {

	public static void arrayPrintTwoDimensions(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {

			System.out.print("line " + i + " : ");

			for (int j = 0; j < arr.length; j++) {

				System.out.print(arr[i][j] + ",");
			}

			System.out.print(System.lineSeparator());

		}

	}

	public static void main(String[] args) {

	}

}
