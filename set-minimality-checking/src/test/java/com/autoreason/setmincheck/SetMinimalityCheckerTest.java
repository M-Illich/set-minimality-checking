package com.autoreason.setmincheck;

import java.util.NavigableSet;
import java.util.Set;

public abstract class SetMinimalityCheckerTest<C extends Comparable<C>> implements CandidateIterator<C> {

	
	// TODO tests
		
	/**
	 * Check if the Set testSet is minimal wr.t. Set elements represented by collection
	 * @param testSet A Set
	 * @param collection A NavigableSet of elements that represent Set objects
	 * @return TRUE if no subset of testSet appears in collection, otherwise FALSE
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
	 * Check if first represents a subset of second
	 * @param first
	 * @param second
	 * @return TRUE if first represents a subset of second, otherwise FALSE
	 */
	public abstract boolean isSubsetOf(C first, C second);
	
	
	/**
	 * Get the maximum value that can be represented by the object o of type C
	 * @param o An object of type C representing a set
	 * @return The maximum value that can be represented by an object with same representation as o
	 */
	public abstract C getMaxValue(C o);
	

}
