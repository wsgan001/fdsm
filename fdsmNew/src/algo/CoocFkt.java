package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

import util.MyBitSet;

import util.Functions;

public class CoocFkt {

	/**
	 * swap the int[][] edges and MyBitSet[] degreeM
	 * 
	 * @param lengthOfWalks
	 * @param edges
	 * @param adjM
	 */
	public static void swap(int lengthOfWalks, int[][] edges, MyBitSet[] adjM,
			int seed) {
		int numberOfEdges = edges.length;

		int unswapable = 0;

		// Begin swap and change cooc.
		Random generator_edge = new Random(seed);

		for (int i = 0; i < lengthOfWalks; i++) {

			// Find any edge pair.
			int edgeNumber1 = generator_edge.nextInt(numberOfEdges);
			int edgeNumber2;
			do {
				edgeNumber2 = generator_edge.nextInt(numberOfEdges);
			} while (edgeNumber1 == edgeNumber2);

			// Check if the edge pair are swapable

			int user_m = edges[edgeNumber1][0];
			int film_p = edges[edgeNumber1][1];
			int user_n = edges[edgeNumber2][0];
			int film_q = edges[edgeNumber2][1];

			// Check if they are swapable.
			if (adjM[user_m].get(film_q) == true
					|| adjM[user_n].get(film_p) == true) {
				unswapable++;
				continue;
			}

			// If they are swapable, then

			// change the edges's Information
			edges[edgeNumber1][1] = film_q;
			edges[edgeNumber2][1] = film_p;

			// change the degreeM's Information
			adjM[user_m].flip(film_p);
			adjM[user_m].flip(film_q);
			adjM[user_n].flip(film_p);
			adjM[user_n].flip(film_q);

		}

		System.out.println("unswapable steps = " + unswapable);

	}

	/**
	 * calculate cooccurence from a bipartite Graph MyBitSet[], every MyBitSet
	 * from this array represent the friends(PrimaryIds) of a SecondaryId.
	 * 
	 * @param adjM
	 *            Format: SecondaryId: PrimaryId_1, PrimaryId_2...
	 * @param coocc
	 *            a two dimensional array, with stores the value of coocurence
	 */
	public static void readCooccSecTopRight(MyBitSet[] adjM, int[][] coocc) {

		int adjM_length = adjM.length;

		int[] primaryIds = null;

		for (int i = 0; i < adjM_length; i++) {

			primaryIds = adjM[i].toArray();

			int i_length = adjM[i].length();

			for (int j = 0; j < i_length; j++) {

				for (int k = j + 1; k < i_length; k++) {

					coocc[primaryIds[j]][primaryIds[k]]++;

				}
			}

		}

	}

	/**
	 * calculate cooccurence from a bipartite Graph MyBitSet[], every MyBitSet
	 * from this array represent the friends(PrimaryIds) of a SecondaryId.
	 * 
	 * @param adjM
	 *            Format: SecondaryId: PrimaryId_1, PrimaryId_2...
	 * @param coocc
	 *            a two dimensional array, with stores the value of coocurence
	 */
	public static void readCooccSecLowerLeft(MyBitSet[] adjM, int[][] coocc) {

		int adjM_length = adjM.length;

		int[] primaryIds = null;

		for (int i = 0; i < adjM_length; i++) {

			primaryIds = adjM[i].toArray();

			int i_length = adjM[i].length();

			for (int j = 0; j < i_length; j++) {

				for (int k = j + 1; k < i_length; k++) {

					coocc[primaryIds[k]][primaryIds[j]]++;

				}
			}

		}

	}

	public static void readCooccPrimaryTopRight(MyBitSet[] adjM, int[][] coocc) {

		int adjM_length = adjM.length;

		for (int i = 0; i < adjM_length; i++) {

			for (int j = i + 1; j < adjM_length; j++) {

				coocc[i][j] = adjM[i].myand(adjM[j]).cardinality();

			}

		}

	}

	public static void readCooccPrimaryLowerLeft(MyBitSet[] adjM, int[][] coocc) {

		int adjM_length = adjM.length;

		for (int i = 0; i < adjM_length; i++) {

			for (int j = i + 1; j < adjM_length; j++) {

				coocc[j][i] = adjM[i].myand(adjM[j]).cardinality();

			}

		}

	}



	public static void main(String[] args) {

		


	}

}
