package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public class MinimalityCheckerTest<C extends Comparable<C>, M extends MinimalityChecker<C, Set<?>> > extends MatchIteratorTest<C, Set<?>, M> {
	
	M minimalityChecker;
	Collection<C> col;	// TODO create (random) collection of sets or/and load from file (use set-file-converter (dependency in pom))
	Set<?> test;
	
	
	@Test
	public void testIsMinimal() {
		 // compare with result from iterating over every element of set collection 
	}

	@Test
	public void testSubsetOf() {
		
	}

	
	// TODO convert set to type C
	
	
}
