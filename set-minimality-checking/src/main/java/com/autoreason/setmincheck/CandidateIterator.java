package com.autoreason.setmincheck;
import java.util.Set;

/**
 * 
 *
 * @param <C>
 */
public interface CandidateIterator<C extends Comparable<C>> {
	
	
	/**
	 * Get the next candidate of test that is greater than previous
	 * @param previous
	 * @param test
	 * @return object of type {@code C} which is greater than previous and a candidate of test based on {@link #isCandidateOf(Comparable, Comparable)}
	 * or NULL if no such element exists
	 */
	C getNextCandidate(C previous, C test);
	
	/**
	 * Check if first is a certain candidate of second (e.g. subset)
	 * @param first An object of type {@code C} representing a set
	 * @param second An object of type {@code C} representing a set
	 * @return TRUE if first satisfies certain conditions to be a candidate of second, otherwise FALSE
	 */
	boolean isCandidateOf(C first, C second);
	
	/**
	 * Convert a {@code Set} to the same representation as given by {@code example}
	 * @param example An object of type {@code C} representing a {@code Set}
	 * @param set A {@code Set}
	 * @return An object of type {@code C} that represents the given {@code Set} based on {@code example's} representation
	 */
	C convert(C example, Set<?> set);
	
		
}