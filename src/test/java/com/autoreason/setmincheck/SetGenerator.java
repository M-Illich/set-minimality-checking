package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Class to generate random sets for testing
 */
public class SetGenerator {

	/**
	 * Create a random {@link Set} of {@link Integer} values
	 * 
	 * @param maxSize An {@code int} defining the maximum number of elements
	 *                contained in the resulting set
	 * @return A {@link Set} with at most {@code maxSize} randomly drawn
	 *         {@link Integer} values
	 */
	public static Set<Integer> randomSet(int maxSize) {
		Random rdm = new Random();
		// create random set consisting of at most maxSize int values
		Set<Integer> set = new HashSet<Integer>();
		// define size
		int size = rdm.nextInt(maxSize) + 1;
		// add random int values to set until size reached
		while (set.size() != size) {
			set.add(rdm.nextInt());
		}
		return set;
	}

	/**
	 * Create a random {@link Set} of {@link Integer} values based on a provided
	 * seed
	 * 
	 * @param maxSize An {@code int} defining the maximum number of elements
	 *                contained in the resulting set
	 * @param seed    A {@code long} serving as seed for the drawing of random
	 *                {@link Integer} values
	 * @return A {@link Set} with at most {@code maxSize} randomly drawn
	 *         {@link Integer} values
	 */
	public static Set<Integer> randomSet(int maxSize, long seed) {
		Random rdm = new Random(seed);
		// create random set consisting of at most maxSize int values
		Set<Integer> set = new HashSet<Integer>();
		// define size
		int size = rdm.nextInt(maxSize) + 1;
		// add random int values to set until size reached
		while (set.size() != size) {
			set.add(rdm.nextInt());
		}
		return set;
	}

	/**
	 * Create a {@link Collection} of minimal {@link Set} elements consisting of
	 * random {@link Integer} values
	 * 
	 * @param numSets An {@code int} defining the number of sets contained in the
	 *                resulting collection
	 * @param maxSize An {@code int} defining the maximum number of elements
	 *                contained in the resulting set
	 * @param seed    A {@code long} serving as seed for the drawing of random
	 *                {@link Integer} values
	 * @return A {@link Collection} of {@link Set} elements from which none is a
	 *         subset of any other set included in the collection
	 */
	public static Collection<Set<Integer>> randomMinSetCollection(int numSets, int maxSize, long seed) {		
		// seed generator for sets
		Random seedGen = new Random(seed);
		
		// add random sets to collection until wanted size reached
		Collection<Set<Integer>> col = new HashSet<Set<Integer>>();
		while (col.size() < numSets) {			
			// create random set
			Set<Integer> set = randomSet(maxSize, seedGen.nextLong());
			// only add minimal set, i.e., no subset of set already in collection
			col = addMinimal(col, set);			
		}
		return col;
	}

	/**
	 * Add a {@link Set} to a {@link Collection} if none of its subsets occurs in
	 * the latter and furthermore remove all of its supersets to ensure that only
	 * minimal sets appear in the collection
	 * 
	 * @param col A {@link Collection} of minimal {@link Set} elements consisting of
	 *            {@link Integer} values
	 * @param set A {@link Set} of {@link Integer} values
	 * @return A {@link Collection} of minimal {@link Set} elements
	 */
	static Collection<Set<Integer>> addMinimal(Collection<Set<Integer>> col, Set<Integer> set) {
		// create modifiable copy of collection
		Collection<Set<Integer>> newCol = new HashSet<Set<Integer>>();
		newCol.addAll(col);
		
		// search for subsets in collection
		Iterator<Set<Integer>> iter = col.iterator();		
		boolean checkSubset = true;
		Set<Integer> cur;
		while (iter.hasNext()) {
			cur = iter.next();
			// check if element is subset of set
			if (checkSubset && set.containsAll(cur)) {
				// subset found -> set not added to collection
				return newCol;
			}
			// check if element is superset
			else if (cur.containsAll(set)) {
				// replace superset by set
				newCol.remove(cur);
				// no need to check for subsets if superset found due to minimality of sets in
				// collection
				checkSubset = false;
			}
		}

		// add set to collection
		newCol.add(set);

		return newCol;
	}

}
