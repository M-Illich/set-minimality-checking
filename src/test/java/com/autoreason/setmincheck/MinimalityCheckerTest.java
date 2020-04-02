package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.datagenerator.SetGenerator;

public abstract class MinimalityCheckerTest<C extends Comparable<C>, M extends MinimalityChecker<C, Set<?>>>
		extends MatchIteratorTest<C, Set<?>, M> {

	// TODO maybe load from file (use set-file-converter (dependency in pom))

	@Test
	public void runMinChkTests() {
		// get random seed
		long seed = getSeed(); 					
		// conduct tests
		try {
			// create random sets
			int maxSize = 10;
			int numSets = 10;
			Set<Integer> test = SetGenerator.randomSet(maxSize, seed);
			Collection<Set<Integer>> sets = FileSetConverter.readSetsFromFile("src\\test\\resources\\minSets.txt");			
			
			// TODO Collection<Set<Integer>> sets = SetGenerator.randomMinSetCollection(numSets, maxSize, seed);
			// perform tests
			testSubsetOf(test);
			testIsMinimal(sets, test);

		} catch (Throwable e) {
			throw new RuntimeException("seed: " + seed, e);
		}
	}

	private void testIsMinimal(Collection<Set<Integer>> sets, Set<Integer> test) {
		// convert collection sets to type C
		Collection<C> col = convertCollection(sets);

		// compare with result from iterating over every element of set collection
		assertEquals(matchOperator.isMinimal(col, test), isMinimalSimple(sets, test));

	}

	private void testSubsetOf(Set<?> test) {		
		// create subset by removing one element
	// TODO termination problems ???
//		Set<?> subset = new HashSet<>(test);		
//		subset.remove(subset.iterator().next());
//			
//		assertTrue(matchOperator.subsetOf(convert(subset), test));
//		assertFalse(matchOperator.subsetOf(convert(test), subset));
	}

	/**
	 * Convert all {@link Set} elements from a {@link Collection} into objects of
	 * type {@code C}
	 * 
	 * @param sets A {@link Collection} of {@link Set} elements
	 * @return A {@link Collection} containing the {@code C}-ype representation of
	 *         given sets
	 */
	protected Collection<C> convertCollection(Collection<Set<Integer>> sets) {
		Collection<C> col = new HashSet<C>();
		// go through sets
		Iterator<Set<Integer>> iter = sets.iterator();
		while (iter.hasNext()) {
			// add set conversion to new collection
			col.add(convert(iter.next()));
		}
		return col;
	}

	/**
	 * Check if a {@link Set} is minimal w.r.t. a {@link Collection} by checking the
	 * subset relation with each element of the collection
	 * 
	 * @param sets A {@link Collection} of {@link Set} elements
	 * @param test A {@link Set} of {@link Integer} values
	 * @return {@code true} if the {@code col} does not contain any subset of
	 *         {@code test}, otherwise {@code false}
	 */
	private boolean isMinimalSimple(Collection<Set<Integer>> sets, Set<Integer> test) {
		// go through each element of the collection
		Iterator<Set<Integer>> iter = sets.iterator();
		while (iter.hasNext()) {
			// check subset relation
			if (test.containsAll(iter.next())) {
				// subset found -> not minimal
				return false;
			}
		}
		// no subset found -> minimal
		return true;
	}

}
