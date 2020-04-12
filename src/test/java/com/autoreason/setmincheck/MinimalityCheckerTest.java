package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.datagenerator.SetGenerator;
import com.autoreason.setmincheck.setobjects.SetRepresent;

public abstract class MinimalityCheckerTest<C extends SetRepresent<C, ?, ?>, M extends MinimalityChecker<C, Set<?>>>
		extends MatchIteratorTest<C, Set<?>, M> {

	// object representing a set
	protected C c = initSetRepresent();

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
			// use stored test file
			// Collection<Set<Integer>> sets =
			// FileSetConverter.readSetsFromFile("src\\test\\resources\\minSets.txt");
			// create random collection
			Collection<Set<Integer>> sets = SetGenerator.randomMinSetCollection(numSets, maxSize, seed);
			// perform tests
			testSubsetOf(test);
			testIsMinimal(sets, test);

		} catch (Throwable e) {
			throw new RuntimeException("seed: " + seed, e);
		}
	}

	private void testIsMinimal(Collection<Set<Integer>> sets, Set<Integer> test) {
		// convert collection sets to type C
		NavigableSet<C> col = new TreeSet<C>(c.convertCollection(sets));

		// compare with result from iterating over every element of set collection
		assertEquals(matchOperator.isMinimal(col, test), isMinimalSimple(sets, test));

	}

	private void testSubsetOf(Set<?> test) {
		// create subset by removing one element
		Set<?> subset = new HashSet<>(test);
		subset.remove(subset.iterator().next());

		assertTrue(matchOperator.subsetOf(convert(subset), test));
		assertFalse(matchOperator.subsetOf(convert(test), subset));
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
	public static boolean isMinimalSimple(Collection<Set<Integer>> sets, Set<Integer> test) {
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

	@Override
	public C convert(Set<?> test) {
		return c.convertSet(test);
	}

	/**
	 * Initialize the {@link SetRepresent} object by some arbitrary object of the
	 * subclass type {@code C}
	 * 
	 * @return An object of type {@code C} being a subclass of {@link SetRepresent}
	 */
	protected abstract C initSetRepresent();

}
