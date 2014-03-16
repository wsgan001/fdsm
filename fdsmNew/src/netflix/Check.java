package netflix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.RandomAccess;

public class Check {

	public static String movieTitleTXT = info.DataSource.Pfad_doc
			+ "movie_titles.txt";

	public static void readGL_Top(String levFDSM_GL_TXT, int top) {

		ArrayList<String[]> movieTitles = new ArrayList<String[]>();

		try {

			// Read the movie titles into an Arraylist
			BufferedReader br = new BufferedReader(
					new FileReader(movieTitleTXT));

			String line = br.readLine();
			line = br.readLine();

			while (line != null) {

				String[] content = line.split(",", 3);

				movieTitles.add(Arrays.copyOfRange(content, 1, 3));

				line = br.readLine();
			}

			br.close();

			// Read the global list.
			br = new BufferedReader(new FileReader(levFDSM_GL_TXT));

			line = br.readLine();

			HashMap<String, String> hm = util.Text.readLineInfos(line);

			int numberOflines = Integer.parseInt(hm.get("length"));

			if (Math.abs(top) > numberOflines) {
				System.err
						.println("You can't input a number which bigger than the number of the total cooccurences.");
				System.exit(-1);
			}

			int begin = 0;
			
			if (top<0) {
				begin = numberOflines + top;
			}
			
			
			
			
			
			
			
			
			
			
			
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public static void test() {

	}

	public static void main(String[] args) {
		readGL_Top(algo.levFDSM.levFDSM_GL_TXT, 100);

	}

}
