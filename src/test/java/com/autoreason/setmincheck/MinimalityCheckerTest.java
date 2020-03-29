package com.autoreason.setmincheck;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public abstract class MinimalityCheckerTest<C extends Comparable<C>, M extends MinimalityChecker<C, Set<?>> > extends MatchIteratorTest<C, Set<?>, M> {
	
	M minimalityChecker;
	Collection<C> col;	// TODO create (random) collection of sets or/and load from file (use set-file-converter (dependency in pom))
	Set<?> test;
	
	
	@Test
	public void testIsMinimal() {
		 // compare with result from iterating over every element of set collection 
	}

	@Test
	public void testSubsetOf() {	
		Set<?> set = test;
		
		assertTrue(minimalityChecker.subsetOf(convert(set), test));
	}

	
	/**
	 * Convert a {@link Set} to an object of type {@code C}
	 * @param set A {@link Set}
	 * @return An object of type {@code C} that represents the given {@code set}
	 */
	public abstract C convert(Set<?> set);
	
	
}
