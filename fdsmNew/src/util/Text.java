package util;

import info.DataSource;
import info.dataReadWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Text {

	public static void textReader(String inputTXT) {

		ArrayList<String> list = new ArrayList<String>();

		try {

			Scanner s = new Scanner(new File(inputTXT));

			while (s.hasNext()) {
				list.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner read = new Scanner(System.in);
		
		String line = "1";
		
		while(10 > 0){
			
			System.out.println("Please give the number of the lines, which you want to show, please use the \",\" to split the numbers ");
			
			line = read.nextLine();
			
			if(line == "exit"){
				break;
			}
			
			String[] lineInfo = line.split(",");
			
			for(String e : lineInfo){
				
				System.out.print("line "+e+ System.lineSeparator());
				
				System.out.println(list.get(Integer.parseInt(e)));
				
				
			}
			
		}
		

		
		
		
		
	}
	
	public static void main(String[] args) {
		
//		textReader(dataReadWrite.selectedEntriesSecondaryIdTXT);
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Please input:");
		String line = s.nextLine();
		System.out.println(line);
		
		System.out.println(line == "exit");
		
	}

}
