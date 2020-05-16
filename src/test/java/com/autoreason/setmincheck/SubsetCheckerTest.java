package com.autoreason.setmincheck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.autoreason.setmincheck.setobjects.SetRepresent;

public abstract class SubsetCheckerTest<C extends SetRepresent<?>> {

	// create random test objects based on seed
	protected static final Random SEED_GENERATOR = new Random();
	protected static final long currentSeed = SEED_GENERATOR.nextLong(); // Long.valueOf("8270419487638343139"); //
	
	SubsetChecker<C> subsetChecker = defineSubsetChecker();

	@Test
	public void runSubsetCheckerTest() {
		// conduct tests
		try {
			testSubsetOf(currentSeed);
		} catch (Throwable e) {
			throw new RuntimeException("seed: " + currentSeed, e);
		}
	}

	private void testSubsetOf(long seed) {
		// get test set
		Set<Integer> test = defineTest(seed);
		// test with subset candidate
		C cand = defineSubsetCand(test);
		assertTrue(subsetChecker.subsetOf(cand, test));
		// define non-subset candidate
		cand = defineNotSubsetCand(test);
		assertFalse(subsetChecker.subsetOf(cand, test));
	}

	/**
	 * Define an instance of a {@link SubsetChecker} implementation
	 * 
	 * @return An instance of a {@link SubsetChecker} implementation
	 */
	protected abstract SubsetChecker<C> defineSubsetChecker();

	/**
	 * Define a {@link SetRepresent} instance that represents a subset of the given
	 * {@link Set}
	 * 
	 * @param set A {@link Set} of {@link Integer} values
	 * 
	 * @return An object {@code cand} implementing {@link SetRepresent} for which
	 *         {@link SubsetChecker#subsetOf(cand, set)} = {@code true}
	 */
	protected abstract C defineSubsetCand(Set<Integer> set);

	/**
	 * Define a {@link SetRepresent} instance that does not represent a subset of
	 * the given {@link Set}
	 * 
	 * @param set A {@link Set} of {@link Integer} values
	 * 
	 * @return An object {@code cand} implementing {@link SetRepresent} for which
	 *         {@link SubsetChecker#subsetOf(cand, set)} = {@code false}
	 */
	protected abstract C defineNotSubsetCand(Set<Integer> set);

	/**
	 * Define an {@link Set} of {@link Integer} values that can be used for testing
	 * 
	 * @param seed A {@link long} value used as seed to generate a random
	 *             {@link Set}
	 * @return A {@link Set} of randomly generated {@link Integer} values based on
	 *         {@code seed}
	 */
	protected abstract Set<Integer> defineTest(long seed);

}
