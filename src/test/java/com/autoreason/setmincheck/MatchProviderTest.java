package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import com.autoreason.setmincheck.setobjects.BitVectorSet;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;
import com.autoreason.setmincheck.setobjects.BoolVectorSet2;

public abstract class MatchProviderTest<C extends Comparable<C>, T, M extends MatchProvider<C, T>> {

	protected M matchOperator = initializeOperator();

	@Test
	public void runMatchProviderTests() {
		// get random seed
		long seed = getSeed(); 					
		// conduct tests
		try {
			testGetNextMatch(seed);
			testMatches(seed);

		} catch (Throwable e) {
			throw new RuntimeException("seed: " + seed, e);
		}
	}

	
	private void testGetNextMatch(long seed) {
		// define test objects
		T test = defineTest(seed);
		C previous = defineSmaller(convert(test));
		// compute next element
		C nextExpect = getNextMatchSimple(previous, test);
		C next = matchOperator.getNextMatch(previous, test);
		
//		// TEST TODO		
//		System.out.println("test: " + Long.toBinaryString(new BitVectorSet((Set) test).setRepresentation[0]));
//		System.out.println("prev: " +  Long.toBinaryString(((BitVectorSet) previous).setRepresentation[0]));
//		System.out.println("nextExpect: " +  Long.toBinaryString(((BitVectorSet) nextExpect).setRepresentation[0]));
//		System.out.println("next      : " +  Long.toBinaryString(((BitVectorSet) next).setRepresentation[0]));
//		System.out.println("nextExpect length: " +  ((BitVectorSet) nextExpect).setRepresentation.length);
//		System.out.println("next length: " +  ((BitVectorSet) next).setRepresentation.length);
		
//		System.out.println("test: " + Arrays.toString(new BoolVectorSet((Set) test).setRepresentation));
//		System.out.println("prev: " + Arrays.toString(((BoolVectorSet) previous).setRepresentation));
//		System.out.println("nextExpect: " + Arrays.toString(((BoolVectorSet) nextExpect).setRepresentation));
//		System.out.println("next      : " + Arrays.toString(((BoolVectorSet) next).setRepresentation));
//		System.out.println("nextExpect length: " +  ((BoolVectorSet) nextExpect).setRepresentation.length);
//		System.out.println("next length: " +  ((BoolVectorSet) next).setRepresentation.length);
		
//		System.out.println("test: " + (new BoolVectorSet2((Set) test).setRepresentation.toString()));
//		System.out.println("prev: " + ((BoolVectorSet2) previous).setRepresentation.toString());
//		System.out.println("nextExpect: " + ((BoolVectorSet2) nextExpect).setRepresentation.toString());
//		System.out.println("next      : " + ((BoolVectorSet2) next).setRepresentation.toString());
//		System.out.println("nextExpect length: " +  ((BoolVectorSet2) nextExpect).setRepresentation.size());
//		System.out.println("next length: " +  ((BoolVectorSet2) next).setRepresentation.size());
		
		assertTrue(nextExpect.compareTo(next) == 0);
	}

	private void testMatches(long seed) {
		// define test object
		T test = defineTest(seed);
		// convert test to type C
		C testC = convert(test);
			
		// TEST TODO	
//		System.out.println("test: " + Long.toBinaryString(((BitVectorSet) testC).setRepresentation[0]));
//		System.out.println("defi: " + Long.toBinaryString(((BitVectorSet) defineMatch(testC)).setRepresentation[0]));
//		System.out.println("test length: " +  ((BitVectorSet) testC).setRepresentation.length);
//		System.out.println("defi length: " +  ((BitVectorSet) defineMatch(testC)).setRepresentation.length);
//		System.out.println("next length: " +  ((BitVectorSet) next).setRepresentation.length);
//		System.out.println(new BoolVectorSet2((Set) test).setRepresentation.toString());
//		System.out.println(((BoolVectorSet2) defineMatch(testC)).setRepresentation.toString());
		
		// perform test
		assertTrue(matchOperator.matches(defineMatch(testC), test));
		assertFalse(matchOperator.matches(defineNotMatch(testC), test));
	}

	private C getNextMatchSimple(C previous, T test) {
		// convert test to type C
		C testC = convert(test);
		
		// return null if next match cannot be smaller than test
		if (previous.compareTo(testC) > -1) {			
			return null;
		}

		// consider in a sorted order all elements of type C greater than previous until
		// one matches test
		C next = previous;
		while (next.compareTo(testC) < 1) {
			// get next C object greater than previous
			next = getNext(next);
			// check if next matches test
			if (matchOperator.matches(next, test)) {
				// match found -> stop search
				break;
			}
		}
		return next;
	}

	/**
	 * Convert an object of type {@code T} to type {@code C}
	 * 
	 * @param test An object of type {@code T}
	 * @return An object of type {@code C} that represents {@code test}
	 */
	protected abstract C convert(T test);

	/**
	 * Get the next element after {@code previous} based on an assumed order of
	 * every possible element of type {@code C}
	 * 
	 * @param previous An object of type {@code C}
	 * @return An object of type {@code C} that is the smallest object that is
	 *         greater than {@code previous}
	 */
	protected abstract C getNext(C previous);

	/**
	 * Define an object of type {@code C} that {@link MatchProvider#matches} the
	 * given {@code test}
	 * 
	 * @param test An object of type {@code C}
	 * @return An object {@code o} of type {@code C} for which
	 *         {@link MatchProvider#matches(o,test)} returns {@code true}
	 */
	protected abstract C defineMatch(C test);

	/**
	 * Define an object of type {@code C} that does not satisfy
	 * {@link MatchProvider#matches} with the given {@code test}
	 * 
	 * @param test An object of type {@code C}
	 * @return An object {@code o} of type {@code C} for which
	 *         {@link MatchProvider#matches(o,test)} returns {@code false}
	 */
	protected abstract C defineNotMatch(C test);

	/**
	 * Define an object of type {@code C} that is smaller than the given
	 * {@code test}
	 * 
	 * @param test An object of type {@code C}
	 * @return An object {@code o} of type {@code C} that is smaller than
	 *         {@code test}, which means that {@code o.compareTo(test) == -1}
	 */
	protected abstract C defineSmaller(C test);

	/**
	 * Define an object of type {@code T} that can be used for testing
	 * 
	 * @param seed A {@link long} value used as seed to generate a random object
	 * @return An object of type {@code T}
	 */
	protected abstract T defineTest(long seed);

	/**
	 * Return the currently used seed for the generating of random test objects
	 * @see java.util.Random#Random(long) 
	 * @return A {@link long} value that serves as random seed
	 */
	protected abstract long getSeed();
	
	/**
	 * Initialize the object to call the tested methods
	 * @return An object of type {@code M}
	 */
	protected abstract M initializeOperator();

}
