package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Functions {

	public static void arrayPrintTwoDimensions(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {

			System.out.print("line " + i + " : ");

			for (int j = 0; j < arr[i].length; j++) {

				System.out.print(arr[i][j] + ",");
			}

			System.out.print(System.lineSeparator());

		}

	}
	
	public static void serObjectWrite_wrap(String outPutFile, Object obj) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(outPutFile));
			os.writeObject(obj);
			os.close();
			os = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Object serObjectRead_wrap(String serFile){
		
		Object ob = new Object();
		
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(serFile));
			ob = is.readObject();
			is.close();
			is = null;

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return ob;
		
	}

	public static void main(String[] args) {

	}

}
