package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class to generate random sets for testing
 * 
 *
 */
public class SetGenerator {
	
	/**
	 * Create a random set of {@code int} values
	 * @param maxSize An {@code int} defining the maximum number of elements contained in the resulting set
	 * @return A {@code Set} with at most maxSize randomly drawn {@code int} values
	 */
	public static Set<Integer> randomSet(int maxSize){
		Random rdm = new Random();
		// create random set consisting of at most maxSize int values
		Set<Integer> set = new HashSet<Integer>();
		// define size
		int size = rdm.nextInt(maxSize) + 1;
		// add random int values to set until size reached
		while(set.size() != size) {
			set.add(rdm.nextInt());
		}		
		return set;
	}

	/**
	 * Create a random set of {@code int} values based on a provided seed
	 * @param maxSize An {@code int} defining the maximum number of elements contained in the resulting set
	 * @param seed A {@code long} serving as seed for the drawing of random {@code int} values
	 * @return A {@code Set} with at most maxSize randomly drawn {@code int} values
	 */
	public static Set<Integer> randomSet(int maxSize, long seed){
		Random rdm = new Random(seed);
		// create random set consisting of at most maxSize int values
		Set<Integer> set = new HashSet<Integer>();
		// define size
		int size = rdm.nextInt(maxSize) + 1;
		// add random int values to set until size reached
		while(set.size() != size) {
			set.add(rdm.nextInt());
		}		
		return set;
	}
	
	
	// TODO create a collection of sets
	public static Collection<Set<Integer>> randomSetCollection(int numSets, int maxSize, long seed){
		Collection<Set<Integer>> col = new HashSet<Set<Integer>>();
		
		// TODO only add minimal Set elements, i.e., no subset already in collection
		
		return col;
		
	}
	
}
