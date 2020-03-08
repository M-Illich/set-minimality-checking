package com.autoreason.setmincheck;

import java.util.NavigableSet;
import java.util.Set;

public abstract class SetMinimalityChecker<C extends Comparable<C>> implements CandidateIterator<C> {

	
	// TODO alternative: only consider one representation at a time
		
	/**
	 * Check if the {@code Set} {@code testSet} is minimal wr.t. {@code Set} elements represented by {@code collection}
	 * @param testSet A {@code Set}
	 * @param collection A {@code NavigableSet} of elements that represent {@code Set} objects
	 * @return TRUE if no subset of {@code testSet} appears in {@code collection}, otherwise FALSE
	 */
	boolean isMinimal(Set<?> testSet, NavigableSet<C> collection) {	
		// start with first element 
		C current = collection.first();
		// perform subset test for candidate elements of collection
		while(current != null) {
			// convert testSet to same representation as current
			C test = convert(current, testSet);
			// iterate over subset candidates of test based on current representation
			while(current != null) {
				// perform subset test
				if(isSubsetOf(current, test)) {
					// subset found -> not minimal
					return false;
				}
				// get next candidate
				C next = getNextCandidate(current, test);
				if(next == null) {
					// no further candidates for current representation available
					// look for next element with other representation in collection
					current = collection.ceiling(getMaxValue(current));					
					break;
				}				
				// select next candidate element from collection
				current = collection.ceiling(next);
			}			
		}	
		// no subset found -> testSet is minimal w.r.t. collection
		return true;
	}
	
	/**
	 * Check if {@code first} represents a subset of {@code second}
	 * @param first
	 * @param second
	 * @return TRUE if {@code first} represents a subset of {@code second}, otherwise FALSE
	 */
	public abstract boolean isSubsetOf(C first, C second);
	
	
	/**
	 * Get the maximum value that can be represented by the object {@code o} of type {@code C}
	 * @param o An object of type {@code C} representing a {@code Set}
	 * @return The maximum value that can be represented by an object with same representation as the provided element {@code o}
	 */
	public abstract C getMaxValue(C o);
	

}
